package com.company;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.ClassWriter.COMPUTE_MAXS;

public class MAgent {


    public static void premain(String args, Instrumentation inst) {
        System.out.println(args);
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                className = className.replace("/", ".");
                if ("com.company.Order".equalsIgnoreCase(className)) {
                    ClassReader reader = new ClassReader(classfileBuffer);
                    ClassWriter writer = new ClassWriter(reader, COMPUTE_MAXS | COMPUTE_FRAMES);
                    CostExcuteTimeVisitor visitor = new CostExcuteTimeVisitor(Opcodes.ASM8, writer);
                    reader.accept(visitor, 0);


                    //                    MyClassLoader classLoader = new MyClassLoader();
//                    Class clazz = classLoader.defineClass("com.company.Main", bytes);
//                    Object o = null;
//                    try {
//                        o = clazz.newInstance();
//                    } catch (InstantiationException e) {
//                        e.printStackTrace();
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        Method m = clazz.getMethod("buy");
//                        m.setAccessible(true);
//                        m.invoke(o);
//                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        FileOutputStream stream = new FileOutputStream("D:\\Users\\Documents\\Java\\AsmStudy\\target\\classes\\com\\company\\Order.class");
//                        stream.write(bytes);
//                        stream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                    return writer.toByteArray();
                }
                return new byte[0];
            }
        });
    }

    public static void premain(String args) {

    }
}
