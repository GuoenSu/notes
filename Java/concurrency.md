# Java Concurrency

> Pattern-Oriented Software Architecture: A Pattern Language for Distributed Computing, 4th Volume, 
>
> Frank Buschmann, Kevlin Henney, Douglas C. Schmidt

A **process** is a collection of resources, such as virtual memory, I/O handles, and threads of control, that provide the context for executing program instructions. Each process serves as a unit of protection and resource allocation within a hardware-protected address space [StRa05]. In contrast, a **thread** is a single sequence of instruction steps executed in the context of a process [Lew95]. In addition to an instruction pointer, a thread consists of resources, such as a runtime stack of function activation records, a set of registers, and thread-specific data. Each thread serves as a *unit of execution* that runs inside a process and shares its address space with other threads.

Distributed system software uses multiple processes and threads for a number of purposes, including:

- *Improving performance transparently* by using the concurrent processing capabilities of today's hardware and software platforms.

- *Improving performance explicitly* by allowing programmers to overlap computation and communication in their service processing.

- *Shortening perceived response time* for interactive software such as graphical user interfaces by associating separate threads with different service processing tasks, so that users are able to perform useful work while some tasks are blocked.

- *Simplifying application design* by allowing multiple service processing tasks to run independently using synchronous programming abstractions, such as two-way method invocations and operations that block on I/O and locks.

> Modern Java in Action
>
> Raoul-Gabriel Urma, Alan Mycroft, Mario Fusco

Chapter 7. **Parallel data processing and performance**

A *parallel* stream is a stream that splits its elements into multiple chunks, processing each chunk with a different thread. Thus, you can automatically partition the workload of a given operation on all the cores of your multicore processor and keep all Chapter 7. Parallel data processing and performanceof them equally busy. Let’s experiment with this idea by using a simple example.

Let’s suppose you need to write a method accepting a number n as argument and returning the sum of the numbers from one to n.

```java
public long parallelRangedSum() {
    return LongStream.rangeClosed(1, N)
                     .parallel()
                     .reduce(0L, Long::sum);
}

// traditional
public long iterativeSum(long n) {
    long result = 0;
    for (long i = 1L; i <= n; i++) {
        result += i;
    }
    return result;
}

```

- 7.2. **The fork/join framework**

The fork/join framework was designed to recursively split a parallelizable task into smaller tasks and then combine the results of each subtask to produce the overall result. It’s an implementation of the `ExecutorService` interface, which distributes those subtasks to worker threads in a thread pool, called `ForkJoinPool`.

```java
public abstract class ForkJoinTask<V> implements Future<V>, Serializable {
    
    /**
     * Arranges to asynchronously execute this task in the pool the
     * current task is running in, if applicable, or using the {@link
     * ForkJoinPool#commonPool()} if not {@link #inForkJoinPool}.  While
     * it is not necessarily enforced, it is a usage error to fork a
     * task more than once unless it has completed and been
     * reinitialized.  Subsequent modifications to the state of this
     * task or any data it operates on are not necessarily
     * consistently observable by any thread other than the one
     * executing it unless preceded by a call to {@link #join} or
     * related methods, or a call to {@link #isDone} returning {@code
     * true}.
     *
     * @return {@code this}, to simplify usage
     */
    public final ForkJoinTask<V> fork() { }

    /**
     * Returns the result of the computation when it
     * {@linkplain #isDone is done}.
     * This method differs from {@link #get()} in that abnormal
     * completion results in {@code RuntimeException} or {@code Error},
     * not {@code ExecutionException}, and that interrupts of the
     * calling thread do <em>not</em> cause the method to abruptly
     * return by throwing {@code InterruptedException}.
     *
     * @return the computed result
     */
    public final V join() { }

}
```

> example from, Java Concurrency and Multithreading in Practice, Tatiana Fesenko

```java
public class Lesson3 {

    public static void main(String[] args) {
        AppleTree[] appleTrees = AppleTree.newTreeGarden(12);
        ForkJoinPool pool = ForkJoinPool.commonPool();

        PickFruitTask task = new PickFruitTask(appleTrees, 0, appleTrees.length - 1);
        int result = pool.invoke(task);

        System.out.println();
        System.out.println("Total apples picked: " + result);
    }

    public static class PickFruitTask extends RecursiveTask<Integer> {

        private final AppleTree[] appleTrees;
        private final int startInclusive;
        private final int endInclusive;

        private final int taskThreadshold = 4;

        public PickFruitTask(AppleTree[] array, int startInclusive, int endInclusive) {
            this.appleTrees = array;
            this.startInclusive = startInclusive;
            this.endInclusive = endInclusive;
        }

        @Override
        protected Integer compute() {
            if (endInclusive - startInclusive < taskThreadshold) {
                return doCompute();
            }
            int midpoint = startInclusive + (endInclusive - startInclusive) / 2;

            PickFruitTask leftSum = new PickFruitTask(appleTrees, startInclusive, midpoint);
            PickFruitTask rightSum = new PickFruitTask(appleTrees, midpoint + 1, endInclusive);

            rightSum.fork(); // computed asynchronously

            return leftSum.compute()// computed synchronously: immediately and in the current thread
                    + rightSum.join();
        }

        protected Integer doCompute() {
            return IntStream.rangeClosed(startInclusive, endInclusive)//
                    .map(i -> appleTrees[i].pickApples())//
                    .sum();
        }
    }
}
```

