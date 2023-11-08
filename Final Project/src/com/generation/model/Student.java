package com.generation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student
    extends Person
    implements Evaluation
{
    private double average;

    private final List<Course> courses = new ArrayList<>();

    private final Map<String, Course> approvedCourses = new HashMap<>();

    public Student( String id, String name, String email, Date birthDate )
    {
        super( id, name, email, birthDate );
    }

    public void enrollToCourse( Course course )
    {
        //TODO implement this method
        // Martin's hints
        //TODO 1. 'put' the course to student's approvedCourses aka HashMap<code, course> via registerApprovedCourse
        registerApprovedCourse( course );
        //TODO 2. ALSO, 'add' the course in the students' courses aka List<course>
        courses.add(course);

    }

    public void registerApprovedCourse( Course course )
    {
        approvedCourses.put( course.getCode(), course );
    }

    public boolean isCourseApproved( String courseCode )
    {
        //TODO implement this method
        // Martin's hints
        //TODO 1. HINT: does the students approvedCourses aka HashMap<code, course> 'contains the key' to courseCode?
        //TODO if so, what do you return? Otherwise, what else do you return?
        return approvedCourses.containsKey(courseCode);

    }

    // CHALLENGE IMPLEMENTATION: Read README.md to find instructions on how to solve. 
    public List<Course> findPassedCourses( Course course )
    {
        //TODO implement this method----------------------------------------------------------------------------------
        List<Course> passedCourses = new ArrayList<>();

        if ( course.getGrade() >= 60 )
        {
            passedCourses.add(course);
            return passedCourses;
        }

        return null;
    }

    public boolean isAttendingCourse( String courseCode )
    {
        //TODO implement this method
        // Martin's hints
        //TODO 1. HINT: does the students approvedCourses aka HashMap<code, course> 'contains the key' to courseCode?
        //TODO if so, what do you return? Otherwise, what else do you return?
        return approvedCourses.containsKey(courseCode);

    }

    @Override
    public double getAverage()
    {
        /* if use courses arraylist
        double sum = 0;
        for (Course course: courses) {
            sum += course.getGrade();
        }
        average = sum/courses.size();

        return average;
         */

        double sum = 0;
        for ( String key : approvedCourses.keySet() )
        {
            sum += approvedCourses.get( key ).getGrade();
        }
        average = sum/approvedCourses.size();

        return average;
    }

    @Override
    public List<Course> getApprovedCourses()
    {
        //TODO implement this method
        // Martin's hints
        //TODO 1. Hint, where do the list of courses come from?
        return new ArrayList<>(approvedCourses.values());
    }

    @Override
    public String toString()
    {
        return "Student {" + super.toString() + "}";
    }
}
