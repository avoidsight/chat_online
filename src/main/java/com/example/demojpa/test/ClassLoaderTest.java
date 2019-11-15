package com.example.demojpa.test;

/**
 * @author: zp
 * @Date: 2019-09-23 14:34
 * @Description:
 */
public class ClassLoaderTest {
    static int a = 5;

    static ClassLoaderTest classLoaderTest = new ClassLoaderTest();

    static {
        System.out.println("static block");
    }

    {
        System.out.println("constructor block");
    }

    ClassLoaderTest(){
        System.out.println("consrtuctor");
    }

    public static void main(String[] args) {
        ClassLoaderTest clt = new ClassLoaderTest();
        System.out.println(a);
    }
}
