package com.samarthsoft.prabandhak.services.common;

public abstract class RmiStarter {

    /**
     * 
     * @param clazzToAddToServerCodebase a class that should be in the java.rmi.server.codebase property.
     */
    public RmiStarter(Class<?> clazzToAddToServerCodebase) {

        System.setProperty("java.rmi.server.codebase", clazzToAddToServerCodebase
                .getProtectionDomain().getCodeSource().getLocation().toString());

        System.setProperty("java.security.policy", PolicyFileLocator.getLocationOfPolicyFile());

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        doCustomRmiHandling();
    }

    /**
     * extend this class and do RMI handling here
     */
    public abstract void doCustomRmiHandling();

}