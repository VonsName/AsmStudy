package com.company;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class CostExcuteTimeVisitor extends ClassVisitor {
    public CostExcuteTimeVisitor(int api) {
        super(api);
    }

    public CostExcuteTimeVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visitField(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "timer", Type.INT_TYPE.getDescriptor(), null, null);
        cv.visitEnd();
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor cv;
        if ("buy".equalsIgnoreCase(name)) {
            cv = new OrderBuyMethodVisitor(api);
            return cv;
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
}
