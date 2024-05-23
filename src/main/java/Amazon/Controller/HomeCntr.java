package Amazon.Controller;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Amazon.Config.SpringConfig;
import Amazon.Login.Login;
import Amazon.Product.Product;
import Amazon.User.User;
import Amazon.User.UserData; 

@Controller
public class HomeCntr {
	List<User> list = new LinkedList<>();
	Login lg = new Login();
	User us = new User();
	Product pd = new Product();
	UserData ud = new UserData();
	//@ResponseBody
	@RequestMapping("/")
	public String home()
	{
		System.out.println("WelcomeController.home()");
		return "/home";
	}
	
	@RequestMapping("/admin")
	public String adminDetails()
	{
		System.out.println("WelcomeController.adminDetails()");
		//pd.incert();
		return "/admin";
	}
	
	@RequestMapping("/login")
	public String login()
	{
		System.out.println("WelcomeController.login()");
		return "/login";
	}
	
	@RequestMapping("/loginverification")
	public String loginVerification(@RequestParam String username, @RequestParam String password)
	{
		System.out.println("WelcomeController.loginVerification()");
		if (lg.userVerification(username).equals(password)) {
            return "/dekhteHai"; 
        } else {
            return "/login"; 
        }
	}
	
	@RequestMapping("/adminlogin")
	public String adminLogin(@RequestParam String username, @RequestParam String password)
	{
		System.out.println("WelcomeController.loginVerification()");
		if (lg.adminVerification(username).equals(password)) {
            return "/dekhteHai"; 
        } else {
            return "/login"; 
        }
	}
	
	@RequestMapping("/list")
	public String listEmps(Model model) {
		System.out.println("WelcomeController.list()");
		List<User> list= ud.showUser();
		model.addAttribute("list",list);
		return "/list";

	}


	@RequestMapping("/registration")
	public String model(Model model) {
		System.out.println("WelcomeController.registration()");
		model.addAttribute("user", new User());
		return "/registration";
	}

	@RequestMapping("/registeruser")
	public String registered(@ModelAttribute User user, @RequestParam String name, String password) {
		lg.newUser(name, password);
		System.out.println("WelcomeController.registred()");
		ud.insert(user);
		//ud.update(user,4);
		return "/registered";
	}
	
	@RequestMapping("/searchProduct")
	public String searchProduct(@RequestParam String tag, Model model) {
		System.out.println("WelcomeController.searchProduct()");
		List<Product> list1= pd.showProduct(tag);
		model.addAttribute("list1",list1);
		return "/ProductList";
	}
	
//	@RequestMapping("/registeruser")
//	public String registered1(@ModelAttribute User user, @RequestParam String name, @RequestParam String password) {
//		lg.newUser(name, password);
//		System.out.println("WelcomeController.registred()");
//		ud.insert(user);
//		//ud.update(user,4);
//		return "/registered";
//	}
	
	@RequestMapping("/search")
	public String searchMethod() {
		
		return "/search";

	}
	
	@RequestMapping("/searchUser")
	public String searchUser(@RequestParam int id, Model model) {
//		Query query1 = session.createQuery("from User where name = :name");
//		System.out.println(((Object)name).getClass().getSimpleName());
//		query1.setParameter("name", name);
//		List user=query1.list();
//		model.addAttribute("user",user);
//		return "found";
		User user = ud.showUser(id);
		if(user.getName() != null) {
			model.addAttribute("user",user);
			return "/found";
		}
		return "/error";
		
//		for (User user1 : user) {
//			if (user1.getName().equals(name)) {
//				model.addAttribute("user",user1);
//				return "done";
//			}
//		}
//		model.addAttribute("name",name);
//		return "error";

	}
	
	SessionFactory ssr = SpringConfig.getSessionFactory();
	Session session = ssr.openSession();
	Transaction tx = session.beginTransaction();
	
	
}
