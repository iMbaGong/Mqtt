<template>
    <div>
        <h3>{{roomInfo}}</h3>
        <el-button type="primary" style="float: left" @click="getCurTemp">获取当前温度</el-button>
        <br/><br/><br/>
        <div>
            <div ref="myChart" style="height:500px;width: 800px"></div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "RoomInfo",
        props: ["roomInfo"],
        data() {
            return {
                tempInfo: []
            }
        },
        created() {
            this.$axios.get("/allTemperature?location=" + this.roomInfo).then(
                resp => {
                    console.log("data back")
                    this.tempInfo = resp.data
                }
            )
        },
        mounted() {
            this.draw();
        },
        computed: {
            tranDate: function () {
                return function (date) {
                    return this.$moment(date).format(
                        "YYYY-MM-DD HH:mm:ss"
                    );
                }
            },
        },
        methods: {
            draw() {
                let _this = this;
                let chartRef = this.$refs.myChart;
                let myChart = this.$echarts.init(chartRef);
                setTimeout(() => {
                    var date = [];
                    var data = [];
                    let preData = [];
                    for (let i = 0; i < _this.tempInfo.length-72; i++) {
                        date.push(_this.tranDate(_this.tempInfo[i].date));
                        data.push(_this.tempInfo[i].temp);
                        preData.push('-')
                    }
                    for (let i=_this.tempInfo.length-72; i < _this.tempInfo.length; i++) {
                        date.push(_this.tranDate(_this.tempInfo[i].date));
                        data.push('-');
                        preData.push(_this.tempInfo[i].temp)
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
                            boundaryGap: [0, '10%']
                        },
                        dataZoom: [{
                            type: 'inside',
                            start: 75,
                            end: 100
                        }, {
                            start: 75,
                            end: 100,
                        }],
                        series: [
                            {
                                name: '温度',
                                type: 'line',
                                smooth: true,
                                symbol: 'none',
                                sampling: 'average',
                                itemStyle: {
                                    color: 'rgba(255,253,253,0.33)'
                                },
                                areaStyle: {
                                    color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                        offset: 0,
                                        color: 'rgb(255,0,3)'
                                    }, {
                                        offset: 1,
                                        color: 'rgb(0,122,255)'
                                    }])
                                },
                                data: data.slice(0,data.length-72)
                            },
                            {
                                name: '预测',
                                type: 'line',
                                smooth: true,
                                symbol: 'none',
                                sampling: 'average',
                                itemStyle: {
                                    color: 'rgba(255,255,255,0.52)'
                                },
                                areaStyle: {
                                    color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                        offset: 0,
                                        color: 'rgba(96,20,0,0.64)'
                                    }, {
                                        offset: 1,
                                        color: 'rgba(0,1,90,0.67)'
                                    }])
                                },
                                data: preData
                            }
                        ]
                    });
                }, 1000);

            },
            getCurTemp(){
                let _this = this;
                _this.$axios.get("/currentTemperature?location=" + this.roomInfo).then(
                    resp => {
                        if(resp)
                        console.log("data back")
                        location.reload()
                    }
                )
            }
        }
    }
</script>

<style scoped>

</style>