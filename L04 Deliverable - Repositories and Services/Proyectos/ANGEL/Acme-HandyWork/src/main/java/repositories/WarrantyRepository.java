
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import domain.Warranty;

@Transactional
public interface WarrantyRepository extends JpaRepository<Warranty, Integer> {

}
