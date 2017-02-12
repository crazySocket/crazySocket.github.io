---
layout: post
title:  "optimistic locking basics in java"
date:   2017-02-12 00:27:55 +0100
categories: java concurrency
---

## fast, lean, frightening - what industry desires

Have you ever got this feeling looking at ludicroulsy long stacktrace, deploying 200MB war, restarting web app running on tomcat on virtual machine that java might be somewhat heavy? I mean each function call adds frame to stacktrace. So when you scroll through nested exceptions you must wonder - how much it all costs?

What about application containers? These feel like necessary evil; especially during development. Why must I push every single bit of application every time? Just imagine you are a new developer in the team. You get a (maven) project with configuration expanded beyond freaking time and space. You propably think: god save me from ever modifying that. So you comply, do your job, build and push the whole war as soon as it builds.

With experience like this - traumas piling up - you come across for instance [projectreactor][reactor] that promises the tools their provide can "sustain high throughput rates on the order of 10's of millions of messages per second". Yeah, right. Laughable.

However, as time passes by you dropped the ill (maven) configuration and replaced with something more sensible (gradle), removed tomcat from development for jetty plugin or (even better) nice integration tests. You managed to lower build time from half an hour to a minute and startup from 5 minutes to 30 seconds. Now you cannot help but wonder: why the project did not look like this before? Ahh, I know. Let's not delve in the past; let it rest in peace.

How now look these "10's of millions per second"? Well, seriously? To be honest I still doubt it. I mean, who needs SOAP interface to serve such traffic? Ahh, I forgot SOAP has been somewhat condemned. Nowadays marketing guys jerks off with REST. Even suggesting that SOAP might have some advantages makes you frowned upon. Not that these people now the difference. REST sells, SOAP does not. Its that simple.

What else sells today? Scalability? High throughput? SaaS? Low development cost? Ohh, the last one always sells. Client does not really care or see that cost. Your boss does. And they will always go for least amount of effort. Think about it; why script languages gather such interest? You are not gonna set up a service with bash, are you? With bash not, but with javascript? Sure. Now you entered nodejs realm.

My introduction got little off the topic so let's get back on track. To summarize, industry wants to achieve always available service that is easy to create and maintain. In order to achieve these goals we need tools that are both simple to use and powerful. [reactor] promises "10's of millions" to advertise how powerful the solution is.

How to measure easiness? Take for instance development time taken to use/apply a tool. You need database? Get an image from [dockerhub] and deploy into your cloud via [docker]. Now add some service. Ever heard of [aws lambda]? Anyway your boss just stopped timer. 5 min - good enough - they say. (It is oversimplification, I know, deal with it).

Where the hell is optimistic locking, as topic suggests, in all this? You might ask that. Patience, my son, is a virtue. Why you think I mentioned amazon service called lambda? Lambda operates on events. The idea of integrating totally incompatible software via simple messages is essential for big companies. They have loads of software no one knows how to develop anymore. And they do make money. With great afford company's maintainance team managed to make the software produce and accept some kind of messages we call events. The events are handled by specialized broker. If you are interested in real-life example read introduction to [apache kafka].

That is where [reactor] wants to place itself. The tools they give you are designed to handle the stream of events and push them to java-8-like stream. [reactor] does not propagate events to cluster; just accepts them and locally process. You might think all you need is to implement producer-consumer in java. Smash ArrayBlockingQueue and you are done. Why bother with 3-rd party libraries? You can approch the problem from this side, however, your solution will be much slower - not even close.

The part responsible for handling "10's of millions per second" is [reactor-core]. The concurrency pattern used there is what made me write this post. How reactor-core works is so interesting that I shall write a post about its design one day. The project takes advantage of past eperiences. Even general description will require serious research. Task will be challenging; the post lengthy. Here I decide to narrow my interests to basics.

## lock free computations - make it faster

