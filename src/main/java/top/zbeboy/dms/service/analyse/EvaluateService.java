package top.zbeboy.dms.service.analyse;

import top.zbeboy.dms.domain.dms.tables.pojos.Evaluate;

public interface EvaluateService {

    /**
     * 通过评价id查询
     *
     * @param evaluateId 评价id
     * @return 数据
     */
    Evaluate findByEvaluateId(String evaluateId);
}
