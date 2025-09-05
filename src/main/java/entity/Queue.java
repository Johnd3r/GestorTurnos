package entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name="queue")
public class Queue {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Column(name="code", unique=true, nullable=false)
  private String code;

  @Column(name="name", nullable=false)
  private String name;

  @Column(name="last_number", nullable=false)
  private Integer lastNumber;

  @Column(name="created_at", updatable=false, insertable=false)
  private Timestamp createdAt;

  // getters/setters
}
