package com.example.URLShortener.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.URLShortener.entity.URLS;
import com.example.URLShortener.entity.User;
import com.example.URLShortener.remote.URLSRemote;
import com.example.URLShortener.remote.UserRemote;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ClientController 
{
	@Autowired
	private UserRemote userRemote;
	
	@Autowired
	private URLSRemote urlsRemote;
	
	@GetMapping("/home")
	public ModelAndView home()
	{
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	
	@GetMapping("/register")
	public ModelAndView navigateToSignup()
	{
		ModelAndView modelAndView = new ModelAndView("signup");
		modelAndView.addObject("message", message);
		setValue(null);
		return modelAndView;
	}
	
	@PostMapping("/signup")
	public String signUpUser(HttpServletRequest request)
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setRole("ROLE_USER");
		String m = userRemote.addUser(user);
		return "redirect:loginwithmessage?message="+m;
	}
	
	@GetMapping("/")
	public String homeURL(HttpSession session)
	{
		session.removeAttribute("email");
		session.removeAttribute("name");
		return "redirect:home";
	}
	
	private String message;
	
	private void setValue(String m)
	{
		message = m;
	}
	
	@GetMapping("/loginwithmessage")
	public String redirectLogin(@RequestParam("message") String m)
	{
		setValue(m);
		return "redirect:login";
	}
	
	@GetMapping("login")
	public ModelAndView navigateToLogin()
	{
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("message", message);
		setValue(null);
		return modelAndView;
	}
	
	@PostMapping("/authenticate")
	public String checkUser(HttpServletRequest request)
	{
		String email = request.getParameter("username");
		String password = request.getParameter("password");
		if(userRemote.isUserAvailable(email))
		{
			User user = userRemote.getByUsername(email);
			if(user.getPassword().equals(password))
			{
				return "redirect:/user/homeredirect?email="+user.getEmail()+"&name="+user.getName();
			}
			else
			{
				setValue("Invalid Password");
				return "redirect:login";
			}
		}
		setValue("User donot exist!");
		return "redirect:register";
	}
	
	@GetMapping("/user/homeredirect")
	public ModelAndView userHomeNav(@RequestParam Map<String, String> map, HttpSession session)
	{
		if(map.size() != 2)
		{
			return new ModelAndView("redirect:login");
		}
		String email = map.get("email");
		String name = map.get("name");
		session.setAttribute("email", email);
		session.setAttribute("name", name);
		ModelAndView modelAndView = new ModelAndView("redirect:/user/home");
		return modelAndView;
	}
	
	@GetMapping("/user/home")
	public ModelAndView userHome(HttpSession session)
	{
		String email = (String) session.getAttribute("email");
		String name = (String) session.getAttribute("name");
		if(email == null || name == null)
		{
			return new ModelAndView(
					"redirect:/login");
		}
		ModelAndView modelAndView = new ModelAndView("userhome");
		
		List<URLS> urls = urlsRemote.getShortenedURLsByUser(email);
		Collections.reverse(urls);
		modelAndView.addObject("urllist", urls);
		modelAndView.addObject("msg", message);
		setValue(null);
		return modelAndView;
	}
	
	@PostMapping("/shortenurl")
	public ModelAndView shorturl(HttpServletRequest request, HttpSession session)
	{
		String url = request.getParameter("url");
		URLS urls = new URLS();
		urls.setUrl(url);
		urls.setShortenedBy((String)session.getAttribute("email"));
		urls.setAccessedCount(0);
		urlsRemote.addNewURL(urls);
		setValue("URL Shortened Successfully, please check the table for details");
		ModelAndView modelAndView = new ModelAndView("redirect:/user/home");
		return modelAndView;
	}
	
	@GetMapping("/url/{value}")
	public ModelAndView openURL(@PathVariable("value") String identifier) throws IOException
	{
		ModelAndView modelAndView = new ModelAndView("openURL");
		if(urlsRemote.checkIfAvailable(identifier))
		{
			URLS urls = urlsRemote.getByURL(identifier);
			String s = urls.getUrl();
			URL url = new URL(s);
			urls.setAccessedCount(urls.getAccessedCount() + 1);
			urlsRemote.updateNewURL(urls);
			modelAndView.addObject("url", url);
		}
		else
		{
			modelAndView.addObject("url", "val");
		}
		return modelAndView;
	}
	
		
}
