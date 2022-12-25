<template>
  <div class="header-container">
    <div class="l-content">
      <el-button @click="handleMenu" icon="el-icon-menu" size="mini"></el-button>
      <!-- 面包屑 -->
      <span class="text">首页</span>
    </div>
    <div class="r-content">
      <!-- 下拉菜单 -->
      <el-dropdown @command="handleClick">
        <span class="el-dropdown-link">
          <img class="user" :src="headImage" />
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>个人中心</el-dropdown-item>
          <el-dropdown-item command="cancel">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import Cookie from 'js-cookie'
import {logoutManager} from '../api/index'
export default {
  computed:{
      headImage(){
         return JSON.parse(Cookie.get('user')).headImage
      }
  },
  data() {
    return {

    };
  },
  methods: {
    handleMenu() {
      this.$store.commit('collapseMenu')
    },
    handleClick(command) {
      if (command === 'cancel') {
        logoutManager(null).then(res => {
          if (res.success === true) {
            //清除cookie中的token
            Cookie.remove('user')
            this.$message({
              message: '退出成功',
              type: 'success'
            });
            //跳转到登录页面
            this.$router.push('/login')
          }
        })
      }
    }
  },

};
</script>

<style lang="less" scoped>
.header-container {
  padding: 0 20px;
  background-color: black;
  height: 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;

  .text {
    color: white;
    font-size: 14px;
    margin-left: 20px;
  }

  .r-content {
    .user {
      width: 50px;
      height: 55px;
      border-radius: 50%;
    }
  }
}
</style>
