import { createRouter, createWebHashHistory } from "vue-router";

export const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: "/login",
            name: "login",
            component: () => import("../view/index/login.vue"),
        },
        {
            path: "/",
            name: "/",
            component: () => import("../view/index/index.vue"),
        },    
        {
            path: "/index",
            name: "index",
            component: () => import("../view/index/index.vue"),
        },
        {
            path: "/Welcome",
            name: "Welcome",
            component: () => import("../view/index/Welcome.vue"),
        },
         {
            path: "/emplist",
            name: "emplist",
            component: () => import("../view/index/emplist.vue"),
        }
    ]
})