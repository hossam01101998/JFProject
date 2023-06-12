package be.ehb.jfproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Verkoper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Jij moet de username schrijven")
    private String username;
    @NotBlank(message = "Jij moet de email schrijven")
    private String email;
    //people will only see the email , username and id is private
    @OneToMany(mappedBy = "verkopers")
    //un vendedor puede vender muchos productos!!
    List<Product> products =new ArrayList<>();

    public Verkoper() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