As I argue above the measure of software quality is how many requests/jobs you can process in time unit. There are two options to increase the flow: increase speed of processor or number of processors. Third option would be to avoid network connections but I gonna ignore it here. With some work and attention to details you can polish your computations so that they take little time. The issue arises when you pass results down the pipeline. In each system tasks depend upon previous ones. With limited processing power at our disposal we have to somehow connect jobs done with jobs pending.

The pipeline idea might be little vague; let's scetch an example. Have you ever worked with tomcat? It goes like this: tomcat listens for connections on selected port (8080 by default). Once connection received, new thread is created. According to configuration a servlet is chosen. Your job as developer is to supply application that contains servlet class. Instance is created and a method called. Now the thread goes deeper and deeper into your code. Perhaps you need to select data from database, maybe consult service on another host. So you do. Request has been send. Now we wait. And wait. Still waiting. What are we doing right now? Waiting. The thread hangs. What should thread/process manager do? Context switch. The idle process is shelved and processor time is given to another one. In our case next request arrived and a thread has been task to serve it.

Is that a problem? As traffic grows it is going to be. Context switch takes time. It's a job of the developer of the operating system to optimise the procedure but still context switch is considered heavy duty (more on [thread switching heaviness][into the wild async io]). How often switch happens? Tomcat creates new thread every request. Many request - many switches. To sum up, the request is served almost instantaneously or user perceives lags.

This must be a big deal since java introduces work stealing and ForkJoinTasks. What is work stealing? The job is send to pool thread manager who either creates new thread or reuse available. When that thread turns idle, instead of waiting it takes another job that is managed by thread pool. What about the previous job? When it resumes, another thread takes it. Esentially threads 'steal' jobs from each other; job does not stay assigned to one and only thread. A thread does not wait, instead, executes another job. That reduces switching. What about ForkJoinTasks? It is a class introduced in java 7 that can spawn separate tasks but somehow these tasks, even though they fork, are lighter than threads. It seems you can arrange with decreasing heaviness process > thread > ForkJoinTask. ForkJoinPool is an implementation of thread manager (ExecutorService) that employs work stealing (in opposition to ThreadPoolExecutor). Maybe that is why the task is lighter?

