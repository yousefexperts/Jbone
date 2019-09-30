package cn.jbone.common.rpc;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -875864042638324305L;

    private ResultStatus status = new ResultStatus();
    private T data;


    private static final int STATUS_404 = 404;
    private static final int STATUS_500 = 500;
    private static final int STATUS_PROTECTED = 1000;
    private static final int SUCCESS_CODE=0;

    public Result(int code, String message){
        status.setCode(code);
        status.setMessage(message);
    }

    public Result(T data){
        this.setData(data);
    }

    public Result(){}

    public boolean isSuccess(){
        return this.getStatus().getCode() == SUCCESS_CODE;
    }

    /**
     *
     * @param code
     * @param message
     * @return
     */
    public static Result wrapError(int code, String message) {
        Result result = new Result();
        result.getStatus().setCode(code);
        result.getStatus().setMessage(message);
        return result;
    }

    /**
     *
     * @param message
     * @return
     */
    public static Result wrap404Error(String message) {
        return wrapError(STATUS_404,message);
    }


    /**
     *
     * @param message
     * @return
     */
    public static Result wrap500Error(String message) {
        return wrapError(STATUS_500,message);
    }

    /**
     *
     * @return
     */
    public static Result wrap500Error() {
        return wrap500Error("wrap500Error");
    }

    /**
     *
     * @return
     */
    public static Result wrapProtectedError() {
        return wrapError(STATUS_PROTECTED,"STATUS_PROTECTED");
    }


    public static Result wrapSuccess() {
        return new Result();
    }

    public static <T> Result<T> wrapSuccess(T a) {
        return new Result<>(a);
    }
}
