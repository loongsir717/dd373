package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.DdPayOrderApi;
import com.ruoyi.common.core.domain.entity.OrderLinkJson;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.security.Md5Utils;
import com.ruoyi.system.controller.DuanxingApi;
import com.ruoyi.system.domain.Ddpayshop;
import com.ruoyi.system.mapper.DdpayshopMapper;
import com.ruoyi.system.service.IDdpayshopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DdpayorderMapper;
import com.ruoyi.system.domain.Ddpayorder;
import com.ruoyi.system.service.IDdpayorderService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.web.util.UriUtils;

import javax.swing.*;

/**
 * 多多373订单Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-09
 */
@Service
public class DdpayorderServiceImpl implements IDdpayorderService
{
    @Autowired
    private DdpayorderMapper ddpayorderMapper;

    @Autowired
    private DdpayshopMapper ddpayshopMapper;

    @Value(value = "${ddconfig.appid}")
    private String appid;

    @Value(value = "${ddconfig.token}")
    private String token;


    private static final Logger log = LoggerFactory.getLogger(DdpayorderServiceImpl.class);


    private String orderIdUri = "https://newpay.dd373.com/Api/BankInfo/UserCenter/GetPayParamInfoForDiamond";
    private String orderNoUri = "https://newpay.dd373.com/Api/Cashier/GetPayInfo";
    private String orderPayLink = "https://newpay.dd373.com/Api/Cashier/PaySubmit";
    private String queryOrder = "https://newpay.dd373.com/Api/FundInfo/UserCenter/List";
    /**
     * 查询多多373订单
     *
     * @param id 多多373订单主键
     * @return 多多373订单
     */
    @Override
    public Ddpayorder selectDdpayorderById(Long id)
    {
        return ddpayorderMapper.selectDdpayorderById(id);
    }

    /**
     * 查询多多373订单列表
     *
     * @param ddpayorder 多多373订单
     * @return 多多373订单
     */
    @Override
    public List<Ddpayorder> selectDdpayorderList(Ddpayorder ddpayorder)
    {
        return ddpayorderMapper.selectDdpayorderList(ddpayorder);
    }

    /**
     * 新增多多373订单
     *
     * @param ddpayorder 多多373订单
     * @return 结果
     */
    @Override
    public int insertDdpayorder(Ddpayorder ddpayorder)
    {
        ddpayorder.setCreateTime(DateUtils.getNowDate());
        return ddpayorderMapper.insertDdpayorder(ddpayorder);
    }

    /**
     * 修改多多373订单
     *
     * @param ddpayorder 多多373订单
     * @return 结果
     */
    @Override
    public int updateDdpayorder(Ddpayorder ddpayorder)
    {
        ddpayorder.setUpdateTime(DateUtils.getNowDate());
        return ddpayorderMapper.updateDdpayorder(ddpayorder);
    }

