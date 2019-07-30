package com.example.algrorithm;

/**
 * @Author:cuijialei
 * @Date: 2019/7/29
 * @Describe
 */
public class Test20190729 {

    public static void main(String[] args) {
        //有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
        //菲波拉契数列 F(1)=1，F(2)=1, F(n)=F(n-1)+F(n-2)
        tuzi();
    }

    public static void tuzi(){
        System.out.println("第1个月的兔子对数: 1");
        System.out.println("第2个月的兔子对数: 1");
        int f1 = 1, f2 = 1, f, M = 24;
        for (int i = 3; i <= M; i++) {
            f = f2;
            f2 = f1 + f2;
            f1 = f;
            System.out.println("第" + i + "个月的兔子对数: " + f2);
        }
    }
}
