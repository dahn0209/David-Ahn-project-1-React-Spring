

// package com.example.project_1.repository;

// import com.example.project_1.entity.Ticket;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.util.List;

// @Repository
// public interface TicketRepository extends JpaRepository<Ticket, Integer> {

//     /**
//      * Find all tickets by a specific user ID.
//      * @param userId The user ID to search for.
//      * @return List of tickets for the given user ID.
//      */
//     List<Ticket> findByUserId(Integer userId);

//     /**
//      * Find all tickets with a specific status.
//      * @param status The status to search for.
//      * @return List of tickets with the given status.
//      */
//     List<Ticket> findByStatus(String status);

//     /**
//      * Find tickets submitted after a specific date.
//      * @param submissionDate The date to search from.
//      * @return List of tickets submitted after the given date.
//      */
//     List<Ticket> findBySubmissionDateAfter(java.time.LocalDateTime submissionDate);
// }


package com.example.project_1.repository;

import com.example.project_1.entity.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Logger logger = LoggerFactory.getLogger(TicketRepository.class);

    List<Ticket> findByUserId(Integer userId);

    List<Ticket> findByStatus(String status);

    List<Ticket> findBySubmissionDateAfter(LocalDateTime submissionDate);

    default List<Ticket> logAndFetchByStatus(String status) {
        logger.info("Fetching tickets with status: {}", status);
        List<Ticket> tickets = findByStatus(status);
        logger.debug("Retrieved tickets: {}", tickets);
        return tickets;
    }
}
