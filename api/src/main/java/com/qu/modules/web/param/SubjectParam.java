package com.qu.modules.web.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "题目新增入参", description = "题目新增入参")
public class SubjectParam {
    /**
     * 问卷id
     */
    @Excel(name = "问卷id", width = 15)
    @ApiModelProperty(value = "问卷id")
    @NotNull(message = "问卷id不能为空")
    private java.lang.Integer quId;
    /**
     * 题目名称
     */
    @Excel(name = "题目名称", width = 15)
    @ApiModelProperty(value = "题目名称")
    @NotBlank(message = "题目名称不能为空")
    private java.lang.String subName;

    @ApiModelProperty(value = "题目类型 1.单选 2.多选 3.日期 4.时间 5.下拉框 6.单行文本 7.多行文本 8.分组框 9.提示标题")
    @NotBlank(message = "题目类型不能为空")
    private java.lang.String subType;
    /**
     * 是否为必填 0:非必填 1:必填
     */
    @Excel(name = "是否为必填 0:非必填 1:必填", width = 15)
    @ApiModelProperty(value = "是否为必填 0:非必填 1:必填")
    private java.lang.Integer required;
    /**
     * 默认是否显示 0:不显示 1:显示
     */
    @Excel(name = "默认是否显示 0:不显示 1:显示", width = 15)
    @ApiModelProperty(value = "默认是否显示 0:不显示 1:显示")
    private java.lang.Integer display;
    /**
     * 最多字数限制
     */
    @Excel(name = "最多字数限制", width = 15)
    @ApiModelProperty(value = "最多字数限制")
    private java.lang.Integer limitWords;
    /**
     * 文本框校验 num:数字 email:电子邮箱 ch:中文 en:英文 idcard:身份证号码 qqno:QQ号 phone:手机号码(仅支持大陆地区) tel:电话号码
     */
    @Excel(name = "文本框校验 num:数字 email:电子邮箱 ch:中文 en:英文 idcard:身份证号码 qqno:QQ号 phone:手机号码(仅支持大陆地区) tel:电话号码", width = 15)
    @ApiModelProperty(value = "文本框校验 num:数字 email:电子邮箱 ch:中文 en:英文 idcard:身份证号码 qqno:QQ号 phone:手机号码(仅支持大陆地区) tel:电话号码")
    private java.lang.String textCheck;
    /**
     * 多行文本框高度
     */
    @Excel(name = "多行文本框高度", width = 15)
    @ApiModelProperty(value = "多行文本框高度")
    private java.lang.Integer textHight;
    /**
     * 时间是否为区间 0:非区间 1:区间
     */
    @Excel(name = "时间是否为区间 0:非区间 1:区间", width = 15)
    @ApiModelProperty(value = "时间是否为区间 0:非区间 1:区间")
    private java.lang.Integer section;
    /**
     * 默认值
     */
    @Excel(name = "默认值", width = 15)
    @ApiModelProperty(value = "默认值")
    private java.lang.String defValue;
    /**
     * 时间显示方式 0:时分 1:时分秒
     */
    @Excel(name = "时间显示方式 0:时分 1:时分秒", width = 15)
    @ApiModelProperty(value = "时间显示方式 0:时分 1:时分秒")
    private java.lang.Integer defDisplay;
    /**
     * 备注
     */
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
    /**
     * 跳题逻辑
     */
    @Excel(name = "跳题逻辑", width = 15)
    @ApiModelProperty(value = "跳题逻辑")
    private java.lang.String jumpLogic;

    @ApiModelProperty(value = "分组题包含题号")
    private String[] groupIds;
    @ApiModelProperty(value = "绑定值")
    private java.lang.String bindValue;

    @ApiModelProperty(value = "是否抓取0:不是抓取  1:抓取")
    private java.lang.Integer grab;

    @ApiModelProperty(value = "抓取类型")
    private java.lang.String grabType;

    /**
     * 数据库列名
     */
    @Excel(name = "数据库列名", width = 15)
    @ApiModelProperty(value = "数据库列名")
    private java.lang.String columnName;

