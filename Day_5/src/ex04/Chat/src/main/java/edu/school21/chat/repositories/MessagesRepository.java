package edu.school21.chat.repositories;

import java.util.Optional;
import java.util.List;
import edu.school21.chat.models.*;

public interface MessagesRepository {
    Optional<Message> findById(Long id);
    void save(Message message);
    void update(Message message);  

    List<User> findAll(int page, int size); 
}
