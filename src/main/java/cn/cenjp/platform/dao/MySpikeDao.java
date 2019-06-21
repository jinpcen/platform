package cn.cenjp.platform.dao;

import cn.cenjp.platform.bean.MySpike;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MySpikeDao{

    @Select("SELECT `my_spike`.`myspike_date`,\n" +
            "    `my_spike`.`ifpay`,\n" +
            "    `my_spike`.`myspike_id`,\n" +
            "    `my_spike`.`user_id`,\n" +
            "    `my_spike`.`spike_id`\n" +
            "FROM `plotform`.`my_spike`" +
            "where `my_spike`.`myspike_id`=#{id};\n")
    MySpike getMySpikeById(int id);

    @Insert("INSERT INTO `plotform`.`my_spike`\n" +
            "(`myspike_date`,\n" +
            "`ifpay`,\n" +
            "`user_id`,\n" +
            "`spike_id`,\n" +
            "`address`)\n" +
            "VALUES\n" +
            "(#{myspike_date},\n" +
            "#{ifpay},\n" +
            "#{user_id},\n" +
            "#{spike_id},\n" +
            "#{address});\n")
    @Options(useGeneratedKeys=true, keyProperty="myspike_id", keyColumn="myspike_id")
    int insertMySpike(MySpike mySpike);

    @Update("UPDATE `plotform`.`my_spike`\n" +
            "SET\n" +
            " `ifpay` = 1\n" +
            " WHERE `myspike_id` = #{spikeId};\n")
    int updateMySpike(@Param("spikeId") String spikeId);


    @Delete("delete from my_spike where myspike_id=#{id}")
    int deleteMySpike(int id);

    @Select("SELECT `my_spike`.`myspike_date`,\n" +
            "    `my_spike`.`ifpay`,\n" +
            "    `my_spike`.`ifpay`,\n" +
            "    `my_spike`.`myspike_id`,\n" +
            "    `my_spike`.`user_id`,\n" +
            "    `my_spike`.`spike_id`\n" +
            " FROM `plotform`.`my_spike`" +
            " where `my_spike`.`spike_id`=#{spike_id} and `my_spike`.`user_id`=#{user_id};\n")
    MySpike selectByUserIdAndGoodId(@Param("user_id") Integer user_id, @Param("spike_id") String spike_id);

    @Select("SELECT `my_spike`.`myspike_date`,\n" +
            "    `my_spike`.`ifpay`,\n" +
            "    `my_spike`.`ifpay`,\n" +
            "    `my_spike`.`myspike_id`,\n" +
            "    `my_spike`.`user_id`,\n" +
            "    `my_spike`.`spike_id`\n" +
            "     FROM `plotform`.`my_spike`" +
            "    where `my_spike`.`myspike_id`=#{spike_id};\n")
    MySpike selectByMyspikeId(@Param("spike_id") String spike_id);
}
