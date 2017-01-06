package com.daoliuhe.sell.web.controller;

import com.daoliuhe.sell.model.User;
import com.daoliuhe.sell.service.UserService;
import com.daoliuhe.sell.util.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;
import java.math.BigDecimal;

/**
 * Created by CYY on 2016/12/25.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    UserService userService;

    @RequestMapping
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            mav.setViewName("redirect:/main");
        } else {
            mav.setViewName("login");
        }
        return mav;
    }

    /**
     * 登录
     *
     * @param session
     * @param user
     * @param request
     * @return
     */
    @RequestMapping("doLogin")
    public ModelAndView doLogin(HttpSession session, User user, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        String status = "";
        Subject currentUser = SecurityUtils.getSubject();
        //是否记住我
        //boolean isRemembered = currentUser.isRemembered();
        // 判断用户是否处于登录状态
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getUserPassword());
            token.setRememberMe(false);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                uae.printStackTrace();
                logger.error("doLogin(),,user:{},UnknownAccountException uae.getMessage:{},uae.getLocalizedMessage:{}, uae.getStackTrace :{}",
                        user, uae.getMessage(), uae.getLocalizedMessage(), uae.getStackTrace());
                status = "用户名不正确!";
            } catch (IncorrectCredentialsException ice) {
                ice.printStackTrace();
                logger.error("doLogin(),,user:{},IncorrectCredentialsException ice.getMessage:{},ice.getLocalizedMessage:{}, ice.getStackTrace :{}",
                        user, ice.getMessage(), ice.getLocalizedMessage(), ice.getStackTrace());
                status = "密码不正确！";
            } catch (LockedAccountException lae) {
                lae.printStackTrace();
                logger.error("doLogin(),,user:{},LockedAccountException lae.getMessage:{},lae.getLocalizedMessage:{}, lae.getStackTrace :{}",
                        user, lae.getMessage(), lae.getLocalizedMessage(), lae.getStackTrace());
                status = "用户已被禁用！";
            } catch (AuthenticationException ae) {
                ae.printStackTrace();
                logger.error("doLogin(),,user:{},AuthenticationException ae.getMessage:{},ae.getLocalizedMessage:{}, ae.getStackTrace :{}",
                        user, ae.getMessage(), ae.getLocalizedMessage(), ae.getStackTrace());
                status = "无法登录!";
            }

        }
        boolean isAuth = currentUser.isAuthenticated();
        if (isAuth) {
            User loginUser = userService.selectByLoginName(user.getLoginName());
            //设置客户端ip
            String ip = request.getHeader("x-forwarded-for");
            session.setAttribute(Constants.USER_IP, ip);
            session.setAttribute(Constants.USERINFO, loginUser);
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            logger.info("doLogin(),savedRequest:{}", savedRequest);
            if (null == savedRequest) {
                mav.setViewName("redirect:/main");
            } else {
                String redirect = savedRequest.getRequestUrl().replace("/sell", "");
                logger.info("doLogin(),redirect:{}", redirect);
                mav.setViewName("redirect:" + redirect);
            }
            return mav;
        } else {
            logger.info("doLogin(),status:{}", status);
            mav.setViewName("login");
            mav.addObject("status", status);
        }
        return mav;
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        logger.info("logout");
        ModelAndView mav = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        session.removeAttribute(Constants.USERINFO);
        subject.logout();
        mav.setViewName("login");
        return mav;

    }

    /**
     * 没有权限的页面
     *
     * @return
     */
    @RequestMapping("/unauthorized")
    public ModelAndView unauthorized() {
        logger.info("unauthorized()");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("unauthorized");
        return mav;

    }
}
