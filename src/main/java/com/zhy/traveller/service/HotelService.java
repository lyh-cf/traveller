package com.zhy.traveller.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zhy.traveller.mapper.HotelMapper;
import com.zhy.traveller.pojo.Hotel;
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
public class HotelService extends ServiceImpl<HotelMapper, Hotel> {

}
