package vvc.framework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class UrlResource implements Resource{
    private final URL url; // URL对象，表示资源的统一资源定位符

    /**
     * 构造函数，使用给定的URL创建UrlResource
     * @param url 资源的URL
     */
    public UrlResource(URL url) {
        Assert.notNull(url,"URL must not be null"); // 断言URL不为空
        this.url = url; // 存储URL对象
    }

    /**
     * 获取资源的输入流
     * @return 资源的输入流
     * @throws IOException 如果无法获取输入流，抛出IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection(); // 打开URL连接
        try {
            return con.getInputStream(); // 获取URL连接的输入流
        }
        catch (IOException ex){
            if (con instanceof HttpURLConnection){ // 如果连接是HttpURLConnection类型
                ((HttpURLConnection) con).disconnect(); // 断开连接
            }
            throw ex; // 抛出异常
        }
    }
}
