package dynamicLocator;

import java.io.File;

public class SystemInformation {

    public static void main(String[] args) {
        String osName = System.getProperty("os.name");

        String projectPath = System.getProperty("user.dir");

        String separtor =  System.getProperty("file.separator");

        String brightSide = projectPath + File.separator + "uploadFiles" + File.separator + "brightside.jpg";
    }
}
