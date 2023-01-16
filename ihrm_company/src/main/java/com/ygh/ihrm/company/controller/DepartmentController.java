package com.ygh.ihrm.company.controller;

import com.ygh.ihrm.common.entity.Result;
import com.ygh.ihrm.common.entity.ResultCode;
import com.ygh.ihrm.company.service.CompanyService;
import com.ygh.ihrm.company.service.DepartmentService;
import com.ygh.ihrm.domain.company.Company;
import com.ygh.ihrm.domain.company.Department;
import com.ygh.ihrm.domain.company.vo.DeptListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YangGh
 * @version 1.0
 */
@Api(tags = "部门管理接口")
@RestController
@CrossOrigin
@RequestMapping("/company/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CompanyService companyService;

    @ApiOperation("添加部门")
    @PostMapping
    public Result saveDepartment(@ApiParam(name = "companyId", value = "企业id")
                                 @RequestParam String companyId,
                                 @ApiParam(name = "department", value = "部门实体对象")
                                 @RequestBody Department department) {
        department.setCompanyId(companyId);
        departmentService.addDepartment(department);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation("更新部门")
    @PutMapping
    public Result updateDepartment(@ApiParam(name = "department", value = "部门实体对象")
                                   @RequestBody Department department) {
        departmentService.updateDepartment(department);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation("根据id删除部门")
    @DeleteMapping("/{departmentId}")
    public Result removeDepartment(@ApiParam(name = "departmentId", value = "部门id")
                                   @PathVariable String departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation("根据id查询部门")
    @GetMapping("/{departmentId}")
    public Result findDepartment(@ApiParam(name = "departmentId", value = "部门id")
                                 @PathVariable String departmentId) {
        Department department = departmentService.findDepartmentById(departmentId);
        return new Result(ResultCode.SUCCESS, department);
    }

    @ApiOperation("查询所属公司所有部门")
    @GetMapping
    public Result getAllDepartment(@ApiParam(name = "companyId", value = "企业id")
                                   @RequestParam String companyId) {
        Company company = companyService.findCompanyById(companyId);
        List<Department> departmentList = departmentService.findAllDepartment(companyId);
        DeptListVo deptListVo = new DeptListVo(company, departmentList);
        return new Result(ResultCode.SUCCESS, deptListVo);
    }
}
