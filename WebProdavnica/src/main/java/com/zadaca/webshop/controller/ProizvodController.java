package com.zadaca.webshop.controller;

import com.zadaca.webshop.dto.ProizvodDto;
import com.zadaca.webshop.model.Proizvod;
import com.zadaca.webshop.service.ProizvodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProizvodController {

    @Autowired
    private ProizvodService proizvodService;

    @GetMapping("/pregledproizvoda")
    public String showP(Model model){
        model.addAttribute("products",proizvodService.getAllProizvod());
        return "proizvodi";
    }

    @GetMapping("/dodajproizvod")
    public String showFormForDodajProizvod(Model model){
        model.addAttribute("proizvod",new ProizvodDto());
        return "dodajProizvod";
    }

    @PostMapping("/dodajproizvod")
    public String dodajProizvod(Model model, @Valid @ModelAttribute("proizvod") ProizvodDto proizvodDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("proizvod",proizvodDto);

            return "dodajProizvod";
        }

        MultipartFile multipartFile=proizvodDto.getImg();


        if (multipartFile!=null && !multipartFile.isEmpty()) {
            try {

                int i=(int)(Math.random()*1000);
                String b=String.valueOf(i)+String.valueOf(proizvodService.getMaxId()+1);
                byte[] bytes = multipartFile.getBytes();
                Path path = Paths.get("src\\main\\resources\\static\\images\\", b + ".png");
                proizvodDto.setSlika("\\images\\"+ b + ".png");
                Files.write(path, bytes);
            } catch (Exception e) {
                throw new RuntimeException("Product Image saving failed", e);
            }
        }
        proizvodService.saveProizvod(proizvodDto);
        return "redirect:/azuriranjeproizvoda";
    }

    @GetMapping("/urediproizvod/{id}")
    public String showFormForUrediProizvod(Model model, @PathVariable("id") int id){
        ProizvodDto proizvod=proizvodService.getProizvodById(id);
        if(proizvod!=null){

        model.addAttribute("proizvod",proizvod);
        return "urediProizvod";
        }
        else{
            return "/azuriranjeproizvoda";
        }
    }

    @PostMapping("/urediproizvod/{id}")
    public String urediProizvod(Model model, @Valid @ModelAttribute("proizvod") ProizvodDto proizvodDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("proizvod",proizvodDto);
            System.out.println(proizvodDto.getSlika());
            return "urediProizvod";
        }

        MultipartFile multipartFile=proizvodDto.getImg();


        if (multipartFile!=null && !multipartFile.isEmpty()) {


            Path pt = Paths.get("src\\main\\resources\\static\\"+proizvodDto.getSlika());
            deleteFile(pt);



           try {


               int i=(int)(Math.random()*1000);
               String b=String.valueOf(i)+String.valueOf(proizvodDto.getId());
                byte[] bytes = multipartFile.getBytes();
                Path path = Paths.get("src\\main\\resources\\static\\images\\"+ b+ ".png");
               proizvodDto.setSlika("\\images\\"+ b + ".png");
                Files.write(path, bytes);
            } catch (Exception e) {
                throw new RuntimeException("Product Image saving failed", e);
            }
        }else{
            proizvodDto.setSlika(proizvodDto.getSlika());
        }
        proizvodService.editProizvod(proizvodDto);
        return "redirect:/azuriranjeproizvoda";
    }

    private boolean deleteFile(Path path){
        try {


            Files.delete(path);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Failed to Delete image !!"+e);
            return false;
        }

    }


    @GetMapping("/proizvod/{id}")
    public String showProizvod(Model model,@PathVariable("id") int id){
        model.addAttribute("proizvod",proizvodService.getProizvodById(id));
        return "proizvod";
    }

    @GetMapping("/azuriranjeproizvoda")
    public String showAzuriranje(Model model){
        model.addAttribute("products",proizvodService.getAllProizvod());
        return "azuriranje";
    }

}
