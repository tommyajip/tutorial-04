package com.apap.tu04.repository;

import com.apap.tu04.model.FlightModel;
import com.apap.tu04.model.PilotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDb extends JpaRepository<FlightModel, Long> {
	void deleteByPilotAndFlightNumber(PilotModel pilot, String flightNumber);
	FlightModel findByPilotAndFlightNumber(PilotModel pilot, String flightNumber2);
}