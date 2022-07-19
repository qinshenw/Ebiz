import {get} from '@/utils/request'

/**
 * 全部字典值
 * @returns {AxiosPromise<any>}
 */
export async function allDict() {
  return get("/api/sysDictData/allList", {})
}

export default {
  allDict
}
