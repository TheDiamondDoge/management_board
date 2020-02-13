DROP TABLE IF EXISTS PRJ_WORKSPACE_GENERAL;
CREATE TABLE `PRJ_WORKSPACE_GENERAL` (
  `project_id` int(5) NOT NULL AUTO_INCREMENT,
  `project_uid` varchar(255) DEFAULT NULL,
  `project_name` varchar(100) DEFAULT NULL,
  `project_type` varchar(10) DEFAULT NULL,
  `project_rigor` varchar(10) DEFAULT NULL,
  `project_state` varchar(10) DEFAULT NULL,
  `project_manager` varchar(100) DEFAULT NULL,
  `EPM_project` tinyint(1) DEFAULT NULL,
  `project_template` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
);

DROP TABLE IF EXISTS prj_products;
CREATE TABLE IF NOT EXISTS `prj_products` (
  `project_id` int(5) DEFAULT NULL,
  `product_line` varchar(45) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `product_manager` varchar(100) DEFAULT NULL,
  `product_group` varchar(100) DEFAULT NULL,
  `product_division` varchar(10) DEFAULT NULL,
  `product_bu` varchar(10) DEFAULT NULL,
  `product_unit` varchar(45) DEFAULT NULL,
  `product_team` varchar(45) DEFAULT NULL,
  `product_release` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
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
  `overall` int(1) DEFAULT 0,
  `schedule` int(1) DEFAULT 0,
  `scope` int(1) DEFAULT 0,
  `quality` int(1) DEFAULT 0,
  `cost` int(1) DEFAULT 0,
  `modification_date` date NOT NULL,
  PRIMARY KEY (`project_id`, `modification_date`)
);
ALTER TABLE `prj_indicators_health` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_indicators_comments;
CREATE TABLE IF NOT EXISTS `prj_indicators_comments` (
  `project_id` int(5) NOT NULL,
  `label` varchar(10) DEFAULT 0,
  `comment` text DEFAULT 0,
  PRIMARY KEY (`project_id`, `label`)
);
-- ALTER TABLE `prj_indicators_comments` ADD FOREIGN KEY (project_id) REFERENCES prj_indicators_health(project_id);

