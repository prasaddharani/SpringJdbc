package org.SpringMvc.controller;

import java.io.IOException;

import org.SpringMvc.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeleteController {

	@Autowired
	private StudentDao studentDao;

	@RequestMapping(value = "/delete/{studentId}")
	public ModelAndView deleteStudentById(ModelAndView mv, @PathVariable("studentId") int studentId)
			throws IOException {

		int counter = studentDao.delete(studentId);

		if (counter > 0) {
			mv.addObject("msg", "Student records deleted against student id: " + studentId);
		} else {
			mv.addObject("msg", "Error- check the console log.");
		}

		mv.setViewName("delete");

		return mv;
	}

}
