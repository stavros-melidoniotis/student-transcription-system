//package gr.hua.dit.team9.IntranetSystem.controller;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import gr.hua.dit.team9.IntranetSystem.entity.Application;
//import gr.hua.dit.team9.IntranetSystem.entity.Check;
//import gr.hua.dit.team9.IntranetSystem.entity.Student;
//
//@Controller
//public class StudentController {
//	
//	/* Returns student-register.jsp.
//	 * "ModelAndView" creates the view (in our case the .jsp page) and sets the type of
//	 * object that it will create (a Student object).
//	 * ModelAndView Constructor parameters: 1)JSP page to be displayed
//	 * 										2)Object's name
//	 * 										3)Object
//	 * */
//	@RequestMapping("/student/register")
//	public ModelAndView createStudentForm() {
//        return new ModelAndView("student-register", "student", new Student());
//    }
//
//	/* "@ModelAttribute" gets the Student object that was created from the form.
//	 * Create a connection with the Database and save the new Student in "student" table. 
//	 */
//	@RequestMapping(value="/student/create", method = RequestMethod.POST)
//	public void createStudent(@ModelAttribute("student")Student student) {
//		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
//				.buildSessionFactory();
//
//		Session session = factory.getCurrentSession();
//
//		try {
//			session.beginTransaction();
//			session.save(student);
//			session.getTransaction().commit();
//			System.out.println("Created and Inserted Student: Done!");
//		} finally {
//			factory.close();
//		}
//	}
//	
//	//Returns application-fill.jsp
//	@RequestMapping("/student/application/fill")
//    public ModelAndView createApplicationForm() {
//        return new ModelAndView("application-fill", "application", new Application());
//    }
//	
//	@RequestMapping(value="/student/application/create", method = RequestMethod.POST)
//	public String createApplication(@ModelAttribute("application")Application application) {
//		
//		//Calculate application's points through calculatePoints() method.
//		int points = application.calculatePoints(application.isHasBrotherSameCity(), application.getNoOfUnderageBrothers(), application.getCityOfOrigin(), application.getFamilyIncome());
//		application.setPoints(points);
//	
//		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Application.class)
//				.buildSessionFactory();
//
//		Session session = factory.getCurrentSession();
//		
//		try {			
//			session.beginTransaction();
//			
//			//check if student has already applied
//			if (session.get(Application.class, application.getAmka()) != null) {
//				factory.close();
//				
//				return "application-already-exists";
//			} else {
//				//save application object in DB
//				session.save(application);
//				session.getTransaction().commit();
//			}
//			
//			System.out.println("Created and Inserted Application: Done!");
//		} finally {
//			factory.close();
//		}
//		return "redirect:/home_page";
//	}
//
//	//returns application-progress.jsp when student decides to check his/her application
//	@RequestMapping(value = "/student/application/check_progress", method = RequestMethod.GET)
//	public String checkApplicationProgress(Model model) {
//		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Check.class)
//				.buildSessionFactory();
//
//		Session session = factory.getCurrentSession();
//		
//		try {
//			session.beginTransaction();
//			//MAKE AMKA NOT HARDCODED
//			//retrieve Check object from DB
//			Check checked_application = session.get(Check.class, 500);
//			
//			//if no Check was found then checked_application is null
//			if (checked_application == null) {
//				model.addAttribute("check", null);
//			} else {
//				model.addAttribute("check", checked_application);
//			}
//		} finally {
//			factory.close();
//		}
//		return "application-progress";
//	}
//}
