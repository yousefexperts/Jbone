package cn.jbone.fs.api;

import cn.jbone.common.rpc.Result;
import cn.jbone.fs.common.dataobject.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/file")
public interface FileApi {

    @RequestMapping(value = "/upload",method = {RequestMethod.POST})
    Result<UploadResponse> upload(@RequestBody UploadRequest request);


    @RequestMapping(value = "/download",method = {RequestMethod.GET})
    Result<DownloadResponse> download(@RequestBody DownloadRequest request);


    @RequestMapping(value = "/delete",method = {RequestMethod.DELETE})
    Result<Void> delete(@RequestBody DeleteRequest request);


    @RequestMapping(value = "/view",method = {RequestMethod.GET})
    Result<ViewResponse> view(@RequestBody ViewRequest request);
}
