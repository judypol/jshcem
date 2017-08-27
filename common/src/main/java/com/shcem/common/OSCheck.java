package com.shcem.common;

import java.io.File;

/**
 * 判断操作系统类型
 */
public class OSCheck {
    /**
     * 判断是否是windows系统
     */
    public static boolean isWindows() {
        String osname = System.getProperty("os.name").toLowerCase();
        if (osname.startsWith("win"))
            return true;
        return false;
    }

    /**
     * 判断是否是linux系统
     */
    public static boolean isLinux() {
        String osname = System.getProperty("os.name").toLowerCase();
        if (osname.startsWith("linux"))
            return true;
        return false;
    }
}
