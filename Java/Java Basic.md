# Java Basic

## String

Strings are constant; their values cannot be changed after they are created. String buffers support mutable strings. Because String objects are immutable they can be shared.

Source code from JDK11

```java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
  
    /**
     * The value is used for character storage.
     *
     * @implNote This field is trusted by the VM, and is a subject to
     * constant folding if String instance is constant. Overwriting this
     * field after construction will cause problems.
     *
     * Additionally, it is marked with {@link Stable} to trust the contents
     * of the array. No other facility in JDK provides this functionality (yet).
     * {@link Stable} is safe here, because value is never null.     
     */
    @Stable
    private final byte[] value;  
  
    /**
     * The identifier of the encoding used to encode the bytes in
     * {@code value}. The supported values in this implementation are
     *
     * LATIN1
     * UTF16
     *
     * @implNote This field is trusted by the VM, and is a subject to
     * constant folding if String instance is constant. Overwriting this
     * field after construction will cause problems.
     */
    private final byte coder;

    /** Cache the hash code for the string */
    private int hash; // Default to 0  
}
```

## final

- **修饰类**: 当用final修饰一个类时，表明这个类不能被继承。也就是说，String类是不能被继承的，
- **修饰方法**：把方法锁定，以防任何继承类修改它的含义。
- **修饰变量**：修饰基本数据类型变量，则其数值一旦在初始化之后便不能更改；如果是引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象。

### final 修饰好处

- final的关键字提高了性能，JVM和java应用会缓存final变量；

- final变量可以在多线程环境下保持线程安全；

- 使用final的关键字提高了性能，JVM会对方法变量类进行优化；

Two ways to create a String objects: String literals vs using "new" keyword. Store String Literal Objects in **String Constant Pool** where located in **heap**, and created when code is compiled, saved in the .class file. Store String objects using "new" keyword in **normal HEAP memory** when the program is running.

Thanks to the immutability of *Strings* in Java, the JVM can optimize the amount of memory allocated for them by **storing only one copy of each literal \*String\* in the pool**. This process is called *interning*.

When we create a *String* variable and assign a value to it, the JVM searches the pool for a *String* of equal value. **If found, the Java compiler will simply return a reference to its memory address, without allocating additional memory.** If not found, it'll be added to the pool (interned) and its reference will be returned.

When we create a *String* via the *new* operator, the Java compiler will create a new object and store it in the heap space reserved for the JVM.

Every *String* created like this will point to a different memory region with its own address.

```java
@Test
public void stringBasic() {
    String apple1 = "Apple";
    String apple2 = "Apple";
    String apple3 = new String("Apple");

    Assert.assertSame(apple1, apple2);
    Assert.assertNotSame(apple1, apple3);

    String apple4 = apple3.intern();

    Assert.assertSame(apple1, apple4);
}
```

