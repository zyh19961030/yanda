package com.qu.modules.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 录入表单表
 * @Author: jeecg-boot
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SingleEnterQuestionInfoSubjectVo", description = "SingleEnterQuestionInfoSubjectVo")
public class SingleEnterQuestionInfoSubjectVo {

    @ApiModelProperty(value = "题目id")
    private Integer id;

    @ApiModelProperty(value = "题目名称")
    private String name;

}
