package com.ch11;


import java.io.File;
import java.io.FilenameFilter;

public class FileAccpet implements FilenameFilter {

    private String extendName;
    public void setExtendName(String s) {
        this.extendName = "."+s;
    }
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extendName);
    }
}
