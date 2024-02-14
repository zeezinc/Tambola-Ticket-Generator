package com.onito.tambola.tambolaticketgenerator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.onito.tambola.tambolaticketgenerator.controller.TambolaTicketController;
import com.onito.tambola.tambolaticketgenerator.entity.TambolaTicket;
import com.onito.tambola.tambolaticketgenerator.service.TambolaTicketServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TambolaTicketControllerTest {

    @Mock
    private TambolaTicketServiceImpl ticketService;

    @InjectMocks
    private TambolaTicketController ticketController;

    @Test
    public void testGenerateTickets() {
        // Mocking the service layer response
        List<TambolaTicket> tickets = Arrays.asList(
                new TambolaTicket(),
                new TambolaTicket()
        );
        when(ticketService.generateTickets(2)).thenReturn(tickets);

        // Calling the controller method
        ResponseEntity<?> responseEntity = ticketController.generateTickets(2);

        // Verifying the response status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Extracting the response body
        List<TambolaTicket> responseBody = (List<TambolaTicket>) responseEntity.getBody();

        // Verifying the content of the response body
        assertEquals(tickets.size(), responseBody.size());
        assertEquals(tickets, responseBody);
    }

    @Test
    public void testGetAllTickets() {
        // Mocking the service layer response
        TambolaTicket ticket = new TambolaTicket();
        Page<TambolaTicket> ticketPage = new PageImpl<>(Collections.singletonList(ticket));

        // Mocking the service method call
        when(ticketService.getAllTickets(Pageable.unpaged())).thenReturn(ticketPage);

        // Calling the controller method
        ResponseEntity<?> responseEntity = ticketController.getAllTickets(Pageable.unpaged());

        // Verifying the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Extracting the body and verifying its content
        Page<TambolaTicket> responseBody = (Page<TambolaTicket>) responseEntity.getBody();
        assertEquals(ticketPage.getTotalElements(), responseBody.getTotalElements());
        assertEquals(ticketPage.getContent(), responseBody.getContent());
    }

    
    @Test
    public void testGetTicketById() {
        // Mocking the service layer response
        TambolaTicket ticket = new TambolaTicket();
        when(ticketService.getTicketById(1L)).thenReturn(ticket);

        // Calling the controller method
        ResponseEntity<?> responseEntity = ticketController.getTicketById(1L);

        // Verifying the response status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Extracting the response body
        TambolaTicket responseBody = (TambolaTicket) responseEntity.getBody();

        // Verifying the content of the response body
        assertEquals(ticket, responseBody);
    }
}
