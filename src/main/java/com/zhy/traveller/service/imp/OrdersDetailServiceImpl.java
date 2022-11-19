package com.zhy.traveller.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zhy.traveller.mapper.OrdersDetailMapper;
import com.zhy.traveller.pojo.OrdersDetail;
import com.zhy.traveller.service.IOrdersDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 罗义恒
 * @since 2022-11-19
 */
@Service
public class OrdersDetailServiceImpl extends ServiceImpl<OrdersDetailMapper, OrdersDetail> implements IOrdersDetailService {

}
