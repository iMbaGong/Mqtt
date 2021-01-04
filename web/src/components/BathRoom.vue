<template>
    <div>
        <RoomInfo :roomInfo="Room" style="height: 100%"></RoomInfo>
    </div>
</template>

<script>
    import RoomInfo from "./RoomInfo";
    export default {
        name: "BathRoom",
        components: {RoomInfo},
        data(){
            return{
                Room:"BathRoom"
            }
        },
        created(){
        },
        mounted() {
        },
        methods:{
        }
    }
</script>

<style scoped>

</style>