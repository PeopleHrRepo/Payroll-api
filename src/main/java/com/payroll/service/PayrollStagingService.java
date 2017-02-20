package com.payroll.service;
import java.util.List;

import com.payroll.entity.PayrollStagingRecord;
import com.ptg.payroll.domain.payroll.PayGroup;
public interface PayrollStagingService {
	
	public void insertStagingRecords(List<PayrollStagingRecord> records, PayGroup paygroup, String source);
	public void publishStagingRecords(PayGroup payGroup, long profileId, String source, String userEmployeeId);
	
}