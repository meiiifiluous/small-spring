package vvc.framework.beans.factory.support;

import vvc.framework.beans.BeansException;
import vvc.framework.core.io.Resource;
import vvc.framework.core.io.ResourceLoader;


public interface BeanDefinitionReader {

    /**
     * 获取BeanDefinition注册表
     * @return BeanDefinition注册表
     */
    BeanDefinitionRegistry getRegistry(); // 获取BeanDefinition注册表

    /**
     * 获取资源加载器
     * @return 资源加载器
     */
    ResourceLoader getResourceLoader(); // 获取资源加载器

    /**
     * 加载给定资源的Bean定义
     * @param resource 要加载的资源
     * @throws BeansException 如果加载Bean定义时发生错误，抛出BeansException
     */
    void loadBeanDefinitions(Resource resource) throws BeansException; // 加载给定资源的Bean定义

    /**
     * 加载给定多个资源的Bean定义
     * @param resources 要加载的多个资源
     * @throws BeansException 如果加载Bean定义时发生错误，抛出BeansException
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException; // 加载给定多个资源的Bean定义

    /**
     * 加载指定位置的Bean定义
     * @param location 要加载的资源位置
     * @throws BeansException 如果加载Bean定义时发生错误，抛出BeansException
     */
    void loadBeanDefinitions(String location) throws BeansException; // 加载指定位置的Bean定义

}

