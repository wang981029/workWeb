<template>
    <!-- 面包屑 -->
    <el-breadcrumb separator="/" class="breadcrumb">
        <el-breadcrumb-item>职工管理</el-breadcrumb-item>
        <el-breadcrumb-item>职工列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索框 -->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline" >
        <el-form-item label="用户名">
            <el-input v-model="searchForm.empName" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="部门">
            <el-select v-model="searchForm.deptName" placeholder="部门" :clearable="true">
                <el-option label="业务部" value="业务部" />
                <el-option label="人事部" value="人事部" />
                <el-option label="后勤部" value="后勤部" />
            </el-select>
        </el-form-item>
        <el-form-item label="学历">
            <el-select v-model="searchForm.empDegreeName" placeholder="学历" :clearable="true">
                <el-option label="大专" value="大专" />
                <el-option label="本科" value="本科" />
                <el-option label="研究生" value="研究生" />
            </el-select>
        </el-form-item>
      
        <el-form-item>
            <el-button type="primary" @click="onSearch">搜索</el-button>
            <el-button type="success" @click="onAdd" >添加</el-button>
            <el-button type="primary" @click="exportOrder" style="float: right;">导出</el-button>   
        </el-form-item>
    </el-form>

    <!-- table 数据表格 -->
    <el-table :data="userList" border style="width: 80%;overflow-y: auto;">
      
        <el-table-column prop="empName" label="姓名" >
        </el-table-column>
   
        <el-table-column prop="sex" label="性别" width="120">
        </el-table-column>
        <el-table-column prop="age" label="年龄" width="120" >
        </el-table-column>
        <el-table-column prop="deptName" label="部门名称" >
        </el-table-column>
      
        <el-table-column prop="empDegreeName" label="学历" >
            <template #default="scope">
                <span style="color: aqua;" v-if="scope.row.empDegreeName == '大专'">大专</span>
                <span style="color: red;" v-if="scope.row.empDegreeName == '本科'">本科</span>
                <span style="color: rgb(25, 0, 155);" v-if="scope.row.empDegreeName == '研究生'">研究生</span>
            </template>
        </el-table-column>
        <el-table-column align="center" label="操作" width="250">
            <template #default="{ row }">
                <el-button link type="primary" size="small" @click.prevent="onShow(row)">
                    查看
                </el-button>
                <el-button link type="info" size="small" @click.prevent="onUpdate(row)">
                    修改
                </el-button>
                <el-button link type="danger" size="small" @click.prevent="onDel(row)" >
                    删除
                </el-button>
            </template>
        </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination v-model:page-size="size" :page-sizes="[5, 10, 15, 20]" layout="total, sizes, prev, pager, next, jumper"
        :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange" />

    <!-- 添加、查看、编辑 -->
    <el-dialog v-model="showDialogVisible" :title="title" width="35%">
        <el-form :model="showForm" label-width="80px" :rules="rules">
            <el-form-item label="用户名" prop="empame">
                <el-input v-model="showForm.empName" placeholder="请输入用户名" :readonly="isRead"></el-input>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
                <el-input v-model="showForm.age" placeholder="请输入年龄" :readonly="isRead"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex">
                <el-select v-model="showForm.sex" placeholder="请选择" style="width: 100%;" :disabled="isRead">
                    <el-option label="男" value='男'></el-option>
                    <el-option label="女" value='女'></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="部门名称" prop="deptName">
                <el-select v-model="showForm.deptName" placeholder="请选择角色" :disabled="isRead" style="width: 100%;">
                    <el-option label="业务部" value="业务部" />
                <el-option label="人事部" value="人事部" />
                <el-option label="后勤部" value="后勤部" />
                </el-select>
            </el-form-item>
            <el-form-item label="学历" prop="deptName">
                <el-select v-model="showForm.empDegreeName" placeholder="请选择角色" :disabled="isRead" style="width: 100%;">
                    <el-option label="大专" value="大专" />
                    <el-option label="本科" value="本科" />
                    <el-option label="研究生" value="研究生" />
                </el-select>
            </el-form-item>
            <span slot="footer" class="dialog-footer" v-if="!isRead">
                <el-button @click="showDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="onSubmit">确 定</el-button>
            </span>

        </el-form>

    </el-dialog>
    
</template>

