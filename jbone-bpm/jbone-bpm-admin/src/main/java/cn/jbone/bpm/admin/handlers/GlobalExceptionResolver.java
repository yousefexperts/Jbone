package cn.jbone.bpm.admin.handlers;

import cn.jbone.common.ui.result.Result;
import cn.jbone.common.utils.ResultUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        boolean isajax = isAjax(request,response);
        Throwable deepestException = deepestException(e);
        return processException(request, response, o, deepestException, isajax);
    }


    private boolean isAjax(HttpServletRequest request, HttpServletResponse response){
        return StringUtils.isNotEmpty(request.getHeader("X-Requested-With"));
    }


    private Throwable deepestException(Throwable e){
        Throwable tmp = e;
        int breakPoint = 0;
        while(tmp.getCause()!=null){
            if(tmp.equals(tmp.getCause())){
                break;
            }
            tmp=tmp.getCause();
            breakPoint++;
            if(breakPoint>1000){
                break;
            }
        }
        return tmp;
    }


    private ModelAndView processException(HttpServletRequest request,
                                          HttpServletResponse response, Object handler,
                                          Throwable ex, boolean isajax) {
        if(isajax){
            return processAjax(request,response,handler,ex);
        }else{
            return processNotAjax(request,response,handler,ex);
        }
    }


    private ModelAndView processAjax(HttpServletRequest request,
                                     HttpServletResponse response, Object handler,
                                     Throwable deepestException){
        ModelAndView empty = new ModelAndView();
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        response.setCharacterEncoding("UTF-8");
        String message = deepestException.getMessage();
        if(deepestException instanceof AuthorizationException){
            message = "AuthorizationException!";
        }
        Result result = ResultUtils.wrapFail(message);
        try(PrintWriter pw =response.getWriter()){
            pw.write(JSON.toJSONString(result));
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        empty.clear();
        return empty;
    }


    private ModelAndView processNotAjax(HttpServletRequest request,
                                        HttpServletResponse response, Object handler, Throwable ex) {
        String view = "/pages/common/error";
        if (ex instanceof AuthorizationException){
            view = "/pages/common/notAuthorized";
        }
        String exceptionMessage = ex.getMessage();
        String fullExceptionMessage = getThrowableMessage(ex);
        Map<String, Object> model = new HashMap<>();
        model.put("exceptionMessage", exceptionMessage);
        model.put("fullExceptionMessage", fullExceptionMessage);
        return new ModelAndView(view, model);
    }


    public String getThrowableMessage(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
