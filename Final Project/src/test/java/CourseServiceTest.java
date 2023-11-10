import com.generation.service.CourseService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {

    CourseService courseService = new CourseService();

    @Test
    public void testgetCourse()
    {
        assertNotNull(courseService.getCourse( "INTRO-CS-1" ));
        assertNull(courseService.getCourse( "CS-1" ));
    }

    @Test
    public void testshowAverageGrade(){
        assertEquals(75.0, courseService.showAverageGrade("INTRO-CS-1"));
    }

}

