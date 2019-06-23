package cn.cenjp.platform.exception;


import cn.cenjp.platform.result.CodeMsg;
import cn.cenjp.platform.result.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
		e.printStackTrace();
		if(e instanceof GlobalException) {
			GlobalException ex = (GlobalException)e;
			return Result.error(ex.getCm());
		}else if(e instanceof BindException) {
			BindException ex = (BindException)e;
			List<ObjectError> errors = ex.getAllErrors();
			ObjectError error = errors.get(0);
			String msg = error.getDefaultMessage();
			return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
		}else if (e instanceof AccessDeniedException){
			return Result.error(CodeMsg.Access_Deny);
		}else if(e instanceof InternalAuthenticationServiceException){
			return Result.error(CodeMsg.MOBILE_NOT_EXIST);
		}else if (e instanceof  InternalAuthenticationServiceException){
			return Result.error(CodeMsg.MOBILE_NOT_EXIST);
		}else {
			return Result.error(CodeMsg.SERVER_ERROR);
		}
	}
	
}
