package cn.cenjp.platform;

import cn.cenjp.platform.bean.Good;
import cn.cenjp.platform.bean.Manager;
import cn.cenjp.platform.bean.SpikeGoods;
import cn.cenjp.platform.dao.GoodVoDao;
import cn.cenjp.platform.service.SendSpikeService;
import cn.cenjp.platform.service.UserService;
import cn.cenjp.platform.utils.MD5Util;
import cn.cenjp.platform.vo.GoodVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatformApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	GoodVoDao goodVoDao;

	@Autowired
	SendSpikeService sendSpikeService;

	@Test
	public void testLogin(){
		String password= MD5Util.formPassToDBPass("1a44d494fb586f279417cf32cd6488ea","a1b2c3d4");

		System.out.println(password);
	}

	@Test
	public void tgoodVoDaoestGoodVo(){
		GoodVo good = goodVoDao.getGood("5");
		System.out.println(good.toString());
	}

	@Test
	public void testTimeConvert(){
		//2019-05-16T12:00
//		new LocalDateTime("2019-05-16T12:00");
//		System.out.println(time);
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime localDateTime = LocalDateTime.parse("2019-05-16T12:00",df);
		System.out.println(localDateTime);
	}

	@Test
	public void testSend(){
		Good good = new Good();
		good.setGood_originalPrice(1999l);
		good.setGood_introduction("圣诞节啊十九大");
		good.setGood_kind(1);
		good.setGood_name("曾小锋");


		SpikeGoods spikeGoods = new SpikeGoods();
		spikeGoods.setSpike_count(50);
		spikeGoods.setSpike_price(2d);
		spikeGoods.setSpike_startTime(LocalDateTime.now());
		spikeGoods.setSpike_endTime(LocalDateTime.now().plusSeconds(60*60*24*30));
		spikeGoods.setUser_id(45);

		boolean b = sendSpikeService.sendSpike(good, spikeGoods);
		System.out.println(b);
	}
}
