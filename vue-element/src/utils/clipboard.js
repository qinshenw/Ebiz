import Vue from "vue";
import Clipboard from "clipboard";

function clipboardSuccess() {
  Vue.prototype.$message.success("复制成功");
}

function clipboardError() {
  Vue.prototype.$message.error("复制失败");
}

/**
 * 复制数据
 * @param text
 * @param event
 */
export default function handleClipboard(text, event) {
  const clipboard = new Clipboard(event.target, {
    text: () => text,
  });
  clipboard.on("success", () => {
    clipboardSuccess();
    clipboard.destroy();
  });
  clipboard.on("error", () => {
    clipboardError();
    clipboard.destroy();
  });
  clipboard.onClick(event);
}