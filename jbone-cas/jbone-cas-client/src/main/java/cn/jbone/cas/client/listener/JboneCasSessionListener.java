package cn.jbone.cas.client.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JboneCasSessionListener implements SessionListener {

    private Logger logger = LoggerFactory.getLogger(JboneCasSessionListener.class);

    @Override
    public void onStart(Session session) {
        logger.debug("onStart {}",session.getId());
    }

    @Override
    public void onStop(Session session) {
        logger.debug("onStop {}",session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        logger.debug("onExpiration {}",session.getId());
    }
}
