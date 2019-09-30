package cn.jbone.common.ui.result;


public class ModelResult extends Result {
    private Object data;

    public ModelResult(ResultStatus resultStatus){
        super(resultStatus);
    }

    public ModelResult(ResultStatus resultStatus,Object data){
        this(resultStatus.getStatus(),resultStatus.getMsg(),data);
    }

    public ModelResult(int status,String msg){
        super(status, msg);
    }

    public ModelResult(int status,String msg, Object data){
        this(status, msg);
        this.data = data;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
