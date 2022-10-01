package com.gl.StudentManagment.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.StudentManagment.Entity.Student;
import com.gl.StudentManagment.Service.StudentService;


@Controller
@RequestMapping("/student")

public class StudentController {

	@Autowired
	private StudentService studentService;

	// add mapping for "/List"

	@RequestMapping("/list")
	public String listBooks(Model theModel) {

		System.out.println("request recieved");
		// get books from DB
		List<Student> theStudent = studentService.findAll();

		// add to the spring model
		theModel.addAttribute("Students", theStudent);
		return "list-Students";

	}

	@RequestMapping("/showFormForAdd")
	public String showFormAdd(Model theModel) {

		Student theStudent = new Student();
		theModel.addAttribute("student", theStudent);
		return "Student-form";

	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {
		Student theStudent = studentService.findById(theId);

		theModel.addAttribute("Student", theStudent);
		return "Student-form";
	}

	@RequestMapping("/save")
	public String saveBook(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("course") String course,
			@RequestParam("country") String country) {
		System.out.println(id);
		Student theStudent;

		if (id != 0) {
			theStudent = studentService.findById(id);
			theStudent.setFirstName(firstName);
			theStudent.setLastName(lastName);
			theStudent.setCourse(course);
			theStudent.setCountry(country);
		} else
			theStudent = new Student(firstName, lastName, course, country);
		studentService.save(theStudent);

		return "redirect:/student/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {

		// delete the Book
		studentService.deleteById(theId);

		// redirect to /Books/list
		return "redirect:/student/list";
	}
}