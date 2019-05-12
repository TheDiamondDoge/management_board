DROP TABLE IF EXISTS PRJ_WORKSPACE_GENERAL;
CREATE TABLE `PRJ_WORKSPACE_GENERAL` (
  `project_id` int(5) NOT NULL AUTO_INCREMENT,
  `project_uid` varchar(255) DEFAULT NULL,
  `project_name` varchar(100) DEFAULT NULL,
  `project_type` varchar(10) DEFAULT NULL,
  `project_rigor` varchar(10) DEFAULT NULL,
  `project_state` varchar(10) DEFAULT NULL,
  `project_manager` varchar(100) DEFAULT NULL,
  `project_completion` int(3) DEFAULT NULL,
  `EPM_project` tinyint(4) DEFAULT NULL,
  `project_template` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
);

DROP TABLE IF EXISTS prj_products;
CREATE TABLE IF NOT EXISTS `prj_products` (
  `product_id` int(5) NOT NULL AUTO_INCREMENT,
  `product_line` varchar(45) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `product_manager` varchar(100) DEFAULT NULL,
  `product_group` varchar(100) DEFAULT NULL,
  `product_division` varchar(10) DEFAULT NULL,
  `product_bu` varchar(10) DEFAULT NULL,
  `product_unit` varchar(45) DEFAULT NULL,
  `product_team` varchar(45) DEFAULT NULL,
  `product_release` varchar(45) DEFAULT NULL,
  `project_id` int(5) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
);
ALTER TABLE `prj_products` ADD FOREIGN KEY (project_id) REFERENCES `PRJ_WORKSPACE_GENERAL`(project_id);

DROP TABLE IF EXISTS prj_milestones;
CREATE TABLE IF NOT EXISTS `prj_milestones` (
  `project_id` int(5) NOT NULL,
  `milestone_label` varchar(45) NOT NULL,
  `baseline_date` date DEFAULT NULL,
  `actual_date` date DEFAULT NULL,
  `completion` int(3) DEFAULT NULL,
  `url` varchar(512) DEFAULT NULL,
  `show_in_timeline` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`project_id`,`milestone_label`)
);
ALTER TABLE `prj_milestones` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_workspace_info;
CREATE TABLE IF NOT EXISTS `prj_workspace_info` (
  `project_id` int(5) NOT NULL,
  `workspace_status` varchar(10) DEFAULT NULL,
  `workspace_created` date DEFAULT NULL,
  `workspace_modified` datetime DEFAULT NULL,
  `workspace_modified_by` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
);
ALTER TABLE `prj_workspace_info` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_indicators_health;
CREATE TABLE IF NOT EXISTS `prj_indicators_health` (
  `project_id` int(5) NOT NULL,
  `label` varchar(20) NOT NULL,
  `status` int(1) DEFAULT NULL,
  `modification_date` date NOT NULL,
  PRIMARY KEY (`project_id`, `modification_date`, `label`)
);
ALTER TABLE `prj_indicators_health` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_additional_info;
CREATE TABLE IF NOT EXISTS `prj_additional_info` (
  `project_id` int(5) NOT NULL,
  `description` text,
  `business_line_manager` varchar(100) DEFAULT NULL,
  `sponsor` varchar(100) DEFAULT NULL,
  `oem_partner` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
);
ALTER TABLE `prj_additional_info` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_urls;
CREATE TABLE IF NOT EXISTS `prj_urls` (
  `project_id` int(5) NOT NULL,
  `charter` varchar(512) DEFAULT NULL,
  `or_business_plan` varchar(512) DEFAULT NULL,
  `updated_business_plan` varchar(512) DEFAULT NULL,
  `tailored_checklist` varchar(512) DEFAULT NULL,
  `lessons_learned` varchar(512) DEFAULT NULL,
  `project_collab_url` varchar(512) DEFAULT NULL,
  `project_pwa_url` varchar(512) DEFAULT NULL,
  `document_repo_url` varchar(512) DEFAULT NULL,
  `defects_url` varchar(512) DEFAULT NULL,
  `requirements_url` varchar(512) DEFAULT NULL,
  `cis_url` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
);
ALTER TABLE `prj_urls` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);