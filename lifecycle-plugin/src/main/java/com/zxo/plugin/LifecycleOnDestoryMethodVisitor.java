package com.zxo.plugin;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static com.android.dx.cf.code.ByteOps.POP;
import static com.android.tools.r8.org.objectweb.asm.Opcodes.ALOAD;
import static com.android.tools.r8.org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.gradle.internal.impldep.bsh.ParserConstants.NEW;
import static org.gradle.internal.impldep.com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.gradle.internal.impldep.com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.DUP;

/**
 * User: xiaoouzhai
 * Date: 2019/3/20
 */
public class LifecycleOnDestoryMethodVisitor extends MethodVisitor {
    public LifecycleOnDestoryMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM4, mv);
    }

    @Override
    public void visitCode() {
        //Log.d("zxo", "visitCode: onDestory:=="+this.getClass().getName());
        mv.visitLdcInsn("zxo");
        mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        mv.visitLdcInsn("visitCode: onDestory:==");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(POP);

        super.visitCode();
    }

    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
    }
}
