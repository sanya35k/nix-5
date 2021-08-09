package services;

import dao.LessonDAO;
import dao.StudentDAO;
import models.Lesson;
import models.Student;
import models.Teacher;
import models.Topic;
import org.hibernate.Session;

public class LessonService {
    public LessonDAO lessonDAO = new LessonDAO();
    public StudentDAO studentDAO = new StudentDAO();

    public void printInformationAboutClosestLesson(Session session, Long studentId){
        Student studentById = studentDAO.getStudentById(session, studentId);
        Lesson closestLesson = lessonDAO.getLessonByStudentId(session, studentId);
        Topic topic = closestLesson.getTopic();
        Teacher teacher = closestLesson.getTopic().getTeacher();
        System.out.printf("Student %s %s", studentById.getFirstName(), studentById.getLastName());
        System.out.printf("Closest lesson is %s starts at %s", closestLesson.getName(), closestLesson.getDateTime());
        System.out.printf("Teacher: %s %s, Topic: %s", teacher.getFirstName(), teacher.getLastName(), topic.getTopicName());
    }
}