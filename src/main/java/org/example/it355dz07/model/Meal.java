package org.example.it355dz07.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jelo")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String opis;
    private double cena;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_restorana", nullable = false)
    private Restaurant restoran;

    public Meal() {
    }

    public Meal(Long id, String naziv, String opis, double cena, Restaurant restoran) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.restoran = restoran;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Restaurant getRestoran() {
        return restoran;
    }

    public void setRestoran(Restaurant restoran) {
        this.restoran = restoran;
    }
}
