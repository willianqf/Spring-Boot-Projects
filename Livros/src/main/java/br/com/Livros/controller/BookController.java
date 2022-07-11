package br.com.Livros.controller;

import br.com.Livros.entily.Author;
import br.com.Livros.entily.Book;
import br.com.Livros.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/book/form/add")
    public ModelAndView getFormAdd()
    {
        ModelAndView mv = new ModelAndView("bookform"); //
        List<Author> authorList = this.authorService.getAuthorList();
        mv.addObject("authorlist", authorList);
        return mv;
    }

    @PostMapping("/book/form/save")
    public String saveBook(@Valid Book book, BindingResult result,
                           RedirectAttributes redirect){
        if(result.hasErrors())
        {
            redirect.addFlashAttribute("mensagem", "Verifique os campos obrigatórios!");
            return "redirect:/book/form/add";
        }
        return "redirect:/lista";
    }
}
