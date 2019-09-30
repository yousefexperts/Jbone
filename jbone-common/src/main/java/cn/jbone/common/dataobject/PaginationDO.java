package cn.jbone.common.dataobject;

import lombok.Data;

import java.util.List;


@Data
public class PaginationDO {
    private boolean showLastButton;
    private boolean showNextButton;
    private int lastPage;
    private int nextPage;

    private List<PageDO> pages;

}
