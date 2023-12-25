package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.Order;
import com.example.homemanagementsystem.pojo.Worker;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorkerMapper {

    /**
     * 查看所有空闲的家政人员
     * @return 家政人员列表
     */
    @Select("select id, username, name, password, sex, age, address, phone, email, brief_introduction, status, image " +
            "from worker where status = 0")
    List<Worker> getWorkerByStatus();

    /**
     * 通过用户名和密码查询用户
     * @param worker
     * @return 家政人员对象
     */
    @Select("select id, username, name, password, sex, age, address, phone, email, brief_introduction, status, image, " +
            "money from worker where username = #{username} and password = #{password}")
    Worker getWorkerByUsernameAndPassword(Worker worker);

    /**
     * 通过id获取用户信息
     * @param id 用户id
     * @return 家政人员对象
     */
    @Select("select id, username, name, password, sex, age, address, phone, email, brief_introduction, status, image, " +
            "money from worker where id = #{id}")
    Worker getWorkerById(Integer id);

    /**
     * 查询所有家政人员信息
     * @return
     */
    @Select("select id, username, name, password, sex, age, address, phone, email, brief_introduction, status, image, " +
            "money from worker")
    List<Order> selectAll();

    /**
     * 通过用户名查找家政人员信息
     * @param username 用户名
     * @return List<Worker>
     */
    @Select("select username from worker where username = #{username}")
    List<Worker> getUserByName(String username);

    /**
     * 更新image路径
     * @param id 用户id
     * @param url 图片url路径
     */
    @Update("update worker set image = #{url} where id = #{id}")
    void updateImage(Integer id, String url);

    /**
     * 更新家政人员余额
     * @param id 家政人员id
     * @param money 金额
     */
    @Update("update worker set money = money + #{money} where id = #{id}")
    void updateMoney(int id, double money);

    /**
     * 插入家政人员信息
     * @param worker 家政人员
     */
    @Insert("insert into worker(username, password, image) values (#{username}, #{password}, #{image})")
    void insert(Worker worker);

    /**
     * 通过id删除家政人员
     * @param ids 家政人员id列表
     */
    void deleteById(List<Integer> ids);



}
