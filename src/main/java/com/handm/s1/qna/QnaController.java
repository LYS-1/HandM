package com.handm.s1.qna;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.handm.s1.util.Pager;


@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@RequestMapping (value="list", method = RequestMethod.GET)
	public ModelAndView getQnaList(Pager pager) throws Exception {
	ModelAndView mv = new ModelAndView();
	List<QnaDTO> ar = qnaService.getQnaList(pager);
	
	mv.setViewName("qna/list");
	mv.addObject("list",ar);
	mv.addObject("pager", pager);
	return mv;
	}
	
	@RequestMapping (value="detail" , method = RequestMethod.GET)
	public ModelAndView getQnaDetail(QnaDTO qnaDTO) throws Exception {
	ModelAndView mv = new ModelAndView();
	qnaDTO = qnaService.getQnaDetail(qnaDTO);
	
	mv.setViewName("qna/detail");
	mv.addObject("dto",qnaDTO);
	
	return mv;
			
	}
	
	@RequestMapping(value="add" , method = RequestMethod.GET)
	public ModelAndView setQnaAdd(ModelAndView mv) throws Exception {
		mv.setViewName("qna/add");
		
		return mv;
	}
	
	@RequestMapping(value="add" , method = RequestMethod.POST)
	public ModelAndView setQnaAdd(QnaDTO qnaDTO, MultipartFile pic, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("Name : "+pic.getName());
		System.out.println("Ori Name : "+pic.getOriginalFilename());
		System.out.println("size : "+pic.getSize());
		System.out.println(session.getServletContext());
		
		
		int result = qnaService.setQnaAdd(qnaDTO, pic);
		
		mv.setViewName("redirect: ./list");
		
		return mv;
	}
	
	@RequestMapping(value ="delete", method = RequestMethod.GET)
	public ModelAndView setQnaDelete(QnaDTO qnaDTO) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setQnaDelete(qnaDTO);
		
		mv.setViewName("redirect: ./list");
		
		return mv;
	
	}
	//?????? ??? ??????
	@RequestMapping(value="update" , method = RequestMethod.GET)
	public ModelAndView setQnaUpdate(QnaDTO qnaDTO) throws Exception {
		ModelAndView mv = new ModelAndView();
		// ????????? ?????? ??? ????????? ?????? ????????? ????????? qna ???????????? ?????? ??????????????? ????????? ???????????? ????????? ??????????????? dto??? ?????????
		qnaDTO = qnaService.getQnaDetail(qnaDTO);
		
		mv.setViewName("qna/update");
		//jsp??? ????????? mv??? ????????? ?????? ???????????? addobject ????????? ?????????????????? ???????????? dto??? ???????????? ?????? dto??? 
		mv.addObject("dto",qnaDTO);
		return mv;
	}
	
	//?????? 
	@RequestMapping(value = "update",method = RequestMethod.POST)
	public ModelAndView setQnaUpdate(QnaDTO qnaDTO, ModelAndView mv) throws Exception {
		int result = qnaService.setQnaUpdate(qnaDTO);
		mv.setViewName("redirect: ./list");
		return mv;
	}
	
	
}
