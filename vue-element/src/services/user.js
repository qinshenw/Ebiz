import {get, post, removeAuthorization} from '@/utils/request'
import {authPermissionsKey, authRoleKey, authRoutesKey} from '@/config'

/**
 * 登录服务
 * @param name 账户名
 * @param password 账户密码
 * @returns {Promise<>}
 */
export async function login(name, password) {
  return post("/api/login", {
    username: name,
    password: password
  })
}


/**
 * 退出登录
 */
export function logout() {
  localStorage.removeItem(authRoutesKey)
  localStorage.removeItem(authPermissionsKey)
  localStorage.removeItem(authRoleKey)
  removeAuthorization()
}
export default {
  login,
  logout
}
