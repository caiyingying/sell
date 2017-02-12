package com.daoliuhe.sell.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
