package com.company;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class MAgent {

    public static ClassWriter cw = new ClassWriter(0);

    public static void premain(String args, Instrumentation inst) {
        System.out.println("我来也 我来自Java Agent");
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                System.out.println(className);
                String internalName = Type.getInternalName(Order.class);
                if (className.equalsIgnoreCase(internalName)) {
                    ClassReader reader = new ClassReader(classfileBuffer);
                    ClassWriter writer = new ClassWriter(reader, 0);
                    CostExcuteTimeVisitor visitor = new CostExcuteTimeVisitor(Opcodes.ASM8, writer);
                    reader.accept(visitor,0);
                }
                return new byte[0];
            }
        });
    }

    public static void premain(String args) {

    }
}
