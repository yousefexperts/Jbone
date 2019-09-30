package cn.jbone.cas.client.realm;

import cn.jbone.common.exception.JboneException;
import cn.jbone.common.rpc.Result;
import cn.jbone.sys.api.UserApi;
import cn.jbone.sys.common.UserRequestDO;
import cn.jbone.sys.common.UserResponseDO;
import io.buji.pac4j.realm.Pac4jRealm;
import io.buji.pac4j.subject.Pac4jPrincipal;
import io.buji.pac4j.token.Pac4jToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.pac4j.cas.profile.CasProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class JboneCasRealm extends Pac4jRealm {
    private static final Logger logger = LoggerFactory.getLogger(JboneCasRealm.class);

    private UserApi userApi;
    private String serverName;

    public JboneCasRealm(EhCacheManager ehCacheManager,UserApi userApi,String serverName){
        this.setCacheManager(ehCacheManager);
        this.userApi = userApi;
        this.serverName = serverName;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Pac4jToken pac4jToken = (Pac4jToken)token;
        LinkedHashMap profiles = pac4jToken.getProfiles();
        Pac4jPrincipal principal = new Pac4jPrincipal(profiles, this.getPrincipalNameAttribute());

        String  username = principal.getName();

        CasProfile casProfile =(CasProfile) profiles.get("CasClient");
        if(casProfile != null){
                Object clientName = casProfile.getAttribute("clientName");
                if(clientName != null){
                    username = clientName.toString().toUpperCase() + "_" + casProfile.getId();
                }
        }

        UserRequestDO userRequestDO = UserRequestDO.buildAll(username,serverName);
        Result<UserResponseDO> result = userApi.commonRequest(userRequestDO);

        if(!result.isSuccess() || result.getData() == null ){
            throw new JboneException(String.format("user[%s] server[%s] is not found.", username, serverName));
        }
        List<Object> principals = CollectionUtils.asList(new Object[]{result.getData(), principal});
        SimplePrincipalCollection principalCollection = new SimplePrincipalCollection(principals, this.getName());

        return new SimpleAuthenticationInfo(principalCollection, pac4jToken.getCredentials());
    }

    /**
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("--AuthorizationInfo--");
        UserResponseDO userResponseDO = principals.oneByType(UserResponseDO.class);
        Set<String> roles = userResponseDO.getAuthInfo().getRoles();
        Set<String> permissions = userResponseDO.getAuthInfo().getPermissions();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        if(roles != null && !roles.isEmpty()){
            Iterator<String> iterator = roles.iterator();
            while (iterator.hasNext()){
                info.addRole(iterator.next());
            }
        }

        if(permissions != null && !permissions.isEmpty()){
            Iterator<String> iterator = permissions.iterator();
            while (iterator.hasNext()){
                info.addStringPermission(iterator.next());
            }
        }

        return info;
    }
}
