import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  //代理
  server: {
    port: 8080,
    proxy: {
      '/api': {
        //允许跨域
        changeOrigin: true,
        target: 'http://localhost:8888/', //这里后台的地址模拟的;应该填写你们真实的后台接口
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }

  },
})

