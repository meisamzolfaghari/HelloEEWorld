package ir.javaland.projects.config;

import ir.javaland.projects.model.Employee;
import ir.javaland.projects.model.User;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static Session session;

    static {
        session = new Configuration().configure()
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory().openSession();
    }

    public static Session getSession() {
        return session;
    }
}
