package com.onito.tambola.tambolaticketgenerator.entity;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TambolaTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private int[][] numbers = new int[3][9];
//    
//    public TambolaTicket(int[][] numbers) {
//        this.numbers = numbers;
//    }
//    
//    public TambolaTicket(int[] ticketData) {
//        this.numbers = parseTicketData(ticketData);
//    }
//    
//    private int[][] parseTicketData(int[] ticketData) {
//        int[][] parsedNumbers = new int[3][9];
//        int index = 0;
//        for (int row = 0; row < 3; row++) {
//            for (int col = 0; col < 9; col++) {
//                parsedNumbers[row][col] = ticketData[index++];
//            }
//        }
//        return parsedNumbers;
//    }
    
    @Column(columnDefinition = "jsonb")
    private String ticketData;
    
    public TambolaTicket(String ticketData) {
        this.ticketData = ticketData;
    }

}
