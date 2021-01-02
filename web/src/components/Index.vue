<template>
    <body>
    <div>
        <NavMenu class="shadow"/>
        <el-container style="margin: 0 auto auto;max-width: 1100px">
            <el-aside style="width: 150px;margin-top:20px;padding: 5px;">
                <switch></switch>
                <SideMenu ref="sideMenu" class="shadow"></SideMenu>
            </el-aside>
            <el-main class="shadow main">
                <router-view/>
            </el-main>
        </el-container>
    </div>
    </body>

</template>

<script>
import NavMenu from "./NavMenu";
import SideMenu from "./SideMenu";

export default {
    name: "Index",
    components: {
        SideMenu,
        NavMenu,
    }
}
</script>

<style scoped>
    .shadow {

        box-shadow: 0 0 5px  rgba(255, 255, 255, 0.5);
    }
    body{
        background:url("../assets/backgroud.png") no-repeat;
        height: 105%;
        width: 105%;
        background-size: cover;
        position: fixed;
        margin: -10px;
    }
    main{
        background-color: rgba(255,255,255,0.5);

        margin:25px 5px;
    }
</style>