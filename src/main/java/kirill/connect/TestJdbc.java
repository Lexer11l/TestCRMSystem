package kirill.connect;

import smth.model.old.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class TestJdbc {
  static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Instructor.class).buildSessionFactory();


  private static void testJdbc() throws ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");

    String link = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
    String user = "hbstudent";
    String pass = "hbstudent";

    try{
      Connection mycon = DriverManager.getConnection(link, user, pass);
      System.out.println(mycon.isClosed());
    }
    catch (Exception e){
      System.out.println(e);
    }
  }

  public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
    //testJdbc();
//    deleteStudent();
    deleteCourse();
  }

  private static void deleteCourse() {
    Session session = factory.getCurrentSession();
    session.beginTransaction();
    Course student = session.get(Course.class, 15);
    session.delete(student);
    session.getTransaction().commit();
  }


  private static void deleteStudent() {
    Session session = factory.getCurrentSession();
    session.beginTransaction();
    Student student = session.get(Student.class, 3);
    session.delete(student);
    session.getTransaction().commit();
  }

  private static void makeSomething7() {
    Session session = factory.getCurrentSession();

    session.beginTransaction();

    Student student = session.get(Student.class, 3);
    System.out.println(student);
    System.out.println(student.getCourses());

    session.getTransaction().commit();

  }

  private static void makeSomething6() {
    Session session = factory.getCurrentSession();

    session.beginTransaction();

    Course course = new Course("3");
    Course course2 = new Course("4");
    Student student = session.get(Student.class, 3);
    student.addCourse(course);
    student.addCourse(course2);
    session.save(course);
    session.save(course2);

    session.getTransaction().commit();
  }

  private static void makeSomething5() {
    Session session = factory.getCurrentSession();

    session.beginTransaction();

    Course course = new Course("2");
    Student student = new Student("1", "1", "1");
    Student student2 = new Student("2", "2", "2");
    session.save(course);
    course.addStudent(student);
    course.addStudent(student2);
    session.save(student);
    session.save(student2);
    session.getTransaction().commit();

  }

  private static void makeSomething4() {
    Session session = factory.getCurrentSession();

    session.beginTransaction();

    // get the instructor from db
    int theId = 1;
    Instructor tempInstructor = session.get(Instructor.class, theId);

    System.out.println("luv2code: Instructor: " + tempInstructor);

    // commit transaction
    session.getTransaction().commit();

    // close the session
    session.close();

    System.out.println("\nluv2code: The session is now closed!\n");

    // option 1: call getter method while session is open

    // get courses for the instructor
    System.out.println("luv2code: Courses: " + tempInstructor.getCourses());

    System.out.println("luv2code: Done!");
    session.close();
  }

  private static void makeSomething3() {
    Session session = factory.getCurrentSession();
    session.beginTransaction();
    Instructor instructor = new Instructor("new","new2", "new3");
    Course instructorDetail = new Course("new course 5");
    instructor.add(instructorDetail);
    session.save(instructor);
    instructorDetail.addReview(new Review("1"));
    session.save(instructorDetail);
    session.getTransaction().commit();
  }

  private static void makeSomething2() {
    Session session = factory.getCurrentSession();
    session.beginTransaction();
    System.out.println(session.get(InstructorDetail.class, 1).getInstructor());

    session.getTransaction().commit();
  }

  private static void makeSomething() {
    Session session = factory.getCurrentSession();
    session.beginTransaction();
    Instructor instructor = new Instructor("1","2", "3");
    InstructorDetail instructorDetail = new InstructorDetail("1","2");
//    instructor.setInstructorDetail(instructorDetail);
    session.save(instructor);
    session.getTransaction().commit();
  }

  private static List<Student> getStudents() {
    Session session = factory.getCurrentSession();
    session.beginTransaction();
    List<Student> studentList = session.createQuery("from Student s where s.lastName='BRO1VI'").getResultList();

    return studentList;
  }

  private static void getObject() throws InterruptedException {
    Session session = factory.getCurrentSession();
    session.beginTransaction();
    Student student1 = session.get(Student.class, 4);

    System.out.println(student1);
    student1.setFirstName("BAZOOK1");
    session.update(student1);
    Thread.sleep(50000);
    student1.setFirstName("BAZOOK");
    session.getTransaction().commit();
  }

  private static Student addObject() {
    Session session = factory.getCurrentSession();


      Student student = new Student("SANYA", "BROVI", "");
      session.beginTransaction();
      session.save(student);
            session.getTransaction().commit();
      return student;



  }

}






