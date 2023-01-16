package com.ygh.ihrm.company.service.impl;

import com.ygh.ihrm.common.utils.IdWorker;
import com.ygh.ihrm.company.dao.CompanyDao;
import com.ygh.ihrm.company.service.CompanyService;
import com.ygh.ihrm.domain.company.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YangGh
 * @version 1.0
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private IdWorker idWorker;

    // 添加企业
    @Override
    public void addCompany(Company company) {
        // 基本属性的设置
        String id = idWorker.nextId() + "";
        company.setId(id);
        // 默认状态
        company.setAuditState("0"); // 0:未审核，1:已审核
        company.setState("1"); // 0:未激活，1:已激活
        companyDao.save(company);
    }

    // 更新企业
    @Override
    public void updateCompany(Company company) {
        Company temp = companyDao.findById(company.getId()).get();
//        BeanUtils.copyProperties(company, temp);
        if (company.getCompanyPhone() != null) {
            temp.setCompanyPhone(company.getCompanyPhone());
        }
        if (company.getCompanySize() != null) {
            temp.setCompanySize(company.getCompanySize());
        }
        if (company.getCompanyArea() != null) {
            temp.setCompanyArea(company.getCompanyArea());
        }
        if (company.getCompanyAddress() != null) {
            temp.setCompanyAddress(company.getCompanyAddress());
        }
        if (company.getLegalRepresentative() != null) {
            temp.setLegalRepresentative(company.getLegalRepresentative());
        }
        if (company.getBalance() != null) {
            temp.setBalance(company.getBalance());
        }
        if (company.getIndustry() != null) {
            temp.setIndustry(company.getIndustry());
        }
        if (company.getMailbox() != null) {
            temp.setMailbox(company.getMailbox());
        }
        if (company.getRemarks() != null) {
            temp.setRemarks(company.getRemarks());
        }
        companyDao.save(temp);
    }

    // 根据id删除企业
    @Override
    public void deleteCompanyById(String id) {
        companyDao.deleteById(id);
    }

    // 根据id查询企业
    @Override
    public Company findCompanyById(String id) {
        return companyDao.findById(id).get();
    }

    // 查询企业列表
    @Override
    public List<Company> findAllCompany() {
        return companyDao.findAll();
    }

    // 审核企业
    @Override
    public void auditCompany(Company company) {
        Company temp = companyDao.findById(company.getId()).get();
        temp.setRemarks(company.getRemarks()); // 添加备注
        temp.setAuditState(company.getAuditState()); // 添加审核状态
        companyDao.save(temp);
    }
}
