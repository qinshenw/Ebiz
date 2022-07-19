<template>
  <a-card :bordered="false">

    <advance-form :form-model="formModel" :form-items="formItems" ref="form">

      <template #actions>
        <a-form-model-item :wrapper-col="{ span: 14, offset: 6 }">
          <a-button type="primary" @click="onSubmit">Submit</a-button>
        </a-form-model-item>
      </template>
    </advance-form>

  </a-card>
</template>

<script>
import AdvanceForm from "@/components/form/AdvanceForm";
import {post} from "@/utils/request";
import {logout} from "@/services/user";

export default {
  components: {AdvanceForm},
  data() {
    return {
      loading: false,
      urls: {
        mergeUrl: '/api/modifyPassword'
      },
      formModel: {},
      formItems: [
        {label: 'Original Password', key: 'password', type: "password"},
        {label: 'New Password', key: 'pwd', type: "password"},
        {label: 'Confirm New Password', key: 'pwd2', type: "password"},
      ]
    }
  },
  mounted() {
    const id = this.$route.query.id;
  },
  computed: {},
  methods: {
    onSubmit() {
      const self = this;
      this.$refs.form.validate(() => {
        post(self.urls.mergeUrl, self.formModel).then(res => {
          self.$message.success("Edit Successfully!");
          logout()
          this.$router.push('/login')
        })
      });
    }
  }
}
</script>

<style lang="less" scoped>

</style>