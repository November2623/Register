package com.citynow.smallerp.entity;


import lombok.Data;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@Entity
@Table(name="leave_calendar")
public class LeaveCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "from_date")
    private Date fromDate;

    @NotNull
    @Column(name = "to_date")
    private Date toDate;

    @NotBlank
    @Column(name="applicant")
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public LeaveCalendar(Date fromDate,Date toDate,String name, User user) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.name = name;
        this.user = user;
    }

    public LeaveCalendar() {
    }
}