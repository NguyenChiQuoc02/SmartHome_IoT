package com.iot.websocket.Repository;

import com.iot.websocket.Entity.SensorDataString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDataStringRepository extends JpaRepository<SensorDataString,Long> {

}
