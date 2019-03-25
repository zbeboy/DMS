package top.zbeboy.dms.web.analyse;

import org.jooq.Record;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.zbeboy.dms.config.Workbook;
import top.zbeboy.dms.domain.dms.tables.pojos.Credit;
import top.zbeboy.dms.domain.dms.tables.pojos.Diploma;
import top.zbeboy.dms.domain.dms.tables.pojos.Wining;
import top.zbeboy.dms.service.analyse.CreditService;
import top.zbeboy.dms.service.analyse.DiplomaService;
import top.zbeboy.dms.service.analyse.WiningService;
import top.zbeboy.dms.service.common.UploadService;
import top.zbeboy.dms.service.data.StudentService;
import top.zbeboy.dms.service.entry.CreditReadExcel;
import top.zbeboy.dms.service.export.CreditDataExport;
import top.zbeboy.dms.service.util.RequestUtils;
import top.zbeboy.dms.web.bean.analyse.CreditBean;
import top.zbeboy.dms.web.bean.data.student.StudentBean;
import top.zbeboy.dms.web.bean.file.FileBean;
import top.zbeboy.dms.web.util.AjaxUtils;
import top.zbeboy.dms.web.util.BootstrapTableUtils;
import top.zbeboy.dms.web.vo.analyse.CreditAddVo;
import top.zbeboy.dms.web.vo.analyse.CreditEditVo;
import top.zbeboy.dms.web.vo.analyse.DiplomaAddVo;
import top.zbeboy.dms.web.vo.analyse.WiningAddVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
public class AnalyseRestController {

    private final Logger log = LoggerFactory.getLogger(AnalyseRestController.class);

    @Resource
    private CreditService creditService;

    @Resource
    private StudentService studentService;

    @Resource
    private WiningService winingService;

    @Resource
    private DiplomaService diplomaService;

