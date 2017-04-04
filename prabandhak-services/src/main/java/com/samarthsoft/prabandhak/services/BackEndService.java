package com.samarthsoft.prabandhak.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BackEndService extends Remote{
	void updateRoleNumbers(String classGuid,String schoolGuid,String societyGuid) throws RemoteException;
}
