<template>
  <a-card :bordered="false">

    <advance-form :form-model="user" :form-items="formItems" ref="form">

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
import {mapGetters, mapMutations} from "vuex";

export default {
  components: {AdvanceForm},

  data() {
    return {
      loading: false,
      urls: {
        mergeUrl: '/api/update'
      },
      formItems: [
        {label: 'Profile', key: 'avatar', type: "upload", count: 1},
        {label: 'Name', key: 'name',},
        {label: 'Phone Number', key: 'mobile'},
        {label: 'Email', key: 'email'},
      ]
    }
  },
  mounted() {
    const id = this.$route.query.id;
  },
  computed: {
    ...mapGetters('account', ['user']),
  },
  methods: {
    ...mapMutations('account', ['setUser']),
    onSubmit() {
      const self = this;
      this.$refs.form.validate(() => {
        delete self.user.createTime
        delete self.user.updateTime
        post(self.urls.mergeUrl, self.user).then(res => {
          self.$message.success("Edit Successfully!");
          self.setUser(res.data)
        })
      });
    }
  }
}
</script>

<style lang="less" scoped>

</style>