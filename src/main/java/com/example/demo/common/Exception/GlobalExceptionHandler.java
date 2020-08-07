package com.example.demo.common.Exception;

import com.example.demo.common.ResultCodeEnum;
import com.example.demo.common.Vo.R;
import com.example.demo.common.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 统一异常处理器
@ControllerAdvice // 增强
@Slf4j // 使用十分简单
public class GlobalExceptionHandler {

    // 处理的所有的 Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error();
    }

    // 处理自己写的统一异常 CustomException
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public R error(CustomException e){
//        e.printStackTrace();  默认的
//        log.error(e.getMessage()); // 直接获取信息
        log.error(ExceptionUtil.getMessage(e));
        return R.error().message(e.getMessage()).code(e.getCode());
    }

    // 优先匹配精确的异常
    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public R error(BadSqlGrammarException e){
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }




}