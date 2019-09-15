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
 * @date 2019-09-15 15:35:37
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
	 * 服务质量打分
	 */
	private Integer service;
	/**
	 * 物流速度打分
	 */
	private Integer speed;
	/**
	 * 物品完好度
	 */
	private Integer goods;
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
