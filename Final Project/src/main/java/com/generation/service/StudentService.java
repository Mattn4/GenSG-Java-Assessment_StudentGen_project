package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Module;
import com.generation.model.Student;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService
{
    private final Map<String, Student> students = new HashMap<>();

    public StudentService()
    {
        subscribeStudent( new Student( "stu01", "Cheryl Tan", "cheryltan@gmail.com", new Date(101,0,17))); // 2001 Jan 17
        subscribeStudent( new Student( "stu02", "Fang Xin Hui", "fangxinhui@hotmail.com", new Date(102,8,4)) ); // 2002 Sep 4
        subscribeStudent( new Student( "stu03", "Ahmad bin Osman", "ahmadbinosman@gmail.com", new Date(103,4,30)) ); // 2003 May 30

        Module module = new Module( "INTRO-CS", "Introduction to Computer Science",
                "Introductory module for the generation technical programs" );
        enrollToCourse("stu01", new Course( "INTRO-CS-1", "Introduction to Computer Science", 9, module ));
        enrollToCourse("stu02", new Course( "INTRO-CS-1", "Introduction to Computer Science", 9, module ));

        enrollToCourse("stu01", new Course( "INTRO-CS-2", "Introduction to Algorithms", 9, module ) );
        enrollToCourse("stu03", new Course( "INTRO-CS-3", "Algorithm Design and Problem Solving - Introduction ", 9, module ));
    }


    public void subscribeStudent( Student student )
    {
        students.put( student.getId(), student );
    }

    public Student findStudent( String studentId )
    {
        if ( students.containsKey( studentId ) )
        {
            return students.get( studentId );
        }
        return null;
    }

    public boolean isSubscribed( String studentId )
    {
        //TODO implement this method
        // Is the student in the students hashmap?
        //   yes -> the student is subscribed (return true)
        //   no  -> the student is not subscribed (return false)
        return students.containsKey(studentId);
    }

    public void showSummary()
    {
        //TODO implement
        // Prints the list all the subscribed students & their corresponding courses
        System.out.println( "List of students including enrolled courses:" );
        for ( String key : students.keySet() )
        {
            Student studentName = students.get(key);
            System.out.println( studentName );
            List<Course> courses = studentName.getApprovedCourses();
            for ( Course course : courses )
            {
                System.out.println( "\t\t" + course );
            }
        }

    }

    public void enrollToCourse( String studentId, Course course )
    {
        if ( students.containsKey( studentId ) )
        {
            students.get( studentId ).enrollToCourse( course );
        }
    }


}
