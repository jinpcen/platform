package cn.cenjp.platform.service;

import cn.cenjp.platform.dao.GoodVoDao;
import cn.cenjp.platform.Prefix.GoodVoKey;
import cn.cenjp.platform.redis.RedisService;
import cn.cenjp.platform.vo.GoodVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 订单信息服务类
 */
@Service
public class GoodService {

    private static Logger log = LoggerFactory.getLogger(GoodService.class);
    @Autowired
    RedisService redisService;

    @Autowired
    GoodVoDao goodVoDao;

    /**
     * 获取某一类正在秒杀的全部商品信息
     * @param goodKind 商品种类
     * @return list集合 代表该类正在秒杀的所有商品
     */
    public List<GoodVo> getGoodVoList(String goodKind){
        List<GoodVo> list=goodVoDao.getGoodList(goodKind);
        return list;
    }

    /**
     * 获取某个具体的秒杀的商品的信息
     * @param goodId 商品id
     * @return 返回该商品的信息
     */
    public GoodVo getGoodVo(String goodId){
        return goodVoDao.getGood(goodId);
    }

    public boolean getAllGoodByList(){
        List<GoodVo> goodVoList=null;
        for (int i=1;i<=7;i++){
            goodVoList = goodVoDao.getGoodList("" + i);
            //把商品的数量全部放进redis中
            for (GoodVo goodVo:goodVoList){
                redisService.set(GoodVoKey.goodVoCount,""+goodVo.getGood_id(),goodVo.getSpike_count());
            }
        }
        return true;
    }

}
