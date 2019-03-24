package top.zbeboy.dms.web.vo.quality.apply;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QualityApplyAddVo {
    @NotBlank(message = "内容不能为空")
    @Size(max = 100, message = "内容100位字符")
    private String applyContent;
    @NotBlank(message = "发布ID不能为空")
    private String qualityReleaseId;
    @NotNull(message = "学生ID不能为空")
    private Integer studentId;

    public String getApplyContent() {
        return applyContent;
    }

    public void setApplyContent(String applyContent) {
        this.applyContent = applyContent;
    }

    public String getQualityReleaseId() {
        return qualityReleaseId;
    }

    public void setQualityReleaseId(String qualityReleaseId) {
        this.qualityReleaseId = qualityReleaseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
