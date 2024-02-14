package com.onito.tambola.tambolaticketgenerator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onito.tambola.tambolaticketgenerator.entity.TambolaTicket;
import com.onito.tambola.tambolaticketgenerator.repository.TambolaTicketRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@Service
public class TambolaTicketServiceImpl implements TambolaTicketService {
	
	private static final Logger logger = LoggerFactory.getLogger(TambolaTicketServiceImpl.class);
	
	@Autowired
    private final TambolaTicketRepository ticketRepository;
    
    public TambolaTicketServiceImpl(TambolaTicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Page<TambolaTicket> getAllTickets(Pageable pageable) {
        try {
            return ticketRepository.findAll(pageable);
        } catch (DataAccessException e) {
            logger.error("Error retrieving tickets from the database: {}", e.getMessage(), e);
            throw new RuntimeException("Error retrieving tickets from the database", e);
        }
    }

    @Override
    public List<TambolaTicket> generateTickets(int numberOfSets) {
        List<TambolaTicket> tickets = new ArrayList<>();
        for (int i = 0; i < numberOfSets; i++) {
            List<List<Integer>> ticketNumbers = generateTicketNumbers();
            TambolaTicket ticket = new TambolaTicket();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String ticketData = objectMapper.writeValueAsString(ticketNumbers);
                ticket.setTicketData(ticketData);
                tickets.add(ticket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ticketRepository.saveAll(tickets);
    }

    private List<List<Integer>> generateTicketNumbers() {
        List<List<Integer>> ticketNumbers = new ArrayList<>();
        Random random = new Random();

        for (int row = 0; row < 3; row++) {
            List<Integer> rowNumbers = new ArrayList<>();
            int nonZeroCount = 0;

            // Initialize the row with zeros
            for (int col = 0; col < 9; col++) {
                rowNumbers.add(0);
            }

            // Distribute non-zero numbers based on their ranges
            while (nonZeroCount < 5) {
                int num = random.nextInt(90) + 1;
                System.out.println("num = "+num);
                int colIndex = (num - 1) / 10; // Calculate the column index based on the number's range
                System.out.println("index = "+colIndex);
                if (rowNumbers.get(colIndex) == 0) {
                    rowNumbers.set(colIndex, num-1);
                    nonZeroCount++;
                }
            }

            // Add the row to the ticket numbers
            ticketNumbers.add(rowNumbers);
        }

        return ticketNumbers;
    }
    
    @Override
    public TambolaTicket getTicketById(Long id) {
        try {
            return ticketRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
        } catch (DataAccessException e) {
            logger.error("Error retrieving ticket with id {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Error retrieving ticket with id " + id, e);
        }
    }
}
