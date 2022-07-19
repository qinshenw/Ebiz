<template>
  <a-card :bordered="false">
    <el-form :inline="true" :model="formSearch" class="demo-form-inline" size="small">
      <el-form-item label="用户名">
        <el-input v-model="formSearch.username"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="formSearch.name"></el-input>
      </el-form-item>
      <el-form-item>
        <a-button type="primary" @click="onSubmit">查询</a-button>
      </el-form-item>

    </el-form>
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="username" label="用户名"/>
      <el-table-column prop="name" label="姓名"/>
      <el-table-column prop="mobile" label="联系电话"/>
      <el-table-column prop="qq" label="QQ"/>
      <el-table-column prop="email" label="邮箱"/>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <a-button @click="detail(scope.row.id)" type="link" size="small" >编辑</a-button>
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

    detail: function (id) {
      this.$router.push("/user/detail?id=" + id);
    },

    search: function () {
      const that = this;
      this.formSearch.pageNum = this.pageNum;
      post("/api/admin/user/list", this.formSearch).then(res => {
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