package com.payroll.dao;

import java.util.List;

import com.payroll.entity.PayrollStagingRecord;
import com.ptg.payroll.domain.payroll.PayGroup;

public interface PayrollStagingDao {
	void insertStagingRecords(List<PayrollStagingRecord> records, PayGroup paygroup, String source);
	void publishStagingRecords(PayGroup payGroup, long profileId, String source, String userEmployeeId);
	int deleteStagingRecords(PayGroup payGroup, long profileId, String source);
	int deleteStagingRecords(PayGroup payGroup, String source);
	}