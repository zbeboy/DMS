package top.zbeboy.dms.web.bean.quality;

import top.zbeboy.dms.domain.dms.tables.pojos.QualityRelease;

public class QualityReleaseBean extends QualityRelease {
    private String releaseDateStr;

    private String realName;

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
