package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Module;
import com.generation.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService
{
    private final Map<String, Student> students = new HashMap<>();

    public StudentService()
    {
    //    subscribeStudent( new Student( "stu-01", "Cheryl Tan", "cheryltan@gmail.com", Tue Dec 12 00:00:00 SGT 2000) );
    //    subscribeStudent( new Student( "stu-02", "Fang Xin Hui", "fangxinhui@hotmail.com", "Fri Nov 23 00:00:00 SGT 2001") );
    //    subscribeStudent( new Student( "stu-03", "Ahmad bin Osman", "ahmadbinosman@gmail.com", Wed May 05 00:00:00 SGT 2010) );
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
        // Prints the list of all the subscribed students
        System.out.println( "List of students:" );
        System.out.println( students.values() );

        // Prints the list all the subscribed students & their corresponding courses
        System.out.println( "List of students including enrolled courses:" );
        for ( String key : students.keySet() )
        {
            Student studentName = students.get(key);
            System.out.println( studentName );
            List<Course> courses = studentName.getApprovedCourses();
            for ( Course course : courses )
            {
                System.out.println( course );
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
