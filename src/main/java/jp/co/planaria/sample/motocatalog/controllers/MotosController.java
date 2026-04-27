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
import jp.co.planaria.sample.motocatalog.beans.SearchForm;
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
    
    /**
     * バイク一覧を検索する
     * @param searchForm 検索条件
     * @param model Model
     * @return 遷移先
     */
    @GetMapping("/motos")
    public String motos(SearchForm searchForm, Model model){
        log.info("検索条件{}", searchForm);
        //ブランドリストを準備   
        this.setBrands(model);

        //バイク
        List<Motorcycle> motos = new ArrayList<>();
        motos = service.getMotos(searchForm);

         model.addAttribute("motos",motos);

         log.debug("mots:{}",motos); //ログ出力する

        return "moto_list";
    }
    
    /**
     * 検索条件をクリアする
     * @param searchForm　検索条件
     * @param model model
     * @return 遷移先
     */
    @GetMapping("/motos/reset")
    public String reset(SearchForm searchForm, Model model){
        //ブランドリストを準備
        this.setBrands(model);

        //検索条件のクリア
        searchForm = new SearchForm();
        return "moto_list";
    }
    
    /**
     * ブランドリストをModelにセットする
     * @param model Model
     */
    private void setBrands(Model model){
         List<Brand> brands = new ArrayList<>();
         brands = service.getBrands();
         model.addAttribute("brands", brands);

    }
}