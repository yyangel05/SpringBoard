package download.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import board.model.BoardModel;
import board.service.BoardService;



@Controller
public class DownloadController  implements ApplicationContextAware  {
	
	private WebApplicationContext context = null;
	
	private ApplicationContext context2 = new ClassPathXmlApplicationContext("/config/applicationContext.xml");
	private BoardService boardService = (BoardService) context2.getBean("boardService");
	
	
	@RequestMapping("/download.yy")
	public ModelAndView download(HttpServletRequest request) throws Exception {
		
		int idx = Integer.parseInt(request.getParameter("idx"));//get���� ���ƿ� idx�� ��Ʈ�� ��ȯ�Ͽ� idx������ ����
		BoardModel board = new BoardModel();
		board = boardService.getOneArticle(idx); //��ȣ�� �ش��ϴ� �Խñ� ������ �������� �������� �����ϰ� �Ѵ�
		
		String fileN = board.getFileName();
		String fileName = "/files/" + fileN;
		
		String path = context.getServletContext().getRealPath(fileName );
		File downloadFile = new File(path);
	
		return new ModelAndView("download", "downloadFile", downloadFile);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = (WebApplicationContext) applicationContext;
	}
	

}
