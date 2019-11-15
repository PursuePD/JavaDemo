package com.example.java8.Lambda;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @Author:cuijialei
 * @Date: 2018/8/31
 * Lambda允许把函数作为一个方法的参数（函数作为参数传递进方法中），
 * 或者把代码看成数据：函数式程序员对这一概念非常熟悉。
 * 在JVM平台上的很多语言（Groovy，Scala，……）从一开始就有Lambda，
 * 但是Java程序员不得不使用毫无新意的匿名类来代替lambda。
 */
public class LambdaFirstTest {

    public static void main(String[] args){
        //在最简单的形式中，一个lambda可以由用逗号分隔的参数列表、–>符号与函数体三部分表示。例如：
        Arrays.asList( "a", "b", "d" ).forEach(e -> System.out.println( e ) );
        //在某些情况下lambda的函数体会更加复杂，这时可以把函数体放到在一对花括号中，就像在Java中定义普通函数一样。
        Arrays.asList( "a", "b", "d" ).forEach( e -> {
            System.out.print( e );
            System.out.print( e );
        } );
        //Lambda可以引用类的成员变量与局部变量（如果这些变量不是final的话，它们会被隐含的转为final，这样效率更高
        String separator = ",";
        Arrays.asList( "a", "b", "d" ).forEach(
                ( String e ) -> System.out.print( e + separator ) );

	BigDecimal a1 = null;
	BigDecimal a2 = null;
        BigDecimal b = new BigDecimal("100");

	System.out.println(b.add(a1 == null?BigDecimal.ZERO:a1).add(a2 == null?BigDecimal.ZERO:a2));

    }
}
