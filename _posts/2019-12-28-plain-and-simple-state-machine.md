---
layout: post
title: plain and simple state machine
---

## a framework foundations

Recently I have made a breakthrough. I mean I broke through thick layer of reinforced concrete what happened to be the mindset of my coworker. A miracle indeed. After painful yet unresolved deliberation a seed of an idea was planted in his mind. Maybe it resonated with an old, half-forgotten childhood memory. Whatever it was we ended up agreeing to make a state machine framework and use one in our upcoming endevour.

You may think "a state machine? what a big deal? stop loitering - get busy already!". It should come as a surprise to you, goddamm noob, that just two programmers is a quorum for never-ending my-code-is-prettier war. With each commit both guys spend more and more time rewriting parts of a program considered done. Those who miss the point yet attempt to mediate the quarrel usually say "Why don't you leave the ready code as is? Why don't you work on absent features instead of spoiling finished ones?". What bothers me most is how anyone could sound so insulting and get away with it. Afterall, one must realize the equivalence is to state that person has bad judgement, is unable to work without supervision and thus is unfit for the project.

If you smirked a little, then let me state it explicitly. Yes, I write from my own experience. And no, I disagree. The singular reason why any programmer rewrites any established, ready and tested functionality is that "the bigger picture" failed to capture their mind. You may blame them for lack of mental power to digest a product of some hidden logic plagued by miriad of notable exceptions. However, if you fail to discover the roots of such a commonplace phenomena, the blame is on you.

You could force your programmers to add a new code atop existing. However, you will inevitably witness slower and slower pace of development. What you failed to understand is when there is no big picture, no idea behind how and what the program does, the simple white-collar worker has to remember more and more unrelated tricks and hacks that grease the wheels. By "playing safe" you just made that simple program as over the top complicated as human anatomy. The thing about anatomy is that it is finished. A completly new design is not going to arrive next year. With software - yes, it will - next month.

With state machine as the main design principle I could believe the work pace in my team is going to be steady and reasonable. Now we can attempt to make one. And yes, I know your thoughts. Why bother? Boost did that already. Two reasons. First, have you tried it? I have. The horrible experiance still haunts me to this very day. Second, boost meta state machine is so over-the-top I believe there must be a simpler way.

## first attempt: structured paradigm

Let's start with basic requirements. I want my state machine to be:

* finite; with all possible states known at compile time
* all states should be listed in definition
* no other features like, for instance, guards
* transitions can be done manually by programmer.

I got the solution all of you should be happy with. Firstly, no need for third party libraries. Secondly, is packed with standard library. Thirdly, is designed by C++ experts. The solution is: `std::variant`.

Firstly, we define states.

```c++
namespace state
{
    struct A {};
    struct B {};
    struct C {};

    using any = std::variant<A,B,C>;
}
```

Secondly, we create an instance of state machine.

```c++
state::any machine = state::A{};
```

Lastly, we perform transition.

```c++
if(std::holds_alternative<state::A>(machine))
{
    cout << "state A" << endl;
    cout << "transition to B" << endl;
    machine = state::B{};
}
else if(std::holds_alternative<state::B>(machine))
{
    cout << "state B" << endl;
    cout << "transition to C" << endl;
    machine = state::C{};
}
else if(std::holds_alternative<state::C>(machine))
{
    cout << "state C" << endl;
    cout << "transition to A" << endl;
    machine = state::A{};
}
```

What? You don't like it? So what it is C++17? How is it experimental in 2019? We are preparing for C++20 so C++17 is workaday. Is not supported? I have successfully built C++17 applications for *arm*. No tricks needed.

What puzzles me most is how ungrateful people are. I give you a solution you need but you still insist it is not the solution you want. Each time it goes like this. Firstly, you state a problem. Secondly, I solve the problem. Thirdly, you reject my solution. Forthly, you choose to use a third-party library you know nothing about even though it does not solve our problem in the slightest. A few rounds like these and now I see clearly the cause. What you are looking for is not a solution but an authority you can put your trust in.

No one understands the tools already available. Use *boost::asio* but avoid *POSIX*, they say. *POSIX* is too old. Use abstractions but avoid templates. Templates are too complicated. Use third-party libraries because they are made by deities of superb intellect and C++ expertise. It seems they would have followed my advices gladly, had I only posted them anonymously online. Just like all those third-party libraries seem to be.

