package edu.service.impl;


import edu.support.base.service.BaseServiceImpl;
import edu.mapper.NoticeMapper;
import edu.domain.Notice;
import edu.service.NoticeService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Notice)表服务实现类
 *
 * @author 执笔
 * @date 2021-05-07 23:48:10
 */
@Service
public class NoticeServiceImpl extends BaseServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
}
