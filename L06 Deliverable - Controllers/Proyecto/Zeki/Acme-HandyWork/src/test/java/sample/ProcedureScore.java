
package sample;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.jpa.HibernatePersistenceProvider;

public class ProcedureScore {

	public static void main(final String[] args) {
		final HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
		final EntityManagerFactory entityManagerFactory = provider.createEntityManagerFactory("Acme-HandyWork", null);
		final EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		Query query;
		query = em.createNativeQuery("{CALL scoreNormalised(?, ?, ?)}");
		query.setParameter(1, "3078");
		System.out.println(query.getParameterValue(2));
		System.out.println(query.getParameterValue(3));
		em.getTransaction().commit();
		em.close();
	}

}
