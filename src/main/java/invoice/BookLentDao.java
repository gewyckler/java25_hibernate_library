package invoice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookLentDao {

    public List<Book> getBooksLentByClient(Long id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(BookLent.class);
            Root root = criteriaQuery.from(BookLent.class);

            criteriaQuery.select(root)
                    .where(criteriaBuilder.equal(root.get("client"), id));
            return session.createQuery(criteriaQuery).list();
        }
    }
}