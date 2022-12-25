<template>
  <el-row>
    <!-- 24份，栅格布局 -->
    <el-col :span="8">
      <div class="grid-content bg-purple">
        <el-card class="box-card">
          <div class="user">
            <img :src="headImage" />
            <div class="userInfo">
              <p class="name">Admin</p>
              <p class="access">管理员</p>
            </div>
          </div>
          <div class="login-info"></div>
        </el-card>
      </div>
    </el-col>
    <el-col :span="16">
      <div class="num">
        <el-card v-for="item in countData" :key="item.name" :body-style="{ display: 'flex', padding: 0 }">
          <i class="icon" :class="`el-icon-${item.icon}`" :style="{ background: item.color }"></i>
          <div class="detail">
            <p class="price">￥{{ item.value }}</p>
            <p class="desc">{{ item.name }}</p>
          </div>
        </el-card>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import Cookie from 'js-cookie'
export default {
  data() {
    return {
      countData: [
        {
          name: "今日支付订单",
          value: 1234,
          icon: "success",
          color: "#2ec7c9",
        },
        {
          name: "今日收藏订单",
          value: 210,
          icon: "star-on",
          color: "#ffb980",
        },
        {
          name: "今日未支付订单",
          value: 1234,
          icon: "s-goods",
          color: "#5ab1ef",
        },
        {
          name: "本月支付订单",
          value: 1234,
          icon: "success",
          color: "#2ec7c9",
        },
        {
          name: "本月收藏订单",
          value: 210,
          icon: "star-on",
          color: "#ffb980",
        },
        {
          name: "本月未支付订单",
          value: 1234,
          icon: "s-goods",
          color: "#5ab1ef",
        },
      ],
    };
  },
  computed:{
      headImage(){
         return JSON.parse(Cookie.get('user')).headImage
      }
  }
};
</script>
<style lang='less' scoped>
.user {
  display: flex;
  align-items: center;

  img {
    margin-right: 40px;
    width: 150px;
    height: 150px;
    border-radius: 50%;
  }

  .userInfo {
    .name {
      font-size: 32px;
      margin-bottom: 10px;
    }

    .access {
      color: #999;
    }
  }
}

.num {
  display: flex;
  // 多余的会自己的重新排列成一行
  flex-wrap: wrap;
  // 让item贴边
  justify-content: space-between;

  .icon {
    width: 80px;
    height: 80px;
    font-size: 30px;
    text-align: center;
    line-height: 80px;
    color: #fff;
  }

  .detail {
    display: flex;
    flex-direction: column;
    justify-content: center;
    margin-left: 15px;

    .price {
      font-size: 30px;
      margin-bottom: 10px;
      line-height: 30px;
      height: 30px;
    }

    .desc {
      font-size: 14px;
      color: #999;
      text-align: center;
    }
  }

  .el-card {
    width: 32%;
    margin-bottom: 20px;
  }
}
</style>
