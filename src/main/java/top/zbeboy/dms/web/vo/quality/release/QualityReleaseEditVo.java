package top.zbeboy.dms.web.vo.quality.release;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QualityReleaseEditVo {
    @NotBlank(message = "发布id不能为空")
    private String qualityReleaseId;
    @NotBlank(message = "标题不能为空")
    @Size(max = 100, message = "标题100位字符")
    private String releaseTitle;
    @NotBlank(message = "年份不能为空")
    @Size(max = 5, message = "年份5位字符")
    private String year;
    @NotNull(message = "学期不能为空")
    private Integer term;

    public String getQualityReleaseId() {
        return qualityReleaseId;
    }

    public void setQualityReleaseId(String qualityReleaseId) {
        this.qualityReleaseId = qualityReleaseId;
    }

    public String getReleaseTitle() {
        return releaseTitle;
    }

    public void setReleaseTitle(String releaseTitle) {
        this.releaseTitle = releaseTitle;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }
}
