package cn.jbone.bpm.admin.controller;

import cn.jbone.configuration.JboneConfiguration;
import cn.jbone.sys.api.UserApi;
import cn.jbone.sys.common.UserResponseDO;
import org.apache.shiro.SecurityUtils;
import org.springframework.ui.ModelMap;

public class CommonController {

    /**
     *
     * @param modelMap
     * @param userApi
     * @param jboneConfiguration
     */
    public void setCurrentUser(ModelMap modelMap, UserApi userApi, JboneConfiguration jboneConfiguration) {
        UserResponseDO currentUser = (UserResponseDO) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        modelMap.put("user", currentUser);
    }
}
