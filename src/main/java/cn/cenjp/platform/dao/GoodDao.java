package cn.cenjp.platform.dao;

import cn.cenjp.platform.bean.Good;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GoodDao {


    @Select("select last_insert_id()")
    int getLast();

    @Select("SELECT `goods`.`good_id`,\n" +
            "    `goods`.`good_name`,\n" +
            "    `goods`.`good_introduction`,\n" +
            "    `goods`.`good_originalPrice`\n" +
            "FROM `plotform`.`goods`" +
            "where `good_id`=#{id};\n")
    Good getGoodById(int id);

    @Insert("INSERT INTO `plotform`.`goods`\n" +
            "(`good_name`,\n" +
            "`good_introduction`,\n" +
            "`good_originalPrice`,\n" +
            "`good_kind`," +
            "`good_image`)" +
            "VALUES\n" +
            "(#{good_name},\n" +
            "#{good_introduction},\n" +
            "#{good_originalPrice}," +
            "#{good_kind},#{good_image});\n")
    int insertGood(Good good);

    @Update("UPDATE `plotform`.`goods`\n" +
            "SET\n" +
            "`good_name` = #{good_name},\n" +
            "`good_introduction` = #{good_introduction},\n" +
            "`good_originalPrice` = #{good_originalPrice}\n" +
            "WHERE `good_id` = #{good_id};\n")
    int updateGood(Good good);

    @Delete("DELETE FROM `plotform`.`goods`\n" +
            "WHERE `good_id`=#{id};\n")
    int deleteGood(int id);

}
