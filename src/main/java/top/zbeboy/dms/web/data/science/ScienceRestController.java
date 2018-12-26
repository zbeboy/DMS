package top.zbeboy.dms.web.data.science;

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
import top.zbeboy.dms.domain.dms.tables.pojos.Science;
import top.zbeboy.dms.domain.dms.tables.records.ScienceRecord;
import top.zbeboy.dms.service.data.ScienceService;
import top.zbeboy.dms.web.bean.data.science.ScienceBean;
import top.zbeboy.dms.web.util.AjaxUtils;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class ScienceRestController {

    @Resource
    private ScienceService scienceService;

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/science/data")
    public BootstrapTableUtils<ScienceBean> scienceData(HttpServletRequest request) {
        BootstrapTableUtils<ScienceBean> bootstrapTableUtils = new BootstrapTableUtils<>(request);
        Result<Record> records = scienceService.findAllByPage(bootstrapTableUtils);
        List<ScienceBean> sciences = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            sciences = records.into(ScienceBean.class);
        }
        bootstrapTableUtils.setTotal(scienceService.countByCondition(bootstrapTableUtils));
        bootstrapTableUtils.setRows(sciences);
        return bootstrapTableUtils;
    }

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/science/all")
    public ResponseEntity<Map<String, Object>> scienceAll(@RequestParam("departmentId") int departmentId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Result<ScienceRecord> records = scienceService.findByScienceIsDelAndDepartmentId(false, departmentId);
        List<ScienceBean> sciences = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            sciences = records.into(ScienceBean.class);
        }
        ajaxUtils.success().msg("获取数据成功").put("sciences", sciences);
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 数据
     *
     * @return 数据
     */
    @PostMapping(value = "/web/data/science/one")
    public ResponseEntity<Map<String, Object>> science(@RequestParam("scienceId") int scienceId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Optional<Record> record = scienceService.findByScienceIdRelation(scienceId);
        ScienceBean science = new ScienceBean();
        if (record.isPresent()) {
            science = record.get().into(ScienceBean.class);
        }
        ajaxUtils.success().msg("获取数据成功").put("science", science);
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 检验专业名
     *
     * @param scienceName 专业名
     * @return true or false
     */
    @PostMapping(value = "/web/data/science/check/add/name")
    public ResponseEntity<Map<String, Object>> checkAddName(@RequestParam("departmentId") int departmentId,
                                                            @RequestParam("scienceName") String scienceName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(scienceName);
        Result<ScienceRecord> sciences = scienceService.findByDepartmentIdAndScienceName(departmentId, name);
        if (Objects.isNull(sciences) || sciences.isEmpty()) {
            ajaxUtils.success().msg("专业名未被使用");
        } else {
            ajaxUtils.fail().msg("专业名已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 检验专业名
     *
     * @param scienceId    专业Id
     * @param departmentId 系id
     * @param scienceName  专业名
     * @return true or false
     */
    @PostMapping(value = "/web/data/science/check/update/name")
    public ResponseEntity<Map<String, Object>> checkUpdateName(@RequestParam("scienceId") int scienceId,
                                                               @RequestParam("departmentId") int departmentId,
                                                               @RequestParam("scienceName") String scienceName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(scienceName);
        Result<ScienceRecord> sciences = scienceService.findByDepartmentIdAndScienceNameNeScienceId(scienceId, departmentId, name);
        if (Objects.isNull(sciences) || sciences.isEmpty()) {
            ajaxUtils.success().msg("专业名未被使用");
        } else {
            ajaxUtils.fail().msg("专业名已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 保存专业
     *
     * @param scienceName 专业名
     * @return true or false
     */
    @PostMapping(value = "/web/data/science/save")
    public ResponseEntity<Map<String, Object>> save(@RequestParam("departmentId") int departmentId,
                                                    @RequestParam("scienceName") String scienceName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(scienceName);
        Science science = new Science();
        science.setDepartmentId(departmentId);
        science.setScienceName(name);
        scienceService.save(science);
        return new ResponseEntity<>(ajaxUtils.success().msg("保存成功").send(), HttpStatus.OK);
    }

    /**
     * 更新
     *
     * @param scienceId    专业id
     * @param departmentId 系Id
     * @param scienceName  专业名
     * @return true or false
     */
    @PostMapping(value = "/web/data/science/update")
    public ResponseEntity<Map<String, Object>> update(@RequestParam("scienceId") int scienceId,
                                                      @RequestParam("departmentId") int departmentId,
                                                      @RequestParam("scienceName") String scienceName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(scienceName);
        Science science = scienceService.findByScienceId(scienceId);
        science.setDepartmentId(departmentId);
        science.setScienceName(name);
        scienceService.update(science);
        return new ResponseEntity<>(ajaxUtils.success().msg("更新成功").send(), HttpStatus.OK);
    }

    /**
     * 更新状态
     *
     * @param scienceId    id
     * @param scienceIsDel 状态
     * @return true or false
     */
    @PostMapping(value = "/web/data/science/status")
    public ResponseEntity<Map<String, Object>> update(@RequestParam("scienceId") int scienceId, @RequestParam("scienceIsDel") boolean scienceIsDel) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Science science = scienceService.findByScienceId(scienceId);
        science.setScienceIsDel(scienceIsDel);
        scienceService.update(science);
        return new ResponseEntity<>(ajaxUtils.success().msg("更新成功").send(), HttpStatus.OK);
    }
}