    /**
     * 创建数据库列名的数据类型
     */
    @Excel(name = "创建数据库列名的数据类型", width = 15)
    @ApiModelProperty(value = "创建数据库列名的数据类型")
    private java.lang.String columnType;

    @ApiModelProperty(value = "选项")
    private List<QoptionParam> optionParamList;
    /**
     * 特殊跳题逻辑,前端使用
     */
    @Excel(name = "特殊跳题逻辑,前端使用", width = 15)
    @ApiModelProperty(value = "特殊跳题逻辑,前端使用")
    private java.lang.String specialJumpLogic;
    /**
     * 数字类型最小值
     */
    @Excel(name = "数字类型最小值", width = 15)
    @ApiModelProperty(value = "数字类型最小值")
    private BigDecimal valueMin;
    /**
     * 数字类型最大值
     */
    @Excel(name = "数字类型最大值", width = 15)
    @ApiModelProperty(value = "数字类型最大值")
    private BigDecimal valueMax;

    /**提示属性*/
    @Excel(name = "提示属性", width = 15)
    @ApiModelProperty(value = "提示属性")
    private java.lang.String tips;
    /**是否开启痕迹 改0:不开启1:开启 */
    @Excel(name = "是否开启痕迹 改0:不开启1:开启 ", width = 15)
    @ApiModelProperty(value = "是否开启痕迹 改0:不开启1:开启 ")
    private java.lang.Integer mark;
    /**分值*/
    @Excel(name = "分值", width = 15)
    @ApiModelProperty(value = "分值")
    private java.math.BigDecimal score;
    /**计算类型  0加分  1减分*/
    @Excel(name = "计算类型  0加分  1减分", width = 15)
    @ApiModelProperty(value = "计算类型  0加分  1减分")
    private java.lang.Integer scoreType;
    /**排列方式 0每行1列  1每行2列 2每行3列 3每行4列  4每行5列  5每行6列*/
    @Excel(name = "排列方式 0每行1列  1每行2列 2每行3列 3每行4列  4每行5列  5每行6列", width = 15)
    @ApiModelProperty(value = "排列方式 0每行1列  1每行2列 2每行3列 3每行4列  4每行5列  5每行6列")
    private java.lang.Integer arrangement;
    /**视图名称*/
    @Excel(name = "视图名称", width = 15)
    @ApiModelProperty(value = "视图名称")
    private java.lang.String viewName;
    /**针对 19.数量统计题型 的统计的答案值的条件类型  0等于 1大于 2大于等于 3小于 4小于等于*/
    @Excel(name = "针对 19.数量统计题型 的统计的答案值的条件类型  0等于 1大于 2大于等于 3小于 4小于等于", width = 15)
    @ApiModelProperty(value = "针对 19.数量统计题型 的统计的答案值的条件类型  0等于 1大于 2大于等于 3小于 4小于等于")
    private java.lang.Integer conditionType;
    /**针对 19.数量统计题型 的统计的答案值的条件的值*/
    @Excel(name = "针对 19.数量统计题型 的统计的答案值的条件的值", width = 15)
    @ApiModelProperty(value = "针对 19.数量统计题型 的统计的答案值的条件的值")
    private java.lang.String conditionValue;
    /**针对 19.数量统计题型 20.选择题分数求和题型 21.结果评价题型 22.数值题求和题型 选择统计题目的id集合*/
    @Excel(name = "针对 19.数量统计题型 20.选择题分数求和题型 21.结果评价题型 22.数值题求和题型 选择统计题目的id集合", width = 15)
    @ApiModelProperty(value = "针对 19.数量统计题型 20.选择题分数求和题型 21.结果评价题型 22.数值题求和题型 选择统计题目的id集合")
    private java.lang.String choiceSubjectId;
    /**针对  23.预设题目数量题型的项目总数量*/
    @Excel(name = "针对  23.预设题目数量题型 的项目总数量", width = 15)
    @ApiModelProperty(value = "针对  23.预设题目数量题型 的项目总数量")
    private java.lang.String subjectCount;
}
