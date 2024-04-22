package vvc.framework.beans.factory.support;

import vvc.framework.beans.BeansException;
import vvc.framework.beans.factory.config.BeanDefinition;

/**
 * 只完成了创建bean实例的功能
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    /**
     *
     * @param beanName
     * @param beanDefinition
     * @return
     * @throws BeansException
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            //todo 这块会埋下一个坑，有构造函数入参的对象怎么处理
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }
}
