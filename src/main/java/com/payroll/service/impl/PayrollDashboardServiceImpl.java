package com.payroll.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.payroll.constant.PtgGatewayConstants;
import com.payroll.dao.PayrollDao;
import com.payroll.dao.PayrollDataAccessDao;
import com.payroll.dao.PayrollDataEntryDao;
import com.payroll.dao.PayrollEntryDao;
import com.payroll.dao.PayrollProcessDao;
import com.payroll.dao.PayrollStagingDao;
import com.payroll.entity.PayrollEarningCode;
import com.payroll.entity.PayrollHolidanBean;
import com.payroll.entity.PayrollStagingRecord;
import com.payroll.entity.TimesheetPayrollGroup;
import com.payroll.entity.ps.ClientOption;
import com.payroll.entity.ps.payrollentry.EmployeeDOM;
import com.payroll.mapper.PayrollDashboardMapper;
import com.payroll.mapper.PayrollDataAccessMapper;
import com.payroll.service.PayrollDashboardService;
import com.payroll.utils.CommonUtils;
import com.payroll.utils.DateUtils;
import com.payroll.utils.PayrollDataAdapter;
import com.ptg.payroll.db.sp.payroll.payrollentry.DeleteSpecialPayrollSpService;
import com.ptg.payroll.db.sp.payroll.payrollentry.PayGroupByCompanyPersonSpService;
import com.ptg.payroll.db.sp.payroll.payrollentry.PayrollGroupsByRoleSpService;
import com.ptg.payroll.db.sp.payroll.payrollentry.PayrollGroupsSpService;
import com.ptg.payroll.db.sp.payroll.payrollentry.InsertSpecialPayrollSpService;
import com.ptg.payroll.domain.PayGroupDetail;
import com.ptg.payroll.domain.Payroll;
import com.ptg.payroll.domain.PayrollByRole;
import com.ptg.payroll.domain.PayrollGroup;
import com.ptg.payroll.domain.PayrollList;
import com.ptg.payroll.domain.PayrollUserSecurity;
import com.ptg.payroll.domain.SortApprovalConfiguration;
import com.ptg.payroll.domain.SpecialPayroll;
import com.ptg.payroll.domain.payroll.PayGroup;
import com.ptg.payroll.domain.payroll.PayrollEmployee;
import com.ptg.payroll.domain.payroll.SpecialPayrollInfo;
import com.ptg.payroll.entity.ps.pe.dashboard.PayrollDeadline;




public class PayrollDashboardServiceImpl implements PayrollDashboardService {

    private static final Logger log = LoggerFactory.getLogger(PayrollDashboardServiceImpl.class);


    @Autowired
    private PayrollDataAccessDao payrollDataAccessDao;

    @Autowired
    private PayrollDataEntryDao payrollDataEntryDao;

    @Autowired
    private PayrollStagingDao payrollStagingDao;
    
    @Autowired
    private PayrollEntryDao payrollEntryDao;

    @Autowired
    private PayrollDao payrollDao;
    
    @Autowired
    private PayrollProcessDao payrollProcessDao;
    
    @Autowired
    private InsertSpecialPayrollSpService spInsertSpecialPayroll;

    @Autowired
    private DeleteSpecialPayrollSpService spDeleteSpecialPayroll;

    @Autowired
    private PayrollGroupsSpService spGetPayrollGroups;
    
    @Autowired
    private PayrollGroupsByRoleSpService spGetPayrollGroupsByRole;       

    @Autowired
    private PayGroupByCompanyPersonSpService spGetPayGroupByCompanyPerson; 
    
  

    
    
    public void setPayrollDataAccessDao(PayrollDataAccessDao payrollDataAccessDao) {
		this.payrollDataAccessDao = payrollDataAccessDao;
	}

	public void setSpInsertSpecialPayroll(InsertSpecialPayrollSpService spInsertSpecialPayroll) {
		this.spInsertSpecialPayroll = spInsertSpecialPayroll;
	}

	public void setSpDeleteSpecialPayroll(DeleteSpecialPayrollSpService spDeleteSpecialPayroll) {
		this.spDeleteSpecialPayroll = spDeleteSpecialPayroll;
	}

	public void setPayrollDataEntryDao(PayrollDataEntryDao payrollDataEntryDao) {
		this.payrollDataEntryDao = payrollDataEntryDao;
	}

