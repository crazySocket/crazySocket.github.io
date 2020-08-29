---
layout: post
title: the indended yet unwanted C++ use cases
---

## the essence of a software developer job

There is some unconscious goal, sort of a beauty all software developers strive to achieve. I wonder where this ideal comes from. Have someone taught me or I read about? The more I think about the origins, the more I believe the goal one day just appeared to me. And once that happened it has been with me ever since. If I were to describe that beauty, I would say it is beyond things as trivial as proper indendation or code highlight. It operates rather on the philosophical plane. It is the "easiness of mind" you experience - the clarity of the concept expressed with code. But not entirely.

To develop software one requires enough knowledge to bootstrap one's confidence that the problem tasked can eventually be solved. Whenever you face an uphill challenge you need some momentum to keep you going. Curiosity, wisdom or this elusive beauty. To those outside our little world what we do might seem like magic. None of our unique insights leak outside. From our point of view the clarity roughly compares to what one might experience when following a mathematical proof. "Where that idea came from? Why this substitution?". I know what I describe partially escapes the familiar realm of science in favor of mysticism. You are welcome to feel excluded.

Yet the beauty is very real. In fact we rely on it to keep the wheels turning. Let's consider what happens to a project lacking the clarity; let's take a look at enterprise software. It resembles a huge, overgrown and patched monstrosity. People who develop that are not oblivious to its overwhelming uglyness. In fact it is them who produce loads of jokes and comic strips in desperate attempt to alleviate the guilt. The guilt that it is them who abandoned the ultimate goal for a common paycheck. However, a big company has a big budget and one must keep on living. So people get over the guilt and keep the monster alive. A transfusion a week. A transplant from time to time. And the monster persists. Since monster receives no love, it has none for its unfortunate keepers. Perpetual wheel of misery emerges.

Closure demands a few words said on how to recognize the uglyness; a sneak peek into our world for newcomers. The very first thing that comes to you attention is how ridiculously unfriendly enterprise software is. Take for instance oracle database. It is equiped with supposedly useful graphical user interface, however, unless you are an expert in that specific database flavor, that GUI is utterly useless. I understand that a production database requires numerous and sophisticated features. However, I find any product that cannot be used at all unless you master all its features thoroughly flawed. On the other hand you have compilers like gcc or clang. Although these are very elaborate and powerful programs, you are welcome to limit yourself to the most basic functionality. You list source files, optionally add the executable name, and you are good to go. It is tempting to prove your intellectual superiority by developing software designed to be sophisticated. Unfortunately, no user would appreciate unnecessarily steep learning curve. In you best interest is to mitigate the inclination. One day you might be the user yourself.

