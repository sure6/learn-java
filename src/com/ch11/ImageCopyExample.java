package com.ch11;
/**
 * 案例2：Java文件字节流复制图片案例
 *
 * 字节流以字节方式读取数据，不能很好操作Unicode字符， 如汉字在文件中占用2个字节，如果用字节流可能会出现乱码
 * 因此字符流处理字符是最后的选择
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageCopyExample {
    public static void main(String[] args) {
        // 源图片路径
        String sourcePath = "D:\\idea-workspaces\\learn-java\\123.jpg";
        // 目标图片路径
        String targetPath = "D:\\idea-workspaces\\learn-java\\456.jpg";

        // 声明文件输入流和输出流
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            // 1. 创建文件输入流，读取源文件
            fis = new FileInputStream(sourcePath);

            // 2. 创建文件输出流，写入目标文件
            fos = new FileOutputStream(targetPath);

            // 3. 创建缓冲区，提高读写效率
            byte[] buffer = new byte[1024];
            int length;

            // 4. 循环读取并写入文件
            while ((length = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }

            System.out.println("图片复制完成！");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5. 关闭流资源
            try {
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

