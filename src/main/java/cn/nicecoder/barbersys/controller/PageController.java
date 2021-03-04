package cn.nicecoder.barbersys.controller;
import cn.nicecoder.barbersys.entity.BarberMember;
import cn.nicecoder.barbersys.entity.BarberOrder;
import cn.nicecoder.barbersys.entity.BarberUser;
import cn.nicecoder.barbersys.entity.BarberUserRole;
import cn.nicecoder.barbersys.entity.VO.BarberUserVO;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.service.BarberMemberService;
import cn.nicecoder.barbersys.service.BarberOrderService;
import cn.nicecoder.barbersys.service.BarberUserRoleService;
import cn.nicecoder.barbersys.service.BarberUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员前端
 */
@Controller
public class PageController {

    @Autowired
    BarberUserRoleService barberUserRoleService;

    @Autowired
    BarberUserService barberUserService;

    @Autowired
    BarberMemberService barberMemberService;

    @Autowired
    BarberOrderService barberOrderService;

    @RequestMapping("/welcome")
    public String admin(){
        return "admin/index";
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
    public String articleListForm(){
        return "admin/app/content/listform";
    }

    @GetMapping("/catalog/list")
    public String catalogList(){
        return "admin/app/content/tags";
    }

    @GetMapping("/catalog/listform")
    public String catalogListForm(){
        return "admin/app/content/tagsform";
    }

    @GetMapping("/comment/list")
    public String commentList(){
        return "admin/app/content/comment";
    }

    @GetMapping("/user0/list")
    public String user0List(){
        return "admin/user/administrators/list";
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

    @GetMapping("/recharge/list")
    public String rechargeList(){
        return "admin/account/recharge/list";
    }

    @GetMapping("/recharge/rechargeform")
    public String rechargeform(){
        return "admin/account/recharge/rechargeform";
    }



    @GetMapping("/user/role")
    public String userRole(){
        return "admin/user/role";
    }

    @GetMapping("/user/list")
    public String userList(){
        return "admin/user/user";
    }

    @GetMapping("/member/list")
    public String memberList(){
        return "admin/member/member";
    }

    @GetMapping("/order/list")
    public String orderList(){
        return "admin/order/order";
    }

    @GetMapping("/user/password")
    public String userPassword(){
        return "admin/user/password";
    }

    @GetMapping("/user/info")
    public String userInfo(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        BarberUserVO barberUserVO = barberUserService.getOneByUsername(username);
        model.addAttribute("barberUserVO",barberUserVO);
        return "admin/user/info";
    }

    @GetMapping("/user/roleform")
    public String roleform(Model model, @RequestParam(value = "id", required = false) Long id){
        BarberUserRole barberUserRole = new BarberUserRole();
        if(id != null) {
            barberUserRole = barberUserRoleService.getById(id);
        }
        model.addAttribute("barberUserRole", barberUserRole);
        return "admin/user/roleform";
    }

    @GetMapping("/user/userform")
    public String userform(Model model, @RequestParam(value = "id", required = false) Long id){
        BarberUser barberUser = new BarberUser();
        if(id != null) {
            barberUser = barberUserService.getById(id);
        }
        model.addAttribute("barberUser", barberUser);
        return "admin/user/userform";
    }

    @GetMapping("/member/memberform")
    public String memberform(Model model, @RequestParam(value = "id", required = false) Long id){
        BarberMember barberMember = new BarberMember();
        if(id != null) {
            barberMember = barberMemberService.getById(id);
        }
        model.addAttribute("barberMember", barberMember);
        return "admin/member/memberform";
    }

    @GetMapping("/member/rechargeform")
    public String rechargeform(Model model, @RequestParam(value = "memberId", required = false) Long memberId){
        BarberMember barberMember = barberMemberService.getById(memberId);
        model.addAttribute("barberMember",barberMember);
        return "admin/member/rechargeform";
    }

    @GetMapping("/member/expenseform")
    public String expenseform(Model model, @RequestParam(value = "memberId", required = false) Long memberId){
        BarberMember barberMember = barberMemberService.getById(memberId);
        model.addAttribute("barberMember",barberMember);
        return "admin/member/expenseform";
    }

    @GetMapping("/order/orderform")
    public String orderform(Model model, @RequestParam(value = "id", required = false) Long id){
        BarberOrder barberOrder = new BarberOrder();
        if(id != null) {
            barberOrder = barberOrderService.getById(id);
        }
        model.addAttribute("barberOrder", barberOrder);
        return "admin/order/orderform";
    }

}