In fact you are user already. This very browser is not your creation. You can have displeasure of reading these passages thanks to significant effort put into the entire internet web stack. Effort much greater than any singular person could exert in their life. You are the user. And what would a user do if the software they cannot modify "misbehave"? Restart and try again? Reinstall? Clear cache? There are many eligible tricks and you have for sure tried some. Users tend to accept buggy software as long as they are accustomed to the defects. You, a developer, are unfortunately on colision course. Your new software is by definition unfamiliar. If you attempt to mimic other existing user interface you are in a risk of hitting [uncanny valley](https://en.wikipedia.org/wiki/Uncanny_valley). In enterprise this effect is much enhanced. You are allowed to develop only in one direction - forward. There is no room to prototype anything. Once you slip into the gravity well of enterprise recognition you are stuck with never ending backward campability. Any refactor is potentially feature-breaking - thus illegal.

The bigger the company the less effective central management is. Some authority has to be outsourced to departments. A mistake is bound to happen. A buggy, deficient piece of software might be ordered and processed. Later down the road another department might require similar functionlity. The management is more than happy to reuse the software. Would you tell them you prefer to replace the faulty software? To pay again; to pay twice? Would you risk your career to expose a trouble or bite teeth to plough the thin soil riddled with stones? Would you learn visual basic to "print" somehow to microsoft excel? What is the price of your integrity?

You must not think we are still in the 80s. Today information systems are expected to communicate between departments. The pesky decentralization poping out must be conquered with extreme prejudice! Individial units are not free to build their own understanding. The right interpretation must be forced upon them. This is opinion of those who fail to see how the same thing under the same name can differ across organization. Take for instance "price". In an accounting it designates how much a unit of product was selled for. In a shop it is the money to charge for a unit. For an economist it is a function of supply and demand. The price has not just diffent meaning - history record, value per unit, function - but is placed in diffent time - past, present, future. However, the centralized system has place only for one definition. Which would that be?

The monster is built from incompatible body parts. The longer it lives the more effort is put into chiseling the parts to somehow fit together. Ever incresing maintenance cost must be its verion of death. The death by decay, by entropy and by sophistication. All that maintenance produces lots of heat but little progress. I cannot help but wonder why we devote our lifetimes to make the monster live but we deny it the bliss of death. We really should accept that every singular enterprise is going to eventually perish.

## what is a success?

From observing grinding cogs we can conclude no more then the immediate reason of failure. We need to see examples of cogs well greased. I am choosing those from the software collection that keeps me efficient. *Bash* deserves the first place. I had opportunity to work with vide variety of Integrated Development Environments. And every time I find myself gravitating towards good old xterm running bash. Each graphical user interface turns out to be just a fancy facade employing a few clever commands. Once I know that commands the appeal of the GUI is gone. I know I hold no prejudice. I simply express myself easier with a *for* loop or a *pipe*. It helps greatly I can save my commands into a script. There are few tools to automate GUI. No wonder, simply put they pale in comparison to ordinary shell.

The second would be the famous pair of cmake and make. Even though a bit dated today they are still really impressive. The benefit is twofold. Firstly, I can outsource all those mundaine bulding procedures. Secondly, cmake introduces the language I can effortlessly express relations between modules with. As a result, I am willing to divide the codebase into modules. There are books written on good practices you should employ. Each tip requires you to consciously alter your course. I do not have to. With the right tool the right direction is the natural choice.

To choose the right tools is far less trivial than you think. I have seen far too many billable hours wasted only because a developer was strugling to communicate with the tool at hand. And that was not just my problem. IT is plagued by stalled tasks. Most are stalled due to lack of proper equipment. In such an enviroment developer is afraid to replace the familiar tool. There might be a better tool for the job but no one would risk offending vengeful spirits. Yep, superstitions come into play when you lack control over your own work. The fear is so great that eventually they utterly depend on that one tool. The tool becomes their god. I have been told again and again how great this and that IDE is. And I can see again and again how clueless my colleagues are anytime they are required to perform something nontrivial. They boast how much better they are than me by using latest framework with streamlined deployment, yet, in face of any trouble they offer nothing but bewildered look. How can they live with themselves? They are pathetic.

In each of those cases I find a recurring theme. An IDE takes upon itself to compile, build and manage entire project. What I do with the triad of git, cmake and make is poorly attempted by an IDE. Understandably so. There is no way to cover all the options of those powerful tools with a simple GUI. It is much better to do one job best than many poorly. Enterprise software seems to share the same flaw. Company requires the software to perform multiple, diverse jobs at once. That would not be natural for any human worker. Thus software developer must perform extensive mental gimnastics before they can explain the program to a machine. We are capable of understanding elaborate human-to-human relations but how those translate to machine-to-machine relations? Barely at best.

Our interpersonal relations play significant role in how we interpret the world. Words like manager or worker are prevalent in software nomenclature. You should be surprised by this fact. In computing things like a queue, register or a reference are used. To name anything "manager" requires level of abstract thinking reaching way outside the domain of computing. Abstraction is always recognized as a valor. No questions asked. I think this is a mistake. It may be counterproductive to think in terms of pointers to raw memory. But to imagine that same pointer is a database manager is simply inacurate. In my opinion to abstract means to recognize repeating theme and explain a function availing the theme. Computing machines have nothing in common with human hierarchy. And yet we mingle both. The fact that computer systems reflect the structure of the company it was built for is widely known. Self-important people are to be blamed. They think themselves to be so much more important than the infrastructure that they lose sight of how the work is in fact done. Instead they want the software to be built in their image.

## time impervious solutions

It should be evident by now what features make software useful. Moreover, that usefulness is impervious to time. Tools like *vim*, *curl*, *bash*, *git* or *posix* are decades old. If you juxtapose those with any microsoft windows, you shall be confused why windows is outdated after barely few years from release. I believe any tool designed to solve well stated problem should retain its functionality years later. Afterall, software does not wear off. I do not ignore the fact that users might prefer next piece of software. Let's take a look at *vim* and *tex*. The first is competent text editor. If you were to work with a server, you would be happy to use the old editor designed for nongraphical user interface. It does not mean there are no better alternatives for those who work on a workstation with a video adapter. *vim* is simply the best in its category. The second is professional typesetting system. The problem of placing proper types onto page was well defined by Donald Knuth in late 70s. If you were to print a book or an article, you would face the same chanllange Knuth did. Obviously, there have been additions made to this very popular system. For instance *latex* or *xelatex*. But still the problem formulated decades ago has now the same solution as in 70s. It is up to us to recognize and acknowledge previous achievements in order to build ontop of them.

We should carefully formulate the problem in the language of its domain. Create a solution with as few assumptions as possible. Only working in that spirit we might create the simple solution for the simple problem. When all distractions are skimmed, we can finally access the one thing that makes our solution work - the simple, functional idea. That is what we should appreciate in any software. That is what keeps us productive. That is what the software cost is spent on. That is what software developers strive to create. This is the ultimate goal.

At this point I expect strong oposition to my findings. Some might argue that my analysis is not valid for anything but trivial systems. In the real world any useful system must juggle multitude of tasks. Real problems are too sophisticated to be reduced to a set of a simple ideas. Those are arguments of people with short attention span. They are unable to record enough observations in their mind to notice a pattern. If you observe sinus plot from up close, you might thing it is a part of a conic section. It takes a change of perspective to understand that the sinus plot is not infinitely complex but only cyclic. I agree it is nontrivial to make such observation in the real world. Yet, to cower, to choose false sense of security is unsustainable. You should take pride in a simple solution you created than attempt to build another giant on clay feet.

## how to measure code quality?

My goal here is to investigate how recent additions to *c++* improve my ability to create those simple, functional ideas. There is a schism in software developers between proponents and opposition to new standards (*c++14*, *c++17* and soon *c++20*). You may missed that but the trouble is actually massive. How else would you summarize the situation when *c++* commitee extends the usage of templates while major corporations like google discourage metaprogramming? It is unfathomable why *c++* is not only full of but receives new language constructs which should (according to the community) be avoided. Why are there features clearly intended to be useful yet are at the same time not wanted? It is time to go beyond the so called good practices and make informative opinion of our own.

What should be established first is the method on how to assess a programming language. Since such a language is dedicated only to express a computer program one should measure how algorithmic building blocks can be expressed. "To measure" might be an overstatement. I can easily think of two contradicting measures. Surprisingly often performance is not a factor. And yet performance or speed is the measure most can think of. In practice the real cost is how many hours regular software developer requires to finish an application. Unexpected, right? I am sorry to dissolve your illusion but only insignificant fraction of developer work force is in a field demanding high performance (for instance forex trading).

Maybe it is all about the expression problem? It cannot be. That problem relies too much on individial's opinion to produce a reliable measure. Regardless, that is the thing you can see discussed on most conferences. A revolutionary solution is always promised to help you with the mundane part of your job. The more code examples of those solutions I see the more I am convinced those are deliberately designed to prove the author's point. And that makes such examples not only useless but Machiavellian. People are not in there for any truth but their own gain. Unfortunately, those hidden advertisements have influence on developers. They will choose what seems familiar. They will unknowingly push your project into a venue of unfit tools. You must remain ever vigilant. It is you who is in charge not the tools you happen to use.

You do not believe me? Then let me show a misleading example. How to print a `std::vector<T> queue` in reverse? It should be simple enough to present in a minute on a conference. Let's start with the simple and expected solution.

```c++
for(const auto it = queue.rbegin(); it != queue.rend(); it++)
{
    cout << to_string(*it) << endl;
}
```

Simple, short, to the point. Some details are explicit but the level of details is still reasonable. You are required to know what auxilary interator is and how to use it. Now for the improved way.

```c++
for(const auto & i : reverse(queue))
{
    cout << to_string(i) << endl;
}
```

Shorter, without the auxilary interator, aesthetically pleasing. But I am sure you are unable to tell how much memory this loop requires. In fact `reverse` is total mystery. Does it mutate the queue? Does it copy the entire queue or just data? The most important question is how much freedom are you willing to outsource to get an aesthetically looking solution?

```c++
template<typename T>
struct reverse_wrapper
{
    T & iterable;
};

template<typename T>
reverse_wrapper<T> reverse(T & t)
{
    return {t};
}

template<typename T>
auto begin(reverse_wrapper<T> & wrapper)
{
    return std::rbegin(wrapper.iterable);
}

template<typename T>
auto end(reverse_wrapper<T> & wrapper)
{
    return std::rend(wrapper.iterable);
}
```

Luckily, it turns out that only one auxilary type is defined. However, we use a trick. There are many *c++* developers unaware of `std::begin`, `std::end` traits and how those relate to for-each loop. I would not be surprised if one day those traits are moved away - outside semantic context of our loop - by a mistake. Those oblivious developers would have to debug in search of feature they are unaware of. That would increase your costs quite a bit. So, is `reverse()` an improvement to the languange or not?

## algorithmic measure

In a workday of a regular software developer the use of algorithms is scarce at best of times. Thus algorithmic problems are not used in examples presented on any conference, in a book or internet tutorial. This creates an illusion that everyone can be a software developer. As the influx of neebies dilutes professionals programming languages obscure important implementation details. It got to the point when libraries in *javascript* operate on strings of *base64* instead of good old arrays. This trend has to be stopped! All this junk is bound to fail. In *java* they had Remote Method Invocation. The idea was to hide, to encapsulate unimportant implementation detail - network traffic. That idea is the prime example of how importat it is to use the nomenclature of the proper domain. Network communication is not comparable to regular, local function call and thus must not be swept under a rug.

I would like to reverse that trend. I refuse to think in terms of "managers" and "workers". My programs are built in terms of queues, parsers, state machines and transactions. Abstraction is to serve me - not the other way. That is why in my example I will implement a parser of a finite grammar. Quite a few of our everyday challenges can be expressed in terms of an automata. To abandon those useful concepts is the mistake I shall correct.

### problem definition

I want to parse simple message consisting of unknown number of lines. Each line contains a pair of key and value. You would need such a parser to make use of this http message.

```
Location: http://www.google.com/
Content-Type: text/html; charset=UTF-8
Date: Thu, 10 Oct 2019 11:01:01 GMT
Expires: Sat, 09 Nov 2019 11:01:01 GMT
Cache-Control: public, max-age=2592000
```

Do you remember what grammar is? You should have sufficient understanding of english grammar. Basic layout of a sentence could be *Subject Verb*. Subject and Verb are nonterminals. A nonterminal symbol is a symbol involved into ruleset but not part of grammar output. It is a placeholder that must be replaced with a terminal symbol. In this case we can interpret nonterminal as a category while terminal is a word belonging to that category. For instance category Subject has instances Alice or Bob. Grammar does not define categories. The connection between terminal and nonterminal is defined in the ruleset. Example ruleset for our english grammar could be

```
Subject -> Alice
Subject -> Bob
Verb -> stands
Verb -> walks
```

These rules are trivial for the purpose of demonstration. Both input and output of a rule can involve a sequence of symbols both terminal and nonterminal alike. There are two symbols with special meaning. The empty symbol *E* allows us to replace nonterminal with nothing - to delete a nonterminal. The start symbol *S* is the first input we apply rules to. Remember that the purpose is to generate a sentence. In our problem the text already exists. We need to apply grammar in such a way that the sentence generated is the same as the text. The benefit is that during the process we will learn what terminals replace nonterminals. Thus we will find the meaning hidden in the unprocessed text. With this short introduction to finite automate we can define our grammar.

```
S -> Message
Message -> Line \n Message
Message -> E
Line -> Key:Value
Key -> alnum Key
Key -> E
Value -> alnum Value
Value -> E
```

Message must consist of a Line ended with new line character or be empty. Line must consist of a Key and a Value seperated by a colon. Key and Value are defined recursively. Each is a sequence of alphanumerics with length zero or more. I used *alnum* as a shortcut for any alphanumeric character. To be fully formal I would have to define a rule for each alphanumeric character. But use of *alnum* abbreviation depicts my intention better than formal approach. The are three important observations about this grammar. First is that each rule contains only singular nonterminal in its input. Second is that the choice of rule is dictated by the text. In fact there is always only one rule we can apply. If text is nonempty then Message cannot be *E*. You append *alnum* to Key or Value until the text cursor points to nonalphanumeric character (here colon or new line) or all characters from input are processed. Third is that Value cannot contain colon. Although that prevents us from processing actual http message, we can process a simplified http header what is for our purpose sufficient.

In general automata theory there are grammars those three observations do not apply to. Observations first and second are required and hold great significance. Unless there is only one rule to apply at the moment, parsing turns into optimization problem. Because we want parsers to work fast that observation becomes the requirement. All programming languanges fulfill this requirement.

All that remains is to explain to a machine how to use our grammar. Let's formulate the steps we take ourselves. To do so let's consider example text.

```
a:1
b:2
text:hello world
```

I can see that each line ends with new line character. A line should be generated by *Key:Value*. Next I notice the colon symbol. With that we finished singular line. Next is repetition. I can program sequence of these operations into a machine. That might result in both practical and fast parser, however, it differs quite a bit from the solution we would use to prove our model. To prove our procedure works we must present how a grammar generates a text in general. And if the grammar does not generate the text our procedure must arrive at the same conclusion.

Let's try again but this time we label each rule.

```
0: S -> Message
1: Message -> Line \n Message
2: Message -> E
3: Line -> Key:Value
4: Key -> alnum Key
5: Key -> E
6: Value -> alnum Value
7: Value -> E
```

* start
`S`
* apply rule 0
`Message`
* apply rule 1
`Line \n Message`
* apply rule 3
`Key:Value \n Message`
* apply rule 4
`a Key:Value \n Message`
* apply rule 5
`a:Value \n Message`
* apply rule 6 and 7
`a:1 \n Message`

Continue until finished. As you can see we apply rules to the first symbol until it is replaced with terminals. In the process we add more nonterminals. Hopefully those nonterminals are eventually replaced with terminals. We do not store terminals. Only compare them with the text. We keep cursor pointing to the next terminal in the text. We need only to remember the sequence of nonterminals. We always need the first nonterminal and new symbols replace the first thus we can keep them on a stack. In fact it is useful to think we pop one symbol form the stack at each step. Then we apply a rule. We ignore the prefix terminal and put the rest onto the stack. Sometimes a terminal ends up on the stack. For instance colon. When we take terminal from the stack it is easy to determine if the terminal pointed by the cursor is the same. In fact we did same thing by applying rule 4. *alnum* abbreviation makes that fact implicit. When those are the same, we discard the terminal and move the cursor forward.

We see that to implement our model we need the cursor pointing to first unprocessed terminal in the text, the stack for symbols with *S* symbol placed into. Now we have enough to prove the model.

```
initialization: push start symbol on stack
invariant: stack contains unmatched symbols
loop: as long as stack is not empty
    pop symbol from stack
    if a rule matches to input
        apply rule, if present push unknown symbols on stack
    else
        push symbol back and break
termination: if both input and stack are empty, then grammar matched.
```

Symbol is matched when we found out the terminal for substitution. We know that only one rule matches at a time. If rule adds a symbol to the stack that symbol is unmatched. Previously unmatched symbols are still on the stack in the same order.

Let's assume the grammar generates the text. The sequence of rules exists. Would our procedure find the same sequence? As stated before the choice of rule is determind by terminal in the text pointed by the cursor. This is not implementation detail. That observation applies to the grammar itself. Since procedure shares that grammar the sequence is determined by input.

Let's assume the grammar cannot generate the text. Necessary sequence of rules does not exist. Would our procedure record mismatch? Procedure shares the grammar and the text thus shares sequence of rules. Because the grammar cannot generate the text the sequence does not consume all all terminals from text or stack is not empty. QED

## *c* solution

The very first implementation of our parser should be in *c* - lingua franca of computer world. We define symbol as an enum.

```c
enum symbol
{
    SYMBOL_START,
    SYMBOL_MESSAGE,
    SYMBOL_LINE,
    SYMBOL_KEY,
    SYMBOL_VALUE,
    SYMBOL_TEXT,
    SYMBOL_CHARACTER,
    SYMBOL_CHARACTER_MORE,
    SYMBOL_CHARACTER_COLON,
    SYMBOL_CHARACTER_NL,
};
```

As you remember, there are three things we need to store in memory: the text, the stack and the cursor.

```c
const char * input_it = input;
enum symbol stack[input_size];
char key[input_size];
char value[input_size];
int stack_top = 0;
unsigned key_it = 0;
unsigned value_it = 0;
stack[stack_top] = SYMBOL_START;
```

Since we work in *c* we must manage our memory ourselves. The stack is bigger than necessary. Arrays `key` and `value` are to accumulate the terminals for placeholders (symbols) *Key* and *Value*. We need them to print a meaningful debug message.

```c
while(stack_top > -1 && input_it != input_end)
{
    const enum symbol symbol = stack[stack_top];
    stack_top--;
    const char next_char = * input_it;

    switch (symbol)
    {
        ...
    }
}
```

The ruleset is implemented with the `switch`. Each rule is identitied by the symbol and the terminal pointed to by the cursor.

```c
case SYMBOL_LINE:
    stack[++stack_top] = SYMBOL_VALUE;
    stack[++stack_top] = SYMBOL_CHARACTER_COLON;
    stack[++stack_top] = SYMBOL_KEY;
break;
case SYMBOL_CHARACTER:
    if(isalnum(next_char) || next_char == ' ')
    {
        input_it++;
        field[(* field_it)++] = next_char;
    }
break;
```

There might be some discrepancy between the model and the solution. It seems I was too eager to avoid memory fault. Thus `while` loops until both symbol stack is not empty and cursor points to the text. Model uses a weaker condition.

The solution is very explicit. We had to find a way to explain to a computer how to implement a queue, string buffer and iterator/cursor. Since we need the length of the input we have to remember how a string is stored in memory - namely null terminator. The stack index requires special value for empty stack. There are accumulators defined far away from place of use. All that puts implementation details up front and obscures the model itself.

In *c* the language domain is the machine. Mental gymnastics is required to transform our model into computer procedure. Let's improve our solution with *c++*. In the wnext step we focus only on containers. I want evolution not revolution. Each new solution should remain functional but (hopefully) introduce some improvement.

## *c++* transition

*c++* gives us a more convenient way to express a symbol. Even though it is absolutely nonessential I could not help myself not to replace `enum` with `enum class`.

```c++
enum class symbol
{
    start,
    message,
    line,
    key,
    value,
    text,
    character,
    character_more,
    character_colon,
    character_nl
};
```

We keep global accumulators but delegate details to containers. We lose control over memory management what might be detrimental in some applications - namely embedded.

```c++
auto input_it = input.begin();
std::vector<enum symbol> stack;
std::string key;
std::string value;
stack.push_back(symbol::start);
```

Even though the changes are mostly aesthetic the intention behind the code feels much clearer.

```c++
while(not stack.empty() and input_it != input.end())
{
    const enum symbol symbol = stack.back();
    stack.pop_back();
    const char next_char = * input_it;

    switch (symbol)
    {
        ...
    }
}
```

Same goes with `switch`.

```c++
case symbol::line:
    stack.push_back(symbol::value);
    stack.push_back(symbol::character_colon);
    stack.push_back(symbol::key);
break;
```

If a seasoned developer were to inspect the code, I am sure they would not rate it highly. They would argue that the *big switch* is a sign of a bad code. That opinion comes from the so-called "good practices". It is a "good practice" to keep a function short - preferably shorter than a screen-long. There is no real need to follow that advice. Regardless, people adhere to this one and alike blindly. It comes from the believe that no good advice can make things worse. It is obviously false. All we know for sure is that sometimes it helps to keep your function short.

Unfortunately, the demand is strong. We must not give in to the popular demand, however, let's explore a possibility while we are at it. We need a less mundaine, cleverer way to express the *big switch* with.

## visitor pattern

If I were to describe again the function of *big switch*, I would say it routes code execution to the callback assigned to the symbol. Our routing involves only a symbol. In the model it takes both symbol and terminal to choose the rule from the ruleset. Here we perform the choice in two steps. Find the callback for the symbol. Then find the rule for the terminal (pointed by the input cursor). We would like to outsource that routing to the programming language.

In latest additions to *c++* we can find a few tools seemingly just for that. We can outsource the routing to `std::visit`. Function takes two arguments, namely a visitor and a visitee. The visitor is the replacement for the *big switch* and the visitee is any symbol. Because the visitee is of template type `std::variant<...>` we have to define a type for each symbol. In the end `std::visit` unpacks visitee to find out the exact type and calls correct method from visitor.

Before we can make use of the visitor pattern we need to declare the visitee.

```c++
namespace symbol
{
    struct start {};
    struct message {};
    struct line {};

    struct text {};

    struct key : text {};
    struct value : text {};
    struct character
    {
        char ch;
    };
    struct character_any {};
    struct character_more {};

    using any = std::variant<
        start,
        message,
        line,
        key,
        value,
        character,
        character_any,
        character_more,
        text
    >;
}
```

Our visitee is `symbol::any`. You should notice that by replacing `enum class` with `std::variant<...>` we can put much more data into a singular symbol. We were required to create new `struct` for the sake of variant but it turns out to be a worthwhile pursuit. Take a look at `symbol::character`. With build in parameter it replaces three symbols used in *c* solution: `character`, `character_colon` and `character_nl`. Moreover, no mental gymnastics is necessary since the symbol has one to one translation into character category we are accustomed to. As a side note, notice that perhaps the most important improvement of *c++* over *c* is that the enhanced type system allows us to transition from memory mappings into a space of categories.

Next one is the visitor. A little more work is required. For a visitor to handle the visitee for each symbol an overloaded functional operator is necessary. Since we are commited to gradual changes we divide the whole transition into two steps. The first is to transform singular case. The second is to combine those cases into the visitor.

We know that each case handles only one symbol. We need a functional object. Thus it stands to reason we could use lambda feature. Now since we have our callbacks we need to pack them all into a singular class. Fortunately, there is a new, recently popular construct we can use. We can combine those lambdas into a class practically on-the-fly.

```c++
const symbol::any symbol = stack.back();
...
std::visit(
    overloaded
    {
        [&](symbol::start)
        {
            ...
        },
        [&](symbol::message)
        {
            ...
        },
        ...
    },
    symbol
);
```

The idea is to define a class that inherits from a list of lambdas. Each lambda is an instance of anonymous functional class. To operate on a list of anonymous types is definitely unwieldy. However, with the advent of variadic templates in *c++11* we finally have the tool.

```c++
template<typename ... Ts>
struct overloaded : Ts...
{
    using Ts::operator()...;
};

template<typename ... Ts>
overloaded(Ts...) -> overloaded<Ts...>;
```

The benefit is that our symbols are no longer of some enumeration type but actual [sum type](https://en.wikipedia.org/wiki/Algebraic_data_type). We had to use `enum` but it was the sum type what we wanted all the way. A sum type is a quite easy concept used widely in mathematics. We group types together and construct a new one. Thus it is easier to express any relation for the entire set. Without that functionality we have to repeat the same relation for each member. As before with categories we circle back to the example of `symbol::character`. It is much simpler for us to apply the same category system we use outside programming. The drawback is that we distant ourselves from the hardware domain our equipment operates in.

Is it the right direction? Should we build programming languages that present user friendly semantics? If you think it is no-brainer, then you are one of those poor souls who bought into clean code/code style guidelines crowd. Although clarity does speed up development what is indeed important business measure, we unwillingly allow in laymans by lowering the learning curve. And those bastards draw us all into Mariana Trench. Much more software is being released but quality of most is that of the trash. But let's go back to the main topic. Ad meritum.

The capability to define a sum type is definitely a plus. But we pay a hefty price. Our solution requires more than one nasty trick introduced with the newest standard. In short, `overloaded` represents all what is wrong with the direction *c++* is going in. Yes, it is great we have variadic templates. We can generalize not only over one, two or greater known number of unknown objects/types but over a list of unknown size. The trouble is that templates have always been and still are nothing but a glorified macro system. The bad news is that it is never going to change. There is no way to improve templates without braking backward compatibility. To do so means to create a new language. And, obviously, no one would try it - no one would risk their career.

Templates share a drawback with macros. Both rely on syntax. Both templates and macros do not convey semantics. Thus any error is by necessity a syntax error. The reported error is usually placed later down the code where language parser fails not where the mistake was made. Such compile errors are useless. To fix problem you have to literally guess the mistake. It is either boom or bust; templates either work as expected or fail so hard that there is no other choice but to abandon the code. As a result, the so-called "good practices" advice to avoid metaprogramming. It is not technically the same as templates but the bar is so low that nowadays software developers know no difference.

The question is whether anything we made so far can be salvaged? The point of all this template magic was to outsource the boring and mundaine routing to the language. Symbol routes execution. Grammar puts symbols into a hierarchy. If we assign a parser function per symbol, then routing would not be necessary. The function would know if symbol should consume another terminal from the input. We could reduce the entire *big switch* to a singular function call `parse(message)`. Since we abandon an object in favor of a procedure we transition from object oriented to procedural paradigm. Is that the right direction? We shall see.

## procedural paradigm

The direction might feel counterintuitive. Procedural is older then object oriented paradigm. Some might say it was superseded by better alternative. Anyway, we all were educated to work within the constraints of object oriented paradigm. To deviate from that radically contradicts the learned instincts. Thus I believe in-depth examination of our thought process is in order.

The grammar is specifically designed so that we could choose the proper rule from the ruleset taking into account only the current symbol and the current terminal (pointed by the cursor). The *c* solution first narrows down the ruleset to the rules that apply to the current symbol. That is the *big switch*. If there are multiple rules to choose from, then it is up to the `case` to choose and apply one. The visitor is another way to express the same program. From a topological perspective the shapes of both solutions are the same. Each iteration we access current symbol, current terminal and then make decision.

We observe that symbols can be structured into a hierarchy. We could say that to parse a line means to parse a key, a colon, then a value. If we were to keep the current symbol but move cursor, we would avoid unnecessary stack operations. The symbol hierarchy helps us design the set of parser functions each capable of parsing the input for a singular symbol.

```c++
bool parse(
    std::string_view::iterator & input_it,
    std::string_view::iterator input_end,
    data::message & message
)
{
    while(input_it != input_end)
    {
        message.lines.push_back({});
        bool successful = parse(input_it, input_end, message.lines.back());
        if(not successful)
        {
            return false;
        }
        input_it++;
    }
    return true;
}

bool parse(
    std::string_view::iterator & input_it,
    std::string_view::iterator input_end,
    data::line & line
)
{
    bool successful;
    successful = parse(input_it, input_end, line.key);
    if(not successful)
    {
        return false;
    }

    char next_char = *input_it;
    if(next_char != ':')
    {
        return false;
    }
    else
    {
        input_it++;
    }

    successful = parse(input_it, input_end, line.value);
    if(not successful)
    {
        return false;
    }

    return true;
}
```

Those remind me the very first time I applied the grammar myself. Having a symbol in mind I was controling the input as necessary. Symbol defines here the context. The `parse` procedure does not require symbol per se but a symbol's derative to accumulate data.

```c++
namespace data
{
    struct text
    {
        std::string content;
    };

    struct key : text {};
    struct value : text {};

    struct line
    {
        struct key key;
        struct value value;
    };

    struct message
    {
        std::vector<line> lines;
    };
}
```

The difference is that `data` namespace stores information in the memory while `symbol` namespace refers to how we interpret terminals. Even though in theory those differ only slightly in practice are distantly apart.

```c++
data::message message;
auto input_it = input.begin();
bool successful = parse(input_it, input.end(), message);
```

Well, that is short. A studious follower of "good practices" would wholeheartedly approve. But I am not. It is very easy to get lured into following code style guidelines. Those `parse` functions are indeed short and clear. Arguments leave little room for any mistake. Mundaine operations are delegated to either the iterator or the container. This code is guaranteed to receive approval at every review.

Yet, this solution has ginormous design flaw built-in. I am adamant about it. To catch a flaw of this kind you need true experience or thorough education. It could be a useful tool to sievie a real programmer from some wannabe software developer.

The design flaw is unintentional coupling between text parsing and input control. There are more people working with web services then microcontrollers. Things like communication error or time-based frame separation are very low level. Because of a layered models like for instance [OSI model](https://en.wikipedia.org/wiki/OSI_model) people who interface with higher layer are oblivious to low level concerns. And rightfully so. We build those layers to make stable guarantees. It is great they are so reliable that users can forgo the low level details.

But when a high-level developer finds themself working with a layer lower than they are accustomed to they tend to view issues from the familiar but not the correct perspective. So, why is the coupling objectively deficient? To couple input control with parsing is to deem the control trivial. Firstly, is your input complete? I know you outsource transport resposibilities but those transport routines only convey data blindly. There are known circumstances where [MTU](https://en.wikipedia.org/wiki/Maximum_transmission_unit) is not preserved across the route. Thus you should not assume your message is complete. Secondly, what separates consecutive messages? It is the parser that uncovers the meaning hidden in some raw input. You can try a delimeter but then you must guarantee that this delimeter never occurs in the payload of your message. Thirdly, since mistakes do happen it is reasonable to ask how your program recovers if two messages unpredictably overlap?

Those concerns stem from uncertainty of the input. What is your input? A file? A socket? A pipe? There are two types of parser in use. A streaming parser is the kind that returns information as soon as it is found then forgets. A nonstreaming parser is the kind to take the complete input and return complete in-memory representation. The first might be faster and simpler but to find any information you must start entire parsing process from scratch. The second one builds a database for you which you can question later repeatedly.

Do you know what purpose should our parser serve? No. What I am doing in this post is investigation/research. The coupling is real. To accept the flaw at this stage is a mistake. There might be a situation where the design with coupling is faster but one should not optimize without thorough analysis. Since in IT thorough analysis is almost unheard of the only way to find out what should be optimized is to measure performance. And that requires working software. Thus coupling is a design flaw we must remove.

### `Iterator` concept

As unbelievable as it sounds *c++* commitee does improve those awful templates. If only we could specify a requirement for a template parameter. And it turns out we can.

```c++
template<typename I>
concept Iterator = requires(I i)
{
    { * i } -> auto&&;
    { i != i } -> bool;
    i++;
};
```

Ain't that clever? We can apply `*` operator to an instance. That hardly fixes template system but it is all we got. Let's try them on.

```c++
bool parse(Iterator & input_it, Iterator input_end, data::message & message)
```

At the very least we are no longer limited to `string` container. But the flaw still stands. To really fix the flaw we have to take a more radical action. The most important factor is the lack of control. Once we pass the interator to a function we cannot interfere. What would happen if we did? What would happen if we were to carve the interator out of a function? That function would require us to supply the next terminal. A function has an internal state. We have to preserve that state somehow. Let's move it out to the global scope - to keep transition relatively simple. Another problem is that to have full control we must not allow those carved functions to call each other. We have to implement function call stack ourserlves. That sounds horrid. But might work. Let's do it then!

## implement you own function call stack

It pains me to do so but I assume it is a good idea to store the context of each `parse` function into a global variable. "Global" is always wrong, right? Well, our program is inherently single threaded and each function has only singular accumulator argument. Perhaps we should pass relevant accumulator to each function as an argument? But that forces us to operate on raw memory (`void *`) what is another idea that sends cold shiver down the spine. Global accumulator it is.

```c++
data::message message;
data::line * current_line;
data::key * current_key;
data::value * current_value;
data::text * current_text;
```

`data::message` accumulates all information. We need those pointers to keep "cursor" pointing to the field we are filling in at the moment.

```c++
struct parse_fn_res
{
    bool error;
    bool consumed;
    parse_fn next_parser;
};

parse_fn_res parse_text(char next_char)
{
    if(isalnum(next_char) || next_char == ' ')
    {
        current_text->content.push_back(next_char);
        return
        {
            .error = false,
            .consumed = true,
            .next_parser = nullptr
        };
    }
    else
    {
        return
        {
            .error = current_text->content.empty(),
            .consumed = false,
            .next_parser = nullptr
        };
    }
}
```

Function signature had to undergo serious change. Since the function is no longer in charge of call stack it must inform us what should be parser's next step. The function might consume the terminal and ask for another, parsing might fail or be delegated to a function for another symbol.

You should notice the signature accepts a `char` - terminal. It is assumed there is terminal available in each iteration. Again, that might be not true for any grammar but works this time. In general loop should use the condition specified in the model - not to couple the loop with the input as done here.

```c++
using parse_fn = parse_fn_res(*)(char);
auto input_it = input.begin();
std::vector<parse_fn> backtrace;
backtrace.push_back(parse_message);
```

We store function call stack in `backtrace`. There is no symbol stack, obviously, since in procedural approach we do not use them.

```c++
while(not backtrace.empty() and input_it != input.end())
{
    parse_fn parser = backtrace.back();
    const char next_char = * input_it;
    auto result = parser(next_char);
    if(result.error)
    {
        break;
    }
    else if(result.consumed)
    {
        input_it++;
    }
    else if(result.next_parser)
    {
        backtrace.push_back(result.next_parser);
    }
    else // rejected
    {
        backtrace.pop_back();
    }
}
```

That is all fine and dandy but we need to clear up a few not so well coded workarounds.

### `parse` returns a tagged union

First is `parse` return type. We want sum type (tagged union) again.

```c++
struct error {};
struct consumed {};
struct rejected {};
struct next_parser
{
    parse_fn parser;
};
struct parse_fn_res : std::variant<error, consumed, rejected, next_parser> {};
```

There is no point in recruting any heavy equipment like `std::visit`. Regular `if-else` can do the job nicely.

```c++
auto result = parser(next_char);
if(std::holds_alternative<error>(result))
{
    break;
}
else if(std::holds_alternative<consumed>(result))
{
    input_it++;
}
else if(std::holds_alternative<next_parser>(result))
{
    backtrace.push_back(std::get<next_parser>(result).parser);
}
else if(std::holds_alternative<rejected>(result))
{
    backtrace.pop_back();
}
```

The next item on the chopping block is the global accumulator.

## add closure to each parser

To get rid of the global accumulators we replace each `parse` function with a functional object.

```c++
using parse_fn = std::function<parse_fn_res(char)>;

struct message_parser
{
    data::message * message;

    parse_fn_res operator()(char)
    {
        message->lines.push_back({});
        return {next_parser{line_parser{&message->lines.back()}}};
    }
};
```

Each parser object requires the pointer to proper accumulator. There is only one `data::message` instance with local scope. No variable has to be global any longer.

```c++
auto input_it = input.begin();
std::vector<parse_fn> backtrace;
data::message message;
backtrace.push_back(message_parser{&message});
```

You should take a moment to examine how helpful `std::function` turns out to be. Both *c++* and *c* can easily manipulate a pointer to a function. But to add some context to a function in *c* requires raw memory (`void *`). Since `this` is always implicit *c++* offered us very smooth transition from a function with global state to a functional object.

## separate data from behaviour, `parser` trait

We are not done yet. Not by a long shot. Take a look at our `line_parser`.

```c++
struct line_parser
{
    data::line * current_line;
    enum class stage
    {
        next_line,
        parsed_key,
        parsed_value
    } current_stage;

    line_parser(data::line * current_line)
    : current_line(current_line), current_stage(stage::next_line)
    {}

    parse_fn_res operator()(char ch)
    {
        if(current_stage == stage::next_line)
        {
            current_stage = stage::parsed_key;
            return {next_parser{text_parser{&current_line->key}}};
        }
        else if(ch == ':')
        {
            return {consumed{}};
        }
        else if(current_stage == stage::parsed_key)
        {
            current_stage = stage::parsed_value;
            return {next_parser{text_parser{&current_line->value}}};
        }
        else if(ch == '\n')
        {
            return {consumed{}};
        }
        else
        {
            return {rejected{}};
        }
    }
};
```

Do you see? Another thing that would not survive a code review - the *big if*. I am not exaggerating. The *big if* shares flaws with the *big switch*. Complex of too many instructions is too hard to remember thus to understand. You would be asked to divide it into a collection of functions. Does it sound stupid? Because it is!

`line_parser` covers all paths parser execution might take, however, the execution reenters the function. In the meantime other parsers might be executed; notice that a *Line* "consists" of a *Key* and a *Value*. The reentry makes execution unpredictable. Even though our parser is very simple the design is still too complex for a human being to casually understand. Yes, too complex! In a code review the prime directive is to satisfy the reviewers. And I was told that a code review is to improve code quality. Ha!

We could say that the closure of each functional object crosses the boundary marked by a struct definition. Take a look at `enum class line_parser::stage`. We know that to parse *Key* and *Value* symbols `line_parser` modifies indirectly the call stack. As a result, the execution jumps up and down the symbol hierarchy.

Don't get me wrong. It works fine. But reviewers are not going to give it a pass. It is little troublesome. I am sure you experienced the same situation yourself. Although your work is finished, your reviewers say otherwise. What is my purpose? To solve problems or satisfy reviewers? It gets complicated when reviewers are dedicated to fulfill their whims more than client's requirements.

You might think that bringing symbols back is a good idea. I share that sentiment. Yet, If I were to reintroduce symbols, `line_parser` would become much bigger - an overload for each symbol; a lot of boilerplate. I would like to show you the way to resolve that conundrum.

You must have heard of a `trait`. I am sure you have forgotten all about it by now. Example `trait` available in *c++* is [`std::numeric_limits`](https://en.cppreference.com/w/cpp/types/numeric_limits).

```c++
#include <limits>
#include <iostream>

int main()
{
    std::cout << "type\tlowest()\tmin()\t\tmax()\n\n";

    std::cout << "uchar\t"
              << +std::numeric_limits<unsigned char>::lowest() << '\t' << '\t'
              << +std::numeric_limits<unsigned char>::min() << '\t' << '\t'
              << +std::numeric_limits<unsigned char>::max() << '\n';
    std::cout << "int\t"
              << std::numeric_limits<int>::lowest() << '\t'
              << std::numeric_limits<int>::min() << '\t'
              << std::numeric_limits<int>::max() << '\n';
    std::cout << "float\t"
              << std::numeric_limits<float>::lowest() << '\t'
              << std::numeric_limits<float>::min() << '\t'
              << std::numeric_limits<float>::max() << '\n';
    std::cout << "double\t"
              << std::numeric_limits<double>::lowest() << '\t'
              << std::numeric_limits<double>::min() << '\t'
              << std::numeric_limits<double>::max() << '\n';
}
```

The general idea is to create a template (a struct, a class, a function or even an alias) that can supply you with additional information about the type. There are absolutely no restrictions on what that `trait` should be. As a result, you are required to exert mental discipline to stick to implicit (or unknown!) requirements. Since that technique is neither supported nor sanctioned, you should brace youself for lots of trouble.

I intend to utilize a `trait` in the spirit of programming language `rust`. Out there a `trait` replace a `class`. A `trait` is like an interface but you "assign" it to an existing type. You are required to explain how the `trait` should be implemented for a given type; not the other way around. The benefit is that you can extend any (not really) previously defined type. In *c++* once a type is defined it is sealed and closed. You should not confuse it with type inheritance. This technique allows us to create a derivate type - not to modify the base type. [Curiously recurring template pattern](https://en.wikipedia.org/wiki/Curiously_recurring_template_pattern) can do just that but it is a far fetched pattern.

I would like to convince you of *`trait`* advantages. Let's examine a trival `class`.

```c++
class trivial
{
    void operation();
};

void trivial::operation() {}
```

We know a method has an implicit `this` argument. Let's use our imagination and make `this` explicit.

```
class trivial
{
    void operation(trivial * this);
};

void trivial::operation(trivial * this) {}
```

It turns out we could add `operation` to class `trivial` like below.

```c++
class trivial {};

template <typename T>
void operation(T * self) {}

template <>
void operation(trivial * self) {}
```

I had to replace `this` with `self` since no language keyword can be used as a variable name.

You might dislike this approach because for any `trait` to compile the content of `trivial` must be public. In `rust` you have robust module support. Unfortunately, `c++` is stuck still in the late 90s. I am willing to overlook that detail. I wish to trade some discomfort for the power to extend any type as I please. To make that work we have to abandon our object orientend mindset. For a `trait` to work at its full potential it has to be truly free.

```c++
namespace parser
{
    namespace result
    {
        struct error {};
        struct consumed {};
        struct rejected {};
        struct next_parser
        {
            frame::any parser;
        };

        struct any : std::variant<error, consumed, rejected, next_parser> {};
    }

    template<typename T>
    result::any parse(T & self, char next_char);
}
```

What we should do now is to split each parser (functional object) into implementation of `parser::parse` (behaviour) for the relevant symbol and the context. Because a context (state) has the same purpose as a function frame (is placed on a call stack) for a function call, I shall put each context into `frame` namespace.

```c++
namespace frame
{
    struct text
    {
        data::text * current_text;
    };

    struct line
    {
        data::line * current_line;
        enum class stage
        {
            next_line,
            parsed_key,
            parsed_value
        } current_stage;

        line(data::line * current_line)
        : current_line(current_line), current_stage(stage::next_line)
        {}
    };

    struct message
    {
        data::message * message;
    };

    using any = std::variant<text, line, message>;
}
```

We got another sum type `frame::any`. A sum type (a tagged union) has the size of biggest member. That might lead to inefficient memory usage. At lease a few clever workarounds would be necessary. Here I ignore this issue entirely.

```c++
template<>
parser::result::any parser::parse(frame::text & self, char next_char)
{
    if(isalnum(next_char) || next_char == ' ')
    {
        self.current_text->content.push_back(next_char);
        return {result::consumed{}};
    }
    else
    {
        if(self.current_text->content.empty())
        {
            return {result::error{}};
        }
        else
        {
            return {result::rejected{}};
        }
    }
}
```

Transition fortunately does not entail extensive refactor.

```c++
auto input_it = input.begin();
std::vector<frame::any> backtrace;
data::message message;
backtrace.push_back(frame::message{&message});
...
while(not backtrace.empty() and input_it != input.end())
{
    frame::any & frame = backtrace.back();
    const char next_char = * input_it;
    auto result = std::visit(
        [next_char](auto & frame)
        {
            return parser::parse(frame, next_char);
        },
        frame
    );
    ...
}
```

One more small change to unpack the new sum type and the transition is finished.

## bring back symbols, rules instead of `if-else`

Let's take a look at the context of `parser::parse(frame::line &, char)`.

```c++
namespace frame
{
    ...
    struct line
    {
        data::line * current_line;

        enum class stage
        {
            next_line,
            parsed_key,
            parsed_value
        } current_stage;

        line(data::line * current_line)
        : current_line(current_line), current_stage(stage::next_line)
        {}
    };
    ...
}
```

Surprisingly, there no such elaborate logic was present in the grammar. It seems we unknowingly made a workaround. We need line parser to know the progress. There was no such need in the model. The model has symbol stack to keep track of progress. In the functional paradigm solution we used the function call stack for that. We walked up and down the symbol hierarchy. The state was internalized into the code of each `parser` function. Thus the next step is to bring back symbols. But to do so we have to redo the `trait`.

```c++
namespace symbol
{
    struct message {};
    struct line {};
    struct key {};
    struct value {};
    struct colon {};
    struct new_line {};

    using any = std::variant<message, line, key, value, colon, new_line>;
}

namespace parser
{
    namespace result
    {
        struct error {};
        struct consumed {};
        struct finished
        {
            bool consume;
            bool frame;
        };
        struct append_symbols
        {
            std::list<symbol::any> symbols;
        };
        struct change_frame
        {
            frame::any frame;
        };
        struct any : std::variant<error, consumed, finished, append_symbols, change_frame> {};
    }

    template<typename S, typename F>
    result::any parse(S symbol, F & frame, char next_char)
    {
        return {result::error{}};
    }

    template<typename S, typename F>
    result::any parse(S symbol, F & frame)
    {
        return {result::error{}};
    }
}
```

Now our line parser is divided into a collection of functions. One for each pair of the symbol and the frame.

```c++
template<>
parser::result::any
parser::parse(symbol::line, frame::line & frame, char next_char)
{
    return
    {
        result::append_symbols
        {
            .symbols =
            {
                symbol::key{}, symbol::colon{}, symbol::value{},
                symbol::new_line{}
            }
        }
    };
}

template<>
parser::result::any
parser::parse(symbol::key, frame::line & frame, char next_char)
{
    return
    {
        result::change_frame
        {
            .frame = frame::text{&frame.current_line->key}
        }
    };
}

template<>
parser::result::any
parser::parse(symbol::colon, frame::line & frame, char next_char)
{
    return {result::finished{.consume = true, .frame = false}};
}

template<>
parser::result::any
parser::parse(symbol::value, frame::line & frame, char next_char)
{
    return
    {
        result::change_frame
        {
            .frame = frame::text{&frame.current_line->value}
        }
    };
}

template<>
parser::result::any
parser::parse(symbol::new_line, frame::line & frame, char next_char)
{
    return {result::finished{.consume = true, .frame = true}};
}
```

You know what I am about to write. Too long. That would not be accepted in a code review. We adhere to the model closely. Each implementation of `parser::parse` maps one-to-one to a rule in the ruleset. But no - too long. You should comprehend by now how insignificant things like a model, an invariant or any other academic devices are to community of software developers. In real software development you should make an interface and replace the *big if* with dynamic dispatch. Some ignorant might even call it "polymorphism". I call it a half-wit workaround.

## use `parser` trait to make rule table

Even when you are surrounded by troglodytes you should not brake under the pressure. Remember to refine your model and avail it in your program. The final refinement to my parser is to highlight the strong relation to the model's ruleset.

```
#define PARSER_TABLE_ENTRY_WITHOUT_INPUT(S, F) \
    template<> parser::result::any parser::parse(S, F&);
#define PARSER_TABLE_ENTRY_WITH_INPUT(S, F) \
    template<> parser::result::any parser::parse(S, F&, char);
```

Grammar consists of rules. Each one describes a substitution for a nonterminal symbol. If there was a reference manual for the grammar, rules would be presented in the form of a table. A row for each rule. That is easy to grasp. So the macros defined above should help a newcomer. Entire ruleset implemented in my parser is concisely presented below.

```c++
PARSER_TABLE_ENTRY_WITH_INPUT(symbol::not_delimeter, frame::text)
PARSER_TABLE_ENTRY_WITH_INPUT(symbol::not_delimeter_more, frame::text)

template<typename S>
parser::result::any parser::parse(S, frame::text & frame, char next_char)
{
    return
    {
        result::append_symbols
        {
            .symbols = {symbol::not_delimeter{}, symbol::not_delimeter_more{}}
        }
    };
}

PARSER_TABLE_ENTRY_WITH_INPUT(symbol::value, frame::line)
PARSER_TABLE_ENTRY_WITH_INPUT(symbol::character, frame::line)
PARSER_TABLE_ENTRY_WITH_INPUT(symbol::key, frame::line)
PARSER_TABLE_ENTRY_WITH_INPUT(symbol::line, frame::line)
PARSER_TABLE_ENTRY_WITH_INPUT(symbol::line, frame::message)
PARSER_TABLE_ENTRY_WITH_INPUT(symbol::message, frame::message)
PARSER_TABLE_ENTRY_WITHOUT_INPUT(symbol::message, frame::message)
```

But those are no rules! It is convenient to think we have a thing in the codebase that can be directly mapped to a rule. In my parser each implementation of the `parser` trait combines all rules that apply to the same nonterminal symbol. That differs from the model. Our "rules" (implementations of the trait) are expressed in terms of a nonterminal symbol and a frame. Is there any way to reconcile the model with the implementation?

To do so we need to remind ourselves what is the purpose of `frame` in the trait. Each symbol has an accumulator type assigned. It accumulates the meaning behind the symbol during parsing process. Frame narrows down the memory context we focus on.

Take for instance implementations for `symbol::line`. When you are in `frame::message` context, then you need to narrow it down by putting `frame::line` on the stack. To process line symbol in line frame you apply the relevant grammar rule and replace line symbol with key, colon and value symbols. Now our context is `symbol::key` and `frame::line`. Next we parse the key symbol. Only `frame::text` is put on the stack. When finished both the `symbol::key` and `frame::text` are gone. We are back to `frame::line`.

My impression is that there are two type of nonterminal symbols. A symbol with inner structure like `symbol::line` and a recursive symbol like `symbol::text` (`symbol::key` or `symbol::value`). A "structured" symbol needs memory. A recursive symbol needs a pointer to that memory. A "structured" symbol is replaced with a sequence of symbols. Each one has to be processed with the "parent" frame to properly configure the pointer (why `symbol::key` is processed for `frame::line`? to set the pointer in `frame::key` to `frame::line.key` container).

Let's rephrase the above explanation but for generic case. As we walk down the symbol hierarchy the nonterminal symbol keeps the progress of parsing process, frame keeps the progress of accumulating process. If the symbol and the frame correspond we either apply a grammar rule to the symbol or accumulate terminal symbols into the accumulator.

Maybe it is better that we did not end up with a copy of grammar in the code. Afterall, we want to explain to a machine how to parse a grammar; not how to be a grammar. So in a sense `parser::parse` trait explains how to accumulate data into a frame for a symbol. We delegate execution to another pair of a symbol and a frame to reuse a procedure. We end up with some unholy alliance between machine and automata domains that preserves the benefits of both. Good stuff.
