package vvc.framework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 处理资源加载流,提供获取 InputStream 流的方法
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}

