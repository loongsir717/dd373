package com.ruoyi.web.controller.outside;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.security.Md5Utils;
import com.ruoyi.system.domain.Ddpayorder;
import com.ruoyi.system.service.IDdpayorderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;
import java.util.Objects;

@Controller
@RequestMapping("/outside/order")
public class OutsideController extends BaseController {

    @Autowired
    private IDdpayorderService ddpayorderService;
    @Value(value = "${ddconfig.appid}")
    private String appid;

    @Value(value = "${ddconfig.token}")
    private String token;

    /**
     * /outside/order/createOrder
     * @param ddpayorder
     * @return
     */
    @PostMapping("/createDdOrder")
    @ResponseBody
    public AjaxResult createDdOrder(Ddpayorder ddpayorder){
        String afterSign = appid+ddpayorder.getMerchantOrderNo()+ddpayorder.getCallbackUrl()+
                ddpayorder.getAmount()+ddpayorder.getTimestamps()+token;
        logger.info("afterSign:"+afterSign);
        String sign = Md5Utils.hash(afterSign).toUpperCase();
        logger.info("sign:"+sign);
        if(!sign.equals(ddpayorder.getSign())){
            return new AjaxResult(AjaxResult.Type.ERROR,"验签失败！","");
        }else{
            return ddpayorderService.craeteOrderNo(ddpayorder);
        }
    }


    @PostMapping("/queryOrder")
    @ResponseBody
    public AjaxResult queryOrder(Ddpayorder ddpayorder){
        String afterSign = appid+ddpayorder.getMerchantOrderNo()+ddpayorder.getOrderId()+
                ddpayorder.getAmount()+ddpayorder.getTimestamps()+token;
        logger.info("afterSign:"+afterSign);
        String sign = Md5Utils.hash(afterSign).toUpperCase();
        logger.info("sign1:"+sign);
        logger.info("sign2:"+ddpayorder.getSign());
        if(!sign.equals(ddpayorder.getSign())){
            return new AjaxResult(AjaxResult.Type.ERROR,"验签失败！","");
        }else{
            Ddpayorder order = ddpayorderService.queryOrder(ddpayorder);
            if(order == null|| order.getId() == null){
                return new AjaxResult(AjaxResult.Type.ERROR,"未找到对应订单！","");
            }else{
                return new AjaxResult(AjaxResult.Type.SUCCESS,"",order.getStatus());
            }
        }
    }


    @PostMapping("/createOrder")
    @ResponseBody
    public AjaxResult createOrder(Ddpayorder ddpayorder){
//        String afterSign = appid+ddpayorder.getMerchantOrderNo()+ddpayorder.getCallbackUrl()+
//                ddpayorder.getAmount()+ddpayorder.getTimestamps()+token;
//        logger.info("afterSign:"+afterSign);
//        String sign = Md5Utils.hash(afterSign).toUpperCase();
//        logger.info("sign:"+sign);
//        if(!sign.equals(ddpayorder.getSign())){
//            return new AjaxResult(AjaxResult.Type.ERROR,"验签失败！","");
//        }else{
//            return ddpayorderService.craeteOrderNo(ddpayorder,"Shop");
//        }
        return ddpayorderService.craeteOrderNo(ddpayorder,"Shop");
    }

    @PostMapping("/queryShopOrder")
    @ResponseBody
    public AjaxResult queryShopOrder(Ddpayorder ddpayorder){
        String afterSign = appid+ddpayorder.getMerchantOrderNo()+ddpayorder.getOrderId()+
                ddpayorder.getAmount()+ddpayorder.getTimestamps()+token;
        logger.info("afterSign:"+afterSign);
        String sign = Md5Utils.hash(afterSign).toUpperCase();
        logger.info("sign1:"+sign);
        logger.info("sign2:"+ddpayorder.getSign());
        if(!sign.equals(ddpayorder.getSign())){
            return new AjaxResult(AjaxResult.Type.ERROR,"验签失败！","");
        }else{
            Ddpayorder order = ddpayorderService.queryOrder(ddpayorder);
            if(order == null|| order.getId() == null){
                return new AjaxResult(AjaxResult.Type.ERROR,"未找到对应订单！","");
            }else{
                return new AjaxResult(AjaxResult.Type.SUCCESS,"",order.getStatus());
            }
        }
    }



}
