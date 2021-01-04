<template>
    <div>
        <RoomInfo :roomInfo="Room" style="height: 100%"></RoomInfo>
    </div>
</template>

<script>
    import RoomInfo from "./RoomInfo";
    export default {
        name: "BedRoom",
        components: {RoomInfo},
        data(){
            return{
                Room:"BedRoom"
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