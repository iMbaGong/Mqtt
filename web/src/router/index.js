import VueRouter from "vue-router";
import Vue from "vue";
import App from "@/App";
import Index from "@/components/Index";

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
        },

    ]
})