package com.graduation.recommendsystem.dianping.controller.admin;

import com.graduation.recommendsystem.dianping.common.AdminPermission;
import com.graduation.recommendsystem.dianping.common.BusinessException;
import com.graduation.recommendsystem.dianping.common.EmBusinessError;
import com.graduation.recommendsystem.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller("/admin/admin")
@RequestMapping("/admin/admin")
public class AdminController {

    @Value("${admin.email}")
    private String email;
    @Value("${admin.encryptPassword}")
    private String encrptyPassword;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private UserService userService;

    public static final String CURRENT_ADMIN_SESSION = "currentAdminSession";

    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/admin/admin/index");
        modelAndView.addObject("userCount",userService.countAllUser());
        modelAndView.addObject("CONTROLLER_NAME","admin");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    @RequestMapping("/loginpage")
    public ModelAndView loginpage() {
        ModelAndView modelAndView = new ModelAndView("/admin/admin/login");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String  login(@RequestParam(name = "email")String email,
                              @RequestParam(name = "password")String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"email or password can't be null");
        }

        if (email.equals(this.email) && encoderByMD5(password).equals(this.encrptyPassword)) {
            // login successfully
            httpServletRequest.getSession().setAttribute(CURRENT_ADMIN_SESSION,email);
            return "redirect:/admin/admin/index";
        } else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"username or password error");
        }

    }

    private String encoderByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(messageDigest.digest(str.getBytes("UTF-8")));
    }

}
