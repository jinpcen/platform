var i = 10;

function checkUserName() {
	var len = $("#username").val().length;
	if(len <= 0) {
		alert("用户名不能为空")
	} else {
		return true;
	}
}

function checkAddress() {
	var len = $("#address").val().length;
	if(len <= 0) {
		alert("地址不能为空");
	} else {
		return true;
	}
}

function checkemail() {
	var text = $("#email").val();
	var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(!reg.test(text)) {
		alert("请输出正确的邮箱地址")
	} else {
		return true;
	}
}

function checkSex() {
    var sex=$("input[type='radio']:checked").val()
	if(sex!="男"||sex!="女")
		alert("请选择性别")
}

function checketel() {
	var text = $("#telephone").val();
	var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
	if(!reg.test(text)) {
		alert("请输入正确的手机号码");
	} else {
		return true;
	}
}

function checkPassWord() {
	var len = $("#password").val().length;
	if(len < 6) {
		alert("密码长度不能小于6");
	} else {
		return true;
	}
}

function recheckPassWord() {
	var text = $("#password").val();
	var text1 = $("#password2").val();
	if(text != text1) {
		alert("密码不一致")
	} else {
		return true;
	}
}