	public void setPayrollStagingDao(PayrollStagingDao payrollStagingDao) {
		this.payrollStagingDao = payrollStagingDao;
	}

	public void setSpGetPayrollGroups(PayrollGroupsSpService spGetPayrollGroups) {
		this.spGetPayrollGroups = spGetPayrollGroups;
	}

	public void setSpGetPayrollGroupsByRole(PayrollGroupsByRoleSpService spGetPayrollGroupsByRole) {
		this.spGetPayrollGroupsByRole = spGetPayrollGroupsByRole;
	}

	public void setSpGetPayGroupByCompanyPerson(PayGroupByCompanyPersonSpService spGetPayGroupByCompanyPerson) {
		this.spGetPayGroupByCompanyPerson = spGetPayGroupByCompanyPerson;
	}

	public void setPayrollEntryDao(PayrollEntryDao payrollEntryDao) {
		this.payrollEntryDao = payrollEntryDao;
	}

	public void setPayrollDao(PayrollDao payrollDao) {
		this.payrollDao = payrollDao;
	}

	public void setPayrollProcessDao(PayrollProcessDao payrollProcessDao) {
		this.payrollProcessDao = payrollProcessDao;
	}

	@Override
    public List<Payroll> getPayrollGroups(String company, Date startDate, Date endDate) {

        return spGetPayrollGroups.execute(company, startDate, endDate);

    }
    
    /*@Override
    public List<PayrollList> getPayrollGroupsList(String company, String personId, Date startDate, Date endDate) {
        List<PayrollList> payrollsList = new ArrayList<PayrollList>();
        boolean hasParent = false;
        
        List<PayrollByRole> payrollByRoleList = spGetPayrollGroupsByRole.execute(company, personId, startDate, endDate);        
        String userRole = payrollDao.getUserRole(company, personId);
        
        if(payrollByRoleList == null){
        	return payrollsList;
        }
        for(PayrollByRole payrollByRole : payrollByRoleList) {
            if("1".equalsIgnoreCase(payrollByRole.getIsParent())) {
                hasParent = true;
                break;
            }
        }
        
        
        if(hasParent) {
            PayrollList payrollList = null;
            List<PayrollByRole> childPayrolls = new ArrayList<PayrollByRole>();
            for (Iterator<PayrollByRole> it = payrollByRoleList.iterator(); it.hasNext();) {
                PayrollByRole payrollByRole = it.next();
                if("1".equalsIgnoreCase(payrollByRole.getIsParent())) {
                    if(payrollList != null) {
                        payrollList.setChildPayrolls(childPayrolls);
                        payrollsList.add(payrollList);
                        childPayrolls = new ArrayList<PayrollByRole>();
                    }
                    payrollList = new PayrollList();
                  //TODO put highest role in config and then check
                    if("EPTAG".equals(userRole)){
                    	payrollList.setParentPayroll(payrollByRole);	
                    }else{
                    	payrollList.setParentPayroll(null);
                    }
                    
                } else {
                	//prevent this for offcycle
                	if("N".equals(payrollByRole.getPayrollGroup().getOffCycle())){
                		payrollByRole.setIsParent("2");
                    	///quick fix to update status from child status, child properties will be ignored in UI
                    	//we will probably change this payrollByROle with payrolls only if need
                    	payrollByRole.getPayrollGroup().setStatus(payrollByRole.getChildPayrollStatus());
                    	
                    	payrollByRole.getPayGroupDetail().setPayrollStatusDesc(payrollByRole.getChildPayrollStatusDesc());
                    	payrollByRole.getPayGroupDetail().setOperatorId(payrollByRole.getChildOperatorId());
                    	payrollByRole.getPayGroupDetail().setOperatorName(payrollByRole.getChildOperatorName());
                    	payrollByRole.getPayGroupDetail().setLastUpdatedDate(payrollByRole.getChildLastUpdatedDate());
                    	
                        childPayrolls.add(payrollByRole);	
                	}
                }
                if(!it.hasNext()) {
                	payrollList.setChildPayrolls(childPayrolls);
                	payrollsList.add(payrollList);
                }
            }
        } else {
            for (PayrollByRole payrollByRole : payrollByRoleList) {
                List<PayrollByRole> childPayrolls = new ArrayList<PayrollByRole>();
                
                payrollByRole.setIsParent("2");
            	///quick fix to update status from child status, child properties will be ignored in UI
            	//we will probably change this payrollByROle with payrolls only if need
            	payrollByRole.getPayrollGroup().setStatus(payrollByRole.getChildPayrollStatus());
            	
            	payrollByRole.getPayGroupDetail().setPayrollStatusDesc(payrollByRole.getChildPayrollStatusDesc());
            	payrollByRole.getPayGroupDetail().setOperatorId(payrollByRole.getChildOperatorId());
            	payrollByRole.getPayGroupDetail().setOperatorName(payrollByRole.getChildOperatorName());
            	payrollByRole.getPayGroupDetail().setLastUpdatedDate(payrollByRole.getChildLastUpdatedDate());
            	
                childPayrolls.add(payrollByRole);
                PayrollList payrollList = new PayrollList();
                payrollList.setChildPayrolls(childPayrolls);
                payrollsList.add(payrollList);
            }
        }
        return payrollsList;
    }*/
    
    
    @Override
    public List<PayrollList> getPayrollGroupsList(String company, String personId, Date startDate, Date endDate) {
        List<PayrollList> payrollsList = new ArrayList<PayrollList>();
        boolean hasParent = false;
        String userRole = payrollDao.getUserRole(company, personId);
        String payGroupStatus;
        int currentSortApprovalOrder = 0;
        
        List<PayrollByRole> payrollByRoleList = spGetPayrollGroupsByRole.execute(company, personId, startDate, endDate);
        
        if (!"EPTAG".equalsIgnoreCase(userRole)) {
            SortApprovalConfiguration currentSortApprovalConfig = payrollDao.getCompanyEffectiveCurrentSortApprovalOrder(company);
            if (currentSortApprovalConfig != null && currentSortApprovalConfig.getCurrentSortOrder().indexOf("4") != -1) {
                currentSortApprovalOrder = 1;
            }
        }
        if(CommonUtils.isResult(payrollByRoleList)){
        	for (PayrollByRole payrollByRole : payrollByRoleList) {
                payGroupStatus = payrollByRole.getPayrollGroup().getStatus();
                if ("S".equalsIgnoreCase(payGroupStatus)) {
                    PayrollGroup payrollGroup = payrollProcessDao.getPayrollEngineStatus(payrollByRole.getPayrollGroup());
                    if (payrollGroup != null && payrollGroup.isHasPayrollError()) {
                        payrollProcessDao.updatePayrollStatus(payrollByRole.getPayrollGroup(), payrollByRole.getPayGroupDetail().getOperatorId(), PtgGatewayConstants.PE_PROCESS_ERROR);
                        payrollByRole.getPayrollGroup().setStatus(PtgGatewayConstants.PE_PROCESS_ERROR);
                        payrollByRole.getPayGroupDetail().setPayrollStatusDesc(PtgGatewayConstants.PE_PROCESS_ERROR_TEXT);
                    }
                }
                
            }
            
            payrollsList = new PayrollDashboardMapper.EntityToDomainMapper().translate(payrollByRoleList, userRole, currentSortApprovalOrder);
        }
        
        
        return payrollsList;
    }

    
    
