import com.generation.service.StudentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    StudentService studentService = new StudentService();

    @Test
    public void testisSubscribed() {
        assertTrue(studentService.isSubscribed( "stu01") );
        assertFalse(studentService.isSubscribed( "stu10") );
    }

    @Test
    public void testfindStudent()
    {
        assertNotNull(studentService.findStudent( "stu01" ));
        assertNull(studentService.findStudent( "stu10" ));
    }
}
