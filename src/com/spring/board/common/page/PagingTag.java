package com.spring.board.common.page;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class PagingTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	
	/*
	 * @param page ���� ������ ��ȣ
	 * @param total ��ü ��ȸ�� row ��
	 * @param list_size �������� �����ִ� ���ڵ� ��
	 * @param page_size ������ �׺�����Ϳ� ǥ�õǴ� ���� �ӹ�ư�� ����
	 * */
	
	private int page= 1;
	private int total = 1;
	private int list_size = 10;
	private int page_size = 10;
	
	@Override
	public int doStartTag() throws JspException {
		try{
			pageContext.getOut().println(getPaging());
		}catch(Exception e){
			System.out.println("���� >> : "+ e.getMessage());
		}
		return super.doStartTag();
	}//end of doStartTag
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getList_size() {
		return list_size;
	}
	public void setList_size(int list_size) {
		this.list_size = list_size;
	}
	public int getPage_size() {
		return page_size;
	}
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getPaging(){
		System.out.println("PagingTag.getPaging()�Լ� ����");
		
		String ret = "";
		
		if(page < 1)
			page = 1;
		if(total < 1)
			return "";
		
		//�������� 1�������� page_size�� 2�̸�
		//currentFirst�� 1
		int currentFirst = ((page-1)/page_size) * page_size + 1;
		System.out.println("currentFirst > : "+ currentFirst);
		//currentFirst�� 2
		int currentlast = ((page-1)/page_size) * page_size + page_size;
		System.out.println("currentlast > : "+ currentlast);
		//nextFirst�� 3
		int nextFirst = (((page-1)/page_size)+1) * page_size + 1;
		System.out.println("nextFirst > : "+ nextFirst);
		//prevLast�� 0
		int prevLast = (((page-1)/page_size) -1) * page_size + 1;
		System.out.println("prevLast > : "+ prevLast);
		int lastPage = 1;
		lastPage = total/list_size;
		
		//lastPage(�� ������ ��)�� total�� 11�̰� list_size�� 10�̸� 1�� ������
		//�׷��� �� ������ ���� ������ �������� ������ ������ ���ڵ带 �����
		//�������� �ʿ��ϴٴ� �ǹ�
		
		if(total%list_size != 0) lastPage = lastPage + 1;
		
		//currentlast�� lastPage(�� ��������)���� ũ�� ������ �������� ����
		currentlast = (currentlast > lastPage)?lastPage:currentlast;
		
		ret += "<div class='paginate'>";
		
		if(page > 1){
			ret += "<a href=\"javascript:goPage('1')\"><span><img src='../images/common/btn_paginate_first.gif' alt='ó��' /></span></a>";
		}else{
			ret += "<span><img src='../images/common/btn_paginate_first.gif' alt='ó��'/></span>";//"ó��><
		}
		
		
		if(prevLast > 0){                                       
			ret += "<a href=\"javascript:goPage('"+prevLast+"');\"><span><img src='../images/common/btn_paginate_prev.gif' alt='����'/></span></a>";
		}else{
			ret += "<span><img src='../images/common/btn_paginate_prev.gif' alt='����' /></span>";
		}
		
		for(int j=currentFirst; j<currentFirst+page_size && j<=lastPage; j++){
			if(j <= currentlast){
				if(j == page){
					ret += " <a href='#' class='on textAn'>"+j+"</a> ";
				}else{
					ret += " <a href=\"javascript:goPage('"+j+"');\"class='textAn'>"+j+"</a> ";
				}
			}
		}
		
		if (nextFirst <= lastPage){
			ret += " <a href=\"javascript:goPage('"+nextFirst+"')\"><span><img src='../images/common/btn_paginate_next.gif' alt='����'/></sapn></a>";
		}else{
			ret += "<span><img src='../images/common/btn_paginate_next.gif' alt='����'/></span>";
		}
		
		
		if(page < lastPage){
			ret += "<a href=\"javascript:goPage('"+lastPage+"');\"><span><img src='../images/common/btn_paginate_last.gif' alt='������'/></span></a>";
		}else{
			ret += "<span><img src='../images/common/btn_paginate_last.gif' alt='������'/></span>";
		}
		
		ret += "</div>";
		
		System.out.println("PagingTag.getPaging()�Լ� ��");
		return ret;
		
	}
	
	

}//end of class
 