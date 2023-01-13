package com.ygh.ihrm.company;

import com.ygh.ihrm.company.dao.CompanyDao;
import com.ygh.ihrm.domain.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyTest {

    @Autowired
    private CompanyDao companyDao;

    @Test
    public void test01() {
        Company company = companyDao.findById("1").get();
        System.out.printf("公司1：" + company);
    }
}
