package cn.jbone.fs.common.dataobject;

import lombok.Data;

import java.util.Map;


@Data
public class DownloadResponse {
    private byte[] bytes;
    private Map<String,String> metadata;
}
