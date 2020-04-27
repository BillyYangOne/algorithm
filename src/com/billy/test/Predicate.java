package com.billy.test;

/**
 * 函数式接口：只定义一个抽象方法的接口
 * @param <T>
 */
public interface Predicate<T> {
    boolean test(T t);
}