What is the hidden advantage we are looking for? What exactly are we trying to achieve? We want to keep processor busy with work we are interested in. Not just spinning cpu; get results, avoid unrelated workload. Namely waiting. Ok. Our thread from now on does not wait. Focus on job. Work hard. And then - you have to pass the data down the pipeline. After all what is the point of computation when no one wants the result? The data is stored in memory. Either we make some interrupt or function call to notify observer - other thread (no, no, our thread is not waiting but someone's else, who is clearly not as great of a computer scientis as we are, might) or device.

If we have two threads working in tandem and none is waiting then both access memory. The memory is the board they use to signal each other. How should they go about it? Is reading and writing to memory plain and simple or we have to use some protocol? Here wild theory ends; there are innate limitations we must accept.

Let's say your cpu can run two threads concurrently (single or multi processor unit - whatever works). They both access same memory. Since memory is separate device cpu mirrors chunk of it into its local memory called cache. Each thread might have private cache (multi processor cpu) that is periodically synchronized with regular memory. If both threads run the same code they access same variables. When they write at similar times they are likely to overwrite the value - if they differ then one is lost; what did you waste the cpu time for? That is a valid use case. How can we fix it?

There is solution but first you have to understand concept of transaction. Databases use them extensively. First you mark the start. Prepare query. In the end you commit - push query to database. The commit may fail if data no longer meets restrictions. Maybe external id is now invalid? Removed in the meantime. You have to accept the fact and prepare next, hopefully valid query and make commit - maybe this time will work. You could use some locks to queue other threads trying to access database but would it not defeat our purpose?

The transactional approach - calculate change, apply, if failed try again - is called [optimistic locking][optimistic concurrency control]. It is 'optimistic' because we spend processing power in faith the outcome will be still valid the moment we try to store it. Even though the transaction might succeed the very first attempt you should wrap the code in a loop that ends when data is stored. Save must be atomic (fail or succeed - no middle ground) and isolated (result of concurrent calls is same to sequential execution) (the meaning like in abbreviation [ACID]).

## implementation - try new and compare to synchronized method

The implementation of that loop is used in [reactor] to modify sequence (basically long) and repeat if failed. First you read long, next increment, then compare and swap. The compare and swap is special x86 instruction that stores data only if target is equal to expected value - the value we read last time and based our calculations upon. Moreover, the command locks; no other instruction can be executed simultaneously; cpu guarantees isolation. This is the fast processor's lock not the slow operating system's. The compare and swap instruction is so fundamental for the loop that I call the loop 'CAS loop'. This loop is the solution for thread overwriting same variable problem stated above. One thread still does lose time performing incorrect calculations, however, now they realize their result is no longer valid - memory contains correct outcome.

The CAS loop seems to carry these "10's of millions" [reactor] promises. I would like to use this power. I wondered 'can I write a simple program that speeds up thanks to the CAS loop?'. What if I increment variable in the loop? You cannot get anything simpler. Spawn some threads and you got yourself a test.

I created test in java and measured time taken for different counters.

```java
public class Test {
  private interface Counter {
    int get();
    int increment();
  }

  private static final int ITERATIONS = 1000000;
  private static final int THREADS_COUNT = 100;

  private static Runnable runnable(Counter counter) {
    return () -> {
      for(int i=0; i<ITERATIONS; i++) {
        counter.increment();
      }
    };
  }

  private static void run(Counter counter) {
    Collection<Thread> threads = new HashSet<>(THREADS_COUNT);
    for(int i=0; i<THREADS_COUNT; i++) {
      threads.add(new Thread(runnable(counter)));
    }
    System.out.println(counter.getClass().getSimpleName());
    long start = System.currentTimeMillis();
    threads.forEach(Thread::start);
    threads.forEach((thread) -> {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    long stop = System.currentTimeMillis();
    System.out.println("job took " + (stop-start));
  }
}
```

Now we need an implementation and main(). Let's start with the simplest, exclusive-access

```java
private static class LockCounter implements Counter {
  private int i = 0;
  @Override
  public int get() {
    return i;
  }
  @Override
  public synchronized int increment() {
    return ++i;
  }
}

public static void main(String[] args) {
  run(new LockCounter());
}
```

Compile, run and we get

    LockCounter
    job took 4187

LockCounter serves as reference implementation. Java has AtomicInteger class. It allows for atomic operations on integer and has compare and spap method. Let's try them

```java
private static class AtomicCounter implements Counter {
  AtomicInteger i = new AtomicInteger(0);
  @Override
  public int get() {
    return i.get();
  }
  @Override
  public int increment() {
    return i.incrementAndGet();
  }
}

private static class ManualAtomicCounter extends AtomicCounter {
  @Override
  public int increment() {
    int a;
    do {
      a = i.get();
    } while(!i.compareAndSet(a, a+1));
    return a+1;
  }
}

public static void main(String[] args) {
  run(new AtomicCounter());
  run(new ManualAtomicCounter());
}
```

    AtomicCounter
    job took 1587
    ManualAtomicCounter
    job took 10480

What the hell? AtomicCounter meets expectations but what happened to ManualAtomicCounter? Isn't CAS loop supposed to be faster? Maybe the AtomicInteger.compareAndSet() method is badly implemented. You can inspect the code to see how AtomicInteger works (use for instance IntelliJ's decompiler). It uses Unsafe class; the class so internal that only jvm is allowed to create an instance. You can go around this restriction but only if you leave well-bred java developer domain and enter hackerdom.

