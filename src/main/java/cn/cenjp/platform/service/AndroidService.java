package cn.cenjp.platform.service;

import cn.cenjp.platform.dao.GoodVoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AndroidService {

    @Autowired
    GoodVoDao goodVoDao;

    public long countGood(String goodKind){
        return goodVoDao.countGood(goodKind);
    }
}
