package board.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardCommentModel;
import board.model.BoardModel;
import board.service.BoardService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/board")
public class BoardController {
	//DI
	private ApplicationContext context = new ClassPathXmlApplicationContext("/config/applicationContext.xml");
	
	private BoardService boardService = (BoardService) context.getBean("boardService");
	
		//�ٿ�ε嶧���� �־
	private WebApplicationContext context2 = null;
	
	//�Խù� ����¡ ����
	private int currentPage =1;
	private int showArticleLimit  = 10;
	private int showPageLimit = 10;
	private int startArticleNum = 0;
	private int endArticleNum = 0;
	private int totalNum = 0;
	
	//���� ���ε� ���
	//private String uploadPath = "C:\\Java\\App\\SpringBoard\\WebContent\\files\\";  //�п�pc���
	private String uploadPath = "C:\\Users\\NTGAYEON\\git\\SpringBoard\\WebContent\\files\\"; //��Ʈ�� ���
	
	
	@RequestMapping("/list.yy")
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response) {
		
		String type= null;
		String keyword = null;
		
		//request parameter�κ����� ���� �����ϱ�
		if(request.getParameter("page") == null || request.getParameter("page").trim().isEmpty() || request.getParameter("page").equals("0")) {
			currentPage = 1;
		}
		else {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		//�˻��� ���� ���ǹ�
		   if(request.getParameter("type") != null) {
			   type= request.getParameter("type").trim();
		   }
		//�˻��� ���� ���ǹ�
		    if(request.getParameter("keyword") != null) {
		    	keyword = request.getParameter("keyword").trim();
		    }
		
		//������ �� ���?
		startArticleNum = (currentPage -1) * showArticleLimit;
		endArticleNum = startArticleNum + showArticleLimit -1;
		
		//
		List<BoardModel> boardList;
		if(type != null && keyword != null) {
			boardList = boardService.searchArticle(type, keyword, startArticleNum, endArticleNum);
			totalNum = boardService.getSearchTotalNum(type, keyword);
		}
		else {
			boardList = boardService.getBoardList(startArticleNum, endArticleNum);
			totalNum = boardService.getTotalNum();
		}
		
		//����¡
		StringBuffer pageHtml = getPageHtml(currentPage, totalNum, showArticleLimit, showPageLimit, type, keyword);
		
				
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList);
		mav.addObject("pageHtml", pageHtml);
		mav.setViewName("/board/list");
		
		return mav;
	}
	
	private StringBuffer getPageHtml(int currentPage, int totalNum, int showArticleLimit, int showPageLimit, String type, String keyword ) {
		
		StringBuffer pageHtml = new StringBuffer();
		int startPage = 0;
		int lastPage = 0;
		
		startPage = ((currentPage -1) / showPageLimit) * showPageLimit +1;
		lastPage = startPage + showPageLimit -1;
		
		if(lastPage > totalNum / showArticleLimit) {
			lastPage = (totalNum / showArticleLimit) +1;
		}
		
		
		// create page html code
				// if: when no search	
				if(type == null && keyword == null){			
					if(currentPage == 1){
						pageHtml.append("<span>");
					} else {
						pageHtml.append("<span><a href=\"list.yy?page=" + (currentPage-1) + "\"><����></a>&nbsp;&nbsp;");
					}
					
					for(int i = startPage ; i <= lastPage ; i++) {
						if(i == currentPage){
							pageHtml.append(".&nbsp;<strong>");
							pageHtml.append("<a href=\"list.yy?page=" +i + "\" class=\"page\">" + i + "</a>");
							pageHtml.append("&nbsp;</strong>");
						} else {
							pageHtml.append(".&nbsp;<a href=\"list.yy?page=" +i + "\" class=\"page\">" + i + "</a>&nbsp;");
						}
						
					}
					if(currentPage == lastPage){
						pageHtml.append(".</span>");
					} else {
						pageHtml.append(".&nbsp;&nbsp;<a href=\"list.yy?page=" + (currentPage+1) + "\"><����></a></span>");
					}
				//
				// else: when search
				} else {			
					if(currentPage == 1){
						pageHtml.append("<span>");
					} else {
						pageHtml.append("<span><a href=\"list.yy?page=" + (currentPage-1) + "&type=" + type + "&keyword=" + keyword + "\"><����></a>&nbsp;&nbsp;");
					}
					
					for(int i = startPage ; i <= lastPage ; i++) {
						if(i == currentPage){
							pageHtml.append(".&nbsp;<strong>");
							pageHtml.append("<a href=\"list.yy?page=" +i + "&type=" + type + "&keyword=" + keyword + "\" class=\"page\">" + i + "</a>&nbsp;");
							pageHtml.append("&nbsp;</strong>");
						} else {
							pageHtml.append(".&nbsp;<a href=\"list.yy?page=" +i + "&type=" + type + "&keyword=" + keyword + "\" class=\"page\">" + i + "</a>&nbsp;");
						}
						
					}
					if(currentPage == lastPage){
						pageHtml.append("</span>");
					} else {
						pageHtml.append(".&nbsp;&nbsp;<a href=\"list.yy?page=" + (currentPage+1) + "&type=" + type + "&keyword=" + keyword + "\"><����></a></span>");
					}
				}
				//		
				return pageHtml;
	}
	
	//�Խñ� �󼼺��⸦ �Ѵ�
	@RequestMapping("/view.yy")
	public ModelAndView boardView(HttpServletRequest request) {
		
		int idx = Integer.parseInt(request.getParameter("idx"));//get���� ���ƿ� idx�� ��Ʈ�� ��ȯ�Ͽ� idx������ ����
		
		BoardModel board = boardService.getOneArticle(idx); //��ȣ�� �ش��ϴ� �Խñ� ������ �������� �������� �����ϰ� �Ѵ�
		boardService.updateHitcount(board.getHitcount()+1, idx); //��ȸ�� ������Ű��
		
		List<BoardCommentModel> commentList = boardService.getCommentList(idx); //�Խñۿ� �ش��ϴ� ���۸���� �������� ������ �����ϰ� �Ѵ�
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board); //jsp���� board��� �̸��� �ڹٺ� ��ü ������ ���پ� �� �ִ�.
		mav.addObject("commentList",commentList); //jsp���� commentList��� �̸��� �ڹٺ� ��ü ������ ���پ� �� �ִ�.
		
		mav.setViewName("/board/view");
		return mav;
	}
	
	//�Խñ� �ۼ��ϱ� ���� ����.
	@RequestMapping("/write.yy")
	public String boardWrite(@ModelAttribute("BoardModel") BoardModel boardModel) {
		return "/board/write";
	}
	
	//�Խñ� �ۼ��ϱ⸦ �����Ѵ�. (�ۼ��ϷḦ ������, post������� ���۵� ���� db�� �ִ´�)
	@RequestMapping(value="/write.yy", method=RequestMethod.POST)
	public String boardWriteProc(@ModelAttribute("BoardModel") BoardModel boardModel, MultipartHttpServletRequest request) {

		//MultipartHttpServletRequest�� ���ε带 ����.(4�� 10������ 3���� 2��°���)
		
		//���ε� ���� ���
		MultipartFile file = request.getFile("file"); //���ϰ�ü ����
		String fileName = file.getOriginalFilename(); //�����̸��� �� fileName���� ����
		File uploadFile = new File(uploadPath + fileName); //���� ���ε� ��� ������  uploadFile ���ϰ�ü ����
		
		//���� �̸��� ������ �̹� �����ϸ�
		if(uploadFile.exists()) {
			fileName = new Date().getTime() + fileName; //���ó�¥+�����̸� ���� �����̸��� �ٲ۴�
			uploadFile = new File(uploadPath + fileName); //���ε� ��ο� �����̸��� ��ģ��
		}
		
		//���ε� ��ο� ���� �����ϱ�
		try {
			file.transferTo(uploadFile); 
		} catch(Exception e) {
			
		}
		boardModel.setFileName(fileName); //�ڹٺ��� �����̸��� ����
		
		//�ٹٲ��� ���ִ� �ڵ�
		String content = boardModel.getContent().replaceAll("\r\n", "<br/>"); //<br/>�� �ٹٲ����� �ٲ㼭  content������ ��´�
		boardModel.setContent(content); //�ڹٺ� ���뺯�� ����
		
		boardService.writeArticle(boardModel); //���弭���� writeArticle�̶�� �̸��� insert���� �����ϰ� �Ѵ�.
		
		return "redirect:list.yy"; //�ۼ��Ϸ�Ǹ� ���ȭ������ �����̷�Ʈ
	}
	
	//�Խñ� �����ϱ� ���� ����.
	@RequestMapping("/modify.yy")
	public ModelAndView boardModify(HttpServletRequest request, HttpSession session) {
		String userId = (String) session.getAttribute("userId");//�������κ��� userid�� ��� userId������ �ִ´�.
		int idx = Integer.parseInt(request.getParameter("idx")); //get������� Ÿ�� ����� idx�� idx������ �ִ´�
		
		BoardModel board = boardService.getOneArticle(idx); //�Խñۿ� �ش��ϴ� ������ db���� �����ͼ� board��� �ڹٺ�ü�� ����
		String content = board.getContent().replaceAll("<br/>","\r\n");
		board.setContent(content);
		
		ModelAndView mav = new ModelAndView();
		
		//�� �ۼ��� ���̵�� �� ���̵� �ٸ��� 
		if(!userId.equals(board.getWriterId())) {
			mav.addObject("errCode","1"); // errCodeCheck�Լ��� ("�߸��� ���� ����Դϴ�");��� ���� �ڵ带 ��Ÿ��.
			mav.addObject("idx",idx); //view�� ���������� ���۵� �۹�ȣ�� idx�� ����
			mav.setViewName("redirect:view.yy"); //view�� �����̷�Ʈ
		}
		//�� �ۼ��� ���̵�� �� ���̵� ������
		else {
			mav.addObject("board",board); //�Խñ� ������ ���� board��ü�� mav�� ����.
			mav.setViewName("/board/modify"); //���� �����ϱ� ������ ������
		} 
		return mav;
	}
	
	//�Խñ� �����ϱ⸦ ������. 
	@RequestMapping(value= "/modify.yy", method=RequestMethod.POST)
	public ModelAndView boardModifyProc(@ModelAttribute("BoardModel") BoardModel boardModel, MultipartHttpServletRequest request) {
		String orgFileName = request.getParameter("orgFile");
		MultipartFile newFile = request.getFile("newFile");
		String newFileName = newFile.getOriginalFilename();
		
		boardModel.setFileName(orgFileName);
		
		//���� ���ε�� ���ϰ�ü�� �����ϰ�, ���� �̸��� �����ϸ�
		if(newFile != null && !newFileName.equals("")) {
			//���� ���� �̸��� �����ϸ�
			if(orgFileName != null || !orgFileName.equals("")) { 
				//������ ������ ����
				File removeFile = new File(uploadPath + orgFileName);
				removeFile.delete();
			}
			
			//���ο� ���� �̸��� ���� ���ϰ�ü ����
			File newUploadFile = new File(uploadPath + newFileName);
			
			try {
				newFile.transferTo(newUploadFile);
			} catch(Exception e) {
				e.printStackTrace();
			}
			boardModel.setFileName(newFileName);
		}
		
		//�ٹٲ��� �����ϴ� �ڵ�
		String content = boardModel.getContent().replaceAll("\r\n", "<br/>");
		boardModel.setContent(content);
		
		boardService.modifyArticle(boardModel); //�Խñ��� �����ϴ� �������� �����ϰ� �Ѵ�.
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("idx", boardModel.getIdx());
		mav.setViewName("redirect:/board/view.yy"); //�� ����Ǹ� view�� �����̷�Ʈ
		return mav;
	}
	
	@RequestMapping("/delete.yy")
	public ModelAndView boardDelete(HttpServletRequest request, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardModel board = boardService.getOneArticle(idx); //�ش� �۹�ȣ�� �Խñ��� �������� ������ ������ �ڹٺ� ��´�
		
		ModelAndView mav = new ModelAndView();
		
		//�Խñ� �ۼ��ڿ� �� ���̵� ���� �ٸ���
		if(!userId.equals(board.getWriterId())) {
			mav.addObject("errCode", "1"); //errCodeCheck�Լ��� case 1:�� ����. ("�߸��� ���� ����Դϴ�");
			mav.addObject("idx",idx); //�Ķ���ͷ� ���۵� idx�� mav�� ����
			mav.setViewName("redirect:view.yy"); //�󼼺���� �����̷�Ʈ
		}
		else {
			
			List<BoardCommentModel> commentList = boardService.getCommentList(idx); //������ ����Ʈ�� ��� ����Ʈ��ü�� �ִ´�
			
			if(commentList.size() > 0) { //����Ʈ��ü�� ũ�Ⱑ 0���� ũ��(������ �����ϸ�)
				mav.addObject("errCode","2"); //errCodeCheck�Լ��� case 2:�� ����. ("����� �־� ���� �����Ͻ� �� �����ϴ�");
				mav.addObject("idx",idx);  //�Ķ���ͷ� ���۵� idx�� mav�� ����
				mav.setViewName("redirect:view.yy"); //�󼼺���� �����̷�Ʈ
			}
			else { //������ ���ٸ�
				if(board.getFileName() != null) { //�����̸��� �����ϸ� ���� ����
					File removeFile = new File(uploadPath + board.getFileName()); //���ϰ�ü�� �����Ѵ�.
					removeFile.delete(); //������ �����Ѵ�
				}
				boardService.deleteArticle(idx); //�Խñ��� �����ϴ� ������ ����
				
				mav.setViewName("redirect:list.yy"); //������� �����̷�Ʈ
			}
		}
		return mav;
	}
	
	//�Խñ� ��õ�ϱ� 
	@RequestMapping("/recommend.yy")
	public ModelAndView updateRecommendcount(HttpServletRequest request, HttpSession session) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		String userId = (String) session.getAttribute("userId");
		BoardModel board = boardService.getOneArticle(idx);
		
		ModelAndView mav = new ModelAndView();
		
		//�� ���̵�� �Խñ��ۼ��ڰ� ������ 
		if(userId.equals(board.getWriterId())) {
			mav.addObject("errCode","1"); ////errCodeCheck�Լ��� case 1:�� ����. ("�߸��� ��ζ���");
		}
		else {
			boardService.updateRecommendCount(board.getRecommendcount() + 1, idx); //��õ�� ������Ű�� ������ �����ϰ� ��
		}
		
		mav.addObject("idx", idx); 
		mav.setViewName("redirect:/board/view.yy"); //�󼼺���� �����̷�Ʈ
		
		return mav;
	}
	
	@RequestMapping("/commentWrite.yy")
	public ModelAndView commentWriteProc(@ModelAttribute("CommentModel") BoardCommentModel commentModel) {
		
		String content = commentModel.getContent().replaceAll("\r\n", "<br/>");
		commentModel.setContent(content);
		
		boardService.writeComment(commentModel); //������ �ۼ��ϴ� ������ �����ϰ���
		ModelAndView mav = new ModelAndView();
		mav.addObject("idx", commentModel.getLinkedArticleNum());
		mav.setViewName("redirect:view.yy"); //�󼼺���� �����̷�Ʈ
		
		return mav;
	}
	
	@RequestMapping("/commentDelete.yy")
	public ModelAndView commentDelete(HttpServletRequest request, HttpSession session) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int linkedArticleNum = Integer.parseInt(request.getParameter("linkedArticleNum"));
		
		String userId = (String) session.getAttribute("userId");
		BoardCommentModel comment = boardService.getOneComment(idx);
		
		ModelAndView mav = new ModelAndView();
		
		//���� �ۼ��ڿ� �� ���̵� �ٸ���
		if(!userId.equals(comment.getWriterId())) {
			mav.addObject("errCode","1"); //errCodeCheck�Լ��� case 1:�� ����. ("�߸��� ��ζ���");
		}
		else {
			boardService.deleteComment(idx); //������ �����ϴ� ������ �����ϰ���
		}
		
		mav.addObject("idx",linkedArticleNum);
		mav.setViewName("redirect:view.yy"); //�󼼺���� �����̷�Ʈ
		
		return mav;
	}
	
	
}