    /**
     * 批量删除多多373订单
     *
     * @param ids 需要删除的多多373订单主键
     * @return 结果
     */
    @Override
    public int deleteDdpayorderByIds(String ids)
    {
        return ddpayorderMapper.deleteDdpayorderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除多多373订单信息
     *
     * @param id 多多373订单主键
     * @return 结果
     */
    @Override
    public int deleteDdpayorderById(Long id)
    {
        return ddpayorderMapper.deleteDdpayorderById(id);
    }

    @Override
    public AjaxResult craeteOrderNo(Ddpayorder ddpayorder){
        String param = "Number="+ddpayorder.getAmount()+"&isWap=true&returnUrl=";
        String resultCode = "";
        //查詢店鋪信息  提要订单支付金额 获取最小直接订单店铺
        Ddpayshop ddpayshop =  ddpayshopMapper.getMinAmountDdpayshop();
        if(ddpayshop == null){
            return new AjaxResult(AjaxResult.Type.ERROR,"店铺已打烊！",null);
        }
        //查询状态正常
        //獲取店鋪appid/和對應賬號的cookie
           //根據店铺 获取订单ID号
           String cookie = ddpayshop.getCookie();
           String objJson ="";
            try{
                objJson = HttpUtils.sendGet(orderIdUri, param, Constants.UTF8,cookie);
            }catch (Exception e){

            }
            if(StringUtils.isEmpty(objJson)){
                return new AjaxResult(AjaxResult.Type.ERROR,"调用失败",null);
            }
           if(StringUtils.isEmpty(objJson)){
               return new AjaxResult(AjaxResult.Type.ERROR1,"返回参数为空",null);
           }
           JSONObject jsonObject  = JSONObject.parseObject(objJson);
           // {"StatusCode":"0","StatusMsg":"请求成功","StatusData":{"ResultCode":"0","ResultMsg":"操作成功","ResultData":"79fbf99327d04ebf84c37c45689fe46c"}}
           JSONObject jsonObject1 = (JSONObject) jsonObject.get("StatusData");
           if(StringUtils.isEmpty(jsonObject1)){
               return new AjaxResult(AjaxResult.Type.ERROR1,"返回参数为空",null);
           }
           resultCode = (String) jsonObject1.get("ResultCode");
           if(!"0".equals(resultCode)){
               return new AjaxResult(AjaxResult.Type.ERROR1,(String) jsonObject1.get("ResultMsg"),null);
           }
           String orderId = (String) jsonObject1.get("ResultData");
           log.info( "----------------获取订单Id:"+orderId);
            param = "OrderId="+orderId+"&payScene[0]=1&payScene[1]=3";

           String orderNoJson = "";

            try{
                orderNoJson = HttpUtils.sendGet(orderNoUri, param, Constants.UTF8,cookie);
            }catch (Exception e){

            }
            if(StringUtils.isEmpty(orderNoJson)){
                return new AjaxResult(AjaxResult.Type.ERROR,"调用失败",null);
            }


           if(StringUtils.isEmpty(orderNoJson)){
               return new AjaxResult(AjaxResult.Type.ERROR2,"获取订单号请求，返回参数为空！",null);
           }
           JSONObject jsonObject2  = JSONObject.parseObject(orderNoJson);
           JSONObject statusData = (JSONObject) jsonObject2.get("StatusData");
           if(StringUtils.isEmpty(statusData)){
               return new AjaxResult(AjaxResult.Type.ERROR2,"返回参数为空！",null);
           }
           resultCode  = (String) statusData.get("ResultCode");
           if(!"0".equals(resultCode)){
               return new AjaxResult(AjaxResult.Type.ERROR2,(String) jsonObject1.get("ResultMsg"),null);
           }
           JSONObject resultData= (JSONObject) statusData.get("ResultData");
           JSONArray array = (JSONArray) resultData.get("OrderInfos");
           String OrderNo ="";
           String describe = "";
           BigDecimal price = null;  //订单金额
            if(array.size()>0){
                JSONObject ob = (JSONObject) array.get(0);//得到json对象
                 OrderNo =(String)  ob.get("OrderId");//订单编号
                 describe = (String) ob.get("Describe");// 订单说明
                price = (BigDecimal) ob.get("Price");  //订单金额
            }
           log.info( "----------------获取订单编号:"+OrderNo);
           //根據订单ID号 获取支付链接
           OrderLinkJson olj = new OrderLinkJson();
           olj.setOrderId(orderId);
           olj.setBankCode("");
           olj.setScenceType("");
           olj.setPayScene(3);
           olj.setVerifyCode("");
           olj.setPurpose("");
           olj.setSendWay(0);
           olj.setIsWap(true);
           olj.setPayTypeId("12F3F41DE3124EAFB335FF571A9D164A");
           log.info( "----------------获取订单链接参数:"+olj.toString());
            String resorderPayLinkJson = "";
            try{
                resorderPayLinkJson = HttpUtils.doHttpPost(orderPayLink,olj.toString(),"application/json",cookie);
            }catch (Exception e){
            }
            if(StringUtils.isEmpty(resorderPayLinkJson)){
                return new AjaxResult(AjaxResult.Type.ERROR,"调用失败",null);
            }
           log.info( "----------------返回值:"+resorderPayLinkJson);
           if(StringUtils.isEmpty(resorderPayLinkJson)){
               return new AjaxResult(AjaxResult.Type.ERROR3,"获取订单支付链接失败",null);
           }
           JSONObject orderPayLinkJson  = JSONObject.parseObject(resorderPayLinkJson);
           JSONObject statusData1 = (JSONObject) orderPayLinkJson.get("StatusData");
           resultCode = (String) statusData1.get("ResultCode");
           if(!"0".equals(resultCode)){
               return new AjaxResult(AjaxResult.Type.ERROR3,(String) statusData1.get("ResultMsg"),null);
           }
           JSONObject resultData2 = (JSONObject) statusData1.get("ResultData");
           String orderPayLink = (String) resultData2.get("Action");
           log.info( "获取订单链接:"+orderPayLink);
           ddpayorder.setAppid(ddpayshop.getAppid());
           ddpayorder.setName(ddpayshop.getName());
           ddpayorder.setOrderId(OrderNo);
           ddpayorder.setAmount(price);
           ddpayorder.setCallbackStatus(0);
           ddpayorder.setPayUrl(UriUtils.decode(orderPayLink,"UTF-8"));
           ddpayorder.setOrderUrl(UriUtils.decode(orderPayLink,"UTF-8"));
           ddpayorder.setMethod("0");  //只有支付宝
           ddpayorder.setBody(describe);
           ddpayorder.setCreateTime(new Date());
           int count = ddpayorderMapper.insertDdpayorder(ddpayorder);
           if(count>0){
               Map<String,String > map = new HashMap();
               map.put("orderPayLink",orderPayLink);
               map.put("orderNo",OrderNo);
               map.put("merchantOrderNo",ddpayorder.getMerchantOrderNo());
               return new AjaxResult(AjaxResult.Type.SUCCESS,null, JSONObject.toJSON(map));
           }else{
               return new AjaxResult(AjaxResult.Type.ERROR,"插入数据失败",null);
           }
    }

    /**
     * 1、查询未回调的订单
     * 2、循环查询对应的订单
     * 3、回调平台接口
     * 4、根据回调状态更新订单回调状态
     * @return
     */
    @Override
    public String callbackOrder(Ddpayorder order) {
            if(order.getStatus() < 1 ){  //如果订单状态为0 则需要请求dd373
                String appid = order.getAppid();
                Ddpayshop   ddpayshop  =   ddpayshopMapper.selectDdpayshopByAppId(appid);
                if(ddpayshop == null){
                   return "未找到店铺信息";
                }
                String orderNo =  order.getOrderId();
                String  param= "?StartDate=&EndDate=&Keyword="+orderNo+"&Classify=2&Type=0&PageSize=20&PageIndex=1";
                String objJson = "";
                try{
                    objJson = HttpUtils.sendGet(queryOrder, param, Constants.UTF8,ddpayshop.getCookie());
                }catch (Exception e){

                }
                if(StringUtils.isEmpty(objJson)){
                    return "请求失败";
                }
                JSONObject jsonObject  = JSONObject.parseObject(objJson);
                JSONObject statusData = (JSONObject) jsonObject.get("StatusData");
                String resultCode  = (String) statusData.get("ResultCode");
                if(!"0".equals(resultCode)){
                    return "查询失败";
                }
                JSONObject resultData= (JSONObject) statusData.get("ResultData");
                JSONArray pageResult = (JSONArray) resultData.get("PageResult");
                if(pageResult.size()>0){
                    JSONObject ob = (JSONObject) pageResult.get(0);//得到json对象
                    String state =(String)  ob.get("State");//订单状态
                    String createDate = (String)  ob.get("CreateDate");//订单日期
                    String orderId = (String) ob.get("OrderId");// 订单号
                    if(StringUtils.isNotEmpty(state) && "成功".equals(state)){
                        order.setStatus(1L);
                        try{
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            order.setCompletionTime(df.parse(createDate));
                        }catch (Exception e){

                        }
                        order.setUpdateTime(new Date());
                        ddpayorderMapper.updateDdpayorder(order);
                    }
                   //回调
                    String result =  callbackUrl(order);
                    return result;
                }
            }else{
                //如果已查询成功、但回调失败，需要重新回调！
                String result =  callbackUrl(order);
                return result;
            }
        return null;
    }

    private String callbackUrl(Ddpayorder ddpayorder){
        String parm = appid+ddpayorder.getOrderId()+ddpayorder.getCallbackUrl()+ ddpayorder.getAmount()+ddpayorder.getCompletionTime().getTime();
        log.info("加密前未加token的串： "+parm);
        String sign = Md5Utils.hash(parm+token).toUpperCase();
        log.info("加token后的加密后的串： "+sign);
        String postData = "{\"appId\":\""+appid+"\"," +
                "\"orderNo\":\""+ddpayorder.getOrderId()+"\"," +
                "\"merchantOrderNo\":\""+ddpayorder.getMerchantOrderNo()+"\"," +
                "\"payStatus\":\""+ddpayorder.getStatus()+"\"," +
                "\"amount\":\""+ddpayorder.getAmount()+"\"," +
                "\"payTime\":\""+ddpayorder.getCompletionTime().getTime()+"\"," +
                "\"sign\":\""+sign+"\"}";
        log.info("回调参数： "+postData);
        log.info("回调地址： "+ddpayorder.getCallbackUrl());
        String callbackJson = "";
        try{
           callbackJson  =  HttpUtils.doHttpPost(ddpayorder.getCallbackUrl(),postData,"application/json",null);
        }catch (Exception e){

        }
        if("success".equals(callbackJson)){
            ddpayorder.setCallbackStatus(1);
            int count = ddpayorderMapper.updateDdpayorder(ddpayorder);
            if(count>0){
                log.info("回调订单号："+ddpayorder.getOrderId()+"，成功");
            }
        }
        return callbackJson;
    }
}
