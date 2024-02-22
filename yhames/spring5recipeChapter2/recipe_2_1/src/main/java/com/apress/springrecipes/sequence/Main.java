package com.apress.springrecipes.sequence;

import com.sun.xml.internal.ws.model.AbstractWrapperBeanGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.sequence.config.SequenceGeneratorConfiguration;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SequenceGeneratorConfiguration.class);

//        SequenceGenerator generator = (SequenceGenerator) context.getBean("sequenceGenerator");
//        SequenceGenerator generator = context.getBean("sequenceGenerator", SequenceGenerator.class);
        SequenceGenerator generator = context.getBean(SequenceGenerator.class);

        System.out.println(generator.getSequence());
        System.out.println(generator.getSequence());
    }
}