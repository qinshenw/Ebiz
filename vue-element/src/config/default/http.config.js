/**
 * http 配置
 */
const HTTP = {
  httpTimeout: 30000,                                                                              // 最长请求时间
  httpBaseUrl: process.env.NODE_ENV === "development" ? "http://127.0.0.1:8888" : "http://127.0.0.1:8888",            // 基础请求地址
  // 配后端数据的接收方式application/json;charset=UTF-8或者application/x-www-form-urlencoded;charset=UTF-8
  httpContentType: "application/x-www-form-urlencoded;charset=UTF-8",
  // token名称
  tokenName: "X-Auth-Token",
}

module.exports = HTTP