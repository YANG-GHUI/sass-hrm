package com.ygh.ihrm.company.service;

import com.ygh.ihrm.domain.company.Company;

import java.util.List;

/**
 * @author YangGh
 * @version 1.0
 */
public interface CompanyService {

    /**
     * 保存企业
     */
    void addCompany(Company company);

    /**
     * 更新企业
     */
    void updateCompany(Company company);

    /**
     * 删除企业
     */
    void deleteCompanyById(String id);

    /**
     * 根据id查询企业
     */
    Company findCompanyById(String id);

    /**
     * 查询企业列表
     */
    List<Company> findAllCompany();

    void auditCompany(Company company);
}
