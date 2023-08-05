<template>
  <el-menu default-active="2" class="el-menu-vertical-demo" background-color="#545c64" text-color="#fff"
    active-text-color="#ffd04b" style="height: 100vh;">
    <el-sub-menu :key="`${index} + 1`" :index="`${index} + 1`" v-for="(item, index) in menuList">
      <template #title>
        <span>{{ item.name }}</span>
      </template>
      <el-menu-item v-for="(child, key) in item.childMenuList" @click="onSelect(`${child.path}`)">{{ child.name
      }}</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script setup lang="ts">

import { ref, onMounted } from 'vue';
import { router } from '../router';



const menuList = ref([
      {name:"职工管理",
      childMenuList:[{name:"职工统计",path:'/aaa'},{name:"职工列表",path:'emplist'},]
    },
    {name:"财务管理",
      childMenuList:[{name:"财务统计",path:'/bbb'},{name:"财务列表",path:'moneylist'},]
    }
])


const onSelect = (path: any) => {
  console.log(path);

  router.push(path)
}

onMounted(() => {
})




</script>



<style scoped lang="less"></style>