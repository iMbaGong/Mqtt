<template>
    <div>
        <RoomInfo :roomInfo="LivingRoom" style="height: 100%"></RoomInfo>
    </div>
</template>

<script>
    import RoomInfo from "./RoomInfo";
    export default {
        name: "LivingRoom",
        components: {RoomInfo},
        data(){
            return{
                LivingRoom:"LivingRoom"
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