package com.pentaKill.dao;

import com.pentaKill.domain.CSManager;

public interface CustomerServiceMMapper {
    public CSManager selectBy(CSManager csManager);

    public void insert(CSManager csManager);
}
