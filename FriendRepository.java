package springrest.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springrest.exam.entity.Friend;

public interface FriendRepository extends JpaRepository<Friend, Integer> {

}
