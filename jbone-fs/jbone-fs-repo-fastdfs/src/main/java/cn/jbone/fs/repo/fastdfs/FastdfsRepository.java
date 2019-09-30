package cn.jbone.fs.repo.fastdfs;

import cn.jbone.common.exception.JboneException;
import cn.jbone.configuration.JboneConfiguration;
import cn.jbone.fs.common.dataobject.*;
import cn.jbone.fs.common.utils.FileUtils;
import cn.jbone.fs.repo.IFileRepository;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class FastdfsRepository implements IFileRepository {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StorageClient1 storageClient;

    @Autowired
    private JboneConfiguration jboneConfiguration;
    private NameValuePair[] nameValuePairs;

    @Override
    public UploadResponse upload(UploadRequest request){
        UploadResponse response = new UploadResponse();
        String fileUrl = null;
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            NameValuePair nameValuePair = new NameValuePair();
            nameValuePair.setName("size");
            nameValuePair.setValue(request.getBytes().length + "");
            nameValuePairs.add(nameValuePair);

            NameValuePair nameValuePair2 = new NameValuePair();
            nameValuePair2.setName("originName");
            nameValuePair2.setValue(request.getFileName());
            nameValuePairs.add(nameValuePair2);

            try {
                fileUrl = storageClient.upload_file1(request.getBytes(), FileUtils.getSuffix(request.getFileName()),nameValuePairs.toArray(new NameValuePair[nameValuePairs.size()]));
            } catch (Exception e) {

                logger.warn("warn",e);
                fileUrl = storageClient.upload_file1(request.getBytes(), FileUtils.getSuffix(request.getFileName()),nameValuePairs.toArray(new NameValuePair[nameValuePairs.size()]));
            }
        } catch (Exception e) {
            logger.error("error",e);
            throw new JboneException("JboneException");
        }
        response.setUrl(fileUrl == null ? null : jboneConfiguration.getFs().getFastDfs().getDomain() + fileUrl);
        return response;
    }

    @Override
    public DownloadResponse download(DownloadRequest request) {
        DownloadResponse response = new DownloadResponse();
        try {
            byte[] bytes = storageClient.download_file1(FileUtils.getFileNameWithoutDomain(request.getFileUrl()));
            response.setBytes(bytes);

            ViewRequest viewRequest = new ViewRequest();
            viewRequest.setFileUrl(request.getFileUrl());
            ViewResponse viewResponse = this.view(viewRequest);
            if(viewRequest != null){
                response.setMetadata(viewResponse.getMetadata());
            }
        } catch (Exception e) {
            logger.error("error",e);
            throw new JboneException("JboneException");
        }
        return response;
    }

    @Override
    public void delete(DeleteRequest request) {
        try {
            int count = storageClient.delete_file1(FileUtils.getFileNameWithoutDomain(request.getFileUrl()));
            if(count != 0){
                throw new JboneException("JboneException");
            }
        } catch (Exception e) {
            logger.error("error",e);
            throw new JboneException("JboneException");
        }
    }

    @Override
    public ViewResponse view(ViewRequest request) {
        ViewResponse response = new ViewResponse();
        try {
            NameValuePair[] nameValuePairs = storageClient.get_metadata1(FileUtils.getFileNameWithoutDomain(request.getFileUrl()));
            if(nameValuePairs != null && nameValuePairs.length > 0){
                Map<String,String> metadata = new HashMap<>();
                for (NameValuePair nameValuePair:nameValuePairs){
                    metadata.put(nameValuePair.getName(),nameValuePair.getValue());
                }
                response.setMetadata(metadata);
            }
        } catch (Exception e) {
            logger.error("error",e);
            throw new JboneException("JboneException");
        }

        return response;
    }
}
