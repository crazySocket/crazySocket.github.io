---
layout: post
title: tackle enterprise software distribution with nix package manager
date: 2017-07-16 00:00:00 +0100
categories: software nix
---

## the art of software delivery

There comes the time when you harnessed the frightful power of pointer arithmetic, sealed all memory leaks, stretched your distributed application over cluster counting myriad of servers and finally successfully implemented the paxos algorithm with all of the 99...9 exceptions. That time a new catastrophe shows over horizon. The catastrophe able to toppel the entire endeavour. The time has come for delivery.

If delivery does not send a cold shiver down your spine, you must be still young padawan. In that case, let me explain the problem a little. The software comes to be in, more or less, as a result of these steps:
* cutomer needs to improve existing process or get new ability
* developer makes a sketch of a proposed solution
* customer is convinced (sketch is ignored in favor of pricetag)
* software is developed (tasked programmer has no idea there even was a sketch)
* software is rushed (since there was actually no planning whatsoever programmer has to cut corners everywhere even if not possible)
* software is delivered (everyone hopes all the technical dept will never be discovered)
* software is being fixed (hopes are for dummies)

Yep. That is how software comes to be. For your information: technical dept is a state when parts (libraries, frameworks, whatever comes to mind) instead of being carefully connected were smashed together (or sticked with a duck tape so to speak) in order to save time and meet the deadline. Do you remember Batman Arkham or Assasin Creed games? Buggy as hell but on time. Well, it ain't exception; happens all the time; it's the rule.

Lack of vision and reliable plan plagues software developent. Technical dept accumulates with every feature. Programmers implement them based on vague description of incompetent supervisors. And number of supervisors usually exceeds number of developers; they hunt for any oportunity to call someone's else success their own; due to supervisors' uselessness let's name them as deserved - project parasites. Once the feature is presented three things can happen
* client does not like the result
* feature does not work
* all is fine

Roughly speaking less than one third you succeed. Difficulties may come from vague description, supervisor's, administrator's or (god forbid) programmer's incompetence. Whatever the case, now we have a bug to fix.

What happens now is back and forth shooting patches in hope one will cover the bug. You are very lucky if you host the software yourself. Then you can examine entire system. Otherwise you are screwed. Your sanity is challenged by
* hardly described bugs with no logs at all
* bugs that exists in third party software you have to cooperate with
* bugs that happen only in restricted access enviroment
* and the cherry on top - bugs that did not actually happen

When your customer is a corporation than you deal with dozen of departments, however, when anything goes wrong you are obviously guilty. It is like all these people who participated not so long ago in making decisions do not take any responsibility. Parasites. Administrators use to say 'if it ain't working, blame the provider'.

Even though you may think I am complaining, all I do is paint the picture of real software development. Software is not a bridge or skyscraper. Objectives and means are way more fluid. Moreover, every developer tries their best to hook the customer so that they order new features in the future or enroll in some kind of subscription. And how can you do it better than give a taste of the desired feature to the client as fast a possible.

Whether it is a new feature or bugfix you require a tool to reliably manipulate installed software. If you host the software yourself, you can get around without the tool. However, when you deal with administrators who blame you for anything even their own mistakes, the tool is a must. The burning question is how others did solve the problem and with what tools?

## what software actually is

To describe the tools we should first take a look at what is the substance the tools ought to operate on and what difficulties we actually expect them to alleviate. The substance in question is software. However, as obvious the fact is, the details are very obscure. For programmer software is text document with contents meeting some sophisticated criteria. On the other hand, for user the software is the program they can run to get the job done.

