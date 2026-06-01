package com.ch11;

import java.io.*;

// 对象深拷贝 浅拷贝 深拷贝拷贝对象及里面属性值， 浅拷贝只拷贝对象内地址
public class DeepCopyUtil {
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepCopy(T obj) {
        try (
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos)
        ) {
            oos.writeObject(obj);

            try (ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(bos.toByteArray()))) {
                return (T) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("深拷贝失败", e);
        }
    }
}

