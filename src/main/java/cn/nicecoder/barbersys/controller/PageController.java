package cn.nicecoder.barbersys.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前端
 */
@Controller
public class PageController {

    @GetMapping("/")
    public String admin(){
        return "index";
    }
}

