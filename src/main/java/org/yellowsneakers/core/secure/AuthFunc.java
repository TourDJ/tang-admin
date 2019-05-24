package org.yellowsneakers.core.secure;

import org.yellowsneakers.generic.utils.CollectionUtils;
import org.yellowsneakers.generic.utils.Func;
import org.yellowsneakers.generic.utils.StringUtils;

public class AuthFunc {
	/**
     * 放行所有请求
     * @return {boolean}
     */
    public boolean permitAll() {
        return true;
    }

    /**
     * 只有超管角色才可访问
     * @return {boolean}
     */
    public boolean denyAll() {
        return hasRole(RoleConstants.ROLE_ADMIN);
    }

    /**
	 * 判断请求是否有权限
	 * @param permission 权限表达式
	 * @return {boolean}
	 */
	public boolean hasPermission(String permission) {
		return true;
	}

	/**
	 * 判断是否有该角色权限
	 * @param role 单角色
	 * @return {boolean}
	 */
	public boolean hasRole(String role) {
	    return hasAnyRole(role);
	}

    /**
     * 判断是否有该角色权限
     * @param role 角色集合
     * @return {boolean}
     */
    public boolean hasAnyRole(String... role) {
        String userRole = TokenSecurer.getUser().getRoleName();
        if (StringUtils.isBlank(userRole)) {
            return false;
        }
        String[] roles = Func.toStrArray(userRole);
        if(CollectionUtils.contains(roles, role)) {
            return true;
        }
        return false;
    }

}
