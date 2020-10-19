package com.fun.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author huanye
 * Date: 2017/9/19 上午11:34
 */
public class DataAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("----into process...");
        //获取所有编译类元素，并打印，测试用
        Set<? extends Element> elements = roundEnv.getRootElements();
        System.out.println("输入的所有类有：");
        for(Element e: elements){
            System.out.println(">>> "+e.getSimpleName());
        }
        if (annotations instanceof Data) {
            System.out.println("get data annotation");
        }
        return false;
    }
}
