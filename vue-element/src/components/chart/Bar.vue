<template>
  <div class="bar">
    <h4 style="text-align: center">{{title}}</h4>
    <div class="chart">
      <v-chart :force-fit="true" :height="height" :data="data" >
        <v-tooltip/>
        <v-axis :show="true" dataKey="key" position="left"/>
        <!-- x 轴 和  y 轴-->
        <v-bar position="title*val"/>
      </v-chart>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Bar',
  props: {
    title: String,
    height: {
      default: 312
    },
    data: Array
  },
  data() {
    return {}
  }
}
</script>

<style scoped lang="less">
  .bar{
    position: relative;
    .chart{
    }
  }
</style>
