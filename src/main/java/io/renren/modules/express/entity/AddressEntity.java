package io.renren.modules.express.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-12-26 20:27:45
 */
@Data
@TableName("tb_address")
public class AddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 地址名字
	 */
	private String name;
	/**
	 * 地址电话
	 */
	private String phone;
	/**
	 * 所在省
	 */
	private String provence;
	/**
	 * 所在市
	 */
	private String city;
	/**
	 * 所在区
	 */
	private String area;
	/**
	 * 详细地址
	 */
	private String detailAddr;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	/**
	 * 修改人
	 */
	private String modifyBy;

}
