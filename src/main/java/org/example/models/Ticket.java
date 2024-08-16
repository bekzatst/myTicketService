package org.example.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Ticket {
    private int id;
    private int userId;
    private TicketType ticketType;
    private Timestamp creationDate = new Timestamp(System.currentTimeMillis());
}
