<template>
  <div class="home-container">
    <div v-for="(item, i) in dataList" :key="i" class="datasetItem">
      {{ item.name }}
    </div>
    <!-- <div class="pageBox">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage3"
        :page-size="100"
        layout="prev, pager, next"
        :total="1000"
      >
      </el-pagination>
    </div> -->
  </div>
</template>

<script>
import { post } from "@/utils/request";
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
      this.dataList = res.data.files;
    });
  },
  computed: {
    ...mapGetters("account", ["user"]),
  },
  methods: {},
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
</style>