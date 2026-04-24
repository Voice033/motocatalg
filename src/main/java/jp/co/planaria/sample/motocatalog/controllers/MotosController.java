package jp.co.planaria.sample.motocatalog.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.planaria.sample.motocatalog.beans.Brand;
import jp.co.planaria.sample.motocatalog.beans.Motorcycle;
import jp.co.planaria.sample.motocatalog.services.MotosService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j //ログ部分を使えるようになる
public class MotosController {

    @Autowired
    MotosService service;

    
    //private static final Logger log = LoggerFactory.getLogger(clazz : MotosController.class);

    @RequestMapping("/hello")
    public String hello(@RequestParam String name, Model model){
        model.addAttribute("name", name);
        return "test";
    }

    @GetMapping("/motos")
    public String motos(Model model){
        //ブランド        
        List<Brand> brands = new ArrayList<>();
        brands = service.getBrands();

        //バイク
        List<Motorcycle> motos = new ArrayList<>();
        motos = service.getMotos();

         model.addAttribute("brands",brands);
         model.addAttribute("motos",motos);

         log.debug("mots:{}",motos); //ログ出力する

        return "moto_list";
    }
}