package com.ruoyi.system.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
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
import com.ruoyi.system.domain.ShopGoods;
import com.ruoyi.system.domain.SysTokenInfo;
import com.ruoyi.system.mapper.DdpayshopMapper;
import com.ruoyi.system.mapper.SysTokenInfoMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
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

    @Autowired
    private SysTokenInfoMapper sysTokenInfoMapper;

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


    @Override
    public List<Ddpayorder> selectPayorderStatusList(Ddpayorder ddpayorder)
    {
        return ddpayorderMapper.selectPayorderStatusList(ddpayorder);
    }


    @Override
    public Ddpayorder queryOrder(Ddpayorder ddpayorder) {
        return ddpayorderMapper.queryOrder(ddpayorder);
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
           if("4001".equals(resultCode)){
               ddpayshop.setStatus(0);
               ddpayshopMapper.updateDdpayshop(ddpayshop);
               return new AjaxResult(AjaxResult.Type.ERROR1,(String) jsonObject1.get("ResultMsg"),null);
           }else if(!"0".equals(resultCode)){
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

            JSONArray payTypes = (JSONArray) resultData.get("PayTypes");
            String payTypeId ="";
            if(payTypes.size()>0){
                JSONObject ob = (JSONObject) payTypes.get(0);//得到json对象
                payTypeId =(String)  ob.get("Id");//支付类型Id
            }
           log.info( "----------------PayTypes——Id:"+payTypeId);
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
           olj.setPayTypeId(payTypeId);
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
                order = updateOrderPayStatus(order);
                if(order.getStatus()==1){
                    String result =  callbackUrl(order);
                    return result;
                }else{
                    return "";
                }
            }else{
                //如果已查询成功、但回调失败，需要重新回调！
                String result =  callbackUrl(order);
                return result;
            }
    }

    private String callbackUrl(Ddpayorder ddpayorder){
        try{
            String parm = appid+ddpayorder.getOrderId()+ddpayorder.getMerchantOrderNo()+ddpayorder.getStatus()+ ddpayorder.getAmount()+ddpayorder.getCompletionTime().getTime();
            log.info("加密前未加token的串： "+parm);
            String sign = Md5Utils.hash(parm+token).toUpperCase();
            log.info("加token后的加密后的串： "+sign);
            String postData = "{\"appid\":\""+appid+"\"," +
                    "\"orderNo\":\""+ddpayorder.getOrderId()+"\"," +
                    "\"merchantOrderNo\":\""+ddpayorder.getMerchantOrderNo()+"\"," +
                    "\"payStatus\":\""+ddpayorder.getStatus()+"\"," +
                    "\"amount\":\""+ddpayorder.getAmount()+"\"," +
                    "\"payTime\":\""+ddpayorder.getCompletionTime().getTime()+"\"," +
                    "\"sign\":\""+sign+"\"}";
            log.info("回调参数： "+postData);
            log.info("回调地址： "+ddpayorder.getCallbackUrl());
            String callbackJson = "";

            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("appid", appid);
            paramMap.put("orderNo", ddpayorder.getOrderId());
            paramMap.put("merchantOrderNo", ddpayorder.getMerchantOrderNo());
            paramMap.put("payStatus", ddpayorder.getStatus()+"");
            paramMap.put("amount", ddpayorder.getAmount()+"");
            paramMap.put("payTime", ddpayorder.getCompletionTime().getTime()+"");
            paramMap.put("sign", sign);
            callbackJson = HttpUtil.post("http://apis3.haha555.xyz/notify/anquan/notify_res.htm", paramMap);
            if("success".equals(callbackJson)){
                ddpayorder.setCallbackStatus(1);
                int count = ddpayorderMapper.updateDdpayorder(ddpayorder);
                if(count>0){
                    log.info("回调订单号："+ddpayorder.getOrderId()+"，成功");
                }
            }
            return callbackJson;
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return "";
    }


    @Override
    public AjaxResult craeteOrderNo(Ddpayorder ddpayorder, String type) {

        SysTokenInfo sysTokenInfo= sysTokenInfoMapper.selectTokenInfoOrderTopOne();
        String cookie = sysTokenInfo.getCookie();
        String adderssId = sysTokenInfo.getUserAdderss();
        //2、查询商品列表  URL  http://h5.mall2.yingliao.tv/api/groom/list/1?page=1&limit=8  get
        String queryGoodsUrl = "http://h5.mall2.yingliao.tv/api/groom/list/1";
        String resultShopGoodsStr  = HttpUtils.sendGet(queryGoodsUrl,"page=1&limit=20",Constants.UTF8,cookie);
        if(StringUtils.isEmpty(resultShopGoodsStr)){
            return new AjaxResult(AjaxResult.Type.ERROR,"查詢商品列表失敗",null);
        }
        log.info( "----------------返回值:"+resultShopGoodsStr);
        JSONObject shopGoodsJSONObject  = JSONObject.parseObject(resultShopGoodsStr);
        JSONObject resultDataJson = (JSONObject) shopGoodsJSONObject.get("data");
        String shopGoodslist = resultDataJson.getString("list");
        List<ShopGoods> shopGoods = JSON.parseArray(shopGoodslist, ShopGoods.class);
        String productId = "";
        for (ShopGoods goods:shopGoods ) {
            BigDecimal bd = new BigDecimal(goods.getPrice());
            log.info( "-—---商品价格："+bd+";订单价格："+ddpayorder.getAmount());
            if(bd.compareTo(ddpayorder.getAmount()) == 0 ){
                productId = goods.getId();
                break;
            }
        }
        if(StringUtils.isEmpty(productId)){
            return new AjaxResult(AjaxResult.Type.ERROR,"没有价格为"+ddpayorder.getAmount()+"的商品",null);
        }
        //3、获取商品详情  URL  http://h5.mall2.yingliao.tv/api/product/detail/{productId} get
        String queryGoodsdetailsUrl = "http://h5.mall2.yingliao.tv/api/product/detail/"+productId;
        String resultProductInfoStr  = HttpUtils.sendGet(queryGoodsdetailsUrl,null,Constants.UTF8,cookie);
        if(StringUtils.isEmpty(resultProductInfoStr)){
            return new AjaxResult(AjaxResult.Type.ERROR,"查詢商品详情失敗",null);
        }
        log.info( "----获取商品详情---返回值:"+resultProductInfoStr);
        JSONObject productInfoObj  = JSONObject.parseObject(resultProductInfoStr);
        JSONObject resultProductInfoJson = (JSONObject) productInfoObj.get("data");
        JSONArray productAttr = (JSONArray)  resultProductInfoJson.get("productAttr");
        JSONObject productStoreInfo = (JSONObject)  resultProductInfoJson.get("storeInfo");
        Integer product_id = null;
        String unique = "";
        if(productAttr != null && productAttr.size()>0){
            JSONObject attr_valuesObj = (JSONObject) productAttr.get(0);
            JSONArray attr_values = (JSONArray)  attr_valuesObj.get("attr_values");
            String arrt = "";
            if(attr_values.size()>0){
                arrt = (String) attr_values.get(0);
            }else{
                return new AjaxResult(AjaxResult.Type.ERROR,"获取商品详情失敗",productAttr);
            }
            if(StringUtils.isEmpty(arrt)){
              return new AjaxResult(AjaxResult.Type.ERROR,"获取商品详情失敗",productAttr);
            }
            JSONObject productValues = (JSONObject)  resultProductInfoJson.get("productValue");
            Set<String> keys = productValues.keySet();
            JSONObject productValue = null;
            for(String t:keys){
                productValue = (JSONObject) productValues.get(t);
            }
            if(productValue == null ){
                return new AjaxResult(AjaxResult.Type.ERROR,"获取商品详情失败",null);
            }
            product_id = productValue.getInteger("product_id");
            unique = productValue.getString("unique");
        }
        if(productStoreInfo != null){
            product_id = productStoreInfo.getInteger("id");
        }
        log.info("获取商品详情  product_id:"+product_id);
        log.info("获取商品详情  unique:"+unique);
        if(product_id < 0){
            return new AjaxResult(AjaxResult.Type.ERROR,"获取商品详情失敗",null);
        }
        //4、加入购物车  URL   http://h5.mall2.yingliao.tv/api/cart/add post           {"productId":"4","cartNum":1,"new":1,"uniqueId":"1e734f6b","virtual_type":0}
        String addCartUrl = " http://h5.mall2.yingliao.tv/api/cart/add";
        String addCarPostDate = "{\"productId\":\""+product_id+"\",\"cartNum\":1,\"new\":1,\"uniqueId\":\""+unique+"\"}";
        String resultaddCarStr  = HttpUtils.doHttpPost(addCartUrl,addCarPostDate,"application/json",cookie);
        if(StringUtils.isEmpty(resultaddCarStr)){
            return new AjaxResult(AjaxResult.Type.ERROR,"查詢商品详情失敗",null);
        }
        log.info( "----加入购物车---参数:"+resultaddCarStr);
        log.info( "----加入购物车---返回值:"+resultaddCarStr);
        JSONObject addCarObj  = JSONObject.parseObject(resultaddCarStr);
        JSONObject addCarJson = (JSONObject) addCarObj.get("data");
        String cartId = (String)  addCarJson.get("cartId");
        log.info( "----加入购物车---Id:"+cartId);
        if(StringUtils.isEmpty(cartId)){
            return new AjaxResult(AjaxResult.Type.ERROR,"加入购物车失敗",null);
        }

        //5、确认订单信息  URL  http://h5.mall2.yingliao.tv/api/order/check_shipping  post   {"cartId":"4332171792833576960","new":1}
        String checkShippingUrl = "http://h5.mall2.yingliao.tv/api/order/check_shipping";
        //6、确认订单价格  URL   http://h5.mall2.yingliao.tv/api/coupons/order/0.01?cartId=4332171792833576960&new=1&shippingType=1  get
        String couponsOrderUrl = "http://h5.mall2.yingliao.tv/api/coupons/order/";
        //7、提交订单信息  URL   http://h5.mall2.yingliao.tv/api/order/confirm  post         {"cartId":"4332172211529973760","new":1,"addressId":0,"shipping_type":1}
        String confirmOrderUrl = "http://h5.mall2.yingliao.tv/api/order/confirm";
        String confirmOrderPostDate = "{\"cartId\":\""+cartId+"\"," +
                "\"new\":1," +
                "\"addressId\":"+adderssId+"," +
                "\"shipping_type\":1}";
        String resultConfirmOrderStr  = HttpUtils.doHttpPost(confirmOrderUrl,confirmOrderPostDate,"application/json",cookie);
        if(StringUtils.isEmpty(resultConfirmOrderStr)){
            return new AjaxResult(AjaxResult.Type.ERROR,"提交订单失败",null);
        }
        log.info( "----提交订单---参数："+resultConfirmOrderStr);
        log.info( "----提交订单---返回值:"+resultConfirmOrderStr);
        JSONObject confirmOrderObj  = JSONObject.parseObject(resultConfirmOrderStr);
        JSONObject confirmOrderData = (JSONObject) confirmOrderObj.get("data");
        String orderKey = (String) confirmOrderData.get("orderKey");

        //8、验证订单orderkey  URL  http://h5.mall2.yingliao.tv/api/order/computed/{购物车id}  post   {"addressId":2,"useIntegral":0,"couponId":0,"shipping_type":1,"payType":"alipay"}
        String orderkeyUrl = "http://h5.mall2.yingliao.tv/api/order/computed/"+orderKey;
        String orderkeyPostDate = "{\"addressId\":"+adderssId+",\"useIntegral\":0,\"couponId\":0,\"shipping_type\":1,\"payType\":\"alipay\"}";
        String resultorderkeyStr  = HttpUtils.doHttpPost(orderkeyUrl,orderkeyPostDate,"application/json",cookie);
        if(StringUtils.isEmpty(resultorderkeyStr)){
            return new AjaxResult(AjaxResult.Type.ERROR,"验证订单失败",null);
        }
        log.info( "----验证订单---参数："+orderkeyPostDate);
        log.info( "----验证订单---返回值:"+resultorderkeyStr);
        JSONObject orderkeyObj  = JSONObject.parseObject(resultorderkeyStr);
        Integer status = orderkeyObj.getInteger("status");
        if(200 != status){
            return new AjaxResult(AjaxResult.Type.ERROR,"创建订单失败",null);
        }
        //9、创建订单  URL    http://h5.mall2.yingliao.tv/api/order/create/{orderkey}  post
        String createOrderUrl =  "http://h5.mall2.yingliao.tv/api/order/create/"+orderKey;
        String createOrderPostDate = "{\"custom_form\":[]," +
                "\"real_name\":\""+sysTokenInfo.getUsername()+"\"," +
                "\"phone\":\""+sysTokenInfo.getUsername()+"\"," +
                "\"addressId\":"+adderssId+"," +
                "\"formId\":\"\",\"couponId\":0," +
                "\"payType\":\"alipay\"," +
                "\"useIntegral\":false,\"bargainId\":0,\"combinationId\":0,\"discountId\":null,\"pinkId\":0,\"advanceId\":0,\"seckill_id\":0,\"mark\":\"\",\"from\":\"weixinh5\",\"shipping_type\":1,\"new\":1,\"invoice_id\":\"\"," +
                "\"quitUrl\":\"http://h5.mall2.yingliao.tv/pages/goods/order_pay_status/index?&type=3&totalPrice=0.01\"}";
        String resultcreateOrderPostDateStr  = HttpUtils.doHttpPost(createOrderUrl,createOrderPostDate,"application/json",cookie);
        if(StringUtils.isEmpty(resultcreateOrderPostDateStr)){
            return new AjaxResult(AjaxResult.Type.ERROR,"提交订单失败",null);
        }
        log.info( "----创建订单---参数："+createOrderPostDate);
        log.info( "----创建订单---返回值:"+resultcreateOrderPostDateStr);

        JSONObject createOrderObj  = JSONObject.parseObject(resultcreateOrderPostDateStr);
        status = createOrderObj.getInteger("status");
        if(200 != status){
            return new AjaxResult(AjaxResult.Type.ERROR,"创建订单失败",null);
        }
        JSONObject createOrderData = (JSONObject) createOrderObj.get("data");
        JSONObject resultData = (JSONObject) createOrderData.get("result");

        String jsConfig = (String) resultData.get("jsConfig");
        String key = (String) resultData.get("key");
        String orderId = (String) resultData.get("orderId");
        String statusData = (String) createOrderData.get("status");

        Document document = Jsoup.parse(jsConfig);
        Elements eForm = document.getElementsByTag("form");
        Elements eInput = document.getElementsByTag("input");
        Map<String,String> map = new HashMap<String,String>();
        Attributes attrs = null;
        for(int i=0;i<eInput.size();i++){
            attrs = eInput.get(i).attributes();
            map.put(attrs.get("name"),attrs.get("value"));
        }
        String alipayUrl = eForm.attr("action");
        map.put("action",alipayUrl);

        String payUrl = sendRequest(map) ;
        ddpayorder.setOrderId(orderId);  //订单号
        ddpayorder.setMethod(statusData); //支付方式
        ddpayorder.setOrderKey(key);    //orderkey
        ddpayorder.setBody(jsConfig);//返回结果
        ddpayorder.setMethod("Alipay");
        ddpayorder.setCreateTime(new Date());
        ddpayorder.setUpdateTime(new Date());
        ddpayorder.setAppid(sysTokenInfo.getShopId());
        ddpayorder.setName(sysTokenInfo.getUsername());
        ddpayorder.setCookie(cookie);
        ddpayorder.setCallbackStatus(0);
        ddpayorder.setPhone(sysTokenInfo.getUsername());
        ddpayorder.setPayUrl(payUrl);
        int count = ddpayorderMapper.insertDdpayorder(ddpayorder);
        if(count>0&&StringUtils.isNotEmpty(payUrl)){
            Map<String,String > resMap = new HashMap();
            resMap.put("orderPayLink",payUrl);
            resMap.put("orderNo",orderId);
            resMap.put("merchantOrderNo",ddpayorder.getMerchantOrderNo());
            return new AjaxResult(AjaxResult.Type.SUCCESS,null, JSONObject.toJSON(resMap));
        }
        return new AjaxResult(AjaxResult.Type.SUCCESS,"成功",payUrl);
    }

    public  String sendRequest(Map<String,String> map){
        HttpResponse response = null;
        try {
            // 创建HttpClient实例及Post方法
            CloseableHttpClient httpclient = new DefaultHttpClient();
            String url =map.get("action");
            String respContent = "";
            HttpPost httppost = new HttpPost(url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            //因为传入的值为汉字，所以使用ContentType.create(HTTP.PLAIN_TEXT_TYPE,HTTP.UTF_8)进行一个字符的转换，否则将会出现乱码。字母和数字不需要使用。
            builder.addTextBody("method", map.get("method"), ContentType.create(HTTP.PLAIN_TEXT_TYPE,HTTP.UTF_8));
            builder.addTextBody("app_id", map.get("app_id"), ContentType.create(HTTP.PLAIN_TEXT_TYPE,HTTP.UTF_8));
            builder.addTextBody("timestamp", map.get("timestamp"), ContentType.create(HTTP.PLAIN_TEXT_TYPE,HTTP.UTF_8));
            builder.addTextBody("format", map.get("format"), ContentType.create(HTTP.PLAIN_TEXT_TYPE,HTTP.UTF_8));
            builder.addTextBody("version", map.get("version") ,ContentType.create(HTTP.PLAIN_TEXT_TYPE,HTTP.UTF_8));
            builder.addTextBody("alipay_sdk", map.get("alipay_sdk"), ContentType.create(HTTP.PLAIN_TEXT_TYPE,HTTP.UTF_8));
            builder.addTextBody("charset", map.get("charset"), ContentType.create(HTTP.PLAIN_TEXT_TYPE,HTTP.UTF_8));
            builder.addTextBody("sign_type", map.get("sign_type"), ContentType.create(HTTP.PLAIN_TEXT_TYPE,HTTP.UTF_8));
            builder.addTextBody("biz_content", map.get("biz_content"), ContentType.create(HTTP.PLAIN_TEXT_TYPE,HTTP.UTF_8));
            builder.addTextBody("notify_url", map.get("notify_url"), ContentType.create(HTTP.PLAIN_TEXT_TYPE,HTTP.UTF_8));
            builder.addTextBody("sign", map.get("sign"), ContentType.create(HTTP.PLAIN_TEXT_TYPE,HTTP.UTF_8));
            HttpEntity multipart = builder.build();
            httppost.setEntity(multipart);
            response =httpclient.execute(httppost);// 发送请求
            System.out.println(response.getStatusLine().getStatusCode());

            //注意，返回的结果的状态码是302，非200
            if (response.getStatusLine().getStatusCode() == 302) {
                HttpEntity he = response.getEntity();
                System.out.println(response.getHeaders("Location"));
                respContent = EntityUtils.toString(he, "UTF-8");
                String line="";
                StringBuilder result = new StringBuilder();
                int count = response.getAllHeaders().length;
                for (int i=0;i<count;i++) {
                    if("Location".equals(response.getAllHeaders()[i].getName()) ){
                        String str =response.getAllHeaders()[i].getValue();
                        return str;
                    }
                }
            }
            httppost.releaseConnection();
            httpclient.getConnectionManager().shutdown();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Ddpayorder updateOrderPayStatus(Ddpayorder ddpayorder) {
        SysTokenInfo sysTokenInfo = new SysTokenInfo();
        sysTokenInfo.setUsername(ddpayorder.getPhone());
        List<SysTokenInfo> sysTokenInfos= sysTokenInfoMapper.selectSysTokenInfoList(sysTokenInfo);
        if(sysTokenInfos !=null||sysTokenInfos.size()>0){
            sysTokenInfo = sysTokenInfos.get(0);
            String loginUrl = "http://h5.mall2.yingliao.tv/api/login";
            String postDate = " {\"account\":\""+ddpayorder.getPhone()+"\",\"password\":\""+sysTokenInfo.getPwd()+"\"}";
            String loginJson = HttpUtils.doHttpPost(loginUrl,postDate,"application/json",null);
            if(StringUtils.isEmpty(loginJson)){
                return null;
            }
            log.info( "----------------返回值:"+loginJson);
            JSONObject resultloginson  = JSONObject.parseObject(loginJson);
            JSONObject resultloginDataJson = (JSONObject) resultloginson.get("data");
            String tokenJson = "Bearer "+ resultloginDataJson.get("token");
            String url = "http://h5.mall2.yingliao.tv/api/order/detail/";//cp332958393926942720
            String resultShopGoodsStr  = HttpUtils.sendGet(url+ddpayorder.getOrderId(),StringUtils.EMPTY,Constants.UTF8,tokenJson);
            if(StringUtils.isEmpty(resultShopGoodsStr)){
                return null;
            }
            log.info( "------查询订单支付状态------返回值:"+resultShopGoodsStr);
            JSONObject shopGoodsJSONObject  = JSONObject.parseObject(resultShopGoodsStr);
            Integer status =  shopGoodsJSONObject.getInteger("status");
            if(200==status){
                JSONObject resultDataJson = (JSONObject) shopGoodsJSONObject.get("data");
                Integer integer =  resultDataJson.getInteger("paid");
                int count =0;
                if(integer==1){
                    ddpayorder.setStatus(1L);
                    ddpayorder.setCompletionTime(new Date());
                    count = ddpayorderMapper.updateDdpayorder(ddpayorder);
                    Long countOrder = sysTokenInfo.getCountOrder();
                    sysTokenInfo.setCountOrder(countOrder++);
                    sysTokenInfoMapper.updateSysTokenInfo(sysTokenInfo);
                }
            }
            return ddpayorder;
        }
        return ddpayorder;
    }
}
