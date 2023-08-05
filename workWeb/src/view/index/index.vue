<template>
    <div class="background">
        <div class="login-title">职工系统</div>
        <div class="form">
            <Login />
            
        </div>
    </div>
</template>

<script setup lang="ts">
import Login from './login.vue';



</script>

<style lang="less" scoped>
.background {
    background-image: url('../../assets/dc.jpg');
    background-repeat: no-repeat;
    background-size: 100% 100%;
    width: 100vw;
    height: 100vh;
}

.form {
    border: 2px solid rgba(158, 241, 179, 0.2);
    border-radius: 10px;
    padding: 10px;
    width: 30vw;
    margin: 0 auto;
    text-align: center;
    background-color: rgba(17, 17, 17, 0.2);
}

.login-title {
    text-align: center;
    font-size: 3rem;
    font-weight: bold;
    color: #000;
    padding-top: 100px;
    padding-bottom: 20px;
}

</style>