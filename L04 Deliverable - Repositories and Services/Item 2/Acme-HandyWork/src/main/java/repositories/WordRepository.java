
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {

	@Query("select w from Word w where w.isGood = 0")
	Collection<Word> getBadWords();
	@Query("select w from Word w where w.isGood = 1")
	Collection<Word> getGoodWords();
}
