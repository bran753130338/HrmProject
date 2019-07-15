package cn.wuyi.domain;

import lombok.Data;

import java.util.Date;

//公告实体类
@Data
public class Anno {
    private Integer id;//公告编号

    private String title;//公告标题

    private String content;//公告内容

    private Date date;//发布日期
}
