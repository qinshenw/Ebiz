/**
 * 获取缓存
 * json 格式
 * @returns {string|*}
 */
export function getCache(key) {
  key = key.toUpperCase();
  return JSON.parse(localStorage.getItem(key));
}

/**
 * 保存数据
 * json格式
 * @returns {void|*}
 */
export function setCache(key, val) {
  key = key.toUpperCase();
  localStorage.setItem(key, JSON.stringify(val));
}

/**
 * 删除缓存
 * @param key
 * @returns {void|*}
 */
export function removeCache(key) {
  key = key.toUpperCase();
  localStorage.removeItem(key);
}
