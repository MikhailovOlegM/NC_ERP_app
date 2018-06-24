package com.netcracker.project.model;

import com.netcracker.project.model.entity.Task;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

public interface TaskDAO {

  void createTask(Task task);

  void updateTask(Task task);

  Task findTaskByTaskId(BigInteger taskId);

  Collection<Task> findTaskByProjectIdAndTaskId(BigInteger projectId, BigInteger taskId);

  Collection<Task> findTaskByProjectId(BigInteger projectId);

  Collection<Task> findTaskByProjectIdAndPriority(BigInteger projectId, Integer taskPriority);

  Collection<Task> findTaskByProjectIdAndStatus(BigInteger projectId, Integer status);

  Collection<Task> findTaskByUserId(BigInteger userId);

  Collection<Task> findTaskByUserIdAndPriority(BigInteger userId, Integer taskPriority);

  Collection<Task> findTaskByUserIdAndStatus(BigInteger userId, Integer taskStatus);

  BigInteger findTaskIdByPMLogin(String pmLogin);

  BigInteger findTaskIdByUserLogin(String pmLogin);

  Collection<Task> findTaskByUserAndStatusPerPeriod(BigInteger userId, Integer taskStatus, Date startDate, Date endDate);

  void updateStatus(Integer taskStatus, BigInteger taskId);

  void updateEndDate(Date date, BigInteger taskId);

  void updateReopenCounter(Integer counter, BigInteger taskId);

  void updateComment(String comment, BigInteger taskId);

  Integer findIfTaskExists(BigInteger id);

  String UPDATE_END_DATE =
          " UPDATE ATTRIBUTES " +
          " SET DATE_VALUE = ? " +
          " WHERE ATTRIBUTES.OBJECT_ID = ? " +
          " AND ATTRIBUTES.ATTR_ID = 23 ";

  String UPDATE_REOPEN_COUNTER =
      " UPDATE ATTRIBUTES " +
      " SET ATTRIBUTES.VALUE = ? " +
      " WHERE ATTRIBUTES.OBJECT_ID = ? " +
      " AND ATTRIBUTES.ATTR_ID = 28 ";;

  String CREATE_TASK =
          "INSERT ALL " +
          "INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION, OBJECT_VERSION) VALUES (OBJECT_SEQUENCE.NEXTVAL,NULL,3,'Task' || OBJECT_SEQUENCE.CURRVAL,NULL, 1) " +
          "INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,LIST_VALUE_ID) VALUES (20,OBJECT_SEQUENCE.CURRVAL,?,NULL,NULL) " +
          "INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,LIST_VALUE_ID) VALUES (21,OBJECT_SEQUENCE.CURRVAL,NULL,NULL,?) " +
          "INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,LIST_VALUE_ID) VALUES (22,OBJECT_SEQUENCE.CURRVAL,NULL,?,NULL) " +
          "INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,LIST_VALUE_ID) VALUES (23,OBJECT_SEQUENCE.CURRVAL,NULL,?,NULL) " +
          "INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,LIST_VALUE_ID) VALUES (24,OBJECT_SEQUENCE.CURRVAL,NULL,?,NULL) " +
          "INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,LIST_VALUE_ID) VALUES (25,OBJECT_SEQUENCE.CURRVAL,NULL ,NULL,?)" +
          "INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,LIST_VALUE_ID) VALUES (26,OBJECT_SEQUENCE.CURRVAL,NULL,NULL,?) " +
          "INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,LIST_VALUE_ID) VALUES (27,OBJECT_SEQUENCE.CURRVAL,?,NULL,NULL) " +
          "INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,LIST_VALUE_ID) VALUES (28,OBJECT_SEQUENCE.CURRVAL,?,NULL,NULL) " +
          "INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,LIST_VALUE_ID) VALUES (29,OBJECT_SEQUENCE.CURRVAL,?,NULL,NULL) " +
          "INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (30, OBJECT_SEQUENCE.CURRVAL, ?)" +
          "INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (31, OBJECT_SEQUENCE.CURRVAL, ?)" +
          "INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (32, OBJECT_SEQUENCE.CURRVAL, ?)" +
          "SELECT * " +
          "FROM Dual";

  String FIND_TASK_BY_PROJECT_ID_AND_TASK_ID =
          " SELECT TASK.OBJECT_ID AS TASK_ID, TASK_NAME.VALUE AS NAME, TASK_TYPE_VALUE.VALUE AS TASK_TYPE, " +
          " TASK_START_DATE.DATE_VALUE AS START_DATE, TASK_END_DATE.DATE_VALUE AS END_DATE, TASK_PLANNED_END_DATE.DATE_VALUE AS PLANNED_END_DATE, " +
          " TASK_PRIORITY_VALUE.VALUE AS TASK_PRIORITY, TASK_STATUS_VALUE.VALUE AS STATUS, TASK_DESCRIPTION.VALUE AS TASK_DESCRIPTION, " +
          " TASK_REOPEN_COUNTER.VALUE AS REOPEN_COUNTER, TASK_COMMENT.VALUE AS TASK_COMMENT, AUTHOR_ID_REF.REFERENCE AS AUTHOR_ID,  " +
          " USER_ID_REF.REFERENCE AS USER_ID, PROJECT_ID_REF.REFERENCE AS PROJECT_ID " +
          " FROM OBJECTS TASK, OBJECTS PROJ, OBJTYPE TASK_TYPE, " +
          " ATTRIBUTES TASK_NAME, ATTRIBUTES TASK_TYPE, ATTRIBUTES TASK_START_DATE, ATTRIBUTES TASK_END_DATE, " +
          " ATTRIBUTES TASK_PLANNED_END_DATE, ATTRIBUTES TASK_PRIORITY, ATTRIBUTES TASK_STATUS, ATTRIBUTES TASK_DESCRIPTION, " +
          " ATTRIBUTES TASK_REOPEN_COUNTER, ATTRIBUTES TASK_COMMENT, " +
          " LISTVALUE TASK_TYPE_VALUE, LISTVALUE TASK_PRIORITY_VALUE, LISTVALUE TASK_STATUS_VALUE, " +
          " OBJREFERENCE PROJECT_ID_REF, OBJREFERENCE AUTHOR_ID_REF, OBJREFERENCE USER_ID_REF " +
          " WHERE TASK_TYPE.OBJECT_TYPE_ID = 3 AND TASK.OBJECT_TYPE_ID = TASK_TYPE.OBJECT_TYPE_ID " +
          " AND PROJ.OBJECT_ID = ? " +
          " AND TASK.OBJECT_ID = ? " +
          " AND PROJ.OBJECT_TYPE_ID = 2 " +
          " AND TASK_NAME.ATTR_ID = 20 AND TASK_NAME.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE.ATTR_ID = 21 AND TASK_TYPE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE_VALUE.ATTR_ID = 21 AND TASK_TYPE_VALUE.LIST_VALUE_ID = TASK_TYPE.LIST_VALUE_ID " +
          " AND TASK_START_DATE.ATTR_ID = 22 AND TASK_START_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_END_DATE.ATTR_ID = 23 AND TASK_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PLANNED_END_DATE.ATTR_ID = 24 AND TASK_PLANNED_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY.ATTR_ID = 25 AND TASK_PRIORITY.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY_VALUE.ATTR_ID = 25 AND TASK_PRIORITY_VALUE.LIST_VALUE_ID = TASK_PRIORITY.LIST_VALUE_ID " +
          " AND TASK_STATUS.ATTR_ID = 26 AND TASK_STATUS.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_STATUS_VALUE.ATTR_ID = 26 AND TASK_STATUS_VALUE.LIST_VALUE_ID = TASK_STATUS.LIST_VALUE_ID " +
          " AND TASK_DESCRIPTION.ATTR_ID = 27 AND TASK_DESCRIPTION.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_REOPEN_COUNTER.ATTR_ID = 28 AND TASK_REOPEN_COUNTER.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_COMMENT.ATTR_ID = 29 AND TASK_COMMENT.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.ATTR_ID = 30 " +
          " AND USER_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND USER_ID_REF.ATTR_ID = 31 " +
          " AND PROJ.OBJECT_ID = PROJECT_ID_REF.REFERENCE" +
          " AND PROJ.OBJECT_ID = PROJECT_ID_REF.REFERENCE " +
          " AND PROJECT_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND PROJECT_ID_REF.ATTR_ID = 32 " ;

