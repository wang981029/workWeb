<template>
  <div class="log-title">系统登录</div>
  <el-form :model="form" label-width="60px" :rules="rules">
      <el-form-item label="姓名" prop="empName">
          <el-input v-model="form.empName" placeholder="请输入姓名"  style="width: 95%;"/>
      </el-form-item>
      <el-form-item label="密码" prop="password" >
          <el-input v-model="form.password" placeholder="请输入密码" type="password"  style="width: 95%;" />
      </el-form-item>
      <el-form-item style="margin-left: 35%;">
          <el-button type="primary" @click="onLogin">登录</el-button>
      </el-form-item>
  </el-form>
</template>

<script setup lang="ts">
import axios from 'axios';
import { reactive } from 'vue';
import { BASE_URL, errMsg, sucMsg } from '../../util/Axios';
import { router } from '../../router';


const form = reactive({
  empName: "",
  password: "",
})

const onLogin = () => {
  axios.post(BASE_URL + "employee/login", form).then(response => {
      if (response.data.success) {
          sucMsg("登录成功");
          setTimeout(() => {
              router.push("/Welcome")
          }, 200)
      } else {
          errMsg(response.data.message)
      }
  }).catch(error => {
    errMsg(error.data.message)
  })
}



//规则
const rules = reactive({
  empName: [
      { required: true, message: '请输入姓名', trigger: 'blur' },

  ],
  password: [
      { required: true, message: '请输入密码', trigger: 'blur' },


  ],


})
</script>

<style lang="less" scoped>
.log-title{
  letter-spacing: 4px;
  animation: showup 3s forwards;
  color: #000;
  font-size: 30px;
}
</style>