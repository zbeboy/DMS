package top.zbeboy.dms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 系统属性配置
 *
 * @author zbeboy
 * @version 1.0
 * @since 1.0
 */
@ConfigurationProperties(prefix = "dms", ignoreUnknownFields = false)
public class DMSProperties {

    private Constants constants = new Constants();

    public Constants getConstants() {
        return constants;
    }

    /**
     * 通用初始化参数
     */
    public class Constants {

        private String documentRoot;

        private String staticImages;

        public String getDocumentRoot() {
            return documentRoot;
        }

        public void setDocumentRoot(String documentRoot) {
            this.documentRoot = documentRoot;
        }

        public String getStaticImages() {
            return staticImages;
        }

        public void setStaticImages(String staticImages) {
            this.staticImages = staticImages;
        }
    }
}
