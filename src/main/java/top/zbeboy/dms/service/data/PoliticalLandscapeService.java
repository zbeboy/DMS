package top.zbeboy.dms.service.data;

import top.zbeboy.dms.domain.dms.tables.pojos.PoliticalLandscape;

import java.util.List;

public interface PoliticalLandscapeService {

    /**
     * 查询全部
     *
     * @return 数据
     */
    List<PoliticalLandscape> findAll();
}
