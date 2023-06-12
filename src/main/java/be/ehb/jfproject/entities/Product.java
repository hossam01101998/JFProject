package be.ehb.jfproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {
    //Elk product dat is aangeboden bevat een id, een naam, een categorie,
// een omschrijving een username voor de contactpersoon, zijn email en
// een initiële vraagprijs. Denk zelf na hoe je dit best kan bijhouden.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Jij moet de naam schrijven!")
    private String name;
    @NotBlank (message = "Jij moet de categorie schrijven!")
    private String categorie;
    @NotBlank (message = "Jij moet de omschrijving schrijven!")
    private String omschrijving;

    @NotNull(message = "Jij moet de prijs schrijven!")
    @DecimalMin(value="0.5", message="Prijs moet meer dan 0.5 zijn")
    @Max(value = 1000000, message = "De prijs kan niet meer dan één miljoen zijn")
    private Double price;

    //muchos productos para un vendedor
    @ManyToOne
    @JoinColumn(name = "VerkoperID", nullable = false)
    private Verkoper verkopers;

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Verkoper getVerkopers() {
        return verkopers;
    }

    public void setVerkopers(Verkoper verkopers) {
        this.verkopers = verkopers;
    }


}

