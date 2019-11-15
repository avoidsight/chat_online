package com.example.demojpa.service;

import com.example.demojpa.consts.SecurityConsts;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zp
 * @Date: 2019-11-15 16:10
 * @Description:
 */
@Service
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private IBpUserService userService;
    @Autowired
    private IBpRoleService roleService;
    @Autowired
    private IBpAuthorityService bpAuthorityService;
    @Autowired
    private CacheClient cacheClient;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 用户名信息验证
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth)
            throws AuthenticationException {
        String token = (String)auth.getPrincipal();
        String account  = JwtUtil.getClaim(token, SecurityConsts.ACCOUNT);

        if (account == null) {
            throw new AuthenticationException("token invalid");
        }

        BpUser bpUserInfo = userService.findUserByAccount(account);
        if (bpUserInfo == null) {
            throw new AuthenticationException("BpUser didn't existed!");
        }

        String refreshTokenCacheKey = SecurityConsts.PREFIX_SHIRO_REFRESH_TOKEN + account;
        if (JwtUtil.verify(token) && cacheClient.exists(refreshTokenCacheKey)) {
            String currentTimeMillisRedis = cacheClient.get(refreshTokenCacheKey);
            // 获取AccessToken时间戳，与RefreshToken的时间戳对比
            if (JwtUtil.getClaim(token, SecurityConsts.CURRENT_TIME_MILLIS).equals(currentTimeMillisRedis)) {
                return new SimpleAuthenticationInfo(token, token, "shiroRealm");
            }
        }
        throw new AuthenticationException("Token expired or incorrect.");
    }

    /**
     * 检查用户权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        String account = JwtUtil.getClaim(principals.toString(), SecurityConsts.ACCOUNT);
        BpUser bpUserInfo = userService.findUserByAccount(account);

        //获取用户角色
        List<BpRole> bpRoleList = roleService.findRoleByUserId(bpUserInfo.getId());
        //获取权限
        List<Object> bpAuthorityList = bpAuthorityService.findByUserId(bpUserInfo.getId());
        for(BpRole bpRole : bpRoleList){
            authorizationInfo.addRole(bpRole.getName());
            for(Object auth: bpAuthorityList){
                authorizationInfo.addStringPermission(auth.toString());
            }
        }
        return authorizationInfo;
    }
}