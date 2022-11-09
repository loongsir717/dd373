package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.DdPayOrderApi;
import com.ruoyi.common.core.domain.entity.OrderLinkJson;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.controller.DuanxingApi;
import com.ruoyi.system.domain.Ddpayshop;
import com.ruoyi.system.mapper.DdpayshopMapper;
import com.ruoyi.system.service.IDdpayshopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DdpayorderMapper;
import com.ruoyi.system.domain.Ddpayorder;
import com.ruoyi.system.service.IDdpayorderService;
import com.ruoyi.common.core.text.Convert;

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

    private static final Logger log = LoggerFactory.getLogger(DdpayorderServiceImpl.class);


    private String orderIdUri = "https://newpay.dd373.com/Api/BankInfo/UserCenter/GetPayParamInfoForDiamond";
    private String orderNoUri = "https://newpay.dd373.com/Api/Cashier/GetPayInfo";
    private String orderPayLink = "https://newpay.dd373.com/Api/Cashier/PaySubmit";
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
    public Ddpayorder craeteOrderNo(Ddpayorder ddpayorder) {
       String param = "Number="+ddpayorder.getAmount()+"&isWap=true&returnUrl=";
        //   String param = "Number=2&isWap=true&returnUrl=";
        //String cookie = "clientId=27a558b5-b367-4d70-8be6-c769c2932e94; loginToken=be867f2f-075e-415f-890f-9fb4cf81b11a; refreshToken=b3c456ff-01d5-4aba-a9f3-7790091a6a4f; login.dd373.com=a38fad9e-8e03-4bf9-ad2d-8024a0b416e7; userName_cc=dd_itm4hbep; newuser.dd373.com=26ab52a8-71c6-4349-aa77-c01667fe4c0b; goods.dd373.com=532bc20e-0445-44e1-9d48-10a47f031a30; point.dd373.com=15df2b29-fe62-43c6-90c1-59fc4eb147fb; newpay.dd373.com=30d0acf4-0530-497c-82eb-b4de6577b576; mission.dd373.com=23312274-86e4-4de0-9e99-645f4e7d6135; thirdbind.dd373.com=19f101a9-1ddd-4cb0-a474-33fd254464c3; imservice.dd373.com=166b4f3e-fe7c-4949-8f25-51edb3d0f099";
        Ddpayshop ddpayshop = new Ddpayshop();
        //查詢店鋪信息
        List<Ddpayshop> ddpayshopList=  ddpayshopMapper.selectDdpayshopList(ddpayshop);
        //獲取店鋪appid/和對應賬號的cookie
       if(ddpayshopList == null || ddpayshopList.size()==0){
           return null;
       }else {
           //根據店铺 获取订单ID号
           String cookie = ddpayshopList.get(0).getCookie();
           String objJson = HttpUtils.sendGet(orderIdUri, param, Constants.UTF8,cookie);
           JSONObject jsonObject  = JSONObject.parseObject(objJson);
           // {"StatusCode":"0","StatusMsg":"请求成功","StatusData":{"ResultCode":"0","ResultMsg":"操作成功","ResultData":"79fbf99327d04ebf84c37c45689fe46c"}}
           JSONObject jsonObject1 = (JSONObject) jsonObject.get("StatusData");
           String orderId = (String) jsonObject1.get("ResultData");
           if(StringUtils.isEmpty(orderId)){
               return null;
           }
           log.info( "----------------获取订单Id:"+orderId);
           //根據订单ID号 获取订单号    https://newpay.dd373.com/Api/Cashier/GetPayInfo?OrderId=a0757d0fe6bf457a9f45c88e70017051&payScene[0]=1&payScene[1]=3
            param = "OrderId="+orderId+"&payScene[0]=1&payScene[1]=3";
           String orderNoJson = HttpUtils.sendGet(orderNoUri, param, Constants.UTF8,cookie);
           JSONObject jsonObject2  = JSONObject.parseObject(orderNoJson);
           JSONObject statusData = (JSONObject) jsonObject2.get("StatusData");
           JSONObject resultData = (JSONObject) statusData.get("ResultData");
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
           //String resorderPayLinkJson  =  HttpUtils.sendPost(orderPayLink, olj.toString(),cookie);
           String resorderPayLinkJson  =  HttpUtils.doHttpPost(orderPayLink,olj.toString(),"application/json",cookie);
           log.info( "----------------返回值:"+resorderPayLinkJson);
           JSONObject orderPayLinkJson  = JSONObject.parseObject(resorderPayLinkJson);
           JSONObject statusData1 = (JSONObject) orderPayLinkJson.get("StatusData");
           JSONObject resultData2 = (JSONObject) statusData1.get("ResultData");
           String orderPayLink = (String) resultData2.get("Action");
           log.info( "获取订单链接:"+orderPayLink);
           ddpayorder.setAppid(ddpayshop.getAppid());
           ddpayorder.setName(ddpayshop.getName());
           ddpayorder.setOrderId(OrderNo);
           ddpayorder.setAmount(price);
           ddpayorder.setPayUrl(orderPayLink);
           ddpayorder.setOrderUrl(orderPayLink);
           ddpayorder.setMethod("0");  //只有支付宝
           ddpayorder.setBody(describe);
           ddpayorder.setCreateTime(new Date());
           return ddpayorder;
       }
    }
}
