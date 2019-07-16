package com.citynow.smallerp.mapper;

import com.citynow.smallerp.entity.LeaveCalendar;
import com.citynow.smallerp.model.LeaveForm;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface LeaveMapper {
    LeaveMapper INTANCE = Mappers.getMapper(LeaveMapper.class);

    @Mapping(source = "fromDate", target = "fromDate")
    @Mapping(source = "toDate", target = "toDate")
    @Mapping(source = "applicant", target = "applicant")
    @Mapping(source = "approve", target = "approve")
    LeaveForm LeaveFormleaveFormtoLeaveFormModel (LeaveCalendar leaveCalendar);

    @Mapping(source = "fromDate", target = "fromDate")
    @Mapping(source = "toDate", target = "toDate")
    @Mapping(source = "applicant", target = "applicant")
    @Mapping(source = "approve", target = "approve")
    LeaveCalendar leaveFormModeltoLeaveForm (LeaveForm leaveForm);
}
