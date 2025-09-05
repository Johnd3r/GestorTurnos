package repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import entity.Queue;
import jakarta.persistence.LockModeType;

public interface QueueRepository extends JpaRepository<Queue, Long> {
  Optional<Queue> findByCode(String code);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("select q from Queue q where q.code = :code")
  Optional<Queue> findByCodeForUpdate(@Param("code") String code);
}
