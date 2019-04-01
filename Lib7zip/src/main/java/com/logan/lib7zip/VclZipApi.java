package com.logan.lib7zip;

/**
 * 压缩解压缩工具类
 */
public class VclZipApi {
    /**
     * 0	No error
     * 1	Warning (Non fatal error(s)). For example, one or more files
     *      were locked by some other application, so they were not compressed.
     * 2	Fatal error
     * 7	Command line error
     * 8	Not enough memory for operation
     * 255	User stopped the process
     */
    public static final int EXIT_OK = 0;
    public static final int EXIT_WARNING = 1;
    public static final int EXIT_FATAL = 2;
    public static final int EXIT_CMD_ERROR = 7;
    public static final int EXIT_MEMORY_ERROR = 8;
    public static final int EXIT_NOT_SUPPORT = 255;

    static {
        System.loadLibrary("p7zip");
    }
    public static native String get7zVersionInfo();

    private static native int executeCommand(String command);

    /**
     * 解压缩
     * @param archivePath 需要解压的文件
     * @param outPath 解压后的文件夹
     * @return
     */
    public static int extractFile(String archivePath, String outPath) {
        return executeCommand(String.format("7z x '%s' '-o%s' -aoa", archivePath, outPath));
    }


    /**
     * 压缩
     * type = {"rar", "zip", "7z", "bz2", "bzip2","tbz2", "tbz", "gz", "gzip", "tgz", "tar", "xz", "txz"};
     * @param filePath 需要压缩的文件
     * @param outPath 压缩后的输出文件
     * @param type
     * @return
     */
    public static int compressFile(String filePath, String outPath, String type) {
        return executeCommand(String.format("7z a -t%s '%s' '%s'", type, outPath, filePath));
    }

}
