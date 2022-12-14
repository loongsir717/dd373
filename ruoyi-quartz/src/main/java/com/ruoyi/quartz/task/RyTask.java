package com.ruoyi.quartz.task;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.http.HttpRequest;
import com.ruoyi.system.domain.Ddpayorder;
import com.ruoyi.system.domain.Ddpayshop;
import com.ruoyi.system.service.IDdpayorderService;
import com.ruoyi.system.service.IDdpayshopService;
import com.ruoyi.system.service.ISysTokenInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IDdpayorderService ddpayorderService;

    @Autowired
    private IDdpayshopService ddpayshopService;
    @Autowired
    private ISysTokenInfoService sysTokenInfoService;


    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        logger.info("DdOrderCallbackTask定时任务开始");
        Ddpayorder ddpayorder = new Ddpayorder();
        ddpayorder.setCallbackStatus(0);
        List<Ddpayorder> ddpayorderList = ddpayorderService.selectPayorderStatusList(ddpayorder);
        if(ddpayorderList != null&& ddpayorderList.size()>0){
            for (Ddpayorder order:ddpayorderList) {
                ddpayorderService.callbackOrder(order);
            }
        }
        logger.info("DdOrderCallbackTask定时任务结束");
    }


    /**
     * 更新cookies
     */
    public void upCookies()
    {
        logger.info("upCookiesTask定时任务开始");
        sysTokenInfoService.updateTokeninfo();
        logger.info("upCookiesTask定时任务结束");
    }


}
