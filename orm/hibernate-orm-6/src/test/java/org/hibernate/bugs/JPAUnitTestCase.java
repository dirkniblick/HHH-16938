package org.hibernate.bugs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.bugs.entity.PropertyHolder;
import org.hibernate.bugs.entity.StringProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
		entityManager = entityManagerFactory.createEntityManager();
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh16938Test() throws Exception {
		StringProperty nameProperty = new StringProperty();
		nameProperty.setId(2L);
		nameProperty.setName("name");
		nameProperty.setValue("John Doe");
		save(nameProperty);

		StringProperty namePropertyRetrieved1 = entityManager.find(StringProperty.class, 2L);
		StringProperty namePropertyRetrieved2 = retrieve(StringProperty.class, 2L);

		PropertyHolder namePropertyHolder = new PropertyHolder();
		namePropertyHolder.setId(1L);
		namePropertyHolder.setProperty(nameProperty);
		save(namePropertyHolder);

		PropertyHolder propertyHolderRtrieved1 = entityManager.find(PropertyHolder.class, 1L);
		PropertyHolder propertyHolderRtrieved2 = retrieve(PropertyHolder.class, 1L);

		entityManager.close();
	}

	void save(Object object) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(object);
		transaction.commit();
	}

	<T> T retrieve(Class<T> entityType, Object id) {
		EntityManager newEntityManager = entityManagerFactory.createEntityManager();
		return newEntityManager.find(entityType, id);
	}
}
