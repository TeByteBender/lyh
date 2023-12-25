package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.ConsumerUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ConsumerUserMapper {

    /**
     * 通过用户名和密码登录
     * @param consumerUser 用户对象
     * @return 用户对象
     */
    @Select("select id, username from consumer_user where username = #{username} and password = #{password}")
    public ConsumerUser getConsumerUserNameAndPassWord(ConsumerUser consumerUser);

    /**
     * 通过id查询用户信息
     * @param id 用户id
     * @return 用户对象
     */
    @Select("select id, username, name, password, sex, address, phone, email, image, money " +
            "from consumer_user where id = #{id}")
    ConsumerUser getUserById(Integer id);

    /**
     * 查询所有用户信息
     * @return
     */
    @Select("select id, username, name, password, sex, address, phone, email, image, money from consumer_user")
    List<ConsumerUser> getAllUser();

    /**
     * 通过用户名查找用户信息
     * @param username 用户名
     * @return ConsumerUser
     */
    @Select("select id from consumer_user where username = #{username}")
    List<ConsumerUser> getUserByName(String username);

    /**
     * 添加
     * @param consumerUser 要添加的用户对象
     */
    @Insert("insert into consumer_user(username, password, image) values (#{username}, #{password}, #{image})")
    void insert(ConsumerUser consumerUser);

    /**
     * 修改用户信息
     * @param consumerUser 用户对象
     */
    void updateUser(ConsumerUser consumerUser);

    /**
     * 修改密码
     * @param password 新密码
     */
    @Update("update consumer_user set password = #{password} where id = #{id}")
    void updatePassword(Integer id, String password);

    /**
     * 修改图片路径
     *
     * @param id
     * @param url 图片路径
     */
    @Update("update consumer_user set image = #{url} where id = #{id};")
    void updateImage(Integer id, String url);

    /**
     * 修改用户金额
     * @param id 用户id
     * @param money 修改的金额
     */
    @Update("update consumer_user set money = money + #{money} where id = #{id}")
    void updateMoney(Integer id, Double money);


}
