package com.xyh.common.exception;

import com.xyh.common.entity.ResultCode;
import lombok.Getter;

/**
 * @Author SQ  自定义异常处理类
 * @Date 2020/8/10 0010 20:48
 * @Version 1.0
 */

@Getter
public class CommonException extends RuntimeException{

    private ResultCode code = ResultCode.SERVER_ERROR;
    public CommonException(){}
    public CommonException(ResultCode resultCode) {
        super(resultCode.message());
        this.code = resultCode;
    }
}
