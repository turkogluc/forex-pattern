package com.forex.patterns;


import com.forex.patterns.model.Bar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PatternsApplicationTests {

    @Test
    public void someListTest(){

        List<Bar> liste = new ArrayList<>();

        Bar t2 = new Bar(10.0, 20.0, 30.0, 40.0, "t2");

        liste.add(new Bar(1.0,2.0,3.0,4.0,"t1"));
        liste.add(new Bar(10.0,20.0,30.0,40.0,"t2"));
        liste.add(new Bar(100.0,200.0,300.0,400.0,"t3"));

        liste.subList(liste.indexOf(t2)+1,liste.size()).forEach(s -> System.out.println(s.toString()));

    }


}
