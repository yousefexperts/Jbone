package cn.jbone.sm.monitor.publisher;

import com.netflix.hystrix.strategy.HystrixPlugins;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JboneHystrixMetricsPublisherConfigration {
    @Bean
    public JboneHystrixMetricsPublisher getJboneHystrixMetricsPublisher(){
        JboneHystrixMetricsPublisher publisher = new JboneHystrixMetricsPublisher();
        return publisher;
    }
}
