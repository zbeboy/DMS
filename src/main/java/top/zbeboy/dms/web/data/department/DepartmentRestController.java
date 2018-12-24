package top.zbeboy.dms.web.data.department;

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
import top.zbeboy.dms.domain.dms.tables.pojos.Department;
import top.zbeboy.dms.domain.dms.tables.records.CollegeRecord;
import top.zbeboy.dms.domain.dms.tables.records.DepartmentRecord;
import top.zbeboy.dms.service.data.DepartmentService;
import top.zbeboy.dms.web.bean.data.college.CollegeBean;
import top.zbeboy.dms.web.bean.data.department.DepartmentBean;
import top.zbeboy.dms.web.util.AjaxUtils;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class DepartmentRestController {

    @Resource
    private DepartmentService departmentService;

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/department/data")
    public BootstrapTableUtils<DepartmentBean> departmentData(HttpServletRequest request) {
        BootstrapTableUtils<DepartmentBean> bootstrapTableUtils = new BootstrapTableUtils<>(request);
        Result<Record> records = departmentService.findAllByPage(bootstrapTableUtils);
        List<DepartmentBean> departments = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            departments = records.into(DepartmentBean.class);
        }
        bootstrapTableUtils.setTotal(departmentService.countByCondition(bootstrapTableUtils));
        bootstrapTableUtils.setRows(departments);
        return bootstrapTableUtils;
    }

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/department/all")
    public ResponseEntity<Map<String, Object>> departmentAll(@RequestParam("collegeId") int collegeId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Result<DepartmentRecord> records = departmentService.findByDepartmentIsDelAndCollegeId(false, collegeId);
        List<DepartmentBean> departments = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            departments = records.into(DepartmentBean.class);
        }
        ajaxUtils.success().msg("获取数据成功").put("departments", departments);
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 数据
     *
     * @return 数据
     */
    @PostMapping(value = "/web/data/department/one")
    public ResponseEntity<Map<String, Object>> department(@RequestParam("departmentId") int departmentId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Optional<Record> record = departmentService.findByDepartmentIdRelation(departmentId);
        DepartmentBean department = new DepartmentBean();
        if (record.isPresent()) {
            department = record.get().into(DepartmentBean.class);
        }
        ajaxUtils.success().msg("获取数据成功").put("department", department);
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 检验系名
     *
     * @param departmentName 系名
     * @return true or false
     */
    @PostMapping(value = "/web/data/department/check/add/name")
    public ResponseEntity<Map<String, Object>> checkAddName(@RequestParam("collegeId") int collegeId,
                                                            @RequestParam("departmentName") String departmentName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(departmentName);
        Result<DepartmentRecord> departments = departmentService.findByCollegeIdAndDepartmentName(collegeId, name);
        if (Objects.isNull(departments) || departments.isEmpty()) {
            ajaxUtils.success().msg("系名未被使用");
        } else {
            ajaxUtils.fail().msg("系名已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 检验系名
     *
     * @param departmentId   系Id
     * @param collegeId      院id
     * @param departmentName 系名
     * @return true or false
     */
    @PostMapping(value = "/web/data/department/check/update/name")
    public ResponseEntity<Map<String, Object>> checkUpdateName(@RequestParam("departmentId") int departmentId,
                                                               @RequestParam("collegeId") int collegeId,
                                                               @RequestParam("departmentName") String departmentName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(departmentName);
        Result<DepartmentRecord> departments = departmentService.findByCollegeIdAndDepartmentNameNeDepartmentId(departmentId, collegeId, name);
        if (Objects.isNull(departments) || departments.isEmpty()) {
            ajaxUtils.success().msg("系名未被使用");
        } else {
            ajaxUtils.fail().msg("系名已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 保存系
     *
     * @param departmentName 系名
     * @return true or false
     */
    @PostMapping(value = "/web/data/department/save")
    public ResponseEntity<Map<String, Object>> save(@RequestParam("collegeId") int collegeId,
                                                    @RequestParam("departmentName") String departmentName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(departmentName);
        Department department = new Department();
        department.setCollegeId(collegeId);
        department.setDepartmentName(name);
        departmentService.save(department);
        return new ResponseEntity<>(ajaxUtils.success().msg("保存成功").send(), HttpStatus.OK);
    }

    /**
     * 更新
     *
     * @param departmentId   系id
     * @param collegeId      院Id
     * @param departmentName 系名
     * @return true or false
     */
    @PostMapping(value = "/web/data/department/update")
    public ResponseEntity<Map<String, Object>> update(@RequestParam("departmentId") int departmentId,
                                                      @RequestParam("collegeId") int collegeId,
                                                      @RequestParam("departmentName") String departmentName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(departmentName);
        Department department = departmentService.findByDepartmentId(departmentId);
        department.setCollegeId(collegeId);
        department.setDepartmentName(name);
        departmentService.update(department);
        return new ResponseEntity<>(ajaxUtils.success().msg("更新成功").send(), HttpStatus.OK);
    }

    /**
     * 更新状态
     *
     * @param departmentId    id
     * @param departmentIsDel 状态
     * @return true or false
     */
    @PostMapping(value = "/web/data/department/status")
    public ResponseEntity<Map<String, Object>> update(@RequestParam("departmentId") int departmentId, @RequestParam("departmentIsDel") boolean departmentIsDel) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Department department = departmentService.findByDepartmentId(departmentId);
        department.setDepartmentIsDel(departmentIsDel);
        departmentService.update(department);
        return new ResponseEntity<>(ajaxUtils.success().msg("更新成功").send(), HttpStatus.OK);
    }
}
