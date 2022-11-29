package com.ruoyi.quartz.task;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.http.HttpRequest;
import com.ruoyi.system.domain.Ddpayorder;
import com.ruoyi.system.domain.Ddpayshop;
import com.ruoyi.system.service.IDdpayorderService;
import com.ruoyi.system.service.IDdpayshopService;
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
        HashMap<String, String> submitdata  = new HashMap<String, String>();;
        submitdata.put("StartDate","");
        submitdata.put("EndDate","");
        submitdata.put("Classify","2");
        submitdata.put("Type","0");
        submitdata.put("PageSize","20");
        submitdata.put("PageIndex","1");
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("X-mssvc-sec-ts", "1587820160");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("X-mssvc-access-id", "HQQ7P0SxOla0n7yg");
        headers.put("X-app-machine", "iPhone 7");
        headers.put("X-app-system-version", "13.3");
        headers.put("X-device-code", "C88A0C48-C02C-401D-BDD3-BD1775F78D9D");
        headers.put("Accept", "application/json");
        HttpRequest gettext = null;
        List<Ddpayshop> DdpayshopList = ddpayshopService.selectDdpayshopList(new Ddpayshop());
        if(DdpayshopList != null&& DdpayshopList.size()>0){
            for (Ddpayshop ddpayshop:DdpayshopList) {
                headers.put("Cookie",ddpayshop.getCookie());
                submitdata.put("Keyword","DH2022111102052314082");
                String cookie = ddpayshop.getCookie();
                gettext = new HttpRequest("https://newpay.dd373.com/Api/FundInfo/UserCenter/List", "GET",headers,cookie,submitdata);
                logger.info("upCookiesTask定时任务getData"+gettext.getData());
                StringBuffer sb = new StringBuffer();
                String newCookie = "";
                String requestCookie = gettext.getCookies();
                String[] newCookies = requestCookie.split(";");
                for (int i=0;i<newCookies.length;i++) {
                    if(newCookies[i].indexOf("SERVERID=")>-1){
                        newCookie = newCookies[i];
                    }
                }
                if(cookie.indexOf("SERVERID=")!=-1){
                    String[] cookies = cookie.split(";");
                    for (int i=0;i<cookies.length;i++) {
                        if(cookies[i].indexOf("SERVERID=")==-1){
                            sb.append(cookies[i]+";");
                        }else{
                            sb.append(newCookie+";");
                        }
                    }
                }else{
                    sb.append(cookie+";");
                    sb.append(newCookie);
                }
                ddpayshop.setCookie(sb.toString());
                ddpayshopService.updateDdpayshop(ddpayshop);
            }
        }
        logger.info("upCookiesTask定时任务结束");
    }

}
