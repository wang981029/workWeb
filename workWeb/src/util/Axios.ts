import axios from "axios";
import { ElMessage } from 'element-plus'
import { router } from "../router";

//定义基础路径
const BASE_URL = "api/"

//创建一个请求实例
const service = axios.create({
    baseURL: BASE_URL,
    headers: {
        //配置请求数据类型
        "content-type": "application/json",
    },
    timeout: 30 * 1000,
})


//创建请求拦截


//创建响应拦截
service.interceptors.response.use(
    response => {
        console.info("~~~~~~~响应拦截器~~~~~~~", response)
        if (response?.data?.code == 401) {
            sessionStorage.clear();
            errMsg(response.data.message)
            router.push("/index");
        } else if (response?.data?.success) {
            return response.data;
        } else {
            errMsg(response.data.message)
            return null;
        }
    },
    //接口错误状态处理，也就是说无响应时的处理
    err => errMsg(err.message)
)







//响应成功的提示
const sucMsg = (msg: string) => {
    ElMessage({ showClose: true, message: msg, type: 'success', duration: 2000 })
}


//响应错误的提示
const errMsg = (msg: string) => {
    ElMessage({ showClose: true, message: msg, type: 'error', duration: 2000 })
}

//创建公共的POST请求
const POST = (url: string, param = {}) => service({ url: url, method: "post", data: param });

//创建POST直接数据
const POSTDATA = async (url: string, param = {}) => {
    const data = await POST(url, param);
    return data?.data;
}


//暴露
export {
    BASE_URL,
    sucMsg,
    errMsg,
    POST,
    POSTDATA,
}