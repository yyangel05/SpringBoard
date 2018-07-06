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
	
		//다운로드때문에 넣어봄
	private WebApplicationContext context2 = null;
	
	//게시물 페이징 변수
	private int currentPage =1;
	private int showArticleLimit  = 10;
	private int showPageLimit = 10;
	private int startArticleNum = 0;
	private int endArticleNum = 0;
	private int totalNum = 0;
	
	//파일 업로드 경로
	//private String uploadPath = "C:\\Java\\App\\SpringBoard\\WebContent\\files\\";  //학원pc경로
	private String uploadPath = "C:\\Users\\NTGAYEON\\git\\SpringBoard\\WebContent\\files\\"; //노트북 경로
	
	
	@RequestMapping("/list.yy")
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response) {
		
		String type= null;
		String keyword = null;
		
		//request parameter로부터의 변수 세팅하기
		if(request.getParameter("page") == null || request.getParameter("page").trim().isEmpty() || request.getParameter("page").equals("0")) {
			currentPage = 1;
		}
		else {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		//검색을 위한 조건문
		   if(request.getParameter("type") != null) {
			   type= request.getParameter("type").trim();
		   }
		//검색을 위한 조건문
		    if(request.getParameter("keyword") != null) {
		    	keyword = request.getParameter("keyword").trim();
		    }
		
		//페이지 수 계산?
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
		
		//페이징
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
						pageHtml.append("<span><a href=\"list.yy?page=" + (currentPage-1) + "\"><이전></a>&nbsp;&nbsp;");
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
						pageHtml.append(".&nbsp;&nbsp;<a href=\"list.yy?page=" + (currentPage+1) + "\"><다음></a></span>");
					}
				//
				// else: when search
				} else {			
					if(currentPage == 1){
						pageHtml.append("<span>");
					} else {
						pageHtml.append("<span><a href=\"list.yy?page=" + (currentPage-1) + "&type=" + type + "&keyword=" + keyword + "\"><이전></a>&nbsp;&nbsp;");
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
						pageHtml.append(".&nbsp;&nbsp;<a href=\"list.yy?page=" + (currentPage+1) + "&type=" + type + "&keyword=" + keyword + "\"><다음></a></span>");
					}
				}
				//		
				return pageHtml;
	}
	
	//게시글 상세보기를 한다
	@RequestMapping("/view.yy")
	public ModelAndView boardView(HttpServletRequest request) {
		
		int idx = Integer.parseInt(request.getParameter("idx"));//get으로 날아온 idx를 인트로 변환하여 idx변수에 설정
		
		BoardModel board = boardService.getOneArticle(idx); //번호에 해당하는 게시글 내용을 가져오는 쿼리문을 실행하게 한다
		boardService.updateHitcount(board.getHitcount()+1, idx); //조회수 증가시키기
		
		List<BoardCommentModel> commentList = boardService.getCommentList(idx); //게시글에 해당하는 덧글목록을 가져오는 쿼리를 실행하게 한다
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board); //jsp에서 board라는 이름의 자바빈 객체 내용을 갖다쓸 수 있다.
		mav.addObject("commentList",commentList); //jsp에서 commentList라는 이름의 자바빈 객체 내용을 갖다쓸 수 있다.
		
		mav.setViewName("/board/view");
		return mav;
	}
	
	//게시글 작성하기 폼을 띄운다.
	@RequestMapping("/write.yy")
	public String boardWrite(@ModelAttribute("BoardModel") BoardModel boardModel) {
		return "/board/write";
	}
	
	//게시글 작성하기를 수행한다. (작성완료를 누르고, post방식으로 전송된 값을 db에 넣는다)
	@RequestMapping(value="/write.yy", method=RequestMethod.POST)
	public String boardWriteProc(@ModelAttribute("BoardModel") BoardModel boardModel, MultipartHttpServletRequest request) {

		//MultipartHttpServletRequest는 업로드를 위함.(4장 10섹션의 3개중 2번째방법)
		
		//업로드 파일 얻기
		MultipartFile file = request.getFile("file"); //파일객체 생성
		String fileName = file.getOriginalFilename(); //파일이름을 얻어서 fileName변수 생성
		File uploadFile = new File(uploadPath + fileName); //파일 업로드 경로 설정한  uploadFile 파일객체 생성
		
		//같은 이름의 파일이 이미 존재하면
		if(uploadFile.exists()) {
			fileName = new Date().getTime() + fileName; //오늘날짜+파일이름 으로 파일이름을 바꾼다
			uploadFile = new File(uploadPath + fileName); //업로드 경로와 파일이름을 합친다
		}
		
		//업로드 경로에 파일 저장하기
		try {
			file.transferTo(uploadFile); 
		} catch(Exception e) {
			
		}
		boardModel.setFileName(fileName); //자바빈의 파일이름을 설정
		
		//줄바꿈을 해주는 코드
		String content = boardModel.getContent().replaceAll("\r\n", "<br/>"); //<br/>을 줄바꿈으로 바꿔서  content변수에 담는다
		boardModel.setContent(content); //자바빈에 내용변수 세팅
		
		boardService.writeArticle(boardModel); //보드서비스의 writeArticle이라는 이름의 insert문을 실행하게 한다.
		
		return "redirect:list.yy"; //작성완료되면 목록화면으로 리다이렉트
	}
	
	//게시글 수정하기 폼을 띄운다.
	@RequestMapping("/modify.yy")
	public ModelAndView boardModify(HttpServletRequest request, HttpSession session) {
		String userId = (String) session.getAttribute("userId");//세션으로부터 userid를 얻어 userId변수에 넣는다.
		int idx = Integer.parseInt(request.getParameter("idx")); //get방식으로 타고 날라온 idx를 idx변수에 넣는다
		
		BoardModel board = boardService.getOneArticle(idx); //게시글에 해당하는 내용을 db에서 가져와서 board라는 자바빈객체에 담음
		String content = board.getContent().replaceAll("<br/>","\r\n");
		board.setContent(content);
		
		ModelAndView mav = new ModelAndView();
		
		//글 작성자 아이디와 내 아이디가 다르면 
		if(!userId.equals(board.getWriterId())) {
			mav.addObject("errCode","1"); // errCodeCheck함수의 ("잘못된 접근 경로입니다");라는 에러 코드를 나타냄.
			mav.addObject("idx",idx); //view를 눌렀을때의 전송된 글번호를 idx에 설정
			mav.setViewName("redirect:view.yy"); //view로 리다이렉트
		}
		//글 작성자 아이디와 내 아이디가 같으면
		else {
			mav.addObject("board",board); //게시글 내용을 담은 board객체를 mav에 담음.
			mav.setViewName("/board/modify"); //보드 수정하기 폼으로 포워딩
		} 
		return mav;
	}
	
	//게시글 수정하기를 수행함. 
	@RequestMapping(value= "/modify.yy", method=RequestMethod.POST)
	public ModelAndView boardModifyProc(@ModelAttribute("BoardModel") BoardModel boardModel, MultipartHttpServletRequest request) {
		String orgFileName = request.getParameter("orgFile");
		MultipartFile newFile = request.getFile("newFile");
		String newFileName = newFile.getOriginalFilename();
		
		boardModel.setFileName(orgFileName);
		
		//새로 업로드된 파일객체가 존재하고, 파일 이름이 존재하면
		if(newFile != null && !newFileName.equals("")) {
			//기존 퍄일 이름이 존재하면
			if(orgFileName != null || !orgFileName.equals("")) { 
				//기존의 파일을 지움
				File removeFile = new File(uploadPath + orgFileName);
				removeFile.delete();
			}
			
			//새로운 파일 이름을 넣은 파일객체 생성
			File newUploadFile = new File(uploadPath + newFileName);
			
			try {
				newFile.transferTo(newUploadFile);
			} catch(Exception e) {
				e.printStackTrace();
			}
			boardModel.setFileName(newFileName);
		}
		
		//줄바꿈을 수행하는 코드
		String content = boardModel.getContent().replaceAll("\r\n", "<br/>");
		boardModel.setContent(content);
		
		boardService.modifyArticle(boardModel); //게시글을 수정하는 쿼리문을 실행하게 한다.
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("idx", boardModel.getIdx());
		mav.setViewName("redirect:/board/view.yy"); //잘 수행되면 view로 리다이렉트
		return mav;
	}
	
	@RequestMapping("/delete.yy")
	public ModelAndView boardDelete(HttpServletRequest request, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardModel board = boardService.getOneArticle(idx); //해당 글번호의 게시글을 가져오는 쿼리를 실행해 자바빈에 담는다
		
		ModelAndView mav = new ModelAndView();
		
		//게시글 작성자와 내 아이디가 서로 다르면
		if(!userId.equals(board.getWriterId())) {
			mav.addObject("errCode", "1"); //errCodeCheck함수의 case 1:을 수행. ("잘못된 접근 경로입니다");
			mav.addObject("idx",idx); //파라미터로 전송된 idx를 mav에 설정
			mav.setViewName("redirect:view.yy"); //상세보기로 리다이렉트
		}
		else {
			
			List<BoardCommentModel> commentList = boardService.getCommentList(idx); //덧글의 리스트를 얻어 리스트객체에 넣는다
			
			if(commentList.size() > 0) { //리스트객체의 크기가 0보다 크면(덧글이 존재하면)
				mav.addObject("errCode","2"); //errCodeCheck함수의 case 2:을 수행. ("댓글이 있어 글을 삭제하실 수 없습니다");
				mav.addObject("idx",idx);  //파라미터로 전송된 idx를 mav에 설정
				mav.setViewName("redirect:view.yy"); //상세보기로 리다이렉트
			}
			else { //덧글이 없다면
				if(board.getFileName() != null) { //파일이름이 존재하면 삭제 수행
					File removeFile = new File(uploadPath + board.getFileName()); //파일객체를 생성한다.
					removeFile.delete(); //파일을 삭제한다
				}
				boardService.deleteArticle(idx); //게시글을 삭제하는 쿼리를 수행
				
				mav.setViewName("redirect:list.yy"); //목록으로 리다이렉트
			}
		}
		return mav;
	}
	
	//게시글 추천하기 
	@RequestMapping("/recommend.yy")
	public ModelAndView updateRecommendcount(HttpServletRequest request, HttpSession session) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		String userId = (String) session.getAttribute("userId");
		BoardModel board = boardService.getOneArticle(idx);
		
		ModelAndView mav = new ModelAndView();
		
		//내 아이디와 게시글작성자가 같으면 
		if(userId.equals(board.getWriterId())) {
			mav.addObject("errCode","1"); ////errCodeCheck함수의 case 1:을 수행. ("잘못된 경로란다");
		}
		else {
			boardService.updateRecommendCount(board.getRecommendcount() + 1, idx); //추천수 증가시키는 쿼리를 실행하게 함
		}
		
		mav.addObject("idx", idx); 
		mav.setViewName("redirect:/board/view.yy"); //상세보기로 리다이렉트
		
		return mav;
	}
	
	@RequestMapping("/commentWrite.yy")
	public ModelAndView commentWriteProc(@ModelAttribute("CommentModel") BoardCommentModel commentModel) {
		
		String content = commentModel.getContent().replaceAll("\r\n", "<br/>");
		commentModel.setContent(content);
		
		boardService.writeComment(commentModel); //덧글을 작성하는 쿼리를 수행하게함
		ModelAndView mav = new ModelAndView();
		mav.addObject("idx", commentModel.getLinkedArticleNum());
		mav.setViewName("redirect:view.yy"); //상세보기로 리다이렉트
		
		return mav;
	}
	
	@RequestMapping("/commentDelete.yy")
	public ModelAndView commentDelete(HttpServletRequest request, HttpSession session) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int linkedArticleNum = Integer.parseInt(request.getParameter("linkedArticleNum"));
		
		String userId = (String) session.getAttribute("userId");
		BoardCommentModel comment = boardService.getOneComment(idx);
		
		ModelAndView mav = new ModelAndView();
		
		//덧글 작성자와 내 아이디가 다르면
		if(!userId.equals(comment.getWriterId())) {
			mav.addObject("errCode","1"); //errCodeCheck함수의 case 1:을 수행. ("잘못된 경로란다");
		}
		else {
			boardService.deleteComment(idx); //덧글을 삭제하는 쿼리를 수행하게함
		}
		
		mav.addObject("idx",linkedArticleNum);
		mav.setViewName("redirect:view.yy"); //상세보기로 리다이렉트
		
		return mav;
	}
	
	
}









