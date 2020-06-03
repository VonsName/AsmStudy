package com.company;

import org.objectweb.asm.*;

public class CostExcuteTimeVisitor extends ClassVisitor {
    private String owner;
    private Boolean isInterface;

    public CostExcuteTimeVisitor(int api) {
        super(api);
    }

    public CostExcuteTimeVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
        isInterface = (access & Opcodes.ACC_INTERFACE) != 0;
        this.owner = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if ("buy".equalsIgnoreCase(name)) {
            mv = new OrderBuyMethodVisitor(api, mv, owner);
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        if (!isInterface) {
            FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "timer", Type.LONG_TYPE.getDescriptor(), null, null);
            if (fv != null) {
                fv.visitEnd();
            }
        }
        cv.visitEnd();
    }
}
