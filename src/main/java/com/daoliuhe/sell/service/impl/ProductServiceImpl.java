package com.daoliuhe.sell.service.impl;

import com.daoliuhe.sell.mapper.ProductMapper;
import com.daoliuhe.sell.model.Product;
import com.daoliuhe.sell.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CYY on 2016/12/25.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductMapper productMapper;

    @Override
    public Map<String, Object> getPageData(Product product) {
        logger.info("getPageData,product:{}", product);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = productMapper.getPageCount(product);
        map.put("total", total);
        int curPage = product.getPage();
        int rows = product.getRows();
        int maxPage = (total + rows - 1) / rows;
        if (curPage > maxPage && maxPage > 0) {
            product.setPage(maxPage);
        }
        map.put("rows", productMapper.getPageData(product));
        return map;
    }

    @Override
    public Product getProductById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveOrUpdate(Product product) {
        logger.info("saveOrUpdate,product:{}", product);
        if (!StringUtils.isEmpty(product.getId())) {
            productMapper.updateByPrimaryKeySelective(product);
        } else {
            productMapper.insert(product);
        }
    }

    @Override
    public void updateProduct(Product product) {
        logger.info("updateProduct,product:{}", product);
        productMapper.updateByPrimaryKeySelective(product);
    }
}
