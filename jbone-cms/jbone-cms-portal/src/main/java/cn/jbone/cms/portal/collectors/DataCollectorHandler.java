package cn.jbone.cms.portal.collectors;

import cn.jbone.cms.common.constant.GlobalConstant;
import cn.jbone.cms.common.dataobject.TemplateDO;
import cn.jbone.cms.portal.cache.CachedDataManager;
import cn.jbone.cms.portal.cache.CachedSiteManager;
import cn.jbone.common.exception.JboneException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class DataCollectorHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CachedSiteManager cachedSiteManager;

    @Autowired
    Map<String,IDataCollector> dataCollectorMap;

    @Autowired
    private CachedDataManager cachedDataManager;

    ExecutorService executorService =  new ThreadPoolExecutor(10, 200,60L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(100));

    public void handle(DataCollectorContext context){

        if(StringUtils.isBlank(context.getPage())){
            throw new JboneException("JboneException");
        }

        context.setSiteId(cachedSiteManager.getCurrentSiteId());

        TemplateDO templateDO = cachedSiteManager.getCurrentSite().getTemplate();
        if(templateDO == null){
            templateDO = cachedDataManager.getTemplate(GlobalConstant.DEFAULT_TEMPLATE_CODE);
        }

        if(templateDO == null){
            throw new JboneException("JboneException[" + context.getSiteId() + "]JboneException");
        }

        if(StringUtils.isBlank(templateDO.getDataCollectors())){
            logger.warn("'warn",templateDO.getId());
            return;
        }

        JSONObject dataCollectorObj = JSON.parseObject(templateDO.getDataCollectors());
        String pageDataCollectors = dataCollectorObj.getString(context.getPage());
        logger.info("info：{}",context.getSiteId(),context.getPage(),pageDataCollectors);

        if(StringUtils.isBlank(pageDataCollectors)){
            return;
        }



        String[] dataCollectors = pageDataCollectors.split(",");
        if(dataCollectors.length == 1){

            IDataCollector dataCollector = dataCollectorMap.get(dataCollectors[0]);
            if(dataCollector == null){
                logger.error("error:{}",dataCollectors[0]);
            }else{
                dataCollector.collect(context);
            }
        }else{

            List<Future<Void>> futureList = new ArrayList<>();
            for (String collectorName : dataCollectors){
                IDataCollector dataCollector = dataCollectorMap.get(collectorName);
                if(dataCollector == null){
                    logger.error("error:{}",collectorName);
                    continue;
                }
                futureList.add(executorService.submit(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        dataCollector.collect(context);
                        return null;
                    }
                }));
            }

            if(!CollectionUtils.isEmpty(futureList)){
                for (Future<Void> future : futureList) {
                    try {
                        future.get(1000l,TimeUnit.MILLISECONDS);
                    } catch (Exception e) {
                        logger.error("error",e);
                    }
                }
            }

        }

    }
}
