package gr.hua.dit.team9.IntranetSystem.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import gr.hua.dit.team9.IntranetSystem.entity.Department;
import gr.hua.dit.team9.IntranetSystem.entity.HuaDepartment;
import gr.hua.dit.team9.IntranetSystem.entity.University;

@Controller
public class SecSupervisorController {

	/* Returns update-department-positions.jsp
	 * The page is first filled with the current positions of each HUA Department
	 */
	@RequestMapping("/secSuper/update_dep_pos")
	public String updatePositionsForm(Model model) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(HuaDepartment.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			
			//get all HUA Departments
			List<HuaDepartment> hua_departments = session.createQuery("from HuaDepartment").getResultList();

			//get current positions of each department
			model.addAttribute("informatics_pos", hua_departments.get(3).getNoOfPositions());
			model.addAttribute("dietology_pos", hua_departments.get(0).getNoOfPositions());
			model.addAttribute("geography_pos", hua_departments.get(1).getNoOfPositions());
			model.addAttribute("home_economics_pos", hua_departments.get(2).getNoOfPositions());
		} finally {
			factory.close();
		}
		return "update-department-positions";
	}

	//Gets the new positions entered by the secSupervisor and saves them in the Database.
	@RequestMapping(value = "/secSuper/new_positions", method = RequestMethod.POST)
	public String updatePositions(@RequestParam("informatics_pos") int informatics_pos,
			@RequestParam("geography_pos") int geography_pos, 
			@RequestParam("dietology_pos") int dietology_pos,
			@RequestParam("home_economics_pos") int home_economics_pos) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(HuaDepartment.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			//get each department based on their code
			System.out.println("retrieving HUA Departments");
			HuaDepartment dep_inf = session.get(HuaDepartment.class, 4);
			HuaDepartment dep_geo = session.get(HuaDepartment.class, 2);
			HuaDepartment dep_diet = session.get(HuaDepartment.class, 1);
			HuaDepartment dep_home_eco = session.get(HuaDepartment.class, 3);

			//update department positions
			System.out.println("updating number of positions");
			dep_inf.setNoOfPositions(informatics_pos);
			dep_geo.setNoOfPositions(geography_pos);
			dep_diet.setNoOfPositions(dietology_pos);
			dep_home_eco.setNoOfPositions(home_economics_pos);

			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		
		return "redirect:update_dep_pos";
	}

	//Returns add-new-department.jsp
	@RequestMapping(value = "/secSuper/add", method = RequestMethod.GET)
	public ModelAndView addNewDepForm() {
		ModelAndView mav = new ModelAndView("add-new-department", "department", new Department());
	
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(HuaDepartment.class)
				.buildSessionFactory();
		SessionFactory factory2 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(University.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		Session session2 = factory2.getCurrentSession();

		try {
			session.beginTransaction();
			//get HUA Departments from DB
			List<HuaDepartment> hua_departments = session.createQuery("from HuaDepartment").getResultList();
			session2.beginTransaction();
			List<University> unis = session2.createQuery("from University").getResultList();
			
			//add list of hua_departments and unis in the ModelAndView for use in the JSP page
			mav.addObject("hua_departments", hua_departments);
			mav.addObject("unis", unis);
		} finally {
			factory.close();
			factory2.close();
		}
		return mav;
	}

	//Gets Department Object created from the form and saves it in the Database.
	@RequestMapping(value = "/secSuper/add/new_department", method = RequestMethod.POST)
	public String addNewDep(@ModelAttribute("department") Department department,
			@RequestParam("matchingDep") int code,
			@RequestParam("belongingUni") int uniCode) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Department.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		//set department's matching HUA department
		switch(code) {
			case(1):
				department.setMatchingDep("Τμήμα Διαιτολογίας");
				break;
			case(2):
				department.setMatchingDep("Τμήμα Γεωγραφίας");
				break;
			case(3):
				department.setMatchingDep("Τμήμα Οικιακής Οικονομίας");
				break;
			case(4):
				department.setMatchingDep("Τμήμα Πληροφορικής κ Τηλεματικής");
				break;
		}
		
		SessionFactory factory2 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(University.class)
				.buildSessionFactory();

		Session session2 = factory2.getCurrentSession();
		
		try {
			session2.beginTransaction();
			University u = session2.get(University.class, uniCode);
			//set department's belonging uni based on uni's code
			department.setBelongingUni(u.getUniName());
		} finally {
			factory2.close();
		}
		
		try {
			session.beginTransaction();
			//save department object in DB
			session.save(department);
			session.getTransaction().commit();
			System.out.println("Created and Inserted Department: Done!");
		} finally {
			factory.close();
		}
		
		return "redirect:/secSuper/add";
	}

	// returns delete-department.jsp
	@RequestMapping(value = "/secSuper/delete", method = RequestMethod.GET)
	public String deleteDepForm(Model model) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Department.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			//list of all Departments retrieved from the DB
			List<Department> departments = session.createQuery("from Department").getResultList();
			
			//add the list of Departments in the model for further use in the JSP page
			model.addAttribute("departments", departments);
		} finally {
			factory.close();
		}
		return "delete-department";
	}

	//Deletes the chosen Department from the dropdown list.
	@RequestMapping(value = "/secSuper/delete/delete_department", method = RequestMethod.POST)
	public String deleteDep(@RequestParam("depCode") String depCode) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Department.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			session.createQuery("delete from Department where depCode= '" + depCode +"'").executeUpdate();
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return "redirect:/secSuper/delete";
	}

}
