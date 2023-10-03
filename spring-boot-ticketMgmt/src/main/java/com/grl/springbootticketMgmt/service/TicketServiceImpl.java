package com.grl.springbootticketMgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grl.springbootticketMgmt.dao.TicketRepository;
import com.grl.springbootticketMgmt.entity.Ticket;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Override
	public List<Ticket> findAll() {
		List<Ticket> tickets = ticketRepository.findAll();
		return tickets;
	}

	@Override
	public Ticket findById(int theId) {
		Optional<Ticket> result = ticketRepository.findById(theId);
		Ticket ticket = null;
		if (result.isPresent()) {
			ticket = result.get();
		} else {
			throw new RuntimeException("Did not find Ticket ID - " + theId);
		}
		return ticket;
	}

	@Override
	public void save(Ticket ticket) {
		ticketRepository.save(ticket);
	}

	@Override
	public void deleteById(int theId) {
		ticketRepository.deleteById(theId);
	}

	@Override
	public List<Ticket> search(String searchString) {

		List<Ticket> tickets = new ArrayList<Ticket>();

		if (!searchString.isEmpty()) {
			tickets = ticketRepository.searchTicket(searchString);

		} else {
			tickets = ticketRepository.findAll();
		}
		return tickets;
	}

}
