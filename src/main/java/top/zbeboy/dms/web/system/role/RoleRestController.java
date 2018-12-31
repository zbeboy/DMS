package top.zbeboy.dms.web.system.role;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zbeboy.dms.service.system.AuthoritiesService;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class RoleRestController {

    @Resource
    private AuthoritiesService authoritiesService;

    /**
     * 角色数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/system/role/data")
    public BootstrapTableUtils<Map<String, String>> roles(HttpServletRequest request) {
        BootstrapTableUtils<Map<String, String>> bootstrapTableUtils = new BootstrapTableUtils<>(request);
        bootstrapTableUtils.setTotal(authoritiesService.getAuthAll().size());
        bootstrapTableUtils.setRows(authoritiesService.getAuthAll());
        return bootstrapTableUtils;
    }
}
