# java-contest-template

This repository contains code I've used in the past to participate in programming competitions using Java.

## Motivation

As Java can be quite slow for some important things, like I/O, especially when comparing to languages such as C and C++ (usually the favourites for those competitions), I devised my own set of methods to overcome those problems, and reuse during competitions.

This code evolved over the years, as I've learned new tricks, understood better how Java works under the hood, and worked on more and more in new problems, adding new helper methods and making fixes to existing code.

_Javadocs_ were added for better clarification on the goals of some classes and methods.

## Notes and design goals

An important thing to note here is that all the classes and methods included here were not designed to be state-of-the-art software engineering, or object oriented artifacts.
Instead, they were designed for use in programming competitions.
The design goals for this project include:
+ Run fast, really, really fast;
+ be minimalist (do one thing, quick and well);
+ be easy to tweak (if a specific condition needs to be met in some new use);
+ be easy to copy-paste into new code, or read-write from a paper, for a programming competition;
+ be intelligible, so that others can reason and understand what the code is supposed to do - avoid cryptic function names.
