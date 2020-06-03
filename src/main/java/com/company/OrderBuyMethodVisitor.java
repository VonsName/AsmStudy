package com.company;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.io.PrintStream;

public class OrderBuyMethodVisitor extends MethodVisitor {
    private String owner;

    public OrderBuyMethodVisitor(int api, MethodVisitor methodVisitor, String owner) {
        super(api, methodVisitor);
        this.owner = owner;
    }

    public OrderBuyMethodVisitor(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    @Override
    public void visitCode() {
        mv.visitCode();
        mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "timer", "J");
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(System.class), "currentTimeMillis", "()J", false);
        mv.visitInsn(Opcodes.LSUB);
        mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "timer", "J");
    }

    @Override
    public void visitInsn(int opcode) {
//
        if (opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN || opcode == Opcodes.ATHROW) {
            mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "timer", Type.LONG_TYPE.getDescriptor());
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(System.class), "currentTimeMillis", "()J", false);
            mv.visitInsn(Opcodes.LADD);
            mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "timer", Type.LONG_TYPE.getDescriptor());

            mv.visitFieldInsn(Opcodes.GETSTATIC, Type.getType(System.class).getInternalName(), "out", Type.getType(PrintStream.class).getDescriptor());
            mv.visitTypeInsn(Opcodes.NEW, Type.getType(StringBuilder.class).getInternalName());
            mv.visitInsn(Opcodes.DUP);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getType(StringBuilder.class).getInternalName(), "<init>", "()V", false);
            mv.visitLdcInsn("耗时时间为:[");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getType(StringBuilder.class).getInternalName(), "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "timer", Type.LONG_TYPE.getDescriptor());
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getType(StringBuilder.class).getInternalName(), "append", "(J)Ljava/lang/StringBuilder;", false);
            mv.visitLdcInsn("]秒");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getType(StringBuilder.class).getInternalName(), "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getType(StringBuilder.class).getInternalName(), "toString", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getType(PrintStream.class).getInternalName(), "println", "(Ljava/lang/String;)V", false);
        }
        mv.visitInsn(opcode);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack, maxLocals);
    }
}
