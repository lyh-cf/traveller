package com.zhy.traveller.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhy.traveller.common.Response;
import com.zhy.traveller.converter.UserMapper;
import com.zhy.traveller.dto.UserDTO;
import com.zhy.traveller.pojo.Sights;
import com.zhy.traveller.pojo.Ticket;
import com.zhy.traveller.pojo.User;
import com.zhy.traveller.service.SightsService;
import com.zhy.traveller.service.TicketService;
import com.zhy.traveller.service.UserService;
import com.zhy.traveller.utils.MD5;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 *文件名: ManagerController
 *创建者: 罗义恒
 *描述:
 *创建时间:2022/12/4 0:11
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;

    @Autowired
    private SightsService sightsService;

//    @PostMapping("/loginManager")
//    public Response loginManager(HttpServletResponse response, @RequestBody UserMsg userMsg) {
//        userMsg.setPassWord(MD5.Encrypt(userMsg.getPassWord()));
//
//    }
    @GetMapping("/selectTicketByPage")
    public Response selectTicketByPage(@RequestParam(defaultValue = "1")int nowPage){
        IPage<Ticket> page=new Page<>(nowPage,10);
        ticketService.selectTicketPage(page);
        System.out.println(page.getRecords());
        return Response.success(page);
    }

    @PostMapping("/addTicket")
    public Response addTicket(@RequestBody Ticket ticket){
        ticket.setNowNum(ticket.getTotalNum());
        ticketService.save(ticket);
        return Response.success(ticket);
    }

    @RequestMapping(value ="/updateTicket",method = RequestMethod.PUT)
    public Response updateTicket(@RequestBody Ticket ticket){
        ticketService.updateTicket(ticket);
        return Response.success();
    }
    @RequestMapping(value ="/deleteTicket",method = RequestMethod.DELETE)
    public Response deleteTicket(@RequestBody Ticket ticket){
        ticketService.deleteTicket(ticket.getPkId());
        return Response.success();
    }
    @GetMapping("/selectUserByPage")
    public Response selectUserByPage(@RequestParam(defaultValue = "1") int nowPage, @RequestParam(required = false)String userName) {
        IPage<User> page = new Page<>(nowPage, 10);
        userService.selectUserPage(page,userName);
        return Response.success(page);
    }
    @PostMapping("/addUser")
    public Response addUser(@RequestBody UserDTO userMsg){
        if (!userService.checkUserEmailIsExist(userMsg.getUserEmail())) {
            userMsg.setPassWord(MD5.Encrypt(userMsg.getPassWord()));
            User user= UserMapper.INSTANCT.conver(userMsg);
            userService.save(user);
            return Response.success();
        }
        else return Response.error();
    }
    @RequestMapping(value ="/updateUser",method = RequestMethod.PUT)
    public Response updateUser(@RequestBody UserDTO userMsg){
        User user=UserMapper.INSTANCT.conver(userMsg);
        userService.updateUser(user);
        return Response.success();
    }
    @RequestMapping(value ="/deleteUser",method = RequestMethod.DELETE)
    public Response deleteUser(@RequestBody UserDTO userMsg){
        userService.deleteUser(userMsg.getUserEmail());
        return Response.success();
    }
    @GetMapping("/selectSightsByPage")
    public Response selectSightsByPage(@RequestParam(defaultValue = "1") int nowPage) {
        IPage<Sights> page = new Page<>(nowPage, 10);
        sightsService.selectSightsPage(page);
        return Response.success(page);
    }
    @PostMapping("/addSights")
    public Response addSights(@RequestBody Sights sights){
        sights.setNowNum(sights.getTotalNum());
        sightsService.save(sights);
        return Response.success();
    }
    @RequestMapping(value ="/updateSights",method = RequestMethod.PUT)
    public Response updateSights(@RequestBody Sights sights){
        sightsService.updateSights(sights);
        return Response.success();
    }
    @RequestMapping(value ="/deleteSights",method = RequestMethod.DELETE)
    public Response deleteSights(@RequestBody Sights sights){
        sightsService.deleteSights(sights.getPkId());
        return Response.success();
    }

}
