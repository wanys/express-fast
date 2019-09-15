package io.renren.modules.express.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 快递公司表
 * 
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
@Data
@TableName("tb_express_company")
public class ExpressCompanyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 快递公司ID
	 */
	@TableId
	private Integer expressId;
	/**
	 * 快递公司编码
	 */
	private String expressCode;
	/**
	 * 快递公司名称
	 */
	private String expressName;
	/**
	 * 快递公司头像
	 */
	private String expressImg;
	/**
	 * 快递公司电话
	 */
	private Integer expressPhone;

}
