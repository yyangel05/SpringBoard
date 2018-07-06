package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView { //다운로드를 담당하는 class
	
	public DownloadView() {
		setContentType("application/download; charset=utf-8"); //다운로드 창이 뜨게 한다
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
						HttpServletRequest request, HttpServletResponse response) throws Exception {
		File file = (File) model.get("downloadFile");
		
		response.setContentType(getContentType()); //contentType설정
		response.setContentLength((int) file.length()); //크기 설정
		
		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1; //인터넷 익스플로러인지 체크하는 변수
		
		String fileName = null;
		if(ie) { //인터넷 익스플로러이면
			fileName = URLEncoder.encode(file.getName(), "utf-8"); //인코딩 설정
		} 
		else { //인터넷 익스플로러가 아니면
			fileName = new String(file.getName().getBytes("utf-8"), "iso-8859-1"); //인코딩 설정
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" +fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out = response.getOutputStream(); //다운로드 할 outputstream객체생성
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(file); //설명.txt를 fis에 담는다?
			FileCopyUtils.copy(fis, out); //fis를 읽어서 out에 쓴다
		} finally {
			if(fis != null) 
				try {
					fis.close();
				} catch (IOException e) {
					// TODO: handle exception
				}
		}
		out.flush();
	}

}
