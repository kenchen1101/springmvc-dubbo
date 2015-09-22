package com.rpc.auth.util;

import com.rpc.auth.model.User;
import com.rpc.common.constants.Constants;
import com.rpc.util.salt.Digests;
import com.rpc.util.salt.Encodes;

/**
 * 用户编码
 */
public class UserEncodes {

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    public static void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(Constants.SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, Constants.HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }
    
    public static void main(String[] args) {
        User user = new User();
        user.setPassword("123456");
        entryptPassword(user);
        System.out.println(user.getPassword());
    }
    

}
