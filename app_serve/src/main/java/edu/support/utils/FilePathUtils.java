package edu.support.utils;

import com.gitee.zhibi.fast.commons.base.Constants;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.util.Date;

/**
 * 文件路径工具类
 *
 * @author 执笔
 * @date  17:36
 */
public class FilePathUtils {

    /**
     * 生成  文件路径
     *
     * @return
     */
    public static String getFilePath(String model, String name) {
        return "/" + Constants.Upload.PREFIX + "/" + model + "/" + DateFormatUtils.format(new Date(), "yyyy-MM-dd") + "/" + name;
    }

    /**
     * 创建文件夹
     * 并返回文件名
     *
     * @param rootPath
     */
    public static String createDir(String rootPath, String model, String name) {
        String fileName = getFilePath(model, name);
        File file = new File(rootPath + fileName);
        file.getParentFile().mkdirs();
        return fileName;
    }
}
