package top.zbeboy.dms.service.analyse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.zbeboy.dms.domain.dms.tables.daos.CreditDao;
import top.zbeboy.dms.domain.dms.tables.daos.EvaluateDao;
import top.zbeboy.dms.domain.dms.tables.pojos.Evaluate;

import javax.annotation.Resource;

@Service("evaluateService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EvaluateServiceImpl implements EvaluateService {

    @Resource
    private EvaluateDao evaluateDao;

    @Override
    public Evaluate findByEvaluateId(String evaluateId) {
        return evaluateDao.findById(evaluateId);
    }
}
