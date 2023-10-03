package com.grl.springbootticketMgmt.controller;

import java.time.LocalDate;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grl.springbootticketMgmt.entity.Ticket;
import com.grl.springbootticketMgmt.service.TicketService;

@Controller
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	TicketService ticketService;

	@GetMapping("/list")
	public String listEmployee(Model theModel) {

		// get employee from database
		List<Ticket> tickets = ticketService.findAll();

		// add to the spring model
		theModel.addAttribute("tickets", tickets);

		return "views/list-tickets";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Ticket ticket = new Ticket();

		theModel.addAttribute("ticket", ticket);

		return "views/ticket-form";
	}

//	@PostMapping("/showFormForUpdate")
//	public String showFormForUpdate(@RequestParam("ticketId") int theId, Model theModel) {
//
//		// get the employee from the service
//		Ticket ticket = ticketService.findById(theId);
//
//		// set employee as a model attribute to pre-populate the form
//		theModel.addAttribute("ticket", ticket);
//
//		// send over to our form
//		return "views/ticket-update-form";
//	}
	@GetMapping
	@RequestMapping(value = "/showFormForUpdate/{id}")
	public String updateForm(@PathVariable("id") int id, Model model) {
//		 get the employee from the service
		Ticket ticket = ticketService.findById(id);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("ticket", ticket);

		// send over to our form
		return "views/ticket-update-form";
	}
	
	

	@GetMapping("/showFormForView")
	public String showFormForView(@RequestParam("ticketId") int theId, Model theModel) {

		// get the employee from the service
		Ticket ticket = ticketService.findById(theId);

		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("ticket", ticket);

		// send over to our form
		return "views/ticket-view-form";
	}

	@PostMapping("/save")
	public String saveBook(@ModelAttribute("ticket") Ticket ticket) {

		if (StringUtils.isNoneEmpty(ticket.getTitle()) && StringUtils.isNoneEmpty(ticket.getContent())) {
			ticket.setCreationdate(LocalDate.now());

			// save the employee
			ticketService.save(ticket);

			// use a redirect to prevent duplicate submissions
			return "redirect:/ticket/list";
		}

		return "views/ticket-form";
	}

	@PostMapping("/delete")
	public String delete(@RequestParam("ticketId") int theId) {

		// delete the employee
		ticketService.deleteById(theId);

		// use a redirect to prevent duplicate submissions
		return "redirect:/ticket/list";

	}

	@GetMapping("/search")
	public String searchEmployee(Model theModel, @RequestParam("searchString") String searchString) {

		// get employee from database
		List<Ticket> tickets = ticketService.search(searchString);

		// add to the spring model
		theModel.addAttribute("tickets", tickets);

		return "views/list-tickets";
	}
}
