package io.renren.modules.express.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

//import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

import io.renren.modules.app.entity.UserEntity;
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
	 * 订单编号
	 */
	@TableId(type = IdType.INPUT)
	private String orderId;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 寄件人姓名
	 */
	private String senderName;
	/**
	 * 寄件人号码
	 */
	private String senderPhone;

    /**
     * 寄件人省份
     */
    private String senderProvince;
    /**
     * 寄件人城市
     */
    private String senderCity;
    /**
     * 寄件人区
     */
    private String senderArea;
    /**
     * 寄件人地址
     */
    private String senderDetaileAddr;
	/**
	 * 收件人姓名
	 */
	private String receiverName;
	/**
	 * 收件人号码
	 */
	private String receiverPhone;
    /**
     * 收件人省份
     */
    private String receiverProvince;
    /**
     * 收件人城市
     */
    private String receiverCity;
    /**
     * 收件人区
     */
    private String receiverArea;
	/**
	 * 收件人地址
	 */
	private String receiverDetaileAddr;
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
	private String orderStatus;

	/**
	 * 快递公司
	 */
	private String express;
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
	 * 创建人
	 */
	private String modifyBy;

    @TableField(exist = false)
	private UserEntity userEntity;
}
