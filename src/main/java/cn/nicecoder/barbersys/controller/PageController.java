package cn.nicecoder.barbersys.controller;

import cn.nicecoder.barbersys.entity.*;
import cn.nicecoder.barbersys.entity.VO.BarberUserVO;
import cn.nicecoder.barbersys.entity.comm.MenuTreeResp;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 管理员前端
 */
@Controller
public class PageController {

    @Autowired
    BarberRoleService barberRoleService;

    @Autowired
    BarberUserService barberUserService;

    @Autowired
    BarberMemberService barberMemberService;

    @Autowired
    BarberOrderService barberOrderService;

    @Autowired
    BarberMenuService barberMenuService;

    @Autowired
    BarberRoleMenuService barberRoleMenuService;

    @GetMapping("/homepage")
    public String homepage(){
        return "admin/home/homepage";
    }

    @GetMapping("/console")
    public String console(){
        return "admin/home/console";
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

    @GetMapping("/menu/list")
    public String menuList(){
        return "admin/menu/menu";
    }

    @GetMapping("/403")
    public String accessdenied(){
        return "403";
    }

    @RequestMapping("/admin")
    public String admin(Model model){
        MenuTreeResp resp = new MenuTreeResp(barberMenuService.createMenuTreeRoot(false));
        model.addAttribute("menuTree", resp);
        return "admin/index";
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
        BarberRole BarberRole = new BarberRole();
        if(id != null) {
            BarberRole = barberRoleService.getById(id);
        }
        List<BarberRoleMenu> barberRoleMenuList = barberRoleMenuService.list(new LambdaQueryWrapper<BarberRoleMenu>()
                .eq(BarberRoleMenu::getRoleId, id));
        StringBuffer menuBuffer = new StringBuffer();
        barberRoleMenuList.stream().forEach(item ->{
            menuBuffer.append(item.getMenuId()).append(",");
        });
        if(menuBuffer.length() > 0){menuBuffer.deleteCharAt(menuBuffer.length() - 1);}
        model.addAttribute("menus",menuBuffer.toString());
        model.addAttribute("barberRole", BarberRole);
        return "admin/user/roleform";
    }

    @GetMapping("/menu/menuform")
    public String menuform(Model model, @RequestParam(value = "id", required = false) Long id){
        BarberMenu barberMenu = new BarberMenu();
        if(id != null) {
            barberMenu = barberMenuService.getById(id);
        }
        model.addAttribute("barberMenu", barberMenu);
        return "admin/menu/menuform";
    }

    @GetMapping("/user/userform")
    public String userform(Model model, @RequestParam(value = "id", required = false) Long id){
        BarberUser barberUser = new BarberUser();
        if(id != null) {
            barberUser = barberUserService.getById(id);
        }
        model.addAttribute("barberUser", barberUser);
        List<BarberRole> roleList = barberRoleService.getRoleByUsername(barberUser.getUsername());
        Long[] roleArr = new Long[roleList.size()];
        for(BarberRole item : roleList){
            roleArr[roleList.indexOf(item)] = item.getId();
        }
        model.addAttribute("roles", roleArr);
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
        List<BarberUser> barberUserList = barberUserService.list(new LambdaQueryWrapper<BarberUser>().eq(BarberUser::getStatus, CommonEnum.NORMAL.getCode()));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        model.addAttribute("username",username);
        model.addAttribute("barberMember",barberMember);
        model.addAttribute("barberUserList",barberUserList);
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

