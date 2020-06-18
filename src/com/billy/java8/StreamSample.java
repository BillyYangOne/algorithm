package com.billy.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Billy
 * @description: java8 流操作
 *   筛选、切片和匹配
 *   查找、匹配和归约
 *   使用数值范围等数值流
 *   从多个源创建流
 *   无限流
 * @date 2020/6/18 19:54
 */
public class StreamSample {

    public static void main(String[] args) {

        new StreamSample().test();
    }

    /**
     * 基本使用
     */
    private void test() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0)
                .distinct()
                .limit(1) //截短流
                .skip(2) // 跳过前n个元素
                .forEach(System.out::println);
    }

    /**
     * 映射
     */
    private void map() {
        List<Dish> menu = new ArrayList<>();
        List<String> dishName = menu.stream().map(Dish::getName).collect(Collectors.toList());
        System.out.println(dishName);

        // 返回集合中元素的字符长度
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLength = words.stream().map(String::length).collect(Collectors.toList());
        System.out.println(wordLength);

        // 流的扁平化处理 ，使用 flatMap
        List<String> uniqueCharacters  = words.stream().map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueCharacters);

        // 给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应 该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        System.out.println(pairs);

        // 对上面的扩展，返回总和能被3整除的数对
        numbers1.stream().flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j}))
                .collect(Collectors.toList());



        // 查找 和 匹配



    }

}
