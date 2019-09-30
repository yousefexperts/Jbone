package cn.jbone.fs.common.dataobject;

import lombok.Data;

import java.util.Map;

@Data
public class ViewResponse {
    private Map<String,String> metadata;
}
