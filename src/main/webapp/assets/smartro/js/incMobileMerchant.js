

/****************************************************************************************
 * objName		- select box Object
 * description	- select box 값 얻어오기
 ****************************************************************************************/
function selectedBoxValue(objName) {
	return objName[objName.selectedIndex].value;
}

/****************************************************************************************
 * 특수 문자 체크
 ****************************************************************************************/
function isSpecial(checkStr) {
	var checkOK = "~`':;{}[]<>,.!@#$%^&*()_+|\\/?";

	for (var i = 0;  i < checkStr.length;  i++)	{
		ch = checkStr.charAt(i);
		for (var j = 0;  j < checkOK.length;  j++) {
			if (ch == checkOK.charAt(j)) {return true; break;}
		}
	}
	return false;
}

/****************************************************************************************
 * Email Check
 ****************************************************************************************/
function EmailCheck(arg_v) {
	var	vValue = "";

	if(arg_v.indexOf("@") < 0) return false;

	for(var i = 0; i < arg_v.length; i++) {
		vValue = arg_v.charAt(i);

		if (AlphaCheck(vValue) == false  && NumberCheck(vValue) == false && EmailSpecialCheck(vValue) == false )
			return false;
	}
	return true;
}

/****************************************************************************************
 * 영문 판별
 ****************************************************************************************/
function AlphaCheck(arg_v) {
	var alphaStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	if ( alphaStr.indexOf(arg_v) < 0 )
		return false;
	else
		return true;
}

/****************************************************************************************
 * 숫자 판별
 ****************************************************************************************/
function NumberCheck(arg_v) {
	var numStr = "0123456789";

	if ( numStr.indexOf(arg_v) < 0 )
		return false;
	else
		return true;
}

/****************************************************************************************
 * Email 특수 문자 체크
 ****************************************************************************************/
function EmailSpecialCheck(arg_v) {
	var SpecialStr = "_-@.";

	if ( SpecialStr.indexOf(arg_v) < 0 )
		return false;
	else
		return true;
}
