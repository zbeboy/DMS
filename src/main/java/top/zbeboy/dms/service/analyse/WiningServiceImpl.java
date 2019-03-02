package top.zbeboy.dms.service.analyse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.zbeboy.dms.domain.dms.tables.daos.WiningDao;
import top.zbeboy.dms.domain.dms.tables.pojos.Wining;

import javax.annotation.Resource;
import java.util.List;

@Service("winingService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class WiningServiceImpl implements WiningService {

    @Resource
    private WiningDao winingDao;

    @Override
    public List<Wining> findByCreditId(int creditId) {
        return winingDao.fetchByCreditId(creditId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(Wining wining) {
        winingDao.insert(wining);
    }

    @Override
    public void deleteById(int id) {
        winingDao.deleteById(id);
    }
}
