<template>
  <el-menu default-active="home" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
    :collapse="isCollapse" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
    >
    <h3>{{ isCollapse ? '后台' : '旅行者后台管理' }}</h3>
    <!-- 没有子菜单 -->
    <el-menu-item @click="clickMenu(item)" v-for="item in noChildren" :key="item.name" :index="item.name">
      <!-- 字符串拼接图标 -->
      <i :class="`el-icon-${item.icon}`"></i>
      <span slot="title">{{ item.label }}</span>
    </el-menu-item>
    <!-- 有子菜单 -->
    <el-submenu v-for="item in hasChildren" :key="item.label" :index="item.label">
      <template slot="title">
        <i :class="`el-icon-${item.icon}`"></i>
        <span slot="title">{{ item.label }}</span>
      </template>
      <el-menu-item-group v-for="subItem in item.children" :key="subItem.path">
        <el-menu-item @click="clickMenu(subItem)" :index="subItem.path"> <i :class="`el-icon-${subItem.icon}`"></i>{{ subItem.label }}</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>
  
  
<script>
export default {
  data() {
    return {
      //是否水平折叠起菜单
      //isCollapse: false,
      menuData: [
        {
          path: "/home",
          name: "home",
          label: "首页",
          icon: "s-home",
          url: "Home/Home",
        },
        {
          label: "用户管理",
          icon: "user",
          children: [
            {
              path: "/user",
              name: "user",
              icon: "menu",
              label: "用户列表",
              url: "UserManage/UserManage",
            },
          ],
        },
        {
          label: "车票列表",
          icon: "orange",
          children: [
            {
              path: "/ticket",
              name: "ticket",
              icon: "menu",
              label: "车票管理",
              url: "TicketManage/TicketManage",
            },
          ],
        },
        {
          label: "景点管理",
          icon: "star-on",
          children: [
            {
              path: "/sights",
              name: "sights",
              icon: "menu",
              label: "景点列表",
              url: "SightsManage/SightsManage",
            }
          ],
        },
        {
          path: "/chat",
          name: "chat",
          label: "用户私信",
          icon: "chat-line-square",
          url: "UserChat/UserChat",
        },
        {
          label: "订单管理",
          icon: "s-order",
          children: [
            {
              path: "/order",
              name: "order",
              icon: "menu",
              label: "订单列表",
              url: "Order/Order",
            }
          ],
        },
      ],
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    //点击菜单
    clickMenu(item) {
      //当页面的路由和跳转的路由不一样时才允许跳转，否则会报错
      if (this.$route.path !== item.path) {
        //this指vue实例
        this.$router.push(item.path)
      }
    }
  },
  computed: {
    //没有子菜单
    noChildren() {
      //filter起过滤作用，参数内是一个函数，符合条件就返回
      //item是menuData的任一项
      return this.menuData.filter(item => !item.children)
    },
    //有子菜单
    hasChildren() {
      return this.menuData.filter(item => item.children)
    },
    isCollapse() {
      return this.$store.state.tab.isCollapse
    }
  }
};
</script>
<style lang="less" scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.el-menu {
  border-right: none;
  height: 100vh; //填满竖直高度

  h3 {
    color: white;
    text-align: center;
    line-height: 48px;
    font-size: 16px;
    // 字体粗细
    font-weight: 400;
  }
}
</style>