DROP TABLE IF EXISTS prj_additional_info;
CREATE TABLE IF NOT EXISTS `prj_additional_info` (
  `project_id` int(5) NOT NULL,
  `description` text,
  `business_line_manager` varchar(100) DEFAULT NULL,
  `sponsor` varchar(100) DEFAULT NULL,
  `oem_partner` varchar(256) DEFAULT NULL,
  `key_customer` varchar(256) DEFAULT NULL,
  `composite` tinyint(1) DEFAULT 0,
  `maintenance` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`project_id`)
);
ALTER TABLE `prj_additional_info` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_jira_params;
CREATE TABLE IF NOT EXISTS `prj_jira_params` (
    `project_id` int(5) NOT NULL,
    `metrics_scope` varchar(100) DEFAULT NULL,
    `rq_release` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`project_id`)
);
ALTER TABLE `prj_jira_params` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_urls;
CREATE TABLE IF NOT EXISTS `prj_urls` (
  `project_id` int(5) NOT NULL,
  `charter` varchar(512) DEFAULT NULL,
  `or_business_plan` varchar(512) DEFAULT NULL,
  `updated_business_plan` varchar(512) DEFAULT NULL,
  `tailored_checklist` varchar(512) DEFAULT NULL,
  `lessons_learned` varchar(512) DEFAULT NULL,
  `project_plan` varchar(512) DEFAULT NULL,
  `launching_plan` varchar(512) DEFAULT NULL,
  `project_collab_url` varchar(512) DEFAULT NULL,
  `sales_force` varchar(512) DEFAULT NULL,
  `project_pwa_url` varchar(512) DEFAULT NULL,
  `document_repo_url` varchar(512) DEFAULT NULL,
  `defects_url` varchar(512) DEFAULT NULL,
  `requirements_url` varchar(512) DEFAULT NULL,
  `cis_url` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
);
ALTER TABLE `prj_urls` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_field_comments;
CREATE TABLE IF NOT EXISTS `prj_field_comments` (
    `project_id` int(5) NOT NULL,
    `field_name` VARCHAR(25) NOT NULL,
    `comment` VARCHAR(255) DEFAULT NULL
);
ALTER TABLE `prj_field_comments` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_ecma_backlog_target;
CREATE TABLE IF NOT EXISTS `prj_ecma_backlog_target` (
  `project_id` int(5) NOT NULL,
  `milestone_label` VARCHAR(5) DEFAULT NULL,
  `value` int(5) DEFAULT 0,
  PRIMARY KEY (`project_id`, `milestone_label`)
);
ALTER TABLE `prj_ecma_backlog_target` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_contrib;
CREATE TABLE IF NOT EXISTS `prj_contrib` (
    `project_id` int(5) NOT NULL,
    `contrib_id` int(5) NOT NULL,
    PRIMARY KEY (`project_id`, `contrib_id`)
);
ALTER TABLE `prj_contrib` ADD FOREIGN KEY (`project_id`) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_status_report;
CREATE TABLE IF NOT EXISTS `prj_status_report` (
    `project_id` int(5) NOT NULL,
    `executive_summary` text,
    `red_flag` text,
    `orange_flag` text,
    `green_flag` text,
    `details` text,
    PRIMARY KEY (`project_id`)
);
ALTER TABLE `prj_status_report` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_indicators_reqs;
CREATE TABLE IF NOT EXISTS `prj_indicators_reqs` (
    `project_id` int(5) NOT NULL,
    `committed_at_dr1` int(6),
    `added_after_dr1` int(6),
    `removed_after_dr1` int(6),
    `modified_after_dr1` int(6),
    PRIMARY KEY (`project_id`)
);
ALTER TABLE `prj_indicators_reqs` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_blc_dashboard;
CREATE TABLE IF NOT EXISTS `prj_blc_dashboard` (
    `project_id` int(5) NOT NULL,
    `role` varchar(5) NOT NULL,
    `updated_on` date,
    `updated_by` varchar (20),
    `or` int(1) DEFAULT 0,
    `charter` int(1) DEFAULT 0,
    `prj_plan` int(1) DEFAULT 0,
    `tailoring` int(1) DEFAULT 0,
    `acc_prg_mgr` int(1) DEFAULT 0,
    `acc_core_team` int(1) DEFAULT 0,
    `bp_plan` int(1) DEFAULT 0,
    `bp_sales` int(1) DEFAULT 0,
    `launch_plan` int(1) DEFAULT 0,
    `launch_sales` int(1) DEFAULT 0,
    `lessons` int(1) DEFAULT 0,
    `risks` int(1) DEFAULT 0,
    PRIMARY KEY (`project_id`, `role`)
);
ALTER TABLE `prj_blc_dashboard` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_blc_dashboard_comments;
CREATE TABLE IF NOT EXISTS `prj_blc_dashboard_comments` (
    `project_id` int(5) NOT NULL,
    `role` varchar(5) NOT NULL,
    `comment` text,
    PRIMARY KEY (`project_id`, `role`)
);
ALTER TABLE `prj_blc_dashboard_comments` ADD FOREIGN KEY (project_id, role) REFERENCES prj_blc_dashboard(project_id, role);

DROP TABLE IF EXISTS prj_indicators_quality;
CREATE TABLE IF NOT EXISTS `prj_indicators_quality` (
    `project_id` int(5) NOT NULL,
    `kpi_id` varchar(15) NOT NULL,
    `row_num` int(5) NOT NULL,
    `objective` int(5),
    `actual` int(5),
    PRIMARY KEY (`project_id`, `kpi_id`, `row_num`)
);
ALTER TABLE `prj_indicators_quality` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_indicators_quality_comments;
CREATE TABLE IF NOT EXISTS `prj_indicators_quality_comments` (
    `project_id` int(5) NOT NULL,
    `kpi_id` varchar(15) NOT NULL,
    `comment` text,
    PRIMARY KEY (`project_id`, `kpi_id`)
);
-- ALTER TABLE `prj_indicators_quality_comments` ADD FOREIGN KEY (project_id, kpi_id) REFERENCES prj_indicators_quality(project_id, kpi_id);

