package cn.cenjp.platform.service;
import cn.cenjp.platform.bean.MySpike;
import cn.cenjp.platform.dao.MySpikeDao;
import cn.cenjp.platform.dao.OderDao;
import cn.cenjp.platform.dao.SpikeGoodsDao;
import cn.cenjp.platform.redis.RedisService;
import cn.cenjp.platform.utils.MethodUtil;
import cn.cenjp.platform.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单服务类
 */
@Service
public class OrderService {

    @Autowired
    GoodService goodService;

    @Autowired
    RedisService redisService;

    @Autowired
    MySpikeDao mySpikeDao;

    @Autowired
    SpikeGoodsDao spikeGoodsDao;

    @Autowired
    OderDao oderDao;


    /**
     * 生成订单
     * @param userId 用户id
     * @param spikeId 秒杀商品id
     * @param address 用户地址
     * @return 返回生成的用户秒杀对象
     */
    @Transactional
    public MySpike create(Integer userId,String spikeId,String address){
        //减少库存，若返回值小于等于0则代表商品已经销售完毕
        int count = spikeGoodsDao.reduceCount(spikeId);
        if (count<=0)
            return  null;

        //创建用户秒杀信息对象
        MySpike mySpike= MethodUtil.getMySpike(userId,spikeId,address);

        //往数据库里插入用户秒杀信息
        mySpikeDao.insertMySpike(mySpike);
        return mySpike;
    }

    /**
     * 判断是否重复秒杀
     * @param user_id 用户id
     * @param spike_id 秒杀商品的id
     * @return 返回true或false，代表是否重复秒杀
     */
    public boolean checkReSpike(Integer user_id, String spike_id) {
        //根据用户id和秒杀商品id在用户表查找是否已经存在该记录
        MySpike mySpike = mySpikeDao.selectByUserIdAndGoodId(user_id, spike_id);
        return mySpike==null;
    }

    /**
     * 根据用户id和秒杀商品id在用户表查找是否已经存在该记录
     * @param user_id 用户id
     * @param spike_id 秒杀商品id
     * @return 返回用户秒杀商品信息
     */
    public MySpike getOrderInfo(Integer user_id, String spike_id){
        return  mySpikeDao.selectByUserIdAndGoodId(user_id, spike_id);
    }

    public boolean doPay(String spikeId){
       return mySpikeDao.updateMySpike(spikeId)>0;
    }

    public MySpike getOrderInfoByMySpikeId(String mySpikeId){
        return  mySpikeDao.selectByMyspikeId(mySpikeId);
    }


    public List<Order> getOrder(Integer userId){
        return oderDao.getOrder(userId);
    }
}
