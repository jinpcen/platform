package cn.cenjp.platform.dao;

import cn.cenjp.platform.vo.GoodVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodVoDao {


    @Select("select g.good_id,g.good_introduction,g.good_kind,g.good_name,g.good_originalPrice,g.good_image,\n" +
            "sg.spike_count,sg.orinal_count,sg.spike_endTime,sg.spike_id,sg.spike_price,sg.spike_startTime\n" +
            "from goods g inner join spike_goods sg \n" +
            "on g.good_id=sg.good_id\n" +
            "where g.good_kind=#{good_kind}")
    List<GoodVo> getGoodList(String goodKind);

    @Select("select g.good_id,g.good_introduction,g.good_kind,g.good_name,g.good_originalPrice,g.good_image,\n" +
            "sg.spike_count,sg.orinal_count,sg.spike_endTime,sg.spike_id,sg.spike_price,sg.spike_startTime\n" +
            "from goods g inner join spike_goods sg \n" +
            "on g.good_id=sg.good_id\n" +
            "where sg.user_id=#{userId}")
    List<GoodVo> getSendList(@Param("userId") String userId);

    @Select("select g.good_id,g.good_introduction,g.good_kind,g.good_name,g.good_originalPrice,g.good_image,\n" +
            "sg.spike_count,sg.orinal_count,sg.spike_endTime,sg.spike_id,sg.spike_price,sg.spike_startTime\n" +
            "from goods g inner join spike_goods sg \n" +
            "on g.good_id=sg.good_id\n" +
            "where g.good_id=#{id}")
    GoodVo getGood(String id);
}
