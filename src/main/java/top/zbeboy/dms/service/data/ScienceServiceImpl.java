package top.zbeboy.dms.service.data;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("scienceService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ScienceServiceImpl implements ScienceService {
}