The more I come across these and similar arguments the more I am convinced people don't learn. Each one arrives at the time in their lifetime when they stop to grow. And from that point they deteriorate. Until, finally, their heart calls it quits so the rotting body can join the rotting mind and be in the end burried six feet under ground. The only question is how to deal with these zombies when they still walk and spread obsolete gospel?

"You cannot learn an old dog new tricks". In this proverb we have the primal reason why you reject `std::variant`. Nothing is perfect. Everybody knows that. But to say in one sentence both "you should use libraries made by experts" and "you should avoid templates" is absurd. C++ committee designs entire language around template features. Both *stl* and *boost* are packed with those. How you can advise *boost* and mock commitee at the same time? "We are not committee" - we apply language however we feel like. To avoid templates means to make wrong use of C++. Clearly. You lack qualifications to call yourself a C++ programmer.

But the abyss goes deeper. Google code style advises to avoid metaprogramming. Most programmers don't know metaprogramming at all so they confuse it with templates. They think to avoid metaprogramming means to avoid templates all together. Another advice is to avoid non-const references since "they can be confusing, as they have value syntax but pointer semantics".

As I see it the good old language which is C was hijacked by a band of hippies and all of us were taken for a ride. Without a doubt generic programming is great and without a doubt templates suck. No rapture for you. The divide between new C++ and accepted style will grow. I am certain that to start a new language with templates done right was much harder then to hijack C. But I would appreciate such bold move whole-heartedly.

Anyway, we have to somehow cross the abyss. To sum up

* `std::variant` is a new toy children play today but you are too old to play with them
* `std::variant` is a template and as any other template produces horrific logs
* you lack skill today since you were too lazy to learn templates in your youth
* you are too old to learn so you search frantically for authority to ease you fear
* when I ignore your incompetence, you tell me I lack "soft skills".

As you can see it is hard to get anywhere with obstacles like these. And we have barely started. Oh, boy. Since I cannot teach you basics I will do now what I cannot in real life. I will say "you don't like it? get lost!" then proceed with our state machine.

## second attempt: object paradigm, visitor pattern

The first attempt, even though crude, gives me hope. The best thing about generic programming is it helps to prove your program works. All possible states are known and checked at compile time. Every path your program takes is verified even before your first run. At least it should be. Most programmers are attracted to dynamic structures. Use `std::vector` not `std::aray`. Use `std::shared_ptr` instead of regular pointers. What they want is for RAII to guarantee their program is valid, no one wants to prove their program works. Who am I kidding? I work with technicians. When I say "prove" they got confused because they have never done that before. For your explanation, here our state machine is proven to work since you cannot do anything outside defined operations. Whatever you do is really intended. And that is excatly the goal. The machine is tiny. Does not require much proving. I get that. Yet the limitiation is guaranted to hold. And in the world of uncertainties this one is the treasure.

