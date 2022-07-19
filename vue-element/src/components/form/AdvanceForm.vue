<template>
  <div :id="id" class="advanced-form">
      <a-form-model :model="formModel" :label-col="labelCol" :wrapper-col="wrapperCol" ref="form">
        <a-form-model-item v-for="(item, index) in formItems" :key="index" :prop="item.key"
                           :rules="item.rules">
          <!-- slot -->
          <template slot="help">
            <template v-if="item.help">{{ item.help }}</template>
            <template v-else>
              <slot v-if="item.slots['help']" :name="item.slots['help']" v-bind="{item}"></slot>
            </template>
          </template>
          <template slot="extra">
            <template v-if="item.extra">{{ item.extra }}</template>
            <template v-else>
              <slot v-if="item.slots['extra']" :name="item.slots['extra']" v-bind="{item}"></slot>
            </template>
          </template>
          <template slot="label">
            <template v-if="item.label">{{ item.label }}</template>
            <template v-else>
              <slot v-if="item.slots['label']" :name="item.slots['label']" v-bind="{item}"></slot>
            </template>
          </template>

          <!-- 类型判断 -->
          <template v-if="item.type === 'select'">
            <a-select v-model="formModel[item.key]" :placeholder="item.placeholder" :allowClear="true" style="width: 100%"
                      :showSearch="true" :options="item.optionData"></a-select>
          </template>

          <template v-else-if="item.type === 'date'">
            <a-date-picker :allowClear="true" :valueFormat="item.format" v-model="formModel[item.key]"
                           :placeholder="item.placeholder"></a-date-picker>
          </template>

          <template v-else-if="item.type === 'dateRang'">
            <a-range-picker :allowClear="true" :valueFormat="item.format" v-model="formModel[item.key]"
                            :placeholder="item.placeholder"></a-range-picker>
          </template>

          <template v-else-if="item.type === 'switch'">
            <a-switch v-model="formModel[item.key]">
              <a-icon slot="checkedChildren" type="check"/>
              <a-icon slot="unCheckedChildren" type="close"/>
            </a-switch>
          </template>

          <template v-else-if="item.type === 'checkbox'">
            <a-checkbox-group v-model="formModel[item.key]" :options="item.optionData"></a-checkbox-group>
          </template>

          <template v-else-if="item.type === 'radio'">
            <a-radio-group v-model="formModel[item.key]" :options="item.optionData"></a-radio-group>
          </template>

          <template v-else-if="item.type === 'radioButton'">
            <a-radio-group v-model="formModel[item.key]" :buttonStyle="item.style">
              <a-radio-button v-for="(child,index) in item.optionData" :value="child.value" :key="index">
                {{ child.label }}
              </a-radio-button>
            </a-radio-group>
          </template>

          <template v-else-if="item.type === 'textarea'">
            <a-textarea v-model="formModel[item.key]" :placeholder="item.placeholder" :allowClear="true" :rows="3" />
          </template>

          <template v-else-if="item.type === 'number'">
            <a-input-number v-model="formModel[item.key]" :placeholder="item.placeholder" :allowClear="true" style="width: 100%"
                            :formatter="item.format" :precision="item.precision"/>
          </template>

          <template v-else-if="item.type === 'rate'">
            <a-rate v-model="formModel[item.key]" :allowHalf="true" :allowClear="true" :count="item.precision"/>
          </template>

          <template v-else-if="item.type === 'treeSelect'">
            <a-tree-select v-model="formModel[item.key]" :tree-data="item.optionData" :placeholder="item.placeholder"
                           tree-default-expand-all></a-tree-select>
          </template>

          <template v-else-if="item.type === 'tree'">
            <a-tree show-search
                    v-model="formModel[item.key]"
                    :checkable="true"
                    :show-icon="false"
                    :default-expand-all="true"
                    :auto-expand-parent="true"
                    :show-line="true"
                    :tree-data="item.optionData"
            >
              <a-icon slot="switcherIcon" type="caret-down"/>
            </a-tree>
          </template>

          <template v-else-if="item.type === 'upload'">
            <a-upload name="file" listType="picture-card" :multiple="true" :action="actionUrl"
                      :accept="item.accept||'*'"
                      @preview="handlePreview" @change="handleChange"
                      :headers="actionHeaders" :data="{mark:item.key}"
                      :defaultFileList="item.data">
              <a-button v-if="item.uploadShow">
                <a-icon type="upload"/>
                点击上传
              </a-button>

            </a-upload>
          </template>

          <template v-else-if="item.type === 'editor'">
            <vue-ueditor-wrap v-model="formModel[item.key]" :config="item.config"
                              style="line-height: 23px!important;"></vue-ueditor-wrap>
          </template>


          <template v-else-if="item.type === 'custom'">
            <slot :name="item.key" v-bind="{item}"></slot>
          </template>

          <template v-else-if="item.type === 'password'">
            <a-input v-model="formModel[item.key]" :placeholder="item.placeholder" :allowClear="true" type="password"
                     auto-size/>
          </template>

          <template v-else-if="item.type === 'icon'">
            <a-select v-model="formModel[item.key]" :allowClear="true"
                      :placeholder="item.placeholder" show-search>
              <a-select-option v-for="(opt,index) in iconList" :key="index" :value="opt">
                <a-icon :type="opt"></a-icon>
                {{ opt }}
              </a-select-option>
            </a-select>
          </template>

          <template v-else-if="item.type === 'cascader'">
            <a-cascader :default-value="formModel[item.key]" :options="item.optionData" :placeholder="item.placeholder"
                        :allowClear="true" :showSearch="true"
                        @change="(val)=>formModel[item.key] = val"/>
          </template>



          <template v-else>
            <a-input v-model="formModel[item.key]" :placeholder="item.placeholder" :allowClear="true"/>
          </template>


        </a-form-model-item>
        <slot name="actions"></slot>
      </a-form-model>

      <!-- 图片模态框 -->
      <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
        <img alt="example" style="width: 100%" :src="previewImage"/>
      </a-modal>
  </div>
