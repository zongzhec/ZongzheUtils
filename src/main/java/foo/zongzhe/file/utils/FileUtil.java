package foo.zongzhe.file.utils;

import foo.zongzhe.common.utils.LogUtil;

import java.io.File;

/**
 * Utils used for common file process
 */

public class FileUtil {

    public static boolean fileExists(File file) {
        boolean fileExists = false;
        if (null == file) {
            LogUtil.printInfo("File does not exist! " + file.getName());
            fileExists = false;
        }
        return fileExists;
    }
}
