package top.zbeboy.dms.service.system;

import top.zbeboy.dms.domain.dms.tables.pojos.Files;

public interface FilesService {

    /**
     * 根据文件id查询
     *
     * @param fileId 文件id
     * @return 文件
     */
    Files findByFileId(String fileId);
}
