package io.renren.modules.express.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 快递员评价表
 * 
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2020-10-11 17:16:42
 */
@Data
@TableName("tb_comment")
public class CommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer commentId;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 快递员ID
	 */
	private Integer messengerId;
	/**
	 * 订单id
	 */
	private String orderId;
	/**
	 * 快递员服务态度
	 */
	private Integer serviceAttitude;
	/**
	 * 快递员专业性
	 */
	private Integer professional;
	/**
	 * 费用合理性
	 */
	private Integer spending;
	/**
	 * 取货/送货时间
	 */
	private Integer getSpeed;
	/**
	 * 下单之后发货时间/网点派送时间
	 */
	private Integer beginSpeed;
	/**
	 * 物品运输速度
	 */
	private Integer transportSpeed;
	/**
	 * 个人信息安全
	 */
	private Integer personalInfoSec;
	/**
	 * 物品完整度
	 */
	private Integer complete;
	/**
	 * 物品安全性
	 */
	private Integer goodsSec;
	/**
	 * 评价内容
	 */
	private String content;
	/**
	 * 综合得分
	 */
	private Integer score;
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
