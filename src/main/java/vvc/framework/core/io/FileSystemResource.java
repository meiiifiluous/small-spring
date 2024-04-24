package vvc.framework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemResource implements Resource{
    private final File file; // 文件对象，表示资源所对应的文件

    private final String path; // 资源的路径

    /**
     * 构造函数，使用给定的文件对象创建FileSystemResource
     * @param file 资源文件对象
     */
    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath(); // 获取文件路径并存储
    }

    /**
     * 构造函数，使用给定的路径创建FileSystemResource
     * @param path 资源路径
     */
    public FileSystemResource(String path) {
        this.file = new File(path); // 根据路径创建文件对象
        this.path = path; // 存储路径
    }

    /**
     * 获取资源的输入流
     * @return 资源的输入流
     * @throws IOException 如果无法获取输入流，抛出IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file); // 返回资源文件的输入流
    }

    /**
     * 获取资源的路径
     * @return 资源的路径
     */
    public final String getPath() {
        return this.path; // 返回资源的路径
    }

}
