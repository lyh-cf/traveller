import http from "@/utils/http"
//管理员登录
export const loginManager=(params)=>{
    //params是一个对象
    return http.post('/user/login',params)
}

//退出登录
export const logoutManager=(params)=>{
    //params是一个对象
    return http.post('/user/signout',params)
}

