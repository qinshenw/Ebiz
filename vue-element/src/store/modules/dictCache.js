/**
 * 缓存
 */
import {setCache} from "@/utils/cache";
import {commonService} from "@/services";
import {notification} from 'ant-design-vue';


export default {
  namespaced: true,
  state: {
    dictData: []
  },
  getters: {},
  mutations: {
    setDictData(state, dictData) {
      state.dictData = dictData;
    }
  },
  actions: {
    // 加载数据
    async loadCache({commit}) {
      // 加载数据
      const resp = await commonService.allDict();
      commit("setDictData", resp.data);
      resp.data.forEach(item => {
        setCache(item.mark + '_' + item.value, item);
      })
      notification.success({message: '缓存更新成功'})
    },
  }
}