package com.netcracker.project.model.impl;

import com.netcracker.project.model.UserDAO;
import com.netcracker.project.model.entity.User;
import com.netcracker.project.model.entity.WorkPeriod;
import com.netcracker.project.model.impl.mappers.FullUserMapper;
import com.netcracker.project.model.impl.mappers.UserMapper;
import com.netcracker.project.model.impl.mappers.WorkPeriodMapper;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@ImportResource("classpath:Spring-User.xml")
public class UserDAOImpl implements UserDAO {

  private static final Logger logger = Logger.getLogger(UserDAOImpl.class);
  private JdbcTemplate template;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    template = new JdbcTemplate(dataSource);
  }

  @Override
  public User createUser(User user) {
    logger.info("Entering insert(user=" + user + ")");
    this.template.update(CREATE_USER, new Object[]{
        user.getLastName() + " " + user.getFirstName(),
        user.getLastName(),
        user.getFirstName(),
        user.getEmail(),
        user.getDateOfBirth(),
        user.getHireDate(),
        user.getPhoneNumber(),
        user.getJobTitle().getId(),
        user.getProjectStatus().getId(),
        user.getLogin(),
        user.getPassword(),
        user.getUserRole().getId(),
        user.getUserStatus().getId()
    });

    return user;
  }

  @Override
  public User findUserByUserId(BigInteger id) {
    logger.info("Entering findUserByUserId(" + id + ")");
    return template.queryForObject(FIND_USER_BY_USER_ID, new Object[]{id},
        new UserMapper());
  }

  @Override
  public User findFullUserByUserLogin(String login) {
    logger.info("Entering findFullUserByUserLogin(login=" + login + ")");
    return template.queryForObject(FIND_FULL_USER_BY_USER_LOGIN, new Object[]{login},
        new FullUserMapper());
  }

  @Override
  public User findFullUserByUserId(BigInteger id) {
    logger.info("Entering findFullUserByUserId(id= " + id + ")");
    return template.queryForObject(FIND_FULL_USER_BY_USER_ID, new Object[]{id},
        new FullUserMapper());
  }

  @Override
  public User findUserByLogin(String login) {
    logger.info("Entering findUserByLogin(" + login + ")");
    return template.queryForObject(FIND_USER_BY_LOGIN, new Object[]{login},
        new UserMapper());
  }

  @Override
  public User findPMByProjectId(BigInteger projectId) {
    logger.info("Entering findPMByProjectId(" + projectId + ")");
    return template
        .queryForObject(FIND_PM_BY_PROJECT_ID, new Object[]{projectId},
            new UserMapper());
  }

  @Override
  public User findLMByProjectId(BigInteger projectId) {
    logger.info("Entering findLMByProjectId(" + projectId + ")");
    return template
        .queryForObject(FIND_LM_BY_PROJECT_ID, new Object[]{projectId},
            new UserMapper());
  }

  @Override
  public Integer findUserByLoginIfExists(String login) {
    logger.info("Entering findUserByLoginIfExists(login=" + login + ")");
    return template
        .queryForObject(FIND_USER_BY_LOGIN_IF_EXISTS, new Object[]{login},
            Integer.class);
  }

  @Override
  public Integer findUserByUserIdIfExists(BigInteger id) {
    logger.info("Entering findUserByUserIdIfExists(id=" + id + ")");
    return template
        .queryForObject(FIND_USER_BY_USER_ID_IF_EXISTS, new Object[]{id},
            Integer.class);  }

  @Override
  public Collection<User> findUserByLastNameAndFirstName(String lastName,
      String firstName) {
    logger.info(
        "Entering findUserByFirstNameAndLastName(lastName=" + lastName + ","
            + " firstName="
            + firstName + ")");
    return template
        .query(FIND_USER_BY_LAST_NAME_AND_FIRST_NAME,
            new Object[]{lastName, firstName},
            new UserMapper());
  }

  @Override
  public Collection<User> findUserByProjectId(BigInteger projectId) {
    logger.info("Entering findUserByProjectId(" + projectId + ")");
    return template.query(FIND_USER_BY_PROJECT_ID, new Object[]{projectId},
        new UserMapper());
  }

  @Override
  public Collection<User> findUsersByJobTitleAndProjectStatus(Integer jobTitle,
      Integer projectStatus) {
    logger.info(
        "Entering findUsersByJobTitleAndProjectStatus(jobTitle=" + jobTitle
            + "," + " projectStatus="
            + projectStatus + ")");
    return template.query(FIND_USERS_BY_JOB_TITLE_AND_PROJECT_STATUS,
        new Object[]{jobTitle, projectStatus}, new UserMapper());
  }

  @Override
  public void updatePhoneNumber(BigInteger id, String phoneNumber) {
    logger.info("Entering updatePhoneNumber(id=" + id + "," + " phoneNumber="
        + phoneNumber + ")");
    template.update(UPDATE_PHONE_NUMBER, phoneNumber, id);
  }

  @Override
  public void updateEmail(BigInteger id, String email) {
    logger
        .info("Entering updateEmail(id=" + id + "," + " email=" + email + ")");
    template.update(UPDATE_EMAIL, email, id);
  }

  @Override
  public void updatePassword(BigInteger id, String password) {
    logger.info(
        "Entering updatePassword(id=" + id + "," + " password=" + password
            + ")");
    template.update(UPDATE_PASSWORD, password, id);
  }

  @Override
  public void updateJobTitleAndUserRole(BigInteger userId,Integer userRole, Integer jobTitle) {
    logger.info(
        "Entering updateJobTitleAndUserRole(userId=" + userId + "," + " userRole=" + userRole
            + " jobTitle=" + jobTitle + ")");
    template.update(UPDATE_JOB_TITLE, jobTitle, userId);
    template.update(UPDATE_USER_ROLE, userRole, userId);
  }

  @Override
  public void updatePhoto(BigInteger id, String photo) {
    //don't forget about File!!!
  }

  @Override
  public void updateProjectStatus(BigInteger id, Integer status) {
    logger.info(
        "Entering updateProjectStatus(id=" + id + "," + " status=" + status
            + ")");
    template.update(UPDATE_USER_PROJECT_STATUS, status, id);
  }

 /* public void updateUserRole(BigInteger id, Integer role) {
    logger.info("Entering updateUserRole(id=" + id + "," + " role=" + role + ")");
    template.update(UPDATE_USER_ROLE, role, id);
  }*/

  @Override
  public Integer findIfPMExists(BigInteger id) {
    logger.info("Entering findIfPMExists(id=" + id + ")");
    return template
        .queryForObject(FIND_PM_IF_EXISTS, new Object[]{id}, Integer.class);
  }

  @Override
  public Integer findIfSEExists(BigInteger id) {
    logger.info("Entering findIfSEExists(id=" + id + ")");
    return template
        .queryForObject(FIND_SE_IF_EXISTS, new Object[]{id}, Integer.class);
  }

  @Override
  public Integer findHiredUserIfExistsByLastFirstNameAndJobTitle(
      String lastName, String firstName, Integer jobTitle) {
    logger.info(
        "Entering findHiredUserIfExistsByLastFirstNameAndJobTitle(lastName="
            + lastName + "," + " firstName=" + firstName + "  jobTitle="
            + jobTitle + ")");
    return template.queryForObject(
        FIND_HIRED_USER_IF_EXISTS_BY_LAST_FIRST_NAME_AND_JOB_TITLE,
        new Object[]{lastName, firstName, jobTitle}, Integer.class);
  }

  @Override
  public Collection<WorkPeriod> findWorkPeriodsByUserId(BigInteger id) {
    logger.info("Entering findWorkPeriodsByUserId(id=" + id + ")");
    return template.query(FIND_WORK_PERIOD_BY_USER_ID, new Object[]{id},
        new WorkPeriodMapper());
  }

  @Override
  public Collection<WorkPeriod> findWorkPeriodsByProjectId(BigInteger id) {
    logger.info("Entering findWorkPeriodsByProjectId(id=" + id + ")");
    return template.query(FIND_WORK_PERIOD_BY_PROJECT_ID, new Object[]{id},
        new WorkPeriodMapper());
  }

  @Override
  public Collection<WorkPeriod> findWorkPeriodByUserIdAndProjectId(
      BigInteger userId,
      BigInteger projectId) {
    logger.info(
        "Entering findWorkPeriodByUserIdAndProjectId(userId=" + userId + ","
            + " projectId="
            + projectId + ")");
    return template
        .query(FIND_WORK_PERIOD_BY_USER_ID_AND_PROJECT_ID,
            new Object[]{userId, projectId},
            new WorkPeriodMapper());
  }

  @Override
  public Collection<WorkPeriod> findWorkPeriodByProjectIdAndStatus(
      BigInteger projectId, Integer status) {
    logger.info(
        "Entering findWorkPeriodByProjectIdAndStatus(projectId=" + projectId
            + "," + " status="
            + status + ")");
    return template
        .query(FIND_WORKING_PERIOD_BY_PROJECT_ID_AND_STATUS,
            new Object[]{status, projectId},
            new WorkPeriodMapper());
  }

  @Override
  public void updateWorkingPeriodEndDateByUserId(BigInteger userId,
      BigInteger projectId, Date date) {
    logger.info(
        "Entering updateWorkingPeriodByUserId(userId=" + userId
            + ","
            + " projectId=" + projectId + ","
            + " date=" + date
            + ")");
    template
        .update(UPDATE_WORKING_PERIOD_END_DATE, date,
            userId,
            projectId);
  }

  @Override
  public void updateWorkingPeriodStatusByUserId(WorkPeriod workPeriod) {
    logger.info(
        "Entering updateWorkingPeriodStatusByUserId(userId=" + workPeriod
            .getUserId() + ","
            + " projectId=" + workPeriod.getProjectId() + ","
            + " UserDAO.WorkPeriod=" + workPeriod + ")");
    template.update(UPDATE_WORKING_PERIOD_STATUS,
        workPeriod.getWorkPeriodStatus().getId(),
        workPeriod.getUserId(), workPeriod.getProjectId());
  }

  @Override
  public void updateWorkingPeriodStatusByUserId(BigInteger userId,
      BigInteger projectId, Integer status) {
    logger.info(
        "Entering updateWorkingPeriodStatusByUserId(userId=" + userId + ","
            + " projectId=" + projectId + "," + " status=" + status + ")");
    template.update(UPDATE_WORKING_PERIOD_STATUS, status, userId, projectId);
  }

  @Override
  public void createWorkPeriod(WorkPeriod workPeriod, BigInteger projectId) {
    logger.info("Entering createWorkPeriod(workPeriod=" + workPeriod + ")");
    this.template.update(CREATE_WORK_PERIOD, new Object[]{
        "WorkPeriod" + workPeriod.getUserId(),
        workPeriod.getStartWorkDate(),
        workPeriod.getEndWorkDate(),
        workPeriod.getWorkPeriodStatus().getId(),
        workPeriod.getUserId(),
        projectId
    });
  }

  @Override
  public WorkPeriod findWorkingWorkPeriodByUserIdAndProjectId(BigInteger userId,
      BigInteger projectId) {
    logger.info(
        "Entering findWorkingWorkPeriodByUserIdAndProjectId(userId=" + userId
            + "," + " projectId="
            + projectId + ")");
    return template
        .queryForObject(FIND_WORKING_WORK_PERIOD_BY_USER_ID_AND_PROJECT_ID,
            new Object[]{userId, projectId}, new WorkPeriodMapper());
  }

  @Override
  public Integer findWorkingWorkPeriodIfExist(BigInteger userId,
      BigInteger projectId) {
    return template.queryForObject(FIND_WORKING_WORK_PERIOD_IF_EXIST,
        new Object[]{userId, projectId}, Integer.class);
  }

  @Override
  public Integer findIfLMExistsOnProject(BigInteger projectId) {
    logger
        .info("Entering findIfLMExistsOnProject(projectId=" + projectId + ")");
    return template
        .queryForObject(FIND_IF_LM_EXISTS_ON_PROJECT, new Object[]{projectId},
            Integer.class);
  }

  @Override
  public Integer findIfLMExists(BigInteger userId) {
    logger.info("Entering findIfLMExists(userId=" + userId + ")");
    return template
        .queryForObject(FIND_IF_LM_EXIST, new Object[]{userId}, Integer.class);
  }

  @Override
  public Integer findIfLoginExists(String login) {
    logger.info("Entering checkLoginExistence(login=" + login + ")");
    return template.queryForObject(CHECK_LOGIN_EXISTENCE, new Object[]{login},
        Integer.class);
  }
}
