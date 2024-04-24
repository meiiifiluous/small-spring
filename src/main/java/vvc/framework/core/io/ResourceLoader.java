package vvc.framework.core.io;

/**
 * 类加载器
 */
public interface ResourceLoader{
    /**
     * 用于从类路径加载的伪URL前缀："classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:"; // 类路径的伪URL前缀

    /**
     * 根据给定的位置（location）获取资源（Resource）
     * @param location 资源的位置
     * @return 表示资源的Resource对象
     */
    Resource getResource(String location); // 根据给定的位置获取资源
}