- When to use ForkJoinTask

- Divide-and-conquer algorithms
  - Binary search
  - Merge sort
  - Closet pair of points
  - Karatsuba algorithm for integer multiplication

Chapter 15. **Concepts behind CompletableFuture and reactive programming**

- 15.1.2. **Executors and thread pools**

Java 5 provided the Executor framework and the idea of thread pools as a higher-level idea capturing the power of threads, which allow Java programmers to decouple task submission from task execution.

**Problems with threads**,
Java threads access operating-system threads directly. The problem is that operating-system threads are expensive to create and to destroy (involving interaction with page tables), and moreover, only a limited number exist. Exceeding the number of operating-system threads is likely to cause a Java application to crash mysteriously, so be careful not to leave threads running while continuing to create new ones.

**Thread pools and why they’re better**,
The Java ExecutorService provides an interface where you can submit tasks and obtain their results later. The expected implementation uses a pool of threads, which can be created by one of the factory methods, such as the `newFixedThreadPool` method:

`ExecutorService newFixedThreadPool(int nThreads)`

This method creates an ExecutorService containing nThreads (often called *worker threads*) and stores them in a thread pool, from which unused threads are taken to run submitted tasks on a first-come, first-served basis. These threads are returned to the pool when their tasks terminate. One great outcome is that it’s cheap to submit thousands of tasks to a thread pool while keeping the number of tasks to a hardware-appropriate number. Several configurations are possible, including the size of the queue, rejection policy, and priority for different tasks.

Norte the wording: The programmer provides a `task` (a `Runnable` or a `Callable`), which is executed by a `thread`.

**Runable and Callable**,

```java
    ExecutorService pool = Executors.newFixedThreadPool(2);
    
//  Callable<> task with return;
//  package java.util.concurrent;
    Callable<Pizza> task = () -> new Pizza();

    Future<Pizza> pizzaPickupOrder = pool.submit(task);
    Pizza pizza = pizzaPickupOrder.get();

//  Runnable task no return;
//  package java.lang;
    Runnable sayHello = () -> System.out.print("Hello World");    
    pool.execute(sayHello);
```

**Direct use Thread, not recommend**,

```java
    private static final Runnable helloTask = () -> System.out.printf("Hello from '%s'\n", Thread.currentThread().getName());

    public static void demoThread() {
        System.out.println("Demo Thread");
        System.out.println("⚠️For demo purpoeses only, don't create/start Threads yourself - use ExecutorService instead!!");
        
        // submit 10 similar tasks
        for (int i = 0; i < 10; i++) {
            new Thread(helloTask).start();
        }
        // The tasks are executed from _ten_ _different_ threads
        // 10 > 4 (4 is number of cores of my computer)
        // threads are NOT re-used

        System.out.println();
    }
```

**Use Executor**,

```java
public static void demoThreadsCreatedByThreadPool() throws InterruptedException, ExecutionException {
    System.out.println("Demo ThreadPool");
    System.out.println("😄Use an ExecutorService to manage threads");

    ExecutorService pool = Executors.newCachedThreadPool();
    // submit 10 similar tasks and watch that they are executed from different
    // threads
    for (int i = 0; i < 10; i++) {
        pool.submit(helloTask);
    }

    // Unlike thread.start(), threadPool.submit() returns a Future
    Future<Integer> randomNumber = pool.submit(() -> new Random().nextInt());
    System.out.println("Random number: " + randomNumber.get());

    pool.shutdown();
    System.out.println();
}
```

**Different Executor pool type**,

```java

    ExecutorService pool = Executors.newCachedThreadPool()
    ExecutorService pool = Executors.newFixedThreadPool(5)
    ExecutorService pool = Executors.newSingleThreadExecutor()

    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4)
    ScheduledFuture<?> waterReminder = scheduler.scheduleAtFixedRate(
            () -> System.out.println("Hi there, it's time to drink a glass of water"), //
            0, 1, TimeUnit.SECONDS);

```
