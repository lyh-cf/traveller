package com.zhy.traveller.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zhy.traveller.mapper.PassengerMapper;
import com.zhy.traveller.pojo.Passenger;
import com.zhy.traveller.service.IPassengerService;
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
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger> implements IPassengerService {

}
