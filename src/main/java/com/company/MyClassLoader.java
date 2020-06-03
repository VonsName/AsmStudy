package com.company;

/**
 * @author : vons
 * @version : 1.0
 * @date : 2020/5/30 12:18
 */
public class MyClassLoader extends ClassLoader {

    public Class defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}
