package top.zbeboy.dms.web.vo.analyse;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WiningAddVo {
    @NotNull(message = "学分ID不能为空")
    @Min(value = 1, message = "学分ID不正确")
    private int creditId;
    @NotBlank(message = "获奖内容不能为空")
    private String winingContent;
    private Double  winingScore;

    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public String getWiningContent() {
        return winingContent;
    }

    public void setWiningContent(String winingContent) {
        this.winingContent = winingContent;
    }

    public Double getWiningScore() {
        return winingScore;
    }

    public void setWiningScore(Double winingScore) {
        this.winingScore = winingScore;
    }
}
