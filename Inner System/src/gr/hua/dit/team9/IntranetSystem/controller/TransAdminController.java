package gr.hua.dit.team9.IntranetSystem.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.util.Base64Decoder;

import gr.hua.dit.team9.IntranetSystem.entity.Application;
import gr.hua.dit.team9.IntranetSystem.entity.Check;

@Controller
public class TransAdminController {

	String amkaValue;
	Application application;
	
	// returns application-select.jsp in order to select an application to check
	@RequestMapping(value = "/transAdmin/application/select", method = RequestMethod.GET)
	public String selectApplication(Model model) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Application.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			// returns all applications stored in DB
			List<Application> applications = session.createQuery("from Application").getResultList();
			
			model.addAttribute("applications", applications);
		} finally {
			factory.close();
		}
		return "application-select";
	}
	
 	 /*
	 * Returns application-check.jsp. TransAdmin can select an application from the dropdown
	 * list and decide to check it.
	 */
	@RequestMapping(value = "/transAdmin/application/check", method = RequestMethod.POST)
	public ModelAndView checkApplicationForm(@RequestParam("amka") String amka) throws IOException {
		ModelAndView mav = null;
		
		amkaValue = amka;
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Application.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			//returns the selected application from DB based on the amka.
			application = session.get(Application.class, amka);
			
			mav = new ModelAndView("application-check", "application", application);
		} finally {
			factory.close();
		}
		return mav;
	}

	
	/* Creates a Check Object when the transAdmin has checked an application.
	 * Returns application-select.jsp so that the user will be able to select another
	 * application to check.
	 */
	@RequestMapping(value = "/transAdmin/application/checked_application", method = RequestMethod.POST)
	public String checkApplication(@RequestParam("check") String check,
			@RequestParam("acceptedDep") String acceptedDep,
			@RequestParam("position") int position) {
		Check checked_application = null;
		
		//create timestamp 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		switch (check) {
			case "approved":
				checked_application = new Check(amkaValue, dateFormat.format(date), true, acceptedDep, position);
				break;
			case "rejected":
				checked_application = new Check(amkaValue, dateFormat.format(date), false, "Κανένα", position);
				break;
		}

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Check.class)
				.buildSessionFactory();
		SessionFactory factory2 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Application.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		Session session2 = factory2.getCurrentSession();

		try {
			session.beginTransaction();
			// save check object in DB
			String identifier = (String) session.save(checked_application);
			session.getTransaction().commit();
			System.out.println("Created and Inserted Check: Done!");

			//if checked_application was saved successfully
			if (identifier != null) {
				session2.beginTransaction();
				//delete Application from DB
				session2.createQuery("delete from Application where studentAmka="+ amkaValue +"").executeUpdate();
				session2.getTransaction().commit();
				
				System.out.println("Deleted checked Application from DB: Done!");
			}
		} finally {
			factory.close();
			factory2.close();
		}
		return "redirect:select";
	}

	
	@RequestMapping(value = "transAdmin/application/download", method = RequestMethod.POST)
	public void downloadDocuments(@RequestParam("document") String document, HttpServletResponse res) throws IOException {
		byte[] doc;
		
		ServletOutputStream sos = res.getOutputStream();
		res.setContentType("file/txt;charset=UTF-8");
			
		switch(document) {
			case "bebaiwsh_spoudwn":
				doc = application.getBebaiwsh_spoudwn();
				sos.write(doc);
				sos.close();
				break;
			case "oikogeneiakh_katastash":
				doc = application.getOikogeneiakh_katastash();
				sos.write(doc);
				sos.close();
				break;
			case "ekkatharistiko":
				doc = application.getEkkatharistiko();
				sos.write(doc);
				sos.close();
				break;
			case "monimh_katoikia":
				doc = application.getMonimh_katoikia();
				sos.write(doc);
				sos.close();
				break;
		}
	}
	
}
