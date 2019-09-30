package cn.jbone.cms.core.service;

import cn.jbone.cms.common.constant.SiteSettingConstant;
import cn.jbone.cms.common.dataobject.LinkDO;
import cn.jbone.cms.core.converter.LinkConverter;
import cn.jbone.cms.core.dao.entity.Link;
import cn.jbone.cms.core.dao.repository.LinkRepository;
import cn.jbone.cms.core.validator.ContentValidator;
import cn.jbone.common.exception.JboneException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private LinkConverter linkConverter;
    @Autowired
    private ContentValidator contentValidator;
    @Autowired
    private SiteSettingsService siteSettingsService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void addOrUpdate(LinkDO linkDO){
        checkParam(linkDO);
        contentValidator.checkPermition(linkDO.getCreator(),linkDO.getSiteId());
        checkLimit(linkDO);
        Link link = linkConverter.toLink(linkDO);
        linkRepository.save(link);
    }

    private void checkParam(LinkDO linkDO){
        Assert.notNull(linkDO.getTitle(),"notNull");
        Assert.notNull(linkDO.getUrl(),"notNull");
    }

    /**
     * 校验限额
     */
    private void checkLimit(LinkDO linkDO){
        if(linkDO.getId() != null && linkDO.getId() <= 0){
            Map<String, String> settingMap = siteSettingsService.getSettingsMap(linkDO.getSiteId());
            if(!settingMap.containsKey(SiteSettingConstant.LIMIT_LINK_COUNT)){
                throw new JboneException("JboneException");
            }
            Long limitCount = Long.parseLong(settingMap.get(SiteSettingConstant.LIMIT_LINK_COUNT));
            if(limitCount <= 0){
                logger.warn("JboneException{}",limitCount);
                throw new JboneException("JboneException"+ limitCount + "JboneException");
            }
            long currentCount = linkRepository.countBySiteId(linkDO.getSiteId());
            if(currentCount >= limitCount){
                logger.warn("JboneException{}",limitCount);
                throw new JboneException("JboneException"+ limitCount + "JboneException");
            }
        }
    }

    public void delete(Long id,Integer userId){
        if(!linkRepository.existsById(id)){
            throw new JboneException("JboneException");
        }
        Link link = linkRepository.getOne(id);
        contentValidator.checkPermition(userId,link.getSiteId());
        linkRepository.delete(link);
    }

    public void batchDelete(String ids,Integer userId){
        String[] idArray = ids.split(",");
        if(idArray == null || idArray.length <= 0){
            return;
        }
        List<Link> links = new ArrayList<>();
        for (String id: idArray){
            if(StringUtils.isBlank(id)){
                continue;
            }
            Link link = linkRepository.getOne(Long.parseLong(id));
            contentValidator.checkPermition(userId,link.getSiteId());
            links.add(link);
        }
        if(!CollectionUtils.isEmpty(links)){
            linkRepository.deleteInBatch(links);
        }

    }

    public LinkDO get(Long id){
        Link link = linkRepository.getOne(id);
        LinkDO linkDO = linkConverter.toLinkDO(link);
        return linkDO;
    }

    public List<LinkDO> getAll(Integer siteId){
        List<Link> links = linkRepository.findAllBySiteIdOrderByOrders(siteId);
        return linkConverter.toLinkDOs(links);
    }

}