DROP TABLE IF EXISTS prj_risks;
CREATE TABLE IF NOT EXISTS `prj_risks` (
    `project_id` int(5) NOT NULL,
    `risk_id` int(3) NOT NULL,
    `risk_display_id` varchar(5),
    `impact` int(2) NOT NULL,
    `probability` varchar(15),
    `rating` float,
    `previous` float,
    `initial` float,
    `risk_description` text,
    `impact_description` text,
    `business_impact` text,
    `risk_response` varchar(512),
    `mitigation` text,
    `decision_date` date,
    `estimated_cost` varchar(100),
    `provision_budget` varchar(100),
    `responsible` varchar(100),
    `related_action` varchar(1024),
    `target` date,
    `done` date,
    `result` date,
    `report` tinyint(1),
    PRIMARY KEY (`project_id`, `risk_id`)
);
ALTER TABLE `prj_risks` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_actions;
CREATE TABLE IF NOT EXISTS `prj_actions` (
    `project_id` int(5) NOT NULL,
    `registry` int(1),
    `uid` int(5) AUTO_INCREMENT NOT NULL,
    `title` varchar(255),
    `state` int(1),
    `priority` int(1),
    `owner` varchar(255),
    `optional_info` text,
    `due` date,
    `description` text,
    `status` text,
    `created_date` date,
    `closed_date` date,
    PRIMARY KEY (`uid`)
);
ALTER TABLE `prj_actions` ADD FOREIGN KEY (project_id) REFERENCES PRJ_WORKSPACE_GENERAL(project_id);

DROP TABLE IF EXISTS prj_actions_registry;
CREATE TABLE IF NOT EXISTS `prj_actions_registry` (
    `registry_id` int(1) NOT NULL,
    `registry_label` varchar(20),
    PRIMARY KEY (registry_id)
);
ALTER TABLE `prj_actions` ADD FOREIGN KEY (`registry`) REFERENCES prj_actions_registry(`registry_id`);

DROP TABLE IF EXISTS prj_actions_state;
CREATE TABLE IF NOT EXISTS `prj_actions_state` (
    `state_id` int(1) NOT NULL,
    `state_label` varchar(20),
    PRIMARY KEY (state_id)
);
ALTER TABLE `prj_actions` ADD FOREIGN KEY (`state`) REFERENCES prj_actions_state(`state_id`);

DROP TABLE IF EXISTS prj_actions_priority;
CREATE TABLE IF NOT EXISTS `prj_actions_priority` (
    `priority_id` int(1) NOT NULL,
    `priority_label` varchar(20),
    PRIMARY KEY (priority_id)
);
ALTER TABLE `prj_actions` ADD FOREIGN KEY (`priority`) REFERENCES prj_actions_priority(`priority_id`);

DROP TABLE IF EXISTS prj_actions_related_risks;
CREATE TABLE IF NOT EXISTS `prj_actions_related_risks` (
    `action_id` int(3) NOT NULL,
    `risk_id` int(3) NOT NULL,
    `project_id` int(5) NOT NULL,
    PRIMARY KEY (action_id, risk_id, project_id),
    FOREIGN KEY (action_id) REFERENCES  `prj_actions` (uid),
    FOREIGN KEY (risk_id) REFERENCES  `prj_risks` (risk_id),
    FOREIGN KEY (project_id) REFERENCES  `prj_risks` (project_id)
);

DROP TABLE IF EXISTS prj_cost;
CREATE TABLE IF NOT EXISTS `prj_cost` (
    `project_id` int(5) NOT NULL,
    `type` int(1) NOT NULL,
    `state` int(1) NOT NULL,
    `milestone` varchar(5),
    `value` double,
    `comment` text,
    PRIMARY KEY (`project_id`, `type`, `state`)
);

DROP TABLE IF EXISTS prj_cost_details;
CREATE TABLE IF NOT EXISTS `prj_cost_details` (
    `project_id` int(5) NOT NULL,
    `updated` date NOT NULL,
    PRIMARY KEY (`project_id`)
);