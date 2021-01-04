import VueRouter from "vue-router";
import Vue from "vue";
import App from "@/App";
import Index from "@/components/Index";
import Overview from "../components/Overview";
import LivingRoom from "../components/LivingRoom";
import BedRoom from "../components/BedRoom";
import BathRoom from "../components/BathRoom";
import Balcony from "../components/Balcony";
import DiningRoom from "../components/DiningRoom";

Vue.use(VueRouter)

export default new VueRouter({
    mode: 'history',
    routes:[
        {
            path:"/",
            name:"Default",
            component:App,
            redirect:"Index"
        },
        {
            path:"/Index",
            name:"Index",
            component:Index,
            children:[
                {
                    path:"/OverView",
                    name:"OverView",
                    component:Overview,
                },
                {
                    path:"/LivingRoom",
                    name:"LivingRoom",
                    component:LivingRoom,
                },
                {
                    path:"/BedRoom",
                    name:"BedRoom",
                    component:BedRoom,
                },
                {
                    path:"/BathRoom",
                    name:"BathRoom",
                    component:BathRoom,
                },
                {
                    path:"/Balcony",
                    name:"Balcony",
                    component:Balcony,
                },
                {
                    path:"/DiningRoom",
                    name:"DiningRoom",
                    component:DiningRoom,
                },

            ]


        },

    ]
})