package com.onito.tambola.tambolaticketgenerator.service;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.onito.tambola.tambolaticketgenerator.entity.TambolaTicket;

public interface TambolaTicketService {
	public List<TambolaTicket> generateTickets(int numberOfSets);
	
	//public Map<String, List<List<Integer>>> generateTickets(int numberOfSets);
	
	public Page<TambolaTicket> getAllTickets(Pageable pageable);
    
    public TambolaTicket getTicketById(Long id);
}
