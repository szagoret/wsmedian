package com.rapidminer.wsmedian.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * Created by szagoret
 */

@Document
public class QueryHistory {

    private final Map<String, Map<String, Double>> result;
    private final Long duration;
    private final Date date;
    private final String host;

    public QueryHistory(Map<String, Map<String, Double>> result, Long duration, Date date, String host) {
        this.result = result;
        this.duration = duration;
        this.date = date;
        this.host = host;
    }

    public Map<String, Map<String, Double>> getResult() {
        return result;
    }

    public Long getDuration() {
        return duration;
    }

    public Date getDate() {
        return date;
    }

    public String getHost() {
        return host;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryHistory that = (QueryHistory) o;
        return Objects.equals(result, that.result) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(date, that.date) &&
                Objects.equals(host, that.host);
    }

    @Override
    public int hashCode() {

        return Objects.hash(result, duration, date, host);
    }
}