  String FIND_TASK_BY_PROJECT_ID_AND_STATUS =
          " SELECT TASK.OBJECT_ID AS TASK_ID, TASK_NAME.VALUE AS NAME, TASK_TYPE_VALUE.VALUE AS TASK_TYPE, " +
          " TASK_START_DATE.DATE_VALUE AS START_DATE, TASK_END_DATE.DATE_VALUE AS END_DATE, TASK_PLANNED_END_DATE.DATE_VALUE AS PLANNED_END_DATE, " +
          " TASK_PRIORITY_VALUE.VALUE AS TASK_PRIORITY, TASK_STATUS_VALUE.VALUE AS STATUS, TASK_DESCRIPTION.VALUE AS TASK_DESCRIPTION, " +
          " TASK_REOPEN_COUNTER.VALUE AS REOPEN_COUNTER, TASK_COMMENT.VALUE AS TASK_COMMENT, AUTHOR_ID_REF.REFERENCE AS AUTHOR_ID,  " +
          " USER_ID_REF.REFERENCE AS USER_ID, PROJECT_ID_REF.REFERENCE AS PROJECT_ID " +
          " FROM OBJECTS TASK, OBJECTS PROJ, OBJTYPE TASK_TYPE, " +
          " ATTRIBUTES TASK_NAME, ATTRIBUTES TASK_TYPE, ATTRIBUTES TASK_START_DATE, ATTRIBUTES TASK_END_DATE, " +
          " ATTRIBUTES TASK_PLANNED_END_DATE, ATTRIBUTES TASK_PRIORITY, ATTRIBUTES TASK_STATUS, ATTRIBUTES TASK_DESCRIPTION, " +
          " ATTRIBUTES TASK_REOPEN_COUNTER, ATTRIBUTES TASK_COMMENT, " +
          " LISTVALUE TASK_TYPE_VALUE, LISTVALUE TASK_PRIORITY_VALUE, LISTVALUE TASK_STATUS_VALUE, " +
          " OBJREFERENCE PROJECT_ID_REF, OBJREFERENCE AUTHOR_ID_REF, OBJREFERENCE USER_ID_REF " +
          " WHERE TASK_TYPE.OBJECT_TYPE_ID = 3 AND TASK.OBJECT_TYPE_ID = TASK_TYPE.OBJECT_TYPE_ID " +
          " AND PROJ.OBJECT_ID = ? " +
          " AND TASK_STATUS_VALUE.LIST_VALUE_ID = ? " +
          " AND PROJ.OBJECT_TYPE_ID = 2 " +
          " AND TASK_NAME.ATTR_ID = 20 AND TASK_NAME.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE.ATTR_ID = 21 AND TASK_TYPE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE_VALUE.ATTR_ID = 21 AND TASK_TYPE_VALUE.LIST_VALUE_ID = TASK_TYPE.LIST_VALUE_ID " +
          " AND TASK_START_DATE.ATTR_ID = 22 AND TASK_START_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_END_DATE.ATTR_ID = 23 AND TASK_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PLANNED_END_DATE.ATTR_ID = 24 AND TASK_PLANNED_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY.ATTR_ID = 25 AND TASK_PRIORITY.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY_VALUE.ATTR_ID = 25 AND TASK_PRIORITY_VALUE.LIST_VALUE_ID = TASK_PRIORITY.LIST_VALUE_ID " +
          " AND TASK_STATUS.ATTR_ID = 26 AND TASK_STATUS.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_STATUS_VALUE.ATTR_ID = 26 AND TASK_STATUS_VALUE.LIST_VALUE_ID = TASK_STATUS.LIST_VALUE_ID " +
          " AND TASK_DESCRIPTION.ATTR_ID = 27 AND TASK_DESCRIPTION.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_REOPEN_COUNTER.ATTR_ID = 28 AND TASK_REOPEN_COUNTER.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_COMMENT.ATTR_ID = 29 AND TASK_COMMENT.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.ATTR_ID = 30 " +
          " AND USER_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND USER_ID_REF.ATTR_ID = 31 " +
          " AND PROJECT_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND  PROJECT_ID_REF.ATTR_ID = 32 " ;

