package top.zbeboy.dms.service.platform;


import top.zbeboy.dms.domain.dms.tables.pojos.UsersType;

public interface UsersTypeService {

    /**
     * 根据用户类型查询
     *
     * @param usersTypeName 类型名
     * @return 用户类型
     */
    UsersType findByUsersTypeName(String usersTypeName);
}
