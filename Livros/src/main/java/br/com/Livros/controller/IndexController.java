package br.com.Livros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //CRIA AS ROTAS //
public class IndexController {

    @GetMapping("/lista")
    public ModelAndView getList()
    {
        ModelAndView mv = new ModelAndView("index"); //
        return mv;
    }
}
