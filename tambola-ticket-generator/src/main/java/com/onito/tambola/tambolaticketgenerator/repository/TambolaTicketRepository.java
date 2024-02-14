package com.onito.tambola.tambolaticketgenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onito.tambola.tambolaticketgenerator.entity.TambolaTicket;

public interface TambolaTicketRepository extends JpaRepository<TambolaTicket, Long> {
    // You can define custom methods here if needed
}
