package cn.jbone.cms.common.dataobject;

import cn.jbone.cms.common.enums.StatusEnum;
import cn.jbone.sys.common.UserResponseDO;
import lombok.Data;

@Data
public class CommentDO {

    private Long id;
    private String content;
    private String ip;
    private StatusEnum status;
    private String username;
    private Long pid;
    private Long articleId;
    private UserResponseDO author;
    private Integer creator;
    private Long addTime;
    private Long updateTime;
    private String addTimeText;
    private String updateTimeText;
    private Integer siteId;
}
