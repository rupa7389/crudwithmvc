package mvce3.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mvce3.dao.StudentDao;
import mvce3.dto.Student;

@Controller
public class StudentController {

	@Autowired
	private StudentDao dao;

	@RequestMapping("/register")
	public ModelAndView registerStudent() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("student", new Student());
		modelAndView.setViewName("register.jsp");
		return modelAndView;

	}

	@RequestMapping("/save")
	public ModelAndView saveStudent(@ModelAttribute Student student) {
		ModelAndView modelAndView = new ModelAndView();
		dao.saveStudent(student);
		modelAndView.addObject("list", dao.getAllStudents());
		modelAndView.setViewName("display.jsp");
		return modelAndView;
	}

	@RequestMapping("/delete")
	public ModelAndView deleteStudent(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		dao.deleteStudentById(id);
		modelAndView.addObject("list", dao.getAllStudents());
		modelAndView.setViewName("display.jsp");
		return modelAndView;
	}

	@RequestMapping("/update")
	public ModelAndView updateStudent(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
        Student dbStudent=dao.getStudentById(id);
        modelAndView.addObject("student", dbStudent);
        modelAndView.setViewName("edit.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/edit")
	public ModelAndView editStudent(@ModelAttribute Student student) {
		ModelAndView modelAndView=new ModelAndView();
		dao.updateStudent(student);
		modelAndView.addObject("list", dao.getAllStudents());
		modelAndView.setViewName("display.jsp");
		return modelAndView;
	}
}
