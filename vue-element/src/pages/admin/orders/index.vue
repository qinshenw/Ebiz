<template>
  <a-card :bordered="false">
    <el-form :inline="true" :model="formSearch" class="demo-form-inline" size="small">
      <el-form-item label="用户名">
        <el-input v-model="formSearch.username"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="formSearch.nickname"></el-input>
      </el-form-item>
      <el-form-item>
        <a-button type="primary" @click="onSubmit">查询</a-button>
        <a-button type="success" @click="detail(0)" style="margin-left: 6px" v-if="user.role === 'user'">投诉/报修</a-button>
      </el-form-item>

    </el-form>
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="username" label="用户名"/>
      <el-table-column prop="nickname" label="姓名"/>
      <el-table-column prop="type" label="类型"/>
      <el-table-column prop="type" label="图片">
        <template slot-scope="scope">
          <el-image
              style="width: 100px; height: 100px"
              :src="scope.row.path"
              :preview-src-list="[scope.row.path]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="title" label="标题"/>
      <el-table-column prop="remark" label="描述"/>
      <el-table-column prop="wxUser" label="维修人员"/>
      <el-table-column prop="status" label="状态"/>
      <el-table-column prop="result" label="结果"/>
      <el-table-column prop="createTime" label="上报时间"/>
      <el-table-column label="操作"  v-if="user.role === 'admin'">
        <template #default="{ row }">
          <a-button @click="verify(row.id)" type="link" size="small" v-if="row.status === '待处理'">处理</a-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="  text-align: center;padding-top:15px">
      <el-pagination
          :page-size="8"
          @current-change="pageChange"
          background
          layout="prev, pager, next"
          :total="total"
      ></el-pagination>
    </div>

  </a-card>
</template>

<script>
import AdvanceForm from "@/components/form/AdvanceForm";
import {post} from "@/utils/request";
import {mapGetters} from "vuex";
import {httpBaseUrl} from "@/config";


export default {
  components: {AdvanceForm},

  data() {
    return {
      loading: false,
      httpBaseUrl,
      formSearch: {},
      tableData: [],
      total: 0,
      pageNum: 1
    }
  },
  mounted() {
    const id = this.$route.query.id;
    this.search();
  },
  computed: {
    ...mapGetters('account', ['user']),
  },
  activated() {
    this.search();
  },
  methods: {
    verify(id){
      this.$router.push("/orders/verify?id=" + id);
    },
    detail: function (id) {
      this.$router.push("/orders/detail?id=" + id);
    },

    search: function () {
      const that = this;
      this.formSearch.pageNum = this.pageNum;
      post("/api/admin/orders/list", this.formSearch).then(res => {
        that.tableData = res.data.list;
        that.total = res.data.total;
        that.pageNum = res.data.pageNum;
      });
    },
    onSubmit: function () {
      this.pageNum = 1;
      this.search();
    },
    pageChange: function (e) {
      this.pageNum = e;
      this.search();
    },
  }
}
</script>

<style lang="less" scoped>

</style>