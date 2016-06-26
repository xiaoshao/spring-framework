package com.my.factory;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.beans.factory.xml.AbstractBeanFactoryTests;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.tests.sample.beans.TestBean;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

/**
 * Created by zwshao on 6/25/16.
 */
public class MyBeanFactoryTest extends AbstractBeanFactoryTests {


    @Override
    protected BeanFactory getBeanFactory() {
        StaticApplicationContext parent = new StaticApplicationContext();

        HashMap<String, String> father = new HashMap<>();
        father.put("name", "Albert");
        parent.registerSingleton("father", TestBean.class, new MutablePropertyValues(father));

        HashMap<String, String> rod = new HashMap<>();
        rod.put("name", "rod");
        parent.registerSingleton("rod", TestBean.class, new MutablePropertyValues(rod));

        parent.registerSingleton("singletonFactory", TestBean.class);
        new XmlBeanDefinitionReader(parent).loadBeanDefinitions(new ClassPathResource("test.xml"));

        StaticApplicationContext staticApplicationContext = new StaticApplicationContext(parent);

        return staticApplicationContext;
    }
}
