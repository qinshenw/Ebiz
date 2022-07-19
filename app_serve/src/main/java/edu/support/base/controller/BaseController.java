package edu.support.base.controller;


import edu.domain.User;
import edu.support.context.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 基础控制器
 *
 * @author 执笔
 * @date 19:32
 */
@Slf4j
@Controller
public abstract class BaseController {

    @Autowired
    protected HttpSession session;
    @Autowired
    protected HttpServletRequest request;

    @Value("${fast.upload.path}")
    protected String uploadPath;

    /**
     * 保存文件
     *
     * @param file
     * @return
     */
    protected String saveFile(MultipartFile file) {
        String rootPath = uploadPath + "upload";
        // 文件后缀
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        // 新名字
        String fileName = UUID.randomUUID() + "." + extension;
        File newFile = new File(rootPath + "/" + fileName);
        newFile.getParentFile().mkdirs();
        try {
            newFile.createNewFile();
            file.transferTo(newFile);
        } catch (IOException e) {
            log.error("上传文件异常: ", e);
        }
        log.info("上传文件: " + newFile.getAbsolutePath());
        return "/upload/" + fileName;
    }

    /**
     * 登录的用户
     * @return
     */
    protected User loginUser() {
        Object user = session.getAttribute("loginUser");
        return (User) user;
    }

    /**
     * 登录的用户的id
     * @return
     */
    protected Long loginUserId() {
        return loginUser().getId();
    }

}
