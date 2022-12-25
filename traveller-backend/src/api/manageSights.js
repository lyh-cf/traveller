import http from "@/utils/http"
//请求景点列表
export const getSightsList=(params)=>{
    //params是一个对象
    return http.get('/manager/selectSightsByPage',params)
}
//添加景点
export const addSights=(params)=>{
    //params是一个对象
    return http.post('/manager/addSights',params)
}
//修改景点
export const updateSights=(params)=>{
    //params是一个对象
    return http.put('/manager/updateSights',params)
}
//删除景点
export const deleteSights=(params)=>{
    //params是一个对象
    return http.delete('/manager/deleteSights',params)
}
