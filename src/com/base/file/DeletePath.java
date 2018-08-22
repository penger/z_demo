package com.base.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by gongp on 2018/8/22.
 */
public class DeletePath {
    public static void main(String[] args) {
        try {
            FileUtils.deleteDirectory(new File("d://work/output"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
