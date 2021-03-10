package org.SpringMvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.SpringMvc.dao.StudentDao;
import org.SpringMvc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
@SessionAttributes("studentObject")
@Controller
public class CreateController {

	@Autowired
	private StudentDao studentDao;

//	@RequestMapping(value = "/create", method = RequestMethod.POST)
//	public ModelAndView createStudent(@RequestParam("name") String name, @RequestParam("email") String email,
//			@RequestParam("course") String course, ModelAndView mv) {
//
//		Student student = new Student();
//		student.setName(name);
//		student.setEmail(email);
//		student.setCourse(course);
//
//		int counter = studentDao.create(student);
//
//		if (counter > 0) {
//			mv.addObject("msg", "Student"+counter+" registration successful.");
//		}
//		else if(counter==-1) {
//			mv.addObject("msg","Name is already existed in DataBase");
//		}
//		
//		else {
//			mv.addObject("msg", "Error- check the console log.");
//		}
//
//		mv.setViewName("create");
//
//		return mv;
//	}
	
	@RequestMapping(value = "/showWelcome", method = RequestMethod.POST)
	public ModelAndView showWelcome(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("course") String course,HttpServletRequest httpServlet) {
		
//		String name1=httpServlet.getParameter("name");
//		HttpSession session=httpServlet.getSession(); 
//		session.setAttribute("name", name1);
		
		ModelAndView modelAndView=new ModelAndView();
		Student stud = new Student();
		stud.setName(name);
		stud.setEmail(email);
		stud.setCourse(course);
		int counter=studentDao.create(stud);
		stud.setId(counter);
		if(counter>0) {
			modelAndView.addObject("studentObject", stud);
			modelAndView.setViewName("showWelcome");
		}
		else if(counter==-1) {
			modelAndView.addObject("msg","Record is already existed in the DataBase");
			modelAndView.setViewName("create");
		}
		else {
			modelAndView.addObject("msg","Error check the console log");
			modelAndView.setViewName("create");
			
		}
		
		return modelAndView;
 
		
 
	}
}