    @Override
    public SpecialPayroll getSpecialPayroll(PayGroup payGroup) {

        PayrollGroup payrollGroup = payrollDao.getPayrollGroup(payGroup);
        String defaultRegErnCd = payrollDataEntryDao.getDefaultEarnCode(payGroup);
        List<EmployeeDOM> employees = payrollDataAccessDao.getPayrollEmployees(payGroup);
        List<PayrollEarningCode> earningCodes = payrollDataAccessDao.getPayrollEarningCodes(payGroup);

        List<String> usedEarnCodes = payrollDataAccessDao.getReportedEarnCodesForPayroll(payGroup);

        SpecialPayroll specialPayroll = new PayrollDashboardMapper.EntityToDomainMapper().translate(payrollGroup, employees, earningCodes, usedEarnCodes);
        specialPayroll.getPayroll().getPayGroupDetail().setDefaultRegEarnCode(defaultRegErnCd);
        return specialPayroll;
    }

    @Override
    public void createOrUpdateSpecialPayroll(SpecialPayroll specialPayroll, String operatorId) {
        List<PayrollEmployee> employeesList = new ArrayList<PayrollEmployee>();
        PayGroup payGroup = specialPayroll.getPayroll().getPayrollGroup();
        String defaultRegErnCd = payrollDataEntryDao.getDefaultEarnCode(payGroup);
        // Get employees only for update special payroll
        if (null != payGroup.getId()) {
            List<EmployeeDOM> employees = payrollDataAccessDao.getPayrollEmployees(payGroup);
            employeesList = new PayrollDashboardMapper.EntityToDomainMapper().translateEmployees(employees);
        }
        Map<String, List<PayrollEmployee>> updatedEmployees = PayrollDashboardMapper.findUpdatedEmployees(employeesList, specialPayroll.getEmployees());

        //Set OperatorId- the one send from UI is last updated user for the pay group and not the current user 
        specialPayroll.getPayroll().getPayGroupDetail().setOperatorId(operatorId);
        
        // Create Special Payroll
        spInsertSpecialPayroll.execute(specialPayroll);
        
        

        PayrollDataAccessMapper.DomainToEntityMapper domainToEntityMapper = new PayrollDataAccessMapper.DomainToEntityMapper();
        List<PayrollEmployee> newRecords = updatedEmployees.get(PayrollDashboardMapper.NEW_RECORD);
        List<PayrollEmployee> deleteRecords = updatedEmployees.get(PayrollDashboardMapper.DELETE_RECORD);
        List<PayrollStagingRecord> stageRecords = new ArrayList<PayrollStagingRecord>();

        if (newRecords != null && newRecords.size() > 0) {
            stageRecords.addAll(domainToEntityMapper.translateEmployees(newRecords, payGroup, defaultRegErnCd, true,
                    PtgGatewayConstants.STAGE_SPECIAL_PAYROLL_EMPLOYEE));
        }
        if (deleteRecords != null && deleteRecords.size() > 0) {
            stageRecords.addAll(domainToEntityMapper.translateEmployees(deleteRecords, payGroup, defaultRegErnCd, false,
            		PtgGatewayConstants.STAGE_SPECIAL_PAYROLL_EMPLOYEE));
        }
        
        //Insert earning codes
        List<PayrollEarningCode> payrollEaningCodes = domainToEntityMapper.translateEarningCode(specialPayroll.getEarningCodes(), payGroup, operatorId);
        payrollDataAccessDao.insertPayrollEarningCodes(payGroup, payrollEaningCodes);

        // Update Employees list in stage table
        payrollStagingDao.insertStagingRecords(stageRecords, payGroup, PtgGatewayConstants.STAGE_SPECIAL_PAYROLL_EMPLOYEE);
        payrollStagingDao.publishStagingRecords(payGroup, 0, PtgGatewayConstants.STAGE_SPECIAL_PAYROLL_EMPLOYEE, operatorId);

        /*
        List<PayrollEarningCode> existingEarningCodes = payrollDataAccessDao.getPayrollEarningCodes(payGroup);
        List<String> deletedEarnCodes = PayrollDashboardMapper.findDeletedEarnCodes(existingEarningCodes, payrollEaningCodes);
        if (deletedEarnCodes.size() > 0) {
            String deletdEarnCodeString = StringUtils.collectionToDelimitedString(deletedEarnCodes, ",", "'", "'");
            payrollDataAccessDao.deleteReportedEarnCodeRecords(deletdEarnCodeString, payGroup);
        }
        */

        if (specialPayroll.getPayroll().getPayrollGroup().getId() == 0) {
            PayrollGroup payrollGroup = payrollDao.getPayrollGroup(payGroup);
            payGroup = new PayrollDashboardMapper.EntityToDomainMapper().translatePayrollGroup(payrollGroup);
            specialPayroll.getPayroll().setPayrollGroup(payGroup);
        }
    }

