package com.generation;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main
{

    public static void main( String[] args )
        throws ParseException
    {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
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
        student.gradeCourseForStudent(student, courseId, grade);

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

        // This code to check if student is already enrolled to course is not working
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
