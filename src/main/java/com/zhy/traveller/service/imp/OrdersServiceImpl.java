package com.zhy.traveller.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhy.traveller.mapper.OrdersMapper;
import com.zhy.traveller.pojo.Orders;
import com.zhy.traveller.service.IOrdersService;
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
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
