package com.daoliuhe.sell.web.controller;

import com.daoliuhe.sell.model.Product;
import com.daoliuhe.sell.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * Created by CYY on 2016/12/25.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Resource
    ProductService productService;

    @RequestMapping("/list")
    public ModelAndView list(Product product) throws UnsupportedEncodingException {
        logger.info("list,product:{}",product);
        ModelAndView mav = new ModelAndView("product/list");
        mav.addAllObjects(productService.getPageData(product));
        mav.addObject("entity", product);
        return mav;
    }

    @RequestMapping("/data")
    @ResponseBody
    public Object data(Product product) {
        logger.info("data,product:{}",product);
        return productService.getPageData(product);
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Product product) throws UnsupportedEncodingException {
        logger.info("edit,product:{}",product);
        ModelAndView mav = new ModelAndView("product/edit");
        if (!StringUtils.isEmpty(product.getId())) {
            Product ret = productService.getProductById(product.getId());
            mav.addObject("product", ret);
        }
        return mav;
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/product/list";
    }

    @RequestMapping("/update")
    public String update(Product product) {
        productService.updateProduct(product);
        return "redirect:/product/list";
    }
}
