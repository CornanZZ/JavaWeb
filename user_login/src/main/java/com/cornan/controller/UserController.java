package com.cornan.controller;

import com.cornan.entity.User;
import com.cornan.service.UserService;
import com.cornan.utils.CaptchaCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin    //允许跨域
@RequestMapping("user") //请求映射(输入网址时使用user)
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Map<String, Object> login(@RequestBody User user, String code, String codeFlag, HttpServletRequest request){
        //log.info("用户信息：", user.toString());
        Map<String, Object> map = new HashMap<>();
        try{
            User userDB = userService.login(user);
            //log.info(codeFlag);
            if(codeFlag.equals("true")){
                String key = (String)request.getServletContext().getAttribute("code");  //提取之前存入的code
                if(key.equalsIgnoreCase(code)){
                    //User userDB = userService.login(user);
                    map.put("status", true);
                    map.put("msg", "提示：登录成功！");
                    map.put("user", userDB);
                }
                else{
                    map.put("status", false);
                    map.put("msg", "验证码输入错误"); //接收UserServiceImpl中报出的异常信息
                    throw new RuntimeException("验证码输入错误");
                }
            }
            else{
                map.put("status", true);
                map.put("msg", "提示：登录成功！");
                map.put("user", userDB);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("status", false);
            map.put("msg", e.getMessage()); //接收UserServiceImpl中报出的异常信息
        }
        return map;
    }

    @PostMapping("register")
    public Map<String, Object> register(@RequestBody User user, String code, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try{
            String key = (String)request.getServletContext().getAttribute("code");  //提取之前存入的code
            if(key.equalsIgnoreCase(code)){
                userService.register(user);
                map.put("status", true);
                map.put("msg", "提示：注册成功！");
            }
            else{
                map.put("status", false);
                map.put("msg", "验证码输入错误"); //接收UserServiceImpl中报出的异常信息
                throw new RuntimeException("验证码输入错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("status", false);
            map.put("msg", "提示：注册失败！" + e.getMessage());
        }
        return map;
    }

    @GetMapping("getImage")
    public String getImage(HttpServletRequest request) throws IOException {
        //自定义验证码图片格式，并生成验证码图片流
        //CaptchaCodeUtil vCode = new CaptchaCodeUtil(116, 36, 4, 10, code);
        CaptchaCodeUtil vCode = new CaptchaCodeUtil(116, 36, 4, 10);
        //使用工具类生成4位验证码并返回给code
        String code = vCode.getCode();
        //将验证码放入getServletContext作用域(存储)
        request.getServletContext().setAttribute("code", code);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //输出图片流
        vCode.write(byteArrayOutputStream);
        String s = "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
        return s;
    }

}
