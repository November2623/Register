package com.citynow.smallerp.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;


@Data
public class LeaveForm {
    @NotNull
    private Date fromDate;
    @NotNull
    private Date toDate;
    @NotBlank
    private String applicate;
    @NotBlank
    private String approve;


    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getApplicate() {
        return applicate;
    }

    public void setApplicate(String applicate) {
        this.applicate = applicate;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }
}