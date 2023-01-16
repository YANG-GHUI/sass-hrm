package com.ygh.ihrm.company.service.impl;

import com.ygh.ihrm.common.service.BaseService;
import com.ygh.ihrm.common.utils.IdWorker;
import com.ygh.ihrm.company.dao.DepartmentDao;
import com.ygh.ihrm.company.service.DepartmentService;
import com.ygh.ihrm.domain.company.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author YangGh
 * @version 1.0
 */
@Service
public class DepartmentServiceImpl extends BaseService<Department> implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private IdWorker idWorker;

    // 添加部门
    @Override
    public void addDepartment(Department department) {
        // 基本属性的设置
        String id = idWorker.nextId() + "";
        department.setId(id);
        departmentDao.save(department);
    }

    // 更新部门
    @Override
    public void updateDepartment(Department department) {
        Department temp = departmentDao.findById(department.getId()).get();
        if (department.getCompanyId() != null) {
            temp.setCompanyId(department.getCompanyId());
        }
        if (department.getCode() != null) {
            temp.setCode(department.getCode());
        }
        if (department.getIntroduce() != null) {
            temp.setIntroduce(department.getIntroduce());
        }
        if (department.getName() != null) {
            temp.setName(department.getName());
        }
        if (department.getPid() != null) {
            temp.setPid(department.getPid());
        }
        departmentDao.save(temp);
    }

    // 根据id删除部门
    @Override
    public void deleteDepartmentById(String id) {
        departmentDao.deleteById(id);
    }

    // 根据id查询部门
    @Override
    public Department findDepartmentById(String id) {
        return departmentDao.findById(id).get();
    }

    // 查询部门列表
    @Override
    public List<Department> findAllDepartment(String companyId) {
        /**
         * 用户构造查询条件
         * root：包含了所有的对象数据
         * criteriaQuery：构造高级查询条件
         * criteriaBuilder：构造查询条件
         */
//        Specification<Department> spec = (root, criteriaQuery, criteriaBuilder) ->
//                criteriaBuilder.equal(root.get("companyId").as(String.class), companyId);

        return departmentDao.findAll(getSpec(companyId));
    }
}
