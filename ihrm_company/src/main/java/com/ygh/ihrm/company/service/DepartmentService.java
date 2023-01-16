package com.ygh.ihrm.company.service;

import com.ygh.ihrm.domain.company.Department;

import java.util.List;

public interface DepartmentService {
    /**
     * 保存部门
     */
    void addDepartment(Department department);

    /**
     * 更新部门
     */
    void updateDepartment(Department department);

    /**
     * 删除部门
     */
    void deleteDepartmentById(String id);

    /**
     * 根据id查询部门
     */
    Department findDepartmentById(String id);

    /**
     * 查询部门列表
     */
    List<Department> findAllDepartment(String companyId);
}
