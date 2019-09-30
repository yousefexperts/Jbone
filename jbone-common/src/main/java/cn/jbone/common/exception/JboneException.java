package cn.jbone.common.exception;

public class JboneException extends RuntimeException {

    public static final String ILLEGAL_PARAM = "ILLEGAL_PARAM";
    public static final String USER_IS_NOT_FOUND = "USER_IS_NOT_FOUND";
    public static final String SITE_IS_NOT_FOUND = "SITE_IS_NOT_FOUND";
    public static final String PERMISSION_DENIED = "PERMISSION_DENIED";

    public JboneException() {
        super();
    }
    public JboneException(String message) {
        super(message);
    }
}
