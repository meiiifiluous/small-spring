package vvc.framework.core.io;

import cn.hutool.core.lang.Assert;
import vvc.framework.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource{
    private final String path; // 资源路径

    private ClassLoader classLoader; // 类加载器

    /**
     * 构造函数，使用默认类加载器加载资源
     * @param path 资源路径
     */
    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    /**
     * 构造函数，指定类加载器加载资源
     * @param path 资源路径
     * @param classLoader 类加载器
     */
    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null"); // 断言路径不为空
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader()); // 设置类加载器
    }

    /**
     * 获取资源的输入流
     * @return 资源的输入流
     * @throws IOException 如果资源不存在或无法打开，抛出IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path); // 获取资源输入流
        if (is == null) {
            throw new FileNotFoundException(
                    this.path + " cannot be opened because it does not exist"); // 如果资源不存在，抛出FileNotFoundException
        }
        return is; // 返回资源输入流
    }
}
