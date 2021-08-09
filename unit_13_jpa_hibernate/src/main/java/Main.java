import org.hibernate.Session;
import org.hibernate.SessionFactory;
import services.LessonService;
import utils.HibernateSessionFactoryUtil;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                LessonService lessonService = new LessonService();
                lessonService.printInformationAboutClosestLesson(session, 1L);
            }catch (Exception e){
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }
}