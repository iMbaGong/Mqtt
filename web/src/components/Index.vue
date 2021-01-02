<template>
    <div>
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="zhuan" name="first">用户管理</el-tab-pane>
            <el-tab-pane label="配置管理" name="second">配置管理</el-tab-pane>

        </el-tabs>
    </div>
</template>

<script>
export default {
    name: "Index"
}
</script>

<style scoped>

</style>