package com.grl.springbootticketMgmt.service;

import java.util.List;

import com.grl.springbootticketMgmt.entity.Ticket;

public interface TicketService {

	public List<Ticket> findAll();

	public Ticket findById(int theId);

	public void save(Ticket ticket);

	public void deleteById(int theId);

	public List<Ticket> search(String searchString);

}
