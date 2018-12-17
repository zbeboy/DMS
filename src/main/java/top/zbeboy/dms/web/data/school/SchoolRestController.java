package top.zbeboy.dms.web.data.school;

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
import top.zbeboy.dms.domain.dms.tables.pojos.School;
import top.zbeboy.dms.domain.dms.tables.records.SchoolRecord;
import top.zbeboy.dms.service.data.SchoolService;
import top.zbeboy.dms.web.bean.data.school.SchoolBean;
import top.zbeboy.dms.web.util.AjaxUtils;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class SchoolRestController {

    @Resource
    private SchoolService schoolService;

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/school/data")
    public BootstrapTableUtils<SchoolBean> schoolData(HttpServletRequest request) {
        BootstrapTableUtils<SchoolBean> bootstrapTableUtils = new BootstrapTableUtils<>(request);
        Result<Record> records = schoolService.findAllByPage(bootstrapTableUtils);
        List<SchoolBean> schools = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            schools = records.into(SchoolBean.class);
        }
        bootstrapTableUtils.setTotal(schoolService.countByCondition(bootstrapTableUtils));
        bootstrapTableUtils.setRows(schools);
        return bootstrapTableUtils;
    }

    /**
     * 检验学校名
     *
     * @param schoolName 学校名
     * @return true or false
     */
    @PostMapping(value = "/web/data/school/check/add/name")
    public ResponseEntity<Map<String, Object>> checkAddName(@RequestParam("schoolName") String schoolName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(schoolName);
        List<School> schools = schoolService.findBySchoolName(name);
        if (Objects.isNull(schools) || schools.isEmpty()) {
            ajaxUtils.success().msg("学校名未被使用");
        } else {
            ajaxUtils.fail().msg("学校名已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 检验学校名
     *
     * @param schoolId   学校Id
     * @param schoolName 学校名
     * @return true or false
     */
    @PostMapping(value = "/web/data/school/check/update/name")
    public ResponseEntity<Map<String, Object>> checkUpdateName(@RequestParam("schoolId") int schoolId, @RequestParam("schoolName") String schoolName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(schoolName);
        Result<SchoolRecord> schools = schoolService.findBySchoolNameNeSchoolId(schoolId, name);
        if (Objects.isNull(schools) || schools.isEmpty()) {
            ajaxUtils.success().msg("学校名未被使用");
        } else {
            ajaxUtils.fail().msg("学校名已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 保存学校
     *
     * @param schoolName 学校名
     * @return true or false
     */
    @PostMapping(value = "/web/data/school/save")
    public ResponseEntity<Map<String, Object>> save(@RequestParam("schoolName") String schoolName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(schoolName);
        School school = new School();
        school.setSchoolIsDel(false);
        school.setSchoolName(name);
        schoolService.save(school);
        return new ResponseEntity<>(ajaxUtils.success().msg("保存成功").send(), HttpStatus.OK);
    }

    /**
     * 更新
     *
     * @param schoolId   学校Id
     * @param schoolName 学校名
     * @return true or false
     */
    @PostMapping(value = "/web/data/school/update")
    public ResponseEntity<Map<String, Object>> update(@RequestParam("schoolId") int schoolId, @RequestParam("schoolName") String schoolName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(schoolName);
        School school = schoolService.findBySchoolId(schoolId);
        school.setSchoolName(name);
        schoolService.update(school);
        return new ResponseEntity<>(ajaxUtils.success().msg("更新成功").send(), HttpStatus.OK);
    }

    /**
     * 更新状态
     *
     * @param schoolId    学校id
     * @param schoolIsDel 学校状态
     * @return true or false
     */
    @PostMapping(value = "/web/data/school/status")
    public ResponseEntity<Map<String, Object>> update(@RequestParam("schoolId") int schoolId, @RequestParam("schoolIsDel") boolean schoolIsDel) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        School school = schoolService.findBySchoolId(schoolId);
        school.setSchoolIsDel(schoolIsDel);
        schoolService.update(school);
        return new ResponseEntity<>(ajaxUtils.success().msg("更新成功").send(), HttpStatus.OK);
    }
}
