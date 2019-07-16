package com.citynow.smallerp.repository;

import com.citynow.smallerp.entity.LeaveCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<LeaveCalendar, Long> {

}
