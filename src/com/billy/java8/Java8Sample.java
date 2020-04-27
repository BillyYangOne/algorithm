package com.billy.java8;

import java.lang.annotation.Repeatable;
import java.time.Clock;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.LongBinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Java 8 样例
 */
public class Java8Sample {

    public static void main(String[] args) {

        try {

            String dateStr = "2019年10月01日";
            System.out.println(dateStr.substring(0, 8));
//            new Java8Sample().seniorPart();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * stream 样例 : 顺序操作
     */
    private void streams() {

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        // filter && sorted
        stringCollection.stream().filter(item -> item.startsWith("a")).sorted().forEach(System.out::println);

        // map
        System.out.println("map------------------");
        stringCollection.stream().map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        //match 只返回一个匹配结果
        System.out.println("match------------------");
        boolean startA = stringCollection.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println(startA);

        boolean allStartWithA = stringCollection.stream().allMatch(s -> s.startsWith("a"));
        System.out.println(allStartWithA);

        boolean noneMatchWithA = stringCollection.stream().noneMatch(s -> s.startsWith("a"));
        System.out.println(noneMatchWithA);

        //count 终结符，返回当前流中匹配的结果值
        long containACount = stringCollection.stream().filter(s -> s.contains("1")).count();
        System.out.println(containACount);

        //reduce
        Optional<String> reduce = stringCollection.stream().sorted().reduce((a, b) -> a + ":" + b);
        reduce.ifPresent(System.out::println);
    }

    /**
     * parallelStream  并行操作
     */
    private void parallelStream() {

        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long start = System.nanoTime();

        long count = values.parallelStream().sorted().count();
        System.out.println("count:" + count);

        long takenSeconds = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
        System.out.println(String.format("Took time: %d ms", takenSeconds));


        //并行流
        Arrays.asList("a1", "a2", "b1", "b2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: &s [%s] \n",
                            s, Thread.currentThread().getName());
                    return true;
                }).map(s -> {
                    System.out.format("map: &s [%s] \n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
        })
                .forEach(s -> System.out.format("forEach: %s [%s] \n",
                        s, Thread.currentThread().getName()));


    }

    /**
     * clock
     */
    private void clock() {

        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);

        Instant instant = clock.instant();
        Date from = Date.from(instant);
        System.out.println(from);

        IntStream.range(1, 4).forEach(System.out::println);


        Supplier<Stream<String>> streamSupplier = () -> Stream.of("a1", "d1", "c1", "f1").filter(s -> s.startsWith("a"));
        boolean b = streamSupplier.get().anyMatch(s -> true);
        boolean b1 = streamSupplier.get().noneMatch(s -> true);
        System.out.println(b);
        System.out.println(b1);
    }

    /**
     * 高级部分
     */
    private void seniorPart() {

        List<Person> people = Arrays.asList(
                new Person("Billy", 27),
                new Person("Billy1", 27),
                new Person("Yang", 32),
                new Person("Yang1", 32)
        );

//        List<Person> filterPerson = people.stream().filter(person -> person.name.startsWith("B")).collect(Collectors.toList());
//        System.out.println(filterPerson);

        //group by age
        Map<Integer, List<Person>> groupByAge = people.stream().collect(Collectors.groupingBy(person -> person.age));
        groupByAge.forEach((age, person) -> System.out.format("age %d , person %s \n", age, person.get(0)));

        //average
//        Double averageAge = people.stream().collect(Collectors.averagingInt(person -> person.age));
//        System.out.println("average age:" + averageAge);
//
//        //summarizing
//        IntSummaryStatistics ageSummary = people.stream().collect(Collectors.summarizingInt(p -> p.age));
//        System.out.println(ageSummary);
//        System.out.println(ageSummary.getCount());
//
//        //
//        String phrase = people.stream().map(person -> person.name)
//                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
//        System.out.println(phrase);
//
//        //并行流
//        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
//        int parallelism = forkJoinPool.getParallelism();
//        System.out.println("公共线程池：" + parallelism);


    }


    /**
     * 包装类
     */
    private void seniorClass() {

        Hint annotation = Person.class.getAnnotation(Hint.class);
        System.out.println(annotation);

        Hints hints1 = Person.class.getAnnotation(Hints.class);
        System.out.println(hints1.value());

    }

