package com.spring.board.common.util;

public class Util {
	public static int nvl(String text){
		return nvl(text,0);
	}
	
	//nvl () �޼ҵ�� ���ڿ��� ���ڷ� ��ȯ �޼ҵ�
	//@param (���ڷ� ��Ȱ�� ���ڿ�, �ʱⰪ�� ����� ��(��ü ��)
	//���� : ���� ó���� üũ���ܿ� ��üũ���ܷ� ���� üũ���ܴ� ��������� .��Ʈ��ũ �����, �����ͺ��̽� �����  �������� ��üũ ���ܷ� �ν�
	//@return int
	
	public static int nvl(String text, int def){
		System.out.println("Util.nvl()�Լ� ���� ��Ʈ");
		int ret = def;
		try{
			ret= Integer.parseInt(text);
		}catch(Exception e){
			ret = def;
		}
		System.out.println("Util.nvl()�Լ� �� ��Ʈ");
		return ret;
	}//end of nvl
	
	
	public static String nvl(Object text,String def){
		System.out.println("Util.nvl()�Լ� ���� ��Ʈ��");
		if(text==null ||"".equals(text.toString().trim())){
			System.out.println("Util.nvl()�Լ� �� ���� Ʈ��");
			return def;
		}else{
			System.out.println("Util.nvl()�Լ� �� ���� �޽�");
			return text.toString();
		}
	}//end of nvl
	
}
