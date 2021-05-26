package cn.nicecoder.barbersys.controller;

import cn.hutool.core.util.StrUtil;
import cn.nicecoder.barbersys.entity.*;
import cn.nicecoder.barbersys.entity.VO.BarberOrderStatisVO;
import cn.nicecoder.barbersys.entity.VO.MenuNodeVO;
import cn.nicecoder.barbersys.entity.VO.SysUserVO;
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
 * 页面跳转
 * @author: xxxxx
 * @date: 2021/5/20 上午10:49
 */
@Controller
public class PageController {

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    BarberMemberService barberMemberService;

    @Autowired
    BarberOrderService barberOrderService;

    @Autowired
    SysMenuService sysMenuService;

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    @GetMapping("/homepage")
    public String homepage(Model model){
        BarberOrderStatisVO overviewData = barberOrderService.getOverviewData();
        model.addAttribute("overviewData", overviewData);
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
        List<MenuNodeVO> menuTreeRoot = sysMenuService.createMenuTreeRoot(false, true);
        MenuTreeResp resp = new MenuTreeResp(menuTreeRoot);
        model.addAttribute("menuTree", resp);
        String firstHref = getFirstHref(menuTreeRoot.get(0));
        model.addAttribute("firstHref", firstHref);
        return "admin/index";
    }

    public String getFirstHref(MenuNodeVO nodeVO){
        List<MenuNodeVO> children = nodeVO.getChildren();
        for(MenuNodeVO menuNodeVO : children){
            if(StrUtil.isNotEmpty(menuNodeVO.getHref())){
                return menuNodeVO.getHref();
            }else{
                return getFirstHref(menuNodeVO);
            }
        }
        return "";
    }

    @GetMapping("/user/info")
    public String userInfo(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        SysUserVO sysUserVO = sysUserService.getOneByUsername(username);
        model.addAttribute("barberUserVO",sysUserVO);
        return "admin/user/info";
    }

    @GetMapping("/user/roleform")
    public String roleform(Model model, @RequestParam(value = "id", required = false) Long id){
        SysRole SysRole = new SysRole();
        if(id != null) {
            SysRole = sysRoleService.getById(id);
        }
        List<SysRoleMenu> barberRoleMenuList = sysRoleMenuService.list(new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getRoleId, id));
        StringBuffer menuBuffer = new StringBuffer();
        barberRoleMenuList.stream().forEach(item ->{
            menuBuffer.append(item.getMenuId()).append(",");
        });
        if(menuBuffer.length() > 0){menuBuffer.deleteCharAt(menuBuffer.length() - 1);}
        model.addAttribute("menus",menuBuffer.toString());
        model.addAttribute("barberRole", SysRole);
        return "admin/user/roleform";
    }

    @GetMapping("/menu/menuform")
    public String menuform(Model model, @RequestParam(value = "id", required = false) Long id){
        SysMenu sysMenu = new SysMenu();
        if(id != null) {
            sysMenu = sysMenuService.getById(id);
        }
        model.addAttribute("barberMenu", sysMenu);
        return "admin/menu/menuform";
    }

    @GetMapping("/user/userform")
    public String userform(Model model, @RequestParam(value = "id", required = false) Long id){
        SysUser sysUser = new SysUser();
        if(id != null) {
            sysUser = sysUserService.getById(id);
        }
        model.addAttribute("barberUser", sysUser);
        List<SysRole> roleList = sysRoleService.getRoleByUsername(sysUser.getUsername());
        Long[] roleArr = new Long[roleList.size()];
        for(SysRole item : roleList){
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
        List<SysUser> sysUserList = sysUserService.list(new LambdaQueryWrapper<SysUser>().eq(SysUser::getStatus, CommonEnum.NORMAL.getCode()));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        model.addAttribute("username",username);
        model.addAttribute("barberMember",barberMember);
        model.addAttribute("barberUserList", sysUserList);
        return "admin/member/rechargeform";
    }

    @GetMapping("/member/expenseform")
    public String expenseform(Model model, @RequestParam(value = "memberId", required = false) Long memberId){
        BarberMember barberMember = barberMemberService.getById(memberId);
        List<SysUser> sysUserList = sysUserService.list(new LambdaQueryWrapper<SysUser>().eq(SysUser::getStatus, CommonEnum.NORMAL.getCode()));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        model.addAttribute("username",username);
        model.addAttribute("barberMember",barberMember);
        model.addAttribute("barberUserList", sysUserList);
        return "admin/member/expenseform";
    }

    @GetMapping("/order/orderform")
    public String orderform(Model model){
        List<SysUser> sysUserList = sysUserService.list(new LambdaQueryWrapper<SysUser>().eq(SysUser::getStatus, CommonEnum.NORMAL.getCode()));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        model.addAttribute("username",username);
        model.addAttribute("barberUserList", sysUserList);
        return "admin/order/orderform";
    }

}

