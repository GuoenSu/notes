# JVM

## Stack Memory and Heap Space in Java, [by baeldung.com](https://www.baeldung.com/java-stack-heap)

To run an application in an optimal way, JVM divides memory into stack and heap memory. **Whenever we declare new variables and objects, call a new method, declare a String, or perform similar operations, JVM designates memory to these operations from either Stack Memory or Heap Space.**

### Stack Memory in Java

**Stack Memory in Java is used for static memory allocation and the execution of a thread.**

It contains primitive values that are specific to a method and **references** to objects referred from the method that are in a heap.

Access to this memory is in Last-In-First-Out (LIFO) order. Whenever we call a new method, a new block is created on top of the stack which contains values specific to that method, like primitive variables and references to objects.

When the method finishes execution, its corresponding stack frame is flushed, the flow goes back to the calling method, and space becomes available for the next method.

#### Key Features of Stack Memory

Some other features of stack memory include:

- It grows and shrinks as new methods are called and returned, respectively.
- Variables inside the stack exist only as long as the method that created them is running.
- It's automatically allocated and deallocated when the method finishes execution.
- If this memory is full, Java throws java.lang.StackOverFlowError.
- Access to this memory is fast when compared to heap memory.
- This memory **is threadsafe**, as each thread operates in its own stack.

### Heap Space in Java

**Heap space is used for the dynamic memory allocation of Java objects and JRE classes at runtime.**

New objects are always created in heap space, and the references to these objects are stored in stack memory.

These objects have global access and we can access them from anywhere in the application.

We can break this memory model down into smaller parts, called generations, which are:

- **Young Generation** – this is where all new objects are allocated and aged. A minor Garbage collection occurs when this fills up.
- **Old or Tenured Generation** – this is where long surviving objects are stored. When objects are stored in the Young Generation, a threshold for the object's age is set, and when that threshold is reached, the object is moved to the old generation.
- **Permanent Generation** – this consists of JVM metadata for the runtime classes and application methods.

#### Key Features of Java Heap Memory

Some other features of heap space include:

- It's accessed via complex memory management techniques that include the Young Generation, Old or Tenured Generation, and Permanent Generation.
- If heap space is full, Java throws java.lang.OutOfMemoryError.
- Access to this memory is comparatively slower than stack memory
- This memory, in contrast to stack, isn't automatically deallocated. It needs Garbage Collector to free up unused objects so as to keep the efficiency of the memory usage.
- Unlike stack, a heap **isn't threadsafe** and needs to be guarded by properly synchronizing the code.

```java
class Person {
    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class PersonBuilder {
    private static Person buildPerson(int id, String name) {
        return new Person(id, name);
    }

    public static void main(String[] args) {
        int id = 23;
        String name = "John";
        Person person = null;
        person = buildPerson(id, name);
    }
}
```

Let's analyze this step-by-step:

1. When we enter the main() method, a space in stack memory is created to store primitives and references of this method.
    - Stack memory directly stores the primitive value of integer id.
    - The reference variable person of type Person will also be created in stack memory, which will point to the actual object in the heap.

2. The call to the parameterized constructor Person(int, String) from main() will allocate further memory on top of the previous stack. This will store:
    - The this object reference of the calling object in stack memory
    - The primitive value id in the stack memory
    - The reference variable of String argument name, which will point to the actual string from string pool in heap memory

3. The main method is further calling the buildPerson() static method, for which further allocation will take place in stack memory on top of the previous one. This will again store variables in the manner described above.

4. However, heap memory will store all instance variables for the newly created object person of type Person.

![java-heap-stack-diagram](https://raw.githubusercontent.com/GuoenSu/HelloWorld/main/java-heap-stack-diagram.jpg)

## Summary

| Parameter | Stack Memory | Heap Space |
| --------- | ------------ | ---------- |
| Application | Stack is used in parts, one at a time during execution of a thread | The entire application uses Heap space during runtime |
| Size|Stack has size limits depending upon OS, and is usually smaller than Heap|There is no size limit on Heap|
|Storage|Stores only primitive variables and references to objects that are created in Heap Space|All the newly created objects are stored here|
|Order|It's accessed using Last-in First-out (LIFO) memory allocation system|This memory is accessed via complex memory management techniques that include Young Generation, Old or Tenured Generation, and Permanent Generation.
Life|Stack memory only exists as long as the current method is running|Heap space exists as long as the application runs
Efficiency|Much faster to allocate when compared to heap|Slower to allocate when compared to stack
Allocation/Deallocation|This Memory is automatically allocated and deallocated when a method is called and returned, respectively|Heap space is allocated when new objects are created and deallocated by Gargabe Collector when they're no longer referenced