    @Override
    public void deleteSpecialPayroll(PayGroup payGroup, PayrollUserSecurity payrollUserSecurity, String operatorId ) {
        boolean delete = false; 
        String message = "";
    	if(payrollUserSecurity.hasPermission(PayrollUserSecurity.DELETE_ALL_SPECIALPAYROLL)) {
    		delete = true;
        } 
 		else if(payGroup.getStatus().equalsIgnoreCase("L")){
    		message = "Payroll submitted for pre-approval can not be deleted!";
    	}
        else if (!payrollProcessDao.verifySpecialPayrollCreatedUsr(payGroup, operatorId)){
        	message = "You cannot delete a payroll that was created by another payroll user.";
        }
        else if(payrollProcessDao.verifySpecialPayrollUpdatedUsrs(payGroup, operatorId)){
        	message = "You cannot delete this payroll because it has been modified by another payroll user.";
        }
        else {
        	delete = true;
        }
              
    	
    	if(delete)
    		spDeleteSpecialPayroll.execute(payGroup, "D");
    	else {
    		throw new IllegalArgumentException(message);
    	}
		
		
    }

    @Override
    public void updatePayrollCheckDate(PayGroup payGroup, Date checkDate) {
        PayrollGroup payrollGroup = payrollDao.getPayrollGroup(payGroup);
        SpecialPayroll specialPayroll = new PayrollDashboardMapper.EntityToDomainMapper().translate(payrollGroup, new ArrayList(), new ArrayList(),
                new ArrayList());

        PayGroupDetail payGroupDetails = specialPayroll.getPayroll().getPayGroupDetail();
        payGroupDetails.setCheckDate(checkDate);

        spInsertSpecialPayroll.execute(specialPayroll);
    }

