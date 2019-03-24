package top.zbeboy.dms.web.quality.apply;

import org.jooq.Record;
import org.jooq.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zbeboy.dms.domain.dms.tables.pojos.QualityApply;
import top.zbeboy.dms.service.quality.QualityApplyService;
import top.zbeboy.dms.service.util.DateTimeUtils;
import top.zbeboy.dms.service.util.UUIDUtil;
import top.zbeboy.dms.web.bean.quality.QualityApplyBean;
import top.zbeboy.dms.web.util.AjaxUtils;
import top.zbeboy.dms.web.util.BootstrapTableUtils;
import top.zbeboy.dms.web.vo.quality.apply.QualityApplyAddVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class QualityApplyRestController {

    @Resource
    private QualityApplyService qualityApplyService;

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/quality/apply/data")
    public BootstrapTableUtils<QualityApplyBean> applyData(HttpServletRequest request) {
        BootstrapTableUtils<QualityApplyBean> bootstrapTableUtils = new BootstrapTableUtils<>(request);
        Result<Record> records = qualityApplyService.findAllByPage(bootstrapTableUtils);
        List<QualityApplyBean> qualityApplies = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            qualityApplies = records.into(QualityApplyBean.class);
            qualityApplies.forEach(i -> i.setApplyDateStr(DateTimeUtils.defaultFormatSqlTimestamp(i.getApplyDate())));
        }
        bootstrapTableUtils.setTotal(qualityApplyService.countByCondition(bootstrapTableUtils));
        bootstrapTableUtils.setRows(qualityApplies);
        return bootstrapTableUtils;
    }

    /**
     * 保存
     *
     * @param qualityApplyAddVo 数据
     * @return true or false
     */
    @PostMapping(value = "/web/quality/apply/save")
    public ResponseEntity<Map<String, Object>> save(@Valid QualityApplyAddVo qualityApplyAddVo, BindingResult bindingResult) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        if (!bindingResult.hasErrors()) {
            QualityApply qualityApply = new QualityApply();
            qualityApply.setQualityApplyId(UUIDUtil.getUUID());
            qualityApply.setApplyContent(qualityApplyAddVo.getApplyContent());
            qualityApply.setStudentId(qualityApplyAddVo.getStudentId());
            qualityApply.setQualityReleaseId(qualityApplyAddVo.getQualityReleaseId());
            qualityApply.setApplyState(0);
            qualityApply.setApplyDate(DateTimeUtils.getNowSqlTimestamp());
            qualityApplyService.save(qualityApply);

            ajaxUtils.success().msg("保存成功");
        } else {
            ajaxUtils.fail().msg(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 删除
     *
     * @param qualityApplyId 数据
     * @return true or false
     */
    @GetMapping(value = "/web/quality/apply/delete/{qualityApplyId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("qualityApplyId") String qualityApplyId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        QualityApply qualityApply = qualityApplyService.findById(qualityApplyId);
        if (Objects.nonNull(qualityApply)) {
            int applyState = qualityApply.getApplyState();
            if (applyState == 0) {
                qualityApplyService.deleteById(qualityApplyId);
                ajaxUtils.success().msg("删除成功");
            } else {
                ajaxUtils.fail().msg("当前状态不允许删除");
            }
        } else {
            ajaxUtils.fail().msg("查询数据失败");
        }

        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }
}
