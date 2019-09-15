package io.renren.modules.express.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单表
 * 
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
@Data
@TableName("tb_order")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 订单编号
	 */
	private String orderId;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 寄件人姓名
	 */
	private String senderName;
	/**
	 * 寄件人号码
	 */
	private Integer senderPhone;
	/**
	 * 寄件人地址
	 */
	private String senderAddr;
	/**
	 * 收件人姓名
	 */
	private String receiverName;
	/**
	 * 收件人号码
	 */
	private Integer receiverPhone;
	/**
	 * 收件人地址
	 */
	private String receiverAddr;
	/**
	 * 物品种类
	 */
	private String goodsType;
	/**
	 * 运单号
	 */
	private String transportNo;
	/**
	 * 订单状态
	 */
	private Integer orderStatus;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 快递公司
	 */
	private String express;

}
