
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("select m from Message m where m.sender = ?1")
	Collection<Message> findAllMessagesSendedByAnActor(Integer actorId);

	@Query("select m from Message m where m.receiver = ?1")
	Collection<Message> findAllMessagesReceivedByAnActor(Integer actorId);
}
