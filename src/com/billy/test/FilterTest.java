package com.billy.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java8 新特性
 */
public class FilterTest {

    public static <T> List<T> filter1(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c){
        for (T i : list) {
            c.accept(i);
        }
    }

    public static <T, R> List<R> map2Len(List<T> list, Function<T, R> f){

        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(f.apply(s));
        }
        return result;
    }

    public static void process(Runnable r){
        r.run();
    }

    public static void main(String[] args) {

        process(()->{
            System.out.println("hello ");
        });

        List<User> users = new ArrayList<>();
        User u = new User();
        u.setAge(12);
        u.setUserName("billy");
        users.add(u);
        u = new User();
        u.setUserName("happy");
        u.setAge(23);
        users.add(u);
        u = new User();
        u.setUserName("yang");
        u.setAge(18);
        users.add(u);
        List<User> happyUsers = filter1(users, (User user) -> "happy".equals(user.getUserName()));
        users.sort(Comparator.comparing(User::getAge));

        //流 (多核使用 parallelStream)
        happyUsers.stream().filter(user -> user.getAge() > 10)  //筛选出年龄大于10的用户
                .sorted(Comparator.comparing(User::getUserName)) //按照姓名进行排序
                .map(User::getUserName)                          //提取用户的姓名
                .collect(Collectors.toList());                   //将所有的姓名放入list中


        Thread thread = new Thread(() -> System.out.println("123"));
        thread.run();
        try{
            users.forEach((User user) -> System.out.println(user.getAge()));

        }catch (Exception e){
            e.printStackTrace();
        }

        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();


        forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.println(i));


        System.out.println("hello----");

        List<Integer> integers = map2Len(Arrays.asList("hello", "billy", "sex"), (String s) -> s.length());
        System.out.println(integers);


        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);
        System.out.println("result:" + result);


        //代码示例
        Comparator<User> byAge = (User u1, User u2) -> u1.getAge().compareTo(u2.getAge());




    }
}
