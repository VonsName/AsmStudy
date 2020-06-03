package com.company;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class OrderBuyMethodVisitor extends MethodVisitor {
    public OrderBuyMethodVisitor(int api) {
        super(api);
    }

    public OrderBuyMethodVisitor(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    @Override
    public void visitCode() {
    }

    @Override
    public void visitInsn(int opcode) {
    }

    @Override
    public void visitEnd() {

    }
}
