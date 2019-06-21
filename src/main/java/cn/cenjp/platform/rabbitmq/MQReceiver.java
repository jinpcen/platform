package cn.cenjp.platform.rabbitmq;

import cn.cenjp.platform.bean.SpikeMessage;
import cn.cenjp.platform.bean.User;
import cn.cenjp.platform.redis.RedisService;
import cn.cenjp.platform.service.GoodService;
import cn.cenjp.platform.service.OrderService;
import cn.cenjp.platform.vo.GoodVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQReceiver {

    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    RedisService redisService;

    @Autowired
    GoodService goodsService;

    @Autowired
    OrderService orderService;

    @RabbitListener(queues = MQConfig.SPIKE_QUEUE)
    public void receive(String message) {
        log.info("receive message:" + message);
        SpikeMessage msg = RedisService.stringToBean(message, SpikeMessage.class);
        User user = msg.getUser();
        String goodsId = msg.getGoodId();

        GoodVo goods = goodsService.getGoodVo(goodsId);
        int stock = goods.getSpike_count();
        if (stock <= 0) {
            return;
        }

        //判断是否已经秒杀到了
        boolean reSpike = orderService.checkReSpike(user.getUser_id(),String.valueOf(goods.getSpike_id()));
        if (!reSpike) {
            return;
        }

        //减库存 下订单 写入秒杀订单
        orderService.create(user.getUser_id(),String.valueOf(goods.getSpike_id()),user.getUser_addr());
    }
}
