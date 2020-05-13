package com.spring.board.vo;

import org.springframework.web.multipart.MultipartFile;

import com.spring.board.common.vo.CommonVO;

public class BoardVO extends CommonVO {
	
	private String h_Num; //글번호
	private String h_Name; //작성자
	private String h_Title; // 제목
	private String h_Content; // 내용
	private MultipartFile file;
	private String h_File; // 실제 서버에 저장한 파일명
	private String h_Pwd; // 비밀번호
	private String h_Insertdate; // 입력일
	private String h_Updatedate; // 수정일
	private String h_DeleteYN; // 삭제일
	
	
	public String getH_Num() {
		return h_Num;
	}
	public void setH_Num(String h_Num) {
		this.h_Num = h_Num;
	}
	public String getH_Name() {
		return h_Name;
	}
	public void setH_Name(String h_Name) {
		this.h_Name = h_Name;
	}
	public String getH_Title() {
		return h_Title;
	}
	public void setH_Title(String h_Title) {
		this.h_Title = h_Title;
	}
	public String getH_Content() {
		return h_Content;
	}
	public void setH_Content(String h_Content) {
		this.h_Content = h_Content;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getH_File() {
		return h_File;
	}
	public void setH_File(String h_File) {
		this.h_File = h_File;
	}
	public String getH_Pwd() {
		return h_Pwd;
	}
	public void setH_Pwd(String h_Pwd) {
		this.h_Pwd = h_Pwd;
	}
	public String getH_Insertdate() {
		return h_Insertdate;
	}
	public void setH_Insertdate(String h_Insertdate) {
		this.h_Insertdate = h_Insertdate;
	}
	public String getH_Updatedate() {
		return h_Updatedate;
	}
	public void setH_Updatedate(String h_Updatedate) {
		this.h_Updatedate = h_Updatedate;
	}
	public String getH_DeleteYN() {
		return h_DeleteYN;
	}
	public void setH_DeleteYN(String h_DeleteYN) {
		this.h_DeleteYN = h_DeleteYN;
	}
	@Override
	public String toString() {
		return "BoardVO [h_Num=" + h_Num + ", h_Name=" + h_Name + ", h_Title=" + h_Title + ", h_Content=" + h_Content
				+ ", file=" + file + ", h_File=" + h_File + ", h_Pwd=" + h_Pwd + ", h_Insertdate=" + h_Insertdate
				+ ", h_Updatedate=" + h_Updatedate + ", h_DeleteYN=" + h_DeleteYN + "]";
	}
	
	

}
