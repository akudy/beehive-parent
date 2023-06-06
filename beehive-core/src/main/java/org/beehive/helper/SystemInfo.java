/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.helper;

/**
 * 系统信息辅助类。
 * <br>
 * 主要用于定义Java环境获取到的系统级参数
 *
 * @author akudy
 * @version 1.0
 * @see System#getProperties()
 * @since 1.0
 */
public class SystemInfo {

    /**
     * 私有化构造函数，禁止实例化
     */
    private SystemInfo() {
    }

    public static final class HARDWARE {
        /**
         * 私有化构造函数，禁止实例化
         */
        private HARDWARE() {
        }

        /**
         * 当前CPU的字节类型，例如：little
         */
        public static final String CPU_ENDIAN = System.getProperty("sun.cpu.endian");

        /**
         * 当前CPU信息，例如：amd64
         */
        public static final String CPU_ISALIST = System.getProperty("sun.cpu.isalist");

    }

    /**
     * 操作系统相关信息
     */
    public static final class OS {

        /**
         * 私有化构造函数，禁止实例化
         */
        private OS() {
        }

        /**
         * 行分隔符，默认为：\r\n
         */
        public static final String LINE_SEPARATOR = System.getProperty("line.separator", "\r\n");

        /**
         * 文件分隔符，默认为：\
         */
        public static final String FILE_SEPARATOR = System.getProperty("file.separator", "\\");

        /**
         * 多路径分隔符，默认为：;
         */
        public static final String PATH_SEPARATOR = System.getProperty("path.separator", ";");

        /**
         * 系统架构，例如：amd64
         */
        public static final String ARCH = System.getProperty("os.arch");

        /**
         * 操作系统名称，例如：Windows 10
         */
        public static final String NAME = System.getProperty("os.name");

        /**
         * 操作系统版本，例如：10.0
         */
        public static final String VERSION = System.getProperty("os.version");

        /**
         * 当前操作系统的编码，例如：GBK
         */
        public static final String ENCODING = System.getProperty("sun.jnu.encoding");

        /**
         * 操作系统GUI信息，例如：windows
         */
        public static final String GUI = System.getProperty("sun.desktop");

    }

    /**
     * Java运行环境相关信息
     */
    public static final class JAVA {

        /**
         * 私有化构造函数，禁止实例化
         */
        private JAVA() {
        }

        /**
         * Java Class头版本号，例如：52.0
         */
        public static final String CLASS_VERSION = System.getProperty("java.class.version");

        /**
         * Java规范版本号，例如:1.8
         *
         * @see #VERSION
         */
        public static final String SPECIFICATION_VERSION = System.getProperty("java.specification.version");

        /**
         * Java虚拟机规范版本号，例如:1.8
         *
         * @see #SPECIFICATION_VERSION
         */
        public static final String VM_SPECIFICATION_VERSION = System.getProperty("java.vm.specification.version");

        /**
         * Java版本号，例如：1.8.0_172
         *
         * @see #SPECIFICATION_VERSION
         * @see #RUNTIME_VERSION
         */
        public static final String VERSION = System.getProperty("java.version");

        /**
         * Java运行环境版本号，例如：1.8.0_172-b11
         *
         * @see #VERSION
         */
        public static final String RUNTIME_VERSION = System.getProperty("java.runtime.version");

        /**
         * Java编译器信息，例如：HotSpot 64-Bit Tiered Compilers
         */
        public static final String COMPILER = System.getProperty("sun.management.compiler");

        /**
         * Java运行环境（JRE）的主目录（环境目录），例如：C:\Program Files\Java\jdk1.8.0_172\jre
         *
         * @see #RUNTIME_ROOT_PATH
         */
        public static final String HOME = System.getProperty("java.home");

        /**
         * Java运行环境（JRE）根目录（启动目录），例如：C:\Program Files\Java\jdk1.8.0_172\jre\bin
         *
         * @see #HOME
         * @see #RUNTIME_ROOT_CLASS_PATH
         * @see #LIBRARY_PATH
         */
        public static final String RUNTIME_ROOT_PATH = System.getProperty("sun.boot.library.path");

