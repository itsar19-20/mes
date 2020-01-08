package utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static JPAUtil instance;
	
	private EntityManagerFactory entityManagerFactory;

	/**
	 * 
	 */
	private JPAUtil() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("Mes");
	}
	
	public static JPAUtil getInstance() {
		if (instance == null)
			instance = new JPAUtil();
		return instance;
	}

	/**
	 * @return the entityManagerFactory
	 */
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

}
