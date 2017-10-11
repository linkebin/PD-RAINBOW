package com.yidusoft.utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by smy on 2017/9/16.
 */
public class FileUtil {

    public static Data uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
        Data src = new Data();
        src.setSrc(fileName);
        return src;
    }
}
