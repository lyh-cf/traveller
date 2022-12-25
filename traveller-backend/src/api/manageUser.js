import http from "@/utils/http"
//请求用户列表
export const getUserList=(params)=>{
    //params是一个对象
    return http.get('/manager/selectUserByPage',params)
}
//添加用户
export const addUser=(params)=>{
    //params是一个对象
    return http.post('/manager/addUser',params)
}
//修改用户
export const updateUser=(params)=>{
    //params是一个对象
    return http.put('/manager/updateUser',params)
}
//删除用户
export const deleteUser=(params)=>{
    //params是一个对象
    return http.delete('/manager/deleteUser',params)
}