package cn.cenjp.platform.dao;

import cn.cenjp.platform.bean.SpikeGoods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;

import static javafx.scene.input.KeyCode.L;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpikeGoodsDaoTest {
//
//    @Autowired
//    SpikeGoodsDao spikeGoodsDao;
//
//    @Test
//    public void getSpikeGoodsById() throws Exception {
//        SpikeGoods spikeGoodsById = spikeGoodsDao.getSpikeGoodsById(1);
//        System.out.println(spikeGoodsById.toString());
//    }
//
//    @Test
//    public void updateSpikeGoods() throws Exception {
//        SpikeGoods spikeGoodsById = spikeGoodsDao.getSpikeGoodsById(1);
//        spikeGoodsById.setSpike_price(0.01);
//        int i = spikeGoodsDao.updateSpikeGoods(spikeGoodsById);
//        System.out.println(i);
//    }
//
//    @Test
//    public void insertSpikeGoods() throws Exception {
//        SpikeGoods spikeGoods = new SpikeGoods();
//        spikeGoods.setGood_id(2);
//        spikeGoods.setUser_id(1);
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime localDateTime = now.plusSeconds(60*60*24);
//        spikeGoods.setSpike_startTime(now);
//        spikeGoods.setSpike_endTime(localDateTime);
//        spikeGoods.setSpike_price(1D);
//        int i = spikeGoodsDao.insertSpikeGoods(spikeGoods);
//        System.out.println(i);
//    }
//
//    @Test
//    public void deleteSpikeGoods() throws Exception {
//        int i = spikeGoodsDao.deleteSpikeGoods(4);
//        System.out.println(i);
//    }

}