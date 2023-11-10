package com.generation;

import com.generation.model.Course;
import com.generation.model.Module;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main( String[] args )
        throws ParseException
    {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();

        // Register new student, muthu
        Student muthu = new Student( "stu04", "Muthu Patel", "muthupatel@yahoomail.com", new Date(103,6,28)); // 2003 July 28
        studentService.subscribeStudent( muthu );

        // Initialize 2 courses for Muthu
        Module module = new Module( "INTRO-CS", "Introduction to Computer Science",
                "Introductory module for the generation technical programs" );
        Course introCS1 =  new Course( "INTRO-CS-1", "Introduction to Computer Science", 9, module );
        Course introCS7 =  new Course(  "INTRO-CS-7", "Agile Software Development with SCRUM", 9, module );

        // Enroll Muthu into 2 courses, INTRO-CS-1 & INTRO-CS-7
        courseService.enrollStudent( "INTRO-CS-1", muthu );
        studentService.enrollToCourse( "stu04", introCS1 );
        courseService.enrollStudent( "INTRO-CS-7", muthu );
        studentService.enrollToCourse( "stu04", introCS7 );

        // testing isCourseApproved method in student.java
        // return true as Muthu is enrolled into INTRO-CS-1
        System.out.println("Is the course INTRO-CS-1 approved for Muthu? " + muthu.isCourseApproved("INTRO-CS-1"));
        // return false as Muthu is not enrolled into INTRO-CS-2
        System.out.println("Is the course INTRO-CS-2 approved for Muthu? " + muthu.isCourseApproved("INTRO-CS-2"));

        // Grade Muthu in the 2 courses
        courseService.gradeStudentInCourse(muthu, "INTRO-CS-1", 56);
        muthu.gradeCourseForStudent("INTRO-CS-1", 56);
        courseService.gradeStudentInCourse(muthu, "INTRO-CS-7", 81);
        muthu.gradeCourseForStudent("INTRO-CS-7", 81);
        System.out.println();

        // Find Muthu's average grade in the 2 courses
        System.out.println("Muthu's average grade is: " + muthu.getAverage());

        // Muthu failed course introCS1, so return null
        System.out.println("Muthu's pass status for the course INTRO-CS-1: "+ muthu.findPassedCourses(introCS1));

        // Muthu passed course introCS7, so return the course
        System.out.println("Muthu passed the course: " + muthu.findPassedCourses(introCS7));

        System.out.println();
        //-------------------------------------------------------------------------------------------------------------------
        // Start of menu

        Scanner scanner = new Scanner( System.in );
        int option = 0;
        do
        {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch ( option )
            {
                case 1:
                    registerStudent( studentService, scanner );
                    break;
                case 2:
                    findStudent( studentService, scanner );
                    break;
                case 3:
                    gradeStudent( studentService, courseService, scanner );
                    break;
                case 4:
                    enrollStudentToCourse( studentService, courseService, scanner );
                    break;
                case 5:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 6:
                    showCoursesSummary( courseService, scanner );
                    break;
                case 7:
                    showCourseAverageGrade( courseService, scanner );
                    break;
                default:
                    System.out.println("Key in digit 1-8 only");
                    break;
            }
        }
        while ( option != 8 );
    }

    private static void registerStudent( StudentService studentService, Scanner scanner )
            throws ParseException
    {
        Student student = PrinterHelper.createStudentMenu( scanner );
        studentService.subscribeStudent( student );
    }

    private static void findStudent( StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );
        }
        else
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
        }
    }

    private static void gradeStudent( StudentService studentService,  CourseService courseService, Scanner scanner )
    {
        System.out.println( "Insert course ID" );
        String courseId = scanner.next();

        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }

        courseService.showEnrolledStudents(courseId);

        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        boolean isAttendCourse = student.isAttendingCourse(courseId);
        if ( !isAttendCourse )
        {
            System.out.println( "Incorrect student ID. Please refer to the list above." );
            return;
        }

        System.out.println( "Insert/Update student's grade in the course" );
        int grade = scanner.nextInt();

        courseService.gradeStudentInCourse(student, courseId, grade);
        student.gradeCourseForStudent(courseId, grade);
    }

    private static void enrollStudentToCourse( StudentService studentService, CourseService courseService,
                                               Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println( student );
        System.out.println( "Insert course ID" );
        String courseId = scanner.next();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }

        for (Course studentCourse :student.getApprovedCourses()) {
            if (studentCourse.getName().equals(course.getName())) {
                System.out.println( "Student is enrolled into the course already." );
                return;
            }
        }

        System.out.println( course );
        courseService.enrollStudent( courseId, student );
        studentService.enrollToCourse( studentId, course );
        System.out.println( "Student with ID: " + studentId + " enrolled successfully to " + courseId );
    }

    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
        studentService.showSummary();
    }

    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }

    private static void showCourseAverageGrade( CourseService courseService, Scanner scanner )
    {
        System.out.println( "Insert course ID" );
        String courseId = scanner.next();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }
        System.out.println(courseService.showAverageGrade(courseId));
    }

}
