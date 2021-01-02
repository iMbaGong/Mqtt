<template>
    <div>
        <div ref="myChart" style="height:600px;width: 800px"></div>
    </div>
</template>

<script>
    // let echarts = require('echarts/lib/echarts');
    // require('echarts/lib/chart/bar')
    // require('echarts/lib/chart/line')
    // // 引入提示框和title组件
    // require('echarts/lib/component/tooltip')
    // require('echarts/lib/component/title')
    // require('echarts/lib/component/toolbox')
    // require('echarts/lib/component/dataZoom')
    export default {
        name: "RoomInfo",
        data() {
            return {}
        },
        mounted() {
            this.draw();
        },
        methods: {
            draw() {
                let chartRef = this.$refs.myChart;
                let myChart = this.$echarts.init(chartRef);

                var base = +new Date(1968, 9, 3);
                var oneDay = 24 * 3600 * 1000;
                var date = [];

                var data = [Math.random() * 300];

                for (var i = 1; i < 20000; i++) {
                    var now = new Date(base += oneDay);
                    date.push([now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'));
                    data.push(Math.round((Math.random() - 0.5) * 20 + data[i - 1]));
                }

                // 绘制图表
                myChart.setOption({
                    tooltip: {
                        trigger: 'axis',
                        position: function (pt) {
                            return [pt[0], '10%'];
                        }
                    },
                    title: {
                        left: 'center',
                        text: '温度',
                    },
                    toolbox: {
                        feature: {
                            dataZoom: {
                                yAxisIndex: 'none'
                            },
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: date
                    },
                    yAxis: {
                        type: 'value',
                        boundaryGap: [0, '100%']
                    },
                    dataZoom: [{
                        type: 'inside',
                        start: 90,
                        end: 100
                    }, {
                        start: 90,
                        end: 100,
                    }],
                    series: [
                        {
                            name: '模拟数据',
                            type: 'line',
                            smooth: true,
                            symbol: 'none',
                            sampling: 'average',
                            itemStyle: {
                                color: 'rgb(255, 70, 131)'
                            },
                            areaStyle: {
                                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: 'rgb(255, 158, 68)'
                                }, {
                                    offset: 1,
                                    color: 'rgb(255, 70, 131)'
                                }])
                            },
                            data: data
                        }
                    ]
                });
            }
        }
    }
</script>

<style scoped>

</style>