package cn.jbone.fs.repo;

import cn.jbone.fs.common.dataobject.*;


public interface IFileRepository {

    UploadResponse upload(UploadRequest request);
    DownloadResponse download(DownloadRequest request);
    void delete(DeleteRequest request);
    ViewResponse view(ViewRequest request);
}
