package cn.jbone.cms.common.dataobject;

import lombok.Data;

@Data
public class StatisticsDO {
    private long articleCount;
    private long categoryCount;
    private long specialCount;
    private long hitsCount;
    private long tagCount;
    private long linkCount;

}

