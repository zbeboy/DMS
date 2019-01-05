package top.zbeboy.dms.service.analyse;

import org.jooq.Record;
import org.jooq.Result;
import top.zbeboy.dms.domain.dms.tables.pojos.Credit;
import top.zbeboy.dms.web.bean.analyse.CreditBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

public interface CreditService {

    /**
     * 通过主键查询
     *
     * @param id 主键
     * @return 数据
     */
    Credit findByCreditId(int id);

    /**
     * 分页查询
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @return 分页数据
     */
    Result<Record> findAllByPage(BootstrapTableUtils<CreditBean> bootstrapTableUtils);

    /**
     * 根据条件查询总数
     *
     * @return 条件查询总数
     */
    int countByCondition(BootstrapTableUtils<CreditBean> bootstrapTableUtils);

    /**
     * 保存
     *
     * @param credit 数据
     */
    void save(Credit credit);

    /**
     * 更新
     *
     * @param credit 数据
     */
    void update(Credit credit);

    /**
     * 删除
     *
     * @param id id
     */
    void deleteById(int id);
}
