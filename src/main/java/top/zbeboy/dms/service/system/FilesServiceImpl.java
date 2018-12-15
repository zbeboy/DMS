package top.zbeboy.dms.service.system;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.zbeboy.dms.domain.dms.tables.daos.FilesDao;
import top.zbeboy.dms.domain.dms.tables.pojos.Files;

import javax.annotation.Resource;

@Service("filesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FilesServiceImpl implements FilesService {

    @Resource
    private FilesDao filesDao;

    @Override
    public Files findByFileId(String fileId) {
        return filesDao.findById(fileId);
    }
}
