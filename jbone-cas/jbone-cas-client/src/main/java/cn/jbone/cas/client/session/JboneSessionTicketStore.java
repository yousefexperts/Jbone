package cn.jbone.cas.client.session;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;


public class JboneSessionTicketStore {

    private Logger logger = LoggerFactory.getLogger(getClass());
    public JboneSessionTicketStore(){

    }
    private StringRedisTemplate redisTemplate;
    public JboneSessionTicketStore(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    private String TICKET_SESSION_PREFIX = "ticket_session_";

    private String SESSION_TICKET_PREFIX = "session_ticket_";

    private Long timeout = 28800l;

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    /**
     *
     * @param sessionId
     * @param ticket
     */
    public void store(String sessionId,String ticket){
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(TICKET_SESSION_PREFIX + ticket,sessionId);
        valueOperations.set(SESSION_TICKET_PREFIX + sessionId,ticket);
        redisTemplate.expire(TICKET_SESSION_PREFIX + ticket,timeout,TimeUnit.MILLISECONDS);
        redisTemplate.expire(SESSION_TICKET_PREFIX + sessionId,timeout,TimeUnit.MILLISECONDS);

        logger.debug("save ticket and session[ticket:{},sessionId:{}]",ticket,sessionId);
    }

    /**
     *
     * @param sessionId
     */
    public void expireBySession(String sessionId){
        expireBySession(sessionId,timeout);
    }

    /**
     *
     * @param sessionId
     * @param timeout
     */
    public void expireBySession(String sessionId,long timeout){
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        String ticket = valueOperations.get(SESSION_TICKET_PREFIX + sessionId);
        if(StringUtils.isNotBlank(ticket)){
            redisTemplate.expire(TICKET_SESSION_PREFIX + ticket,timeout,TimeUnit.MILLISECONDS);
            redisTemplate.expire(SESSION_TICKET_PREFIX + sessionId,timeout,TimeUnit.MILLISECONDS);
        }
        logger.debug("expire ticket and session[ticket:{},sessionId:{}]",ticket,sessionId);
    }

    /**
     *
     * @param sessionId
     */
    public void deleteBySession(String sessionId){
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        String ticket = valueOperations.get(SESSION_TICKET_PREFIX + sessionId);
        if(StringUtils.isNotBlank(ticket)){
            redisTemplate.delete(TICKET_SESSION_PREFIX + ticket);
            redisTemplate.delete(SESSION_TICKET_PREFIX + sessionId);
        }
        logger.debug("delete ticket and session[ticket:{},sessionId:{}]",ticket,sessionId);
    }

    /**
     *
     * @param ticket
     */
    public void deleteByTicket(String ticket){
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        String sessionId = valueOperations.get(TICKET_SESSION_PREFIX + ticket);
        if(StringUtils.isNotBlank(sessionId)){
            redisTemplate.delete(TICKET_SESSION_PREFIX + ticket);
            redisTemplate.delete(SESSION_TICKET_PREFIX + sessionId);
        }
        logger.debug("delete ticket and session[ticket:{},sessionId:{}]",ticket,sessionId);
    }

    /**
     *
     * @param sessionId
     * @return
     */
    public String getTicket(String sessionId){
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        String ticket = valueOperations.get(SESSION_TICKET_PREFIX + sessionId);
        return ticket;
    }

    /**
     *
     * @param ticket
     * @return
     */
    public String getSessionId(String ticket){
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        String sessionId = valueOperations.get(TICKET_SESSION_PREFIX + ticket);
        return sessionId;
    }

}
