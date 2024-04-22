package vvc.framework;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  用于定义 Bean 实例化信息
 */
public class BeanFactory {
    private Map<String,BeanDefinition> beanDefinitionMap= new ConcurrentHashMap<>();

    public Object getBean(String beanName){
        return beanDefinitionMap.get(beanName).getBean();
    }

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition){
        beanDefinitionMap.put(beanName,beanDefinition);
    }
}
