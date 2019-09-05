# FEB22012 Programming Code Quality Guide

## Introduction

This document contains the code quality guide that we use during the FEB22012(X) Programming course. 

## Mandatory Rules

A number of rules are strict in the sense that we do not award any points for code quality and
style if these are violated. This is only a small set of rules you have to adhere to. 
Most of these rules are all related to writing proper Javadoc comments, but there are three other
rules: a `.java` file should contain a single top-level class, you should refrain from using 
mutable `public` instance variables and if you override either `hashCode()` or `equals()`, you
should also override the other.

Relevant links regarding Javadoc rules:

* [InvalidJavadocPosition](https://checkstyle.sourceforge.io/config_javadoc.html#InvalidJavadocPosition)
* [JavadocMethod](https://checkstyle.sourceforge.io/config_javadoc.html#JavadocMethod)
* [JavadocType](https://checkstyle.sourceforge.io/config_javadoc.html#JavadocType)
* [MissingJavadocMethod](https://checkstyle.sourceforge.io/config_javadoc.html#MissingJavadocMethod)
* [MissingJavadocType](https://checkstyle.sourceforge.io/config_javadoc.html#MissingJavadocType)
* [NonEmptyAtclauseDescription](https://checkstyle.sourceforge.io/config_javadoc.html#NonEmptyAtclauseDescription)
* [VisibilityModifier](https://checkstyle.sourceforge.io/config_design.html#VisibilityModifier)

Relevant links regarding general programming rules:

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

**Note :** in certain exercises, this requirement may be relaxed to some extent.
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

```
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

### Use the @Override annotation when overriding methods.

When you implement and interface or create a subclass, you should always indicate which
methods override other methods. Note that if you use Eclipse to generate overriden methods
for you, the annotation is always added. Adding the notation is considered good practice,
because it avoids that you introduce new methods rather than overriding old ones (e.g.
you create a new method `hashcode()` while you intend to override `hashCode()`).

Annotating overridden methods with @Override ensures at compile time that
the method really overrides one, which helps refactoring and clarifies intent.

**Examples:**

```{.java}
public class Foo implements Runnable {

   // This method is overridden, and should have an @Override annotation
   public void run() {

   }
}
```


## High Priority: Error Prone Coding

The second set of rules are *error prone* coding rules. If these issues are detected in your
code, it will have a strong impact on your score for code quality. While it is technically
possible to use violate these rules in correct code, in almost all cases these things are
mistakes, or can quickly lead to mistakes if you need to change your code at a later moment.
Therefore, you should avoid doing these things.

External links to relevant rules:

* [AvoidNestedBlocks](https://checkstyle.sourceforge.io/config_blocks.html#AvoidNestedBlocks)
* [EmptyCatchBlock](https://checkstyle.sourceforge.io/config_blocks.html#EmptyCatchBlock)
* [NeedBraces](https://checkstyle.sourceforge.io/config_blocks.html#NeedBraces)
* [HiddenField](https://checkstyle.sourceforge.io/config_coding.html#HiddenField)
* [InnerAssignment](https://checkstyle.sourceforge.io/config_coding.html#InnerAssignment)
* [SimplifyBooleanExpression](https://checkstyle.sourceforge.io/config_coding.html#SimplifyBooleanExpression)
* [SimplifyBooleanReturn](https://checkstyle.sourceforge.io/config_coding.html#SimplifyBooleanReturn)
* [UseTryWithResources](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_bestpractices.html#usetrywithresources)
* [EqualsNull](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#equalsnull)
* [UnusedPrivateMethod](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_bestpractices.html#unusedprivatemethod)
* [UnconditionalIfStatement](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#unconditionalifstatement)
* [NonStaticInitializer](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#nonstaticinitializer)
* [UselessOperationOnImmutable](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#uselessoperationonimmutable)
* [JumbledIncrementer](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#jumbledincrementer)
* [UnusedPrivateField](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_bestpractices.html#unusedprivatefield)
* [ForLoopShouldBeWhileLoop](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_codestyle.html#forloopshouldbewhileloop)
* [UseEqualsToCompareStrings](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#useequalstocomparestrings)
* [ReturnFromFinallyBlock](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#returnfromfinallyblock)
* [ForLoopCanBeForeach](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_bestpractices.html#forloopcanbeforeach)
* [UnusedLocalVariable](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_bestpractices.html#unusedlocalvariable)
* [UselessOverridingMethod](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_design.html#uselessoverridingmethod)
* [SingularField](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_design.html#singularfield)
* [CloseResource](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#closeresource)
* [ExtendsObject](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_codestyle.html#extendsobject)
* [MisplacedNullCheck](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#misplacednullcheck)
* [BrokenNullCheck](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#brokennullcheck)


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

```
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

### Avoid hiding fields

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

### UseTryWithResources

Java 7 introduced the try-with-resources statement. This statement ensures that each resource is closed at the end
of the statement, which is important to avoid bugs around resources that are not properly closed. It avoids the need
of explicitly closing the resources in a finally block. Additionally exceptions are better handled: If an exception
occurred both in the `try` block and `finally` block, then the exception from the try block was suppressed. With the
`try`-with-resources statement, the exception thrown from the try-block is preserved.

For example, the code 

```{.java}
InputStream in = null;
try {
	in = openInputStream();
	int i = in.read();
} catch (IOException e) {
	e.printStackTrace();
} finally {
	try {
		if (in != null) in.close();
	} catch (IOException ignored) {
		// ignored
	}
}
```

is better written as:

```{.java}
// better use try-with-resources
try (InputStream in2 = openInputStream()) {
	int i = in2.read();
}
```

### EqualsNull

Tests for null should not use the equals() method, as it could result in an `NullPointerException`.
The `==` operator should be used instead.

Rather than writing

```{.java}
String x = "foo";

if (x.equals(null)) {
    doSomething();
}
```

it is better to write

```{.java}
if (x == null) {
    doSomething();
}
```

### UnusedPrivateMethod

If a private method is declared but never used within the same class, there is no way that
code can ever be executed. It can be deleted from the program safely, and it is cleaner to
do so.

### UnconditionalIfStatement

Do not use "if" statements whose conditionals are always true or always false. Such statements
can be removed to produce cleaner and shorter code.

For example, if statement such as the following ones should be avoided: 

```{.java}
if (true) {
	// This part is always executed
}

if (2<1) {
	// This part is never executed
}

```

### NonStaticInitializer

A non-static initializer block will be called any time a constructor is invoked (just prior to
invoking the constructor). As this is an obscure and confusing language construct, it should be
avoided.

An example:

```{.java}
public class MyClass {
  // this block gets run before any call to a constructor
  {
    System.out.println("I am about to construct myself");
  }
}
```

### UselessOperationOnImmutable

An operation on an Immutable object (String, BigDecimal or BigInteger) won't change the object itself
since the result of the operation is a new object. Therefore, ignoring the operation result is an error.

For example, the following code

```{.java}
BigInteger bi = BigInteger.valueOf(10);
// the new object created in the next line is immediately forgotten
bi.add(BigInteger.valueOf(5));
```

is probably an incorrect version of the following code:

```{.java}
BigInteger bi = new BigInteger.valueOf(10);
// the next object created in now store in the variable
bi = bi.add(BigInteger.valueOf(5));
```

### JumbledIncrementer

Avoid jumbled loop incrementers in for-loops. This is usually a mistake, and is confusing even if intentional.

For example, the inner loop in the following example is not allowed

```{.java}
// Th outer loop only references 'i' and is okay
for (int i = 0; i < 10; i++) {
	// The inner loop references both 'i' and 'k' and is not allowed
	for (int k = 0; k < 20; i++) {
		System.out.println("Hello");
	}
}
```

### UnusedPrivateField

Detects when a private field is declared and/or assigned a value, but not used. If such a field is never used,
this usually means it can be removed without any problems.

**Examples:**

```{.java}
public class Something {
	// The next two fields are never used
    private static int FOO = 2;
    private int i = 5;
	// The next field is used (in the addOne() method)
    private int j = 6;
    public int addOne() {
        return j++;
    }
}
```

### ForLoopShouldBeWhileLoop

Some for loops can be simplified to while loops, this makes them more concise.

For example: 
```{.java}
for (;true;) {
	// Do something
}
```

can be replaced by the simpler

```{.java}
while (true) {
	// Do something
}
```

### UseEqualsToCompareStrings

Using '==' or '!=' to compare strings only works if intern versions are used on both sides,
a topic that is not covered in this course. Therefore, you should always use the equals() method instead.

The following code is usually unreliable

```{.java}
if (s == "one") {
	// Do something
}
```

and should be replaced by

```{.java}
if ("one".equals(s)) {
	// Do something
}
```

### ReturnFromFinallyBlock

In this course, there is no real need to use `finally` blocks (since you can use the *try-with-resources*).
If you still decide to use `finally` block, you should avoid returning from it as this can discard exceptions.

Thus, the following should be avoided:

```{.java}
public String foo() {
	try {
		throw new Exception( "My Exception" );
	} catch (Exception e) {
		throw e;
	} finally {
		// return not recommended here
		return "A. O. K."; 
	}
}
```

### ForLoopCanBeForeach

Reports loops that can be safely replaced with the enhanced for loop, (i.e. the for-each loop) syntax.
The rule considers loops over lists, arrays and iterators. A loop is safe to replace if it only uses
the index variable to access an element of the list or array, only has one update statement, and loops
through *every* element of the list or array left to right. In those cases, we prefer to use the 
enhanced for-loop as it is more concise and less error-prone.

Thus the following regular for-loop will trigger this rule

```{.java}
public void loop(List<String> lst) {
    for (int i = 0; i < lst.size(); i++) {
      System.out.println(lst.get(i));
    }
}
```

and can be safely replaced by

```{.java}
public void loop(List<String> lst) {
	for (String s : lst) {
	  System.out.println(s);
	}
}
```

### UnusedLocalVariable

Detects when a local variable is declared and/or assigned, but not used. In such cases, the variable
assignment can usually be removed. 

```{.java}
public void printSomething() {
	// The local variable i is unused
	int i = 5;
	System.out.println("something");
}
```

### SingularField

Fields whose scopes are limited to just single methods do not rely on the containing
object to provide them to other methods. They may be better implemented as local variables
within those methods.

The following example

```{.java}
public class Foo {
	// no reason to exist at the Foo instance level
    private int x;
    public void add(int y) {
     x = y + 5;
     return x;
    }
}
```

should be implemented as follows, since `x` is only used in the method `add`

```{.java}
public class Foo {
	 public void add(int y) {
     int x = y + 5;
     return x;
    }
}
```

### CloseResource

Ensure that resources (like `java.io.FileInputStream`, i.e. objects
and any subtype of `java.lang.AutoCloseable`) are always closed after use.
Failing to do so might result in resource leaks, that may result in strange
bugs and errors in more serious programs.


Avoid the following:

```{.java}
public void withFile() {
	InputStream file = new FileInputStream(new File("myfile.txt"));
	try {
		int c = file.in();
	} catch (IOException e) {
		// handle exception
	}
}
```

but us a *try-with-resources* block instead.

### ExtendsObject

There is no need to explicitly extend Object, as any class that does not explicitly extend
another class extends `Object` implicitly.

### MisplacedNullCheck

Checks if a null check is misplaced. If the variable is `null` a `NullPointerException` will be thrown.
Either the check is useless (the variable will never be "null") or it is incorrect.

The following are examples of `null` checks that are incorrect:

```{.java}
public void wrong1(String a, String baz) {
	if (a.equals(baz) && a != null) {}
}

public void wrong2(String a, String baz) {
        if (a.equals(baz) || a == null) {}
    }
```

### BrokenNullCheck

Looks for null checks that are broken since they will throw a `NullPointerException` themselves.
It is likely that you used `||` instead of `&&` or vice versa.

Examples:

```{.java}
public String bar(String text) {
    // should be &&
    if (text!=null || !text.equals("")) {
        return text;
	}
    // should be ||
    if (text==null && text.equals("")) {
        return text;
	}
	return "";
}
```

## Indications of sloppy code

The rules in this section are typically just sloppy: they distract other people who try to understand your code
from what is important. Therefore, you should avoid having them in your code, but they are not considered as
severe as the other rules.

Relevant external links to these rules:
* [EmptyBlock](https://checkstyle.sourceforge.io/config_blocks.html#EmptyBlock)
* [EmptyStatement](https://checkstyle.sourceforge.io/config_coding.html#EmptyStatement)
* [IllegalThrows](https://checkstyle.sourceforge.io/config_coding.html#IllegalThrows)
* [IllegalCatch](https://checkstyle.sourceforge.io/config_coding.html#IllegalCatch)
* [IllegalType](https://checkstyle.sourceforge.io/config_coding.html#IllegalType)
* [UnusedImports](https://checkstyle.sourceforge.io/config_imports.html#UnusedImports)
* [TodoComment](https://checkstyle.sourceforge.io/config_misc.html#TodoComment)
* [UncommentedMain](https://checkstyle.sourceforge.io/config_misc.html#UncommentedMain)
* [IdempotentOperations](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#idempotentoperations)
* [MissingBreakInSwitch](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#missingbreakinswitch)
* [DuplicateImports](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_codestyle.html#duplicateimports)
* [DoubleBraceInitialization](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_bestpractices.html#doublebraceinitialization)
* [SwitchStmtsShouldHaveDefault](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_bestpractices.html#switchstmtsshouldhavedefault)
* [UnusedImports](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_bestpractices.html#unusedimports)
* [UnnecessaryReturn](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_codestyle.html#unnecessaryreturn)
* [AvoidCatchingThrowable](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#avoidcatchingthrowable)
* [AvoidCatchingNPE](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#avoidcatchingnpe)
* [WhileLoopWithLiteralBoolean](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_bestpractices.html#whileloopwithliteralboolean)
* [DontImportJavaLang](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_codestyle.html#dontimportjavalang)
* [AbstractClassWithoutAnyMethod](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_design.html#abstractclasswithoutanymethod)
* [UselessParentheses](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_codestyle.html#uselessparentheses)
* [UseDiamondOperator](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_codestyle.html#usediamondoperator)



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

### IdempotentOperations

Avoid idempotent operations - these are operations have no effect and can thus be removed.

```{.java}
int x = 2;
x = x;
```

### MissingBreakInSwitch

You can always avoid using `switch` statements by replacing them with multiple `if`,
`else-if`, `else` statements. If you like to use a `switch` statement instead, make sure
that each case option has a break or return statement. Failing to do so
may indicate problematic behaviour. Empty cases are ignored as these indicate an intentional fall-through.

Examples:

```{.java}
switch(status) {
  case CANCELLED:
	doCancelled();
	// break; hm, should this be commented out?
  case NEW:
	doNew();
	// is this really a fall-through?
  case REMOVED:
	doRemoved();
	// what happens if you add another case after this one?
  case OTHER: // empty case - this is interpreted as an intentional fall-through
  case ERROR:
	doErrorHandling();
	break;
}
```

### SwitchStmtsShouldHaveDefault

All switch statements should include a default option to catch any unspecified values.

**Examples:**

```{.java}
public void bar(int x) {
    switch (x) {
      case 1: int j = 6;
      case 2: int j = 8;
          // missing default: here
    }
}
```

### DuplicateImports

Duplicate or overlapping import statements should be avoided.

**Examples:**

```{.java}
import java.lang.String;
import java.lang.*;
public class Foo {}
```

### DoubleBraceInitialization

Double brace initialisation is a pattern to initialise eg collections concisely. But it implicitly
generates a new `.class` file, and the object holds a strong reference to the enclosing object. For those
reasons, it is preferable to initialize the object normally, even though it's more verbose.

Since you never need inner classes or initializer blocks in this course, the following examples

```{.java}
// this is double-brace initialization
return new ArrayList<String>(){{ addAll("a","b","c"); }};
```

should be written as

```{.java}
// the better way is to not create an anonymous class:
List<String> a = new ArrayList<>();
a.addAll("a","b","c");
return a;
```

Alternatively, it may be an option to use `Arrays.listOf("a","b","c")` or `List.of("a", "b", "c")`
in Java versions where this is supported.

### UnnecessaryReturn

Avoid the use of unnecessary return statements. These are typically return statements at the end
of a `void` method.

For example, the return statement in the following method can be omitted:

```{.java}
public void bar() {
	int x = 42;
	return;
}
```

### AvoidCatchingThrowable

Catching Throwable errors is not recommended since its scope is very broad. It includes runtime issues such as 
OutOfMemoryError that should be exposed and managed separately.

For example:

```{.java}
public void bar() {
    try {
        // do something
    } catch (Throwable th) {  // should not catch Throwable
        th.printStackTrace();
    }
}
```

### AvoidCatchingNPE

Code should never throw NullPointerExceptions under normal circumstances; if it does, you should typically
change the code to avoid this. A catch block that catches `NullPointerException` may hide the original error,
causing other, more subtle problems in the problem as well.

For example:

```{.java}
public class Foo {
    void bar() {
        try {
            // do something
        } catch (NullPointerException npe) { // Avoid doing this
        }
    }
}
```

### WhileLoopWithLiteralBoolean

`do {} while (true);` requires reading the end of the statement before it is
apparent that it loops forever, whereas `while (true) {}` is easier to understand.

`do {} while (false);` is redundant, and if an inner variable scope is required,
a block `{}` is sufficient.

`while (false) {}` will never execute the block and can be removed in its entirety.

**Examples:**

```{.java}
public class Example {
  {
    while (true) { } // allowed
    while (false) { } // disallowed
    do { } while (true); // disallowed
    do { } while (false); // disallowed
  }
}
```

### DontImportJavaLang

Avoid importing anything from the package 'java.lang'. These classes are automatically imported.

For example: 

```{.java}
import java.lang.String;    // this is unnecessary
import java.lang.*;         // this is bad
```

### AbstractClassWithoutAnyMethod

If an abstract class does not provides any methods, it may be acting as a simple data container
that is not meant to be instantiated. In this case, it is probably better to use a private or
protected constructor in order to prevent instantiation than make the class misleadingly abstract.

For example:

```{.java}
// This abstract class has no methods, make it a regular class instead
public abstract class Example {
    public static final String BRAND = "Fancy Brand";
    public static final int BRAND_YEAR = 2019;
}
```

### UselessParentheses

Trivial cases of useless parentheses should be removed as they clutter the code. Note that in mathematical
expressions, unnecessary parentheses are allowed if they make the formula easier to understand.

For example

```{.java}
int n = 5;
Integer i = Integer.valueOf((n));
Integer o = (n);
```

can be written as

```{.java}
int n = 5;
Integer i = Integer.valueOf(n);
Integer o = n;
```

### UseDiamondOperator

Use the diamond operator to let the type be inferred automatically. With the Diamond operator it is possible
to avoid duplication of the type parameters.
Instead, the compiler is now able to infer the parameter types for constructor calls,
which makes the code also more readable.

Example:

```{.java}
// The following contains an unnecessary duplication of type parameters
List<String> strings = new ArrayList<String>();
// The following uses the diamond operator and is more concise
List<String> stringsWithDiamond = new ArrayList<>();
```

## Coding style

Note that many aspects of coding style can not be checked automatically. In particular,
the structure of your program should be reasonable: if it makes sense to introduce
additional helper methods, you should. You should also make sure variables have easy to
understand names, in particular if they are use in long part of the code.

### Naming Conventions

Java has a number of naming conventions. Adhering to these conventions make sure that
other (experienced) Java programmers can understand your code more easily, as things
are named as they expect. It is good practice to adhere to these conventions as well.

An common type of naming used in Java is [camel case](https://en.wikipedia.org/wiki/Camel_case).
In **lower camel case** a name starts with a lower case letter, while every new word after the
first starts with a capital letter. In most cases, it is allowed to use a number (except for the
first symbol of the name).  Examples are `x`, `productPrice`, `student1`, `course2`.
This naming style is most common is used for lambda parameters, local variables, instance
variables, method names, parameter names and static variable names.

In **upper camel case** a name start with an upper case letter, while every new word after the
first starts with a capital letter. Examples are: `Student`, `BufferedReader`, `MyTester`,
etcetera. This style is used for all type names, e.g. class names, interface names, and other
types that are not covered in this course, including enums and annotations.

The naming conventions that are checked are as follows:

* **Constants** : constant fields (e.g. those that are both `static` and `final`)
  should have names in capital letters, where words are separated by an underscore. For
  example `SOFTWARE_VERSION`, `PI`, `FEIGENBAUM_CONSTANT`, etc.
* **Lambda parameters** : parameter names of variables used in lambda expressions
  should have camel case names, with the additional exception that no numbers are allowed.
  So you can have `productPrice`, but not `product3Price`.
* **Local (final) variable names** : local variables (regardless of whether they are
  final) should be in *lower camel case*. 
* **Instance variables / member names** : the names of instance variables should be
  in *lower camel case*, like local variable names.
* **Method names** : the names of methods should be in *lower camel case* (even if they are
  static). Constructors always have a name equal to the name of the class, so for
  constructors the rule for *type names* applies.
* **Parameter names** : the names of parameters (the arguments of a method) should
  be in *lower camel case*.
* **Static variable names**: the names of static (but non-`final`) variables should
  be in *lower camel case*.
* **Type names** : the names of all types, most importantly classes and interfaces,
  should be in *upper camel case*.

Links to relevant naming rules:

* [ConstantName](https://checkstyle.sourceforge.io/config_naming.html#ConstantName)
* [LambdaParameterName](https://checkstyle.sourceforge.io/config_naming.html#LambdaParameterName)
* [LocalFinalVariableName](https://checkstyle.sourceforge.io/config_naming.html#LocalFinalVariableName)
* [LocalVariableName](https://checkstyle.sourceforge.io/config_naming.html#LocalVariableName)
* [MemberName](https://checkstyle.sourceforge.io/config_naming.html#MemberName)
* [MethodName](https://checkstyle.sourceforge.io/config_naming.html#MethodName)
* [ParameterName](https://checkstyle.sourceforge.io/config_naming.html#ParameterName)
* [StaticVariableName](https://checkstyle.sourceforge.io/config_naming.html#StaticVariableName)
* [TypeName](https://checkstyle.sourceforge.io/config_naming.html#TypeName)

### Layout issues

First and foremost, it is important to stress that you should pick a consistent indentation style.
You can have a look at the [Wikipedia page on indentation styles](https://en.wikipedia.org/wiki/Indentation_style),
or use the same style as the book and/or the lectures. Indentation style is checked manually and can
impact your grade after the deadline.

A number of other issues related to layout should also be avoided:

* Avoid adding a space between a method name and the opening parenthesis for the arguments.
  For example, `public void myMethod (int x)` is wrong and should be `public void myMethod(int x)`.
* When spaces are used to separate tokens in the language, avoid using more than a single space.
  For example, the doubles space in `int  x` or `x ==  y` are considered sloppy. 
* Make sure that comments are indented at the right level. Check
  [this page](https://checkstyle.sourceforge.io/config_misc#CommentsIndentation) for a number of relevant examples.
* Avoid using trailing comments that are on the same line as the code they are describing.
  Often they just state the same thing as the line is doing, and if something more complex is explained,
  it looks cleaner to write this above the section of code that is being described. Trailing comments are comments
  that look like `int x = 5; // assign 5 to variable x`.
* Use a single statement for each line. While you can start a new statement directly after a semicolon, it is cleaner
  to do so on a new line. For example, `int x = 5; int y = x + 3;` should be put on two lines rather than one.
* Avoid having space symbols just before a line break; these are unnecessary and sloppy.
* When using generic types, e.g. `List<String>`, make sure that the whitespace around the generic angle brackets `<`
  and `>` is according to the typical convention. This means that when `<` is used to define a generic type, it should
  not be preceded by a space if it follows a type name or method, and should never be followed by a space. When `>`
  is used in a generic type, it should never be preceded by whitespace and always be followed by whitespace, unless
  used in a diamond operator. Check [this page](https://checkstyle.sourceforge.io/config_whitespace.html#GenericWhitespace)
  for a number of examples that are correct.



Links to relevant layout rules:

* [MethodParamPad](https://checkstyle.sourceforge.io/config_whitespace.html#MethodParamPad)
* [GenericWhitespace](https://checkstyle.sourceforge.io/config_whitespace.html#GenericWhitespace)
* [SingleSpaceSeparator](https://checkstyle.sourceforge.io/config_whitespace.html#SingleSpaceSeparator)
* [CommentsIndentation](https://checkstyle.sourceforge.io/config_misc#CommentsIndentation)
* [TrailingComment](https://checkstyle.sourceforge.io/config_misc#TrailingComment)
* [OneStatementPerLine](https://checkstyle.sourceforge.io/config_coding#OneStatementPerLine)
* [RegexpSingleline](https://checkstyle.org/config_regexp.html#RegexpSingleline)

### Other naming issues

There are a number of issues related to names that you should usually avoid, as they are confusing.
These issues are:

* Don't use the names `hashcode()` and `equals()` for methods other than methods that do not
  override the methods with similar or the same name from the `Object` class.
* Avoid defining fields or instance variables that have the same name as the class. For example,
  in a class called `Student` you should avoid defining a field or instance variables with the
  name `student`.
* Avoid using dollar signs in variable/method/class/interface names.
* Avoid creating non-constructor methods that have the same name as their class. For example,
  a class `Student` should not define a method `public void Student()` as it is a non-constructor method.
  A constructor such as `public Student()` is allowed.

Links to rules related to some naming issues:

* [SuspiciousHashcodeMethodName](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#suspicioushashcodemethodname)
* [AvoidFieldNameMatchingTypeName](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#avoidfieldnamematchingtypename)
* [SuspiciousEqualsMethodName](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#suspiciousequalsmethodname)
* [AvoidDollarSigns](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_codestyle.html#avoiddollarsigns)
* [MethodWithSameNameAsEnclosingClass](https://pmd.github.io/pmd-6.17.0/pmd_rules_java_errorprone.html#methodwithsamenameasenclosingclass)


