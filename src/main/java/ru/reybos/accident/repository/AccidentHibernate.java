package ru.reybos.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.reybos.accident.model.Accident;
import ru.reybos.accident.model.AccidentType;
import ru.reybos.accident.model.Rule;

import java.util.List;
import java.util.Optional;

@Repository
public class AccidentHibernate {
    private static final Logger LOG = LoggerFactory.getLogger(AccidentHibernate.class.getName());
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident, String[] ruleIds) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(accident);
            session.getTransaction().commit();

            session.beginTransaction();
            Accident accidentDb = session.get(Accident.class, accident.getId());
            for (String rId : ruleIds) {
                Rule rule = session.get(Rule.class, Integer.parseInt(rId));
                accidentDb.addRule(rule);
            }
            session.getTransaction().commit();

            return accident;
        }
    }

    public List<Accident> findAllAccident() {
        try (Session session = sf.openSession()) {
            String sql = "SELECT DISTINCT accident "
                    + "FROM Accident accident "
                    + "LEFT JOIN FETCH accident.rules "
                    + "LEFT JOIN FETCH accident.type "
                    + "ORDER BY accident.id";
            return session
                    .createQuery(sql, Accident.class)
                    .list();
        }
    }

    public Optional<Accident> findById(int id) {
        try (Session session = sf.openSession()) {
            String sql = "SELECT DISTINCT accident "
                    + "FROM Accident accident "
                    + "LEFT JOIN FETCH accident.rules "
                    + "LEFT JOIN FETCH accident.type "
                    + "WHERE accident.id=:id";
            final Query<Accident> query = session.createQuery(sql);
            query.setParameter("id", id);
            Accident accident = query.uniqueResult();
            return Optional.ofNullable(accident);
        }
    }

    public List<AccidentType> findAllAccidentType() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("FROM AccidentType", AccidentType.class)
                    .list();
        }
    }

    public List<Rule> findAllRule() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("FROM Rule", Rule.class)
                    .list();
        }
    }

    public void delete(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            String sql = "DELETE FROM Accident "
                    + "WHERE id=:id";
            final Query query = session.createQuery(sql);
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }
}