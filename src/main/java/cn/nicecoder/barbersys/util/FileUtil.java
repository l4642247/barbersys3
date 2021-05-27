package cn.nicecoder.barbersys.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 文件工具
 * @author: xxxxx
 * @date: 2021/5/26 下午3:57
 */
public class FileUtil {

    /**
     * 判断是否超过限制
     *
     * @param :multipartFile:上传的文件
     * @param size: 限制大小
     * @param unit:限制单位（B,K,M,G)
     * @return boolean:是否大于
     */
    public static boolean checkFileSize(MultipartFile multipartFile, int size, String unit) {
        //上传文件的大小, 单位为字节.
        long len = multipartFile.getSize();
        //准备接收换算后文件大小的容器
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        //如果上传文件大于限定的容量
        if (fileSize > size) {
            return true;
        }
        return false;
    }

}
