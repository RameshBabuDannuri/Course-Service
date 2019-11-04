package com;

import com.model.Course;
import com.repository.CourseRepository;
import com.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CourseService courseService;
    @MockBean
    CourseRepository courseRepository;


    @Test
    public void getCourseTest(){
        logger.trace("getCourse method test is excuted");
        Course course = new Course("java",2000.00);
        Course course1 = new Course("spring",3000.00);
        ArrayList<Course> list  = new ArrayList<>();
        list.add(course);
        list.add(course1);
        when(courseRepository.findAll()).thenReturn(list);
        assertEquals(2,list.size());
    }


}