  String FIND_TASK_BY_PROJECT_ID =
          " SELECT TASK.OBJECT_ID AS TASK_ID, TASK_NAME.VALUE AS NAME, TASK_TYPE_VALUE.VALUE AS TASK_TYPE, " +
          " TASK_START_DATE.DATE_VALUE AS START_DATE, TASK_END_DATE.DATE_VALUE AS END_DATE, TASK_PLANNED_END_DATE.DATE_VALUE AS PLANNED_END_DATE, " +
          " TASK_PRIORITY_VALUE.VALUE AS TASK_PRIORITY, TASK_STATUS_VALUE.VALUE AS STATUS, TASK_DESCRIPTION.VALUE AS TASK_DESCRIPTION, " +
          " TASK_REOPEN_COUNTER.VALUE AS REOPEN_COUNTER, TASK_COMMENT.VALUE AS TASK_COMMENT, AUTHOR_ID_REF.REFERENCE AS AUTHOR_ID,  " +
          " USER_ID_REF.REFERENCE AS USER_ID, PROJECT_ID_REF.REFERENCE AS PROJECT_ID " +
          " FROM OBJECTS TASK, OBJECTS PROJ, OBJTYPE TASK_TYPE, " +
          " ATTRIBUTES TASK_NAME, ATTRIBUTES TASK_TYPE, ATTRIBUTES TASK_START_DATE, ATTRIBUTES TASK_END_DATE, " +
          " ATTRIBUTES TASK_PLANNED_END_DATE, ATTRIBUTES TASK_PRIORITY, ATTRIBUTES TASK_STATUS, ATTRIBUTES TASK_DESCRIPTION, " +
          " ATTRIBUTES TASK_REOPEN_COUNTER, ATTRIBUTES TASK_COMMENT, " +
          " LISTVALUE TASK_TYPE_VALUE, LISTVALUE TASK_PRIORITY_VALUE, LISTVALUE TASK_STATUS_VALUE, " +
          " OBJREFERENCE PROJECT_ID_REF, OBJREFERENCE AUTHOR_ID_REF, OBJREFERENCE USER_ID_REF " +
          " WHERE TASK_TYPE.OBJECT_TYPE_ID = 3 AND TASK.OBJECT_TYPE_ID = TASK_TYPE.OBJECT_TYPE_ID " +
          " AND PROJ.OBJECT_ID = ? " +
          " AND PROJ.OBJECT_TYPE_ID = 2 " +
          " AND TASK_NAME.ATTR_ID = 20 AND TASK_NAME.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE.ATTR_ID = 21 AND TASK_TYPE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE_VALUE.ATTR_ID = 21 AND TASK_TYPE_VALUE.LIST_VALUE_ID = TASK_TYPE.LIST_VALUE_ID " +
          " AND TASK_START_DATE.ATTR_ID = 22 AND TASK_START_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_END_DATE.ATTR_ID = 23 AND TASK_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PLANNED_END_DATE.ATTR_ID = 24 AND TASK_PLANNED_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY.ATTR_ID = 25 AND TASK_PRIORITY.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY_VALUE.ATTR_ID = 25 AND TASK_PRIORITY_VALUE.LIST_VALUE_ID = TASK_PRIORITY.LIST_VALUE_ID " +
          " AND TASK_STATUS.ATTR_ID = 26 AND TASK_STATUS.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_STATUS_VALUE.ATTR_ID = 26 AND TASK_STATUS_VALUE.LIST_VALUE_ID = TASK_STATUS.LIST_VALUE_ID " +
          " AND TASK_DESCRIPTION.ATTR_ID = 27 AND TASK_DESCRIPTION.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_REOPEN_COUNTER.ATTR_ID = 28 AND TASK_REOPEN_COUNTER.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_COMMENT.ATTR_ID = 29 AND TASK_COMMENT.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.ATTR_ID = 30 " +
          " AND USER_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND USER_ID_REF.ATTR_ID = 31 " +
          " AND PROJ.OBJECT_ID = PROJECT_ID_REF.REFERENCE " +
          " AND PROJECT_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND  PROJECT_ID_REF.ATTR_ID = 32 " ;

  String FIND_TASK_BY_TASK_ID = " SELECT TASK.OBJECT_ID AS TASK_ID, TASK_NAME.VALUE AS NAME, TASK_TYPE_VALUE.VALUE AS TASK_TYPE, " +
      " TASK_START_DATE.DATE_VALUE AS START_DATE, TASK_END_DATE.DATE_VALUE AS END_DATE, TASK_PLANNED_END_DATE.DATE_VALUE AS PLANNED_END_DATE, " +
      " TASK_PRIORITY_VALUE.VALUE AS TASK_PRIORITY, TASK_STATUS_VALUE.VALUE AS STATUS, TASK_DESCRIPTION.VALUE AS TASK_DESCRIPTION, " +
      " TASK_REOPEN_COUNTER.VALUE AS REOPEN_COUNTER, TASK_COMMENT.VALUE AS TASK_COMMENT, AUTHOR_ID_REF.REFERENCE AS AUTHOR_ID,  " +
      " USER_ID_REF.REFERENCE AS USER_ID, PROJECT_ID_REF.REFERENCE AS PROJECT_ID " +
      " FROM OBJECTS TASK, OBJECTS PROJ, OBJTYPE TASK_TYPE, " +
      " ATTRIBUTES TASK_NAME, ATTRIBUTES TASK_TYPE, ATTRIBUTES TASK_START_DATE, ATTRIBUTES TASK_END_DATE, " +
      " ATTRIBUTES TASK_PLANNED_END_DATE, ATTRIBUTES TASK_PRIORITY, ATTRIBUTES TASK_STATUS, ATTRIBUTES TASK_DESCRIPTION, " +
      " ATTRIBUTES TASK_REOPEN_COUNTER, ATTRIBUTES TASK_COMMENT, " +
      " LISTVALUE TASK_TYPE_VALUE, LISTVALUE TASK_PRIORITY_VALUE, LISTVALUE TASK_STATUS_VALUE, " +
      " OBJREFERENCE PROJECT_ID_REF, OBJREFERENCE AUTHOR_ID_REF, OBJREFERENCE USER_ID_REF " +
      " WHERE TASK_TYPE.OBJECT_TYPE_ID = 3 AND TASK.OBJECT_TYPE_ID = TASK_TYPE.OBJECT_TYPE_ID " +
      " AND TASK.OBJECT_ID = ? " +
      " AND TASK_NAME.ATTR_ID = 20 AND TASK_NAME.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_TYPE.ATTR_ID = 21 AND TASK_TYPE.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_TYPE_VALUE.ATTR_ID = 21 AND TASK_TYPE_VALUE.LIST_VALUE_ID = TASK_TYPE.LIST_VALUE_ID " +
      " AND TASK_START_DATE.ATTR_ID = 22 AND TASK_START_DATE.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_END_DATE.ATTR_ID = 23 AND TASK_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_PLANNED_END_DATE.ATTR_ID = 24 AND TASK_PLANNED_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_PRIORITY.ATTR_ID = 25 AND TASK_PRIORITY.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_PRIORITY_VALUE.ATTR_ID = 25 AND TASK_PRIORITY_VALUE.LIST_VALUE_ID = TASK_PRIORITY.LIST_VALUE_ID " +
      " AND TASK_STATUS.ATTR_ID = 26 AND TASK_STATUS.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_STATUS_VALUE.ATTR_ID = 26 AND TASK_STATUS_VALUE.LIST_VALUE_ID = TASK_STATUS.LIST_VALUE_ID " +
      " AND TASK_DESCRIPTION.ATTR_ID = 27 AND TASK_DESCRIPTION.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_REOPEN_COUNTER.ATTR_ID = 28 AND TASK_REOPEN_COUNTER.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_COMMENT.ATTR_ID = 29 AND TASK_COMMENT.OBJECT_ID = TASK.OBJECT_ID " +
      " AND AUTHOR_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
      " AND AUTHOR_ID_REF.ATTR_ID = 30 " +
      " AND USER_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
      " AND USER_ID_REF.ATTR_ID = 31 " +
      " AND PROJ.OBJECT_ID = PROJECT_ID_REF.REFERENCE" +
      " AND PROJ.OBJECT_ID = PROJECT_ID_REF.REFERENCE " +
      " AND PROJECT_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
      " AND  PROJECT_ID_REF.ATTR_ID = 32 " ;

