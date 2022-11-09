package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Dianpu;

/**
 * 店铺Mapper接口
 * 
 * @author ruoyi
 * @date 2022-08-27
 */
public interface DianpuMapper 
{
    /**
     * 查询店铺
     * 
     * @param id 店铺主键
     * @return 店铺
     */
    public Dianpu selectDianpuById(Long id);

    /**
     * 查询店铺列表
     * 
     * @param dianpu 店铺
     * @return 店铺集合
     */
    public List<Dianpu> selectDianpuList(Dianpu dianpu);

    /**
     * 新增店铺
     * 
     * @param dianpu 店铺
     * @return 结果
     */
    public int insertDianpu(Dianpu dianpu);

    /**
     * 修改店铺
     * 
     * @param dianpu 店铺
     * @return 结果
     */
    public int updateDianpu(Dianpu dianpu);

    /**
     * 删除店铺
     * 
     * @param id 店铺主键
     * @return 结果
     */
    public int deleteDianpuById(Long id);

    /**
     * 批量删除店铺
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDianpuByIds(String[] ids);
}
