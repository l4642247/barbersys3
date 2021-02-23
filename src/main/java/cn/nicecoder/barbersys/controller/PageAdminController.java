package cn.nicecoder.barbersys.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员前端
 */
@Controller
@RequestMapping("/admin")
public class PageAdminController {

    @GetMapping("/")
    public String admin(){
        return "admin/index";
    }

    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    @GetMapping("/homepage")
    public String homepage(){
        return "admin/home/homepage";
    }

    @GetMapping("/console")
    public String console(){
        return "admin/home/console";
    }

    @GetMapping("/article/list")
    public String articleList(){
        return "admin/app/content/list";
    }

    @GetMapping("/article/listform")
    public String listform(){
        return "admin/app/content/listform";
    }

    @GetMapping("/catalog/list")
    public String catalogList(){
        return "admin/app/content/tags";
    }

    @GetMapping("/comment/list")
    public String commentList(){
        return "admin/app/content/comment";
    }

    @GetMapping("/user0/list")
    public String user0List(){
        return "admin/user/administrators/list";
    }

    @GetMapping("/user1/list")
    public String user1List(){
        return "admin/user/user/list";
    }

    @GetMapping("/user1/userform")
    public String userform(){
        return "admin/user/user/userform";
    }

    @GetMapping("/role/list")
    public String roleList(){
        return "admin/user/administrators/role";
    }

    @GetMapping("/website")
    public String websiteInfo(){
        return "admin/set/system/website";
    }

    @GetMapping("/email")
    public String email(){
        return "admin/set/system/email";
    }

    @GetMapping("/userInfo")
    public String userInfo(){
        return "admin/set/user/info";
    }

    @GetMapping("/userPsw")
    public String userPsw(){
        return "admin/set/user/password";
    }

    @GetMapping("/recharge/list")
    public String rechargeList(){
        return "admin/account/recharge/list";
    }

    @GetMapping("/recharge/rechargeform")
    public String rechargeform(){
        return "admin/account/recharge/rechargeform";
    }

}

