package top.zbeboy.dms.service.platform;


import top.zbeboy.dms.domain.dms.tables.pojos.UsersType;

public interface UsersTypeService {

    /**
     * 根据用户id查询
     *
     * @param usersTypeId 类型id
     * @return 用户类型
     */
    UsersType findByUsersTypeId(int usersTypeId);

    /**
     * 根据用户类型查询
     *
     * @param usersTypeName 类型名
     * @return 用户类型
     */
    UsersType findByUsersTypeName(String usersTypeName);
}
