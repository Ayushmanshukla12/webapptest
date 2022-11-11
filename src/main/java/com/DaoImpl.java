package com;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoImpl implements DaoInterface{

    @Override
    public void doctorinsertion(VetDoctorPojo doctorPojo) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ayushman");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(doctorPojo);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void pateintpetinsertion(PateintPetPojo pateintPetPojo) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ayushman");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(pateintPetPojo);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void booking(VetDoctorPojo doctorPojo) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ayushman");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.merge(doctorPojo);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
