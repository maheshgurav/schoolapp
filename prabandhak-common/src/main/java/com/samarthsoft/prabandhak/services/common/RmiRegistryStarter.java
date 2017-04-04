package com.samarthsoft.prabandhak.services.common;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RmiRegistryStarter extends Thread {

    private static final Logger log             = LoggerFactory.getLogger(RmiRegistryStarter.class);
    private boolean             registryStarted = false;
    private int                 port            = 1099;

    public RmiRegistryStarter(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            this.registryStarted = false;
            log.debug("starting RMI registry");
            java.rmi.registry.LocateRegistry.createRegistry(this.port);
            log.debug("started RMI registry");
            this.registryStarted = true;
        }
        catch(RemoteException e) {
            log.error("", e);
        }
    }

    public boolean isRegistryStarted() {
        return this.registryStarted;
    }

    public static void startRmiRegistry(int port) {
        try {
            RmiRegistryStarter starter = new RmiRegistryStarter(port);
            starter.start();

            while (!starter.isRegistryStarted())
                Thread.sleep(1000);
        }
        catch(InterruptedException e) {
            log.error("", e);
        }
    }
}
