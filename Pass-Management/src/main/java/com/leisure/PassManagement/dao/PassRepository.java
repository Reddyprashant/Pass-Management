package com.leisure.PassManagement.dao;

import com.leisure.PassManagement.model.Pass;
import org.springframework.data.repository.CrudRepository;

public interface PassRepository extends CrudRepository<Pass,String> {
}
