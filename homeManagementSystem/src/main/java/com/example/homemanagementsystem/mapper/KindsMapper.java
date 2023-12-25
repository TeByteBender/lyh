package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.Kinds;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface KindsMapper {
    /**
     * 查询所有种类
     * @return 种类列表
     */
    @Select("select id, name from kinds")
    List<Kinds> selectAll();

    /**
     * 通过id删除商品种类
     * @param ids 商品种类id列表
     */
    void deleteKinds(List<Integer> ids);
}
