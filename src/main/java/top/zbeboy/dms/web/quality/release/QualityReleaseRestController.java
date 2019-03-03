package top.zbeboy.dms.web.quality.release;

import org.jooq.Record;
import org.jooq.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zbeboy.dms.domain.dms.tables.pojos.QualityRelease;
import top.zbeboy.dms.domain.dms.tables.pojos.Users;
import top.zbeboy.dms.service.platform.UsersService;
import top.zbeboy.dms.service.quality.QualityReleaseService;
import top.zbeboy.dms.service.util.DateTimeUtils;
import top.zbeboy.dms.service.util.UUIDUtil;
import top.zbeboy.dms.web.bean.quality.QualityReleaseBean;
import top.zbeboy.dms.web.util.AjaxUtils;
import top.zbeboy.dms.web.util.BootstrapTableUtils;
import top.zbeboy.dms.web.vo.quality.release.QualityReleaseAddVo;
import top.zbeboy.dms.web.vo.quality.release.QualityReleaseEditVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class QualityReleaseRestController {

    @Resource
    private QualityReleaseService qualityReleaseService;

    @Resource
    private UsersService usersService;

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/quality/release/data")
    public BootstrapTableUtils<QualityReleaseBean> releaseData(HttpServletRequest request) {
        BootstrapTableUtils<QualityReleaseBean> bootstrapTableUtils = new BootstrapTableUtils<>(request);
        Result<Record> records = qualityReleaseService.findAllByPage(bootstrapTableUtils);
        List<QualityReleaseBean> qualityReleases = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            qualityReleases = records.into(QualityReleaseBean.class);
            qualityReleases.forEach(i -> i.setReleaseDateStr(DateTimeUtils.defaultFormatSqlTimestamp(i.getReleaseDate())));
        }
        bootstrapTableUtils.setTotal(qualityReleaseService.countByCondition(bootstrapTableUtils));
        bootstrapTableUtils.setRows(qualityReleases);
        return bootstrapTableUtils;
    }

    /**
     * 保存
     *
     * @param qualityReleaseAddVo 数据
     * @return true or false
     */
    @PostMapping(value = "/web/quality/release/save")
    public ResponseEntity<Map<String, Object>> save(@Valid QualityReleaseAddVo qualityReleaseAddVo, BindingResult bindingResult) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        if (!bindingResult.hasErrors()) {
            Users users = usersService.getUserFromSession();
            QualityRelease qualityRelease = new QualityRelease();
            qualityRelease.setQualityReleaseId(UUIDUtil.getUUID());
            qualityRelease.setReleaseTitle(qualityReleaseAddVo.getReleaseTitle());
            qualityRelease.setYear(qualityReleaseAddVo.getYear());
            qualityRelease.setTerm(qualityReleaseAddVo.getTerm());
            qualityRelease.setReleaseDate(DateTimeUtils.getNowSqlTimestamp());
            qualityRelease.setUsername(users.getUsername());
            qualityReleaseService.save(qualityRelease);

            ajaxUtils.success().msg("保存成功");
        } else {
            ajaxUtils.fail().msg(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 更新
     *
     * @param qualityReleaseEditVo 数据
     * @return true or false
     */
    @PostMapping(value = "/web/quality/release/update")
    public ResponseEntity<Map<String, Object>> update(@Valid QualityReleaseEditVo qualityReleaseEditVo, BindingResult bindingResult) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        if (!bindingResult.hasErrors()) {
            QualityRelease qualityRelease = qualityReleaseService.findById(qualityReleaseEditVo.getQualityReleaseId());
            qualityRelease.setReleaseTitle(qualityReleaseEditVo.getReleaseTitle());
            qualityRelease.setYear(qualityReleaseEditVo.getYear());
            qualityRelease.setTerm(qualityReleaseEditVo.getTerm());
            qualityReleaseService.update(qualityRelease);

            ajaxUtils.success().msg("更新成功");
        } else {
            ajaxUtils.fail().msg(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }
}
