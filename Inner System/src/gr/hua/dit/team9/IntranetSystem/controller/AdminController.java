package gr.hua.dit.team9.IntranetSystem.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import gr.hua.dit.team9.IntranetSystem.entity.Authority;
import gr.hua.dit.team9.IntranetSystem.entity.Student;
import gr.hua.dit.team9.IntranetSystem.entity.User;

@Controller
public class AdminController {
	
	String userToModify;
	
	//returns add-new-user.jsp
	@RequestMapping("/admin/user/add")
	public ModelAndView createUserForm() {
        return new ModelAndView("add-new-user", "user", new User());
    }
	
	/*Create User and Authority Objects based on user inputs
	  and save them in DB.*/
	@RequestMapping(value="/admin/user/created_user", method = RequestMethod.POST)
	public String createUser(@ModelAttribute("user")User user,
							@RequestParam("authority") String authority, Model model) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();
		
		SessionFactory factory2 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Authority.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		Session session2 = factory2.getCurrentSession();

		try {
			session.beginTransaction();
			
			//check if username already exists
			if (session.get(User.class, user.getUsername()) != null) {
				factory.close();
				factory2.close();
				
				return "user-already-exists";
			} else {
				//Encrypt password before storing
				String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
				user.setPassword(password);
					
				//save user
				session.save(user);
				session.getTransaction().commit();
				
				Authority auth = new Authority(user.getUsername(), authority);
				
				session2.beginTransaction();
				//save authority
				session2.save(auth);
				session2.getTransaction().commit();
				
				System.out.println("Created and Inserted User, Authority: Done!");
			}
		
		} finally {
			factory.close();
			factory2.close();
		}
		return "redirect:add";
	}

	// returns delete-user.jsp
	@RequestMapping(value = "/admin/user/delete", method = RequestMethod.GET)
	public String deleteUserForm(Model model) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			// list of all Users retrieved from DB
			List<User> users = session.createQuery("from User").getResultList();

			// add the list of Users in the model for further use in the JSP page
			model.addAttribute("users", users);
		} finally {
			factory.close();
		}
		return "delete-user";
	}
	
	// Deletes the chosen User from the dropdown list.
	@RequestMapping("/admin/user/deleted_user")
	public String deleteUser(@RequestParam("username") String username) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();
		SessionFactory factory2 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Authority.class)
				.buildSessionFactory();
		SessionFactory factory3 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session3 = factory3.getCurrentSession();
		Session session2 = factory2.getCurrentSession();
		Session session = factory.getCurrentSession();

		try {			
			session2.beginTransaction();
			
			Authority auth = session2.get(Authority.class, username);
			if (auth.getAuthority().equals("ROLE_STUDENT")) {
				session3.beginTransaction();
				session3.createQuery("delete from Student where email= '" + username + "'").executeUpdate();
				session3.getTransaction().commit();
			}
			
			//first delete the Authority
			int identifier = session2.createQuery("delete from Authority where username= '" + username + "'").executeUpdate();
			session2.getTransaction().commit();
			
			//check if Authority was deleted successfully
			if (identifier > 0) {
				session.beginTransaction();
				
				//then delete the User
				/* if User is deleted first then foreign key
				 * (User.username -> Authority.username) constraint throws error */
				session.createQuery("delete from User where username= '" + username + "'").executeUpdate();
				session.getTransaction().commit();
			}
			
		} finally {
			factory3.close();
			factory2.close();
			factory.close();
		}
		return "redirect:delete";
	}

	//returns select-user.jsp
	@RequestMapping(value = "/admin/user/select", method = RequestMethod.GET)
	public String selectUserToModify(Model model) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			// returns all users stored in DB
			List<User> users = session.createQuery("from User").getResultList();

			model.addAttribute("users", users);
		} finally {
			factory.close();
		}
		return "select-user";
	}
	
	//returns modify-user.jsp
	@RequestMapping(value = "/admin/user/modify", method = RequestMethod.GET)
	public String modifyUserForm(@RequestParam("username") String username, Model model) {	
		//save username for use in next method
		userToModify = username;
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			// returns the selected user from DB based on the username.
			User user = session.get(User.class, username);
			model.addAttribute("user", user);
			
		} finally {
			factory.close();
		}
		return "modify-user";
	}

	@RequestMapping(value = "/admin/user/modified_user", method = RequestMethod.POST)
	public String modifiedUser(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("authority") String authority,
			@RequestParam("enabled") String enabled) {
		
		SessionFactory userFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();
		SessionFactory userFactory2 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();
		
		SessionFactory authFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Authority.class)
				.buildSessionFactory();
		SessionFactory authFactory2 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Authority.class)
				.buildSessionFactory();
		
		Session userSession = userFactory.getCurrentSession();
		Session userSession2 = userFactory2.getCurrentSession();
		
		Session authSession = authFactory.getCurrentSession();
		Session authSession2 = authFactory2.getCurrentSession();
		
		try {
			//Delete current Authority
			authSession.beginTransaction();
			Authority authToDelete = authSession.get(Authority.class, userToModify);
			authSession.delete(authToDelete);	
			authSession.getTransaction().commit();

			//Delete current User
			userSession.beginTransaction();
			User userToDelete = userSession.get(User.class, userToModify);
			userSession.delete(userToDelete);
			userSession.getTransaction().commit();
			
			//Create new user with updated data
			userSession2.beginTransaction();
			String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			
			boolean enabledUser;
			
			if(enabled.equals("false")){
			    enabledUser = false;
			}else{
				enabledUser = true;
			}
			
			User user = new User(username, encryptedPassword, enabledUser);
			userSession2.save(user);
			userSession2.getTransaction().commit();
			
			//Create new authority with updated data 
			authSession2.beginTransaction();
			Authority updatedAuth = new Authority(username, authority);
			authSession2.save(updatedAuth);
			authSession2.getTransaction().commit();
	
		} finally {
			userFactory.close();
			userFactory2.close();
			authFactory.close();
			authFactory2.close();
		}
		return "redirect:select";
	}
}

