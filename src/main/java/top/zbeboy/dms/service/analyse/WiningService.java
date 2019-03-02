package top.zbeboy.dms.service.analyse;

import top.zbeboy.dms.domain.dms.tables.pojos.Wining;

import java.util.List;

public interface WiningService {

    /**
     * 通过 学分id查询
     *
     * @param creditId 学分id
     * @return 数据
     */
    List<Wining> findByCreditId(int creditId);

    /**
     * 保存
     *
     * @param wining 数据
     */
    void save(Wining wining);

    /**
     * 通过主键删除
     *
     * @param id 主键
     */
    void deleteById(int id);
}
