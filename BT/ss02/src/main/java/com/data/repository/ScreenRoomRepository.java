package com.data.repository;

import com.data.entity.ScreenRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScreenRoomRepository extends JpaRepository<ScreenRoom, Long> {
    List<ScreenRoom> findByNameContainingIgnoreCase(String name);
    List<ScreenRoom> findByTheaterId(Long theaterId);
}
