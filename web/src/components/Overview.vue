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
                temp:10
            }
        },
        mounted() {
            this.drawLine();
        },
        methods: {
            drawLine() {

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
                                temp:"18°",
                                state:"<span style=\"color: #55a532\">正常</span>"
                            },
                            {
                                name: '浴室',            // Second tree
                                value: 15,
                                temp:"18°",
                                state:"<span style=\"color: #55a532\">正常</span>"
                            },
                            {
                                name: '客厅',            // Second tree
                                value: 30,
                                temp:"11°",
                                state:"<span style=\"color: #55a532\">正常</span>"
                            },
                            {
                                name: '卧室',            // Second tree
                                value: 25,
                                temp:"12°",
                                state:"<span style=\"color: #ff0000\">离线</span>"
                            },
                            {
                                name: '阳台',            // Second tree
                                value: 15,
                                temp:"16°",
                                state:"<span style=\"color: #ff0000\">离线</span>"
                            },
                        ]
                    }],
                    dataset:{
                        source:[

                            {
                                name: '餐厅',            // First tree
                                value: 20,
                                temp:"18°"
                            },
                            {
                                name: '浴室',            // Second tree
                                value: 15,
                                temp:"18°"
                            },
                            {
                                name: '客厅',            // Second tree
                                value: 30,
                                temp:"11°"
                            },
                            {
                                name: '卧室',            // Second tree
                                value: 25,
                                temp:"12°"
                            },
                            {
                                name: '阳台',            // Second tree
                                value: 15,
                                temp:"16°"
                            }
                        ]
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>