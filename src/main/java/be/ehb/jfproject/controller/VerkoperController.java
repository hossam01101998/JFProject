package be.ehb.jfproject.controller;

import be.ehb.jfproject.dao.ProductDAO;
import be.ehb.jfproject.dao.VerkoperDAO;
import be.ehb.jfproject.entities.Verkoper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Validated
public class VerkoperController {
    private VerkoperDAO mVerkoperDAO;
    private ProductDAO mProductDAO;
    @Autowired
    public VerkoperController(ProductDAO mProductDAO, VerkoperDAO mVerkoperDAO) {
        this.mProductDAO = mProductDAO;
        this.mVerkoperDAO = mVerkoperDAO;
    }
    @ModelAttribute("verkopers")
    public Iterable<Verkoper> getAllVerkopers(){
        return mVerkoperDAO.findAll();
    }

    @ModelAttribute("VerkopertoSave")
    public Verkoper verkoperForForm(){
        return new Verkoper();
    }

    @GetMapping("/signin")
    public String showSingIn (ModelMap modelMap){
        return "signin";
    }

    @PostMapping("/signin")
    public String InsertVerkoper(@Valid @ModelAttribute("VerkopertoSave") Verkoper verkoper, BindingResult result) {
        if (result.hasErrors()) {
            return "/signin";
        }

        // EMAIL UNIQUE!!
        if (mVerkoperDAO.findByEmail(verkoper.getEmail()) != null) {
            result.rejectValue("email", "error.verkoper", "This email already exists in our database");
            return "/signin";
        }

        // USERNAME UNIQUE!!
        if (mVerkoperDAO.findByUsername(verkoper.getUsername()) != null) {
            result.rejectValue("username", "error.verkoper", "This username already exists in our database");
            return "/signin";
        }

        mVerkoperDAO.save(verkoper);
        return "redirect:/index";
    }

}
