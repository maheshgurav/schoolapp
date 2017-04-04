package com.samarthsoft.prabandhak.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BackEndAuthenticationService extends Remote{
    String SERVICE_NAME = "BackEndService";

    BackEndService getBackEndPrabandhakServiceEngine(String token, String tokenHash) throws RemoteException;

    String generateNewToken() throws RemoteException;
}
