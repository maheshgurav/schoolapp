package com.samarthsoft.prabandhak.services.client;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.NoSuchAlgorithmException;

import com.samarthsoft.prabandhak.services.BackEndService;
import com.samarthsoft.prabandhak.services.BackEndAuthenticationService;
import com.samarthsoft.prabandhak.services.authenticator.BackEndServicesAuthenticationEngine;
import com.samarthsoft.prabandhak.services.encryption.EncryptionUtils;

public class PrabandhakServiceTestClient {
	private static final String hashSaltKey = "fhdkjhfaksjd$gfdjsgk234234vfdslhgsdhg#@$dfsghsjdhgdjlshgklkjsdgkjf";

	private PrabandhakServiceTestClient() {
	}

	public static void main(String args[]) {
		try {
			PrabandhakServiceTestClient serviceTestClient = new PrabandhakServiceTestClient();
			BackEndService service = serviceTestClient.initiateBackEndPrabandhakServicesServer();
			service.updateRoleNumbers("", "", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private BackEndService initiateBackEndPrabandhakServicesServer()
			throws NoSuchAlgorithmException, IOException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(2099);
		BackEndAuthenticationService authEngine = (BackEndAuthenticationService) registry
				.lookup(BackEndServicesAuthenticationEngine.SERVICE_NAME);
		String token = authEngine.generateNewToken();
		String hash = EncryptionUtils.generateSha256Hash(token, hashSaltKey);
		BackEndService service = (BackEndService) authEngine.getBackEndPrabandhakServiceEngine(token, hash);
		return service;
	}
}