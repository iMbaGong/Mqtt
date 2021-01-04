<template>
    <div>
        <RoomInfo :roomInfo="Room" style="height: 100%"></RoomInfo>
    </div>
</template>

<script>
    import RoomInfo from "./RoomInfo";
    export default {
        name: "Balcony",
        components: {RoomInfo},
        data(){
            return{
                Room:"Balcony"
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