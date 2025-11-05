package s.l.f.t.wh_t.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s.l.f.t.wh_t.entity.Thing;

import java.util.UUID;

@Repository
public interface ThingRepository extends JpaRepository<Thing, UUID> {
}
