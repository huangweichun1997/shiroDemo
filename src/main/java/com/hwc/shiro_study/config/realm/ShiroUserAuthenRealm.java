package com.hwc.shiro_study.config.realm;

import com.hwc.shiro_study.dao.UserDao;
import com.hwc.shiro_study.dto.UserDto;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Huang_Weichun
 * @project : shiroDemo
 * @date : 2023/9/24 21:48
 */
@Service
public class ShiroUserAuthenRealm extends AuthenticatingRealm {

    @Autowired
    private UserDao userDao;

    /**
     * 设置凭证匹配器(与用户添加操作使用相同的加密算法)
     *
     * Sets the CrendialsMatcher used during an authentication attempt to verify submitted credentials with those
     * stored in the system.  The implementation of this matcher can be switched via configuration to
     * support any number of schemes, including plain text comparisons, hashing comparisons, and others.
     * <p/>
     * <p>Unless overridden by this method, the default value is a
     * {@link SimpleCredentialsMatcher} instance.
     *
     * @param credentialsMatcher the matcher to use.
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {

        // 构建凭证匹配对象
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 设置加密算法
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 加密迭代次数
        hashedCredentialsMatcher.setHashIterations(1);
        super.setCredentialsMatcher(hashedCredentialsMatcher);
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
