import { createApp } from 'vue'


import './style.css'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

import App from './App.vue'
import { router } from './router'


const app = createApp(App)

app.use(ElementPlus, {
    locale: zhCn,
})


//整个应用支持路由。
app.use(router)
app.mount('#app')
