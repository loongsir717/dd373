package com.ruoyi.system.service;

import java.io.UnsupportedEncodingException;

public interface IDuanxingService {

    /**
     * 调用实例：http://api.d1jiema.com/zc/data.php?code=signIn&user=用户名&password=密码
     * 成功返回值：token
     * 失败返回值：ERROR:错误信息
     * 备注：登录一次获取token后，token长期有效，不重新登录token不会改变。
     * @return
     */
    public String signIn();

    /**
     * 调用实例：http://api.d1jiema.com/zc/data.php?code=leftAmount&token=登录获取的token
     * 成功返回值：余额
     * 失败返回值：ERROR:错误信息
     * 备注：无
   
     * @return
     */
    public String leftAmount();

    /**
     * queryProjs
     * 调用实例：http://api.d1jiema.com/zc/data.php?code=queryProjs&token=登录获取的token&proj=%E7%9F%A5%E4%B9%8E
     * 参数：proj：项目名称或项目id
     * 成功返回值：项目列表，多个项目信息之间是以换行符‘\n’分隔的。
     * 每一个项目内部信息项分别是：项目id、价格、项目名称、卡类型、收发类型，各项之间以制表符‘\t’分隔。
     * 失败返回值：ERROR:错误信息
     * 备注：项目名称一般是验证码短信黑括号里的名字，比如【毛竹】验证码9876，则proj就是“毛竹”二字。如果没有黑括号，可以自己试着搜索其他相关的关键词。返回的项目id可以是0，如果返回0的话后续操作也和非0时的情况一样使用。
   
     * @return
     */
    public String queryProjs() throws UnsupportedEncodingException;

    /**
     获取号码[getPhone]
     调用实例：http://api.d1jiema.com/zc/data.php?code=getPhone&token=登录获取的token&projId=10056&phone=130xxxxxxxx&province=宁夏&cardType=全部
     参数：
     projId：调用查询项目接口返回的项目ID；如果查询项目时返回的为0，此处直接传入0就可以了；
     phone：可选，指定的号码，不填的话表示随机获取号码；
     province：可选，省份，具体名称可参照APP里的；
     cardType：可选，选值范围：[实卡,虚卡,全部]；
     成功返回值：手机号
     失败返回值：ERROR:错误信息
     备注：无
   
     * @return
     */
    public String getPhone(String phone,String province) throws UnsupportedEncodingException;

    /**
     * 获取短信[getMsg]
     * 调用实例：http://api.d1jiema.com/zc/data.php?code=getMsg&token=登录获取的token&phone=165xxxxxxxx&projId=10056
     * 参数：
     * phone：获取/指定的手机号；如果是指定手机号，不能直接在此处指定，要先指定号码请求一次[getPhone]接口才能调用此接口。
     * projId：项目ID；如果查询项目时返回的为0，直接传入0就可以了。
     * 尚未收到返回值：如果包含“[尚未收到]”字样，说明尚未查询到短信。
     * 成功返回值：短信内容
     * 失败返回值：ERROR:错误信息
   
     * @return
     */
    public String getMsg(String phone,String keyWord) throws UnsupportedEncodingException;

    /**
     * 释放号码[release]
     * 调用实例：http://api.d1jiema.com/zc/data.php?code=release&token=登录获取的token&phone=162xxxxxxxx&projId=10056
     * 参数：
     * phone：获取/指定的手机号；
     * projId：项目ID；
     * 成功返回值：释放结果
     * 失败返回值：ERROR:错误信息
     * 备注：如果释放失败无需一直重复释放。
   
     * @return
     */
    public String release(String phone,String projId) throws UnsupportedEncodingException;


    /**
     拉黑号码[block]
     调用实例：http://api.d1jiema.com/zc/data.php?code=block&token=登录获取的token&phone=162xxxxxxxx&projId=10056
     参数：
     phone：获取/指定的手机号；
     projId：项目ID；
     成功返回值：拉黑结果
     失败返回值：ERROR:错误信息
     备注：如果拉黑失败无需一直重复拉黑。
   
     * @return
     */
    public String block(String phone,String projId) throws UnsupportedEncodingException;

    /**
     发送短信[send]
     调用实例：http://api.d1jiema.com/zc/data.php?code=send&token=登录获取的token&phone=162xxxxxxxx&toPhone=1069xxxxxxxx&projId=10056&content=xxxx
     参数：
     phone：获取/指定的手机号，用于发送短信；如果是指定手机号，不能直接在此处指定，要先指定号码请求一次[getPhone]接口才能调用此接口。
     toPhone：要发送到的号码；
     projId：项目ID；
     content：发送内容；
     成功返回值：发送结果
     失败返回值：ERROR:错误信息
     备注：不能向个人手机号发送信息，发送垃圾信息会被封号。
   
     * @return
     */
    public String send(String sendPhone,String toPhone,String content) throws UnsupportedEncodingException;

    /**
     * 查询历史记录[queryUsed]
     * 说明：每分钟调用次数不能超过一次，否则系统会封禁账号的API调用。
     * 调用实例：http://api.d1jiema.com/zc/data.php?code=queryUsed&token=登录获取的token
     * 参数：token：登录获取的token
     * 成功返回值：历史记录，每条以换行符(\n)分割。
     * 失败返回值：ERROR:错误信息
     * 备注：返回最近24小时100条记录
   
     * @return
     */
    public String queryUsed();



}
