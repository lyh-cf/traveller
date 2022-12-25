import http from "@/utils/http"
//请求车票列表
export const getTicketList=(params)=>{
    //params是一个对象
    return http.get('/manager/selectTicketByPage',params)
}
//添加车票
export const addTicket=(params)=>{
    //params是一个对象
    return http.post('/manager/addTicket',params)
}
//修改车票
export const updateTicket=(params)=>{
    //params是一个对象
    return http.put('/manager/updateTicket',params)
}
//删除车票
export const deleteTicket=(params)=>{
    //params是一个对象
    return http.delete('/manager/deleteTicket',params)
}