package com.graduation.recommendsystem.dianping.controller;

import com.graduation.recommendsystem.dianping.common.BusinessException;
import com.graduation.recommendsystem.dianping.common.CommonError;
import com.graduation.recommendsystem.dianping.common.CommonRes;
import com.graduation.recommendsystem.dianping.common.EmBusinessError;
import com.graduation.recommendsystem.dianping.model.UserModel;
import com.graduation.recommendsystem.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller("/user")
@RequestMapping("/user")
public class UserController {

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

}
