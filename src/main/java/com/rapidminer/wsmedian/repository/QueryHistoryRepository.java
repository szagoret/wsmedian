package com.rapidminer.wsmedian.repository;

import com.rapidminer.wsmedian.model.QueryHistory;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by szagoret
 */

public interface QueryHistoryRepository extends CrudRepository<QueryHistory, String> {
}
