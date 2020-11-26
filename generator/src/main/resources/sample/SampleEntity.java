package cn.exrick.xboot.modules.web.entity;

import cn.exrick.xboot.modules.base.mpbase.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
##importDate#

@Data
@TableName("##table_name#")
@ApiModel(value = "##ClassNameSuffix#")
public class ##ClassNameSuffix# extends BaseEntity {
	##Fields#
}