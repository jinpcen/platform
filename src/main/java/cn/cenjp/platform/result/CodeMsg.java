package cn.cenjp.platform.result;

/**
 * 错误代码自定义类
 */
public class CodeMsg {
	
	private int code;
	private String msg;
	
	//通用的错误码
	public static CodeMsg SPIKE_FAIL= new CodeMsg(-1, "秒杀失败");
	public static CodeMsg SPIKE_SUCCESS = new CodeMsg(1, "秒杀成功");
	public static CodeMsg SPIKE_WAIT = new CodeMsg(0, "秒杀进行中");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
	public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
	//登录模块 5002XX
	public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在或者已经失效");
	public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
	public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机号不能为空");
	public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号格式错误");
	public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "手机号未注册");
	public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");
	
	//商品模块 5003XX
	public static CodeMsg GOOD_NOT_EXIST = new CodeMsg(5003001, "不存在该商品信息");
	public static CodeMsg GOOD_NO_MORE = new CodeMsg(5003002, "不存在更多商品");
	//订单模块 5004XX

	//秒杀模块 5005XX
	public static CodeMsg SPIKE_OVER = new CodeMsg(5005001, "秒杀结束");
	public static CodeMsg REPEATE_SPIKE = new CodeMsg(5005002, "重复秒杀");

	//权限异常5006XX
	public static CodeMsg Access_Deny = new CodeMsg(5005001, "对不起你无权访问");

	private CodeMsg( ) {
	}
			
	private CodeMsg( int code,String msg ) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public CodeMsg fillArgs(Object... args) {
		int code = this.code;
		String message = String.format(this.msg, args);
		return new CodeMsg(code, message);
	}

	@Override
	public String toString() {
		return "CodeMsg [code=" + code + ", msg=" + msg + "]";
	}

}
