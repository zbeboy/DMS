package top.zbeboy.dms.web.quality.examine;

import org.jooq.Record;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.zbeboy.dms.config.Workbook;
import top.zbeboy.dms.domain.dms.tables.pojos.QualityApply;
import top.zbeboy.dms.domain.dms.tables.pojos.Users;
import top.zbeboy.dms.service.data.StaffService;
import top.zbeboy.dms.service.platform.UsersService;
import top.zbeboy.dms.service.quality.QualityApplyService;
import top.zbeboy.dms.service.system.AuthoritiesService;
import top.zbeboy.dms.web.bean.data.staff.StaffBean;
import top.zbeboy.dms.web.util.AjaxUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
public class QualityExamineRestController {

    @Resource
    private QualityApplyService qualityApplyService;

    @Resource
    private UsersService usersService;

    @Resource
    private StaffService staffService;

    @Resource
    private AuthoritiesService authoritiesService;

    /**
     * 通过
     *
     * @param qualityApplyId 数据
     * @return true or false
     */
    @GetMapping(value = "/web/quality/examine/update/success/{qualityApplyId}")
    public ResponseEntity<Map<String, Object>> updateSuccess(@PathVariable("qualityApplyId") String qualityApplyId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        QualityApply qualityApply = qualityApplyService.findById(qualityApplyId);
        if (Objects.nonNull(qualityApply)) {
            int applyState = 0;
            Users users = usersService.getUserFromSession();
            Optional<Record> staffData = staffService.findByUsernameRelation(users.getUsername());
            if (staffData.isPresent()) {
                StaffBean staffBean = staffData.get().into(StaffBean.class);
                if (authoritiesService.isCurrentUserInRole(Workbook.ROLE_COLLEGE_YOUTH_LEAGUE_COMMITTEE)) {
                    applyState = 3;
                } else if (authoritiesService.isCurrentUserInRole(Workbook.ROLE_COLLEGE_WORK_DEPARTMENT)) {
                    applyState = 3;
                } else if (authoritiesService.isCurrentUserInRole(Workbook.ROLE_DEPARTMENT_INSTRUCTOR)) {
                    applyState = 2;
                } else if (authoritiesService.isCurrentUserInRole(Workbook.ROLE_HEADMASTER)) {
                    applyState = 1;
                }
            }
            qualityApply.setApplyState(applyState);
            qualityApplyService.update(qualityApply);
            ajaxUtils.success().msg("更新成功");
        } else {
            ajaxUtils.fail().msg("更新失败");
        }

        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 失败
     *
     * @param qualityApplyId 数据
     * @return true or false
     */
    @GetMapping(value = "/web/quality/examine/update/fail/{qualityApplyId}")
    public ResponseEntity<Map<String, Object>> updateFail(@PathVariable("qualityApplyId") String qualityApplyId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        QualityApply qualityApply = qualityApplyService.findById(qualityApplyId);
        if (Objects.nonNull(qualityApply)) {
            qualityApply.setApplyState(4);
            qualityApplyService.update(qualityApply);
            ajaxUtils.success().msg("更新成功");
        } else {
            ajaxUtils.fail().msg("更新失败");
        }

        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }
}