        /**
         * Java运行环境启动的类路径，例如：C:\Program Files\Java\jdk1.8.0_172\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\rt.jar;...
         *
         * @see #RUNTIME_ROOT_PATH
         * @see #CLASS_PATH
         */
        public static final String RUNTIME_ROOT_CLASS_PATH = System.getProperty("sun.boot.class.path");

        /**
         * Java 类库路径，例如：C:\Program Files\Java\jdk1.8.0_172\bin;...
         *
         * @see #RUNTIME_ROOT_PATH
         * @see #CLASS_PATH
         */
        public static final String LIBRARY_PATH = System.getProperty("java.library.path");

        /**
         * Java应用程序的类路径，例如：C:\Program Files\Java\jdk1.8.0_172\lib;...
         *
         * @see #RUNTIME_ROOT_CLASS_PATH
         * @see #LIBRARY_PATH
         */
        public static final String CLASS_PATH = System.getProperty("java.class.path");

        /**
         * Java应用程序扩展类路径，例如：C:\Program Files\Java\jdk1.8.0_172\jre\lib\ext;...
         */
        public static final String EXTEND_CLASS_PATH = System.getProperty("java.ext.dirs");

        /**
         * Java AWT&GUI绘图环境类，例如：sun.awt.Win32GraphicsEnvironment
         */
        public static final String GUI_GRAPHICS_CLASS = System.getProperty("java.awt.graphicsenv");

        /**
         * Java AWT&GUI打印环境类，例如：sun.awt.windows.WPrinterJob
         */
        public static final String GUI_PRINTER_CLASS = System.getProperty("java.awt.printerjob");

        /**
         * Java AWT&GUI工具环境类，例如：sun.awt.windows.WToolkit
         */
        public static final String GUI_TOOLKIT_CLASS = System.getProperty("awt.toolkit");

        /**
         * Java 运行时的数据位数，例如：64
         *
         * @see OS#ARCH
         */
        public static final String ARCH_DATA_BITS = System.getProperty("sun.arch.data.model");

        /**
         * Java文件编码格式，默认为：UTF-8
         *
         * @see OS#ENCODING
         * @see #IO_UNICODE_ENCODING
         */
        public static final String FILE_ENCODING = System.getProperty("file.encoding", "UTF-8");

        /**
         * IO中Unicode字符编码类型，例如：UnicodeLittle
         *
         * @see OS#ENCODING
         * @see #FILE_ENCODING
         */
        public static final String IO_UNICODE_ENCODING = System.getProperty("sun.io.unicode.encoding");

        /**
         * 当前运行的Java命令行信息，例如：
         * 第一种：com.intellij.rt.execution.junit.JUnitStarter -ideVersion5 -junit4 org.beehive.util.test.SystemUtilsTest,testInfo
         * 第二种：org.beehive.util.SystemUtils
         */
        public static final String CURRENT_RUN_INFO = System.getProperty("sun.java.command");

    }

    /**
     * 用户相关信息
     */
    public static final class USER {

        /**
         * 私有化构造函数，禁止实例化
         */
        private USER() {
        }

        /**
         * 当前所在国家地区代码，默认为：CN
         */
        public static final String COUNTRY = System.getProperty("user.country", "CN");

        /**
         * 当前所在时区，默认为：东八区（GMT+8:00）
         */
        public static final String TIMEZONE = System.getProperty("user.timezone", "GMT+8:00");

        /**
         * 当前语言代码，默认：zh
         */
        public static final String LANGUAGE = System.getProperty("user.language", "zh");

        /**
         * 用户临时目录，例如：C:\Users\xx\AppData\Local\Temp\
         */
        public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

        /**
         * 用户根目录，例如：C:\Users\xx
         */
        public static final String HOME_DIR = System.getProperty("user.home");

        /**
         * 用户名称，例如：xx
         */
        public static final String NAME = System.getProperty("user.name");

    }

}