```java
private static abstract class UnsafeCounter implements Counter {
  static final Unsafe unsafe;
  static final long offset;

  static {
    try {
      Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
      theUnsafe.setAccessible(true);
      unsafe = (Unsafe) theUnsafe.get(null);
      offset = unsafe.objectFieldOffset(
          UnsafeCounter.class.getDeclaredField("i")
      );
    } catch (Exception ex) { throw new Error(ex); }
  }

  volatile int i = 0;
}

private static class UnsafeNoLoopCounter extends UnsafeCounter {
  @Override
  public int get() {
    return i;
  }
  @Override
  public int increment() {
    return unsafe.getAndAddInt(this, offset, 1) + 1;
  }
}

private static class UnsafeLoopCounter extends UnsafeCounter {
  @Override
  public int get() {
    return i;
  }
  @Override
  public int increment() {
    int a;
    do {
      a = unsafe.getIntVolatile(this, offset);
    } while(!unsafe.compareAndSwapInt(this, offset, a, a+1));
    return a+1;
  }
}

public static void main(String[] args) {
  run(new UnsafeNoLoopCounter());
  run(new UnsafeLoopCounter());
}
```

    UnsafeNoLoopCounter
    job took 1597
    UnsafeLoopCounter
    job took 10419

It cannot be! I am not gonna give up yet. Let's go native! There is one good [jni tutorial] you could read but you do not have to grasp [jni][jni wiki] too thoroughly to follow this post. First java stub

```java
private static class NativeCounter implements Counter {
  static {
    System.loadLibrary("NativeCounter");
  }
  @Override
  public native int get();
  @Override
  public native int increment();
}

public static void main(String[] args) {
  run(new NativeCounter());
}
```

See how simple? Put `native` keyword in front of the method. Obviously there is no body. We must supply these bodies somehow. Whas does it mean to create native method? The universal language for computers is assembler, however, for operating systems the language is C. Even though C itself is portable the libraries are not. The libraries are delivered as some kind of object files; a file contains assembler code with markings called symbols. Some symbols mark functions. And that is what we have to deliever to java to make native methods work.

Fortunately, java can help us. Run `javah Test`. That creates C header file for the functions we must deliver. You should end up with file Test_NativeCounter.h

```java
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class Test_NativeCounter */

#ifndef _Included_Test_NativeCounter
#define _Included_Test_NativeCounter
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     Test_NativeCounter
 * Method:    get
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_Test_00024NativeCounter_get
  (JNIEnv *, jobject);

/*
 * Class:     Test_NativeCounter
 * Method:    increment
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_Test_00024NativeCounter_increment
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
```

There are some rules behind these signatures. Read more in [jni tutorial]. Test_NativeCounter.c contains functions that access integer variable. External function 'increase' is point of entry to assembler. The signature `int increase(int*)` says we must create object file with symbol `increase`. Once we have symbols `Java_Test_00024NativeCounter_get`, `Java_Test_00024NativeCounter_increment` and `increase` we can build the shared object (object file, library). The instruction `System.loadLibrary("NativeCounter")` tells java to load library `libNativeCounter.so` from `java.library.path`. Once library is loaded java can use `NativeCounter` as any other class.

```java
#include "Test_NativeCounter.h"

int i = 0;

extern int increase(int *);

JNIEXPORT jint JNICALL Java_Test_00024NativeCounter_get
(JNIEnv *env, jobject this) {
  return i;
}

JNIEXPORT jint JNICALL Java_Test_00024NativeCounter_increment
(JNIEnv *env, jobject this) {
  return increase(&i);
}
```

Function 'increase' is defined extern because I want to make sure I have the best compare and swap implementation; I want to be as close to processor as possible hence we go down to forbidden realm of assembler.

increase.asm
```asm
global increase

section .text
increase:
mov rax, [rdi]

retry:
mov rsi, rax
inc rsi
lock
cmpxchg [rdi], rsi
dec rsi
cmp rax, rsi
jnz retry
inc rax

ret
```

