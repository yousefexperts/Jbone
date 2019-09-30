package cn.jbone.cas.client.utils;

import cn.jbone.sys.common.UserResponseDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


public class SessionUtil {
    public static UserResponseDO getCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        if(subject != null && subject.getPrincipals() != null){
            return (UserResponseDO)subject.getPrincipals().getPrimaryPrincipal();
        }
        return null;
    }
}
