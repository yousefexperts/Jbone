package cn.jbone.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason="NOT_FOUND")
public class Jbone404Exception extends RuntimeException {
}
