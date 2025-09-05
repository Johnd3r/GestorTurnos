package entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name="ticket")
public class Ticket {
  public enum Status { ESPERA, LLAMADO, ATENDIDO }

  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Column(name="queue_code", nullable=false)
  private String queueCode;

  @Column(name="number", nullable=false)
  private Integer number;

  @Column(name="label", nullable=false)
  private String label;

  @Enumerated(EnumType.STRING)
  @Column(name="status", nullable=false)
  private Status status;

  @Column(name="created_at", updatable=false, insertable=false)
  private Timestamp createdAt;

  @Column(name="called_at")
  private Timestamp calledAt;

  // getters/setters
}
