package com.ygh.ihrm.common.exception;

import com.ygh.ihrm.common.entity.ResultCode;
import lombok.Getter;

/**
 * 自定义异常
 *
 * @author YangGh
 * @version 1.0
 */
@Getter
public class CommonException extends Exception {

    private final ResultCode resultCode;

    public CommonException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
