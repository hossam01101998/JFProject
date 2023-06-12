package be.ehb.jfproject.controller;

import be.ehb.jfproject.dao.ProductDAO;
import be.ehb.jfproject.dao.VerkoperDAO;
import be.ehb.jfproject.entities.Product;

import be.ehb.jfproject.entities.Verkoper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@Validated
public class ProductController {

    private VerkoperDAO mVerkoperDAO;
    private ProductDAO mProductDAO;
    @Autowired
    public ProductController(ProductDAO mProductDAO, VerkoperDAO mVerkoperDAO) {
        this.mProductDAO = mProductDAO;
        this.mVerkoperDAO = mVerkoperDAO;
    }
    @ModelAttribute("products")
    public Iterable<Product> getAllProducts(){
        return mProductDAO.findAll();
    }

    @GetMapping({"/","/about"})
    public String displayIndex(ModelMap modelMap){
        return "about";
    }
    @GetMapping("/index")
    public String showAbout(){
        return "index";
    }

    @ModelAttribute("producttoSave")
    public Product productForForm(){
        return new Product();
    }

    @GetMapping("/newproduct")
    public String showNewProduct(ModelMap modelMap){
        return "/newproduct";
    }
    /*@PostMapping("/newproduct")
    public String insertProduct(@Valid @ModelAttribute ("producttoSave") Product product, BindingResult result){

        if(result.hasErrors()){
            return "/newproduct";
        }
         mProductDAO.save(product);

        return "redirect:/index";
    }*/
    @PostMapping("/newproduct")
    public String insertProduct(@Valid @ModelAttribute("producttoSave") Product product, BindingResult result) {
        if (result.hasErrors()) {

            return "/newproduct";
        }

        // ID VERKOPER EXISTS??
        int verkoperId = product.getVerkopers().getId();
        if (!mVerkoperDAO.existsById(verkoperId)) {

            return "redirect:/newproduct";
        }

        mProductDAO.save(product);
        return "redirect:/index";
    }


    @GetMapping("/productinfo/{id}")
    public String showNewProduct(ModelMap modelMap, @PathVariable int id) {
        if(mProductDAO.existsById(id) ){
                modelMap.addAttribute("product", mProductDAO.findById(id).get());
                modelMap.addAttribute("verkoper", mVerkoperDAO.findById(mProductDAO.findById(id).get().getVerkopers().getId()).get());

        }else modelMap.addAttribute("error", "NO PRODUCT WITH ID "+ id);

        return "productinfo";
    }

}

