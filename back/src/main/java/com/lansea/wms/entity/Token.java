package com.lansea.wms.entity;

import com.alibaba.fastjson.JSONObject;
import com.lansea.wms.mapper.UserMapper;
import com.lansea.wms.model.User;
import com.lansea.wms.util.AESUtil;
import com.lansea.wms.util.BeanUtil;


public class Token {

    private Integer id;
    private String userName;
    private String realName;
    private String mobile;
    private Long expireTime;

    public Token() {
    }

    public Token(User user) {
        this.setId(user.getId());
        this.setUserName(user.getUserName());
        this.setRealName(user.getUserName());
        this.setMobile(user.getMobile());
        this.setExpireTime(System.currentTimeMillis() / 1000 + 30 * 3600);
    }

    /**
     * 生成 token 字符串
     *
     * @return
     */
    public String createTokenString() {
        return JSONObject.toJSONString(this);
    }

    /**
     * 生成 token 加密字符串
     *
     * @return
     */
    public String createTokenEnString() {
        return AESUtil.encrypt(createTokenString());
    }

    /**
     * 根据 加密字符串 返回 token
     *
     * @param enStr 加密信息
     * @return
     */
    public static Token createToken(String enStr) {
        String json = AESUtil.decrypt(enStr);
        return JSONObject.parseObject(json, Token.class);
    }

    /**
     * 查找用户信息
     *
     * @return
     */
    public User findUser() {
        UserMapper userMapper = BeanUtil.getBean(UserMapper.class);
        return userMapper.findById(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
