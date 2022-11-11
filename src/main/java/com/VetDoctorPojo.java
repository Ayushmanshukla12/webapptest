package com;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
public class VetDoctorPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;
    private int doctorAppointmentSlots;
    private int doctorFees;
    private String doctorName;
    private String doctorSpecialisation;
    private Date date;
    private Time startTime;
    private Time endTime;
    @ManyToMany
    private List<PateintPetPojo> pateintPetPojos;

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getDoctorAppointmentSlots() {
        return doctorAppointmentSlots;
    }

    public void setDoctorAppointmentSlots(int doctorAppointmentSlots) {
        this.doctorAppointmentSlots = doctorAppointmentSlots;
    }

    public int getDoctorFees() {
        return doctorFees;
    }

    public void setDoctorFees(int doctorFees) {
        this.doctorFees = doctorFees;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpecialisation() {
        return doctorSpecialisation;
    }

    public void setDoctorSpecialisation(String doctorSpecialisation) {
        this.doctorSpecialisation = doctorSpecialisation;
    }

    public List<PateintPetPojo> getPateintPetPojos() {
        return pateintPetPojos;
    }

    public void setPateintPetPojos(List<PateintPetPojo> pateintPetPojos) {
        this.pateintPetPojos = pateintPetPojos;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "VetDoctorPojo{" +
                "doctorId=" + doctorId +
                ", doctorAppointmentSlots=" + doctorAppointmentSlots +
                ", doctorFees=" + doctorFees +
                ", doctorName='" + doctorName + '\'' +
                ", doctorSpecialisation='" + doctorSpecialisation + '\'' +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", pateintPetPojos=" + pateintPetPojos +
                '}';
    }
}
