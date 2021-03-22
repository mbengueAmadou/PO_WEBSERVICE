package com.mycompany.myapp.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Sencovid.
 */
@Entity
@Table(name = "sencovid")
public class Sencovid implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nbrtest")
    private String nbrtest;

    @Column(name = "postifcase")
    private String postifcase;

    @Column(name = "imported_case")
    private String importedCase;

    @Column(name = "death")
    private String death;

    @Column(name = "recovered")
    private String recovered;

    @Column(name = "date")
    private LocalDate date;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNbrtest() {
        return nbrtest;
    }

    public Sencovid nbrtest(String nbrtest) {
        this.nbrtest = nbrtest;
        return this;
    }

    public void setNbrtest(String nbrtest) {
        this.nbrtest = nbrtest;
    }

    public String getPostifcase() {
        return postifcase;
    }

    public Sencovid postifcase(String postifcase) {
        this.postifcase = postifcase;
        return this;
    }

    public void setPostifcase(String postifcase) {
        this.postifcase = postifcase;
    }

    public String getImportedCase() {
        return importedCase;
    }

    public Sencovid importedCase(String importedCase) {
        this.importedCase = importedCase;
        return this;
    }

    public void setImportedCase(String importedCase) {
        this.importedCase = importedCase;
    }

    public String getDeath() {
        return death;
    }

    public Sencovid death(String death) {
        this.death = death;
        return this;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getRecovered() {
        return recovered;
    }

    public Sencovid recovered(String recovered) {
        this.recovered = recovered;
        return this;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public LocalDate getDate() {
        return date;
    }

    public Sencovid date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sencovid)) {
            return false;
        }
        return id != null && id.equals(((Sencovid) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sencovid{" +
            "id=" + getId() +
            ", nbrtest='" + getNbrtest() + "'" +
            ", postifcase='" + getPostifcase() + "'" +
            ", importedCase='" + getImportedCase() + "'" +
            ", death='" + getDeath() + "'" +
            ", recovered='" + getRecovered() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
