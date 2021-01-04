<template>
    <el-menu
        default-active="OverView"
        router
        active-text-color="blue"
        style="background-color: rgba(255,255,255,0.5)"
    >
        <el-menu-item index="OverView">
            <template slot="title">
                <i class="iconfont icon-jiajushenghuo" style="margin: 0 3px 2px 0;font-size:15px"></i>
                <span>总览</span>
            </template>
        </el-menu-item>
        <el-menu-item index="LivingRoom">
            <template slot="title">
                <i class="iconfont icon-jiajujiafang" style="margin: 0 3px 2px 0"></i>
                <span>客厅</span>
            </template>
        </el-menu-item>
        <el-menu-item index="DiningRoom" >
            <template slot="title">
                <i class="el-icon-fork-spoon" style="margin: 0 0 2px 0;font-size:19px"></i>
                <span>餐厅</span>
            </template>
        </el-menu-item>
        <el-menu-item index="BedRoom">
            <template slot="title">
                <i class="iconfont icon-jiajubaihuo" style="margin: 0 3px 2px 0"></i>
                <span>卧室</span>
            </template>
        </el-menu-item>
        <el-menu-item index="BathRoom">
            <template slot="title">
                <i class="iconfont icon-jiajuyugang-" style="margin: 0 3px 2px 0"></i>
                <span>浴室</span>
            </template>
        </el-menu-item>
        <el-menu-item index="Balcony">
            <template slot="title">
                <i class="iconfont icon-yangtai" style="margin: 0 3px 2px 0"></i>
                <span>阳台</span>
            </template>
        </el-menu-item>
    </el-menu>
</template>

<script>
export default {
    name: "SideMenu",
    data() {
        return {}
    },
    methods: {}
}
</script>

<style scoped>
el-menu{
    ;
}
</style>