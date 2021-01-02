import VueRouter from "vue-router";
import Vue from "vue";
import App from "@/App";
import Index from "@/components/Index";
import Overview from "../components/Overview";
import LivingRoom from "../components/LivingRoom";

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

            ]


        },

    ]
})