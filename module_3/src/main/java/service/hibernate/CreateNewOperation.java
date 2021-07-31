package service.hibernate;

import models.Balance;
import models.Operation;
import models.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.Instant;

public class CreateNewOperation {
    public void addOperation(Long userId, String username, String password, Long amount, String description, Instant timestamp, Long balanceId){
        Configuration conf = new Configuration().configure()
                .setProperty("hibernate.connection.username", username)
                .setProperty("hibernate.connection.password", password);
        try(SessionFactory sessionFactory = conf.buildSessionFactory()) {
            EntityManager entityManager = sessionFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            try {
                User user = entityManager.find(User.class, userId);
                TypedQuery<Balance> query = entityManager.createQuery("select a from Balance a where a.id=:balanceId and a.user=:user", Balance.class);
                query.setParameter("balanceId", balanceId);
                query.setParameter("user", user);
                Balance balance = query.getSingleResult();
                Operation operation = new Operation();
                operation.setBalance(balance);
                operation.setAmount(amount);
                operation.setDescription(description);
                operation.setTimestamp(timestamp);
                entityManager.persist(operation);
                transaction.commit();
            } catch (RuntimeException exception) {
                transaction.rollback();
                throw exception;
            }
        }
    }
}