package top.zbeboy.dms.web.data.organize;

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
import top.zbeboy.dms.domain.dms.tables.pojos.Organize;
import top.zbeboy.dms.domain.dms.tables.records.OrganizeRecord;
import top.zbeboy.dms.service.data.OrganizeService;
import top.zbeboy.dms.web.bean.data.organize.OrganizeBean;
import top.zbeboy.dms.web.util.AjaxUtils;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class OrganizeRestController {

    @Resource
    private OrganizeService organizeService;

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/organize/data")
    public BootstrapTableUtils<OrganizeBean> organizeData(HttpServletRequest request) {
        BootstrapTableUtils<OrganizeBean> bootstrapTableUtils = new BootstrapTableUtils<>(request);
        Result<Record> records = organizeService.findAllByPage(bootstrapTableUtils);
        List<OrganizeBean> organizes = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            organizes = records.into(OrganizeBean.class);
        }
        bootstrapTableUtils.setTotal(organizeService.countByCondition(bootstrapTableUtils));
        bootstrapTableUtils.setRows(organizes);
        return bootstrapTableUtils;
    }

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/organize/all")
    public ResponseEntity<Map<String, Object>> organizeAll(@RequestParam("gradeId") int gradeId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Result<OrganizeRecord> records = organizeService.findByOrganizeIsDelAndGradeId(false, gradeId);
        List<OrganizeBean> organizes = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            organizes = records.into(OrganizeBean.class);
        }
        ajaxUtils.success().msg("获取数据成功").put("organizes", organizes);
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 数据
     *
     * @return 数据
     */
    @PostMapping(value = "/web/data/organize/one")
    public ResponseEntity<Map<String, Object>> organize(@RequestParam("organizeId") int organizeId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Optional<Record> record = organizeService.findByOrganizeIdRelation(organizeId);
        OrganizeBean organize = new OrganizeBean();
        if (record.isPresent()) {
            organize = record.get().into(OrganizeBean.class);
        }
        ajaxUtils.success().msg("获取数据成功").put("organize", organize);
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 检验班级
     *
     * @param organizeName 班级
     * @return true or false
     */
    @PostMapping(value = "/web/data/organize/check/add/name")
    public ResponseEntity<Map<String, Object>> checkAddName(@RequestParam("gradeId") int gradeId,
                                                            @RequestParam("organizeName") String organizeName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(organizeName);
        Result<OrganizeRecord> organizes = organizeService.findByGradeIdAndOrganizeName(gradeId, name);
        if (Objects.isNull(organizes) || organizes.isEmpty()) {
            ajaxUtils.success().msg("班级未被使用");
        } else {
            ajaxUtils.fail().msg("班级已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 检验班级
     *
     * @param organizeId   班级Id
     * @param gradeId      年级id
     * @param organizeName 班级
     * @return true or false
     */
    @PostMapping(value = "/web/data/organize/check/update/name")
    public ResponseEntity<Map<String, Object>> checkUpdateName(@RequestParam("organizeId") int organizeId,
                                                               @RequestParam("gradeId") int gradeId,
                                                               @RequestParam("organizeName") String organizeName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(organizeName);
        Result<OrganizeRecord> organizes = organizeService.findByGradeIdAndOrganizeNameNeOrganizeId(organizeId, gradeId, name);
        if (Objects.isNull(organizes) || organizes.isEmpty()) {
            ajaxUtils.success().msg("班级未被使用");
        } else {
            ajaxUtils.fail().msg("班级已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 保存班级
     *
     * @param organizeName 班级
     * @return true or false
     */
    @PostMapping(value = "/web/data/organize/save")
    public ResponseEntity<Map<String, Object>> save(@RequestParam("gradeId") int gradeId, int staffId,
                                                    @RequestParam("organizeName") String organizeName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(organizeName);
        Organize organize = new Organize();
        organize.setGradeId(gradeId);
        organize.setOrganizeName(name);
        organize.setStaffId(staffId);
        organize.setOrganizeIsDel(false);
        organizeService.save(organize);
        return new ResponseEntity<>(ajaxUtils.success().msg("保存成功").send(), HttpStatus.OK);
    }

    /**
     * 更新
     *
     * @param organizeId   班级id
     * @param gradeId      年级Id
     * @param organizeName 班级
     * @return true or false
     */
    @PostMapping(value = "/web/data/organize/update")
    public ResponseEntity<Map<String, Object>> update(@RequestParam("organizeId") int organizeId,
                                                      @RequestParam("gradeId") int gradeId,int staffId,
                                                      @RequestParam("organizeName") String organizeName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(organizeName);
        Organize organize = organizeService.findByOrganizeId(organizeId);
        organize.setGradeId(gradeId);
        organize.setStaffId(staffId);
        organize.setOrganizeName(name);
        organizeService.update(organize);
        return new ResponseEntity<>(ajaxUtils.success().msg("更新成功").send(), HttpStatus.OK);
    }

    /**
     * 更新状态
     *
     * @param organizeId    id
     * @param organizeIsDel 状态
     * @return true or false
     */
    @PostMapping(value = "/web/data/organize/status")
    public ResponseEntity<Map<String, Object>> update(@RequestParam("organizeId") int organizeId, @RequestParam("organizeIsDel") boolean organizeIsDel) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Organize organize = organizeService.findByOrganizeId(organizeId);
        organize.setOrganizeIsDel(organizeIsDel);
        organizeService.update(organize);
        return new ResponseEntity<>(ajaxUtils.success().msg("更新成功").send(), HttpStatus.OK);
    }
}
