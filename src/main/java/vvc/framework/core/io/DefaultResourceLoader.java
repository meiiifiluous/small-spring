package vvc.framework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 类加载器
 */
public class DefaultResourceLoader implements ResourceLoader{
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null"); // 断言位置不为空

        if (location.startsWith(CLASSPATH_URL_PREFIX)) { // 如果位置以"classpath:"开头
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length())); // 创建ClassPathResource对象
        }
        else {
            try {
                URL url = new URL(location); // 尝试解析URL
                return new UrlResource(url); // 创建UrlResource对象
            } catch (MalformedURLException e) { // 如果解析URL失败
                return new FileSystemResource(location); // 创建FileSystemResource对象
            }
        }
    }

}
