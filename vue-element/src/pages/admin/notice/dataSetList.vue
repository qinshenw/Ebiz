<template>
  <div class="home-container">
    <div v-for="(item, i) in dataList"  :key="i" class="datasetItem">
      <div>{{ item.filename }}</div>
      <div v-if="item.hasResult == 1">
        <el-button type="primary"  @click="handleDtail(item.filename)" size="mini" style="margin-right: 10px;">Check Result</el-button>
        <el-button @click="export_report(item)" type="success" size="mini">Export Report</el-button>
      </div>
    </div>
    <!-- <button @click="handleDtail('aaa')">dd试</button> -->
    <!-- <div class="pageBox">
      <el-pagination
        @size-change="handleSizeChaedddnge"
        @current-change="handleCurrentChange"ewweewew
        :current-page.sync="currentPage3"
        :page-size="100"
        layout="prev, pager, next"
        :total="1000"
      >wew
      </el-pagination>
    </div> -->
  </div>
</template>

<script>
import { post, get } from "@/utils/request";
import { mapGetters } from "vuex";

export default {
  name: "index",
  data() {
    return {
      dataList: [],
    };
  },
  mounted() {
    post('/api/model/list', {
      username: this.user.name
    }).then((res) => {
      this.dataList = res.data;
    });
  },
  computed: {
    ...mapGetters("account", ["user"]),
  },
  methods: {
    handleDtail(path) {
      this.$router.push({
        path: "/bill/index",
        query: {
          path
        }
      })
    },
    funDownload (content, filename) {
      // 创建隐藏的可下载链接
      var eleLink = document.createElement('a');
      eleLink.download = filename + '_report';
      eleLink.style.display = 'none';
      // 字符内容转变成blob地址
      var blob = new Blob([content]);
      eleLink.href = URL.createObjectURL(blob);
      // 触发点击
      document.body.appendChild(eleLink);
      eleLink.click();
      // 然后移除
      document.body.removeChild(eleLink);
    },
    export_report(item) {
      get(`/api/report/export?username=${this.user.name}&filename=${item.filename}`).then((res) => {
        console.log("res = ", res)
        this.funDownload(res, item.filename.split('.')[0])
      });
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
  justify-content: space-between;
}
.pageBox {
  display: flex;
  justify-content: flex-end;
}
</style>