  String FIND_TASK_BY_PROJECT_ID_AND_PRIORITY =
          " SELECT TASK.OBJECT_ID AS TASK_ID, TASK_NAME.VALUE AS NAME, TASK_TYPE_VALUE.VALUE AS TASK_TYPE, " +
          " TASK_START_DATE.DATE_VALUE AS START_DATE, TASK_END_DATE.DATE_VALUE AS END_DATE, TASK_PLANNED_END_DATE.DATE_VALUE AS PLANNED_END_DATE, " +
          " TASK_PRIORITY_VALUE.VALUE AS TASK_PRIORITY, TASK_STATUS_VALUE.VALUE AS STATUS, TASK_DESCRIPTION.VALUE AS TASK_DESCRIPTION, " +
          " TASK_REOPEN_COUNTER.VALUE AS REOPEN_COUNTER, TASK_COMMENT.VALUE AS TASK_COMMENT, AUTHOR_ID_REF.REFERENCE AS AUTHOR_ID,  " +
          " USER_ID_REF.REFERENCE AS USER_ID, PROJECT_ID_REF.REFERENCE AS PROJECT_ID " +
          " FROM OBJECTS TASK, OBJECTS PROJ, OBJTYPE TASK_TYPE, " +
          " ATTRIBUTES TASK_NAME, ATTRIBUTES TASK_TYPE, ATTRIBUTES TASK_START_DATE, ATTRIBUTES TASK_END_DATE, " +
          " ATTRIBUTES TASK_PLANNED_END_DATE, ATTRIBUTES TASK_PRIORITY, ATTRIBUTES TASK_STATUS, ATTRIBUTES TASK_DESCRIPTION, " +
          " ATTRIBUTES TASK_REOPEN_COUNTER, ATTRIBUTES TASK_COMMENT, " +
          " LISTVALUE TASK_TYPE_VALUE, LISTVALUE TASK_PRIORITY_VALUE, LISTVALUE TASK_STATUS_VALUE, " +
          " OBJREFERENCE PROJECT_ID_REF, OBJREFERENCE AUTHOR_ID_REF, OBJREFERENCE USER_ID_REF " +
          " WHERE TASK_TYPE.OBJECT_TYPE_ID = 3 AND TASK.OBJECT_TYPE_ID = TASK_TYPE.OBJECT_TYPE_ID " +
          " AND PROJ.OBJECT_ID = ? " +
          " AND TASK_PRIORITY_VALUE.LIST_VALUE_ID = ? " +
          " AND PROJ.OBJECT_TYPE_ID = 2 " +
          " AND TASK_NAME.ATTR_ID = 20 AND TASK_NAME.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE.ATTR_ID = 21 AND TASK_TYPE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE_VALUE.ATTR_ID = 21 AND TASK_TYPE_VALUE.LIST_VALUE_ID = TASK_TYPE.LIST_VALUE_ID " +
          " AND TASK_START_DATE.ATTR_ID = 22 AND TASK_START_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_END_DATE.ATTR_ID = 23 AND TASK_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PLANNED_END_DATE.ATTR_ID = 24 AND TASK_PLANNED_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY.ATTR_ID = 25 AND TASK_PRIORITY.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY_VALUE.ATTR_ID = 25 AND TASK_PRIORITY_VALUE.LIST_VALUE_ID = TASK_PRIORITY.LIST_VALUE_ID " +
          " AND TASK_STATUS.ATTR_ID = 26 AND TASK_STATUS.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_STATUS_VALUE.ATTR_ID = 26 AND TASK_STATUS_VALUE.LIST_VALUE_ID = TASK_STATUS.LIST_VALUE_ID " +
          " AND TASK_DESCRIPTION.ATTR_ID = 27 AND TASK_DESCRIPTION.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_REOPEN_COUNTER.ATTR_ID = 28 AND TASK_REOPEN_COUNTER.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_COMMENT.ATTR_ID = 29 AND TASK_COMMENT.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.ATTR_ID = 30 " +
          " AND USER_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND USER_ID_REF.ATTR_ID = 31 " +
          " AND PROJ.OBJECT_ID = PROJECT_ID_REF.REFERENCE " +
          " AND PROJECT_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND  PROJECT_ID_REF.ATTR_ID = 32 " ;

