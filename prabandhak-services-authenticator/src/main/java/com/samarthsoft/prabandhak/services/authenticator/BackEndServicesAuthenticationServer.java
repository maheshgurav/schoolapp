package com.samarthsoft.prabandhak.services.authenticator;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.samarthsoft.prabandhak.services.BackEndAuthenticationService;
import com.samarthsoft.prabandhak.services.common.RmiRegistryStarter;
import com.samarthsoft.prabandhak.services.common.RmiStarter;
import com.samarthsoft.prabandhak.services.server.BackEndPrabandhakServicesServer;

public class BackEndServicesAuthenticationServer  extends RmiStarter {


    public BackEndServicesAuthenticationServer() {
        super(BackEndPrabandhakServicesServer.class);
    }

    private void startServer() {
            RmiRegistryStarter.startRmiRegistry(Declarations.PORT_RMI_REGISTRY);
            startBackgroundThreads();
            startBackgroudPrabandhakServices();
    }

    private void startBackgroundThreads() {
    }

    private void startBackgroudPrabandhakServices() {
        try {
            BackEndServicesAuthenticationEngine engine = new BackEndServicesAuthenticationEngine();
            BackEndAuthenticationService engineStub = (BackEndAuthenticationService) UnicastRemoteObject.exportObject(engine, Declarations.PORT_OBJECT_AUTHENTICATOR);

            Registry registry = LocateRegistry.getRegistry(Declarations.PORT_RMI_REGISTRY);
            registry.rebind(BackEndAuthenticationService.SERVICE_NAME, engineStub);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
    }

    public static void main(String[] args) throws RemoteException {
        new BackEndServicesAuthenticationServer();
    }

    @Override
    public void doCustomRmiHandling() {
        startServer();
    }
}