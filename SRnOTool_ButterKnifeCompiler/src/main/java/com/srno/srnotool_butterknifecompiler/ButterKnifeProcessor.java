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
     * �ļ���صĸ�����
     */
    private Filer mFiler;
    /**
     * Ԫ����صĸ�����
     */
    private Elements mElementUtils;
    /**
     * ��־��صĸ�����
     */
    private Messager mMessager;
    /**
     * ÿһ��ע�⴦�����඼������һ���յĹ��캯��
     * Ȼ����������һ�������init()���������ᱻע�⴦���ߵ���
     * ������ProcessingEnviroment����
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
        //����TestClass��
        TypeSpec.Builder tb = TypeSpec.classBuilder("ButterKnifeActivity_ViewBinding")
                .addModifiers(Modifier.PUBLIC);
        //����main����
        MethodSpec.Builder mb = MethodSpec.methodBuilder("ButterKnifeActivity_ViewBinding")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class);
//                .addParameter(Activity.class, "activity")
//                .addParameter(View.class, "source")

        //���ɴ���飬����ӵ�main������
        for(TypeElement e : ElementFilter.typesIn(roundEnvironment.getElementsAnnotatedWith(BindView.class))){
            CodeBlock cb = CodeBlock.builder()
                    .addStatement("$T.out.println(\"$L + $L\")", System.class,
                            e.getAnnotation(BindView.class).value(), e.getSimpleName())
                    .addStatement("$T.out.println(\"������\")", System.class)
                    .addStatement("$T.out.println(args)", System.class)
                    .build();
            mb.addCode(cb);
        }

        tb.addMethod(mb.build());

        JavaFile jf = JavaFile.builder("com.srno.annotation", tb.build()).build();
        //������д��java�ļ���
        try {
            jf.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
    /**
     * �������ָ�������ע�⴦������ע����ĸ�ע��ġ�
     * ע�⣬���ķ���ֵ��һ���ַ����ļ��ϣ���������������Ҫ�����ע�����͵ĺϷ�ȫ�ơ�
     * ���仰˵�������ﶨ�����ע�⴦����ע�ᵽ��Щע���ϡ�
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
