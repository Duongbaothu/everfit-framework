package commons;

import lombok.Getter;

import java.io.File;

@Getter
public class GlobalConstants {
    private static GlobalConstants globalInstance;

    private GlobalConstants() {
    }

    public static synchronized GlobalConstants getGlobalConstants() {
        if (globalInstance == null) {
            globalInstance = new GlobalConstants();
        }
        return globalInstance;
    }

    private final long shortTimeout = 5;
    private final long longTimeout = 30;

    private final String osName = System.getProperty("os.name");
    private final String javaVersion = System.getProperty("java.version");

    private final String relativeProjectPath = System.getProperty("user.dir");
    private final String uploadPath = relativeProjectPath + File.separator + "uploadFile" + File.separator;
    private final String downloadPath = relativeProjectPath + File.separator + "downloadFile/";
    private final String environmentConfigPath = relativeProjectPath + File.separator + "environmentConfig" + File.separator;
    private final String DataTestPath = relativeProjectPath + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator;
}
