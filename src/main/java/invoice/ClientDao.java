package invoice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientDao {
    public List<Client> getClientBySurName(String surName) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Client.class);
            Root root = criteriaQuery.from(Client.class);

            criteriaQuery.select(root)
                    .where(criteriaBuilder
                            .equal(root.get("surName"), surName));
            return session.createQuery(criteriaQuery).list();

        }
    }

    public Set<Client> getClientById(Long id) {
        Set<Client> clientList = new HashSet<>();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Client.class);
            Root root = criteriaQuery.from(Client.class);

            criteriaQuery.select(root)
                    .where(criteriaBuilder
                            .equal(root.get("id"), id));
            clientList.addAll(session.createQuery(criteriaQuery).list());
        }
        return clientList;
    }
}
