package com.daoliuhe.sell.web.controller;

import com.daoliuhe.sell.model.Customer;
import com.daoliuhe.sell.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/introduce")
public class IntroduceController {
	
	private static final Logger logger = LoggerFactory.getLogger(IntroduceController.class);

	@Autowired
	CustomerService customerService;

	@RequestMapping("/about")
	public ModelAndView about() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("introduce/about");
		return mav;
	}

	@RequestMapping("/story")
	public ModelAndView story() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("introduce/story");
		return mav;
	}

	/**
	 * 注册手机号
	 * @param customer
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/register")
	public ModelAndView register(Customer customer) throws UnsupportedEncodingException {
		logger.info("list,customer:{}",customer);
		ModelAndView mav = new ModelAndView("introduce/register");
		String nick = customer.getNick();
		customer.setNick(new String(nick.getBytes("iso8859-1"),"utf-8"));
		mav.addObject("entity", customer);
		return mav;
	}

	/**
	 * 注册手机号
	 * @param customer
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/doRegister")
	public ModelAndView doRegister(Customer customer) throws UnsupportedEncodingException {
		logger.info("list,customer:{}",customer);
		ModelAndView mav = new ModelAndView("introduce/success");
		customerService.updateCustomerRel(customer);
		return mav;
	}
}