    /**
     * 线程
     */
    private void threadTest() {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            String name = Thread.currentThread().getName();
            System.out.format("Hello, %s!", name);
        });
        executorService.shutdown();

    }

    /**
     * callbale
     */
    private void threadCallbale() throws ExecutionException, InterruptedException {

        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            } catch (Exception e) {
                throw new Exception(e);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);
        System.out.format("future is done ？ %s\n", future.isDone());

        Integer result = future.get();
        System.out.format("future is done? %s\n", future.isDone());
        System.out.format("result : %s", result);

    }

    /**
     * 执行多个
     */
    private void invokeAll() {

        ExecutorService executorService = Executors.newWorkStealingPool(1);
        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task12",
                () -> "task3");

        try {
            executorService.invokeAll(callables)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        }catch (Exception e){
                            throw new IllegalStateException(e);
                        }
                    }).forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * invokeAny
     */
    private void invokeAny() {

        try {

            List<Callable<String>> callables = Arrays.asList(
                    () -> {
                        TimeUnit.SECONDS.sleep(1);
                        return "task1";
                    },
                    () -> {
                        TimeUnit.SECONDS.sleep(3);
                        return "task2";
                    },
                    () -> {
                        TimeUnit.SECONDS.sleep(2);
                        return "task3";
                    }
            );
            ExecutorService executorService = Executors.newWorkStealingPool();
            String result = executorService.invokeAny(callables);
            System.out.println(result);


        }catch (Exception e){

        }


    }

    /**
     * reentrantLock 互斥锁
     */
    private void reentrantLock() {

        ReentrantLock lock = new ReentrantLock();
        lock.lock();


        lock.unlock();

    }


    /**
     * 关闭 executor
     * @param executor
     */
    private static void stop(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        }catch (Exception e){
            System.err.println("termination interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("killing non-finished tasks");
            }
            executor.shutdownNow();
        }

    }
    /**
     * 原子变量 和 currentMap
     */
    private void atomicity() {

        AtomicInteger atomicInteger = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(atomicInteger::incrementAndGet));
        stop(executor);
        System.out.println(atomicInteger.get());

    }

    /**
     * LongAddr 是 AtomicLong 的替代
     */
    private void longAddr() {

        LongAdder longAdder = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        IntStream.range(0, 1000)
                .forEach(i -> executorService.submit(longAdder::increment));
        stop(executorService);

        System.out.println(longAdder.sumThenReset());
    }

    /**
     * LongAccumulator 是 LongAddr 的更通用的版本
     *  以类型为 LongBinaryOperator lambda 表达式构建
     *
     */
    private void longAccumulator() {

        LongBinaryOperator op = (x, y) -> 25 * x + y;
        LongAccumulator accumulator = new LongAccumulator(op, 1L);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1)
                .forEach(i -> executor.submit(() -> accumulator.accumulate(i)));
        stop(executor);

        System.out.println(accumulator.getThenReset());

    }


    /**
     * concurrentMap
     */
    private void concurrentMap() {

        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("Billy", "Yang");
        map.put("Json", "Michael");

        // 在key不存在的情况下，才会插入
        map.putIfAbsent("foo", "name");

        // 当传入的key 不存在的情况下，返回默认值
        Object orDefault = map.getOrDefault("Yang", "Lako");
        System.out.format(" orDefault : %s\n", orDefault);

        map.forEach((key, value) -> System.out.format("%s = %s \n", key, value));

        //replaceAll 接受类型为 BiFunction 的 lambda 表达式。
        // BiFunction 接受两个参数并返回一个值，作为当前键的新值
        map.replaceAll((key, value) -> "Billy".equals(key) ? "Le" : value);
        System.out.println(map.get("Billy"));

        Object result = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if ("Billy".equals(key)) {
                return value;
            }
            return null;
        });
        System.out.println("Result: " + result);


    }



}

@Hint("hint1")
@Hint("hint2")
class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

@interface Hints {
    Hint[] value();
}

@Repeatable(Hints.class)
@interface Hint {
    String value();
}
