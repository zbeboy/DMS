package top.zbeboy.dms.web.data.college;

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
import top.zbeboy.dms.domain.dms.tables.pojos.College;
import top.zbeboy.dms.domain.dms.tables.records.CollegeRecord;
import top.zbeboy.dms.service.data.CollegeService;
import top.zbeboy.dms.web.bean.data.college.CollegeBean;
import top.zbeboy.dms.web.util.AjaxUtils;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class CollegeRestController {

    @Resource
    private CollegeService collegeService;

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/college/data")
    public BootstrapTableUtils<CollegeBean> collegeData(HttpServletRequest request) {
        BootstrapTableUtils<CollegeBean> bootstrapTableUtils = new BootstrapTableUtils<>(request);
        Result<Record> records = collegeService.findAllByPage(bootstrapTableUtils);
        List<CollegeBean> colleges = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            colleges = records.into(CollegeBean.class);
        }
        bootstrapTableUtils.setTotal(collegeService.countByCondition(bootstrapTableUtils));
        bootstrapTableUtils.setRows(colleges);
        return bootstrapTableUtils;
    }

    /**
     * 数据
     *
     * @return 数据
     */
    @PostMapping(value = "/web/data/college/one")
    public ResponseEntity<Map<String, Object>> college(@RequestParam("collegeId") int collegeId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        College college = collegeService.findByCollegeId(collegeId);
        ajaxUtils.success().msg("获取数据成功").put("college", college);
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 检验院名
     *
     * @param collegeName 院名
     * @return true or false
     */
    @PostMapping(value = "/web/data/college/check/add/name")
    public ResponseEntity<Map<String, Object>> checkAddName(@RequestParam("schoolId") int schoolId,
                                                            @RequestParam("collegeName") String collegeName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(collegeName);
        Result<CollegeRecord> colleges = collegeService.findBySchoolIdAndCollegeName(schoolId, name);
        if (Objects.isNull(colleges) || colleges.isEmpty()) {
            ajaxUtils.success().msg("院名未被使用");
        } else {
            ajaxUtils.fail().msg("院名已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 检验院名
     *
     * @param collegeId   院Id
     * @param schoolId    学校id
     * @param collegeName 院名
     * @return true or false
     */
    @PostMapping(value = "/web/data/college/check/update/name")
    public ResponseEntity<Map<String, Object>> checkUpdateName(@RequestParam("collegeId") int collegeId,
                                                               @RequestParam("schoolId") int schoolId,
                                                               @RequestParam("collegeName") String collegeName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(collegeName);
        Result<CollegeRecord> colleges = collegeService.findBySchoolIdAndCollegeNameNeCollegeId(collegeId, schoolId, name);
        if (Objects.isNull(colleges) || colleges.isEmpty()) {
            ajaxUtils.success().msg("院名未被使用");
        } else {
            ajaxUtils.fail().msg("院名已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 保存院
     *
     * @param collegeName 院名
     * @return true or false
     */
    @PostMapping(value = "/web/data/college/save")
    public ResponseEntity<Map<String, Object>> save(@RequestParam("schoolId") int schoolId,
                                                    @RequestParam("collegeName") String collegeName,
                                                    @RequestParam("collegeAddress") String collegeAddress) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(collegeName);
        College college = new College();
        college.setSchoolId(schoolId);
        college.setCollegeName(name);
        college.setCollegeAddress(collegeAddress);
        collegeService.save(college);
        return new ResponseEntity<>(ajaxUtils.success().msg("保存成功").send(), HttpStatus.OK);
    }

    /**
     * 更新
     *
     * @param collegeId      院id
     * @param schoolId       学校Id
     * @param collegeName    院名
     * @param collegeAddress 院地址
     * @return true or false
     */
    @PostMapping(value = "/web/data/college/update")
    public ResponseEntity<Map<String, Object>> update(@RequestParam("collegeId") int collegeId,
                                                      @RequestParam("schoolId") int schoolId,
                                                      @RequestParam("collegeName") String collegeName,
                                                      @RequestParam("collegeAddress") String collegeAddress) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(collegeName);
        College college = collegeService.findByCollegeId(collegeId);
        college.setSchoolId(schoolId);
        college.setCollegeName(name);
        college.setCollegeAddress(collegeAddress);
        collegeService.update(college);
        return new ResponseEntity<>(ajaxUtils.success().msg("更新成功").send(), HttpStatus.OK);
    }

    /**
     * 更新状态
     *
     * @param collegeId    院id
     * @param collegeIsDel 院状态
     * @return true or false
     */
    @PostMapping(value = "/web/data/college/status")
    public ResponseEntity<Map<String, Object>> update(@RequestParam("collegeId") int collegeId, @RequestParam("collegeIsDel") boolean collegeIsDel) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        College college = collegeService.findByCollegeId(collegeId);
        college.setCollegeIsDel(collegeIsDel);
        collegeService.update(college);
        return new ResponseEntity<>(ajaxUtils.success().msg("更新成功").send(), HttpStatus.OK);
    }
}
