package com.hwc.shiro_study.config.realm;

import com.hwc.shiro_study.dao.UserDao;
import com.hwc.shiro_study.dto.UserDto;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : Huang_Weichun
 * @project : shiroDemo
 * @date : 2023/9/24 22:54
 */
@Service
public class ShiroUserAuthorRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;


    /**通过此方法完成授权信息的获取及封装*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        //1.获取登录用户信息，例如用户id
        UserDto user=(UserDto)principals.getPrimaryPrincipal();
        String userName = user.getUserName();
        //2.基于用户id获取用户拥有的角色(sys_user_roles)
//        List<Integer> roleIds=
//                sysUserRoleDao.findRoleIdsByUserId(userId);
//        if(roleIds==null||roleIds.size()==0)
//            throw new AuthorizationException();
        //3.基于角色id获取菜单id(sys_role_menus)
//        List<Integer> menuIds=
//                sysRoleMenuDao.findMenuIdsByRoleIds(roleIds);
//        if(menuIds==null||menuIds.size()==0)
//            throw new AuthorizationException();
        //4.基于菜单id获取权限标识(sys_menus)
//        List<String> permissions=
//                sysMenuDao.findPermissions(menuIds);
        //5.对权限标识信息进行封装并返回
//        Set<String> set=new HashSet<>();
//        for(String per:permissions){
//            if(!StringUtils.isEmpty(per)){
//                set.add(per);
//            }
//        }
        SimpleAuthorizationInfo info=
                new SimpleAuthorizationInfo();
        info.setStringPermissions(null); // set
        return info;//返回给授权管理器
    }

    /**
     * 通过此方法完成认证数据的获取及封装,系统底层会将认证数据传递认证管理器，由认证管理器完成认证操作。
     * <p>
     * Retrieves authentication data from an implementation-specific datasource (RDBMS, LDAP, etc) for the given
     * authentication token.
     * <p/>
     * For most datasources, this means just 'pulling' authentication data for an associated subject/user and nothing
     * more and letting Shiro do the rest.  But in some systems, this method could actually perform EIS specific
     * log-in logic in addition to just retrieving data - it is up to the Realm implementation.
     * <p/>
     * A {@code null} return value means that no account could be associated with the specified token.
     *
     * @param token the authentication token containing the user's principal and credentials.
     * @return an {@link AuthenticationInfo} object containing account data resulting from the
     * authentication ONLY if the lookup is successful (i.e. account exists and is valid, etc.)
     * @throws AuthenticationException if there is an error acquiring data or performing
     *                                 realm-specific authentication logic for the specified <tt>token</tt>
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1.获取用户信息
        UserDto userToken = (UserDto) token;
        String userName = userToken.getUserName();

        UserDto userDto = userDao.selectByUserName(userName);

        if (userDto == null) {
            throw new LockedAccountException();
        }
        ByteSource salt = ByteSource.Util.bytes(userDto.getSalt());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDto, userDto.getUserPwd(), salt, getName());
        return info;
    }
}
