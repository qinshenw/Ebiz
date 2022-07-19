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

export default {
  components: {AdvanceForm},
  data() {
    return {
      urls: {
        detailUrl: '/api/admin/user/detail/',
        mergeUrl: '/api/admin/user/update'
      },
      formModel: {},
      formItems: [
        {label: '用户名', key: 'username'},
        {label: '姓名', key: 'name'},
        {label: '联系电话', key: 'mobile'},
        {label: 'QQ', key: 'qq'},
        {label: '邮箱', key: 'email'},

      ]
    }
  },
  mounted() {
    const id = this.$route.query.id;
    get(this.urls.detailUrl + id).then(res => {
      this.formModel = res.data
    })
  },
  computed: {},
  methods: {
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