package top.zbeboy.dms.web.vo.analyse;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DiplomaAddVo {
    @NotNull(message = "学分ID不能为空")
    @Min(value = 1, message = "学分ID不正确")
    private int creditId;
    @NotBlank(message = "证书不能为空")
    private String diplomaName;

    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public String getDiplomaName() {
        return diplomaName;
    }

    public void setDiplomaName(String diplomaName) {
        this.diplomaName = diplomaName;
    }
}
