package com.my.factory;

import org.junit.Before;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.AbstractBeanFactoryTests;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.tests.sample.beans.LifecycleBean;
import org.springframework.tests.sample.beans.TestBean;
import org.springframework.tests.sample.beans.factory.DummyFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zwshao on 6/25/16.
 */
public class MyBeanFactoryTest extends AbstractBeanFactoryTests {


    private DefaultListableBeanFactory applicationContext;


    protected DefaultListableBeanFactory createContext() throws Exception {
        StaticApplicationContext parent = new StaticApplicationContext();

        Map<String, String> m = new HashMap<>();
        m.put("name", "Robric");
        parent.registerPrototype("rod", TestBean.class, new MutablePropertyValues(m));

        Map<String, Object> fatherProperties = new HashMap<>();
        fatherProperties.put("name", "Albert");
        fatherProperties.put("age", 31);
        parent.registerSingleton("father", TestBean.class, new MutablePropertyValues(fatherProperties));



        DefaultListableBeanFactory applicationContext = new DefaultListableBeanFactory(parent);
        applicationContext.addBeanPostProcessor(new LifecycleBean.PostProcessor());
        new XmlBeanDefinitionReader(applicationContext).loadBeanDefinitions(new ClassPathResource("test.xml"));

        return applicationContext;
    }

    @Before
    public void before() throws Exception {
        this.applicationContext = createContext();
    }

    @Override
    protected BeanFactory getBeanFactory() {
        return applicationContext;
    }
}
