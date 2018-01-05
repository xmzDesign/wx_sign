package com.hdkj.wechat.servlet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Xu.MinZhe
 * @version V1.0
 * @Project merchant-wechat
 * @Package com.hdkj.wechat.servlet
 * @Description
 * @Date 2017/12/26 10:57
 */
@RestController
public class DemoController {

    @RequestMapping(value = "demo")
    public String demo(){
        System.out.println("接口验证");
        return "demo";
    }

    @RequestMapping(value = "check")
    public String check(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("开始验证token");

        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            System.out.print("echostr="+echostr);
            return echostr;
        }else{
            return "fail";
        }
    }

}
