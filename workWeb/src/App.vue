
<template>
  <div class="common-layout" v-if="isShowHeader">
     <el-container>
       
         <el-container>
             <el-aside width="200px">
                 <Aside />
             </el-aside>
             <el-main>
                 <router-view />
             </el-main>
         </el-container>
     </el-container>
 </div>
 <router-view v-else />
</template>


<script setup lang="ts">
import {ref,watchEffect} from 'vue'
import Aside from "./components/Aside.vue";
import { useRoute } from 'vue-router';

const isShowHeader= ref(false);

const router =useRoute();
watchEffect(()=>{
const path = router.path.trim();
 if (path == "/" || path == "/index" || path == "/login") {
     isShowHeader.value = false;
 } else {
     isShowHeader.value = true;
 }
})
</script>


<style scoped>
.logo {
height: 6em;
padding: 1.5em;
will-change: filter;
transition: filter 300ms;
}
.logo:hover {
filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