  String FIND_TASK_BY_USER_ID =
          " SELECT TASK.OBJECT_ID AS TASK_ID, TASK_NAME.VALUE AS NAME, TASK_TYPE_VALUE.VALUE AS TASK_TYPE, " +
          " TASK_START_DATE.DATE_VALUE AS START_DATE, TASK_END_DATE.DATE_VALUE AS END_DATE, TASK_PLANNED_END_DATE.DATE_VALUE AS PLANNED_END_DATE, " +
          " TASK_PRIORITY_VALUE.VALUE AS TASK_PRIORITY, TASK_STATUS_VALUE.VALUE AS STATUS, TASK_DESCRIPTION.VALUE AS TASK_DESCRIPTION, " +
          " TASK_REOPEN_COUNTER.VALUE AS REOPEN_COUNTER, TASK_COMMENT.VALUE AS TASK_COMMENT, AUTHOR_ID_REF.REFERENCE AS AUTHOR_ID,  " +
          " USER_ID_REF.REFERENCE AS USER_ID, PROJECT_ID_REF.REFERENCE AS PROJECT_ID " +
          " FROM OBJECTS TASK, OBJECTS EMP, OBJTYPE TASK_TYPE, " +
          " ATTRIBUTES TASK_NAME, ATTRIBUTES TASK_TYPE, ATTRIBUTES TASK_START_DATE, ATTRIBUTES TASK_END_DATE, " +
          " ATTRIBUTES TASK_PLANNED_END_DATE, ATTRIBUTES TASK_PRIORITY, ATTRIBUTES TASK_STATUS, ATTRIBUTES TASK_DESCRIPTION, " +
          " ATTRIBUTES TASK_REOPEN_COUNTER, ATTRIBUTES TASK_COMMENT, " +
          " LISTVALUE TASK_TYPE_VALUE, LISTVALUE TASK_PRIORITY_VALUE, LISTVALUE TASK_STATUS_VALUE, " +
          " OBJREFERENCE PROJECT_ID_REF, OBJREFERENCE AUTHOR_ID_REF, OBJREFERENCE USER_ID_REF " +
          " WHERE TASK_TYPE.OBJECT_TYPE_ID = 3 AND TASK.OBJECT_TYPE_ID = TASK_TYPE.OBJECT_TYPE_ID " +
          " AND EMP.OBJECT_ID = ? " +
          " AND EMP.OBJECT_TYPE_ID = 1 " +
          " AND TASK_NAME.ATTR_ID = 20 AND TASK_NAME.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE.ATTR_ID = 21 AND TASK_TYPE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE_VALUE.ATTR_ID = 21 AND TASK_TYPE_VALUE.LIST_VALUE_ID = TASK_TYPE.LIST_VALUE_ID " +
          " AND TASK_START_DATE.ATTR_ID = 22 AND TASK_START_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_END_DATE.ATTR_ID = 23 AND TASK_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PLANNED_END_DATE.ATTR_ID = 24 AND TASK_PLANNED_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY.ATTR_ID = 25 AND TASK_PRIORITY.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY_VALUE.ATTR_ID = 25 AND TASK_PRIORITY_VALUE.LIST_VALUE_ID = TASK_PRIORITY.LIST_VALUE_ID " +
          " AND TASK_STATUS.ATTR_ID = 26 AND TASK_STATUS.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_STATUS_VALUE.ATTR_ID = 26 AND TASK_STATUS_VALUE.LIST_VALUE_ID = TASK_STATUS.LIST_VALUE_ID " +
          " AND TASK_DESCRIPTION.ATTR_ID = 27 AND TASK_DESCRIPTION.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_REOPEN_COUNTER.ATTR_ID = 28 AND TASK_REOPEN_COUNTER.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_COMMENT.ATTR_ID = 29 AND TASK_COMMENT.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.ATTR_ID = 30 " +
          " AND EMP.OBJECT_ID = USER_ID_REF.REFERENCE "  +
          " AND USER_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND USER_ID_REF.ATTR_ID = 31 " +
          " AND PROJECT_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND  PROJECT_ID_REF.ATTR_ID = 32 " ;

  String FIND_TASK_BY_USER_ID_AND_PRIORITY =
          " SELECT TASK.OBJECT_ID AS TASK_ID, TASK_NAME.VALUE AS NAME, TASK_TYPE_VALUE.VALUE AS TASK_TYPE, " +
          " TASK_START_DATE.DATE_VALUE AS START_DATE, TASK_END_DATE.DATE_VALUE AS END_DATE, TASK_PLANNED_END_DATE.DATE_VALUE AS PLANNED_END_DATE, " +
          " TASK_PRIORITY_VALUE.VALUE AS TASK_PRIORITY, TASK_STATUS_VALUE.VALUE AS STATUS, TASK_DESCRIPTION.VALUE AS TASK_DESCRIPTION, " +
          " TASK_REOPEN_COUNTER.VALUE AS REOPEN_COUNTER, TASK_COMMENT.VALUE AS TASK_COMMENT, AUTHOR_ID_REF.REFERENCE AS AUTHOR_ID,  " +
          " USER_ID_REF.REFERENCE AS USER_ID, PROJECT_ID_REF.REFERENCE AS PROJECT_ID " +
          " FROM OBJECTS TASK, OBJECTS EMP, OBJTYPE TASK_TYPE, " +
          " ATTRIBUTES TASK_NAME, ATTRIBUTES TASK_TYPE, ATTRIBUTES TASK_START_DATE, ATTRIBUTES TASK_END_DATE, " +
          " ATTRIBUTES TASK_PLANNED_END_DATE, ATTRIBUTES TASK_PRIORITY, ATTRIBUTES TASK_STATUS, ATTRIBUTES TASK_DESCRIPTION, " +
          " ATTRIBUTES TASK_REOPEN_COUNTER, ATTRIBUTES TASK_COMMENT, " +
          " LISTVALUE TASK_TYPE_VALUE, LISTVALUE TASK_PRIORITY_VALUE, LISTVALUE TASK_STATUS_VALUE, " +
          " OBJREFERENCE PROJECT_ID_REF, OBJREFERENCE AUTHOR_ID_REF, OBJREFERENCE USER_ID_REF " +
          " WHERE TASK_TYPE.OBJECT_TYPE_ID = 3 AND TASK.OBJECT_TYPE_ID = TASK_TYPE.OBJECT_TYPE_ID " +
          " AND EMP.OBJECT_ID = ? " +
          " AND EMP.OBJECT_TYPE_ID = 1 " +
          " AND TASK_PRIORITY_VALUE.LIST_VALUE_ID = ? " +
          " AND TASK_NAME.ATTR_ID = 20 AND TASK_NAME.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE.ATTR_ID = 21 AND TASK_TYPE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE_VALUE.ATTR_ID = 21 AND TASK_TYPE_VALUE.LIST_VALUE_ID = TASK_TYPE.LIST_VALUE_ID " +
          " AND TASK_START_DATE.ATTR_ID = 22 AND TASK_START_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_END_DATE.ATTR_ID = 23 AND TASK_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PLANNED_END_DATE.ATTR_ID = 24 AND TASK_PLANNED_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY.ATTR_ID = 25 AND TASK_PRIORITY.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY_VALUE.ATTR_ID = 25 AND TASK_PRIORITY_VALUE.LIST_VALUE_ID = TASK_PRIORITY.LIST_VALUE_ID " +
          " AND TASK_STATUS.ATTR_ID = 26 AND TASK_STATUS.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_STATUS_VALUE.ATTR_ID = 26 AND TASK_STATUS_VALUE.LIST_VALUE_ID = TASK_STATUS.LIST_VALUE_ID " +
          " AND TASK_DESCRIPTION.ATTR_ID = 27 AND TASK_DESCRIPTION.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_REOPEN_COUNTER.ATTR_ID = 28 AND TASK_REOPEN_COUNTER.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_COMMENT.ATTR_ID = 29 AND TASK_COMMENT.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.ATTR_ID = 30 " +
          " AND USER_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND USER_ID_REF.ATTR_ID = 31 " +
          " AND PROJECT_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND  PROJECT_ID_REF.ATTR_ID = 32 " ;

