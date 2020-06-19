package com.billy.test;

/**
 * @author Billy
 * @description: 测试类，测试执行结果
 * @date 2020/6/18 17:22
 */
public class MeaningOfThis {

    public final int value = 4;
    public void doIt() {
        int value = 6;
        Runnable runnable = new Runnable() {
            public final int value = 5;
            @Override
            public void run() {

                int value = 10;
                System.out.println(this.value);
            }
        };

        runnable.run();
    }

    public static void main(String[] args) {
        new MeaningOfThis().doIt();
    }
}
