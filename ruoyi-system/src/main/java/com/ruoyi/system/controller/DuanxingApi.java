package com.ruoyi.system.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Dianpu;
import com.ruoyi.system.domain.PhoneMsgCode;
import com.ruoyi.system.service.IDianpuService;
import com.ruoyi.system.service.IDuanxingService;
import com.ruoyi.system.service.IXiaoeCookieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

@Controller
@RequestMapping("/system/getjiema")
public class DuanxingApi {

    private String prefix = "system/getjiema";

    @Autowired
    private IDuanxingService duanxingService;

    @Autowired
    private IDianpuService dianpuService;

    private static final Logger log = LoggerFactory.getLogger(DuanxingApi.class);

    @GetMapping("/getToken")
    @ResponseBody
    public String getToken() throws InterruptedException, UnsupportedEncodingException
    {
        String keyWord;
        String phone;
        String msg;
        String msgCode;
        //查询店铺要取多少个cookie
        Dianpu dp = new Dianpu();
        dp.setStatus(1L);
        List<Dianpu> dianpuList = dianpuService.selectDianpuList(dp);
        long cookie = 0;
        if(dianpuList.size()>0){
           cookie =  dianpuList.get(0).getCookisNum();
           keyWord=dianpuList.get(0).getName();
        }else{
            return "0";
        }

        List<PhoneMsgCode> pmcList = new ArrayList<PhoneMsgCode>();
        for(long i = 0;i<cookie;i++){
            PhoneMsgCode pmc = new PhoneMsgCode();
            //获取手机号
            phone = duanxingService.getPhone(null, null);
            log.info("jiema平台手机号:"+phone);
            pmc.setPhone(phone);
            pmcList.add(pmc);
        }

        //根据手机号 取短信
        Thread.sleep(60000);

        for (PhoneMsgCode pmc:pmcList) {
            msg = duanxingService.getMsg(pmc.getPhone(),keyWord);
            log.info("jiema平台手机号"+pmc.getPhone()+"收到了短信:"+msg);
            pmc.setMsg(msg);
            if(msg.indexOf("小鹅通")>0){
                String[] strs = msg.split("小鹅通");
                msgCode =strs[1].substring(1,7);
                log.info("jiema平台手机号"+pmc.getPhone()+"收到的验证码:"+msgCode);
                pmc.setMsgCode(msgCode);
            }
        }

        //根据验证码 调用小鹅通接口登录，获取cookies信息，

        //将获取的cookies信息插入数据表备用
        return "1";
    }

}


