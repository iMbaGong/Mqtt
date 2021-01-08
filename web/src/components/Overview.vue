<template>
    <div>
        <div ref="chart" style="width: 800px;height: 600px;color: red"></div>
    </div>
</template>

<script>
    export default {
        name: "Overview",
        data() {
            return {
                temp:10,
                states:[],
                temps:[]
            }
        },
        mounted() {
            this.drawLine();
        },
        computed:{
            getState:function (){
                return function (state){
                    switch (state){
                        case true:return  "<span style=\"color: #55a532\">正常</span>";
                        case false:return "<span style=\"color: #ff0000\">离线</span>";
                    }
                }
            }
        },
        methods: {
            drawLine() {
                let _this = this;
                _this.$axios.get("/overview").then(
                    resp => {
                        _this.states = resp.data.state;
                        _this.temp = resp.date.temp;
                        let chartRef = this.$refs.chart;
                        let myChart = this.$echarts.init(chartRef);
                        myChart.setOption({
                            title: {
                                left: 'center',
                                text: '总览',
                            },
                            tooltip: {
                                formatter: function (info) {
                                    var name  = info.name;
                                    return [
                                        '<div class="tooltip-title">' + name + '</div>',
                                        '<div style="float: left">' + '当前温度: ' + info.data.temp + '</div><br/>',
                                        '<div style="float: left">' + '传感器状态：'+info.data.state + '</div>',
                                    ].join('');
                                }
                            },
                            series: [{
                                name: "总览",
                                type: 'treemap',
                                data:[
                                    {
                                        name: '餐厅',            // First tree
                                        value: 20,
                                        temp:_this.states[1],
                                        state:_this.getState(_this.states[1])
                                    },
                                    {
                                        name: '浴室',            // Second tree
                                        value: 15,
                                        temp:_this.states[3],
                                        state:_this.getState(_this.states[3])
                                    },
                                    {
                                        name: '客厅',            // Second tree
                                        value: 30,
                                        temp:_this.states[0],
                                        state:_this.getState(_this.states[0])
                                    },
                                    {
                                        name: '卧室',            // Second tree
                                        value: 25,
                                        temp:_this.states[2],
                                        state:_this.getState(_this.states[2])
                                    },
                                    {
                                        name: '阳台',            // Second tree
                                        value: 15,
                                        temp:_this.states[4],
                                        state:_this.getState(_this.states[4])
                                    },
                                ]
                            }],
                        })
                    })

            }
        }
    }
</script>

<style scoped>

</style>