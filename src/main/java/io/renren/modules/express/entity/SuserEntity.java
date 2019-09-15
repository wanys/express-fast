package io.renren.modules.express.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * 
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
@Data
@TableName("tb_suser")
public class SuserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	private Integer userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 用户角色
	 */
	private String userRole;
	/**
	 * 用户类型（0用户1快递员）
	 */
	private String userType;
	/**
	 * 头像路径
	 */
	private String userImg;
	/**
	 * 微信id
	 */
	private String wechatId;
	/**
	 * 微信名
	 */
	private String wechatName;
	/**
	 * 身份信息号码
	 */
	private String idcar;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改人
	 */
	private String modifyBy;
	/**
	 * 修改时间
	 */
	private Date modifyTime;

}
