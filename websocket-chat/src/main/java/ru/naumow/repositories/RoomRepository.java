package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumow.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
