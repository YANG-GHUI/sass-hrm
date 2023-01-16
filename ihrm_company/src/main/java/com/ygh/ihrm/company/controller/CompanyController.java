package com.ygh.ihrm.company.controller;

import com.ygh.ihrm.common.entity.Result;
import com.ygh.ihrm.common.entity.ResultCode;
import com.ygh.ihrm.company.service.CompanyService;
import com.ygh.ihrm.domain.company.Company;
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

@Api(tags = "企业管理接口")
@RestController
@RequestMapping("/company")
@CrossOrigin
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @ApiOperation("添加企业")
    @PostMapping
    public Result saveCompany(@ApiParam(name = "company", value = "企业实体对象")
                              @RequestBody Company company) {
        companyService.addCompany(company);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation("更新企业")
    @PutMapping
    public Result updateCompany(@ApiParam(name = "company", value = "企业实体对象")
                                @RequestBody Company company) {
        companyService.updateCompany(company);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation("根据id删除企业")
    @DeleteMapping("/{companyId}")
    public Result removeCompany(@ApiParam(name = "companyId", value = "企业id")
                                @PathVariable String companyId) {
        companyService.deleteCompanyById(companyId);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation("根据id查询企业")
    @GetMapping("/{companyId}")
    public Result findCompany(@ApiParam(name = "companyId", value = "企业id")
                              @PathVariable String companyId) {
        Company company = companyService.findCompanyById(companyId);
        return new Result(ResultCode.SUCCESS, company);
    }

    @ApiOperation("查询所有企业")
    @GetMapping
    public Result getAllCompany() {
        List<Company> companyList = companyService.findAllCompany();
        return new Result(ResultCode.SUCCESS, companyList);
    }

    @ApiOperation("审核企业")
    @PutMapping("/auditCompany")
    public Result auditCompany(@ApiParam(name = "company", value = "企业对象")
                               @RequestBody Company company) {
        companyService.auditCompany(company);
        return new Result(ResultCode.SUCCESS);
    }
}
