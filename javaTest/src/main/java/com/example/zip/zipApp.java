package com.example.zip;

import cn.hutool.core.util.ZipUtil;

public class zipApp {

    public static void main(String[] args) {
        String rootDir = System.getProperty("java.class.path").split(";")[0] + "/com/example/"; // target/classes

        // package
        ZipUtil.zip(rootDir + "zip", rootDir + "zip.zip");
        // unpackage
        ZipUtil.unzip(rootDir + "zip.zip", rootDir + "zip1");

    }

}
