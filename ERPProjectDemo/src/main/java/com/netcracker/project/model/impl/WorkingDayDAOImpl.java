package com.netcracker.project.model.impl;

import com.netcracker.project.model.entity.WorkingDay;
import java.math.BigInteger;
import java.util.Collection;
import javax.sql.DataSource;
import com.netcracker.project.model.WorkingDayDAO;

import java.util.Date;
import com.netcracker.project.model.impl.mappers.WorkingDayMapper;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@ImportResource("classpath:Spring-WorkingDay.xml")
public class WorkingDayDAOImpl implements WorkingDayDAO {

  private static final Logger logger = Logger.getLogger(WorkingDayDAOImpl.class);
  private JdbcTemplate template;

  public void setDataSource(DataSource dataSource) {
    template = new JdbcTemplate(dataSource);
  }

  @Override
  public void createWorkingDay(WorkingDay workingDay) {
    logger.info("Entering createWorkingDay(workingDay=" + workingDay + ")");

    template.update(CREATE_WORKING_DAY, new Object[]{
        "WORKDAY" + workingDay.getUserId(),
        workingDay.getDate(),
        workingDay.getWeekNumber(),
        workingDay.getWorkingHours(),
        workingDay.getStatus().getId(),
        workingDay.getUserId(),
        workingDay.getPmId()
    });
  }

  @Override
  public void updateWorkingDayStatus(BigInteger workingDayId,
      Integer statusId) {
    logger.info("Entering updateWorkingDayStatus(workingDayId=" + workingDayId + ", " + "statusId=" + statusId + ")");
    template.update(UPDATE_WORKING_DAY_STATUS, statusId, workingDayId);
  }


  @Override
  public void updateWorkingHours(BigInteger workingDayId, Double hours) {
    logger.info("Entering updateWorkingHours(workingDayId=" + workingDayId + ", " + "hours=" + hours + ")");
    template.update(UPDATE_WORKING_HOURS, workingDayId, hours, workingDayId);
  }

  @Override
  public void updateDisapprovedWorkingHours(BigInteger workingDayId, Double hours) {
    logger.info("Entering updateDisapprovedWorkingHours(workingDayId=" + workingDayId + ", " + "hours=" + hours + ")");
    template.update(UPDATE_DISAPPROVED_WORKING_HOURS, hours, workingDayId);
  }

  @Override
  public WorkingDay findWorkingDayById(BigInteger id) {
    logger.info("Entering findWorkingDayById(id=" + id + ")");
    return template.queryForObject(FIND_WORKING_DAY_BY_ID, new Object[]{id}, new WorkingDayMapper());
  }

  @Override
  public WorkingDay findWorkingDayByUserIdAndDate(BigInteger userId,
      Date workingDayDate) {
    logger.info("Entering findWorkingDayByUserIdAndDate(userId=" + userId + ", " + "workingDayDate=" + workingDayDate + ")");
    return template.queryForObject(FIND_WORKING_DAY_BY_USER_ID_AND_DATE, new Object[]{userId, workingDayDate}, new WorkingDayMapper());
  }

  @Override
  public Collection<WorkingDay> findWorkingDayPerPeriod(BigInteger userId,
      Date startDate,
      Date endDate) {
    logger.info("Entering findWorkingDayPerPeriod(userId=" + userId + ", " + "startDate=" + startDate + ", " + "endDate=" + endDate + ")");
    return template.query(FIND_HOURS_PER_PERIOD, new Object[]{userId,
        startDate, endDate}, new WorkingDayMapper());
  }

  @Override
  public Collection<WorkingDay> findWorkingDayByUserIdAndStatus(
      BigInteger userId, Integer status) {
    logger.info("Entering findWorkingDayByUserIdAndStatus(userId=" + userId + ", " + "status=" + status + ")");
    return template.query(FIND_WORKING_DAY_BY_USER_ID_AND_STATUS, new Object[]{userId, status}, new WorkingDayMapper());
  }

  @Override
  public Collection<WorkingDay> findWorkingDayByPMIdAndStatus(BigInteger pmId,
      Integer status) {
    logger.info("Entering findWorkingDayByPMIdAndStatus(pmId=" + pmId + ", " + "status=" + status + ")");
    return template.query(FIND_WORKING_DAYS_BY_PM_ID_AND_STATUS, new Object[]{pmId, status}, new WorkingDayMapper());
  }

  @Override
  public Integer findIfWorkingDayExists(BigInteger userId, Date workingDayDate) {
    logger.info("Entering findIfWorkingDayExists(userId=" + userId + ", " + "workingDayDate=" + workingDayDate + ")");
    return template.queryForObject(
        FIND_WORKING_DAY_IF_EXISTS, new Object[]{userId, workingDayDate}, Integer.class);
  }

  @Override
  public Integer findIfWorkingDayExists(BigInteger id) {
    logger.info("Entering findIfWorkingDayExists(id=" + id + ")");
    return template.queryForObject(FIND_WORKING_DAY_IF_EXISTS_BY_WORKING_DAY_ID, new Object[]{id}, Integer.class);
  }
}
