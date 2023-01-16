package com.ygh.ihrm.common.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author YangGh
 * @version 1.0
 */
public class BaseController<T> {

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected String companyId;

    protected String companyName;

    @ModelAttribute
    public void setResAnReq(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.companyId = "1";
        this.companyName = "龙小院";
    }
}
