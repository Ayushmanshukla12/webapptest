package com;

import javax.persistence.*;
import java.util.List;

@Entity
public class PateintPetPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pateintId;
    private String pateintName;
    private String pateintDesease;
    @ManyToMany(mappedBy = "pateintPetPojos")
    private List<VetDoctorPojo> doctorPojos;

    public int getPateintId() {
        return pateintId;
    }

    public void setPateintId(int pateintId) {
        this.pateintId = pateintId;
    }

    public String getPateintName() {
        return pateintName;
    }

    public void setPateintName(String pateintName) {
        this.pateintName = pateintName;
    }

    public String getPateintDesease() {
        return pateintDesease;
    }

    public void setPateintDesease(String pateintDesease) {
        this.pateintDesease = pateintDesease;
    }

    public List<VetDoctorPojo> getDoctorPojos() {
        return doctorPojos;
    }

    public void setDoctorPojos(List<VetDoctorPojo> doctorPojos) {
        this.doctorPojos = doctorPojos;
    }

    @Override
    public String toString() {
        return "PateintPetPojo{" +
                "pateintId=" + pateintId +
                ", pateintName='" + pateintName + '\'' +
                ", pateintDesease='" + pateintDesease + '\'' +
                ", doctorPojos=" + doctorPojos +
                '}';
    }
}
