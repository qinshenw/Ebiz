package edu.controller;

import com.wf.captcha.GifCaptcha;
import com.wf.captcha.base.Captcha;
import edu.support.base.controller.BaseController;
import edu.support.resp.JsonResponse;
import edu.support.resp.UploadResp;
import edu.support.service.FileUploadService;
import edu.support.utils.CacheMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 公共请求
 *
 * @author 执笔
 * @date 13:08
 */
@RestController
@RequestMapping("api/common")
@Slf4j
public class CommonController extends BaseController {

    @Autowired
    private FileUploadService uploadService;
    /**
     * 上传文件
     *
     * @param file
     * @param mark
     * @return
     */
    @PostMapping("upload")
    public JsonResponse<UploadResp> upload(MultipartFile file, String mark) {
        if (file == null || file.isEmpty()) {
            return JsonResponse.fail("请选择要上传的文件");
        }
        String path = uploadService.upload(file, "app");
        return JsonResponse.success(new UploadResp(mark, path, mark));
    }


    /**
     * 下载文件
     *
     * @param path
     * @param response
     */
    @GetMapping("download")
    public void download(String path, HttpServletResponse response) {
        // 下载路径
        String filePath = uploadPath + path;
        //待下载文件名
        String fileName = FilenameUtils.getName(path);
        // 设置强制下载不打开
        response.setContentType("application/force-download");
        // 设置文件名
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
        try {
            IOUtils.copy(new FileInputStream(filePath), response.getOutputStream());
        } catch (IOException e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 验证码
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping("captcha")
    public void captcha(HttpServletResponse response, String flag) throws IOException {
        // 三个参数分别为宽、高、位数
        GifCaptcha gifCaptcha = new GifCaptcha(80, 34, 5);
        // 设置类型：字母数字混合
        gifCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        //获取验证码
        String text = gifCaptcha.text();
        CacheMap.put("captcha_" + flag, text);
        // 输出验证码
        gifCaptcha.out(response.getOutputStream());
    }
}
