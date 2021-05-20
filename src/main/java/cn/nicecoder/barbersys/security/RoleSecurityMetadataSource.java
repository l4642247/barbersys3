package cn.nicecoder.barbersys.security;

import cn.nicecoder.barbersys.entity.PO.PermissionPO;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.service.SysRoleService;
import cn.nicecoder.barbersys.util.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 从数据源中加载ConfigAttribute到SecurityMetadataSource资源器中，为授权决策器作准备
 * @author: xxxxx
 * @date: 2021/5/20 上午10:45
 */
@Component
public class RoleSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final Map<String,List<String>> urlRoleMap = new HashMap<>();

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private RedisClient redisClient;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(urlRoleMap.isEmpty() || redisClient.get(CommonEnum.REDIS_KEY_MENU_PERMISSION.getDesc()) == null){
            List<PermissionPO> permissionPOList = sysRoleService.getResourcePermission();
            /**
             * 固定：给"/admin"添加访问权限
             */
            List<String> anyRoles = new ArrayList<>();
            permissionPOList.stream().forEach(item ->{
                anyRoles.add(item.getCode());
            });
            urlRoleMap.put("/admin", anyRoles.stream().distinct().collect(Collectors.toList()));


            /**
             * 动态：从数据库加载请求需要的权限
             */
            Map<String, List<PermissionPO>> collectGroup = permissionPOList.stream().collect(Collectors.groupingBy(PermissionPO::getHref));
            for (String key : collectGroup.keySet()) {
                List<String> roles = new ArrayList();
                List<PermissionPO> permissionPOS = collectGroup.get(key);
                for(PermissionPO item : permissionPOS){
                    roles.add(item.getCode());
                }
                urlRoleMap.put("/"+key, roles);
            }
            // 缓存设置一天有效
            redisClient.set(CommonEnum.REDIS_KEY_MENU_PERMISSION.getDesc(), permissionPOList,24 * 60 * 60);
        }
        //根据 请求获取 需要的权限
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String url = filterInvocation.getRequestUrl();
        for (Map.Entry<String, List<String>> entry : urlRoleMap.entrySet()) {
            if (antPathMatcher.match(entry.getKey(), url)) {
                String[] array = entry.getValue().toArray(new String[0]);
                return SecurityConfig.createList(array);
            }
        }    
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
     
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}