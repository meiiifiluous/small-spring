package vvc.framework.beans.factory.support;

import vvc.framework.core.io.DefaultResourceLoader;
import vvc.framework.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry; // 向bean容器注册

    private ResourceLoader resourceLoader; // 资源加载器

    /**
     * 构造函数，使用给定的BeanDefinition注册表和默认资源加载器创建AbstractBeanDefinitionReader
     * @param registry BeanDefinition注册表
     */
    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader()); // 调用另一个构造函数，默认使用DefaultResourceLoader作为资源加载器
    }

    /**
     * 构造函数，使用给定的BeanDefinition注册表和资源加载器创建AbstractBeanDefinitionReader
     * @param registry BeanDefinition注册表
     * @param resourceLoader 资源加载器
     */
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry; // 设置BeanDefinition注册表
        this.resourceLoader = resourceLoader; // 设置资源加载器
    }

    /**
     * 获取BeanDefinition注册表
     * @return BeanDefinition注册表
     */
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry; // 返回BeanDefinition注册表
    }

    /**
     * 获取资源加载器
     * @return 资源加载器
     */
    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader; // 返回资源加载器
    }

}