![String1](https://raw.githubusercontent.com/GuoenSu/HelloWorld/main/String1.jpeg)

```java
public String intern()
```

When the intern method is invoked, if the pool already contains a string equal to this `String` object as determined by the [`equals(Object)`](https://docs.oracle.com/javase/9/docs/api/java/lang/String.html#equals-java.lang.Object-) method, then the string from the pool is returned. Otherwise, this `String` object is added to the pool and a reference to this `String` object is returned.

```java
public boolean equals(Object anObject) {
   // shorts compare for string constant pool values
    if (this == anObject) {
        return true;
    }
    if (anObject instanceof String) {
        String aString = (String)anObject;
        if (coder() == aString.coder()) {
            return isLatin1() ? StringLatin1.equals(value, aString.value)
                              : StringUTF16.equals(value, aString.value);
        }
    }
    return false;
}
```

## static

1. **「修饰成员变量和成员方法:」** 被 static 修饰的成员属于类，不属于单个这个类的某个对象，被类中所有对象共享，可以并且建议通过类名调用。被static 声明的成员变量属于静态成员变量，静态变量 存放在 Java 内存区域的方法区。调用格式：`类名.静态变量名` `类名.静态方法名()`
2. **「静态代码块:」** 静态代码块定义在类中方法外, 静态代码块在非静态代码块之前执行(静态代码块—>非静态代码块—>构造方法)。 该类不管创建多少对象，静态代码块只执行一次.
3. **「静态内部类（static修饰类的话只能修饰内部类）：」** 静态内部类与非静态内部类之间存在一个最大的区别: 非静态内部类在编译完成之后会隐含地保存着一个引用，该引用是指向创建它的外围类，但是静态内部类却没有。没有这个引用就意味着：1. 它的创建是不需要依赖外围类的创建。2. 它不能使用任何外围类的非static成员变量和方法。
4. **「静态导包(用来导入类中的静态资源，1.5之后的新特性):」** 格式为：`import static` 这两个关键字连用可以指定导入某个类中的指定静态资源，并且不需要使用类名调用类中静态成员，可以直接使用类中静态成员变量和成员方法。

- 在构造器中使用 `super（）` 调用父类中的其他构造方法时，该语句必须处于构造器的首行，否则编译器会报错。另外，this 调用本类中的其他构造方法时，也要放在首行。
- this、super不能用在static方法中。

被 static 修饰的成员属于类，不属于单个这个类的某个对象，被类中所有对象共享。而 this 代表对本类对象的引用，指向本类对象；而 super 代表对父类对象的引用，指向父类对象；所以， **「this和super是属于对象范畴的东西，而静态方法是属于类范畴的东西」**。

## hashcode

**「hashcode」**:hashCode() 的作用是获取哈希码，也称为散列码；它实际上是返回一个int整数。这个哈希码的作用是确定该对象在哈希表中的索引位置。hashCode() 定义在JDK的Object.java中，这就意味着Java中的任何类都包含有hashCode() 函数。

散列表存储的是键值对(key-value)，它的特点是：能根据“键”快速的检索出对应的“值”。这其中就利用到了散列码！（可以快速找到所需要的对象）

### 为什么要有hashcode

**「我们先以“HashSet 如何检查重复”为例子来说明为什么要有 hashCode：」** 当你把对象加入 HashSet 时，HashSet 会先计算对象的 hashcode 值来判断对象加入的位置，同时也会与其他已经加入的对象的 hashcode 值作比较，如果没有相符的hashcode，HashSet会假设对象没有重复出现。但是如果发现有相同 hashcode 值的对象，这时会调用 `equals()`方法来检查 hashcode 相等的对象是否真的相同。如果两者相同，HashSet 就不会让其加入操作成功。如果不同的话，就会重新散列到其他位置。（摘自我的Java启蒙书《Head first java》第二版）。这样我们就大大减少了 equals 的次数，相应就大大提高了执行速度。

### hashcode和equals的相关规定

1. 如果两个对象相等，则hashcode一定也是相同的

2. 两个对象相等,对两个对象分别调用equals方法都返回true

3. 两个对象有相同的hashcode值，它们也不一定是相等的

4. **「因此，equals 方法被覆盖过，则 hashCode 方法也必须被覆盖」**

5. hashCode() 的默认行为是对堆上的对象产生独特值。如果没有重写 hashCode()，则该 class 的两个对象无论如何都不会相等（即使这两个对象指向相同的数据）

## Interface and the abstract class？

1. Interface can has **default method**, abstract class can not.

2. Interface can not has **contructors**, abstract class can has any number of contructors.

3. Interface **fields** are public, static and final by default, and only can have those. Abstract class could have any type variables.

4. A class can **extends** only from **one** abstract class, but could **implements** **multiple** interface 

   ```java
   public final class StringBuffer
      extends AbstractStringBuilder
      implements java.io.Serializable, CharSequence
   ```

5. Interface is **API and contract**, Abstract class is a **base class and templat**, has some concrate methods. 

## StringBuilder and StringBuffer

```java
abstract class AbstractStringBuilder implements Appendable, CharSequence {
    /**
     * The value is used for character storage.
     */
    byte[] value;

    /**
     * The id of the encoding used to encode the bytes in {@code value}.
     */
    byte coder;

    /**
     * The count is the number of characters used.
     */
    int count;

    /**
     * This no-arg constructor is necessary for serialization of subclasses.
     */
    AbstractStringBuilder() {
    }
```

String 中的对象是不可变的，也就可以理解为常量，线程安全。AbstractStringBuilder 是 StringBuilder 与 StringBuffer 的公共父类，定义了一些字符串的基本操作，如 expandCapacity、append、insert、indexOf 等公共方法。StringBuffer 对方法加了同步锁或者对调用的方法加了同步锁，所以是线程安全的。StringBuilder 并没有对方法进行加同步锁，所以是非线程安全的。

```java
@Override
@HotSpotIntrinsicCandidate
public synchronized StringBuffer append(String str) {
    toStringCache = null;
    super.append(str);
    return this;
}
```
