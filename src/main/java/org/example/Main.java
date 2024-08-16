package org.example;

import org.example.dao.UserTicketDAO;
import org.example.models.Ticket;
import org.example.models.TicketType;
import org.example.models.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        UserTicketDAO dao = new UserTicketDAO();

        try {
            User user = new User();
            user.setName("John Cena");
            dao.saveUser(user);

            Ticket ticket = new Ticket();
            ticket.setUserId(1);
            ticket.setTicketType(TicketType.DAY);
            dao.saveTicket(ticket);

            User fetchedUser = dao.getUserById(1);
            System.out.println("Fetched User: " + fetchedUser.getName());

            List<Ticket> tickets = dao.getTicketsByUserId(1);
            System.out.println("Number of Tickets: " + tickets.size());

            dao.updateTicketType(1, TicketType.WEEK);

            dao.deleteUserById(1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}