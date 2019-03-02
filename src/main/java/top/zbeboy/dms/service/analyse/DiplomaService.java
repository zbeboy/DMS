package top.zbeboy.dms.service.analyse;

import top.zbeboy.dms.domain.dms.tables.pojos.Diploma;

import java.util.List;

public interface DiplomaService {

    /**
     * 通过 学分id查询
     *
     * @param creditId 学分id
     * @return 数据
     */
    List<Diploma> findByCreditId(int creditId);

    /**
     * 保存
     *
     * @param diploma 数据
     */
    void save(Diploma diploma);

    /**
     * 通过主键删除
     *
     * @param id 主键
     */
    void deleteById(int id);
}
