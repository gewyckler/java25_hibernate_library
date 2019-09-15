package invoice;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
    public List<Author> getAuthorBySurName(String surName) {
//        List<Author> authorList = new ArrayList<>();
        SessionFactory factory = HibernateUtil.getSessionFactory();

        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Author.class);
            Root root = criteriaQuery.from(Author.class);

            criteriaQuery.select(root)
                    .where(criteriaBuilder
                            .equal(root.get("surName"), surName));

//            authorList.addAll(session.createQuery(criteriaQuery).list());

            return session.createQuery(criteriaQuery).list();
        }
//        return authorList;
    }
}
