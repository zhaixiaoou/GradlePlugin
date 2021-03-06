package com.zxo.plugin;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * User: xiaoouzhai
 * Date: 2019/3/20
 */
public class LifecycleClassVisitor extends ClassVisitor implements Opcodes {

    private String mClassName;
    public LifecycleClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println("LifecycleClassVisitor: visit----> started:" + name);
        this.mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//        System.out.println("LifecycleClassVisitor: visitMethod :" + name);
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if ("android/support/v4/app/FragmentActivity".equals(this.mClassName)){
            if ("onCreate".equals(name)){
                System.out.println("LifecycleClassVisitor : change method ----> " + name);
                return new LifecycleOnCreateMethodVisitor(mv);
            } else if ("onDestory".equals(name)){
                System.out.println("LifecycleClassVisitor : change method ----> " + name);
                return new LifecycleOnDestoryMethodVisitor(mv);
            }
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        System.out.println("LifecycleClassVisitor: visit----> end:");
        super.visitEnd();
    }
}
