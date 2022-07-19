<template>
  <a-card :bordered="false">
    <el-form :inline="true" :model="formSearch" class="demo-form-inline" size="small">
      <el-form-item label="用户名">
        <el-input v-model="formSearch.username"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="formSearch.nickname"></el-input>
      </el-form-item>
      <el-form-item label="楼栋号">
        <el-input v-model="formSearch.goodsNo"></el-input>
      </el-form-item>
      <el-form-item label="房间号">
        <el-input v-model="formSearch.goodsRoom"></el-input>
      </el-form-item>
      <el-form-item>
        <a-button type="primary" @click="onSubmit">查询</a-button>
      </el-form-item>

    </el-form>
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="username" label="用户名"/>
      <el-table-column prop="nickname" label="姓名"/>
      <el-table-column prop="nickname" label="QQ"/>
      <el-table-column prop="nickname" label="邮箱"/>
      <el-table-column prop="goodsNo" label="楼栋号"/>
      <el-table-column prop="goodsRoom" label="房间号"/>
      <el-table-column prop="unitPrice" label="单价"/>
      <el-table-column prop="total" label="总价"/>
      <el-table-column prop="status" label="状态"/>
      <el-table-column prop="date" label="收费周期"/>
      <el-table-column label="操作" fixed="right" width="150px">
        <template slot-scope="scope">
          <a-button type="link" size="small" v-if="scope.row.status === '待支付' && user.role === 'admin'"
                    @click="cuijiao(scope.row.id)">催缴
          </a-button>
          <a-popconfirm title="确认删除吗？" @confirm="del(scope.row.id)" style="margin-left: 6px"
                        v-if="user.role === 'admin'">
            <a-button type="link" size="small">删除</a-button>
          </a-popconfirm>

          <a-popconfirm title="确认支付吗？" @confirm="zf(scope.row.id)" style="margin-left: 6px"
                        v-if="scope.row.status !== '已支付' && user.role === 'user'">
            <a-button type="link" size="small">支付</a-button>
          </a-popconfirm>
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
  methods: {
    zf(id) {
      post("/api/admin/bill/update/", {id: id, status: '已支付'}).then(() => {
        this.$message.success("支付成功")
        this.search();
      });
    },
    detail: function (id) {
      this.$router.push("/bill/detail?id=" + id);
    },
    del: function (id) {
      post("/api/admin/bill/delete/" + id).then(() => {
        this.$message.success("删除成功")
        this.search();
      });
    },
    cuijiao(id) {
      const that = this;
      post("/api/admin/bill/update", {id: id, status: '已催缴'}).then(res => {
        that.search();
      });
    },
    search: function () {
      const that = this;
      this.formSearch.pageNum = this.pageNum;
      post("/api/admin/bill/list", this.formSearch).then(res => {
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