We have to own the criticism, though. It is indeed troublesome to use a template structure like `std::variant`. Especially when you use variant as a steroid-infused enum. The more options you put into the variant the longer the name. Had you ever debug with `__PRETTY_FUNCTION__` anything employing template? Had you, you would know the pain. It does not matter how compile-time you solution is, when you are unable to digest logs it produces. A programming virtuose might use a structure that inherits from variant. And that works great until it does not. There are [bugs](https://stackoverflow.com/questions/51309467/using-stdvisit-on-a-class-inheriting-from-stdvariant-libstdc-vs-libc) lurking out there and waiting for the right moment to screw you day.

Another one is "big if-else". Even though it is bread and butter part of the language, every programmer has irrational fear of them. It is like a programmer expects to be entertainmented by a code. Like code highlight is not to identify languange constructs but to please the eye of the beholder. People will waste their mental capacity to modify conditionals until the code is estetically and mentally pleasing. For them of course. To hide known language constructs behind unnecessary tricks employed by multitude of macros is total waste of time. It not only obscures code but significantly increases the time your coworkers have to spend decrypting instead of working.

But those pedantic extremists argue it was all to avoid code repetition. When you expand all those macros the total number of repetitions skyrocket is overwhelming but who would waste time to check that? The oposition is no better. They introduce plethora of classes or functions (whatever paradigm is prefered) so that the job granurality is high enough to find common subroutines even in a pair of unrelated code snippets.

Since C++ pretends to be less macro friendly it introduces `std::visit` which plays nicely with the second, misguided approach. Let's try it though; give it benefit of a doubt. The idea is to create a visitor. A class containing a family of methods capable of handling any type of a visitee from predefined set. In our case the class must have functional operator overloaded for each type of a visitee - that is a state in our state machine.

```c++
struct Visitor
{
    state::any operator()(state::A &)
    {
        cout << "state A" << endl;
        cout << "transition to B" << endl;
        return state::B{};
    }
    state::any operator()(state::B &)
    {
        cout << "state B" << endl;
        cout << "transition to C" << endl;
        return state::C{};
    }
    state::any operator()(state::C &)
    {
        cout << "state C" << endl;
        cout << "transition to A" << endl;
        return state::A{};
    }
};
```

To make use of that visitor we need to take a closer look at the signature of template function `std::visit`. First argument is an instance of visitor class, the second is an instance of variant containing a visitee. It is so technical I did subconsciously wrap it all up into a function.

```c++
state::any transition(state::any & machine)
{
    return std::visit(Visitor{}, machine);
}
```

## third attempt: object paradigm, syntatic sugar

You should be concerned right now. Why use a helper function? But before you can make your mind a proponent of "visitor approach" introduces even better way of expressing "big if-else". They must themselves recognize the visitor does not improve much. More like move things around in a fancy spirit. In the end we still have big block of code no one wishes to digest or, god forbid, modify.

```c++
state::any transition(state::any & machine)
{
    return std::visit(
        overloaded
        {
            [](state::A &) -> state::any
            {
                cout << "state A" << endl;
                cout << "transition to B" << endl;
                return state::B{};
            },
            [](state::B &) -> state::any
            {
                cout << "state B" << endl;
                cout << "transition to C" << endl;
                return state::C{};
            },
            [](state::C &) -> state::any
            {
                cout << "state C" << endl;
                cout << "transition to A" << endl;
                return state::A{};
            }
        },
        machine
    );
}
```

Fancy, right? It looks nice. But I bet you don't know what the code does, unless you have known already. I sound like a quote from "John Constantine", doesn't I? Humor me. I can see here aggregate initialization of type `overloaded` with functional objects of anynomous types. That is quite a mouthful. And we haven't finished yet! Class `overloaded` has to be a template class with variable number of parameters. Here it goes.

```c++
template <typename ... Ts>
struct overloaded : Ts...
{
    using Ts::operator()...;
};

template<typename ... Ts>
overloaded(Ts...) -> overloaded<Ts...>;
```

```c++
```

The version above is succint. Before recent additions to the language one had to manually expand the list of template parameters. Sprinkle lots of inheritance atop and you are guranteed no one will ever touch this piece of code again. Perhaps even you.

The last, function like, declaration is user-defined deduction guide. It tells compiler how to deduce template arguments for the type based on how constructor's arguments looks like. The very idea of such heresy deserves at least purgatory. Clearly something went wrong along the way to the standard. But due to backward compatibility we are stuck with this weirdo - forever.

Let's not focus too much on some helper structure. In realm of C++ such helpers are commonplace. Let's ask what is important - why do we even need any helper to avoid code repetition? The cornerstone of the mistake we did is the necessaty to create a separate type we imposed on ourself just to perform pattern matching. Language should support it!

We do test the variant for the type it holds in the first attempt. Next we rely on the language to do the same. In the end what we want to express is that instructions to execute should be chosen based on specified conditions. Here the condition is "variant contains an instance of type". I call such guard pattern because generally you might ask if variant contains `std::vector<_>`, regardless of what type the container holds. As a matter of fact let's try to do just that.

```c++
struct Visitor
{
    void operator()(int)
    {
        cout << __PRETTY_FUNCTION__ << endl;
    }

    template<typename T>
    void operator()(std::vector<T>&)
    {
        cout << __PRETTY_FUNCTION__ << endl;
    }
};

int main()
{
    {
        Visitor()(1);
    }
    {
        std::vector<int> c;
        Visitor()(c);
    }
    {
        std::vector<double> c;
        Visitor()(c);
    }
}
```

Works, right? However, the burden it too heavy. It is one thing to create a type another to create a new language construct. We did just that. From now on we expect fellow programmers to know the syntax `Visitor()(_)` means a conditional that performs one thing for `int` another for any `std::vector`. The one smart guy (surely there is at least one among you my dear readers) might wrap that up into a template function and call that move encapsulation.

The crowd goes wild! Isn't that great? We have a new empowering feature in our language! Let's use it.

```c++
MatchIntOrVectorAny(
    c,
    [](int) {},
    [](std::vector<int>) {},
    [](std::vector<double>) {}
    MatchIntOrVectorAny_Terminator()
)
```

Now, take it easy. I don't mess with you. I did not intend to insult you. (soft skills bitch!). You will arrive at the same conclusion, when you think about it. Why does `MatchIntOrVectorAny` have so many arguments? Let's go back to the smart guy among you. He/She had to realize the function should somehow return control to the user just like any conditional does. But C++ does not support coroutines. The only way to pass control is via a functional object. Function receives it as an argument and calls when conditions are met.

That explains theory but does not the number of argument. Let me remind you there is no such thing as a template lambda. User most likely expects to match `std::vector<T>` of some explicit `T`. Thus the function requires a handler for `else` - all those legal cases we are not interested in. However, in regular conditional `else` is optional but here compulsory. How would you explain that to an oblivios user? Simple: you must put `terminator` at the end of the argument list. End of story.

The entire idea seems too convoluted. How is it that on the way to simplify pattern matching by hiding technical details, which is good old visitor pattern, we ended up with a function which arguments lack reason to be - are seemingly out of nowhere? As an answer all I can do is list the steps we did. Firstly, we hid overcomplicated code that depended on tricks and hacks. Secondly, we realized the solution lacks usefulness. Thirdly, we built on what was a mistake from the start.

I have seen things like that many times. Myriad of people spending years of their lifetime doing unnecessary job only because of some fruitless mistake done before in a project that was too big to fail. Working hard in the strong spirit of righteousness covering rampant dishonesty and corruption. In short: waste of time.

But who is there to see? Users are more than happy to have a battery of functions each hiding the pesky details. Encapsulation. Wrong! The more is hidden from you the higher the risk of the failure. People go in the direction of convenience as they tend to do. So in the end they no longer know what is the truth and what the lies crafted in the righteous effort.

Encapsulation is not about hiding pesky details but about keeping together things of high cohesion. Generations of programmers have already been taught a heap of lies. For instance: why we keep source and  headers separately? To hide implementation and publicise interface. Wrong!

To understand what went wrong with `MatchIntOrVectorAny` we need to delve into the past, remind ourself the roots of C++ and answer why the above is wrong.

## encapsulation - look into the past

Bjarne Stroustrup is the father of C++. According to him lots of features we recognize today as the core concepts of the language were in fact requested by early users. These were not crucial but added only due to popular demand. In contrast, the less successful predecessor of C++ named "C with classes" presented the minimal set of requirements that proved to be vital enough to actually put Stroustrup in the business of creating a new language. This fact shuffles significance of features quite a bit. In the entirety of the language the only vital feature is a class. The rest is [noise](http://www.cplusplus.com/forum/general/164971/).

Working with good old C a programmer might split entire thing into multitude of files. However, the outcome is what matters. And the outcome is a pack of symbols. Each one may be a function or a variable. Such a pack is generally called an object or a library. Now to make use of a few objects one has to make truce between a few packs of symbols. What if symbols collide - share a name? Linker offers no help.

But C offers some assistance. Why don't you advertise all the symbols you wish to take? Thus a header file. Now whoever wishes to use your object first includes the header. When there is a collision, you are immediately notified. Better earlier then later, right? As a result your interface ends up with long names - just to be sure; in order to avoid future potential collision.

That explains why header and source files. Aren't these enough? What would you need a new language for? Using public interface you can interact with an object file as if it was one instance. You should know one instance in multithreaded environment usually requires special precautions. In most cases a routine solves a technical problem which is considered too basic to cause trouble across threads. Thus an interface might lack any semaphore or monitor support. And that is how you get caught off guard by [`gethostbyname`](http://man7.org/linux/man-pages/man3/gethostbyname.3.html). What C++ is offering instead is to ship the structure and behaviour in new languange construct called a class. Then the user creates and manages an instance - not the compiler.

We often confuse a class with a namespace. One might thing the point is to hide pesky details behind private access but one does not need C++ to hide anything. To hide you make it static in source file. To deny access you make it private in header. Whatever is private is still known to the user and thus, in a way, is still public.

A class serves as a closure. Some functions need context to operate - an instance of class. They are tighly coupled. It is not about keeping secrets. Encapsulation is about cohesion and closure. Whoever says different has internal personal problems.

## forth attempt: functional paradigm, trait

It should be obvious by now how redundant object paradigm turns out to be. Yet we are still in need of some dispatch which can break entire block of potential state machine transitions into smaller pieces. In each transition one knows what the state is and expects a new state to be placed into machine. It would be convenient, if I could break the "big if-else" into the set of functions below.

```c++
state::any transition(state::A &)
{
    cout << "state A" << endl;
    cout << "transition to B" << endl;
    return state::B{};
}

state::any transition(state::B &)
{
    cout << "state B" << endl;
    cout << "transition to C" << endl;
    return state::C{};
}

state::any transition(state::C &)
{
    cout << "state C" << endl;
    cout << "transition to A" << endl;
    return state::A{};
}
```

The problem is the dispatch. But we can solve it with clever use of `std::variant`.

```c++
state::any transition(state::any & machine)
{
    return std::visit([](auto & s) { return transition(s); }, machine);
}
```

Some syntatic sugar but fortunately under control. The trouble you might not be aware is how compiler resolves which `transition` should it call. What I observed is all posible choices must be declared prior to the implementation of `transition(state::any & machine)`. That is true unless you declare a tempalate function.

```c++
template<typename T>
state::any transition(T &);
```

What is great about the template here is the declarations are no longer required to be stated before they are used. We moved from overload resolution to template resolution. The template introduces a set of specializations, functions with roughly known signature. This is a particularly useful concept since I can, in a sense, add a behaviour to already existing type.

To prove my point I need to remind you what method signature hides from you. I assume you are aware of `this` keyword. The pointer to an instance of a class. In fact each method receives this pointer as its first argument. But a programmer does not see that in method declaration - a syntatic sugar built into the language for convenience. There are other languages where that argument is not hidden. Our `transition` template function works in the same spirit. You could mentally transform `transition(A)` into `A.transition()`. However, C++ does not allow to add new behaviour to declared class. As I see it a template function allows me to do just that; implement interface on already declared class. To differentiate these I shall call our `transition` a trait.

Unfortunatelly, the use of `auto` keyword in the dispatch requires our trait to have implementation for any type. That will be particularly annoying when in course of development your machine performs this default yet unintended transition. And the whole premise of generic programming is thrown out the window. Yet I choose to ignore that fact today.

## fifth attempt: fsm namespace

A trait opens a horizon of possibilities. I can create not only `transition` trait but also `on_entry`, `on_exit` and others usually associated with state machine. But to make these hooks work one is forced to make a significant observation. The `transition` is no longer allowed to change state of machine. I assume user wishes the `on_exit` hook called before machine enters next state. However, to call hooks in each `transition` is very repetetive and thus a user is doomed to forget to place them eventually. What we need is to place that responsibility on some framework.

The observation leads us to seperate concerns. User decides what is the outcome of transition. Chooses to change into a new or remain in the previous state. Based on this input the framework calls the hooks if necessary. Since trait has default specialization we have the default hook doing nothing.

```c++
namespace transition
{
    struct no_action {};
    struct change
    {
        state::any next_state;
    };

    using any = std::variant<no_action, change>;
}

template<typename M, typename S, typename E>
transition::any transit(M & m, S & s, E e)
{
    return transition::no_action{};
}

template<typename M, typename E>
void dispatch(M & m, E e)
{
    auto result = std::visit(
        [&](auto s)
        {
            return transit(m, s, e);
        },
        m
    );

    if(std::holds_alternative<transition::no_action>(result))
    {
        return;
    }
    else if(std::holds_alternative<transition::change>(result))
    {
        store(m, std::get<transition::change>(result).next_state);
    }
}
```

There were a few steps I kept silent about. Firstly, I introduced a message called event into `transit` trait. Secondly, I differentiate a machine from state. Machine may have information of its own. And the method of storing state might differ. Thirdly, `store` trait is to delegate that concern outside the `dispatch` function.

This little piece of code is the foundation of the framework. I intend to put it into `fsm` namespace (finite state machine). It consists of traits

* `fsm::store`
* `fsm::transit`.

We might add more traits for hooks but I choose end the scope of this post here. My concern is to guide you from absolute basics to working `fsm::dispatch`. I believe I succeeded.

## unanswered questions

At the very first contact user may have some doubts. Software developers are not used to think in terms of design patterns or algorithms. Except, perhaps, a strategy or a facade. As I see it the doubts stem out of simple novelity. The important questions immediately emerge from the ocean of fear. What if the framework lacks functionality? Who should I shift the blame to when a mistake occurs? These are the most prevalent.

I choose to ignore the social aspect. Instead I focus on what was the point, what was the "big picture". In my thought a state machine creates a structure in the world of aggresive memory access. A transition is the limitation put onto memory mutability. It links a pair of nodes in the space of allowed states. Program execution can be explained as a sequence of transitions. In any regular software available on the market such rigidity is unlikely.

This rigidity is in my experience out right rejected by IT society. These guys disdain the use of any theory. They put "the practice" on the pedestal. Mostly, this means they apply only those solutions that are widely accepted by their society. It should not come as a surprise that this collection must be small and preferably consists of the most trivial tools. Tools that are age apprioriate at best for a kindergarten.

I don't get it. People treat any theory with great suspicion. They always fall back to a "simple solution". Such solution is always simple in technique, a memory read, a memory write, but never simple in explanation - why would that work? Why macro would be better than a function? We can always ask compiler to inline function. So why is that? In my opinion the cause is that "greedy" solutions do not require any context. Dynamic optimization requires a model - a "big picture" - to prove it is correct. "Greedy", on the contrary, makes the choice obvious at each step. Thus the urge for easy solution fast always ends up with those socially acceptable.

I want to break free of this malfuncting attitude. I want to avoid pretending. I believe programmers should not deceive one another. Any such attempt is bound to be brough to light. A manager might not know but not a programmer who is paid to work with the nitty-gritty details. We know. Let's stop pretending. We need a "big picture" to get anywhere. Here we make the ridgid rule that outside of a transition nothing is allowed to change the state.

This limitation immediately raises the question whether state machine is any good in multithreaded environment. People have hard time imaging any way to communicate between threads other then a flag or a mutex. Here the limitation allows us only to call `fsm::dispatch`. You saw the code. You know what it does. And I bet you still believe strongly it is magic. Thus to dispatch an event is far worse than direct memory access.

To come up with our state machine during work meeting should be the equivalent of quitting your job. It is too hard to figure out all unforseen troubles. Some time has to be spent by a team of experts before you can harvest the fruits. But in reality no one would give you the time you need before they are convinced you can deliver. Thus you must resort to lies - called marketing - or limit yourself to whatever is the most trivial.

The contr solution to a state machine I was presented with was "simple loop". Why do we have to go through all this trouble when a simple loop can check all sources of events? If you are asked that, you witness the most obvious sight of disrespect. Not only towards you but the world itself. The hidden viewpoint in this question is that a world is plain and simple. Thus to understand or simulate does not take much effort. Whenever I am asked I wish I could reply "if it is so easy, why don't you do all the work youself?". But I am not naive. I know the diligent are rewarded. Regardless if they did any good. I am not talking free market here but corporation reality.

Don't get me wrong. A transition may set a flag for all I care. It does not matter. What does matter is that such action should be placed in a transition. And transition is bound to both state and event. You can make a picture of that if you realy need. It is easy to explain. If you are scared by a mere possibility you machine might deadlock on dispatch you lack courage to call yourself a software developer. You better find another carrier.

The last obstacle I had to go through is when a guy tries to sabbotage a project by becoming overly meticulous. The theory behind a state machine differentiates messages from events. A message is dispatched. An event is point in time when some phenomena happens. In practice we don't care. I see a message always named after an event. What is important is to choose events from the real world. You should not think of on-entry hook as an event. The point of a state machine is to simulate the real world. Who needs a machine pointed inward? I know who. The guy who missed the point and still fights to turn project around into the waters of "simple loop". In that case that guy is beyond redemption. Al that remains is to get rid of him. Leave it on a desolated sandy dune, set sail and voyage into possibilities hiding over horizon.

Perhaps this is the lession worth recording. Not everyone is worth working with. There comes the time you must honestly answer whether the work environment is of benefit or burden to you. If your job is to write C++ but if your colleagues deliberately or not sabbotage you work with any described above, you should cut off any connection to that rotting flesh. I hope you find this post heartening. Merrit has not been gone from this world but is still here and flourishes just outside a few lands of corruption.
