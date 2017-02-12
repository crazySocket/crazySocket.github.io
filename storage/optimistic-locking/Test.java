import java.util.concurrent.atomic.AtomicInteger;
import java.util.Collection;
import java.util.HashSet;
import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class Test {

  private interface Counter {
    int get();
    int increment();
  }

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

  private static class NativeCounter implements Counter {
    static {
      System.loadLibrary("NativeCounter");
    }
    @Override
    public native int get();
    @Override
    public native int increment();
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
    //System.out.println("iterator value: " + counter.get());
    System.out.println("job took " + (stop-start));
  }

  public static void main(String[] args) {
    //run(new LockCounter());
    //run(new UnsafeNoLoopCounter());
    //run(new UnsafeLoopCounter());
    //run(new AtomicCounter());
    //run(new ManualAtomicCounter());
    run(new NativeCounter());
  }
}
