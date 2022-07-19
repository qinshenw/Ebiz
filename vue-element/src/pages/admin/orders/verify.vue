<template>
  <a-card :bordered="false">

    <advance-form :form-model="formModel" :form-items="formItems" ref="form">

      <template #actions>
        <a-form-model-item :wrapper-col="{ span: 14, offset: 6 }">
          <a-button type="primary" @click="onSubmit">提交</a-button>
        </a-form-model-item>
      </template>
    </advance-form>

  </a-card>
</template>

<script>
import AdvanceForm from "@/components/form/AdvanceForm";
import {get, post} from "@/utils/request";
import {mapGetters} from "vuex";

export default {
  components: {AdvanceForm},
  data() {
    return {
      urls: {
        detailUrl: '/api/admin/orders/detail/',
        mergeUrl: '/api/admin/orders/update'
      },
      formModel: {},
      formItems: [
        {
          label: '处理结果',
          key: 'result',
          type: 'select',
          optionData: [{label: '待处理', value: '待处理'}, {label: '已改正', value: '已改正'}, {label: '已上报', value: '已上报'}]
        },
        {
          label: '状态',
          key: 'status',
          type: 'select',
          optionData: [{label: '待处理', value: '待处理'}, {label: '已处理', value: '已处理'}]
        },
        {label: '维修人员', key: 'wxUser',},
      ]
    }
  },
  mounted() {
    this.loadData();
  },
  activated() {
    this.loadData();
  },
  methods: {

    loadData(){
      const id = this.$route.query.id;
      get(this.urls.detailUrl + id).then(res => {
        this.formModel = res.data
      })
    },
    onSubmit() {
      const self = this;
      delete this.formModel.createTime;
      delete this.formModel.updateTime;
      this.$refs.form.validate(() => {
        post(self.urls.mergeUrl, self.formModel).then(res => {
          self.$message.success("更新成功");
          self.$router.push('./index');
        })
      });
    }
  }
}
</script>

<style lang="less" scoped>

</style>