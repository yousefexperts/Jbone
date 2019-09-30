package cn.jbone.fs.common.dataobject;

import lombok.Data;


@Data
public class UploadRequest {
    private String localFilePath;
    private byte[] bytes;
    private String fileName;
}
