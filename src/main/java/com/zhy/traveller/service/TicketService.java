package com.zhy.traveller.service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhy.traveller.mapper.TicketMapper;
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
public class TicketService extends ServiceImpl<TicketMapper, Ticket>{
    public void selectTicketPage(IPage<Ticket> pageParam) {
        LambdaQueryWrapper<Ticket>queryWrapper=new LambdaQueryWrapper<>();
        //(boolean condition, boolean isAsc, R column)
        queryWrapper.eq(Ticket::getIsDeleted,0);
        queryWrapper.orderBy(true,false,Ticket::getState);
        queryWrapper.orderBy(true,true,Ticket::getStartDay);
        queryWrapper.orderBy(true,true,Ticket::getStartTime);
        baseMapper.selectPage(pageParam, queryWrapper);
    }
    public void updateTicket(Ticket ticket){
        LambdaUpdateWrapper<Ticket> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(Ticket::getPkId,ticket.getPkId());
        update(ticket,updateWrapper);
    }
    public void deleteTicket(Long pkId){
        LambdaUpdateWrapper<Ticket> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(Ticket::getPkId,pkId);
        updateWrapper.set(Ticket::getIsDeleted,1);
        update(updateWrapper);
    }

}
