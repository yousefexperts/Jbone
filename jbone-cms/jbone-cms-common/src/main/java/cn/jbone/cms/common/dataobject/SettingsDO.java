package cn.jbone.cms.common.dataobject;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SettingsDO {

    private Long id;
    private String settingKey;
    private String settingValue;
    private String description;
    private Timestamp addTime;
    private Timestamp updateTime;
    private Integer creator;

}
