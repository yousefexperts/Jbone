package cn.jbone.cms.api.starter;

import cn.jbone.cms.api.StatisticsApi;
import cn.jbone.cms.common.dataobject.StatisticsDO;
import cn.jbone.common.rpc.Result;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class StatisticsApiFallbackFactory implements FallbackFactory<StatisticsApi> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public StatisticsApi create(Throwable throwable) {
        return new StatisticsApi() {
            @Override
            public Result<StatisticsDO> count() {
                logger.error("rpc count broke",throwable);
                return Result.wrapProtectedError();
            }
        };

    }
}
