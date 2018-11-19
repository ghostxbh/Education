package com.senoops.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.senoops.model.Section;
import com.senoops.service.VedioService;

@Controller
@RequestMapping("/learn")
public class VedioController {
	
	@Autowired
	private VedioService vedioService;
	
	@RequestMapping("/getSections")
	private ModelAndView getSections(Integer cid){
		ModelAndView view = new ModelAndView();
		try {
			List<Section> list = vedioService.getSections(cid);
			view.setViewName("vedioplay");
			view.addObject("list",list);
			view.addObject("first",list.get(0).getList().get(0));
		} catch (Exception e) {
			view.setViewName("erroe/error");
		}
		return view;
	}
	
	
	
}
