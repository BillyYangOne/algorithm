package com.billy.test;

import java.io.File;
import java.util.Arrays;

/**
 * 各种排序算法
 */
public class SortAlgorithm {

    /**
     * 简单排序算法：将n个元素按照非降序进行排列
     */
    public static void simpleSort(int[] a){

        for (int i = 0; i < a.length - 1; i++) {

            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if(a[j] < a[min]){
                    min = j;
                }
            }
            if(min != i){
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
    }

    /**
     * 方法引用
     * java8--> 方法引用 ::
     */
    public void checkFile(){

        //隐藏的问题
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        for (File file : hiddenFiles) {
            System.out.println(file.getName());
        }

    }

    public static void main(String[] args) {

        int[] a = {5, 2, 9, 10, 8, 6, 4};
        simpleSort(a);
        System.out.println(Arrays.toString(a));

    }

}
