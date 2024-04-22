package vvc.framework.beans.factory.config;

/**
 * 用于定义 Bean 实例化信息
 */
public class BeanDefinition {
        private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
