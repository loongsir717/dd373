package com.ruoyi.common.enums;

/**
 * 操作人类别
 *
 * @author ruoyi
 */
public enum CallBackType
{
    OK("200", "正常"), FAIL("1", "失败"), ERROR("2", "错误");

    private final String code;
    private final String info;

    CallBackType(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}