  String FIND_TASK_BY_USER_ID_AND_STATUS =
          " SELECT TASK.OBJECT_ID AS TASK_ID, TASK_NAME.VALUE AS NAME, TASK_TYPE_VALUE.VALUE AS TASK_TYPE, " +
          " TASK_START_DATE.DATE_VALUE AS START_DATE, TASK_END_DATE.DATE_VALUE AS END_DATE, TASK_PLANNED_END_DATE.DATE_VALUE AS PLANNED_END_DATE, " +
          " TASK_PRIORITY_VALUE.VALUE AS TASK_PRIORITY, TASK_STATUS_VALUE.VALUE AS STATUS, TASK_DESCRIPTION.VALUE AS TASK_DESCRIPTION, " +
          " TASK_REOPEN_COUNTER.VALUE AS REOPEN_COUNTER, TASK_COMMENT.VALUE AS TASK_COMMENT, AUTHOR_ID_REF.REFERENCE AS AUTHOR_ID,  " +
          " USER_ID_REF.REFERENCE AS USER_ID, PROJECT_ID_REF.REFERENCE AS PROJECT_ID " +
          " FROM OBJECTS TASK, OBJECTS EMP, OBJTYPE TASK_TYPE, " +
          " ATTRIBUTES TASK_NAME, ATTRIBUTES TASK_TYPE, ATTRIBUTES TASK_START_DATE, ATTRIBUTES TASK_END_DATE, " +
          " ATTRIBUTES TASK_PLANNED_END_DATE, ATTRIBUTES TASK_PRIORITY, ATTRIBUTES TASK_STATUS, ATTRIBUTES TASK_DESCRIPTION, " +
          " ATTRIBUTES TASK_REOPEN_COUNTER, ATTRIBUTES TASK_COMMENT, " +
          " LISTVALUE TASK_TYPE_VALUE, LISTVALUE TASK_PRIORITY_VALUE, LISTVALUE TASK_STATUS_VALUE, " +
          " OBJREFERENCE PROJECT_ID_REF, OBJREFERENCE AUTHOR_ID_REF, OBJREFERENCE USER_ID_REF " +
          " WHERE TASK_TYPE.OBJECT_TYPE_ID = 3 AND TASK.OBJECT_TYPE_ID = TASK_TYPE.OBJECT_TYPE_ID " +
          " AND EMP.OBJECT_ID = ? " +
          " AND EMP.OBJECT_TYPE_ID = 1 " +
          " AND TASK_STATUS_VALUE.LIST_VALUE_ID = ? "  +
          " AND TASK_NAME.ATTR_ID = 20 AND TASK_NAME.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE.ATTR_ID = 21 AND TASK_TYPE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE_VALUE.ATTR_ID = 21 AND TASK_TYPE_VALUE.LIST_VALUE_ID = TASK_TYPE.LIST_VALUE_ID " +
          " AND TASK_START_DATE.ATTR_ID = 22 AND TASK_START_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_END_DATE.ATTR_ID = 23 AND TASK_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PLANNED_END_DATE.ATTR_ID = 24 AND TASK_PLANNED_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY.ATTR_ID = 25 AND TASK_PRIORITY.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY_VALUE.ATTR_ID = 25 AND TASK_PRIORITY_VALUE.LIST_VALUE_ID = TASK_PRIORITY.LIST_VALUE_ID " +
          " AND TASK_STATUS.ATTR_ID = 26 AND TASK_STATUS.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_STATUS_VALUE.ATTR_ID = 26 AND TASK_STATUS_VALUE.LIST_VALUE_ID = TASK_STATUS.LIST_VALUE_ID " +
          " AND TASK_DESCRIPTION.ATTR_ID = 27 AND TASK_DESCRIPTION.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_REOPEN_COUNTER.ATTR_ID = 28 AND TASK_REOPEN_COUNTER.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_COMMENT.ATTR_ID = 29 AND TASK_COMMENT.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.ATTR_ID = 30 " +
          " AND EMP.OBJECT_ID = USER_ID_REF.REFERENCE "  +
          " AND USER_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND USER_ID_REF.ATTR_ID = 31 " +
          " AND PROJECT_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND  PROJECT_ID_REF.ATTR_ID = 32 " ;

  String UPDATE_TASK_BY_NAME =
          " UPDATE ATTRIBUTES " +
          " SET ATTRIBUTES.VALUE = ?" +
          " WHERE ATTRIBUTES.OBJECT_ID = ? " +
          " AND ATTRIBUTES.ATTR_ID = 20";

  String UPDATE_TASK_BY_TYPE =
          " UPDATE ATTRIBUTES " +
          " SET ATTRIBUTES.LIST_VALUE_ID = ?" +
          " WHERE ATTRIBUTES.OBJECT_ID = ?" +
          " AND ATTRIBUTES.ATTR_ID = 21";

