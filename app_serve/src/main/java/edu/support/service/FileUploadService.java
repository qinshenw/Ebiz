package edu.support.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author 执笔
 * @date 10:47
 */
@Service
@Slf4j
public class FileUploadService {

    @Value("${fast.upload.path}")
    protected String uploadPath;


    public String upload(MultipartFile file, String model) {
        String path = "/upload/" + model + "/" + DateFormatUtils.format(new Date(), "yyyy-MM-dd") + "/";
        String fileName = generateNewName(file.getOriginalFilename());
        File newFile = new File(uploadPath + path + fileName);
        newFile.getParentFile().mkdirs();
        try {
            newFile.createNewFile();
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("◎Upload File◎  " + newFile.getAbsolutePath());
        return path + fileName;
    }

    /**
     * 得到一个新名字
     *
     * @param name 原来的名字
     * @return
     */
    private String generateNewName(String name) {
        String extension = FilenameUtils.getExtension(name);
        if (StringUtils.isBlank(extension)) {
            RandomStringUtils.randomAlphanumeric(32);
        }
        return RandomStringUtils.randomAlphanumeric(32) + "." + extension;
    }
}
