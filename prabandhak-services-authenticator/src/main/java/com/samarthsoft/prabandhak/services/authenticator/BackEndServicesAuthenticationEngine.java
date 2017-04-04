package com.samarthsoft.prabandhak.services.authenticator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samarthsoft.prabandhak.services.BackEndService;
import com.samarthsoft.prabandhak.services.BackEndAuthenticationService;
import com.samarthsoft.prabandhak.services.encryption.EncryptionUtils;
import com.samarthsoft.prabandhak.services.server.BackEndPrabandhakServicesServer;

public class BackEndServicesAuthenticationEngine implements BackEndAuthenticationService {
	
	private static final int         TOKEN_VALIDITY_TIME_IN_MINUTES = 3;
    private static Map<String, Date> tokens                         = new HashMap<String, Date>();
    private static final String      hashSaltKey                    = "fhdkjhfaksjd$gfdjsgk234234vfdslhgsdhg#@$dfsghsjdhgdjlshgklkjsdgkjf";
    private static final Logger      log                            = LoggerFactory.getLogger(BackEndServicesAuthenticationEngine.class);
    private final Lock               lock                           = new ReentrantLock();

	public BackEndService getBackEndPrabandhakServiceEngine(
			String token, String tokenHash) throws RemoteException {
        removeExpiredTokens();
        if (tokens.containsKey(token)) {
            log.debug("token is valid");
            this.lock.lock();
            try {
                tokens.remove(token);
            }
            catch(Exception e) {
                log.error("", e);
            }
            finally {
                this.lock.unlock();
            }

            if (isAuthenticationValid(token, tokenHash)) {
                BackEndService service = new BackEndPrabandhakServicesServer();
                return (BackEndService) UnicastRemoteObject.exportObject(service, Declarations.PORT_OBJECT_SERVICE);
            }
        }
        return null;
    }

    public synchronized String generateNewToken() throws RemoteException {
        UUID uid = UUID.randomUUID();
        String token = uid.toString();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, TOKEN_VALIDITY_TIME_IN_MINUTES);
        Date validUpto = cal.getTime();

        this.lock.lock();
        try {
            tokens.put(token, validUpto);
        }
        catch(Exception e) {
            log.error("", e);
        }
        finally {
            this.lock.unlock();
        }
        return token;
    }

    private synchronized void removeExpiredTokens() {
        this.lock.lock();
        try {
            List<String> tokensToRemove = new ArrayList<String>();
            for (Map.Entry<String, Date> entry : tokens.entrySet()) {
                Date validUpto = entry.getValue();
                if (validUpto.compareTo(new Date()) < 0)
                    tokensToRemove.add(entry.getKey());
            }

            for (String token : tokensToRemove)
                tokens.remove(token);
        }
        catch(Exception e) {
            log.error("", e);
        }
        finally {
            this.lock.unlock();
        }
    }

    private boolean isAuthenticationValid(String token, String tokenHashFromClient) {
        try {
            String newHash = EncryptionUtils.generateSha256Hash(token, hashSaltKey);
            boolean result = EncryptionUtils.isHashSame(newHash, tokenHashFromClient);
            return result;
        }
        catch(Exception e) {
            log.error("", e);
        }
        return false;
    }
}