  String UPDATE_TASK_BY_START_DATE =
          " UPDATE ATTRIBUTES " +
          " SET DATE_VALUE = ?" +
          " WHERE ATTRIBUTES.OBJECT_ID = ?" +
          " AND ATTRIBUTES.ATTR_ID = 22";

  String UPDATE_TASK_BY_END_DATE =
          " UPDATE ATTRIBUTES " +
          " SET DATE_VALUE = ?" +
          " WHERE ATTRIBUTES.OBJECT_ID = ? " +
          " AND ATTRIBUTES.ATTR_ID = 23";

  String UPDATE_TASK_BY_PLANNED_END_DATE =
          " UPDATE ATTRIBUTES " +
          " SET DATE_VALUE = ?" +
          " WHERE ATTRIBUTES.OBJECT_ID = ? " +
          " AND ATTRIBUTES.ATTR_ID = 24";

  String UPDATE_TASK_BY_TASK_PRIORITY =
          " UPDATE ATTRIBUTES " +
          " SET ATTRIBUTES.LIST_VALUE_ID = ? " +
          " WHERE ATTRIBUTES.OBJECT_ID = ? " +
          " AND ATTRIBUTES.ATTR_ID = 25 ";

  String UPDATE_TASK_BY_TASK_STATUS =
          " UPDATE ATTRIBUTES " +
          " SET ATTRIBUTES.LIST_VALUE_ID = ? " +
          " WHERE ATTRIBUTES.OBJECT_ID = ? " +
          " AND ATTRIBUTES.ATTR_ID = 26 ";

  String UPDATE_TASK_BY_DESCRIPTION =
          " UPDATE ATTRIBUTES " +
          " SET ATTRIBUTES.VALUE = ? " +
          " WHERE ATTRIBUTES.OBJECT_ID = ? " +
          " AND ATTRIBUTES.ATTR_ID = 27 ";

  String UPDATE_TASK_BY_REOPEN_COUNTER =
          " UPDATE ATTRIBUTES " +
          " SET ATTRIBUTES.VALUE = ? " +
          " WHERE ATTRIBUTES.OBJECT_ID = ? " +
          " AND ATTRIBUTES.ATTR_ID = 28 ";

  String UPDATE_TASK_BY_COMMENT =
          " UPDATE ATTRIBUTES " +
          " SET ATTRIBUTES.VALUE = ? " +
          " WHERE ATTRIBUTES.OBJECT_ID = ? " +
          " AND ATTRIBUTES.ATTR_ID = 29 ";

  String UPDATE_TASK_BY_TASK_AUTHOR =
          " UPDATE OBJREFERENCE " +
          " SET REFERENCE = ? " +
          " WHERE OBJECT_ID = ? " +
          " AND ATTR_ID = 30 ";

  String UPDATE_TASK_BY_TASK_USER_ID =
          " UPDATE OBJREFERENCE " +
          " SET REFERENCE = ? " +
          " WHERE OBJECT_ID = ? " +
          " AND ATTR_ID = 31 ";

  String UPDATE_TASK_BY_TASK_PROJECT =
          " UPDATE OBJREFERENCE " +
          " SET REFERENCE = ? " +
          " WHERE OBJECT_ID = ? " +
          " AND ATTR_ID = 32 ";

  String FIND_TASK_ID_BY_PM_LOGIN = "SELECT PROJECT_ID.OBJECT_ID "
      + "FROM OBJECTS TASK_ID, OBJECTS USER_ID, "
      + "ATTRIBUTES USER_LOGIN, "
      + "OBJREFERENCE TASK_PM_REF "
      + "WHERE USER_LOGIN.ATTR_ID = 10 AND "
      + "USER_LOGIN.VALUE = ? AND "
      + "USER_ID.OBJECT_ID = USER_LOGIN.OBJECT_ID AND "
      + "TASK_PM_REF.ATTR_ID = 30 AND "
      + "TASK_PM_REF.REFERENCE = USER_ID.OBJECT_ID AND "
      + "TASK_ID.OBJECT_ID = TASK_PM_REF.OBJECT_ID";


  String FIND_TASK_ID_BY_USER_LOGIN = "SELECT PROJECT_ID.OBJECT_ID "
      + "FROM OBJECTS TASK_ID, OBJECTS USER_ID, "
      + "ATTRIBUTES USER_LOGIN, "
      + "OBJREFERENCE TASK_PM_REF "
      + "WHERE USER_LOGIN.ATTR_ID = 10 AND "
      + "USER_LOGIN.VALUE = ? AND "
      + "USER_ID.OBJECT_ID = USER_LOGIN.OBJECT_ID AND "
      + "TASK_PM_REF.ATTR_ID = 31 AND "
      + "TASK_PM_REF.REFERENCE = USER_ID.OBJECT_ID AND "
      + "TASK_ID.OBJECT_ID = TASK_PM_REF.OBJECT_ID";

