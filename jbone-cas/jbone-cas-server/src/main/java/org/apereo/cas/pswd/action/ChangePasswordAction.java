package org.apereo.cas.pswd.action;

import cn.jbone.common.rpc.Result;
import cn.jbone.sys.api.UserApi;
import cn.jbone.sys.common.dto.request.ChangePasswordRequestDTO;
import org.apereo.cas.authentication.principal.Principal;
import org.apereo.cas.ticket.registry.TicketRegistrySupport;
import org.apereo.cas.web.support.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;


public class ChangePasswordAction extends AbstractAction {

    @Autowired
    private UserApi userApi;

    @Autowired
    @Qualifier("defaultTicketRegistrySupport")
    private TicketRegistrySupport ticketRegistrySupport;


    @Override
    protected Event doExecute(RequestContext requestContext) throws Exception {

        String password = (String)requestContext.getCurrentEvent().getAttributes().get("password");
        String confirmedPassword = (String)requestContext.getCurrentEvent().getAttributes().get("confirmedPassword");

        Principal principal = WebUtils.getPrincipalFromRequestContext(requestContext,this.ticketRegistrySupport);

        ChangePasswordRequestDTO changePasswordRequestDTO = new ChangePasswordRequestDTO();
        changePasswordRequestDTO.setConfirmedPassword(confirmedPassword);
        changePasswordRequestDTO.setPassword(password);
        changePasswordRequestDTO.setUsername(principal.getId());

        Result<Void> result = userApi.changePassword(changePasswordRequestDTO);
        if(result.isSuccess()){
            return success();
        }

        return error();
    }
}
