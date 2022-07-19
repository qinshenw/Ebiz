import axios from 'axios'
import Cookie from 'js-cookie'
import qs from 'qs'
import {httpBaseUrl, httpContentType, httpTimeout, tokenName} from "@/config"


/**
 * http 请求
 */
const instance = axios.create({
  baseURL: httpBaseUrl,
  timeout: httpTimeout,
  headers: {"Content-Type": httpContentType},
  transformRequest: [function (data) {
    // `transformRequest` 允许在向服务器发送前，修改请求数据
    // 只能用在 'PUT', 'POST' 和 'PATCH' 这几个请求方法
    return qs.stringify(data)
    // 因此network中查看的结果是：name=xiaoming&age=12&sex=man
  }],
});

/**
 * post 请求
 * @param url
 * @param data
 * @param headers
 */
async function post(url, data, headers = {}) {
  return instance({
    url: url,
    method: "post",
    data,
    headers
  })
}

/**
 * get 请求
 * @param url
 * @param data
 * @param headers
 */
async function get(url, data, headers = {}) {
  return instance({
    url: url,
    method: "get",
    data,
    headers
  })
}

/**
 * 设置认证信息
 * @param authToken {Object}
 */
function setAuthorization(authToken) {
  Cookie.set("loginAuthorization", authToken)
}

/**
 * 移出认证信息
 */
function removeAuthorization() {
  Cookie.remove("loginAuthorization")
}

/**
 * 检查认证信息
 */
function checkAuthorization() {
  if (Cookie.get("loginAuthorization")) {
    return true
  }
  return false
}

/**
 * 加载 axios 拦截器
 * @param interceptors
 * @param options
 */
function loadInterceptors(interceptors, options) {
  const {request, response} = interceptors
  // 加载请求拦截器
  request.forEach(item => {
    let {onFulfilled, onRejected} = item
    if (!onFulfilled || typeof onFulfilled !== 'function') {
      onFulfilled = config => config
    }
    if (!onRejected || typeof onRejected !== 'function') {
      onRejected = error => Promise.reject(error)
    }
    instance.interceptors.request.use(
      config => onFulfilled(config, options),
      error => onRejected(error, options)
    )
  })
  // 加载响应拦截器
  response.forEach(item => {
    let {onFulfilled, onRejected} = item
    if (!onFulfilled || typeof onFulfilled !== 'function') {
      onFulfilled = response => response
    }
    if (!onRejected || typeof onRejected !== 'function') {
      onRejected = error => Promise.reject(error)
    }
    instance.interceptors.response.use(
      response => onFulfilled(response, options),
      error => onRejected(error, options)
    )
  })
}

/**
 * 解析 url 中的参数
 * @param url
 * @returns {Object}
 */
function parseUrlParams(url) {
  const params = {}
  if (!url || url === '' || typeof url !== 'string') {
    return params
  }
  const paramsStr = url.split('?')[1]
  if (!paramsStr) {
    return params
  }
  const paramsArr = paramsStr.replace(/&|=/g, ' ').split(' ')
  for (let i = 0; i < paramsArr.length / 2; i++) {
    const value = paramsArr[i * 2 + 1]
    params[paramsArr[i * 2]] = value === 'true' ? true : (value === 'false' ? false : value)
  }
  return params
}

export {
  post,
  get,
  loadInterceptors,
  parseUrlParams,
  setAuthorization,
  removeAuthorization,
  checkAuthorization
}
