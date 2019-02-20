package com.sparrow.taotao.loginController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sparrow.taotao.User;
import com.sparrow.taotao.service.LoginService;
import com.sparrow.taotao.utils.JsonFormat;
import com.sparrow.taotao.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by xuefeiyuluo on 2019/2/20.
 */

@Controller
@RequestMapping("/userCenter")
public class LoginController {
    @Autowired
    private LoginService loginService;


    // http://localhost:8080/userCenter/login?userName=zhangsan&userPsw=123
    @ResponseBody
    @RequestMapping("/login")
    public JSON login(HttpServletRequest req) {
        JSONObject json = new JSONObject();

        JSON validateResult = validateUserInfo(req,json);
        if (validateResult != null) {
            return validateResult;
        }

        return null;
    }


    // http://localhost:8080/userCenter/register?userName=zhangsan&userPsw=123
    @ResponseBody
    @RequestMapping("/register")
    public JSON register(HttpServletRequest req) {
        JSONObject json = new JSONObject();
        String userName = req.getParameter("userName");
        String userPsw = req.getParameter("userPsw");

        JSON validateResult = validateUserInfo(req,json);
        if (validateResult != null) {
            return validateResult;
        }

        Integer nameResult = loginService.queryByUserName(userName);
        if (nameResult == 1) {
            return JsonFormat.jsonFailWithMsgResult("用户名已存在",json);
        }

        User user = new User();
        user.setUsername(userName);
        // MD5加密
        user.setPassword(DigestUtils.md5DigestAsHex(userPsw.getBytes()));
        user.setCreated(new Date());
        user.setUpdated(new Date());
        Integer registerResult = loginService.addUserInfo(user);
        if (registerResult == 1) {
            return JsonFormat.jsonSuccessWithMsgResult("注册成功",json);
        } else {
            return JsonFormat.jsonFailWithMsgResult("注册失败",json);
        }
    }


    // http://localhost:8080/userCenter/modifyPsw?userName=手机&userPsw=123
    @ResponseBody
    @RequestMapping("/modifyPsw")
    public JSON modifyPsw(HttpServletRequest req) {
        JSONObject json = new JSONObject();
        String userName = req.getParameter("userName");
        String userPsw = req.getParameter("userPsw");

        JSON validateResult = validateUserInfo(req,json);
        if (validateResult != null) {
            return validateResult;
        }
        return null;
    }


    // http://localhost:8080/userCenter/loginOut?userName=手机&userPsw=123
    @ResponseBody
    @RequestMapping("/loginOut")
    public JSON loginOut(HttpServletRequest req) {
        JSONObject json = new JSONObject();

        JSON validateResult = validateUserInfo(req,json);
        if (validateResult != null) {
            return validateResult;
        }

        return null;
    }


    // 用户名与密码验证
    public JSON validateUserInfo (HttpServletRequest req,JSONObject json) {

        String userName = req.getParameter("userName");
        String userPsw = req.getParameter("userPsw");

        if (!StringUtils.isEmpty(userName)){
            return JsonFormat.jsonFailWithMsgResult("用户名为空",json);
        }
        if (!StringUtils.isEmpty(userPsw)){
            return JsonFormat.jsonFailWithMsgResult("用户密码为空",json);
        }
        return null;
    }
}
