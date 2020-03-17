/*
 Navicat Premium Data Transfer

 Source Server         : GentOS-104
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 192.168.0.104:3306
 Source Schema         : springbatch

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 15/03/2020 13:46:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for BATCH_JOB_EXECUTION
-- ----------------------------
DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION`;
CREATE TABLE `BATCH_JOB_EXECUTION` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  `JOB_CONFIGURATION_LOCATION` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`),
  CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `BATCH_JOB_INSTANCE` (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of BATCH_JOB_EXECUTION
-- ----------------------------
BEGIN;
INSERT INTO `BATCH_JOB_EXECUTION` VALUES (1, 2, 1, '2020-03-13 23:03:07', '2020-03-13 23:03:07', '2020-03-13 23:03:07', 'FAILED', 'FAILED', '', '2020-03-13 23:03:07', NULL);
INSERT INTO `BATCH_JOB_EXECUTION` VALUES (2, 2, 2, '2020-03-13 23:08:48', '2020-03-13 23:08:48', '2020-03-13 23:08:48', 'FAILED', 'FAILED', '', '2020-03-13 23:08:48', NULL);
INSERT INTO `BATCH_JOB_EXECUTION` VALUES (3, 2, 3, '2020-03-13 23:12:16', '2020-03-13 23:12:16', '2020-03-13 23:12:16', 'FAILED', 'FAILED', '', '2020-03-13 23:12:16', NULL);
INSERT INTO `BATCH_JOB_EXECUTION` VALUES (4, 2, 4, '2020-03-13 23:13:31', '2020-03-13 23:13:32', '2020-03-14 00:37:11', 'FAILED', 'FAILED', '', '2020-03-14 00:37:11', NULL);
INSERT INTO `BATCH_JOB_EXECUTION` VALUES (5, 1, 5, '2020-03-14 01:01:15', '2020-03-14 01:01:15', NULL, 'STARTED', 'UNKNOWN', '', '2020-03-14 01:01:15', NULL);
INSERT INTO `BATCH_JOB_EXECUTION` VALUES (6, 2, 6, '2020-03-14 12:34:16', '2020-03-14 12:34:16', '2020-03-14 12:35:44', 'FAILED', 'FAILED', '', '2020-03-14 12:35:44', NULL);
INSERT INTO `BATCH_JOB_EXECUTION` VALUES (7, 2, 7, '2020-03-14 12:38:21', '2020-03-14 12:38:22', '2020-03-14 12:39:00', 'FAILED', 'FAILED', '', '2020-03-14 12:39:00', NULL);
INSERT INTO `BATCH_JOB_EXECUTION` VALUES (8, 2, 8, '2020-03-14 12:40:37', '2020-03-14 12:40:37', '2020-03-14 12:40:59', 'FAILED', 'FAILED', '', '2020-03-14 12:40:59', NULL);
INSERT INTO `BATCH_JOB_EXECUTION` VALUES (9, 2, 9, '2020-03-15 13:44:08', '2020-03-15 13:44:08', '2020-03-15 13:44:34', 'FAILED', 'FAILED', '', '2020-03-15 13:44:34', NULL);
COMMIT;

-- ----------------------------
-- Table structure for BATCH_JOB_EXECUTION_CONTEXT
-- ----------------------------
DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION_CONTEXT`;
CREATE TABLE `BATCH_JOB_EXECUTION_CONTEXT` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of BATCH_JOB_EXECUTION_CONTEXT
-- ----------------------------
BEGIN;
INSERT INTO `BATCH_JOB_EXECUTION_CONTEXT` VALUES (1, '{}', NULL);
INSERT INTO `BATCH_JOB_EXECUTION_CONTEXT` VALUES (2, '{}', NULL);
INSERT INTO `BATCH_JOB_EXECUTION_CONTEXT` VALUES (3, '{}', NULL);
INSERT INTO `BATCH_JOB_EXECUTION_CONTEXT` VALUES (4, '{}', NULL);
INSERT INTO `BATCH_JOB_EXECUTION_CONTEXT` VALUES (5, '{}', NULL);
INSERT INTO `BATCH_JOB_EXECUTION_CONTEXT` VALUES (6, '{}', NULL);
INSERT INTO `BATCH_JOB_EXECUTION_CONTEXT` VALUES (7, '{}', NULL);
INSERT INTO `BATCH_JOB_EXECUTION_CONTEXT` VALUES (8, '{}', NULL);
INSERT INTO `BATCH_JOB_EXECUTION_CONTEXT` VALUES (9, '{}', NULL);
COMMIT;

-- ----------------------------
-- Table structure for BATCH_JOB_EXECUTION_PARAMS
-- ----------------------------
DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION_PARAMS`;
CREATE TABLE `BATCH_JOB_EXECUTION_PARAMS` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `TYPE_CD` varchar(6) NOT NULL,
  `KEY_NAME` varchar(100) NOT NULL,
  `STRING_VAL` varchar(250) DEFAULT NULL,
  `DATE_VAL` datetime DEFAULT NULL,
  `LONG_VAL` bigint(20) DEFAULT NULL,
  `DOUBLE_VAL` double DEFAULT NULL,
  `IDENTIFYING` char(1) NOT NULL,
  KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of BATCH_JOB_EXECUTION_PARAMS
-- ----------------------------
BEGIN;
INSERT INTO `BATCH_JOB_EXECUTION_PARAMS` VALUES (1, 'DATE', 'time', '', '2020-03-13 23:03:07', 0, 0, 'Y');
INSERT INTO `BATCH_JOB_EXECUTION_PARAMS` VALUES (2, 'DATE', 'time', '', '2020-03-13 23:08:47', 0, 0, 'Y');
INSERT INTO `BATCH_JOB_EXECUTION_PARAMS` VALUES (3, 'DATE', 'time', '', '2020-03-13 23:12:15', 0, 0, 'Y');
INSERT INTO `BATCH_JOB_EXECUTION_PARAMS` VALUES (4, 'DATE', 'time', '', '2020-03-13 23:13:31', 0, 0, 'Y');
INSERT INTO `BATCH_JOB_EXECUTION_PARAMS` VALUES (5, 'DATE', 'time', '', '2020-03-14 01:01:15', 0, 0, 'Y');
INSERT INTO `BATCH_JOB_EXECUTION_PARAMS` VALUES (6, 'DATE', 'time', '', '2020-03-14 12:34:16', 0, 0, 'Y');
INSERT INTO `BATCH_JOB_EXECUTION_PARAMS` VALUES (7, 'DATE', 'time', '', '2020-03-14 12:38:21', 0, 0, 'Y');
INSERT INTO `BATCH_JOB_EXECUTION_PARAMS` VALUES (8, 'DATE', 'time', '', '2020-03-14 12:40:36', 0, 0, 'Y');
INSERT INTO `BATCH_JOB_EXECUTION_PARAMS` VALUES (9, 'DATE', 'time', '', '2020-03-15 13:44:07', 0, 0, 'Y');
COMMIT;

-- ----------------------------
-- Table structure for BATCH_JOB_EXECUTION_SEQ
-- ----------------------------
DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION_SEQ`;
CREATE TABLE `BATCH_JOB_EXECUTION_SEQ` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of BATCH_JOB_EXECUTION_SEQ
-- ----------------------------
BEGIN;
INSERT INTO `BATCH_JOB_EXECUTION_SEQ` VALUES (9, '0');
COMMIT;

-- ----------------------------
-- Table structure for BATCH_JOB_INSTANCE
-- ----------------------------
DROP TABLE IF EXISTS `BATCH_JOB_INSTANCE`;
CREATE TABLE `BATCH_JOB_INSTANCE` (
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(32) NOT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`),
  UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of BATCH_JOB_INSTANCE
-- ----------------------------
BEGIN;
INSERT INTO `BATCH_JOB_INSTANCE` VALUES (1, 0, 'flowJob', 'fc5c3604100762684b360d7d8e6d18ff');
INSERT INTO `BATCH_JOB_INSTANCE` VALUES (2, 0, 'flowJob', 'f7423f413d11762c9ac3b6c8c88deac7');
INSERT INTO `BATCH_JOB_INSTANCE` VALUES (3, 0, 'flowJob', '7c610d18a4ced99672ed1e5b6f679224');
INSERT INTO `BATCH_JOB_INSTANCE` VALUES (4, 0, 'flowJob', 'a28f9f4bf54f40d694610e4a065df313');
INSERT INTO `BATCH_JOB_INSTANCE` VALUES (5, 0, 'flowJob', '7c9888b6107c1c5c4ae8eace14a10ff0');
INSERT INTO `BATCH_JOB_INSTANCE` VALUES (6, 0, 'flowJob', 'dd3b206ef1e694abb0e9525e90902eaa');
INSERT INTO `BATCH_JOB_INSTANCE` VALUES (7, 0, 'flowJob', '3fb9fb5b7f5066fc9f471522a9335360');
INSERT INTO `BATCH_JOB_INSTANCE` VALUES (8, 0, 'flowJob', '6b5a89fe19113666475036741781fc89');
INSERT INTO `BATCH_JOB_INSTANCE` VALUES (9, 0, 'flowJob', 'd792ce85f4f88c637c8be8518ad49f6e');
COMMIT;

-- ----------------------------
-- Table structure for BATCH_JOB_SEQ
-- ----------------------------
DROP TABLE IF EXISTS `BATCH_JOB_SEQ`;
CREATE TABLE `BATCH_JOB_SEQ` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of BATCH_JOB_SEQ
-- ----------------------------
BEGIN;
INSERT INTO `BATCH_JOB_SEQ` VALUES (9, '0');
COMMIT;

-- ----------------------------
-- Table structure for BATCH_STEP_EXECUTION
-- ----------------------------
DROP TABLE IF EXISTS `BATCH_STEP_EXECUTION`;
CREATE TABLE `BATCH_STEP_EXECUTION` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) NOT NULL,
  `STEP_NAME` varchar(100) NOT NULL,
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `START_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `COMMIT_COUNT` bigint(20) DEFAULT NULL,
  `READ_COUNT` bigint(20) DEFAULT NULL,
  `FILTER_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_COUNT` bigint(20) DEFAULT NULL,
  `READ_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `PROCESS_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `ROLLBACK_COUNT` bigint(20) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of BATCH_STEP_EXECUTION
-- ----------------------------
BEGIN;
INSERT INTO `BATCH_STEP_EXECUTION` VALUES (1, 2, 'generateBillStep', 1, '2020-03-13 23:03:07', '2020-03-13 23:03:07', 'FAILED', 0, 0, 0, 0, 0, 0, 0, 1, 'FAILED', 'java.lang.IllegalArgumentException: org.hibernate.hql.internal.ast.QuerySyntaxException: unexpected token: * near line 1, column 8 [select * from consume_record]\n	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:138)\n	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:181)\n	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:188)\n	at org.hibernate.internal.AbstractSharedSessionContract.createQuery(AbstractSharedSessionContract.java:713)\n	at org.hibernate.internal.AbstractSessionImpl.createQuery(AbstractSessionImpl.java:23)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.orm.jpa.ExtendedEntityManagerCreator$ExtendedEntityManagerInvocationHandler.invoke(ExtendedEntityManagerCreator.java:350)\n	at com.sun.proxy.$Proxy89.createQuery(Unknown Source)\n	at org.springframework.batch.item.database.JpaPagingItemReader.createQuery(JpaPagingItemReader.java:112)\n	at org.springframework.batch.item.database.JpaPagingItemReader.doReadPage(JpaPagingItemReader.java:199)\n	at org.springframework.batch.item.database.AbstractPagingItemReader.doRead(AbstractPagingItemReader.java:108)\n	at org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader.read(AbstractItemCountingItemStreamItemReader.java:92)\n	at org.springframework.batch.core.step.item.SimpleChunkProvider.doRead(SimpleChunkProvider.java:94)\n	at org.springframework.batch.core.step.item.SimpleChunkProvider.read(SimpleChunkProvider.java:161)\n	at org.springframework.batch.core.step.item.SimpleChunkProvider$1.doInIteration(SimpleChunkProvider.java:119)\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\n	at org.springframework.batch.core.step.item.SimpleChunkProvider.provide(SimpleChunkProvider.java:113)\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:69)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(Task', '2020-03-13 23:03:07');
INSERT INTO `BATCH_STEP_EXECUTION` VALUES (2, 2, 'generateBillStep', 2, '2020-03-13 23:08:48', '2020-03-13 23:08:48', 'FAILED', 0, 0, 0, 0, 0, 0, 0, 1, 'FAILED', 'java.lang.IllegalArgumentException: org.hibernate.hql.internal.ast.QuerySyntaxException: unexpected token: * near line 1, column 8 [select * from cn.lsr.entity.ConsumeRecord]\n	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:138)\n	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:181)\n	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:188)\n	at org.hibernate.internal.AbstractSharedSessionContract.createQuery(AbstractSharedSessionContract.java:713)\n	at org.hibernate.internal.AbstractSessionImpl.createQuery(AbstractSessionImpl.java:23)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.orm.jpa.ExtendedEntityManagerCreator$ExtendedEntityManagerInvocationHandler.invoke(ExtendedEntityManagerCreator.java:350)\n	at com.sun.proxy.$Proxy89.createQuery(Unknown Source)\n	at org.springframework.batch.item.database.JpaPagingItemReader.createQuery(JpaPagingItemReader.java:112)\n	at org.springframework.batch.item.database.JpaPagingItemReader.doReadPage(JpaPagingItemReader.java:199)\n	at org.springframework.batch.item.database.AbstractPagingItemReader.doRead(AbstractPagingItemReader.java:108)\n	at org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader.read(AbstractItemCountingItemStreamItemReader.java:92)\n	at org.springframework.batch.core.step.item.SimpleChunkProvider.doRead(SimpleChunkProvider.java:94)\n	at org.springframework.batch.core.step.item.SimpleChunkProvider.read(SimpleChunkProvider.java:161)\n	at org.springframework.batch.core.step.item.SimpleChunkProvider$1.doInIteration(SimpleChunkProvider.java:119)\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\n	at org.springframework.batch.core.step.item.SimpleChunkProvider.provide(SimpleChunkProvider.java:113)\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:69)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTra', '2020-03-13 23:08:48');
INSERT INTO `BATCH_STEP_EXECUTION` VALUES (3, 2, 'generateBillStep', 3, '2020-03-13 23:12:16', '2020-03-13 23:12:16', 'FAILED', 0, 1, 0, 0, 0, 0, 0, 1, 'FAILED', 'java.lang.NullPointerException\n	at cn.lsr.flow.FlowBatchConfig.lambda$generateVisaBillStep$0(FlowBatchConfig.java:64)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doProcess(SimpleChunkProcessor.java:129)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.transform(SimpleChunkProcessor.java:306)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:205)\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:75)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:203)\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:68)\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:67)\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:136)\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:313)\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:144)\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:137)\n	', '2020-03-13 23:12:16');
INSERT INTO `BATCH_STEP_EXECUTION` VALUES (4, 2, 'generateBillStep', 4, '2020-03-13 23:13:32', '2020-03-14 00:37:11', 'FAILED', 0, 1, 0, 0, 0, 0, 0, 1, 'FAILED', 'java.lang.NullPointerException\n	at cn.lsr.flow.FlowBatchConfig.lambda$generateVisaBillStep$0(FlowBatchConfig.java:64)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doProcess(SimpleChunkProcessor.java:129)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.transform(SimpleChunkProcessor.java:306)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:205)\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:75)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:203)\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:68)\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:67)\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:136)\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:313)\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:144)\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:137)\n	', '2020-03-14 00:37:11');
INSERT INTO `BATCH_STEP_EXECUTION` VALUES (5, 1, 'generateBillStep', 5, '2020-03-14 01:01:15', NULL, 'STARTED', 0, 0, 0, 0, 0, 0, 0, 0, 'EXECUTING', '', '2020-03-14 01:01:15');
INSERT INTO `BATCH_STEP_EXECUTION` VALUES (6, 2, 'generateBillStep', 6, '2020-03-14 12:34:16', '2020-03-14 12:35:44', 'FAILED', 0, 1, 0, 0, 0, 0, 0, 1, 'FAILED', 'java.lang.NullPointerException\n	at cn.lsr.flow.FlowBatchConfig.lambda$generateVisaBillStep$0(FlowBatchConfig.java:64)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doProcess(SimpleChunkProcessor.java:129)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.transform(SimpleChunkProcessor.java:306)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:205)\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:75)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:203)\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:68)\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:67)\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:136)\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:313)\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:144)\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:137)\n	', '2020-03-14 12:35:44');
INSERT INTO `BATCH_STEP_EXECUTION` VALUES (7, 2, 'generateBillStep', 7, '2020-03-14 12:38:22', '2020-03-14 12:39:00', 'FAILED', 0, 1, 0, 0, 0, 0, 0, 1, 'FAILED', 'java.lang.NullPointerException\n	at cn.lsr.flow.FlowBatchConfig.lambda$generateVisaBillStep$0(FlowBatchConfig.java:64)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doProcess(SimpleChunkProcessor.java:129)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.transform(SimpleChunkProcessor.java:306)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:205)\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:75)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:203)\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:68)\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:67)\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:136)\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:313)\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:144)\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:137)\n	', '2020-03-14 12:39:00');
INSERT INTO `BATCH_STEP_EXECUTION` VALUES (8, 2, 'generateBillStep', 8, '2020-03-14 12:40:37', '2020-03-14 12:40:59', 'FAILED', 0, 1, 0, 0, 0, 0, 0, 1, 'FAILED', 'java.lang.NullPointerException\n	at cn.lsr.flow.FlowBatchConfig.lambda$generateVisaBillStep$0(FlowBatchConfig.java:64)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doProcess(SimpleChunkProcessor.java:129)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.transform(SimpleChunkProcessor.java:306)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:205)\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:75)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:203)\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:68)\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:67)\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:136)\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:313)\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:144)\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:137)\n	', '2020-03-14 12:40:59');
INSERT INTO `BATCH_STEP_EXECUTION` VALUES (9, 2, 'generateBillStep', 9, '2020-03-15 13:44:08', '2020-03-15 13:44:34', 'FAILED', 0, 1, 0, 0, 0, 0, 0, 1, 'FAILED', 'javax.persistence.PersistenceException: org.hibernate.exception.ConstraintViolationException: could not execute statement\n	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:154)\n	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:181)\n	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:188)\n	at org.hibernate.internal.SessionImpl.doFlush(SessionImpl.java:1460)\n	at org.hibernate.internal.SessionImpl.flush(SessionImpl.java:1440)\n	at org.springframework.batch.item.database.JpaItemWriter.write(JpaItemWriter.java:84)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:188)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doWrite(SimpleChunkProcessor.java:154)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.write(SimpleChunkProcessor.java:287)\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:212)\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:75)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:203)\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:68)\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:67)\n	at org.springframework.ba', '2020-03-15 13:44:34');
COMMIT;

-- ----------------------------
-- Table structure for BATCH_STEP_EXECUTION_CONTEXT
-- ----------------------------
DROP TABLE IF EXISTS `BATCH_STEP_EXECUTION_CONTEXT`;
CREATE TABLE `BATCH_STEP_EXECUTION_CONTEXT` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `BATCH_STEP_EXECUTION` (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of BATCH_STEP_EXECUTION_CONTEXT
-- ----------------------------
BEGIN;
INSERT INTO `BATCH_STEP_EXECUTION_CONTEXT` VALUES (1, '{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":0,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `BATCH_STEP_EXECUTION_CONTEXT` VALUES (2, '{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":0,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `BATCH_STEP_EXECUTION_CONTEXT` VALUES (3, '{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":0,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `BATCH_STEP_EXECUTION_CONTEXT` VALUES (4, '{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":0,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `BATCH_STEP_EXECUTION_CONTEXT` VALUES (5, '{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":0,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `BATCH_STEP_EXECUTION_CONTEXT` VALUES (6, '{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":0,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `BATCH_STEP_EXECUTION_CONTEXT` VALUES (7, '{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":0,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `BATCH_STEP_EXECUTION_CONTEXT` VALUES (8, '{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":0,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `BATCH_STEP_EXECUTION_CONTEXT` VALUES (9, '{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":0,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
COMMIT;

-- ----------------------------
-- Table structure for BATCH_STEP_EXECUTION_SEQ
-- ----------------------------
DROP TABLE IF EXISTS `BATCH_STEP_EXECUTION_SEQ`;
CREATE TABLE `BATCH_STEP_EXECUTION_SEQ` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of BATCH_STEP_EXECUTION_SEQ
-- ----------------------------
BEGIN;
INSERT INTO `BATCH_STEP_EXECUTION_SEQ` VALUES (9, '0');
COMMIT;

-- ----------------------------
-- Table structure for consume_record
-- ----------------------------
DROP TABLE IF EXISTS `consume_record`;
CREATE TABLE `consume_record` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `userId` varchar(128) NOT NULL COMMENT '用户id',
  `consumption` decimal(10,0) NOT NULL COMMENT '花费金额',
  `isGenerateBill` varchar(32) DEFAULT 'false' COMMENT '是否生成账单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of consume_record
-- ----------------------------
BEGIN;
INSERT INTO `consume_record` VALUES (1, '1', 100, 'false');
COMMIT;

-- ----------------------------
-- Table structure for month_bill
-- ----------------------------
DROP TABLE IF EXISTS `month_bill`;
CREATE TABLE `month_bill` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `userId` varchar(128) NOT NULL COMMENT '用户id',
  `totalFee` decimal(10,0) NOT NULL COMMENT '总费用',
  `isPaid` varchar(32) DEFAULT 'false' COMMENT '是否已还',
  `isNotice` varchar(32) DEFAULT 'false' COMMENT '是否通知',
  `createTime` date NOT NULL DEFAULT '0000-00-00' COMMENT '生成时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of month_bill
-- ----------------------------
BEGIN;
INSERT INTO `month_bill` VALUES (1, '1', 100, 'false', 'false', '2020-03-13');
COMMIT;

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `username` varchar(128) NOT NULL COMMENT '用户名字',
  `accountBalance` decimal(10,0) NOT NULL COMMENT '账户余额',
  `accountStatus` varchar(32) DEFAULT NULL COMMENT '账户状态',
  `createTime` date NOT NULL DEFAULT '0000-00-00' COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_account
-- ----------------------------
BEGIN;
INSERT INTO `user_account` VALUES (1, '测试', 1000, '0', '2020-03-13');
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `userId` varchar(128) NOT NULL COMMENT '用户id',
  `totalFee` decimal(10,0) NOT NULL COMMENT '总费用',
  `name` varchar(32) NOT NULL COMMENT '用户姓名',
  `age` varchar(32) NOT NULL COMMENT '性别',
  `description` varchar(128) DEFAULT NULL COMMENT '详情',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES (1, '001', 100, '测试', '男', '测试客户信息');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
