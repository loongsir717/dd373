package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DianpuMapper;
import com.ruoyi.system.domain.Dianpu;
import com.ruoyi.system.service.IDianpuService;
import com.ruoyi.common.core.text.Convert;

/**
 * 店铺Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-27
 */
@Service
public class DianpuServiceImpl implements IDianpuService 
{
    @Autowired
    private DianpuMapper dianpuMapper;

    /**
     * 查询店铺
     * 
     * @param id 店铺主键
     * @return 店铺
     */
    @Override
    public Dianpu selectDianpuById(Long id)
    {
        return dianpuMapper.selectDianpuById(id);
    }

    /**
     * 查询店铺列表
     * 
     * @param dianpu 店铺
     * @return 店铺
     */
    @Override
    public List<Dianpu> selectDianpuList(Dianpu dianpu)
    {
        return dianpuMapper.selectDianpuList(dianpu);
    }

    /**
     * 新增店铺
     * 
     * @param dianpu 店铺
     * @return 结果
     */
    @Override
    public int insertDianpu(Dianpu dianpu)
    {
        return dianpuMapper.insertDianpu(dianpu);
    }

    /**
     * 修改店铺
     * 
     * @param dianpu 店铺
     * @return 结果
     */
    @Override
    public int updateDianpu(Dianpu dianpu)
    {
        return dianpuMapper.updateDianpu(dianpu);
    }

    /**
     * 批量删除店铺
     * 
     * @param ids 需要删除的店铺主键
     * @return 结果
     */
    @Override
    public int deleteDianpuByIds(String ids)
    {
        return dianpuMapper.deleteDianpuByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除店铺信息
     * 
     * @param id 店铺主键
     * @return 结果
     */
    @Override
    public int deleteDianpuById(Long id)
    {
        return dianpuMapper.deleteDianpuById(id);
    }
}
