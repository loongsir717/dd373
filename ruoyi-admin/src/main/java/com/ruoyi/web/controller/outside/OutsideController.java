package com.ruoyi.web.controller.outside;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.security.Md5Utils;
import com.ruoyi.system.domain.Ddpayorder;
import com.ruoyi.system.service.IDdpayorderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

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
    @PostMapping("/createOrder")
    @ResponseBody
    public AjaxResult createOrder(Ddpayorder ddpayorder){
        String afterSign = appid+ddpayorder.getMerchantOrderNo()+ddpayorder.getCallbakUrl()+
                ddpayorder.getAmount()+ddpayorder.getTimestamps()+token;
        logger.info("afterSign:"+afterSign);
        String sign = Md5Utils.hash(afterSign).toUpperCase(Locale.ROOT);
        logger.info("sign:"+sign);
        if(!sign.equals(ddpayorder.getSign())){
            return new AjaxResult(AjaxResult.Type.ERROR,"验签失败！","");
        }else{
            return ddpayorderService.craeteOrderNo(ddpayorder);
        }
    }
}
