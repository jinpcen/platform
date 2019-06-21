package cn.cenjp.platform.dao;

import cn.cenjp.platform.bean.SpikeGoods;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper
public interface SpikeGoodsDao {

    @Select("SELECT `spike_goods`.`spike_price`,\n" +
            "    `spike_goods`.`spike_startTime`,\n" +
            "    `spike_goods`.`spike_endTime`,\n" +
            "    `spike_goods`.`spike_id`,\n" +
            "    `spike_goods`.`user_id`,\n" +
            "    `spike_goods`.`good_id`\n" +
            "    `spike_goods`.`spike_count`," +
            "   `spike_goods`.`orinal_count`\n" +
            "FROM `plotform`.`spike_goods`" +
            "where `spike_goods`.`spike_id`=#{id};\n")
    SpikeGoods getSpikeGoodsById(int id);

    @Update("UPDATE `plotform`.`spike_goods`\n" +
            "SET\n" +
            "`spike_price` = #{spike_price},\n" +
            "`spike_startTime` = #{spike_startTime},\n" +
            "`spike_endTime` = #{spike_endTime},\n" +
            "`user_id` = #{user_id},\n" +
            "`good_id` = #{good_id},\n" +
            "`spike_count` = #{spike_count}\n" +
            "WHERE `spike_id` = #{spike_id};\n")
    int updateSpikeGoods(SpikeGoods spikeGoods);

    @Update("update `plotform`.`spike_goods` set spike_count = spike_count - 1 where spike_id = #{spike_id} and spike_count > 0")
    int reduceCount(@Param("spike_id") String spike_id);

    @Insert("INSERT INTO `plotform`.`spike_goods`\n" +
            "(`spike_price`,\n" +
            "`spike_startTime`,\n" +
            "`spike_endTime`,\n" +
            "`user_id`,\n" +
            "`good_id`,\n" +
            "`spike_count`," +
            "`orinal_count`)\n" +
            "VALUES\n" +
            "(#{spike_price},\n" +
            "#{spike_startTime},\n" +
            "#{spike_endTime},\n" +
            "#{user_id},\n" +
            "#{good_id}," +
            "#{spike_count}," +
            "#{spike_count});\n")
    int insertSpikeGoods(SpikeGoods spikeGoods);

    @Delete("delete from spike_goods where spike_id=#{id}")
    int deleteSpikeGoods(int id);


    @Select("SELECT `spike_goods`.`spike_price`,\n" +
            "    `spike_goods`.`spike_startTime`,\n" +
            "    `spike_goods`.`spike_endTime`,\n" +
            "    `spike_goods`.`spike_id`,\n" +
            "    `spike_goods`.`user_id`,\n" +
            "    `spike_goods`.`good_id`\n" +
            "    `spike_goods`.`spike_count`," +
            "     `spike_goods`.`orinal_count` \n" +
            "FROM `plotform`.`spike_goods`" +
            "where `spike_goods`.`user_id`=#{userId};\n")
    List<SpikeGoods> getSend(@Param("userId") String userId);
}
