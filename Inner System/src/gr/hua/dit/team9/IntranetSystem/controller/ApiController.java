package gr.hua.dit.team9.IntranetSystem.controller;
 
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import gr.hua.dit.team9.IntranetSystem.entity.Application;
import gr.hua.dit.team9.IntranetSystem.entity.Authority;
import gr.hua.dit.team9.IntranetSystem.entity.Check;
import gr.hua.dit.team9.IntranetSystem.entity.Student;
import gr.hua.dit.team9.IntranetSystem.entity.User;

 
@RestController
@RequestMapping("api")
public class ApiController {
	
	String loggedInAmka;
	
	/*Student register method
	 * 
	 * Get each param sent from external system's html page and create a Student object.
	 * Password param is first encrypted before added in student's attributes.
	 * 
	 * Also create and save User and Authority objects with students data (email, password).
	 * 
	 * Create a DB connection and save student in 'student' table.
	 */
	@PostMapping(value="student/register",produces = "application/json;charset=UTF-8;")
	public String createStudent(@RequestBody String body){
		JSONObject response = new JSONObject(body);
		
		String fullname = response.getString("fullname");
		String password = response.getString("password");
		String id = response.getString("id");
		String email = response.getString("email");
		String amka = response.getString("amka");
		int semester = response.getInt("semester");
		String currDep = response.getString("currDep");

		//Encrypt password before storing
		String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		
		Student student = new Student(fullname, id, email, encryptedPassword, semester, amka, currDep);
		User user = new User(email, encryptedPassword, true);
		Authority auth = new Authority(email, "ROLE_STUDENT");
		
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		SessionFactory factoryUser = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();
		SessionFactory factoryAuth = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Authority.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		Session sessionUser = factoryUser.getCurrentSession();
		Session sessionAuth = factoryAuth.getCurrentSession();

		try {
			session.beginTransaction();
			String identifier = (String) session.save(student);
			session.getTransaction().commit();
			
			//if Student was added successfully then add User and Authority
			if (identifier != null) {
				sessionUser.beginTransaction();
				sessionUser.save(user);
				sessionUser.getTransaction().commit();
				
				sessionAuth.beginTransaction();
				sessionAuth.save(auth);
				sessionAuth.getTransaction().commit();
				
				response.put("message","REGISTRATION_COMPLETED");
				System.out.println("Created and Inserted Student, User and Authority: Done!");
			}
		} finally {
			factory.close();
			factoryUser.close();
			factoryAuth.close();
		}
		return response.toString();
	}

	
	/*Student login method
	 * 
	 * Get username and password sent from external system's html page and search in 'user' table for the student.
	 * 
	 * If user doesn't exist, then return.
	 *  
	 * Otherwise compare given password with stored one and if they match 
	 * approve login, else deny it.
	*/
	@PostMapping(value="student/login", produces = "application/json;charset=UTF-8;")
	public String login(@RequestBody String body) {
		
		JSONObject response = new JSONObject(body);
		
		String username = response.getString("username");
		String password = response.getString("password");
		
		SessionFactory factory2 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session2 = factory2.getCurrentSession();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			session2.beginTransaction();
			
			User user = session2.get(User.class, username);
			
			if (user != null) {
				if (user.isEnabled()) {
					//get student with email same as username provided
					List students = session.createQuery("from Student s where s.email= '"+username+"'").getResultList();
					
					//get first element of List (first and only student)
					Student student = (Student) students.get(0);
					
					//check if password is correct
					if (BCrypt.checkpw(password, student.getPassword())) {
						//when user is authenticated
						response.put("message", "correct");
			
						loggedInAmka = student.getAmka();
						
						//put student's amka in JSON response
						response.put("loggedInAmka", loggedInAmka);
					} else {
						//when passwords don't match
						response.put("message", "false");
					}
				} else {
					//when user isn't enabled
					response.put("message", "false");
				}
			} else {
				//when user doesn't exist
				response.put("message", "false");
			}
		} finally {
			factory.close();
			factory2.close();
		}
		return response.toString();
	}
	
	
	/*Fill application method
	 * 
	 * Get each param sent from external system's html page and create an Application object.
	 * Create a DB connection and save application in 'application' table.
	 */
	@PostMapping(value = "/student/application/fill", produces = "application/json;charset=UTF-8;")
	public String createApplication(@RequestBody String body) {
		JSONObject response = new JSONObject(body);
		
		String amka = response.getString("amka");
		boolean hasBrotherSameCity = response.getBoolean("hasBrotherSameCity");
		int noOfUnderageBrothers = response.getInt("noOfUnderageBrothers");
		String cityOfOrigin = response.getString("cityOfOrigin");
		double familyIncome = response.getDouble("familyIncome");
		String choice1 = response.getString("choice1");
		String choice2 = response.getString("choice2");
		String input1 = response.getString("input1");
		String input2 = response.getString("input2");
		String input3 = response.getString("input3");
		String input4 = response.getString("input4");
		
		byte[] file4 = null;
		
		//check if input4 exists, because it's optional
		if (!input4.equals("")) {
			input4 = input4.substring(input4.indexOf(','));
			file4 = DatatypeConverter.parseBase64Binary(input4);
		}
		
		//get base64 encoding only without 'data:*/*;base64,' preffix
		input1 = input1.substring(input1.indexOf(','));
		input2 = input2.substring(input2.indexOf(','));
		input3 = input3.substring(input3.indexOf(','));
		
		//convert base64 to byte arrays
		byte[] file1 = DatatypeConverter.parseBase64Binary(input1);
		byte[] file2 = DatatypeConverter.parseBase64Binary(input2);
		byte[] file3 = DatatypeConverter.parseBase64Binary(input3);
		
		Application application = new Application(amka, hasBrotherSameCity, noOfUnderageBrothers, cityOfOrigin, familyIncome, choice1, choice2, -1, file1, file2, file3, file4);
		
		int points = application.calculatePoints(hasBrotherSameCity, noOfUnderageBrothers, cityOfOrigin, familyIncome);
		application.setPoints(points);
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Application.class)
				.buildSessionFactory();
		SessionFactory factory2 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Check.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		Session session2 = factory2.getCurrentSession();
		
		try {			
			session.beginTransaction();
			session2.beginTransaction();
			
			//check if student has already applied by searching his amka in application and checked tables
			if (session.get(Application.class, application.getAmka()) != null || session2.get(Check.class, application.getAmka()) != null) {
				factory.close();
				factory2.close();
				
				response.put("message","APPLICATION_EXISTS");
				return response.toString();
			} else {
				//save application object in DB
				session.save(application);
				session.getTransaction().commit();
				
				System.out.println("Created and Inserted Application: Done!");
				response.put("message","APPLICATION_SAVED");
			}
		} finally {
			factory.close();
			factory2.close();
		}       
		return response.toString();
    }

	
	/*Check student's application progress method.
	 * 
	 * Get checkID (equal to amka) param from path page and search 'checked' table for Check object.
	 * If application is checked then return the Check object, otherwise return null or empty Check object.
	 */
	@PostMapping(value = "/student/application/check_progress", produces = "application/json;charset=UTF-8;")
	public String checkApplicationProgress(@RequestBody String body) {
		JSONObject response = new JSONObject(body);
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Check.class)
				.buildSessionFactory();
		SessionFactory factory2 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Application.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		Session session2 = factory2.getCurrentSession();
		
		try {
			session.beginTransaction();
			session2.beginTransaction();
			
			//retrieve Check object from DB
			Check checked_application = session.get(Check.class, loggedInAmka);
			Application application = session2.get(Application.class, loggedInAmka);
			
			//if Application isn't checked
			if (checked_application == null) {
				
				//if Application doesn't exist at all
				if (application == null) {
					response.put("message", "APPLICATION_NULL");
				} else {
					response.put("message", "APPLICATION_UNCHECKED");
				}
			} else {
				response.put("message", "APPLICATION_NOT_NULL");
				
				response.put("date", checked_application.getDateOfCheck());
				
				if (checked_application.isApproved()) {
					response.put("status", "Εγκρίθηκε");
					response.put("department", checked_application.getAcceptedDep());
					response.put("position", checked_application.getPosition());
				} else {
					response.put("status", "Απορρίφθηκε");
				}
			}
		} finally {
			factory.close();
			factory2.close();
		}
		return response.toString();
	}

}