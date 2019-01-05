package top.zbeboy.dms.service.system;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import top.zbeboy.dms.config.Workbook;
import top.zbeboy.dms.domain.dms.tables.daos.AuthoritiesDao;
import top.zbeboy.dms.domain.dms.tables.pojos.Authorities;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static top.zbeboy.dms.domain.dms.Tables.AUTHORITIES;

@Service("authoritiesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AuthoritiesServiceImpl implements AuthoritiesService {

    private final DSLContext create;

    @Resource
    private AuthoritiesDao authoritiesDao;

    @Autowired
    AuthoritiesServiceImpl(DSLContext dslContext) {
        create = dslContext;
    }

    @Override
    public List<Authorities> findByUsername(String username) {
        return authoritiesDao.fetchByUsername(username);
    }

    @Override
    public Boolean isAnonymousAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !ObjectUtils.isEmpty(authentication) && new AuthenticationTrustResolverImpl().isAnonymous(authentication);
    }

    @Override
    public Boolean isCurrentUserInRole(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                return springSecurityUser.getAuthorities().contains(new SimpleGrantedAuthority(authority));
            }
        }
        return false;
    }

    @Override
    public StringBuilder getAuthorities() {
        StringBuilder stringBuilder = new StringBuilder();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(i -> {
            if (i.getAuthority().equalsIgnoreCase(Workbook.ROLE_SYSTEM)) {
                stringBuilder.append("系统管理员 ");
            }

            if (i.getAuthority().equalsIgnoreCase(Workbook.ROLE_COLLEGE_YOUTH_LEAGUE_COMMITTEE)) {
                stringBuilder.append("学院团委 ");
            }

            if (i.getAuthority().equalsIgnoreCase(Workbook.ROLE_COLLEGE_WORK_DEPARTMENT)) {
                stringBuilder.append("学生工作部 ");
            }

            if (i.getAuthority().equalsIgnoreCase(Workbook.ROLE_DEPARTMENT_INSTRUCTOR)) {
                stringBuilder.append("系部辅导员 ");
            }

            if (i.getAuthority().equalsIgnoreCase(Workbook.ROLE_HEADMASTER)) {
                stringBuilder.append("班主任 ");
            }

            if (i.getAuthority().equalsIgnoreCase(Workbook.ROLE_STUDENT)) {
                stringBuilder.append("学生 ");
            }

            if (i.getAuthority().equalsIgnoreCase(Workbook.ROLE_STAFF)) {
                stringBuilder.append("教师 ");
            }
        });
        return stringBuilder;
    }

    @Override
    public String getAuthName(String roleId) {
        String role = "";
        if (roleId.equalsIgnoreCase(Workbook.ROLE_SYSTEM)) {
            role = "系统管理员";
        }

        if (roleId.equalsIgnoreCase(Workbook.ROLE_COLLEGE_YOUTH_LEAGUE_COMMITTEE)) {
            role = "学院团委";
        }

        if (roleId.equalsIgnoreCase(Workbook.ROLE_COLLEGE_WORK_DEPARTMENT)) {
            role = "学生工作部";
        }

        if (roleId.equalsIgnoreCase(Workbook.ROLE_DEPARTMENT_INSTRUCTOR)) {
            role = "系部辅导员";
        }

        if (roleId.equalsIgnoreCase(Workbook.ROLE_HEADMASTER)) {
            role = "班主任";
        }

        if (roleId.equalsIgnoreCase(Workbook.ROLE_STUDENT)) {
            role = "学生";
        }

        if (roleId.equalsIgnoreCase(Workbook.ROLE_STAFF)) {
            role = "教师";
        }
        return role;
    }

    @Override
    public List<Map<String, String>> getAuthAll() {
        List<Map<String, String>> maps = new ArrayList<>();

        Map<String, String> map1 = new HashMap<>();
        map1.put("roleId", Workbook.ROLE_SYSTEM);
        map1.put("roleName", "系统管理员");

        Map<String, String> map2 = new HashMap<>();
        map2.put("roleId", Workbook.ROLE_COLLEGE_YOUTH_LEAGUE_COMMITTEE);
        map2.put("roleName", "学院团委");

        Map<String, String> map3 = new HashMap<>();
        map3.put("roleId", Workbook.ROLE_COLLEGE_WORK_DEPARTMENT);
        map3.put("roleName", "学生工作部");

        Map<String, String> map4 = new HashMap<>();
        map4.put("roleId", Workbook.ROLE_DEPARTMENT_INSTRUCTOR);
        map4.put("roleName", "系部辅导员");

        Map<String, String> map5 = new HashMap<>();
        map5.put("roleId", Workbook.ROLE_HEADMASTER);
        map5.put("roleName", "班主任");

        Map<String, String> map6 = new HashMap<>();
        map6.put("roleId", Workbook.ROLE_STUDENT);
        map6.put("roleName", "学生");

        Map<String, String> map7 = new HashMap<>();
        map7.put("roleId", Workbook.ROLE_STAFF);
        map7.put("roleName", "教职工");

        if (isCurrentUserInRole(Workbook.ROLE_SYSTEM)) {
            maps.add(map1);
            maps.add(map2);
            maps.add(map3);
            maps.add(map4);
            maps.add(map5);
            maps.add(map6);
            maps.add(map7);
        } else if (isCurrentUserInRole(Workbook.ROLE_COLLEGE_YOUTH_LEAGUE_COMMITTEE)) {
            maps.add(map3);
            maps.add(map4);
            maps.add(map5);
            maps.add(map6);
            maps.add(map7);

        } else if (isCurrentUserInRole(Workbook.ROLE_COLLEGE_WORK_DEPARTMENT)) {
            maps.add(map4);
            maps.add(map5);
            maps.add(map6);
            maps.add(map7);

        } else if (isCurrentUserInRole(Workbook.ROLE_DEPARTMENT_INSTRUCTOR)) {
            maps.add(map5);
            maps.add(map6);
            maps.add(map7);

        } else if (isCurrentUserInRole(Workbook.ROLE_HEADMASTER)) {
            maps.add(map6);
            maps.add(map7);
        }

        return maps;
    }

    @Override
    public void deleteByUsername(String username) {
        create.deleteFrom(AUTHORITIES).where(AUTHORITIES.USERNAME.eq(username)).execute();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(Authorities authorities) {
        authoritiesDao.insert(authorities);
    }
}
