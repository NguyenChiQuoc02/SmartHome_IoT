package com.iot.websocket.Repository;

import com.iot.websocket.Entity.LightRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LightRoomRepository extends JpaRepository<LightRoom,Long> {
}
