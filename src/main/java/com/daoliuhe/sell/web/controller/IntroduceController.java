package com.daoliuhe.sell.web.controller;

import com.daoliuhe.sell.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/introduce")
public class IntroduceController {
	
	private static final Logger logger = LoggerFactory.getLogger(IntroduceController.class);

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
		//TODO
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
		ModelAndView mav = new ModelAndView("introduce/register");
		//TODO
		mav.addObject("entity", customer);
		return mav;
	}
}
