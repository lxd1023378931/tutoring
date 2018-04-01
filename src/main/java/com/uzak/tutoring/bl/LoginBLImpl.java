package com.uzak.tutoring.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uzak.tutoring.common.util.IDaoUtil;
import com.uzak.tutoring.entity.common.UZToken;
import com.uzak.tutoring.properties.TokenProperties;
import com.uzak.tutoring.util.ObjectUtil;
@Service
public class LoginBLImpl implements LoginBL {

	@Autowired
	private TokenProperties tokenProperties;
	@Autowired
	private IDaoUtil dao;

	@Override
	/**
	 * 判断是否登录
	 * 
	 * @param token
	 * @param type
	 * @return
	 */
	public boolean isLogin(String token, String type) {
		UZToken uzToken = new UZToken();
		uzToken.setType(type);
		uzToken.setToken(token);
		if (dao.fill(uzToken)) {
			long currentTime = System.currentTimeMillis();
			if (currentTime < uzToken.getExpireTime()) {
				Object obj = ObjectUtil.toMap(tokenProperties).get(type);
				long time = (ObjectUtil.isDigit(obj) && obj.toString().indexOf(".") == -1) ? Long.parseLong(obj.toString()) : 0;
				uzToken.setExpireTime(uzToken.getExpireTime() + time);
				dao.update(uzToken);
			}
			return true;
		} else {
			return false;
		}
	}

}
