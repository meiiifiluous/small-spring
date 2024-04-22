package vvc.framework.beans.factory;

import vvc.framework.beans.BeansException;
import vvc.framework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public interface BeanFactory {
        Object getBean(String name) throws BeansException;
        Object getBean(String name, Object... args) throws BeansException;
}
