package cn.ypier.exception;

import cn.ypier.result.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @Author Ypier
 */
@ControllerAdvice
public class AdviceException {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public CommonResult exceptionHandler(Exception e) {
        return CommonResult.failed(e.getMessage());
    }
}
