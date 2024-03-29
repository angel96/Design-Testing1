
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("select m from Message m where m.sender.account.id = ?1")
	Collection<Message> findAllMessagesSendedByAnActor(int id);

	@Query("select m from Message m where m.receiver.account.id = ?1")
	Collection<Message> findAllMessagesReceivedByAnActor(int id);
}
