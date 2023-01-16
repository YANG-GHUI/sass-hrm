package com.ygh.ihrm.common.handler;

import com.ygh.ihrm.common.entity.Result;
import com.ygh.ihrm.common.entity.ResultCode;
import com.ygh.ihrm.common.exception.CommonException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 *
 * @author YangGh
 * @version 1.0
 */
@ControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result error(HttpServletRequest request,
                        HttpServletResponse response,
                        Exception e) {
        if (e.getClass() == CommonException.class) {
            CommonException ce = (CommonException) e;
            return new Result(ce.getResultCode());
        } else {
            return new Result(ResultCode.SERVER_ERROR);
        }
    }
}
