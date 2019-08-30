# FEB22012 Programming Code Quality Guide

## Introduction

This document contains the code quality guide that we use during the FEB22012(X) Programming course. 

## Mandatory Rules

A number of rules are mandatory in the sense that we do not award any points for code quality and
style if these are violated. This is only a small set of rules you have to adhere to. 
Most of these rules are all related to writing proper Javadoc comments, but there are three other
rules: a `.java` file should contain a single top-level class, you should refrain from using 
mutable `public` instance variables and if you override either `hashCode()` or `equals()`, you
should also override the other.

Relevant Rules regarding Javadoc:

* [InvalidJavadocPosition](https://checkstyle.sourceforge.io/config_javadoc.html#InvalidJavadocPosition)
* [JavadocMethod](https://checkstyle.sourceforge.io/config_javadoc.html#JavadocMethod)
* [JavadocType](https://checkstyle.sourceforge.io/config_javadoc.html#JavadocType)
* [MissingJavadocMethod](https://checkstyle.sourceforge.io/config_javadoc.html#MissingJavadocMethod)
* [MissingJavadocType](https://checkstyle.sourceforge.io/config_javadoc.html#MissingJavadocType)
* [NonEmptyAtclauseDescription](https://checkstyle.sourceforge.io/config_javadoc.html#NonEmptyAtclauseDescription)
* [VisibilityModifier](https://checkstyle.sourceforge.io/config_design.html#VisibilityModifier)

Relevant Rules regarding general programming:

* [EqualsHashCode](https://checkstyle.sourceforge.io/config_coding.html#EqualsHashCode)
* [OneTopLevelClass](https://checkstyle.sourceforge.io/config_design.html#OneTopLevelClass)
* [MissingOverride](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_bestpractices.html#missingoverride)


### Proper Javadoc Comments

You should write Javadoc comments to document public *classes* and *methods*. It is not allowed
to write Javadoc comments in other locations. Regular comments are allowed. The following code
fragment contains some examples:

```{.java}
  @Override
  /**
   * This JavaDoc comment should be before the @Override annotation, not here.
   */
   public int compare(MyClass o1, MyClass o2) {
      /**
      * Javadoc comments should never be written inside methods!
      */ 
      // Regular single line comments are allowed anywhere
      /* Multi-line regular comments
       * are also allowed anywhere
      */
      return o1.getValue() - o2.getValue();
   }
}
```

Your classes should always contain Javadoc with a `@author` tag that starts with your ERNA-id.
For example, a correct (but too short) class Javadoc could look as follows:

```{.java}
/**
 * This class does some very interesting stuff.
 *
 * @author 123456ae Adrian Example
 */
public class MyClass {

}
```

Finally, you should use tags such as `@param` and `@return` in your Javadoc comments to explain the meaning of parameters and 
returned values of your methods and constructors. If you define a tag in a Javadoc comment, you are not allowed to leave the
explanation empty.

### Make instance variables private

A well designed class should not burden its users with details of its implementation.
You have probably worked with objects of the String class, but you do not necessarily
know all the details of how it is implemented by the programmers of the Java language.
A common way to do this, it to make sure all interactions with objects of a class are
performed using methods of that class, while the internal data model (i.e. the instance
variables) of the objects are hidden from the users. This referred to as [information hiding](https://en.wikipedia.org/wiki/Information_hiding)
or [encapsulation](https://en.wikipedia.org/wiki/Encapsulation_(computer_programming)).

As a consequence, you should define all instance variables or class members as `private`.

**Note :* in certain exercises, this requirement may be relaxed to some extent.
If so, this is mentioned in the text of the exercise.

### Single Top Level class

You are only allowed to have a single top-level class in a `.java` file. The following
code fragment gives an example of a file with two top-level classes.

```{.java}
public class Foo{
  //methods
}

class Foo2{
  //methods
}
```

If you want to have more than a single class in a file, you can consider to create
*static inner classes*. This can be done as follows:

```{.java}
public class Foo{
  //methods
  
  static class Foo2{
  //methods
  }

}
```

### Override both hashCode and equals

This rule becomes relevant when you are introduce to inheritance and the `Object` class;
it is not likely you will violate it before you understand these concepts. It basically
means that as soon as a class override either the `hashCode()` or `equals()` method,
it should also override the other one. It is almost always incorrect to override only
one of them according to the contracts of these methods.

### Override annotation is mandatory

When you learn how to implement an interface or extend a class, you may want to override
methods. When you override a method, you should always use the `@Override` annotation,
since it avoids that you accidentally introduce a new method while you intend to
override something. For example, you want to override a method `doAnalysis()` but
accidentally call the method in the subclass `analysis()`. With the @Override annotation,
the compiler will warn you that you made a mistake, that is usually hard to find by
debugging.

Annotating overridden methods with @Override ensures at compile time that
            the method really overrides one, which helps refactoring and clarifies intent. For example:

```{.java}
public class Foo implements Comparable<Foo> {
                // This method is overridden, and should have an @Override annotation
                public int compareTo(Foo other) {

                }
            }
```

## Important Rules: Error Prone Coding

The second set of rules are *error prone* coding rules. If these issues are detected in your
code, it will have a strong impact on your score for code quality. While it is technically
possible to use violate these rules in correct code, in almost all cases these things are
mistakes, or can quickly lead to mistakes if you need to change your code at a later moment.
Therefore, you should avoid doing these things.

Relevant rules:

* [AvoidNestedBlocks](https://checkstyle.sourceforge.io/config_blocks.html#AvoidNestedBlocks)
* [EmptyCatchBlock](https://checkstyle.sourceforge.io/config_blocks.html#EmptyCatchBlock)
* [NeedBraces](https://checkstyle.sourceforge.io/config_blocks.html#NeedBraces)
* [HiddenField](https://checkstyle.sourceforge.io/config_coding.html#HiddenField)
* [InnerAssignment](https://checkstyle.sourceforge.io/config_coding.html#InnerAssignment)
* [SimplifyBooleanExpression](https://checkstyle.sourceforge.io/config_coding.html#SimplifyBooleanExpression)
* [SimplifyBooleanReturn](https://checkstyle.sourceforge.io/config_coding.html#SimplifyBooleanReturn)

### Avoid Nested Blocks

In Java, you are allowed to define loose blocks, that have their own scope. The following
code fragment contains a loose block:

```{.java}
public void printSomething() {
   int x = 3;
   {
      // This is a loose block, which you should avoid.
     int x = 5;
   }
   System.out.println("The number is "+x);
   
   // This block belong to an-if statement, and is allowed.
   if (x < 5) {
      System.out.println("Smaller than 3");
   }
   
   // The accidental semi-colon seperates the if statement
   // from the block. This is the type of mistake we like
   // to avoid.
   if (x==5); // notice the semi-colon!!
   {
      // Oops! This part is always executed, not matter
     // what the value of x is.
     System.out.println("x seems to be five");
   }
}
```

### Avoid Empty Catch Blocks

In Java, empty `catch` blocks are considered bad practice since they suppress mistakes,
while the user of your program may want to know that something went wrong. You should
never catch an Exception if you have no good way to deal with. At the very least, you
should print a stacktracte using the `e.printStackTrace()` on the `Exception` object
`e`. Alternatively, you can throw the exception to a caller. Consider the following
code fragments:

```{.java}
public void readFileInBadWay(File input) {
   try {
     List<String> allLines = Files.readAllLines(input.toPath());
   }
   // The following catch-block is empty; you should avoid this.
   catch (IOException ex) {
     
   }
}

public void readFileInBetterWay(File input) {
   try {
     List<String> allLines = Files.readAllLines(input.toPath());
   }
   // The following catch-block at least gives a message to the user.
   // This is allowed
   catch (IOException ex) {
     System.out.println("Error reading file "+input);
    ex.printStackTrace();
   }
}

// Since our caller should provide an input file that we can read properly,
// in this case the best design is to let the caller of this method deal
// with the IOException if the file can not be read.
public void readFileInBestWay(File input) throws IOException {
   List<String> allLines = Files.readAllLines(input.toPath());
}
```

### Control-structures need braces.

The Java language allows you to write a single statement after control structures
such as `if`, `for` and `while`. That means no block is defined using `{` and `}`,
and only a single statement is affected by the control structure. Since this is
often error prone, this should be avoided. The following code fragments contains
some examples:

```{.java}
for (int x=0; x < 10; x++)
   System.out.println("The number is "+x);

if (x>3)
   System.out.println("Greater than three");
   
while (x<10)
   System.out.println("The number is "+x);
   // The following line of code is executed only after the loop finishes!
   // This is a type of mistake we prefer to avoid!
   x++;
```

The correct version of the above code fragment looks as follows:

```{.java}
for (int x=0; x < 10; x++)
{
   System.out.println("The number is "+x);
}

if (x>3)
{
   System.out.println("Greater than three");
}
   
while (x<10)
{
   System.out.println("The number is "+x);
   // Now this is executed in every iteration of the loop.
   x++;
}
```

### Avoid shadowing variables

A variable can have a different scope, depending on the location is where it is defined.
Variables defined within the class are instance variables and are part of objects,
while variables defined as parameters of method or constructors only live while that method
or constructor is executed. We should avoid using the same name at different levels within
the same class if this causes potential confusing, as the class level variable is hidden/shadowed.
The following fragment contains an example of instance variable shadowing:

```{.java}
public class MyClass {

   private int someNumber;
   private int anotherNumber;
   
   public MyClass(int someNumber, int anotherNumber) {
      // This assigns a new value to the parameter, but not to the instance variable!!
     // This is likely a mistake
     someNumber = anotherNumber + 5;
     anotherNumber = someNumber + 3;
   }
}
```

The constructor mentioned can be written as follows:

```{.java}
   public MyClass(int firstNumber, int secondNumber) {
     someNumber = secondNumber + 5;
     anotherNumber = firstNumber + 3;
   }
```

Alternatively, the following is allowed as well:

```{.java}
   public MyClass(int someNumber, int anotherNumber) {
     // With the explicit this reference, the can be no confusion!
     this.someNumber = anotherNumber + 5;
     this.anotherNumber = someNumber + 3;
   }
```

### Refrain from using inner assignments

In programs we often want to update the value of variable. We should always do this using
an assignment statement, for example `x = 5;`, `x++;` or `x -= 2;`. However, in the syntax of
Java assignments are also considered expressions. Thus something like `x = (y = 5) + 3;` is
also valid, and assigns the value `5` to variable `y` and the value `8` to variable `x`.
These constructs are error-prone, and often lead to mistakes, in particular when accidentally
used in an `if` or `while` construction. For example, the following code fragment never
terminates:

```{.java}
boolean doSomeMore = true;
// The assignment in the condition always sets doSomeMore to true, and thus the condition
// is always true and the loop never terminates!
while (doSomeMore = true) {
   doSomething();
   checkSomething();
}
```

### Simplify Boolean Expressions 

It is helpful to keep the condition `if` statement, or a method with a `boolean`
return value simple. This means that if use a `boolean` expression in those contexts,
we should avoid comparing this expression against `true` or `false`.

```{.java}
boolean checkValue = checkSomething();
// The comparison is not necessary!
if (checkValue == true) {
   // do something
}

// This can be simplified to
if (checkValue) {
   // do something
}
```

Something similar holds for methods that return a boolean value. Since we can return
arbitrary restrictions, we can return boolean expressions rather than unnecessary
`if` and `else` constructs, as the following code fragment illlustrates.

```{.java}
boolean checkValue = checkSomething();
if (checkValue == false) {
   return true;
}
else {
   return false;
}

// The above fragment can be simplified to a single line:
return !checkSomething();
```

## Indications of sloppy code

These rules are not necessarily error prone, but they look sloppy or are
distracting to other programmers when they want to read your code. It
makes code more difficult to understand, and therefore more difficult
to spot bugs in the code.

Relevant rules:

* [EmptyBlock](https://checkstyle.sourceforge.io/config_blocks.html#EmptyBlock)
* [EmptyStatement](https://checkstyle.sourceforge.io/config_coding.html#EmptyStatement)
* [IllegalThrows](https://checkstyle.sourceforge.io/config_coding.html#IllegalThrows)
* [IllegalCatch](https://checkstyle.sourceforge.io/config_coding.html#IllegalCatch)
* [IllegalType](https://checkstyle.sourceforge.io/config_coding.html#IllegalType)
* [UnusedImports](https://checkstyle.sourceforge.io/config_imports.html#UnusedImports)
* [TodoComment](https://checkstyle.sourceforge.io/config_misc.html#TodoComment)
* [UncommentedMain](https://checkstyle.sourceforge.io/config_misc.html#UncommentedMain)

### Avoid Empty Blocks

Empty blocks are typically an indication the structure of your program can be improved or
simplified. They distract from the actual logic of your program. Therefore, you should avoid
these. Often, empty blocks that may seem *logical* can be avoided by rewriting `boolean`
expressions. For example. the following pattern:

```{.java}
int x = computeX();
int y = computeY();
// The first block is empty
if (x > 5 && y < 25 && x > y) {
}
else {
   doSomething();
}
```

can be rewritten to the more consise and less error prone:

```{.java}
int x = computeX();
int y = computeY();
// We can use a negation to remove the empty block
if (!(x > 5 && y < 25 && x > y)) {
   doSomething();
}
```

Note that empty methods or constructors are allowed, as these are sometimes
necessary or useful and can not always be avoided.

### Avoid Empty Statements

Empty statement do nothing, and are therefore clutter in your code. They
are causes by having unnecessary semi-colons and should be avoided. 
The following code fragment contains an example:

```{.java}
// Note the double semi-colon that results in an empty statement.
int b = 3+5;;
```

### Avoid catching or throwing exception types that are too general

When you catch an exception, you should catch an exception that is specific
enough. Exception types that are too general, should not be could. Therefore,
it is not allowed to catch exception of type `Error`, `Exception`, `Throwable`
or `RuntimeException`. Similar, you should not define method headers that
mention one of these exception types after the `throws` keyword. If you catch
exceptions that are too general, there is a risk that you catch exceptions
you did not expect: this can make debugging a lot more complicated (in particular
if the exception is ignored). If your method throws exceptions of a type that
is too general, it is highly likely the users of that method do not have enough
information about the potential causes of that exception, and what they can do
to avoid or handle those exceptions.

### Collections API: use the interfaces as types

When you start working with the Collections API, you should declare variables
of the various Collections types by their interface type, rather than use
explicit class types. So, `List`, `Set`, `Map`, `SortedSet` and `SortedMap`
are preferred over `ArrayList`, `HashSet`, `HashMap`, etc. The reason is that
it should be easy to change the implementation of your data structure. The
following code-fragment contains variables that are not defined by the correct
interface type:

```{.java}
public class MyClass {
   
   // This is not allowed
   private ArrayList<String> lines;

   public MyClass() {
      // It is always allowed (and in fact necessary) to call the constructor
     // of concrete Collections API types.
      this.lines = new ArrayList<>();
   }
   
   public void doSomething() {
      // The type of this variable is also not an interface type
      TreeSet<String> mySet = new TreeSet<>();
   }
}
```

To fix this example, it should be as follows:

```{.java}
public class MyClass {
   
   // The correct interface type is List
   private List<String> lines;

   public MyClass() {
      // It is always allowed (and in fact necessary) to call the constructor
     // of concrete Collections API types.
      this.lines = new ArrayList<>();
   }
   
   public void doSomething() {
      // For a TreeSet, we can use either Set or SortedSet as the interface type.
      SortedSet<String> mySet = new TreeSet<>();
   }
}
```

### Avoid defining unused imports

Having import statements that are never used result in warnings from Eclipse and the
Java compiler. This is sloppy, useless code, which we should remove from a `.java`
file. In Eclipse it is possible to use a feature called *Organize imports* (under the
*Source* menu, or with ctrl+shift+O) that automatically removes any unused imports
from a file.

### Remove TODO and auto-generated comments

If your comments contains `TODO:` or `generated method stub`, this indicates that
your code is not neatly finished. Thus, this is not allowed in your final submission.

### Only define a main method in classes that have a name ending with Main

Often it is useful to define `main` methods in classes to test whether they work
as expected or not. In your final code, however, it is best to litter classes with
testing code that is irrelevant to the user of that class. Therefore, only classes
that are designed to contain a `main` method to be used by the end-user should
contain a `main` method. These classes should have a name that ends with `Main`.

Note that in the assignments of FEB22012 Programming, you are typically not required
to hand in your own `main` method, unless this is stated otherwise.
