package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.Kinds;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class KindsTest {

    @Autowired
    private KindsMapper kindsMapper;

    @Test
    public void testSelectAll() {
        List<Kinds> kinds = kindsMapper.selectAll();

        for (Kinds kind : kinds) {
            System.out.println(kind);
        }
    }

}