On top of that, there is plethora of programming languages each having unique peculiarities. Take for instance C and Java. First one delivers executable that can work only on designated cpu type and operating system. Second one requires Java Runtime Enviroment (JRE) to be installed. None just works. We are long past time when a program [could just work](http://www.airs.com/blog/archives/38). This feature today is reserved for bootloaders only (BIOS, grub). Now each and every program is part of something bigger. Part of operating system (vide C) or another application (vide Java; each Java application is by extension part of JRE). Some even defy our perception of what program is. How would you describe popular websites like gmail, facebook or netflix? Are these applications? Do you install, run them? Where are they located? Can you turn them off? I could rise question after question but arrive nowhere. Instead, let's start with good old C++ *hello world*.

```cpp
#include <iostream>
using namespace std;

int main(int argc, char** argv) {
  cout << "hello world" << endl;
}
```

It aint't easier than this one, does it? If this is application to be delivered, it should be straightforward. We call good old `g++ hello.cxx -o hello` and receive *hello* file with size just 8888 bytes. We are done. Let's fill in install protocol (dumb document for dumb admins dumped into some damp basement), send with archived executable both to supervisor and wait for success.

Nope. You have just failed. You do not receive praise; glory does not shines atop your desktop straight from heavens; you have just received critical alert with urgent message from important supervisor who heard from third party company that your application failed. How could that possibly happen - you wonder. Necessary details are almost impossible to acquire but when you finally get them, you see they tried to run the application on winshit.

Winshit? Really? How could anyone possibly get the operating system wrong? Than an accountant goes feverishly through a pile of docs to find some outdated, invalid, made by a marketing appendix that lists in requirements winshit improfessional 2000 BC. You did send specification with CentOS 7 and lots of other details but somehow due to corporation innertia they managed to get it wrong. Situation rescued. Problem solved. Fortunately they had a linux image lying around ready and replaced virtual machine swiftly. Let's install again. Protocol, again, archive, again, send, again, wait, again.

Next morning, right after you arrived with a latte at your desk, even before you took a sip, you hear high heals hitting down the corridor. The outdated accountant enters the room with a terrifying scent of long-past-youth hanging on her dyed hair or ghoulish makeup and immediately shouts at you. What is wrong with her - you wonder. Even though I could replace her with just a few keystrokes in a fucking PostScript, she bosses herself around. For heavens sake a coffee machine is more important than she is! - you are unable to fathom. From screams and bitching you manage to extract that the client is unwilling to pay for something totaly unrelated to your work because the installation according to the protocol you have sent failed. Now, the client demands your presence.

The talk spreads over company that you fucked up - again; that you are unreliable. As you picture the future in dire straits of unemployment, you burn your brain circuts in attempt to figure out what the heck went wrong. You arrived at the client's office. Gun at your temple, terminal in front of you, fixed it - they say. With sweaty palms you prepare enviroment; turn off all side services. Than you start the program directly; not with fancy scripts but by calling the executable right from fresh shell. And you get

```bash
failed to run command ‘/bin/hello’: No such file or directory
```

What, the, fuck. Your sanity is at its limits. THE FILE DOES EXIST! That's it. You feel coldness of the gun's steel and pray there are no computers in afterlive. You smash the keyboard with whatever comes to your mind just like the Shakespeare's monkey. Something happens. You look at the screen with idiotic expression on your face.

```bash
$ ldd /bin/hello
/lib64/ld-linux-x86-64.so.2 not found
```

Loader not found? Ain't loader part of the operating system? Yes, but Alpine linux has it under different name... wait, wasn't that supposed to be CentOS? Welcome to delivery.

## from executable to execution - loader

As unfortunate it is, the executable is bound to the system. Loader is not the only [hardcoded](https://siddhesh.in/posts/changing-the-default-loader-for-a-program-in-its-elf.html) detail. One time I moved an exec compiled on one distribution to another. The exec required [boost](http://www.boost.org/) (popular C++ library), so I installed it with `yum`. The exec horribly failed. After lots of digging I found the boost libraries on build and host distributions had different symbols in shared libraries even though versions were the same. I have no idea what the symbols pointed to. Just were different so exec could not run.

You cannot fix hardcoded-loader-path-problem (you can; let's agree for now that you cannot) but wrong-runtime-shared-objects can. Just merge the executable with libraries. In our case compile with `g++ hello.cxx -static -o hello-static`. The file *hello-static* is significantly bigger; a little over 2MB. This time `ldd` says *not a dynamic executable*. There should be no missing or wrong libraries this time, shoudn't it? However, *hello world* weights 2MB? Ain't that a bit excessive? What if boost was used? And what about transitive dependencies? Well, since these are necessary - better safe than sorry, right? This might be correct but what would you do down the line when customer ordered not one but dozen different applications? Would you deliver each compiled statically? Would you enclose boost every time? What about inter process communication (IPC)? Would you enclose [protobuf](https://github.com/google/protobuf) with each application. Yes? Better safe than sorry? Now you have just made another mistake. You don't get it? Different programmers built separate applications connected via protobuf IPC. They tested the software thoroughly. Once assured the software is legit they delivered - with incompatible versions of protobuf enclosed.

There is a harsh truth to face. Even though you might think you can work alone there is entire ecosystem of software out there. Instead of carving out your own personal space you should embrace the network and embed your application into the existing web. The very cornerstone of computer science is [code reuse](https://www.technovelty.org/linux/plt-and-got-the-key-to-code-sharing-and-dynamic-libraries.html). There is not enough resources to load thousands of executables which comes with individual copies of the same library. Instead each one should declare dependence on a library and outsource responsibility for providing it to operating system/loader. That way you can share common routines. That is where shared objects come from.

Thus each executable has a list of required shared objects. First, where does that list come from? Nowhere. By using `ldd` you have been misguided. There is no such thing as list of [required shared objects](https://stackoverflow.com/questions/29949104/linux-elf-file-how-to-get-the-shared-object-belonging-to-an-imported-function). All executable does is define symbols. And some of these symbols are marked as 'undefined'. Although present in executable they are not defined; if you have source code, look for variables or functions preceded with keyword *extern*; such entities are declared but not defined - lacking either value or body. Loader learns these symbols and searches the database for corresponding shared object. What `ldd` does is present result of that search. These may misinform you.

One time I thought an executable depended on libnvidia.so. I was puzzeled since the program was supposed to be driver neutral. So why would the executable depend on nvidia? What about ati graphics cards? Shouldn't it depend on some libati.so? No, because executables or object files depend on symbols not libraries. Moreover, this dependence does not engage only name (unique identifier) but [version](ftp://ftp.gnu.org/old-gnu/Manuals/ld-2.9.1/html_node/ld_25.html) too. In some hardcore cases you can even [override the version required](https://stackoverflow.com/questions/4032373/linking-against-an-old-version-of-libc-to-provide-greater-application-coverage). Moreover, you can even bind different code to undefined symbols by instructing loader to prefer some shared objects before these present in its database. You can alter program's function or open a subtle way of [monitoring](http://www.drdobbs.com/building-library-interposers-for-fun-and/184404926) execution that way.

## autoconf

With all that mess I wouldn't be suprised if you traded file size for convenience. However, even if you link executable statically, due to unknown loader location it's probably best idea to build the executable right on the target system. But how can one work on one system and build on another? After all, you cannot be expected to have a desktop for every possible target configuration. Can the build process be somehow independent of the system? Fortunately, the issue caused enough sleepless nights to developers of the past so they crafted what is now know as [autoconf](https://www.gnu.org/software/autoconf/manual/autoconf.html#Introduction) and [automake](https://www.gnu.org/software/automake/manual/automake.html#Introduction).

Both tools work in tandem. Automake creates instructions for building *Makefiles*, autoconf creates *configure* script that contains them. The idea is to build platform independent *configure* script that inspects the system to check if the build can be performed, then assemble instructions for *make* into *Makefiles*. As you undoubtedly figure, to build software you are going to need some tools. What autoconf & automake do for you is to simplify process enough so that most use cases can be covered just by executing

```bash
$ ./configure
$ make
$ make install
```

regardless of actual operating sytem. If you ever worked on a big project, you know such streamlining is a must. After all, writing code is recurring process. You make a change, you test it, then next change, test and so forth. To test usually means to assemble program and confirm it performs as expected. Streamlined build process takes the burden of building from your shoulders what allows developer to focus entirely on developing the software, what is exactly the point.

Autoconf covers also software delivery. Once you are satisfied with you work you can prepare distribution; archive both software and build script. User/administrator receives distribution as for instance *tar ball* (archived with `tar` and compressed with `gzip` or `bzip2`; a file with extension `*.tar.gz` or `*.tar.bz2`), extracts, enters, builds and installs with exact same commands as shown above. Simple, right? That is the gold standard of distributing open source software.

However, there is one drawback. User must use the same tools the developer does (well, besides fancy editors developers call [IDE](https://en.wikipedia.org/wiki/Integrated_development_environment)). Almost the same. To build you need `sh` or `bash`, `make` and whatever compiler is employed. Developer needs autoconf and automake too. These may require `awk`, `perl` and `m4`. Sufficie to say, autoconf and automake are old programs using some so obscure unix commands that the fact it all still works may seem magical. Although few recognise `awk`, it's part of [POSIX](https://en.wikipedia.org/wiki/POSIX). What is great about unix and so missed in winshit is implementation of great standards. Most unix and linux adhere to POSIX or [LSB](https://en.wikipedia.org/wiki/Linux_Standard_Base) so once you obtain application's source code distribution you can just build and install every piece of software; holly grail of [portability](https://unix.stackexchange.com/questions/123144/which-is-the-most-portable-of-sed-awk-perl-and-sh).

All nice and dandy but how to use autoconf/automake? Let's go back to our C++ example and add *configure.ac* file to the project

```
AC_INIT([hello], [1.0], [lord.didger@gmail.com])
AM_INIT_AUTOMAKE([-Wall -Werror foreign])
AC_PROG_CXX
AC_CONFIG_FILES([
  Makefile
  src/Makefile
])
AC_OUTPUT
```

What? You expect me to explain above? If you so insist... *configure.ac* is a list of [m4](https://en.wikipedia.org/wiki/M4_(computer_language)) macros that when expanded end up in *configure* script. Each `m4` macro looks like `macro([string])` or ``macro(`string')``. What is unusual is the quotation. In most languages strings are surrounded by `"` or `'`. Surrounded from both sides. But here you use different characters for left and right boundary. Why? In order to repeatedly expand a macro. When you run `m4` with

```m4
divert(-1)
define(`H2_COUNT', 0)
define(`H2', `define(`H2_COUNT', incr(H2_COUNT))<h2>H2_COUNT. $1</h2>')
divert(0)dnl
H2(First Section)
H2(Second Section)
H2(Conclusion)
```

you get result

```html
<h2>1. First Section</h2>
<h2>2. Second Section</h2>
<h2>3. Conclusion</h2>
```

where macro must have been clearly expanded until there was none left to resolve. Whether expansion is nested or goes in runs I don't know. I have never seen used `m4` anywhere else so it's too obscure for me to investigate topic any further.

What is important that you have just got a taste of how `m4` works so *configure.ac* should be digestable now. You can think of *AC_INIT, AM_INIT_AUTOMAKE, AC_OUTPUT* as always required *residents*. *AC_PROG_CXX* specifies your project language as C++. *AC_CONFIG_FILES* takes a list of files that should be created by the *configure* script. Each of these files must have a template file with the same path and `*.am` suffix added. In example setting *Makefile.am* has only one line

```
SUBDIRS = src
```

that tells autoconf that it should delve into *src* directory. The file *src/Makefile.am*

```
bin_PROGRAMS = hello
hello_SOURCES = main.cxx
```

tells autoconf we want to build program *hello* from *main.cxx*. The last file of the project is *src/main.cxx* with *hello world* example listed before. That is all. Simple right? Let's create *configure* script with `autoreconf --install`. The flag `--install` is responsible for copying some utility script into current directory. Let's build distribution (not linux flavor; just archive with source code and build scripts) with

```bash
$ ./configure
$ make dist
```

You should get *hello-1.0.tar.gz* with sources and all necessary scripts to build application. There are neither compilers nor libraries inside. Just instructions on how to find them in current operating system (obviously one POSIX compliant).

## pacman

Unfortunately, few embrace open source culture. Instead, people mostly want just install and just use software. Building has no appeal to them. Around this convenience grow linux distributions (i.e. Debian, Ubuntu, Arch, CentOS). The defining feature of a distribution (not to mistake with one discussed previously) is package manager. Debian has `apt`, CentOS `yum`, Arch `pacman`. Here we are gonna build a package for pacman.

For maintainer (a guy who takes care for updating a package) the start is source code distribution. They build the software and install it. With one difference. Maintainer does not want to alter their system but install the software into some other directory in the manner like it replace the root directory (`/` - file system root). You achieve this feat with enviroment varianble `DESTDIR`. If you want to install into `out`, execute ``DESTDIR=`realpath out` make install``. I used `realpath` since it seems `DESTDIR` requires absolute path. Next archive contents of the `out` directory with `tar czf test.tar.gz -C out/ .`. Your result

```bash
$ tar tf test.tar.gz
./
./usr/
./usr/local/
./usr/local/bin/
./usr/local/bin/hello
```

is archive that can be extracted into `/` of another system. That is what installation really means.

In order to automate the installation process so that user could not mess it up maintainers prepare a package. The package is an archive similar to our *test.tar.gz* but contains also some meta info like dependencies, description and stuff like that. To make a package for pacman we use `makepkg`. Program requires instructions in *PKGBUILD* file.

```bash
pkgname=hello
pkgver=1.0
pkgrel=1
arch=(x86_64)
source=(http://127.0.0.1:8888/$pkgname-$pkgver.tar.gz)
md5sums=('0b453b82c2b60b5d6e52576d92f0f69c')
build() {
  cd "$pkgname-$pkgver"
  ./configure
  make
}
package() {
  cd "$pkgname-$pkgver"
  make DESTDIR="$pkgdir/" install
}
```

These describe what, more or less, we have done before. Get the source distribution, unpack it, call `build`, call `package`, add meta info and archive result into package *hello-1.0-1-x86_64.pkg.tar.xz* ready for installation. One subtle difference though. `makepkg` seems to be unable to accept local url of the source distribution. So to test it locally you have to run a web server. You can use this [simple](https://gist.githubusercontent.com/sumpygump/9908417/raw/5fa991fda103d0b7a0c38512394a83ccada9ad6c/nweb23.c) server. It takes path as argument, and you can access directory's content just like I did in example *PKGBUILD*.

`makepkg` has lots of other options. Some of them allow you to run preinstall and postinstall scripts. That is both advantage and disadvantage. It may be that package has a shared object. It is prevalent behaviour to create a symbolic link under some other name to the shared object so that other programs could depend on the link no matter what implementation is linked there. Sounds nice but I have experienced more often than not this mechanism fails. When that happens lesser user would call quits and reinstall entire system. And I dont't disagree. Such broken link may be real hardore bug to find when you consider how packed `/lib` is (3974 files for me right now). One would wonder if only could packages be somehow immune to such mutability traps? If only could a package, and thus the software installed, be immutable.

## immutability - the utmost desire

The desire for immutability is a sign of developer growing up. We all start with C (some do with pascal - poor beasts). We implement bubble sort and red-black trees like there is no tomorrow. We fight with other students in automated judge contests for shortest program. Removing whitespaces, renaming variables to single letters, reusing variables, optimising swap operatations (from 3 into 2), you name it. Then you graduate with mental scars from aggressive optimisation to end up in a software company where all [atlassian](https://en.wikipedia.org/wiki/Atlassian) products for management of software development weight, in total, (wait for it) 1&nbsp;TB... I mean, holy shit! That's is one heavy *hello world*.

Enterprise does not care how much software weights. Only students fancy these optimisations. Company stress reliability. And if a GB brings you closer to it, let's add two GBs for a good measure. That is why ruby programmers are so keen on importing another gem instead of writing the necessary code themselves no matter how easy it is. Same for Java. You need to turn InputStream into a String? Just import *commons-io* from apache. In the end the project looks like a patient with terminal obesity. Fortunately, project ain't human - it'll live. You might crash at interpreter's (bash by default) line length limit when putting all these jars into classpath but I am sure there is a maven plugin you can download just for that occasion.

The downfall is you imported so much foreign code that you no longer know what (supposedly) your application does when running (ever read [Przekładaniec](https://en.wikipedia.org/wiki/Przek%C5%82adaniec) by [Lem](https://en.wikipedia.org/wiki/Stanis%C5%82aw_Lem)?). The program may start threads escaping your control. What shouldn't bother you, unless you pay for execution time. The cloud services are fed up with hosting such overgrown software so they charge you for how much memory or cpu time you (I mean your application) consumed. Old software is doomed. There is nothing anyone can do anymore to save it. However, new software might just do the trick. Here come smart frameworks like [aws lambda](https://aws.amazon.com/lambda/). All you do is supply one function that processes a request into a response. You pay only for time consumed by executing this function. Nothing else (well, let's leave the statement as is).

But couldn't you do it even better? If you upload a function to lambda service and all lambda does is evaluate this function can you save time and return, like, stored response? What if you already did response for same or similar enough request? What if the old response is still good? Isn't caching what [REST](https://en.wikipedia.org/wiki/Representational_state_transfer) strives for? Wait a sec. Aren't function and caching already connected by something? Yes, [memoization](https://en.wikipedia.org/wiki/Memoization)! Amazon named their service lambda as obvious reference to [functional programming](https://en.wikipedia.org/wiki/Functional_programming).

Functional paradigm, right? Who would thought. After all, it's just a strange idea surrounded by parentheses.
A program in [lisp](https://en.wikipedia.org/wiki/Lisp_(programming_language)) looks like creation of a madman or horny teenager who gets aroused just by looking at (). Nobody would use it, wouldn't they? Oh, just Ericsson. The Ericsson. They brag they do run a service with almost 100% uptime (no stops even for updates) written in functional programming language named [erlang](https://en.wikipedia.org/wiki/Erlang_(programming_language)).

Moreover, you probably know that first non assebler language is fortran but did you know lisp is second? I find suprising lisp is not as exotic as I thought. All languages originate from models of computations devised to assess what computers can actually compute. There are three main models:
* [Turin machine](https://en.wikipedia.org/wiki/Turing_machine)
* [Von Neumann architecture](https://en.wikipedia.org/wiki/Von_Neumann_architecture)
* [Alonzo Church's lambda calculus](https://en.wikipedia.org/wiki/Lambda_calculus)

CPUs are designed according to Von Neumann architecture. So does most programming languages. You could argue the architecture is the reason for prevelance of [imperative paradigm](https://en.wikipedia.org/wiki/Imperative_programming). But functional stemming from lambda calculus ain't dead yet. Quite the contrary, functional paradigm gains popularity with advent of [clojure](https://en.wikipedia.org/wiki/Closure_(computer_programming)) and [rust](https://en.wikipedia.org/wiki/Rust_(programming_language)). The reason for this universal change in paradigm is inability to pursue [Moore's law](https://en.wikipedia.org/wiki/Moore%27s_law) with increasing clock frequency. Instead everyone goes multicore. Unfortunately, multithreading stumbles over *state of execution*. Each thread has its own. If two threads are in incompatible states the result of their cooperation might make no sense or be totaly wrong.

Functional languages do not share this weakness since they do not list steps to do but instead declare what should be done. What in imperative paradigm is necessary for threads to cooperate with each other (locks, monitors, atomics) in [declarative paradigm](https://en.wikipedia.org/wiki/Declarative_programming) has way better alternatives. You can always defer execution until resource is available. Just superpose some functions and put the result compound function into a queue. And function superposition is way easier where functions are [first class](https://en.wikipedia.org/wiki/First-class_citizen) citizens. You can see attempts to achieve similar functionality in Java 8 with [stream api](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html) and lambdas.

## package managers

How you should understand immutability in practice and how does it apply to package manager? First, there are no variables. *Variable* means liable to change. Immutable means unable to change. Instead, for instance in [clojure](https://www.braveclojure.com/do-things/), you assign some value to a name. You build next value from already assigned to a name thus getting rid of state altogether. This might seem terribly inefficient when you work with huge vectors but there are good enough [ways around the problem][persistent-vector]. Nothing beats efficiency of best mutable solution in single-thread application. In multithread one incompatible states usually ruin whole endeavour so if we can avoid them by using extra memory, we are more then willing to do so.

Second, package manager is to add new software atop already installed. Here we are in remarkably similar situation to the above. We assume the dependencies are installed and in exactly the state we expect them to be. The last part might be a little vague I admit, however, that is part of the problem and why we want to ditch the state. Let's assume dependencies are immutable. With that we can reliably add new software.

Mutable package managers like `pacman` or `apt-get` keep local cache of downloaded packages. User may install, uninstall or reinstall any cached package. As time passes the cache grows so admin/user must clean it regularly, what means to remove old, unused packages. Every installation changes the content of file system (`/`). To downgrade (install older version) or upgrade a package the previous one must be removed. Only after that a chosen one can be installed. These operations alter state of file system.

Since a package might have some preinstall or postinstall scripts a maintainer should always have in back of their mind a warning to keep both these scripts compatible across versions. Afterall, it could be the postinstall script of newer version could leave a state where the preinstall of previous version fails. And how are you going to downgrade than? Moreover, how would you test different versions of same software? These can overlap. There is no place to have a file in both versions with same path but different content. What we would like to be able to do is to use different versions of package simultaneously, run one version and remove another and solve preinstall-postinstall issue.

Before we get to that it might be beneficial to contemplate the use of some object (a variable, a function or a structure whatever is available) in functional language. Take for instance operation on a vector (think dynamic array - in clojure its called vector). You add an element to the vector and receive a new vector. The new version can be used beside the previous one. Not just used but alter. Notice that there is not a single point of collision. The vector implementation makes instances [share data][persistent-vector]. Something possible only since the data is immutable. Data is dropped from memory when no one points to it anymore. That goes with all data structures. Now let's replace vector/data structure with a package. Aren't these properties exactly what we long for?

How should we modify a mutable package manager to get a funtional, immutable one that has these properties? First, how to use different versions simultaneously? We have to make sure these two do not share resources. Probably there could be hundreds of resource types but it should be ok for now to narrow to just two: path and port. Each version must be installed into its own path. I did write the installation is basically extraction of prepared archive into root directory (`/`) of file system. You must know the POSIX directory structure (bin, etc, lib, share) can be found not just at `/` but in `/usr` and `/usr/local` too. So it shouldn't be hard to prepare an archive with all these POSIX directories put in some *PREFIX* instead of */*. All it actually entails is to add flag `--prefix=PREFIX` to `configure` script. What about port? If the program uses a socket for communication, then it usually allows for port number choice in configuration.

Second, how to run one version and install or remove another. The installation itself we discussed above. The tricky part is that to run the installed program it must be available in one of directories listed in *PATH* enviroment variable (every console has variable for this purpose). We guaranteed each version has its own prefix so we could use absolute path to call them. Otherwise, we would have to make a *bin* directory that would both be enlisted in *PATH* and contain a symlink to the each version. Thus by choosing different names for executables links we can call both versions in a convenient way.

Third, what about preinstall-postinstall issue? Since each version has its own root directory the issue does not apply.

Lastly, another similarity to functional paradigm emerges. If we keep the *bin* directory just for symlinks, then by removing a symlink we sort of uninstall the software. User still can call it by absolute path, however, it is safe to assume what is missing in the *bin* is not installed. That way we can have many *bins*, for each user for instance, and remove the whole unique prefix directory only when none points to its content. Just like a functional language stores data in memory.

## nix

Functional package manager looks like an idea worth trying. I could both build from source on target platform and distribute compiled software in form of a package. Package managers are designed for this sort of a thing so they come with means of setting up a repository. I could benefit from that too. But is there any such program or are these plans purely academic?

Fortunately no. The functional package manager I found and which made me write the whole post is [nix](https://nixos.org/nix/). Due to hardly any popularity the manager feels more like an experiment then legit tool. It is product of [phd thesis](https://nixos.org/~eelco/pubs/phd-thesis.pdf) by Eelco Dolstra. You can download nix from [official](https://nixos.org/nix/download.html) site, however, to understand the manager it is better to build it yourself from sources at [github](https://github.com/NixOS/nix).

Although the package manager has autoconf build included like most other open source programs this kind does not operate like a regular software. You cannot just use it on its own. Alone it is mostly useless. You must configure some remote (or even local) repository (in nix terms a channel) for the start. There is one official repository called [nixpkgs](https://nixos.org/nixpkgs/), however, I discourage you from using it when experimenting with examples I give you. The pair of package manager and repository is basically cornerstone of a distribution. All revolve around these. One could argue all orbit the kernel but if you can upgrade the kernel itself with a manager then who is really in charge; who is the boss? Pair of nix with nixpkgs establishes another linux distribution called [nixos](https://nixos.org/). If you install nix with default repository either manually or from its home page, you get yourself into strange situation with two systems within one. For instance let's say you have got *automake* from both nix and your system's package manager. You installed make twice. Two separate instances in different directories. And for what? Redundancy? That is the reason why I advise to cut off the default and configure your own repository instead. And to how achieve this goal we will eventually get.

From now on I assume you got sources from github, built and installed so you can use nix. It is worth to write a few words on how to do so. I worked with version from 7th of July. It's nice because some perl's modules are no longer on list of requirements; less to prepare. Unfortunately, there is some issue with building documentation. I had to disable it by commenting out `doc/manual/local.mk` in main *Makefile*. Another thing, you have to create `/nix` directory you can write into. Nix assumes the directory exists and is going to heavily utilize. I believe you can change the path by appending some flag to `configure` but why not create one? It is way simpler that way. The last thing is to prepare your `bash` session so you could call easily (without the need for absolute path) installed executables. You do that by executiong `PREFIX/etc/profile.d/nix.sh` (where *PREFIX* is wherever you installed nix). The [script](https://nixos.org/nix/manual/#ch-env-variables) should create `.nix-channels`, `.nix-defexpr` and `.nix-profile` in your home directory. On their purpose later. Last thing is to add `~/.nix-profile/bin` to *PATH* in `~/.bashrc`.

As package managers operate on packages nix operates on derivations. I wouldn't be suprised to hear the name originated as a pun to the functional paradigm itself. If you look for a serious explanation, you can think of installing your dreamed operating system as an arythmetic operation; precisely as a summation of each and every derivative/package. And we all know any sum is an integral so your system is integral of derivative over subrepository (or channel for nix). Well, at least I tried to be serious you have to give me that.

I like the term derivation applied to package manager. Derivation underlines role of a package as a minor difference yet a part of a big stream. It gives a sense of continuity (can we name anything *topology*? :P). Even though the change might be small you can achieve a lot because you can assemble lots of other derivations.

Derivation consists of attributes

* name - there is no separate attribute for version. It is maintainer's responsibility to embed the version into the name.
* system - a tag name for architecture, i.e. *x86_64-linux*.
* builder - the path to the executable responsible for building the software.

You may wonder what about dependencies? Shouldn't there be special attribute that takes a list of required derivatives? The case is nix treats all information as dependency. Afterall, you depend on the name, system and builder. These are necessary, aren't they? So any attribute is dependency. And you can add any other one with any allowed content: string, number, path or another derivation.

What is special about nix is what it does with path and derivation values assigned to an attribute. For paths it copies the file into *store*. For derivation it builds them. The *store* is directory (`/nix/store` by default) where nix puts all dependencies, derivations and built software. I will go back to what to build means in a sec.

Putting into *store* is very important for nix. A functional package manager assumes the arguments are immutable. By storing dependencies nix guarantees the arguments (dependencies) are immutable. Than you can call the builder anytime in the future and assume the end result is always the same. As a result, calling the builder with given dependencies is similar to running a function with arguments in a functional language.

But before we call builder we might want just to save derivation - you know, for later. There may be thousands of derivations and we don't want to build all of them. This saving is called *instantiation* and the nix command for this purpose is `nix-instantiate`.

Take for instance simple derivation for our *hello world*.

```nix
derivation {
  system = "x86_64-linux";
  name = "hello-1.0";
  builder = ./hello.sh;
  source = ./hello-1.0.tar.gz;
}
```

Clean and simple, right? When you instantiate it, source and builder ends up in store. So does derivation. The contents of store is now

```bash
$ ls /nix/store/
jxx63pywg4wg7lx0nnl76wh8w5w4b48m-hello.sh
rjbch4xh146klwh6ppaiaqq18lkqxiqm-hello-1.0.drv
zdv14ngkgwx43syqw0wp42arrjqp7ql4-hello-1.0.tar.gz
```

Fine but what is this random gibberish in each file's name? This is [hash](https://github.com/nixkoans/nixkoans/tree/master/14_nixstorepaths) that uniquely (in practice; in theory with probability practically one) identify the resource and the store. The resource is the file's name and content. The store is path to our *store*. Since nix depends so heavily on hashes it comes with `nix-hash` capable of hashing a file or a directory. The store path where a file pointed by the path in derivation is placed is created in three steps. Let's demonstrate how store path for `hello.sh` comes to be.

* hash the source
```bash
$ nix-hash --type sha256 hello.sh
a66a2f816dd19d67ec8d8639d6f23da99659af251f2c8c790dd6bb110e9835f2
```

* create the string that identifies both source and store
```bash
$ echo -n source:sha256:`nix-hash --type sha256 hello.sh`:/nix/store:hello.sh > identifier
```

* hash the identifier
```bash
$ nix-hash --type sha256 --truncate --base32 --flat identifier
jxx63pywg4wg7lx0nnl76wh8w5w4b48m
```

The flags used in last step meaning is to truncate the hash to 160 bits, present it in base32 encoding and hash the content of the file not the dump. The dump is an archive nix does when working with directories. The archive format is called *nar* (nix archive). It is like *tar* but for nix internal purposes. The flag `--flat` tells `nix-hash` to do a regular hash just like `sha256sum` would do (regular means don't hash the dump).

Beside sources, `hello.sh` and `hello-1.0.tar.gz`, there is also the file `hello-1.0.drv`. This is derivation saved in computer frendly format. It is listing sources, derivations and other attributes. With all that I would like you to notice that the *hello* has not been built yet. So far we have only instantiated the derivation. If you want to build it, you must *realise* the derivation what means to call `nix-store -r /nix/store/rjbch4xh146klwh6ppaiaqq18lkqxiqm-hello-1.0.drv` where `-r` is synonim for `--realise`.

In your console you should see autoconf building the program. Once finished you should receive the store path where the program has been just installed. Since the build process gives (at least we assume it does) the same result every time I can tell full path you need to call to execute *hello*.

```bash
$ /nix/store/1zcyn4006vvi6lsiz0bn8pq353wd92rj-hello-1.0/bin/hello
hello world!
```

Ready, right? Yet anyone would prefer to type in just `hello` not the whole absolute path. Remember my previous speculation that a directory for symlink would do? `nix-env` does just that.

```bash
$ nix-env -i /nix/store/1zcyn4006vvi6lsiz0bn8pq353wd92rj-hello-1.0
installing ‘hello-1.0’
building path(s) ‘/nix/store/0p85gmxsq8asm5pn451w9ak7ybdb9g67-user-environment’
created 1 symlinks in user environment
```

Nix has created the directory and called it user enviroment (with hash prefix as usual). This directory serves as root for all installed software. Inside you can see symlinks or directories of symlinks. You can just add the user-environment/bin to your *PATH* to get ability to call `hello`. However, when you install another derivation, nix creates another user enviroment so your *PATH* is no longer valid. As a result, setting variable you end up constantly updating it. We do not like it. Instead nix creates something called profile that points to the enviroment. Each time the new enviroment is created new [profile](https://nixos.org/nix/manual/#sec-profiles) is made. For each user there is symlink `~/.nix-profile` pointing to current profile. With `nix-env` you can jump from one profile to the other easily. Read [more](https://nixos.org/nix/manual/#sec-nix-env) on listing and changing generations.

You may wonder why does `nix-env` takes path to build software. However, you should not be surprised. Afterall, whatever is in store might come from all over the place in all sort of manners. You can prepare a set of derivations in nix language like this (`all.nix`)

```nix
{
  hello = import ./hello.nix;
}
```

where `hello.nix` is derivation presented before. List all derivations available in the set

```bash
$ nix-env -f all.nix -qa
hello-1.0
```

and install `nix-env -f all.nix -i hello`. You can simplify installation even more by placing `hello.nix` into `~/.nix-defexpr` directory what allows for `nix-env -i hello`. Such sets of derivations are published. For instance nixpkgs is one. Nix is designed to synchronize with such set remotely. We call this [channel](https://nixos.org/nix/manual/#sec-channels).

What is really cool is that switching profiles is (according to nix's documentation) atomic since creating symbolic link is. Thus we arrive to a very strange conclusion that installation is almost instantaneous. Jumping from one version to another couldn't be easier. Instantiation should be fast too. The only heavy operation is building (realisation of a derivation). And we can avoid this step by copying the already build derivation from binary cache. The cache contains archived dumped `*.nar` and meta info `*.narinfo`. We can create and use a binary cache right now.

```bash
$ nix copy --to file://cache /nix/store/1zcyn4006vvi6lsiz0bn8pq353wd92rj-hello-1.0
$ ls cache
1zcyn4006vvi6lsiz0bn8pq353wd92rj.narinfo  nar  nix-cache-info
$ ls cache/nar
1l626p6rv302qnclhj22b4cl292rhyiqqj6ji3ywz76sdi87cc1n.nar.xz
```

Let's install from the created binary cache. Just a word on this test. I suggest to clear `/nix` before so that you could see yourself what is stored in the *store*. You clear the store and initialize your profile with

```bash
$ chmod u+w /nix/ -R
$ rm /nix/* -rf
$ bash prefix/etc/profile.d/nix.sh
```

and install

```bash
$ nix-store --option binary-caches file://cache --option signed-binary-caches '' -r /nix/store/1zcyn4006vvi6lsiz0bn8pq353wd92rj-hello-1.0
these paths will be fetched (0.01 MiB download, 0.03 MiB unpacked):
  /nix/store/1zcyn4006vvi6lsiz0bn8pq353wd92rj-hello-1.0
fetching path ‘/nix/store/1zcyn4006vvi6lsiz0bn8pq353wd92rj-hello-1.0’...
/nix/store/1zcyn4006vvi6lsiz0bn8pq353wd92rj-hello-1.0
$ ls /nix/store/
1zcyn4006vvi6lsiz0bn8pq353wd92rj-hello-1.0
```

So how does nix compare to other package managers? They download packages from repositories, nix does from binary cache. A package is built from source according to definition like *PKBGUILD*. Nix realises derivation but to guarantee the process ends up with same result all sources are put into the store as the result of instantiation. Nix puts sources in prominent position while others hide them behind creation of a package. `pacman` for instance comes with separate program just for package creation. On the other hand, nix can create binary cache, however, it serves mostly as a convenience while for others the binary cache (they called it repository) is the goto destination to have any software installed.

We saw `makepkg` uses bash as scripting language for *PKGBUILD*. Nix delivers its own functional language. Examples like `all.nix` and `hello.nix` have been already presented. The point of the language is to present one or a set of derivations. Nix inhales them (`nix-env -f *.nix`), calculates hashes so that you can refer to derivation by name (`nix-env -f *.nix -i hello`) and nix uses corresponding path. In order to write your own scripts it is worth to read more on the language in [official documentation](https://nixos.org/nix/manual/#ch-expression-language).

## summary

Each enterprise streamlines software delivery to make as automatic and error-proof as possible. That entails there is somewhere a service that downloads the software every time the source is altered. The service builds and tests. Once all tests passes, the artifact is stored. You can configure the build to create a package as artifact. I would like to play with nix and make binary builds my artifacts and place them into the binary cache. That way I can give my customers access to the cache via apache or any other http server thus solving most difficulties inherent to delivery. As for the service I could use [hydra](https://nixos.org/hydra/) another project related to nix. I like the functional flavor all nix related project have. Since all are open source I could work out any bugs the customers may throw at me. All this excitement make hum [I'm useless but not for long, The future is coming on, It's coming on](https://www.youtube.com/watch?v=1V_xRb0x9aw).

[persistent-vector]: http://hypirion.com/musings/understanding-persistent-vector-pt-1
