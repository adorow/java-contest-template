# java-contest-template

This repository contains code I've used in the past to participate in programming competitions using Java.

## Motivation

It all started when I was practicing for some programming competitions, on platforms like SPOJ, where I noticed that many if my Java solutions would get _Time Limit Exceeded_ results. The problem was that my logic was not all that bad, and when I would translate the Java code into C or C++ code, they ran very, very easily on the platforms. That's when I noticed that the problem was with the I/O code I was using from Java. Using high level APIs was way too slow (in some tests I made at the time, just reading the full input and writing a random output would consume the whole time, plus loading the JVM seemed to take quite some time, as even empty submissions would take almost a second to run), and using lower level APIs meant I needed to write a lot of code to handle parsing and so on - and that's what I did. And I kept adding more and more functionality.

Java can be quite slow for some important things like I/O, especially when comparing to languages such as C and C++ (usually the crowd favourites for programming competitions).
And also, sometimes I just needed to reduce to amount of work I had (a lot of patterns arised on writing the solutions on these competitions).

In this repository you will find code that I devised to overcome those problems I found during competitions, and that I could reuse in future occasions. This code evolved over the years, as I've learned new tricks, understood better how Java works under the hood, and worked on more and more in new problems, adding new helper methods and making fixes to existing code.

_Javadocs_ were added to the code you see here, for better clarification on the goals of some classes and methods.
The code found here was also separated into specific classes, so that they can more easily be understood.
In actual programming competitions what I would usually do was to actually copy-paste all the utilitary methods into the main class running the program.

## Notes and design goals

An important thing to note here is that all the classes and methods included here were not designed to be state-of-the-art software engineering, or object oriented artifacts.
Instead, they were designed for use in programming competitions.
The design goals for this project include:
+ Run fast, really, really fast, when it matters;
+ be minimalist (do one thing, quick and well);
+ be easy to tweak (if a specific condition needs to be met in some new use);
+ be easy to copy-paste into new code, or read-write from a paper, for a programming competition;
+ be intelligible, so that others can reason and understand what the code is supposed to do - avoid cryptic function names;
+ use only standard Java libraries (in most competitions that's all we're allowed to use);
+ also, simply avoid doing the same thing twice.