</template>

<script>

import {httpBaseUrl, tokenName} from "@/config"
import Cookie from 'js-cookie'
import VueUeditorWrap from "vue-ueditor-wrap";
import {get} from "@/utils/request";
import {iconList} from "@/utils/constant";

export default {
  name: 'AdvanceForm',
  components: {VueUeditorWrap},
  props: {
    /**
     * 表单数据
     */
    formModel: Object,
    /**
     * 表单项
     * {
     *   label:
     *   key:
     *   type: text select date datetime radio switch  cascader icon password dataEditor editor
     *   placeholder:
     *   style:      单选样式 outline | solid
     *   precision   数字精度 ||
     *   count      上传文件数量
     *   slots:[help,extra]
     *   rules:[] // 规则
     *   optionData:[{label:  value:}]  // 下拉框的数据 单选  多选
     *   optionUrl:     // 下拉框数据的请求地址
     *   optionMark     // 下拉框数据的缓存 标志
     *   help
     *   extra
     *   config
     * }
     */
    formItems: Array,
    labelCol: {
      default: () => {
        return {
          xs: {span: 24},
          sm: {span: 7}
        }
      }
    },
    wrapperCol: {
      default: () => {
        return {
          xs: {span: 24},
          sm: {span: 14}
        }
      }
    },
    /**
     * 请求头
     */
    headers: {
      type: Object,
      default: () => {
        return {};
      }
    }
  },

  data() {
    return {
      id: `${new Date().getTime()}-${Math.floor(Math.random() * 10)}`,
      iconList: iconList,
      previewVisible: false,
      previewImage: ''
    }
  },
  created() {
    this.formItems.forEach(item => {
      // 赋值默认值
      const type = item.type
      // slots
      this.$set(item, 'slots', item.slots || {})
      this.$set(item, 'rules', item.rules || [{required: true}])
      // placeholder
      if ('dateRang' === type) {
        this.$set(item, 'placeholder', item.placeholder || ['开始时间', '结束时间'])
      } else {
        this.$set(item, 'placeholder', item.placeholder || '请输入' + item.label)
      }
      // 图片列表
      if ('upload' === type) {
        let data = [];
        let urls = this.formModel[item.key] || [];
        if (!Array.isArray(urls)) {
          urls = urls.split("#");
        }
        urls.forEach(url => {
          data.push({
            uid: new Date().getTime() + "" + Math.random() + "" + Math.random() + "" + Math.random(),
            name: url,
            status: 'done',
            url: httpBaseUrl + url,
            response: {data: {mark: item.key}},
          })
        })
        this.$set(item, 'data', data);
        if (item.count) {
          this.$set(item, 'uploadShow', item.count > data.length)
        }else{
          this.$set(item, 'uploadShow', true);
        }
      }
      // 编辑框配置
      if ('editor' === type) {
        this.$set(item, 'config', item.config || {
          // 编辑器不自动被内容撑高
          autoHeightEnabled: false,
          // 初始容器高度
          initialFrameHeight: 240,
          // 初始容器宽度
          initialFrameWidth: "100%",
          // 上传文件接口
          serverUrl: httpBaseUrl + "/api/ueditor/server",
          // UEditor 资源文件的存放路径，如果你使用的是 vue-cli 生成的项目，通常不需要设置该选项，vue-ueditor-wrap 会自动处理常见的情况，如果需要特殊配置，参考下方的常见问题2
          UEDITOR_HOME_URL: "/ueditor/"
        })
      }

      // 格式
      this.$set(item, 'format', item.format || this.getFormat(item))
      this.$set(item, 'style', item.style || "solid")

      // 加载数据
      if (item.optionUrl) {
        get(item.optionUrl, {}).then(res => {
          this.$set(item, 'optionData', res.data)
        })
      } else if (item.optionMark) {
        get("/api/common/optionsList/" + item.optionMark, {}).then(res => {
          this.$set(item, 'optionData', res.data)
        })
      }
    })
  },
  computed: {
    actionUrl() {
      return httpBaseUrl + "/api/common/upload";
    },
    actionHeaders(key) {
      let actionHeaders = Object.assign({}, this.headers);
      actionHeaders[tokenName] = Cookie.get(tokenName)
      return actionHeaders;
    }
  },
  methods: {
    getFormat(col) {
      if (col.format) {
        return col.format
      }
      const dataType = col.type
      switch (dataType) {
        case 'time':
          return 'HH:mm:ss'
        case 'date':
          return 'YYYY-MM-DD'
        case 'dateRang':
          return 'YYYY-MM-DD'
        case 'datetime':
          return 'YYYY-MM-DD HH:mm:ss'
        default:
          return undefined
      }
    },
    /**
     * 校验数据
     */
    validate(callback, callback2) {
      this.$refs.form.validate(valid => {
        if (valid) {
          callback()
        } else {
          callback2()
          return false;
        }
      });
    },


    handleCancel() {
      this.previewVisible = false;
    },
    async handlePreview(file) {
      this.previewImage = httpBaseUrl + file.path;
      this.previewVisible = true;
    },
    handleChange({file, fileList}) {
      const response = file.response;
      if (!response) {
        return;
      }
      // 上传失败的
      const status = response.code;
      if (status && status !== 'success' ) {
        this.$message.error("文件上传失败:" + response.message);
        // 移除
        fileList.some((item, i) => {
          if (item.uid === file.uid) {
            fileList.splice(i, 1)
            // 在数组的some方法中，如果return true，就会立即终止这个数组的后续循环
            return true
          }
        })
        return;
      }
      // 格式化数据
      fileList.forEach(item => {
        item.path = item.url || item.response.data.url;
        item.path = item.path.replace(httpBaseUrl, '')
      })

      // 组装数据
      const mark = response.data.mark;
      let urls = this.formModel[mark] || '';
      let value;
      if (Array.isArray(urls)) {
        value = [];
        fileList.forEach(item => {
          value.push(item.path);
        })
      } else {
        value = '';
        fileList.forEach(item => {
          value = value + item.path + '#';
        })
        value = value.slice(0, value.length - 1);
      }
      this.formModel[mark] = value;
      // 是否还能继续上传
      let formItem = this.formItems.find(item => item.key === mark);
      if (formItem.count) {
        this.$set(formItem, 'uploadShow', formItem.count > fileList.length)
      }
    },

  }
}
</script>

<style scoped lang="less">
.edui-editor, .edui-combox-body {
  height: 20px !important;
}
</style>
