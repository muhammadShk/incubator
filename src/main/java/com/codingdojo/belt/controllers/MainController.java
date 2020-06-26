package com.codingdojo.belt.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.belt.models.Idea;
import com.codingdojo.belt.models.User;
import com.codingdojo.belt.services.ControllerService;


@Controller
public class MainController {
		private final ControllerService controllerService;
		
		public MainController (ControllerService controllerService){
	        this.controllerService = controllerService;
		}
		
/////////////////////////////////////////////////////////////////////////////////////
//		                 Idea CRUD ROUTES
////////////////////////////////////////////////////////////////////////////////////
//	
		
/////////////////////////////////////////////////////////////////////////////////////////	    
//				View all Ideas & add/remove likes
/////////////////////////////////////////////////////////////////////////////////////////	
		
		
	@RequestMapping("/ideas")
    public String index(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		if(session.getAttribute("userId")!=null) {
			Long userId = (Long) session.getAttribute("userId");
			System.out.println(userId);
	        model.addAttribute("allIdeas", controllerService.allIdeas());
	        model.addAttribute("user", controllerService.findUserById(userId));
			return "/ideas/indexView.jsp";
		}else {
			redirectAttributes.addFlashAttribute("accessDenied", "Unauthorized Access");
			return"redirect:/";
		}
    }
    
	@RequestMapping(value="/ideas", method=RequestMethod.POST)
    public String createIdea(@RequestParam("like") boolean like, @RequestParam("idea") Long ideaId, HttpSession session, RedirectAttributes redirectAttributes) {
		if(session.getAttribute("userId")!=null) {
			Long userId = (Long) session.getAttribute("userId");
			if(like) {
    			controllerService.addLikeToIdea(ideaId, userId);
    			return"redirect:/ideas";
    			
    		}else if(!like) {
    			controllerService.removeLikeFromIdea(ideaId, userId);
    			return"redirect:/ideas";
    		}else {
    			return"redirect:/ideas";
    		}
		} else {
			redirectAttributes.addFlashAttribute("accessDenied", "Unauthorized Access");
			return"redirect:/";
			
		}
    }
	
/////////////////////////////////////////////////////////////////////////////////////////	    
//					Create an Idea 
/////////////////////////////////////////////////////////////////////////////////////////	
	

	@RequestMapping("/ideas/new")
    public String showCreatePage(@Valid @ModelAttribute("idea") Idea idea, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		if(session.getAttribute("userId")!=null) {
			Long userId = (Long) session.getAttribute("userId");
			User user = controllerService.findUserById(userId);
			model.addAttribute("user", user);
			
	        return "/ideas/createView.jsp";
		}else {
			redirectAttributes.addFlashAttribute("accessDenied", "Unauthorized Access");
			return"redirect:/";
		}
    }
    
    @RequestMapping(value="/ideas/new", method=RequestMethod.POST)
    public String createIdea(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
    	if(session.getAttribute("userId")!=null) {
	    	if (result.hasErrors()) {
	            return "/ideas/createView.jsp";
	        } else {
	        	Long userId = (Long) session.getAttribute("userId");
				controllerService.createIdea(idea, userId);
	            return "redirect:/ideas";
	        }
    	}else {
			redirectAttributes.addFlashAttribute("accessDenied", "Unauthorized Access");
			return"redirect:/";
		}
    }
	
/////////////////////////////////////////////////////////////////////////////////////////	    
//					Update an Idea 
/////////////////////////////////////////////////////////////////////////////////////////
    
	@RequestMapping("/ideas/{id}/edit")
    public String showUpdatePage(@PathVariable("id") Long ideaId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		Idea idea= controllerService.findIdeaById(ideaId);
		Long userId = (Long) session.getAttribute("userId");
		User user = controllerService.findUserById(userId);
		if(session.getAttribute("userId")!=null && idea.getCreator().equals(user) ) {
	    	model.addAttribute("idea", idea);
	        return "/ideas/editPage.jsp";
			
		}else {
			redirectAttributes.addFlashAttribute("accessDenied", "Unauthorized Access");
			return"redirect:/";
		}
    }
    
    @RequestMapping(value="/ideas/{id}/edit", method=RequestMethod.POST)
    public String updateIdea(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
    	if(session.getAttribute("userId")!=null) {
	    	if (result.hasErrors()) {
	            return "/ideas/editPage.jsp";
	        } else {
	        	Long userId = (Long) session.getAttribute("userId");
				controllerService.updateIdea(idea, userId);
	            return "redirect:/ideas";
	        }
    	}else {
			redirectAttributes.addFlashAttribute("accessDenied", "Unauthorized Access");
			return"redirect:/";
		}
    }
    
//////////////////////////////////////////////////////////////////////////////////////////    
//		Show Idea Detail
//////////////////////////////////////////////////////////////////////////////////////////
    
    @RequestMapping("/ideas/{id}")
    public String showIdea(@PathVariable("id") Long ideaId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    	if(session.getAttribute("userId")!=null) {
	    	Idea idea = controllerService.findIdeaById(ideaId);
			List<User> likedBy = idea.getLikedBy();
	    	model.addAttribute("likedBy", likedBy);
	    	
	    	model.addAttribute("idea", idea);
	    	
	    	System.out.println(model);
	        return "/ideas/detailView.jsp";
    	}else {
			redirectAttributes.addFlashAttribute("accessDenied", "Unauthorized Access");
			return"redirect:/";
		}
    }
    
//    @RequestMapping(value="/evens/{id}", method=RequestMethod.POST)
//    public String addMessages(@PathVariable("id") Long eventId, @Valid @ModelAttribute("messageObject") Message message, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
//    	if(session.getAttribute("userId")!=null) {
//	    	if (result.hasErrors()) {
//	            return "/events/showEvent.jsp";
//	        } else {
//	    		Long userId = (Long) session.getAttribute("userId");
//	    		controllerService.createMessage(message, eventId, userId);
//	            return "redirect:/events/{id}";
//	        }
//		}else {
//			redirectAttributes.addFlashAttribute("accessDenied", "Unauthorized Access");
//			return"redirect:/";
//		}
//    	
//    }
//////////////////////////////////////////////////////////////////////////////////////////
//					delete an Idea
//////////////////////////////////////////////////////////////////////////////////////////
    
    @RequestMapping(value="/ideas/{id}/delete", method=RequestMethod.POST)
	public String delete(@PathVariable("id") Long ideaId, HttpSession session, RedirectAttributes redirectAttributes) {
	  	if(session.getAttribute("userId")!=null) {
		    	controllerService.deleteIdea(ideaId);
		    	return"redirect:/ideas";
			}else {
				redirectAttributes.addFlashAttribute("accessDenied", "Unauthorized Access");
				return"redirect:/";
			}
    	}
}

