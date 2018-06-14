package com.wsmedian.repository;

import com.wsmedian.model.QueryHistory;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by szagoret
 */

public interface QueryHistoryRepository extends CrudRepository<QueryHistory, String> {
}
