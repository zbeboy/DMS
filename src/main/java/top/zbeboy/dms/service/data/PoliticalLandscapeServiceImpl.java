package top.zbeboy.dms.service.data;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.zbeboy.dms.domain.dms.tables.daos.PoliticalLandscapeDao;
import top.zbeboy.dms.domain.dms.tables.pojos.PoliticalLandscape;

import javax.annotation.Resource;
import java.util.List;

@Service("politicalLandscapeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PoliticalLandscapeServiceImpl implements PoliticalLandscapeService {

    @Resource
    private PoliticalLandscapeDao politicalLandscapeDao;

    @Override
    public List<PoliticalLandscape> findAll() {
        return politicalLandscapeDao.findAll();
    }
}
