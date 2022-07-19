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
        detailUrl: '/api/admin/goods/detail/',
        mergeUrl: '/api/admin/goods/update'
      },
      formModel: {},
      formItems: [
        {label: '用户', key: 'userId', type: 'select',optionUrl:'/api/user/userList'},
        {label: '楼栋号', key: 'no'},
        {label: '房间号', key: 'room'},
        {label: '房间面积', key: 'size', type: 'number'}
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