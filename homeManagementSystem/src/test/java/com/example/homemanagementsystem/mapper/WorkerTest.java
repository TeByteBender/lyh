package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.Worker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WorkerTest {

    @Autowired
    private WorkerMapper workerMapper;

    @Test
    public void testGetWorkerByStatus() {
        List<Worker> workers = workerMapper.getWorkerByStatus();

        for (Worker worker : workers) {
            System.out.println(worker);
        }
    }
}
