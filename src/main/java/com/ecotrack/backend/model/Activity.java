package com.ecotrack.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mapped to column "user_email" to match the existing DB schema
    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String category;

    // Column name "subtype" matches the existing DB schema
    @Column(name = "subtype")
    private String subtype;

    private double value;

    private String date;

    private String note;

    private double co2;

    // ── Constructors ──────────────────────────────────────────────────────────

    public Activity() {}

    public Activity(String userEmail, String category, String subtype,
                    double value, String date, String note, double co2) {
        this.userEmail = userEmail;
        this.category  = category;
        this.subtype   = subtype;
        this.value     = value;
        this.date      = date;
        this.note      = note;
        this.co2       = co2;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public Long getId()                   { return id; }
    public void setId(Long id)            { this.id = id; }

    public String getUserEmail()                    { return userEmail; }
    public void   setUserEmail(String userEmail)    { this.userEmail = userEmail; }

    public String getCategory()                     { return category; }
    public void   setCategory(String category)      { this.category = category; }

    public String getSubtype()                      { return subtype; }
    public void   setSubtype(String subtype)        { this.subtype = subtype; }

    public double getValue()                        { return value; }
    public void   setValue(double value)            { this.value = value; }

    public String getDate()                         { return date; }
    public void   setDate(String date)              { this.date = date; }

    public String getNote()                         { return note; }
    public void   setNote(String note)              { this.note = note; }

    public double getCo2()                          { return co2; }
    public void   setCo2(double co2)                { this.co2 = co2; }
}
