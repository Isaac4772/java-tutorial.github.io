package lms.librarymanagement.model.entity;

import java.time.LocalDate;
import java.time.Year;

public class Member {
    private int cardId;
    private String name;
    private String rollNo;
    private Year academicYear;
    private String classYear;
    private LocalDate createdAt;
    private LocalDate expireAt;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public Year getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(Year academicYear) {
        this.academicYear = academicYear;
    }

    public String getClassYear() {
        return classYear;
    }

    public void setClassYear(String classYear) {
        this.classYear = classYear;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDate expireAt) {
        this.expireAt = getCreatedAt().plusYears(1);
    }
}
