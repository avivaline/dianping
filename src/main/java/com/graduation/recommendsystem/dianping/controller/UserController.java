package com.graduation.recommendsystem.dianping.controller;

import com.graduation.recommendsystem.dianping.common.*;
import com.graduation.recommendsystem.dianping.model.UserModel;
import com.graduation.recommendsystem.dianping.request.LoginReq;
import com.graduation.recommendsystem.dianping.request.RegisterReq;
import com.graduation.recommendsystem.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Controller("/user")
@RequestMapping("/user")
public class UserController {

    public static final String  CURRENT_USER_SESSION = "currentUserSession";

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonRes getUser(@RequestParam(name = "id")Integer id) throws BusinessException {
        UserModel userModel = userService.getUser(id);
        if (userModel == null) {
//            return CommonRes.create(new CommonError(EmBusinessError.NO_OBJECT_FOUND),"Failure");
            throw new BusinessException(EmBusinessError.NO_OBJECT_FOUND);
        } else {
            return CommonRes.create(userModel);
        }
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        String username = "harry";
        ModelAndView modelAndView = new ModelAndView("/index.html");
        modelAndView.addObject("name", username);
        return modelAndView;
    }

    @RequestMapping("/register")
    @ResponseBody
    public CommonRes register(@Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        UserModel userModel = new UserModel();
        userModel.setTelphone(registerReq.getTelphone());
        userModel.setPassword(registerReq.getPassword());
        userModel.setNickName(registerReq.getNickName());
        userModel.setGender(registerReq.getGender());

        UserModel resUserModel =  userService.register(userModel);
        return CommonRes.create(registerReq);
    }

    @RequestMapping("/login")
    @ResponseBody
    public CommonRes login(@RequestBody @Valid LoginReq loginReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,CommonUtil.processErrorString(bindingResult));
        }
        UserModel userModel = userService.login(loginReq.getTelphone(),loginReq.getPassword());
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION,userModel);
        return CommonRes.create(userModel);
    }

    @RequestMapping("/loginout")
    @ResponseBody
    public CommonRes loginout() throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        httpServletRequest.getSession().invalidate();
        return CommonRes.create(null);
    }

    //get current user details
    @RequestMapping("/getCurrentUser")
    @ResponseBody
    public CommonRes getCurrentUser() {
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
        return CommonRes.create(userModel);
    }
}
