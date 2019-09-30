package cn.jbone.common.rpc;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultStatus implements Serializable {
	private int code = 0;
    private String message = "";
    
}