  String FIND_TASK_ID_BY_USER_AND_STATUS_PER_PERIOD =
      " SELECT TASK.OBJECT_ID AS TASK_ID, TASK_NAME.VALUE AS NAME, TASK_TYPE_VALUE.VALUE AS TASK_TYPE, " +
          " TASK_START_DATE.DATE_VALUE AS START_DATE, TASK_END_DATE.DATE_VALUE AS END_DATE, TASK_PLANNED_END_DATE.DATE_VALUE AS PLANNED_END_DATE, " +
          " TASK_PRIORITY_VALUE.VALUE AS TASK_PRIORITY, TASK_STATUS_VALUE.VALUE AS STATUS, TASK_DESCRIPTION.VALUE AS TASK_DESCRIPTION, " +
          " TASK_REOPEN_COUNTER.VALUE AS REOPEN_COUNTER, TASK_COMMENT.VALUE AS TASK_COMMENT, AUTHOR_ID_REF.REFERENCE AS AUTHOR_ID,  " +
          " USER_ID_REF.REFERENCE AS USER_ID, PROJECT_ID_REF.REFERENCE AS PROJECT_ID " +
          " FROM OBJECTS TASK, OBJECTS EMP, OBJTYPE TASK_TYPE, " +
          " ATTRIBUTES TASK_NAME, ATTRIBUTES TASK_TYPE, ATTRIBUTES TASK_START_DATE, ATTRIBUTES TASK_END_DATE, " +
          " ATTRIBUTES TASK_PLANNED_END_DATE, ATTRIBUTES TASK_PRIORITY, ATTRIBUTES TASK_STATUS, ATTRIBUTES TASK_DESCRIPTION, " +
          " ATTRIBUTES TASK_REOPEN_COUNTER, ATTRIBUTES TASK_COMMENT, " +
          " LISTVALUE TASK_TYPE_VALUE, LISTVALUE TASK_PRIORITY_VALUE, LISTVALUE TASK_STATUS_VALUE, " +
          " OBJREFERENCE PROJECT_ID_REF, OBJREFERENCE AUTHOR_ID_REF, OBJREFERENCE USER_ID_REF " +
          " WHERE TASK_TYPE.OBJECT_TYPE_ID = 3 AND TASK.OBJECT_TYPE_ID = TASK_TYPE.OBJECT_TYPE_ID " +
          " AND EMP.OBJECT_TYPE_ID = 1 " +
          " AND EMP.OBJECT_ID = ? " +
          " AND TASK_STATUS_VALUE.LIST_VALUE_ID = ? " +
          " AND TASK_NAME.ATTR_ID = 20 AND TASK_NAME.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE.ATTR_ID = 21 AND TASK_TYPE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_TYPE_VALUE.ATTR_ID = 21 AND TASK_TYPE_VALUE.LIST_VALUE_ID = TASK_TYPE.LIST_VALUE_ID " +
          " AND TASK_START_DATE.ATTR_ID = 22 AND TASK_START_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_START_DATE.DATE_VALUE >= ? " +
          " AND TASK_END_DATE.ATTR_ID = 23 AND TASK_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_END_DATE.DATE_VALUE <= ? " +
          " AND TASK_PLANNED_END_DATE.ATTR_ID = 24 AND TASK_PLANNED_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY.ATTR_ID = 25 AND TASK_PRIORITY.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_PRIORITY_VALUE.ATTR_ID = 25 AND TASK_PRIORITY_VALUE.LIST_VALUE_ID = TASK_PRIORITY.LIST_VALUE_ID " +
          " AND TASK_STATUS.ATTR_ID = 26 AND TASK_STATUS.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_STATUS_VALUE.ATTR_ID = 26 AND TASK_STATUS_VALUE.LIST_VALUE_ID = TASK_STATUS.LIST_VALUE_ID " +
          " AND TASK_DESCRIPTION.ATTR_ID = 27 AND TASK_DESCRIPTION.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_REOPEN_COUNTER.ATTR_ID = 28 AND TASK_REOPEN_COUNTER.OBJECT_ID = TASK.OBJECT_ID " +
          " AND TASK_COMMENT.ATTR_ID = 29 AND TASK_COMMENT.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND AUTHOR_ID_REF.ATTR_ID = 30 " +
          " AND USER_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND USER_ID_REF.ATTR_ID = 31 " +
          " AND PROJECT_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
          " AND  PROJECT_ID_REF.ATTR_ID = 32 " ;

  String FIND_TASK_IF_EXIST =  "SELECT COUNT(TASK.OBJECT_ID)" +
      " FROM OBJECTS TASK, OBJECTS PROJ, OBJTYPE TASK_TYPE, " +
      " ATTRIBUTES TASK_NAME, ATTRIBUTES TASK_TYPE, ATTRIBUTES TASK_START_DATE, ATTRIBUTES TASK_END_DATE, " +
      " ATTRIBUTES TASK_PLANNED_END_DATE, ATTRIBUTES TASK_PRIORITY, ATTRIBUTES TASK_STATUS, ATTRIBUTES TASK_DESCRIPTION, " +
      " ATTRIBUTES TASK_REOPEN_COUNTER, ATTRIBUTES TASK_COMMENT, " +
      " LISTVALUE TASK_TYPE_VALUE, LISTVALUE TASK_PRIORITY_VALUE, LISTVALUE TASK_STATUS_VALUE, " +
      " OBJREFERENCE PROJECT_ID_REF, OBJREFERENCE AUTHOR_ID_REF, OBJREFERENCE USER_ID_REF " +
      " WHERE TASK_TYPE.OBJECT_TYPE_ID = 3 AND TASK.OBJECT_TYPE_ID = TASK_TYPE.OBJECT_TYPE_ID " +
      " AND TASK.OBJECT_ID = ? " +
      " AND TASK_NAME.ATTR_ID = 20 AND TASK_NAME.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_TYPE.ATTR_ID = 21 AND TASK_TYPE.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_TYPE_VALUE.ATTR_ID = 21 AND TASK_TYPE_VALUE.LIST_VALUE_ID = TASK_TYPE.LIST_VALUE_ID " +
      " AND TASK_START_DATE.ATTR_ID = 22 AND TASK_START_DATE.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_END_DATE.ATTR_ID = 23 AND TASK_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_PLANNED_END_DATE.ATTR_ID = 24 AND TASK_PLANNED_END_DATE.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_PRIORITY.ATTR_ID = 25 AND TASK_PRIORITY.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_PRIORITY_VALUE.ATTR_ID = 25 AND TASK_PRIORITY_VALUE.LIST_VALUE_ID = TASK_PRIORITY.LIST_VALUE_ID " +
      " AND TASK_STATUS.ATTR_ID = 26 AND TASK_STATUS.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_STATUS_VALUE.ATTR_ID = 26 AND TASK_STATUS_VALUE.LIST_VALUE_ID = TASK_STATUS.LIST_VALUE_ID " +
      " AND TASK_DESCRIPTION.ATTR_ID = 27 AND TASK_DESCRIPTION.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_REOPEN_COUNTER.ATTR_ID = 28 AND TASK_REOPEN_COUNTER.OBJECT_ID = TASK.OBJECT_ID " +
      " AND TASK_COMMENT.ATTR_ID = 29 AND TASK_COMMENT.OBJECT_ID = TASK.OBJECT_ID " +
      " AND AUTHOR_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
      " AND AUTHOR_ID_REF.ATTR_ID = 30 " +
      " AND USER_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
      " AND USER_ID_REF.ATTR_ID = 31 " +
      " AND PROJ.OBJECT_ID = PROJECT_ID_REF.REFERENCE" +
      " AND PROJ.OBJECT_ID = PROJECT_ID_REF.REFERENCE " +
      " AND PROJECT_ID_REF.OBJECT_ID = TASK.OBJECT_ID " +
      " AND  PROJECT_ID_REF.ATTR_ID = 32 " ;


}
