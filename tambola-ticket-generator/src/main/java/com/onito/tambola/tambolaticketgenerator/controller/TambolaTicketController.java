package com.onito.tambola.tambolaticketgenerator.controller;

import com.onito.tambola.tambolaticketgenerator.entity.TambolaTicket;
import com.onito.tambola.tambolaticketgenerator.service.TambolaTicketServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TambolaTicketController {
	
	private static final Logger logger = LoggerFactory.getLogger(TambolaTicketController.class);
	
	@Autowired
    private final TambolaTicketServiceImpl ticketService;

    public TambolaTicketController(TambolaTicketServiceImpl ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateTickets(@RequestParam("numberOfSets") int numberOfSets) {
        try {
            return ResponseEntity.ok(ticketService.generateTickets(numberOfSets*6));
        } catch (Exception e) {
            logger.error("Error generating tickets: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating tickets");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTickets(Pageable pageable) {
        try {
            Page<TambolaTicket> tickets = ticketService.getAllTickets(pageable);
            return ResponseEntity.ok(tickets);
        } catch (Exception e) {
            logger.error("Error retrieving tickets: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving tickets");
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable Long id) {
        try {
            TambolaTicket ticket = ticketService.getTicketById(id);
            return ResponseEntity.ok(ticket);
        } catch (Exception e) {
            logger.error("Error retrieving ticket with id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving ticket");
        }
    }
}
