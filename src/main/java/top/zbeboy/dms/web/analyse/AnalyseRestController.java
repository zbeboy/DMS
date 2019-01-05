package top.zbeboy.dms.web.analyse;

import org.jooq.Record;
import org.jooq.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zbeboy.dms.domain.dms.tables.pojos.Credit;
import top.zbeboy.dms.service.analyse.CreditService;
import top.zbeboy.dms.web.bean.analyse.CreditBean;
import top.zbeboy.dms.web.util.AjaxUtils;
import top.zbeboy.dms.web.util.BootstrapTableUtils;
import top.zbeboy.dms.web.vo.analyse.CreditAddVo;
import top.zbeboy.dms.web.vo.analyse.CreditEditVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class AnalyseRestController {

    @Resource
    private CreditService creditService;

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/analyse/data")
    public BootstrapTableUtils<CreditBean> creditData(HttpServletRequest request) {
        BootstrapTableUtils<CreditBean> bootstrapTableUtils = new BootstrapTableUtils<>(request);
        Result<Record> records = creditService.findAllByPage(bootstrapTableUtils);
        List<CreditBean> credits = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            credits = records.into(CreditBean.class);
        }
        bootstrapTableUtils.setTotal(creditService.countByCondition(bootstrapTableUtils));
        bootstrapTableUtils.setRows(credits);
        return bootstrapTableUtils;
    }

    /**
     * 数据
     *
     * @return 数据
     */
    @PostMapping(value = "/web/analyse/one")
    public ResponseEntity<Map<String, Object>> credit(@RequestParam("creditId") int creditId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Credit credit = creditService.findByCreditId(creditId);
        ajaxUtils.success().msg("获取数据成功").put("analyse", credit);
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 保存
     *
     * @param creditAddVo 分析
     * @return true or false
     */
    @PostMapping(value = "/web/analyse/save")
    public ResponseEntity<Map<String, Object>> save(@Valid CreditAddVo creditAddVo, BindingResult bindingResult) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        if (!bindingResult.hasErrors()) {
            Credit credit = new Credit();
            credit.setStudentNumber(creditAddVo.getStudentNumber());
            credit.setYear(creditAddVo.getYear());
            credit.setTerm(creditAddVo.getTerm());
            credit.setSports(creditAddVo.getSports());
            credit.setSkills(creditAddVo.getSkills());
            credit.setVoluntary(creditAddVo.getVoluntary());
            credit.setTechnological(creditAddVo.getTechnological());
            credit.setPost(creditAddVo.getPost());
            credit.setIdeological(creditAddVo.getIdeological());
            credit.setPractical(creditAddVo.getPractical());
            credit.setWork(creditAddVo.getWork());
            credit.setAchievement(creditAddVo.getAchievement());
            creditService.save(credit);

            ajaxUtils.success().msg("保存成功");
        } else {
            ajaxUtils.fail().msg(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 更新
     *
     * @return true or false
     */
    @PostMapping(value = "/web/analyse/update")
    public ResponseEntity<Map<String, Object>> update(@Valid CreditEditVo creditEditVo, BindingResult bindingResult) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        if (!bindingResult.hasErrors()) {
            Credit credit = creditService.findByCreditId(creditEditVo.getCreditId());
            credit.setStudentNumber(creditEditVo.getStudentNumber());
            credit.setYear(creditEditVo.getYear());
            credit.setTerm(creditEditVo.getTerm());
            credit.setSports(creditEditVo.getSports());
            credit.setSkills(creditEditVo.getSkills());
            credit.setVoluntary(creditEditVo.getVoluntary());
            credit.setTechnological(creditEditVo.getTechnological());
            credit.setPost(creditEditVo.getPost());
            credit.setIdeological(creditEditVo.getIdeological());
            credit.setPractical(creditEditVo.getPractical());
            credit.setWork(creditEditVo.getWork());
            credit.setAchievement(creditEditVo.getAchievement());
            creditService.update(credit);

            ajaxUtils.success().msg("更新成功");
        } else {
            ajaxUtils.fail().msg(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 删除
     *
     * @param creditId 学分id
     * @return true or false
     */
    @PostMapping(value = "/web/analyse/delete")
    public ResponseEntity<Map<String, Object>> delete(@RequestParam("creditId") int creditId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        creditService.deleteById(creditId);
        return new ResponseEntity<>(ajaxUtils.success().msg("删除成功").send(), HttpStatus.OK);
    }
}
