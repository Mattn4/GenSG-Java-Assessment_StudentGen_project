import com.generation.service.CourseService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {

    CourseService courseService = new CourseService();

    @Test
    public void testgetCourse()
    {
        // Correct code
        assertNotNull(courseService.getCourse( "INTRO-CS-1" ));
        // Incorrect code name
        assertNull(courseService.getCourse( "CS-1" ));
    }

    @Test
    public void testshowAverageGrade(){

        /* Course "INTRO-CS-1"
                   cheryl: 60 marks
                   xinhui: 90 marks

           Average grade of both students = 75.0  */

        assertEquals(75.0, courseService.showAverageGrade("INTRO-CS-1"));

        /* Course "INTRO-CS-2"
                   ahmad: no grade yet, initialized with 0

           Average grade = 0.0  */

        assertEquals(0.0, courseService.showAverageGrade("INTRO-CS-2"));

    }

}

