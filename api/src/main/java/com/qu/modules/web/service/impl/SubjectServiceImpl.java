package com.qu.modules.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qu.modules.web.entity.Qoption;
import com.qu.modules.web.entity.Qsubject;
import com.qu.modules.web.mapper.OptionMapper;
import com.qu.modules.web.mapper.QsubjectMapper;
import com.qu.modules.web.param.*;
import com.qu.modules.web.service.ISubjectService;
import com.qu.modules.web.vo.SubjectVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description: 题目表
 * @Author: jeecg-boot
 * @Date: 2021-03-19
 * @Version: V1.0
 */
@Slf4j
@Service
public class SubjectServiceImpl extends ServiceImpl<QsubjectMapper, Qsubject> implements ISubjectService {

    @Autowired
    private QsubjectMapper qsubjectMapper;

    @Autowired
    private OptionMapper optionMapper;

    @Override
    public SubjectVo saveSubject(SubjectParam subjectParam) {
        SubjectVo subjectVo = new SubjectVo();
        Qsubject subject = new Qsubject();
        try {
            BeanUtils.copyProperties(subjectParam, subject);
            Map<String, Object> param = new HashMap<>();
            param.put("quId", subjectParam.getQuId());
            param.put("columnName", subjectParam.getColumnName());
            int colCount = qsubjectMapper.selectColumnNameCount(param);
            if (colCount > 0) {//字段重复
                return null;
            }
            //计算题号
            Integer subSumCount = qsubjectMapper.selectSumCount(subjectParam.getQuId());
            subject.setOrderNum(subSumCount + 1);
            //如果是分组题，计算分组题号字段
            if (subjectParam.getSubType().equals("8")) {
                String[] gids = subjectParam.getGroupIds();
                StringBuffer groupIds = new StringBuffer();
                if (null != gids) {
                    for (String gid : gids) {
                        groupIds.append(gid);
                        groupIds.append(",");
                    }
                }
                subject.setGroupIds(groupIds.toString());
            }
            subject.setDel(0);
            subject.setCreater(1);
            subject.setCreateTime(new Date());
            subject.setUpdater(1);
            subject.setUpdateTime(new Date());
            qsubjectMapper.insert(subject);
            //拷贝到Vo对象
            BeanUtils.copyProperties(subject, subjectVo);
            //选项
            List<Qoption> optionList = new ArrayList<>();
            List<QoptionParam> optionParamList = subjectParam.getOptionParamList();
            if (null != optionParamList) {
                int i = 1;
                for (QoptionParam optionParam : optionParamList) {
                    Qoption option = new Qoption();
                    BeanUtils.copyProperties(optionParam, option);
                    option.setSubId(subject.getId());
                    option.setOpOrder(i);
                    option.setDel(0);
                    option.setCreater(1);
                    option.setCreateTime(new Date());
                    option.setUpdater(1);
                    option.setUpdateTime(new Date());
                    optionMapper.insert(option);
                    i++;
                    optionList.add(option);
                }
                subjectVo.setOptionList(optionList);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return subjectVo;
    }

    @Override
    public SubjectVo insertSubject(InsertSubjectParam insertSubjectParam) {
        SubjectVo subjectVo = new SubjectVo();
        Qsubject subject = new Qsubject();
        try {
            BeanUtils.copyProperties(insertSubjectParam, subject);
            Map<String, Object> param = new HashMap<>();
            param.put("quId", insertSubjectParam.getQuId());
            param.put("columnName", insertSubjectParam.getColumnName());
            int colCount = qsubjectMapper.selectColumnNameCount(param);
            if (colCount > 0) {//字段重复
                return null;
            }

            //计算题号
            Integer nextOrderNum = qsubjectMapper.selectNextOrderNum(insertSubjectParam.getUpSubId());
            subject.setOrderNum(nextOrderNum + 1);

            //如果是分组题，计算分组题号字段
            if (insertSubjectParam.getSubType().equals("8")) {
                String[] gids = insertSubjectParam.getGroupIds();
                StringBuffer groupIds = new StringBuffer();
                if (null != gids) {
                    for (String gid : gids) {
                        groupIds.append(gid);
                        groupIds.append(",");
                    }
                }
                subject.setGroupIds(groupIds.toString());
            }
            subject.setDel(0);
            subject.setCreater(1);
            subject.setCreateTime(new Date());
            subject.setUpdater(1);
            subject.setUpdateTime(new Date());
            qsubjectMapper.insert(subject);

            //把这道题以后的所有题的序号都加1
            Map<String, Object> insParam = new HashMap<>();
            insParam.put("quId", insertSubjectParam.getQuId());
            insParam.put("nextOrderNum", nextOrderNum + 1);
            insParam.put("subId", subject.getId());
            qsubjectMapper.updateNextOrderNum(insParam);

            //拷贝到Vo对象
            BeanUtils.copyProperties(subject, subjectVo);
            //选项
            List<Qoption> optionList = new ArrayList<>();
            List<QoptionParam> optionParamList = insertSubjectParam.getOptionParamList();
            if (null != optionParamList) {
                int i = 1;
                for (QoptionParam optionParam : optionParamList) {
                    Qoption option = new Qoption();
                    BeanUtils.copyProperties(optionParam, option);
                    option.setSubId(subject.getId());
                    option.setOpOrder(i);
                    option.setDel(0);
                    option.setCreater(1);
                    option.setCreateTime(new Date());
                    option.setUpdater(1);
                    option.setUpdateTime(new Date());
                    optionMapper.insert(option);
                    i++;
                    optionList.add(option);
                }
                subjectVo.setOptionList(optionList);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return subjectVo;
    }

    @Override
    public SubjectVo updateQsubjectById(SubjectEditParam subjectEditParam) {
        SubjectVo subjectVo = new SubjectVo();
        Qsubject subject = new Qsubject();
        try {
            BeanUtils.copyProperties(subjectEditParam, subject);
            //如果是分组题，计算分组题号字段
            if (subjectEditParam.getSubType() != null) {
                Map<String, String> existCache = new HashMap<>();
                if (subjectEditParam.getSubType().equals("8")) {
                    String[] gids = subjectEditParam.getGroupIds();
                    StringBuffer groupIds = new StringBuffer();
                    if (null != gids) {
                        for (String gid : gids) {
                            groupIds.append(gid);
                            groupIds.append(",");
                            existCache.put(gid, "1");
                        }
                    }
                    subject.setGroupIds(groupIds.toString());
                }
                //如果其他分组题中groupIds字段包含此题中的groupIds,删除
                //查询此问卷下所有的分组题
                Map<String, Object> groupParam = new HashMap();
                groupParam.put("quId", subjectEditParam.getQuId());
                groupParam.put("subId", subjectEditParam.getId());
                List<Qsubject> qsubjectList = qsubjectMapper.selectGroupQsubjectByQuId(groupParam);
                for (Qsubject qsubject : qsubjectList) {
                    StringBuffer groupIdsUpdate = new StringBuffer();
                    String groupIdsDB = qsubject.getGroupIds();
                    if (groupIdsDB != null && groupIdsDB.length() > 0) {
                        String[] gidsdb = groupIdsDB.split(",");
                        for (String giddb : gidsdb) {
                            if (existCache.get(giddb) == null) {
                                groupIdsUpdate.append(giddb);
                                groupIdsUpdate.append(",");
                            }
                        }
                        Qsubject qsubjectUpdate = new Qsubject();
                        qsubjectUpdate.setId(qsubject.getId());
                        qsubjectUpdate.setGroupIds(groupIdsUpdate.toString());
                        qsubjectMapper.updateById(qsubjectUpdate);
                    }
                }
            }
            subject.setUpdater(1);
            subject.setUpdateTime(new Date());
            qsubjectMapper.updateById(subject);
            //拷贝到Vo对象
            BeanUtils.copyProperties(subject, subjectVo);
            //删除以前的所有选项
            //int delCount = optionMapper.deleteOptionBySubId(subject.getId());
            //选项
            List<Qoption> optionList = new ArrayList<>();
            List<QoptionParam> optionParamList = subjectEditParam.getOptionParamList();
            if (null != optionParamList) {
                int i = 1;
                for (QoptionParam optionParam : optionParamList) {
                    Qoption option = new Qoption();
                    BeanUtils.copyProperties(optionParam, option);
                    option.setSubId(subject.getId());
                    option.setOpOrder(i);
                    option.setDel(0);
                    option.setCreater(1);
                    option.setCreateTime(new Date());
                    option.setUpdater(1);
                    option.setUpdateTime(new Date());
                    if (option.getId() != null && option.getId() != 0) {//如果有id，更新
                        optionMapper.updateById(option);
                    } else {
                        optionMapper.insert(option);
                    }
                    i++;
                    optionList.add(option);
                }
                //删除应该删除的选项


                subjectVo.setOptionList(optionList);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return subjectVo;
    }

    @Override
    public Boolean removeSubjectById(Integer id) {
        Boolean delFlag = true;
        try {
            Qsubject subject = new Qsubject();
            subject.setId(id);
            subject.setDel(1);
            subject.setUpdater(1);
            subject.setUpdateTime(new Date());
            qsubjectMapper.updateById(subject);
            //如果此题在分组中，删除分组题中的groupIds
            Qsubject groupQsubject = qsubjectMapper.selectIdByGroupIdsLike(id);
            if (groupQsubject != null) {
                Integer groupSubId = groupQsubject.getId();
                String groupIds = groupQsubject.getGroupIds();
                String[] gids = groupIds.split(",");
                StringBuffer groupIdsNew = new StringBuffer();
                for (String gid : gids) {
                    if (id != Integer.parseInt(gid)) {
                        groupIdsNew.append(gid);
                        groupIdsNew.append(",");
                    }
                }
                Qsubject qsubjectUpdate = new Qsubject();
                qsubjectUpdate.setId(groupSubId);
                qsubjectUpdate.setGroupIds(groupIdsNew.toString());
                qsubjectMapper.updateById(qsubjectUpdate);
            }
        } catch (Exception e) {
            delFlag = false;
            log.error(e.getMessage(), e);
        }
        return delFlag;
    }

    @Override
    public Boolean updateOrderNum(UpdateOrderNumParam updateOrderNumParam) {
        Boolean flag = true;
        try {
            Qsubject qsubjecta = qsubjectMapper.selectById(updateOrderNumParam.getIda());
            Qsubject qsubjectb = qsubjectMapper.selectById(updateOrderNumParam.getIdb());
            int orderNuma = qsubjecta.getOrderNum();
            int orderNumb = qsubjectb.getOrderNum();
            qsubjecta.setOrderNum(orderNumb);
            qsubjectb.setOrderNum(orderNuma);
            qsubjectMapper.updateById(qsubjecta);
            qsubjectMapper.updateById(qsubjectb);
            //把分组的groupIds字段调换顺序
            int ida = qsubjecta.getId();
            int idb = qsubjectb.getId();
            Qsubject groupQsubject = qsubjectMapper.selectIdByGroupIdsLike(ida);
            if (groupQsubject != null) {
                Integer groupSubId = groupQsubject.getId();
                String groupIds = groupQsubject.getGroupIds();
                String[] gids = groupIds.split(",");
                int ida_index = 0;
                int idb_index = 0;
                for (int i = 0; i < gids.length; i++) {
                    String val = gids[i];
                    if (ida == Integer.parseInt(val)) {
                        ida_index = i;
                    }
                    if (idb == Integer.parseInt(val)) {
                        idb_index = i;
                    }
                }
                gids[ida_index] = String.valueOf(idb);
                gids[idb_index] = String.valueOf(ida);
                StringBuffer groupIdsNew = new StringBuffer();
                for (String id : gids) {
                    groupIdsNew.append(id);
                    groupIdsNew.append(",");
                }
                Qsubject qsubjectUpdate = new Qsubject();
                qsubjectUpdate.setId(groupSubId);
                qsubjectUpdate.setGroupIds(groupIdsNew.toString());
                qsubjectMapper.updateById(qsubjectUpdate);
            }
        } catch (Exception e) {
            flag = false;
            log.error(e.getMessage(), e);
        }
        return flag;
    }


}
