package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.Goods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GoodsTest {

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void testGetByKindsId() {
        List<Goods> goods = goodsMapper.getByKindsId(1);

        for (Goods good : goods) {
            System.out.println(good);
        }
    }

}
