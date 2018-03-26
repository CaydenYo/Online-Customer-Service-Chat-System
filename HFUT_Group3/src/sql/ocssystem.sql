create database scssystem;

--1客户表
DROP TABLE IF EXISTS `customerInfo`;
CREATE TABLE `customerInfo` (
`customer_id` int(11) PRIMARY KEY auto_increment,
`customer_name` varchar(20) NOT NULL,
`customer_sex` int NOT NULL,		-- 0为女,1为男
`customer_email`  varchar(255) DEFAULT NULL,
`customer_pwd`  varchar(20) NOT NULL,
`customer_address`  varchar(255) DEFAULT NULL,
`customer_img`  varchar(255) DEFAULT NULL
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


--2游客表
DROP TABLE IF EXISTS `aCustomerInfo`;
CREATE TABLE `aCustomerInfo` (
`acustomer_id`  int(20) PRIMARY KEY auto_increment,
`acustomer_mac` varchar(255) DEFAULT NULL
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


--3公司表
DROP TABLE IF EXISTS `companyInfo`;
CREATE TABLE `companyInfo` (
`company_id`  int(20) PRIMARY KEY auto_increment,
`company_name`  varchar(20) NOT NULL,
---分配方式
`dstribution_type` int DEFAULT 0,
---不能自增
`robot_id`  int(10) NOT NULL,
`robot_name`  varchar(20) NOT NULL
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


--4客服表
DROP TABLE IF EXISTS `customerServiceInfo`;
CREATE TABLE `customerServiceInfo` (
`cs_id` int(20) PRIMARY KEY auto_increment,
`cs_workId` varchar(20) NOT NULL,
`cs_name` varchar(20) NOT NULL,
`cs_nickName` varchar(20) NOT NULL,
`cs_pwd`  varchar(20) NOT NULL,
`cs_email`  varchar(255) DEFAULT NULL,
`cs_img`  varchar(255) DEFAULT NULL,
--已接待人数
`cs_greetings`  int DEFAULT 0,
`cs_status` int DEFAULT 0,
`cs_operating_number` int(2)  DEFAULT 3,
`cs_waiting_number` int(2)  DEFAULT 3,
`cs_score`  float
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

--5客服管理人员表
DROP TABLE IF EXISTS `csManagerInfo`;
CREATE TABLE `csManagerInfo` (
`csm_id`  int(20) PRIMARY KEY auto_increment,
`csm_email` varchar(255) DEFAULT NULL,
`csm_pwd` varchar(20) NOT NULL,
`company_id` int(20) NOT NULL,
FOREIGN KEY (`company_id`) REFERENCES `companyInfo`(`company_id`)
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

--6对话表
DROP TABLE IF EXISTS `conversation`;
CREATE TABLE `conversation` (
`conversation_id` int(20) PRIMARY KEY auto_increment,
`customer_id`int(20) NOT NULL,
`cs_id` int(20) NOT NULL,
`start_time`  timestamp NOT NULL,
`end_time`  timestamp NOT NULL default CURRENT_TIMESTAMP,
`score` int(20) DEFAULT NULL,
UNIQUE KEY `conversation_id` (`conversation_id`),
FOREIGN KEY (`customer_id`) REFERENCES `customerInfo` (`customer_id`) ,
FOREIGN KEY (`cs_id`) REFERENCES `customerServiceInfo` (`cs_id`)
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

--7会话语句表
DROP TABLE IF EXISTS `chatLog`;
CREATE TABLE `chatLog` (
`chatLog_id` int(20) PRIMARY KEY auto_increment,
`conversation_id` int(20),
`receiver_id` int(20),
`sender_id` int(20),
--0为客户 1为客服
`from_customer` int(2),
`time` timestamp,
--0为文字，1为表情，2为图片
`content_type` int(3),
`content` varchar(255),
FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`conversation_id`)
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;



--8客服快捷语
DROP TABLE IF EXISTS `cs_shortcut_language`;
CREATE TABLE `cs_shortcut_language` (
`shortcut_language_id` int(20) PRIMARY KEY auto_increment,
`cs_id` int(20),
`content` varchar(255),
FOREIGN KEY (`cs_id`) REFERENCES `customerServiceInfo` (`cs_id`)
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


--9客服管理人员查看总信息表
DROP TABLE IF EXISTS `cs_management_tool`;
CREATE TABLE `cs_management_tool` (
`company_id`   int(20) PRIMARY KEY auto_increment,
`waiting_number` int(2),
`operating_number` int(2)
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

--10知识库 机器人
DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge` (
`knowledge_id`  int(20) PRIMARY KEY auto_increment,
`robot_id` int(20),
`question`  varchar(255) NOT NULL,
`answer`  varchar(255) NOT NULL
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
