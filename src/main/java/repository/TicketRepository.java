package repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
  Optional<Ticket> findFirstByQueueCodeAndStatusOrderByCreatedAtAsc(
      String queueCode, Ticket.Status status);
  Optional<Ticket> findFirstByQueueCodeAndStatusOrderByCalledAtDesc(
      String queueCode, Ticket.Status status);
}