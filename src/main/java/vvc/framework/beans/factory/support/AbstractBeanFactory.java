package vvc.framework.beans.factory.support;

import vvc.framework.beans.BeansException;
import vvc.framework.beans.factory.BeanFactory;
import vvc.framework.beans.factory.config.BeanDefinition;

/**
 * 具备使用单例注册的功能
 * 不具备创建实例的功能，抽象出来交给子类去实现
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        //如果单例池中有直接返回
        Object singleton = getSingleton(name);
        if(singleton!=null){
            return singleton;
        }
        //需要自己创建
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name,beanDefinition);
    }
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
