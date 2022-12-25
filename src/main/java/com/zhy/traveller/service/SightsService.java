package com.zhy.traveller.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhy.traveller.mapper.SightsMapper;
import com.zhy.traveller.pojo.Sights;
import com.zhy.traveller.pojo.Ticket;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 罗义恒
 * @since 2022-11-25
 */
@Service
public class SightsService extends ServiceImpl<SightsMapper, Sights>{
    public void selectSightsPage(IPage<Sights> pageParam) {
        LambdaQueryWrapper<Sights> queryWrapper=new LambdaQueryWrapper<>();
        //(boolean condition, boolean isAsc, R column)
        queryWrapper.eq(Sights::getIsDeleted,0);
        baseMapper.selectPage(pageParam, queryWrapper);
    }
    public void updateSights(Sights sights){
        LambdaUpdateWrapper<Sights> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(Sights::getPkId,sights.getPkId());
        update(sights,updateWrapper);
    }
    public void deleteSights(Long pkId){
        LambdaUpdateWrapper<Sights> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(Sights::getPkId,pkId);
        updateWrapper.set(Sights::getIsDeleted,1);
        update(updateWrapper);
    }
}
