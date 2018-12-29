package top.zbeboy.dms.web.data.politicalLandscape;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zbeboy.dms.domain.dms.tables.pojos.PoliticalLandscape;
import top.zbeboy.dms.domain.dms.tables.pojos.School;
import top.zbeboy.dms.service.data.PoliticalLandscapeService;
import top.zbeboy.dms.web.util.AjaxUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class PoliticalLandscapeRestController {

    @Resource
    private PoliticalLandscapeService politicalLandscapeService;

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/politicalLandscape/all")
    public ResponseEntity<Map<String, Object>> politicalLandscapeAll() {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        List<PoliticalLandscape> politicalLandscapes = politicalLandscapeService.findAll();
        ajaxUtils.success().msg("获取数据成功").put("politicalLandscapes", politicalLandscapes);
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }
}
