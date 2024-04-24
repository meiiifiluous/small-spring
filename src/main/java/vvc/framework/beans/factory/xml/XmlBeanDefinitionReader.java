package vvc.framework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import vvc.framework.beans.BeansException;
import vvc.framework.beans.PropertyValue;
import vvc.framework.beans.factory.config.BeanDefinition;
import vvc.framework.beans.factory.config.BeanReference;
import vvc.framework.beans.factory.support.AbstractBeanDefinitionReader;
import vvc.framework.beans.factory.support.BeanDefinitionRegistry;
import vvc.framework.core.io.Resource;
import vvc.framework.core.io.ResourceLoader;


import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream); // 调用子类的方法加载Bean定义
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource); // 加载每个资源的Bean定义
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader(); // 获取资源加载器
        Resource resource = resourceLoader.getResource(location); // 获取资源
        loadBeanDefinitions(resource); // 加载资源的Bean定义
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream); // 读取XML文档并解析为Document对象
        Element root = doc.getDocumentElement(); // 获取文档的根元素
        NodeList childNodes = root.getChildNodes(); // 获取根元素的子节点列表

        for (int i = 0; i < childNodes.getLength(); i++) {
            if (!(childNodes.item(i) instanceof Element)) continue; // 忽略非Element节点
            if (!"bean".equals(childNodes.item(i).getNodeName())) continue; // 忽略非"bean"元素

            // 解析bean元素
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            Class<?> clazz = Class.forName(className); // 根据类名加载Class对象
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName()); // 默认使用类名的首字母小写作为bean的名称
            }

            // 创建BeanDefinition对象
            BeanDefinition beanDefinition = new BeanDefinition(clazz);

            // 读取并填充bean属性
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;

                // 解析property元素
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue; // 如果存在ref属性，则创建BeanReference对象，否则直接使用属性值
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            // 注册BeanDefinition
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

}