    @Resource
    private UploadService uploadService;

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
     * 数据导出
     */
    @GetMapping(value = "/web/analyse/export")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        try {
            BootstrapTableUtils<CreditBean> bootstrapTableUtils = new BootstrapTableUtils<>(request);
            Result<Record> records = creditService.export(bootstrapTableUtils);
            List<CreditBean> credits = new ArrayList<>();
            if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
                credits = records.into(CreditBean.class);
            }
            String fileName = "学分数据";
            String ext = Workbook.XLSX_FILE;
            CreditDataExport export = new CreditDataExport(credits);
            String path = Workbook.creditFilePath() + fileName + "." + ext;
            export.exportExcel(RequestUtils.getRealPath(request) + Workbook.creditFilePath(), fileName, ext);
            uploadService.download(fileName, path, response, request);
        } catch (IOException e) {
            log.error("Export file error, error is {}", e);
        }
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
        double[] credits = new double[]{credit.getSports(), credit.getSkills(), credit.getVoluntary(), credit.getTechnological(),
                credit.getPost(), credit.getIdeological(), credit.getPractical(), credit.getWork(), credit.getAchievement(), credit.getIntellectual()};


        StudentBean student = new StudentBean();
        Optional<Record> data = studentService.findByStudentNumberRelation(credit.getStudentNumber());
        if (data.isPresent()) {
            student = data.get().into(StudentBean.class);
        }

        List<Wining> winings = winingService.findByCreditId(creditId);
        List<Diploma> diplomas = diplomaService.findByCreditId(creditId);

        ajaxUtils.success().msg("获取数据成功").put("analyse", credit)
                .put("evaluate", evaluate(credits, diplomas))
                .put("student", student)
                .put("winings", winings)
                .put("diplomas", diplomas);
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
            credit.setIntellectual(creditAddVo.getIntellectual());
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
            credit.setIntellectual(creditEditVo.getIntellectual());
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

    /**
     * 导入
     *
     * @param multipartHttpServletRequest 数据
     * @return true or false
     */
    @RequestMapping("/web/analyse/import")
    public ResponseEntity<Map<String, Object>> analyseImport(MultipartHttpServletRequest multipartHttpServletRequest) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        try {
            String path = Workbook.creditImportPath();
            List<FileBean> fileBeen = uploadService.upload(multipartHttpServletRequest,
                    RequestUtils.getRealPath(multipartHttpServletRequest) + path, multipartHttpServletRequest.getRemoteAddr());
            if (Objects.nonNull(fileBeen) && fileBeen.size() > 0) {
                String filePath = fileBeen.get(0).getLastPath();
                List<CreditBean> list = new CreditReadExcel().readExcel(filePath);
                if (Objects.nonNull(list)) {
                    list.forEach(creditBean -> {
                        Credit credit = new Credit();
                        credit.setStudentNumber(creditBean.getStudentNumber());
                        credit.setYear(creditBean.getYear());
                        credit.setTerm(creditBean.getTerm());
                        credit.setSports(creditBean.getSports());
                        credit.setSkills(creditBean.getSkills());
                        credit.setVoluntary(creditBean.getVoluntary());
                        credit.setTechnological(creditBean.getTechnological());
                        credit.setPost(creditBean.getPost());
                        credit.setIdeological(creditBean.getIdeological());
                        credit.setPractical(creditBean.getPractical());
                        credit.setWork(creditBean.getWork());
                        credit.setAchievement(creditBean.getAchievement());
                        credit.setIntellectual(creditBean.getIntellectual());
                        creditService.save(credit);
                    });
                }
            }
        } catch (Exception e) {
            log.error("Upload file exception,is {}", e);
        }

        return new ResponseEntity<>(ajaxUtils.success().msg("导入成功").send(), HttpStatus.OK);
    }

    /**
     * 保存获奖
     *
     * @param winingAddVo 获奖
     * @return true or false
     */
    @PostMapping(value = "/web/analyse/wining/save")
    public ResponseEntity<Map<String, Object>> winingSave(@Valid WiningAddVo winingAddVo, BindingResult bindingResult) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        if (!bindingResult.hasErrors()) {
            Wining wining = new Wining();
            wining.setCreditId(winingAddVo.getCreditId());
            wining.setWiningContent(winingAddVo.getWiningContent());
            wining.setWiningScore(winingAddVo.getWiningScore());
            winingService.save(wining);
            ajaxUtils.success().msg("保存成功");
        } else {
            ajaxUtils.fail().msg(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 保存证书
     *
     * @param diplomaAddVo 证书
     * @return true or false
     */
    @PostMapping(value = "/web/analyse/diploma/save")
    public ResponseEntity<Map<String, Object>> diplomaSave(@Valid DiplomaAddVo diplomaAddVo, BindingResult bindingResult) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        if (!bindingResult.hasErrors()) {
            Diploma diploma = new Diploma();
            diploma.setCreditId(diplomaAddVo.getCreditId());
            diploma.setDiplomaName(diplomaAddVo.getDiplomaName());
            diplomaService.save(diploma);
            ajaxUtils.success().msg("保存成功");
        } else {
            ajaxUtils.fail().msg(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 删除
     *
     * @param winingId id
     * @return true or false
     */
    @PostMapping(value = "/web/analyse/wining/delete")
    public ResponseEntity<Map<String, Object>> winingDelete(@RequestParam("winingId") int winingId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        winingService.deleteById(winingId);
        return new ResponseEntity<>(ajaxUtils.success().msg("删除成功").send(), HttpStatus.OK);
    }

    /**
     * 删除
     *
     * @param diplomaId id
     * @return true or false
     */
    @PostMapping(value = "/web/analyse/diploma/delete")
    public ResponseEntity<Map<String, Object>> diplomaDelete(@RequestParam("diplomaId") int diplomaId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        diplomaService.deleteById(diplomaId);
        return new ResponseEntity<>(ajaxUtils.success().msg("删除成功").send(), HttpStatus.OK);
    }

    private String evaluate(double[] credits, List<Diploma> diplomas) {
        int index = 0;
        double finalScore = 0.0;
        double score = 0.0;
        for (int i = 0; i < credits.length; i++) {
            if (credits[i] > score) {
                score = credits[i];
                index = i;
                finalScore = score;
            }
        }

        StringBuilder sb = new StringBuilder();
        // 文体活动
        if (index == 0) {
            if (finalScore > 15) {
                sb.append("积极参加各类校园文化艺术体育活动，");
            } else if (finalScore >= 10) {
                sb.append("积极参加各类活动，");
            } else if (finalScore >= 5) {
                sb.append("偶尔参加校园文化艺术体育活动，");
            } else if (finalScore > 0) {
                sb.append("很少参加校园文化艺术体育活动，");
            } else {
                sb.append("不参加校园文化艺术体育活动，");
            }
        } else if (index == 1) {
            // 技能特长
            if (finalScore > 0) {
                sb.append("认真学习专业知识，努力提升技能水平，经过学习和努力获得了");
                for (Diploma diploma : diplomas) {
                    sb.append(diploma.getDiplomaName()).append(",");
                }
            }
        } else if (index == 2) {
            // 志愿公益
            if (finalScore > 10) {
                sb.append("有强烈的社会责任感，热心公益，积极参加各类志愿服务活动，为人友善，品德兼优，在同学之中起到了模范表率作用，");
            } else if (finalScore >= 5) {
                sb.append("积极参加社会志愿服务活动，为人友善，品德兼优；");
            } else if (finalScore >= 0.5) {
                sb.append("偶尔参加社会志愿服务活动，为人友善；");
            } else {
                sb.append("从未参加志愿服务活动，");
            }
        } else if (index == 3) {
            // 科技创新
            if (finalScore > 0) {
                sb.append("具有一定的创新思维，");
            }
        } else if (index == 4) {
            // 任职经历
            sb.append("");
        } else if (index == 5) {
            // 思想成长
            if (finalScore > 15) {
                sb.append("团结同学，尊敬师长，热爱班级，为人友善，品德兼优，拥护党的领导，思想积极向上，");
            } else if (finalScore >= 13) {
                sb.append("思想端正，讲文明，懂礼貌，遵守校纪校规，");
            } else if (finalScore >= 10) {
                sb.append("品德优良，思想正派，严守校纪校规，");
            } else {
                sb.append("思想态度尚可，");
            }
        } else if (index == 6) {
            // 实践实习
            if (finalScore > 0) {
                sb.append("具有一定的动手能力和社会经验，");
            } else {
                sb.append("欠缺动手能力和社会经验，");
            }
        } else if (index == 7) {
            // 工作履历
            sb.append("");
        } else if (index == 8) {
            // 学习成绩
        } else if (index == 9) {
            // 智育成绩
            if (finalScore > 80) {
                sb.append("学习刻苦认真，学习上一丝不苟，成绩名列前茅，");
            } else if (finalScore >= 70) {
                sb.append("学习刻苦，态度端正，学习成绩优异，");
            } else if (finalScore >= 60) {
                sb.append("学习较为认真，能掌握一定的学习方法，");
            } else {
                sb.append("学习不用心，成绩较为落后，");
            }
        }

        if (credits[9] > 75 && credits[4] > 13 && credits[0] > 10) {
            sb.append("德、智、体、美、劳全面发展，深受老师信任。");
        }

        if (credits[9] > 75 && credits[4] > 13 && credits[0] <= 10) {
            sb.append("是一位品学兼优的学生。");
        }

        if (credits[9] > 75 && credits[4] <= 13 && credits[0] > 10) {
            sb.append("是一位注重专业学习及个人爱好培养的学生，建议多加强思想素质提升。");
        }

        if (credits[9] <= 75 && credits[5] > 13 && credits[0] > 10) {
            sb.append("是一位注重自身思想文化艺术均衡发展的学生，建议在专业知识学习上多下功夫。");
        }

        if (credits[9] < 75 && credits[5] < 13 && credits[0] < 10) {
            sb.append("综合素质并不突出，希望进一步加强自身素质提升。");
        }

        return sb.toString();
    }
}