<script setup lang="ts">
import { reactive, ref, onMounted } from 'vue';
import { BASE_URL, POST, errMsg } from '../../util/Axios';
import axios from 'axios';

//搜索栏
const searchForm = reactive({
   empName:'',
   deptName:null,
   empDegreeName:null
})
const onSearch = async (param: any = {}) => {
    param.empName = searchForm.empName
    param.deptName = searchForm.deptName
    param.empDegreeName = searchForm.empDegreeName
    console.log(param.empame,param.deptName,param.empDegreeName);
    
    param.current = current.value
    param.size = size.value
    
    const data = await POST("employee/getPage", param);
    userList.value = data?.data.records
    current.value = data?.data.current
    total.value = data?.data.total
    size.value = data?.data.size
}
//弹出框
const title = ref('')
const isRead = ref(false)
const userList = ref([])
const showDialogVisible = ref(false);
const showForm = ref({
    id: null,
    sex: null,
    age:null,
    empName:'',
   deptName:'',
   empDegreeName:'',
    deleted: 0,
})
//分页
const total: any = ref<number>(0)
const current = ref(1)
const size = ref(5)
//挂载
onMounted(() => {
    init();
})




//规则
const rules = reactive({
    empName: [
        { required: true, message: '请输入用户名', trigger: 'blur' },

    ],
    deptName: [
        { required: true, message: '请选择部门', trigger: 'blur' },

    ],
    age: [
        { required: true, message: '请输入年龄', trigger: 'blur' },
    ],
    sex: [
        { required: true, message: '请选择性别', trigger: 'blur' },

    ],
    empDegreeName: [
        { required: true, message: '请选择学历', trigger: 'blur' },
    ]
})


//初始化
const init = async (param: any = {}) => {
    param.current = current.value
    param.size = size.value
    const data = await POST("employee/getPage", param);
    userList.value = data?.data.records;
    total.value = data?.data.total
    console.log(total.value);
}
//根据id查询
const onShow = async ({ id }) => {
    showDialogVisible.value = true
    isRead.value = true;
    const data = await POST("employee/getById", { id: id })
    showForm.value = data?.data
}




const onAdd = () => {
    title.value = '添加信息'
    showDialogVisible.value = true;
    isRead.value = false;
    showForm.value = {
        id: null,
        sex: null,
        age:null,
        empName:'',
       deptName:'',
       empDegreeName:'',
       deleted: 0,
    };


}

const onSubmit = async () => {
    let url = "employee/add";
    if (showForm.value.id) {
        url = "employee/update"
    }
    const data = await POST(url, showForm.value);
    if (data?.success) {
        showDialogVisible.value = false;
        init();
    }


}

const onUpdate = async ({ id }) => {
    title.value = '修改信息',
    showDialogVisible.value = true
    const data = await POST("employee/getById", { id: id })
    showForm.value = data?.data
    isRead.value = false;

}



const onDel = async ({ id }) => {
    await POST("employee/delById", { id: id });
    init()
}




const handleSizeChange = (val: number) => {
    console.log(`${val} items per page`)
    size.value = val
    init()
}
const handleCurrentChange = (val: number) => {
    console.log(`current page: ${val}`)
    current.value = val
    init()
}


const  exportOrder=async(param: any = {})=>{
   //请求参数
    param =searchForm;
    param.current = current.value
    param.size = size.value
           const config = {
                    responseType: 'blob',
                    headers: {
                        "content-type": "application/json",
                        "token": null
                    },
                }
                await axios.post(BASE_URL+`employee/export`, param, config).then(res => {
                    console.info("~~~~~~~~~~~~~~~~~~",res)
                    if (res.status == 200) {
                        const link = document.createElement('a')
                        const blob = new Blob([res.data], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" })
                        link.style.display = 'none'
                        link.href = URL.createObjectURL(blob)
                        link.download = res.headers['content-disposition'] //下载后文件名
                        document.body.appendChild(link)
                        link.click()
                        document.body.removeChild(link)
                    } else {
                        //后端响应状态码 201，json数据
                        const reader = new FileReader();
                        reader.readAsText(res.data, 'utf-8');
                        reader.onload = () => errMsg(reader.result);
                    }
                })
            }


</script>

<style scoped>
.demo-form-inline{
    float: left;
    margin-top: 10px;
}
</style>