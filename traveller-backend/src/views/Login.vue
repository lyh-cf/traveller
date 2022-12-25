<template>
    <el-form ref="form" :model="form" label-width="70px" :inline="true" class="login_container" :rules="rules">
        <h3 class="login_title">旅行者后台登录</h3>
        <el-form-item label="邮箱" prop="userEmail">
            <el-input placeholder="请输入用户邮箱" v-model="form.userEmail"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="passWord">
            <el-input v-model="form.passWord" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item class="login_sumbit">
            <el-button style="margin-left:130px" @click="submit" type="primary">登录</el-button>
        </el-form-item>
    </el-form>
</template>
<script>
import { loginManager } from '../api/index'
import Cookie from 'js-cookie'
export default {
    data() {
        return {
            form: {
                userEmail: '111@qq.com              ',
                passWord: 'yiheng'
            },
            rules: {
                // blur失去焦点时出发
                userEmail: [{ required: true, message: "请输入管理员邮箱", trigger: "blur" }],
                passWord: [{ required: true, message: "请输入密码", trigger: "blur" }],
            }
        }
    },
    methods: {
        //登录
        submit() {
            this.$refs.form.validate((valid) => {
                if (valid) {
                    loginManager(this.form).then(res => {
                        if (res.success === true) {
                            this.$message({
                                message: '登录成功',
                                type: 'success'
                            });
                            let minute = 30;
                            let expires = new Date(new Date() * 1 + minute*60* 1000);
                            Cookie.set('user', JSON.stringify(res.data), { expires: expires }); //  30分钟后失效
                            //跳转至首页
                            this.$router.push('/home');
                        } else {
                            this.$message({
                                message: '添加失败',
                                type: 'error'
                            });
                        }
                    })
                }3
            })
        }
    }
}
</script>
<style lang="less" scoped>
.login_container {
    width: 400px;
    border: 1px solid #eaeaea;
    margin: 200px auto;
    padding: 35px 35px 15px 35px;
    background-color: #fff;
    border-radius: 15px;
    box-shadow: 0 0 25px #cacaca;
    //设置盒子模型
    box-sizing: border-box;

    .login_title {
        text-align: center;
        margin-bottom: 40px;
    }

    .el-input {
        width: 240px;
    }

    .login_sumbit {
        margin: 10px auto 0px auto;
    }

}
</style>
