package com.samarthsoft.prabandhak.services.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PolicyFileLocator {

    public static String getLocationOfPolicyFile() {
        try {
            File tempFile = File.createTempFile("rmi-base", ".policy");
            //InputStream is = new FileInputStream(new File(policyFileName));
            //InputStream is = PolicyFileLocator.class.getResourceAsStream(policyFileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            //while ((read = is.read()) != -1) {
            writer.write("grant{permission java.security.AllPermission;};");//allow all permissions to all wizex rmi services
            //}
            writer.close();
            tempFile.deleteOnExit();
            return tempFile.getAbsolutePath();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
