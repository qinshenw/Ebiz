<template>
  <div class="home-container">
    <div class="titBox">
      <h2>Dataset 1</h2>
      <a :href="`/api/report/export/?username=${user.name}&filename=${path}`" download target="_blank">Export Report</a>
    </div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="k1"> </el-table-column>
      <el-table-column prop="lou" label="loU"> </el-table-column>
      <el-table-column prop="area" label="Area"> </el-table-column>
      <el-table-column prop="maxDetextions" label="MaxDetextions">
      </el-table-column>
      <el-table-column prop="value" label="Value"> </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { post } from "@/utils/request";
import { mapGetters } from "vuex";

export default {
  name: "index",
  data() {
    return {
      tableData: [
        {
          k1: "",
          lou: "0.50:0.95",
          area: "all",
          maxDetextions: "100",
          value: "0.101",
        },
        {
          k1: "Average Precision",
          lou: "0.50",
          area: "all",
          maxDetextions: "100",
          value: "0.202",
        },
        {
          k1: "",
          lou: "0.75",
          area: "all",
          maxDetextions: "100",
          value: "0.091",
        },
        {
          k1: "",
          lou: "0.50:0.95",
          area: "small",
          maxDetextions: "100",
          value: "0.021",
        },
        {
          k1: "",
          lou: "0.50:0.95",
          area: "medium",
          maxDetextions: "100",
          value: "0.109",
        },
        {
          k1: "",
          lou: "0.50:0.95",
          area: "large",
          maxDetextions: "100",
          value: "0.178",
        },
        {
          k1: "",
          lou: "0.50:0.95",
          area: "all",
          maxDetextions: "1",
          value: "0.116",
        },
        {
          k1: "Average Recall",
          lou: "0.50:0.95",
          area: "all",
          maxDetextions: "10",
          value: "0.171",
        },
        {
          k1: "",
          lou: "0.50:0.95",
          area: "all",
          maxDetextions: "100",
          value: "0.174",
        },
        {
          k1: "",
          lou: "0.50:0.95",
          area: "small",
          maxDetextions: "100",
          value: "0.035",
        },
        {
          k1: "",
          lou: "0.50:0.95",
          area: "medium",
          maxDetextions: "100",
          value: "0.171",
        },
        {
          k1: "",
          lou: "0.50:0.95",
          area: "large",
          maxDetextions: "100",
          value: "0.302",
        },
      ],
      path: '',
    };
  },
  mounted() {
    console.log("route = ", this.$route.query.path)
    if(this.$route.query.path){
      this.path = this.$route.query.path;
      post('/api/report/display', {
        username: this.user.name,
        filename: this.$route.query.path
      }).then((res) => {
        // this.dataList = res.data;
        let deepdata = [...this.tableData];
        deepdata.forEach((v, i) => {
          v.value = res.data[`value${i + 1}`];
        })
        this.tableData = deepdata;
      });
    }

  },
  computed: {
    ...mapGetters("account", ["user"]),
  },
  methods: {
    handle_export() {
      if(this.$route.query.path){
        post('/api/report/export', {
          username: this.user.name,
          filename: this.$route.query.path
        }).then((res) => {
          
        });
      }
    }
  },
};
</script>
<style>
.el-dialog,
.el-pager li,
.btn-prev,
.btn-next {
  background: transparent !important;
}
</style>
<style scoped>
.datasetItem {
  width: 95%;
  height: 70px;
  padding: 0 25px;
  background: #ffffff;
  box-shadow: 0 5px 3px rgba(0, 0, 0, 0.3);
  margin: 0 auto 20px;
  /* border-radius: 5px; */
  display: flex;
  align-items: center;
  color: black;
}
.pageBox {
  display: flex;
  justify-content: flex-end;
}
.titBox{
  display: flex;
  justify-content: space-between;
  background: #FFF;
  padding: 20px;
}
.titBox div{
  text-decoration: underline;
  cursor: pointer;
}
</style>