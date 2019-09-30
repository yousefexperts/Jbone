package cn.jbone.cms.portal.intercepter;

import cn.jbone.cas.client.utils.SessionUtil;
import cn.jbone.cms.portal.cache.CachedSiteManager;
import cn.jbone.cms.portal.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Component
public class CommonInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private CommonService commonService;

    @Autowired
    private CachedSiteManager cachedSiteManager;

    Logger logger = LoggerFactory.getLogger(getClass());

    private static List<String> excludeUris = new ArrayList<>();

    static {

        excludeUris.add("/login");
        excludeUris.add("/logout");

        excludeUris.add(".css");
        excludeUris.add(".js");
        excludeUris.add(".jpg");
        excludeUris.add(".jpeg");
        excludeUris.add(".gif");
        excludeUris.add(".png");
        excludeUris.add(".ico");

        excludeUris.add(".otf");
        excludeUris.add(".eot");
        excludeUris.add(".svg");
        excludeUris.add(".ttf");
        excludeUris.add(".woff");
        excludeUris.add(".woff2");

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        for (String suffix : excludeUris){
            if(uri.endsWith(suffix)){
                logger.debug("debug. uri : {}" , request.getRequestURI());
                return true;
            }
        }
        cachedSiteManager.checkAndUpdateCurrentSite(request);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        String uri = request.getRequestURI();

        for (String suffix : excludeUris){
            if(uri.endsWith(suffix)){
                logger.debug("debug. uri : {}" , request.getRequestURI());
                return;
            }
        }

        logger.info("uri : {}" , request.getRequestURI());
        if(modelAndView != null){
            cachedSiteManager.checkAndUpdateCurrentSite(request);

            commonService.setCommonProperties(modelAndView.getModelMap());

            modelAndView.getModelMap().addAttribute("CURR_USER", SessionUtil.getCurrentUser());
        }

    }
}
