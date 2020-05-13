package com.spring.board.common.util;

public class Util {
	public static int nvl(String text){
		return nvl(text,0);
	}
	
	//nvl () 메소드는 문자열을 숫자로 변환 메소드
	//@param (숫자로 변활할 문자열, 초기값을 사용할 값(대체 값)
	//참고 : 예외 처리는 체크예외와 비체크예외로 구분 체크예외는 파일입출력 .네트워크 입출력, 데이터베이스 입출력  나머지는 비체크 예외로 인식
	//@return int
	
	public static int nvl(String text, int def){
		System.out.println("Util.nvl()함수 시작 인트");
		int ret = def;
		try{
			ret= Integer.parseInt(text);
		}catch(Exception e){
			ret = def;
		}
		System.out.println("Util.nvl()함수 끝 인트");
		return ret;
	}//end of nvl
	
	
	public static String nvl(Object text,String def){
		System.out.println("Util.nvl()함수 시작 스트링");
		if(text==null ||"".equals(text.toString().trim())){
			System.out.println("Util.nvl()함수 끝 이프 트루");
			return def;
		}else{
			System.out.println("Util.nvl()함수 끝 이프 펄스");
			return text.toString();
		}
	}//end of nvl
	
}
