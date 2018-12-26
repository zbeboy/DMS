package top.zbeboy.dms.web.data.grade;

import org.jooq.Record;
import org.jooq.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zbeboy.dms.domain.dms.tables.pojos.Grade;
import top.zbeboy.dms.domain.dms.tables.records.GradeRecord;
import top.zbeboy.dms.service.data.GradeService;
import top.zbeboy.dms.web.bean.data.grade.GradeBean;
import top.zbeboy.dms.web.util.AjaxUtils;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class GradeRestController {

    @Resource
    private GradeService gradeService;

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/grade/data")
    public BootstrapTableUtils<GradeBean> gradeData(HttpServletRequest request) {
        BootstrapTableUtils<GradeBean> bootstrapTableUtils = new BootstrapTableUtils<>(request);
        Result<Record> records = gradeService.findAllByPage(bootstrapTableUtils);
        List<GradeBean> grades = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            grades = records.into(GradeBean.class);
        }
        bootstrapTableUtils.setTotal(gradeService.countByCondition(bootstrapTableUtils));
        bootstrapTableUtils.setRows(grades);
        return bootstrapTableUtils;
    }

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/grade/all")
    public ResponseEntity<Map<String, Object>> gradeAll(@RequestParam("scienceId") int scienceId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Result<GradeRecord> records = gradeService.findByGradeIsDelAndScienceId(false, scienceId);
        List<GradeBean> grades = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            grades = records.into(GradeBean.class);
        }
        ajaxUtils.success().msg("获取数据成功").put("grades", grades);
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 数据
     *
     * @return 数据
     */
    @PostMapping(value = "/web/data/grade/one")
    public ResponseEntity<Map<String, Object>> grade(@RequestParam("gradeId") int gradeId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Optional<Record> record = gradeService.findByGradeIdRelation(gradeId);
        GradeBean grade = new GradeBean();
        if (record.isPresent()) {
            grade = record.get().into(GradeBean.class);
        }
        ajaxUtils.success().msg("获取数据成功").put("grade", grade);
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 检验年级
     *
     * @param grade 年级
     * @return true or false
     */
    @PostMapping(value = "/web/data/grade/check/add/name")
    public ResponseEntity<Map<String, Object>> checkAddName(@RequestParam("scienceId") int scienceId,
                                                            @RequestParam("grade") String grade) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(grade);
        Result<GradeRecord> grades = gradeService.findByScienceIdAndGrade(scienceId, name);
        if (Objects.isNull(grades) || grades.isEmpty()) {
            ajaxUtils.success().msg("年级未被使用");
        } else {
            ajaxUtils.fail().msg("年级已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 检验年级
     *
     * @param gradeId   年级Id
     * @param scienceId 专业id
     * @param grade     年级
     * @return true or false
     */
    @PostMapping(value = "/web/data/grade/check/update/name")
    public ResponseEntity<Map<String, Object>> checkUpdateName(@RequestParam("gradeId") int gradeId,
                                                               @RequestParam("scienceId") int scienceId,
                                                               @RequestParam("grade") String grade) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(grade);
        Result<GradeRecord> grades = gradeService.findByScienceIdAndGradeNeGradeId(gradeId, scienceId, name);
        if (Objects.isNull(grades) || grades.isEmpty()) {
            ajaxUtils.success().msg("年级未被使用");
        } else {
            ajaxUtils.fail().msg("年级已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 保存年级
     *
     * @param grade 年级
     * @return true or false
     */
    @PostMapping(value = "/web/data/grade/save")
    public ResponseEntity<Map<String, Object>> save(@RequestParam("scienceId") int scienceId,
                                                    @RequestParam("grade") String grade) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(grade);
        Grade gradeData = new Grade();
        gradeData.setScienceId(scienceId);
        gradeData.setGrade(name);
        gradeService.save(gradeData);
        return new ResponseEntity<>(ajaxUtils.success().msg("保存成功").send(), HttpStatus.OK);
    }

    /**
     * 更新
     *
     * @param gradeId   年级id
     * @param scienceId 专业Id
     * @param grade     年级
     * @return true or false
     */
    @PostMapping(value = "/web/data/grade/update")
    public ResponseEntity<Map<String, Object>> update(@RequestParam("gradeId") int gradeId,
                                                      @RequestParam("scienceId") int scienceId,
                                                      @RequestParam("grade") String grade) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(grade);
        Grade gradeData = gradeService.findByGradeId(gradeId);
        gradeData.setScienceId(scienceId);
        gradeData.setGrade(name);
        gradeService.update(gradeData);
        return new ResponseEntity<>(ajaxUtils.success().msg("更新成功").send(), HttpStatus.OK);
    }

    /**
     * 更新状态
     *
     * @param gradeId    id
     * @param gradeIsDel 状态
     * @return true or false
     */
    @PostMapping(value = "/web/data/grade/status")
    public ResponseEntity<Map<String, Object>> update(@RequestParam("gradeId") int gradeId, @RequestParam("gradeIsDel") boolean gradeIsDel) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Grade gradeData = gradeService.findByGradeId(gradeId);
        gradeData.setGradeIsDel(gradeIsDel);
        gradeService.update(gradeData);
        return new ResponseEntity<>(ajaxUtils.success().msg("更新成功").send(), HttpStatus.OK);
    }
}
