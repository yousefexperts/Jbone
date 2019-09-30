package org.apereo.cas.web.authentication;

import cn.jbone.common.rpc.Result;
import cn.jbone.common.utils.PasswordUtils;
import cn.jbone.sys.api.UserApi;
import cn.jbone.sys.common.UserRequestDO;
import cn.jbone.sys.common.UserResponseDO;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.credential.UsernamePasswordCredential;
import org.apereo.cas.authentication.handler.support.*;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

public class JboneAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {

    private Logger logger = LoggerFactory.getLogger(getName());

    private final String requiredRole;
    private UserApi userApi;

    public JboneAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order,final String requiredRole) {
        super(name, servicesManager, principalFactory, order);
        this.requiredRole = requiredRole;
    }

    @Override
    protected AuthenticationHandlerExecutionResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential, String originalPassword) throws GeneralSecurityException, PreventedException {
        UserRequestDO userRequestDO = UserRequestDO.buildAll(credential.getUsername(),null);
        Result<UserResponseDO> result = getUserApi().commonRequest(userRequestDO);

        if(result == null || !result.isSuccess() || result.getData() == null){
            logger.warn("credential.getUsername()",credential.getUsername());
            throw new FailedLoginException("Fails Logged In");
        }

        UserResponseDO userResponseDO = result.getData();


        String caculatePwd = PasswordUtils.getMd5PasswordWithSalt(originalPassword,userResponseDO.getSecurityInfo().getSalt());
        if(!caculatePwd.equals(userResponseDO.getSecurityInfo().getPassword())){
            throw new FailedLoginException("Password Incorect");
        }


        if(userResponseDO.getSecurityInfo().getLocked() == 1){
            logger.warn("credential.getUsername()",credential.getUsername());
            throw new FailedLoginException("Account Locked");
        }

        if(userResponseDO.getAuthInfo() == null || CollectionUtils.isEmpty(userResponseDO.getAuthInfo().getRoles()) || !userResponseDO.getAuthInfo().getRoles().contains(requiredRole)){
            logger.warn("credential.getUsername()",credential.getUsername());
            throw new FailedLoginException("FailedLoginException");
        }
        Map<String,Object> attributes = new HashMap<>();
        attributes.put("userInfo",userResponseDO);

        return createHandlerResult(credential,
                this.principalFactory.createPrincipal(credential.getUsername(),attributes));


    }

    public UserApi getUserApi() {
        return userApi;
    }

    public void setUserApi(UserApi userApi) {
        this.userApi = userApi;
    }
}
