package io.renren.modules.express.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 任务表
 * 
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
@Data
@TableName("tb_task")
public class TaskEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 任务编号
	 */
	@TableId(type = IdType.INPUT)
	private String taskId;
	/**
	 * 任务领取人id
	 */
	private String taskReceiverId;
	/**
	 * 订单id
	 */
	private String orderId;
	/**
	 * 运单号
	 */
	private String transportNo;
	/**
	 * 发/收人姓名
	 */
	private String resendName;
	/**
	 * 发/收手机号
	 */
	private String phoneNum;
	/**
	 * 取货/派送地址 省
	 */ 
	private String province;
	/**
	 * 取货/派送地址 市
	 */
	private String city;
	/**
	 * 取货/派送地址 区
	 */
	private String area;
	/**
	 * 取货/派送地址
	 */
	private String detaileAddr;
	/**
	 * 任务类型（0 揽件；1派送）
	 */
	private String taskType;
	/**
	 * 任务状态（0有效；1取消）
	 */
	private String taskStatus;
	/**
	 * 分配人
	 */
	private String allocationBy;
	/**
	 * 分配时间
	 */
	private Date allocationTime;
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