This works only on linux due to function calling convention. But it is not a problem, isn't it? After all software developers use serious, reliable, professional systems. So you obviosly work on some kind of unix too.

Now we have to put it together. First compile increase.asm into object file. Then compile C file into object file. Combine object files into shared library. Lastly run java.

    nasm -f elf64 increase.asm
    gcc -I/usr/lib/jvm/default/include/{.,linux} Test_NativeCounter.c -c -fPIC
    gcc -shared -fPIC Test_NativeCounter.o increase.o -o libNativeCounter.so
    javac Test.java
    java -Djava.library.path=. Test

    NativeCounter
    job took 11710

Well, there goes our improvement. It is time to revalidate our opinion on CAS loop. Why performance is so horrible? Can you fix it? How to make it fast? What would happen if you replace compare-and-swap instruction (`cmpxchg [rdi], rsi`) with increment (`inc dword [rdi]`)?

    NativeCounter
    job took 2007

Oh. What is that change about? File increase.asm contains `increase` function that takes pointer to integer and increase this integer in CAS loop. The loop is transaction implementation. The job is to read data from memory, increase it and store back. If failed, repeat. The change essentially gets rid of CAS loop. Increment instruction always succeed; the loop executes once. There are no failures. You can make sure the counter has correct value; print it afterwards. The CAS loop failed to deliver performance I hoped for.

You do not have to go into assembler depth. You can use utilities packed with gcc. For instance `__sync_add_and_fetch(&i, 1)` instead of `increase(&i)` or
```java
int a;
do {
  a = i;
} while(!__sync_bool_compare_and_swap(&i, a, a+1));
return a+1;
```

## summary - the usual - pros & cons

I don't know about you but I was quite optimistic and am no more about [optimistic locking][optimistic concurrency control]. The reality check performed made it clear transactional approach does not guarantee speed improvement. Since you can use atomic increment as shown above it may be a good idea to measure how many transactions fail. I see no need for it because the, more or less, 8-second time difference tells me enough. Lots of loops must happen to increase the iterator. Threads fight amongs themself. If we place guard (monitor) around iterator so that each thread must first apply for ownership, we see job takes twice the time of atomic solution. Twice sounds bad but in comparison to compare-and-swap solution is great.

What is great about locked increment? What is the trait that makes it so fast? I believe the solution is the best because the whole job we intend to perform is encapsulated in one command. The instruction does not need to know what is current value of iterator. The instruction behaves like a transaction in the sense it contains recipe for the job. The difference is that the processor guarantees ownership of the data we operate on.

If you think of it you may realise all these threads do is to queue atomic increment on the data. The difference between atomic and compare-and-swap approach is the former queues and the latter does not. If anything is worth remembering is this: transaction allows you to execute command (receipe) as many times as needed to succeed but if threads share data they operate on, contention is factor. Threads should enqueue jobs as encapsulated commands and allow specialized worker thread to actually execute them. As everything both solutions come at a price. If you enqueue, your thread must never wait and you have to fully embrace asynchronous approach. On the other hand transaction is great if you care about [ACID] or just know the contention is low.

As far as I know [reactor] utilities asynchronous approach. I would like to know more about it. Wouldn't you?

[reactor]: http://projectreactor.io/
[reactor-core]: http://projectreactor.io/docs/core/release/api/
[dockerhub]: https://hub.docker.com/
[docker]: https://www.docker.com/
[aws lambda]: https://aws.amazon.com/lambda/
[apache kafka]: https://kafka.apache.org/
[into the wild async io]: https://www.youtube.com/watch?v=uGXsnB2S_vc
[optimistic concurrency control]: https://en.wikipedia.org/wiki/Optimistic_concurrency_control
[ACID]: https://en.wikipedia.org/wiki/ACID
[jni tutorial]: https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html
[jni wiki]: https://en.wikipedia.org/wiki/Java_Native_Interface
[nasm tutorial]: http://cs.lmu.edu/~ray/notes/nasmtutorial/
