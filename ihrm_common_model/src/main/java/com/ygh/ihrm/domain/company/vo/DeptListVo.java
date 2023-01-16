package com.ygh.ihrm.domain.company.vo;

import com.ygh.ihrm.domain.company.Company;
import com.ygh.ihrm.domain.company.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author YangGh
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptListVo {
    private String companyId;
    private String companyName;
    private String companyManager;
    private List<Department> depts;

    public DeptListVo(Company company, List<Department> depts) {
        this.companyId = company.getId();
        this.companyName = company.getName();
        this.companyManager = company.getLegalRepresentative();
        this.depts = depts;
    }
}
