<template>
    <div>
        <RoomInfo :roomInfo="Room" style="height: 100%"></RoomInfo>
    </div>
</template>

<script>
    import RoomInfo from "./RoomInfo";
    export default {
        name: "DiningRoom",
        components: {RoomInfo},
        data(){
            return{
                Room:"DiningRoom"
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