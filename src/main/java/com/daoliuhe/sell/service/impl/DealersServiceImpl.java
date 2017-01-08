package com.daoliuhe.sell.service.impl;

import com.daoliuhe.sell.mapper.DealersMapper;
import com.daoliuhe.sell.model.Dealers;
import com.daoliuhe.sell.service.DealersService;
import com.daoliuhe.sell.util.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by CYY on 2016/12/25.
 */
@Service
public class DealersServiceImpl implements DealersService {

    private static final Logger logger = LoggerFactory.getLogger(DealersServiceImpl.class);

    @Autowired
    DealersMapper dealersMapper;

    @Override
    public Map<String, Object> getPageData(Dealers dealers) {
        logger.info("getPageData,dealers:{}", dealers);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = dealersMapper.getPageCount(dealers);
        map.put("total", total);
        int curPage = dealers.getPage();
        int rows = dealers.getRows();
        int maxPage = (total + rows - 1) / rows;
        if (curPage > maxPage && maxPage > 0) {
            dealers.setPage(maxPage);
        }
        map.put("rows", dealersMapper.getPageData(dealers));
        return map;
    }

    @Override
    public void saveOrUpdate(Dealers dealers) {
        logger.info("saveOrUpdate,dealers:{}", dealers);
        if (!StringUtils.isEmpty(dealers.getId())) {
            dealersMapper.updateByPrimaryKey(dealers);
        } else {
            //获取二维码
            dealersMapper.insert(dealers);
        }
    }

    @Override
    public Dealers getDealersById(Integer id) {
        return dealersMapper.selectByPrimaryKey(id);
    }
}
