package cn.cenjp.platform.dao;

import cn.cenjp.platform.bean.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserDao{

    @Insert("INSERT INTO `plotform`.`user`\n" +
            "(`user_id`,\n" +
            "`user_name`,\n" +
            "`user_sex`,\n" +
            "`user_addr`,\n" +
            "`user_phone`,\n" +
            "`user_password`,\n" +
            "`user_email`)\n" +
            "VALUES\n" +
            "(#{user_id},\n" +
            "#{user_name},\n" +
            "#{user_sex},\n" +
            "#{user_addr},\n" +
            "#{user_phone},\n" +
            "#{user_password},\n" +
            "#{user_email});\n")
    @Options(useGeneratedKeys=true, keyProperty="user_id", keyColumn="user_id")
    int insertUser(User user);

    @Update("UPDATE `plotform`.`user`\n" +
            "SET\n" +
            "`user_name` = #{user_name},\n" +
            "`user_sex` = #{user_sex},\n" +
            "`user_addr` = #{user_addr},\n" +
            "`user_phone` = #{user_phone},\n" +
            "`user_password` = #{user_password},\n" +
            "`user_email` = #{user_email}\n" +
            "WHERE `user_id` = #{user_id};\n")
    int updateUser(User user);

    @Delete("delete from user where user_id=#{id}")
    int deleteUser(int id);

    @Select("select * from user where user_phone=#{user_phone}")
    User selectByUserPhone(String user_phone);

    @Update("update user set user_password=#{password} where user_id=#{id}")
    int updatePass(@Param("password") String password,@Param("id")int id);

    @Update("UPDATE `plotform`.`user`\n" +
            "SET\n" +
            "`user_name` = #{user_name},\n" +
            "`user_sex` = #{user_sex},\n" +
            "`user_addr` = #{user_addr},\n" +
            "`user_phone` = #{user_phone},\n" +
            "`user_password` = #{user_password},\n" +
            "`user_email` = #{user_email}\n" +
            "WHERE `user_id` = #{user_id};\n")
    int updateInfo(User user);
}
