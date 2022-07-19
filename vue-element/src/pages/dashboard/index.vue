<template>
  <page-layout :avatar="user.avatar||'/favicon.ico'">
    <div slot="headerContent">
      <div class="title">{{ systemName }}</div>
      <div>{{ systemDescribe }}</div>
    </div>
    <template slot="extra">
      <head-info class="split-right" title="未处理的报修" :content="countData.count"/>
      <head-info class="split-right" title="未处理的投诉" :content="countData.count1"/>
      <head-info class="split-right" title="催缴中的账单" :content="countData.count3"/>
      <head-info class="split-right" title="未支付的账单" :content="countData.count2"/>
    </template>
    <template>
      <a-card :bordered="false" v-if="user.role === 'user'">

        <advance-form :form-model="formModel" :form-items="formItems" ref="form">
          <template #actions>
            <a-form-model-item :wrapper-col="{ span: 14, offset: 6 }">
              <a-button type="primary" @click="onSubmit">更新</a-button>
            </a-form-model-item>
          </template>
        </advance-form>

      </a-card>
    </template>
  </page-layout>
</template>

<script>
import PageLayout from '@/layouts/PageLayout'
import HeadInfo from '@/components/tool/HeadInfo'
import Radar from '@/components/chart/Radar'
import {mapState} from 'vuex'
import {get, post} from "@/utils/request";
import {httpBaseUrl, systemDescribe, systemName} from "@/config";
import AdvanceForm from "@/components/form/AdvanceForm";


export default {
  name: 'WorkPlace',

  components: { Radar, HeadInfo, PageLayout,AdvanceForm},
  data() {
    return {
      systemDescribe,
      systemName,
      httpBaseUrl,
      loading: true,
      countData: {},

      formModel: {},
      urls: {
        mergeUrl: '/api/goods/update'
      },
      formItems: [
        {label: '楼栋号', key: 'no',},
        {label: '房间号', key: 'room'},
        {label: '面积', key: 'size'},
      ]
    }
  },
  computed: {
    ...mapState('account', {user: 'user'}),

  },
  mounted() {
    this.loadData();
  },
  created() {
  },
  methods: {
    loadData() {
      get('/api/count/index').then(res => {
        this.countData = res.data
      })
      get('/api/goods/index').then(res => {
        this.formModel = res.data
      })

    },
    onSubmit() {
      const self = this;
      this.$refs.form.validate(() => {
        delete self.formModel.createTime
        delete self.formModel.updateTime
        post(self.urls.mergeUrl, self.formModel).then(res => {
          self.$message.success("修改成功");
        })
      });
    }
  }
}
</script>

<style lang="less">

</style>