    @Override
    public PayrollDeadline getPayrollDeadline(String company) {
        return payrollDao.getPayrollDeadline(company);
    }

    @Override
    public SpecialPayrollInfo getSpecialPayrollPageInfo(String companyId) {
        SpecialPayrollInfo specialPayroll = new SpecialPayrollInfo();
        try {
            ClientOption clientOption = payrollEntryDao.getClientOption(companyId);
            List<PayrollHolidanBean> holidays = payrollDao.getHolidays(companyId);
            List<TimesheetPayrollGroup> payGroups = payrollDao.getOnCyclePayrolls(companyId);

            String companyAddress = payrollDao.getCompanyAddress(companyId);

            Date clientDate = PayrollDataAdapter.convertDateToNewTimeZone(new Date(), TimeZone.getTimeZone(clientOption.getTimeZoneStr()));
            SimpleDateFormat dateFormater = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
            String webDeadline = clientOption.getOffCycleWebDeadline() != null ? clientOption.getOffCycleWebDeadline() : "10:00:00 AM";
            Date payrollDeadlineDate = sdf.parse(dateFormater.format(new Date()) + " " + webDeadline);

            specialPayroll.setCheckReportLeadDate(clientOption.getCheckReportLeadDate());
            specialPayroll.setAdviceReportLeadDate(clientOption.getAdviceReportLeadDate());
            specialPayroll.setReportTime(clientOption.getOffCycleWebDeadline() + " " + clientOption.getTimeZone());
            specialPayroll.setPayGroups(payGroups);
            specialPayroll.setHolidays(holidays);
            specialPayroll.setClientTodaysDate(clientDate);
            specialPayroll.setCompanyAddress(companyAddress);

            boolean isAmbroseClient = "AMB".equalsIgnoreCase(clientOption.getPeoId());
            
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date todaysDate = df.parse(df.format(new Date()));
            boolean isClientDateAHoliday = false;
            for (PayrollHolidanBean holiday : holidays) {
                if (todaysDate.compareTo(holiday.getHoliday()) == 0) {
                    isClientDateAHoliday = true;
                    break;
                }
            }
            
            // If Client date is passed payroll deadline
            if (clientDate.compareTo(payrollDeadlineDate) > 0 && !isClientDateAHoliday) {
                if (isAmbroseClient) {
                    specialPayroll.setCheckStartDay(clientOption.getCheckReportLeadDate().intValue() + 1);
                    specialPayroll.setDirectDepositStartDay(clientOption.getAdviceReportLeadDate().intValue() + 1);
                } else {
                    specialPayroll.setCheckStartDay(2);
                    specialPayroll.setDirectDepositStartDay(3);
                }
            } else {
                if (isAmbroseClient) {
                    specialPayroll.setCheckStartDay(clientOption.getCheckReportLeadDate().intValue());
                    specialPayroll.setDirectDepositStartDay(clientOption.getAdviceReportLeadDate().intValue());
                } else {
                    specialPayroll.setCheckStartDay(1);
                    specialPayroll.setDirectDepositStartDay(2);
                }
            }
        } catch (Exception e) {
            log.error("Exception occured while get special payroll profile info :" + e.toString());
        }
        return specialPayroll;
    }
    @Override
    public SpecialPayrollInfo getSpecialPayrollPageInfo(String companyId,String personid) {
        SpecialPayrollInfo specialPayroll = new SpecialPayrollInfo();
        try {
            ClientOption clientOption = payrollEntryDao.getClientOption(companyId);
            List<PayrollHolidanBean> holidays = payrollDao.getHolidays(companyId);
            List<TimesheetPayrollGroup> payGroups = spGetPayGroupByCompanyPerson.execute(companyId, personid);    
            String companyAddress = payrollDao.getCompanyAddress(companyId);

            Date clientDate = PayrollDataAdapter.convertDateToNewTimeZone(new Date(), TimeZone.getTimeZone(clientOption.getTimeZoneStr()));
            SimpleDateFormat dateFormater = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
            String webDeadline = clientOption.getOffCycleWebDeadline() != null ? clientOption.getOffCycleWebDeadline() : "10:00:00 AM";
            Date payrollDeadlineDate = sdf.parse(dateFormater.format(new Date()) + " " + webDeadline);

            specialPayroll.setCheckReportLeadDate(clientOption.getCheckReportLeadDate());
            specialPayroll.setAdviceReportLeadDate(clientOption.getAdviceReportLeadDate());
            specialPayroll.setReportTime(clientOption.getOffCycleWebDeadline() + " " + clientOption.getTimeZone());
            specialPayroll.setPayGroups(payGroups);
            specialPayroll.setHolidays(holidays);
            specialPayroll.setClientTodaysDate(clientDate);
            specialPayroll.setCompanyAddress(companyAddress);

            boolean isAmbroseClient = "AMB".equalsIgnoreCase(clientOption.getPeoId());
            
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date todaysDate = df.parse(df.format(new Date()));
            boolean isClientDateAHoliday = false;
            for (PayrollHolidanBean holiday : holidays) {
                if (todaysDate.compareTo(holiday.getHoliday()) == 0) {
                    isClientDateAHoliday = true;
                    break;
                }
            }
            
            // If Client date is passed payroll deadline
            if (clientDate.compareTo(payrollDeadlineDate) > 0 && !isClientDateAHoliday) {
                if (isAmbroseClient) {
                    specialPayroll.setCheckStartDay(clientOption.getCheckReportLeadDate().intValue() + 1);
                    specialPayroll.setDirectDepositStartDay(clientOption.getAdviceReportLeadDate().intValue() + 1);
                } else {
                    specialPayroll.setCheckStartDay(2);
                    specialPayroll.setDirectDepositStartDay(3);
                }
            } else {
                if (isAmbroseClient) {
                    specialPayroll.setCheckStartDay(clientOption.getCheckReportLeadDate().intValue());
                    specialPayroll.setDirectDepositStartDay(clientOption.getAdviceReportLeadDate().intValue());
                } else {
                    specialPayroll.setCheckStartDay(1);
                    specialPayroll.setDirectDepositStartDay(2);
                }
            }
        } catch (Exception e) {
            log.error("Exception occured while get special payroll profile info :" + e.toString());
        }
        return specialPayroll;
    }
    @Override
    public void updateIsRecallable(List<Payroll> payrolls){
    	if (payrolls !=null && payrolls.size() > 0){

    		PayrollDeadline payrollDeadline = this.getPayrollDeadline(payrolls.get(0).getPayrollGroup().getCompany());

    		SimpleDateFormat dateFormater = new SimpleDateFormat("MM/dd/yyyy");
    		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    		Date timeentryDeadlineDate = 	null;

    		// Get current server time based on User timezone
    		String userTimeZone = payrollDeadline.getTimeZoneFullText();
    		TimeZone toTimeZone = TimeZone.getTimeZone("US/Eastern");
    		if (userTimeZone != null) {
    			toTimeZone = TimeZone.getTimeZone(userTimeZone);
    		}

    		Date currentDate = DateUtils.convertDateToNewTimeZone(new Date(), toTimeZone);

    		for (Payroll payroll : payrolls) {
    			try {
    				timeentryDeadlineDate = sdf.parse(dateFormater.format(payroll.getPayrollGroup().getReportDate()) + " " +  payrollDeadline.getWebDeadline());
    			} catch (ParseException e) {
    				log.debug("Unable to parse the date");
    			}
    			boolean inEngine =  payrollDataAccessDao.checkPayrollBlackout(payroll.getPayrollGroup());
    			Date recallDateTime = DateUtils.reduceHoursAndMinutes(timeentryDeadlineDate, 0, 10);
    			if(currentDate.compareTo(recallDateTime) < 0 && !inEngine){
    				payroll.setRecallable(true);
    			} 

    		}
    	}
    }

}
