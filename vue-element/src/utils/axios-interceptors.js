import Cookie from 'js-cookie'
import {tokenName} from "@/config"
// 401拦截
const resp401 = {
  /**
   * 响应数据之前做点什么
   * @param response 响应对象
   * @param options 应用配置 包含: {router, i18n, store, message}
   * @returns {*}
   */
  onFulfilled(response, options) {
    const {message} = options
    if (response.status === 401) {
      message.error('无此接口权限')
    }
    return response
  },
  /**
   * 响应出错时执行
   * @param error 错误对象
   * @param options 应用配置 包含: {router, i18n, store, message}
   * @returns {Promise<never>}
   */
  onRejected(error, options) {
    const {message} = options
    message.error(error.message)
    return Promise.reject(error)
  }
}

const resp403 = {
  onFulfilled(response, options) {
    const {message} = options
    if (response.status === 403) {
      message.error(`请求被拒绝`)
    }
    return response
  }
}

/**
 * 错误码
 * @type {{onFulfilled(*, *): *}}
 */
const respErr = {
  onFulfilled(response, options) {
    const {message, router} = options
    const code = response.data.code;
    console.log(response)
    const token = response.headers['x-auth-token'];
    if (token) {
      Cookie.set(tokenName, token)
    }
    if (code === "FAIL" || code === 'fail') {
      message.error(response.data.message)
      return Promise.reject()
    } else if (code === "NO_LOGIN" ||  code === "noLogin") {
      message.error(response.data.message)
      // 跳转登录
      router.push('/login');
      return Promise.reject()
    } else {
      return response.data
    }

  }
}

const reqCommon = {
  /**
   * 发送请求之前做些什么
   * @param config axios config
   * @param options 应用配置 包含: {router, i18n, store, message}
   * @returns {*}
   */
  onFulfilled(config) {
    //const {config} = config
    if (Cookie.get(tokenName)) {
      config.headers[tokenName] = Cookie.get(tokenName);
    }
    return config
  },
  /**
   * 请求出错时做点什么
   * @param error 错误对象
   * @param options 应用配置 包含: {router, i18n, store, message}
   * @returns {Promise<never>}
   */
  onRejected(error, options) {
    const {message} = options
    message.error(error.message)
    return Promise.reject(error)
  }
}

export default {
  request: [reqCommon], // 请求拦截
  response: [resp401, resp403, respErr] // 响应拦截
}
