drop database IF EXISTS ocssystem;
create database ocssystem;
use ocssystem;

--1客户表
DROP TABLE IF EXISTS `customerInfo`;
CREATE TABLE `customerInfo` (
`customer_id` int(11) PRIMARY KEY auto_increment,
`customer_nickname` varchar(255),
`customer_name` varchar(255) NOT NULL,
`customer_status` int default 0,
`customer_age` int default 0,
`customer_sex` int NOT NULL,		-- 0为女,1为男
`customer_email`  varchar(255) DEFAULT NULL,
`customer_pwd`  varchar(20) NOT NULL,
`customer_address`  varchar(255) DEFAULT NULL,
`customer_img`  varchar(255) DEFAULT NULL
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
 --余康测试
insert into customerInfo values(2000,'yukang','yukang',1,20,1,'123456@qq.com','123','hefei','cus01.jpg');

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
`company_name`  varchar(255) NOT NULL,
---分配方式
`dstribution_type` int DEFAULT 0,
---不能自增
`robot_id`  int(10) NOT NULL,
`robot_open_flag` int DEFAULT 0,
`customer_info_flag` int DEFAULT 0,
`mininum_operating_num` int DEFAULT 0,
---0网页 1悬浮式
`access_type` int DEFAULT 0,
`robot_name`  varchar(255) NOT NULL
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
--余康测试
insert into companyInfo values(1,"facebook",0,0,1,0,0,0,"miki");
insert into companyInfo values(2,"CISCO",0,0,0,0,0,0,"XIAO AI");
insert into companyInfo values(3,"SUMSUNG",1,1,1,1,3,1,"XIAO BING");

--4客服表
DROP TABLE IF EXISTS `customerServiceInfo`;
CREATE TABLE `customerServiceInfo` (
`cs_id` int(20) PRIMARY KEY auto_increment,
`cs_workId` varchar(255) NOT NULL,
`cs_name` varchar(255) NOT NULL,
`cs_register_status` int default 0,
`cs_nickName` varchar(255) NOT NULL,
`company_id` int(20) NOT NULL,
`cs_pwd`  varchar(20) NOT NULL,
`cs_email`  varchar(255) DEFAULT NULL,
`cs_img`  varchar(255) DEFAULT NULL,
--已接待人数
`cs_greetings`  int DEFAULT 0,
`cs_status` int DEFAULT 0,
`cs_operating_number` int(2)  DEFAULT 3,  --客服自己设置
`cs_waiting_number` int(2)  DEFAULT 3,    --客服自己设置
`cs_operated_number` int(2) DEFAULT 0,
`cs_waited_number` int(2) DEFAULT 0,
`cs_score`  float DEFAULT 0,
`cs_code` varchar(255)
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
--余康测试
insert into customerServiceInfo values(1000,'2015214421','gongchao',1,'kefu1','1','123','809049664@qq.com',
'123.jpg',0,1,3,3,0,0,0,'aaa');
insert into customerServiceInfo values(1001,'2015214421','tom',1,'kefu2','1','123','809049664@qq.com',
'123.jpg',0,1,3,3,0,0,0,'aaa');

insert into customerServiceInfo values(1002,'2015214440','Hu yichuan','1','Pokerface',1,'123456','841160480@qq.com',
			'12.jpg',124,1,0,0,1,1,5.0,'aaabbb');
insert into customerServiceInfo values(1003,'2015214459','Ye Fei','1','Sherhom',1,'123456','846353477@qq.com',
            '1.jpg',124,0,0,0,1,1,5.0,'aaabbb');

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
`start_time`  timestamp NOT NULL default CURRENT_TIMESTAMP,
`end_time`  timestamp NOT NULL default CURRENT_TIMESTAMP,
`score` int(20) DEFAULT NULL,
UNIQUE KEY `conversation_id` (`conversation_id`),
FOREIGN KEY (`customer_id`) REFERENCES `customerInfo` (`customer_id`) ,
FOREIGN KEY (`cs_id`) REFERENCES `customerServiceInfo` (`cs_id`)
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
--余康测试
insert into conversation(conversation_id,customer_id,cs_id,score) values(1,2000,1000,0);

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

insert into chatLog(conversation_id,receiver_id,sender_id,from_customer,content_type,content)
values(1,1000,2000,1,0,'aaa');

--8客服快捷语
DROP TABLE IF EXISTS `cs_shortcut_language`;
CREATE TABLE `cs_shortcut_language` (
`shortcut_language_id` int(20) PRIMARY KEY auto_increment,
`cs_id` int(20),
`content` varchar(255),
FOREIGN KEY (`cs_id`) REFERENCES `customerServiceInfo` (`cs_id`)
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

insert into cs_shortcut_language(cs_id,content)values(1000,'请您稍等');
insert into cs_shortcut_language(cs_id,content)values(1000,'感谢您的评价');
insert into cs_shortcut_language(cs_id,content)values(1000,'这是我的荣幸');

--9客服管理人员查看总信息表
DROP TABLE IF EXISTS `cs_management_tool`;
CREATE TABLE `cs_management_tool` (
`company_id`   int(20) PRIMARY KEY auto_increment,
`waiting_number` int(2),
`operating_number` int(2)
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

insert into cs_management_tool values(1,5,6);

--10知识库 机器人
DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge` (
`knowledge_id`  int(20) PRIMARY KEY auto_increment,
`robot_id` int(20),
`question`  varchar(255) NOT NULL,
`answer`  varchar(255) NOT NULL
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
--余康测试
insert into knowledge values(1,0,"人有几个腿？","2条");
insert into knowledge values(2,0,"大蛇是谁？","变态西尾锦学长");
insert into knowledge values(3,0,"老虎是谁？","虎书翔大佬！");
insert into knowledge values(4,0,"金木是什么级别","SSS级");

--11等待客户表
DROP TABLE IF EXISTS `customer_waiting_team`;
CREATE TABLE `customer_waiting_team` (
`cwt_id`   int(20) PRIMARY KEY auto_increment,
`customer_id` int(11),
`cs_id` int(20)
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
--余康测试
--注意此数据纯属虚构,最终系统测试请勿使用
insert into customer_waiting_team(customer_id,cs_id) values(2000,1000);
insert into customer_waiting_team(customer_id,cs_id) values(2000,1001);
insert into customer_waiting_team(customer_id,cs_id) values(2000,1002);
insert into customer_waiting_team(customer_id,cs_id) values(2000,1003);


--12客服评价表
DROP TABLE IF EXISTS `cs_evaluate`;
CREATE TABLE `cs_evaluate` (
`cse_id`   int(20) PRIMARY KEY auto_increment,
`cs_id` int(11),
`evaluate_time` timestamp NOT NULL default CURRENT_TIMESTAMP,
`cs_score`  float DEFAULT 0,
`content`  varchar(255)

)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;