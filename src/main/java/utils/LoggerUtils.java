package utils;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import java.io.File;

import static org.apache.log4j.LogManager.getRootLogger;

public class LoggerUtils {

    private static final int MAX_BACKUP_INDEX = 10;
    public static final String CONVERSION_PATTERN = "%d{MM-dd-yyyy HH:mm:ss} %F %-5p [%t] %c{2} %L - %m%n";
    public static final String ENCODING = "UTF-8";

    public static void addFileAppender(File file){
        RollingFileAppender apiLog = new RollingFileAppender();
        apiLog.setName("APILOG");
        apiLog.setFile(file.getAbsolutePath());
        apiLog.setEncoding(ENCODING);
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern(CONVERSION_PATTERN);
        apiLog.setLayout(patternLayout);
        apiLog.setImmediateFlush(true);
        apiLog.setMaxBackupIndex(MAX_BACKUP_INDEX);
        apiLog.activateOptions();
        getRootLogger().addAppender(apiLog);
    }
}
