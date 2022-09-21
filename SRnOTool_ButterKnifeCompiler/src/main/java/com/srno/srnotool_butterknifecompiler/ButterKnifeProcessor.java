package com.srno.srnotool_butterknifecompiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.srno.srnotool_butterknifeannotation.BindView;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;

@AutoService(Processor.class)
@SuppressWarnings("NullAway")
public final class ButterKnifeProcessor extends AbstractProcessor {
    /**
     * 文件相关的辅助类
     */
    private Filer mFiler;
    /**
     * 元素相关的辅助类
     */
    private Elements mElementUtils;
    /**
     * 日志相关的辅助类
     */
    private Messager mMessager;
    /**
     * 每一个注解处理器类都必须有一个空的构造函数
     * 然而，这里有一个特殊的init()方法，它会被注解处理工具调用
     * 并输入ProcessingEnviroment参数
     * @param processingEnvironment
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        mFiler = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //生成TestClass类
        TypeSpec.Builder tb = TypeSpec.classBuilder("ButterKnifeActivity_ViewBinding")
                .addModifiers(Modifier.PUBLIC);
        //生成main方法
        MethodSpec.Builder mb = MethodSpec.methodBuilder("ButterKnifeActivity_ViewBinding")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class);
//                .addParameter(Activity.class, "activity")
//                .addParameter(View.class, "source")

        //生成代码块，并添加到main方法中
        for(TypeElement e : ElementFilter.typesIn(roundEnvironment.getElementsAnnotatedWith(BindView.class))){
            CodeBlock cb = CodeBlock.builder()
                    .addStatement("$T.out.println(\"$L + $L\")", System.class,
                            e.getAnnotation(BindView.class).value(), e.getSimpleName())
                    .addStatement("$T.out.println(\"刘东阳\")", System.class)
                    .addStatement("$T.out.println(args)", System.class)
                    .build();
            mb.addCode(cb);
        }

        tb.addMethod(mb.build());

        JavaFile jf = JavaFile.builder("com.srno.annotation", tb.build()).build();
        //将代码写入java文件中
        try {
            jf.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
    /**
     * 这里必须指定，这个注解处理器是注册给哪个注解的。
     * 注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称。
     * 换句话说，在这里定义你的注解处理器注册到哪些注解上。
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(Override.class.getCanonicalName());
        types.add(BindView.class.getCanonicalName());
        return types;
    }
}
