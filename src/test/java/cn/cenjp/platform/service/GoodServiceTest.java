package cn.cenjp.platform.service;

import cn.cenjp.platform.Prefix.CountPrefix;
import cn.cenjp.platform.redis.RedisService;
import cn.cenjp.platform.utils.GetDate;
import cn.cenjp.platform.vo.GoodVo;
import cn.cenjp.platform.vo.Info;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodServiceTest {

    @Autowired
    RedisService redisService;


    @Test
    public void getMonthSpike(){
        ArrayList<Integer> list = new ArrayList<>();
        int year = LocalDate.now().getYear();
        for(int i=30;i>0;i--){
            String key = ""+year+6+i;
            Integer integer = redisService.get(CountPrefix.sendCountPrefix, key, Integer.class);
            list.add(integer);
        }

        list.stream()
             .forEach(System.out::println);
    }

    @Test
    public void getWeekInfo(){
        String key = GetDate.getDate();
        Integer dayUser =redisService.getCount(CountPrefix.dayUserCountPrefix, key);
        System.out.println(dayUser);
    }

}