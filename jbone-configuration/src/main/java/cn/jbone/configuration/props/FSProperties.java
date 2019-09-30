package cn.jbone.configuration.props;

import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
public class FSProperties {

    @NestedConfigurationProperty
    private QiniuProperties qiniu = new QiniuProperties();

    @NestedConfigurationProperty
    private FastDfsProperties fastDfs = new FastDfsProperties();

    @Data
    public static class QiniuProperties{
        private String accessKey;
        private String secretKey;
        private String bucket;
        private String domain;
    }

    @Data
    public static class FastDfsProperties{
        private String connectTimeoutInSeconds;
        private String networkTimeoutInSeconds;
        private String charset;
        private String httpAntiStealToken;
        private String httpSecretKey;
        private String httpTrackerHttpPort;
        private String trackerServers;

        private String domain;
    }
}
