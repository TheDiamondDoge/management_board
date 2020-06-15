INSERT INTO PRJ_WORKSPACE_GENERAL (project_uid, project_name, project_type, project_rigor, project_state, project_manager, EPM_project, project_template) VALUES ('19CDC35F-96D4-450C-882F-0B633D1D405E', 'Office Setup (start you testing here)', 'Offer', 'High', 'COMMITTED', 'SMIRNOV Aleksey',  0, 'Offer');
INSERT INTO PRJ_WORKSPACE_GENERAL (project_uid, project_name, project_type, project_rigor, project_state, project_manager, EPM_project, project_template) VALUES ('19GHJGHF-96D4-450C-882F-AFGAFGDFS233', 'PC', 'Product', 'Medium', 'FORECAST', 'IVANOV Ivan', 0, 'PMO');
INSERT INTO PRJ_WORKSPACE_GENERAL (project_uid, project_name, project_type, project_rigor, project_state, project_manager, EPM_project, project_template) VALUES ('19GHJGHF-96D4-450C-882F-AF2321232233', 'Desktop Phone', 'Product', 'Medium', 'FORECAST', 'IVANOV Ivan', 0, 'PMO');
INSERT INTO PRJ_WORKSPACE_GENERAL (project_uid, project_name, project_type, project_rigor, project_state, project_manager, EPM_project, project_template) VALUES ('19GHJGHF-6754-450C-882F-AFGAFGDFS233', 'Interactive Desk', 'OEM Product', 'Low', 'FORECAST', 'IVANOV Ivan', 0, 'PMO');

INSERT INTO prj_products(project_id, product_line, product_name, product_manager, product_group, product_division, product_bu, product_unit, product_team, product_release) VALUES (1, 'Office Awesome line', 'Office setup 2020', 'COOL Andrew', 'TDD Group', 'NBD', 'OBU', 'Office unit', 'Office team', '0.5-release');
INSERT INTO prj_products(project_id, product_line, product_name, product_manager, product_group, product_division, product_bu, product_unit, product_team, product_release) VALUES (2, 'PC Awesome line', 'Ursus x100', 'BEAR Ivan', 'TDD Group', 'OBD', 'OBU', 'PC unit', 'PC team', '1.5-release');
INSERT INTO prj_products(project_id, product_line, product_name, product_manager, product_group, product_division, product_bu, product_unit, product_team, product_release) VALUES (3, 'Desktop Awesome line', 'Desktop setup 2020', 'FAST Alex', 'TDD Group', 'OBD', 'OBU', 'Desktop unit', 'Desktop team', '1.3-Desktop');
INSERT INTO prj_products(project_id, product_line, product_name, product_manager, product_group, product_division, product_bu, product_unit, product_team, product_release) VALUES (4, 'IntDesc Awesome line', 'IntDesc setup 2020', 'CRAZY Miha', 'TDD Group', 'OBD', 'OBU', 'IntDesc unit', 'IntDesc team', '1.0-release');

INSERT INTO prj_milestones VALUES (1, 'OR', '2019-10-19', '2019-03-05', 50, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (1, 'DR0', '2019-09-22', '2019-09-29', 50, 'That`s cool!', 0);
INSERT INTO prj_milestones VALUES (1, 'DR1', '2019-04-09', '2019-04-10', 100, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (1, 'DR2', '2019-11-22', '2019-11-30', 50, 'http://www.google.com', 0);
INSERT INTO prj_milestones VALUES (1, 'DR3', '2019-12-24', '2019-12-25', 50, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (1, 'DR4', '2019-09-24', '2020-02-22', 50, 'http://www.google.com', 0);
INSERT INTO prj_milestones VALUES (1, 'OBR', '2020-01-16', '2020-02-06', 50, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (1, 'CI', '2020-02-06', '2020-02-06', 50, 'Not cool :(', 1);
INSERT INTO prj_milestones VALUES (1, 'DR5', '2020-01-06', '2020-01-15', 50, 'http://www.google.com', 1);

INSERT INTO prj_milestones VALUES (2, 'OR', '2019-11-22', '2019-11-30', 50, 'http://www.google.com', 0);
INSERT INTO prj_milestones VALUES (2, 'DR1', '2019-12-24', '2019-12-25', 50, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (2, 'DR4', '2019-09-24', '2020-02-22', 50, 'http://www.google.com', 0);
INSERT INTO prj_milestones VALUES (2, 'CI', '2020-01-16', '2020-02-06', 50, 'http://www.google.com', 1);

INSERT INTO prj_milestones VALUES (3, 'OR', '2019-10-19', '2019-03-05', 50, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (3, 'DR0', '2019-09-22', '2019-09-29', 50, 'www.google.com', 0);
INSERT INTO prj_milestones VALUES (3, 'DR1', '2019-04-09', '2019-04-10', 100, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (3, 'DR2', '2019-11-22', '2019-11-30', 50, 'http://www.google.com', 0);
INSERT INTO prj_milestones VALUES (3, 'DR3', '2019-12-24', '2019-12-25', 50, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (3, 'DR4', '2019-09-24', NULL, 50, 'http://www.google.com', 0);
INSERT INTO prj_milestones VALUES (3, 'OBR', '2020-01-16', '2020-02-06', 50, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (3, 'CI', '2020-02-06', '2020-02-06', 50, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (3, 'DR5', '2020-01-06', '2020-01-15', 50, 'http://www.google.com', 1);

INSERT INTO prj_milestones VALUES (4, 'OR', '2019-09-24', NULL, 50, 'www.google.com', 0);
INSERT INTO prj_milestones VALUES (4, 'DR1', '2020-01-16', '2020-02-06', 50, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (4, 'CI', '2020-02-06', '2020-02-06', 50, 'www.google.com', 1);
INSERT INTO prj_milestones VALUES (4, 'DR5', '2020-01-06', '2020-01-15', 50, 'www.google.com', 1);

INSERT INTO prj_workspace_info VALUES (1, 'ENABLED', '2019-04-11', '2019-04-11', 'admin_user');
INSERT INTO prj_workspace_info VALUES (2, 'ENABLED', '2019-04-12', '2019-04-12', 'admin_user');
INSERT INTO prj_workspace_info VALUES (3, 'ENABLED', '2019-04-13', '2019-04-13', 'admin_user');
INSERT INTO prj_workspace_info VALUES (4, 'ENABLED', '2019-04-14', '2019-04-14', 'admin_user');

INSERT INTO prj_indicators_health VALUES (1, 1, 1, 2, 1, 0, '2019-04-11');
INSERT INTO prj_indicators_health VALUES (1, 1, 1, 2, 0, 2, '2019-04-16');
INSERT INTO prj_indicators_health VALUES (1, 3, 3, 3, 3, 3, '2019-04-30');

INSERT INTO prj_indicators_health VALUES (2, 1, 1, 2, 0, 2, '2020-04-16');
INSERT INTO prj_indicators_health VALUES (2, 3, 3, 3, 3, 3, '2020-04-30');

INSERT INTO prj_indicators_health VALUES (3, 1, 1, 2, 0, 2, '2019-08-16');
INSERT INTO prj_indicators_health VALUES (3, 3, 3, 3, 3, 3, '2019-09-30');

INSERT INTO prj_indicators_health VALUES (3, 3, 3, 3, 3, 3, '2020-09-30');

INSERT INTO prj_indicators_comments VALUES (1, 'overall', 'Overall comment');
INSERT INTO prj_indicators_comments VALUES (1, 'schedule', 'Schedule comment');
INSERT INTO prj_indicators_comments VALUES (1, 'scope', 'Scope comment');
INSERT INTO prj_indicators_comments VALUES (1, 'quality', 'Quality comment');
INSERT INTO prj_indicators_comments VALUES (1, 'cost', 'Cost comment');

INSERT INTO prj_indicators_comments VALUES (2, 'schedule', 'Schedule comment');
INSERT INTO prj_indicators_comments VALUES (2, 'scope', 'Scope comment');

INSERT INTO prj_indicators_comments VALUES (3, 'schedule', 'Schedule comment');
INSERT INTO prj_indicators_comments VALUES (3, 'scope', 'Scope comment');

INSERT INTO prj_indicators_comments VALUES (4, 'quality', 'Quality comment');
INSERT INTO prj_indicators_comments VALUES (4, 'cost', 'Cost comment');

INSERT INTO prj_urls VALUES (1, 'http://www.charter.url', 'http://or.bplan.url', 'http://upd.bplan.url', 'http://www.tailored.ru', 'http://www.lessons.learned', 'http://www.project.plan', 'http://www.launching.plan', '', 'http://www.sales.force', 'http://pwa.url', '', 'http://defects.url', 'http://req.url', 'http://cis.url');
INSERT INTO prj_urls VALUES (2, 'http://www.charter.url', '', 'http://upd.bplan.url', 'http://www.tailored.ru', '', 'http://www.project.plan', 'http://www.launching.plan', 'http://project.collab.url', 'http://www.sales.force', '', 'http://repo.url', 'http://defects.url', '', 'http://cis.url');
INSERT INTO prj_urls VALUES (3, 'http://www.charter.url', '', 'http://upd.bplan.url', 'http://www.tailored.ru', 'http://www.lessons.learned', '', 'http://www.launching.plan', 'http://project.collab.url', 'http://www.sales.force', 'http://pwa.url', '', 'http://defects.url', 'http://req.url', 'http://cis.url');
INSERT INTO prj_urls VALUES (4, '', 'http://or.bplan.url', 'http://upd.bplan.url', '', 'http://www.lessons.learned', 'http://www.project.plan', 'http://www.launching.plan', 'http://project.collab.url', 'http://www.sales.force', '', 'http://repo.url', 'http://defects.url', 'http://req.url', 'http://cis.url');

INSERT INTO prj_field_comments VALUES (1, 'updatedBusinessPlan', 'test bp');
INSERT INTO prj_field_comments VALUES (1, 'drChecklist', 'test check');
INSERT INTO prj_field_comments VALUES (1, 'lessonsLearned', 'test ll');
INSERT INTO prj_field_comments VALUES (1, 'projectPlan', 'test pp');
INSERT INTO prj_field_comments VALUES (1, 'launchingPlan', 'test lp');

INSERT INTO prj_field_comments VALUES (2, 'updatedBusinessPlan', 'test bp');
INSERT INTO prj_field_comments VALUES (2, 'drChecklist', 'test check');

INSERT INTO prj_field_comments VALUES (3, 'lessonsLearned', 'test ll');
INSERT INTO prj_field_comments VALUES (3, 'projectPlan', 'test pp');

INSERT INTO prj_field_comments VALUES (4, 'updatedBusinessPlan', 'test bp');
INSERT INTO prj_field_comments VALUES (4, 'lessonsLearned', 'test ll');

INSERT INTO prj_additional_info VALUES (1, 'This is super nice office. Trust me.', 'Awesome BL Manager', 'Awesome Sponsor', 'OEM Partner', 'Key customer', 1, 0);
INSERT INTO prj_additional_info VALUES (2, 'Cool project. You will like it', 'Awesome BL Manager', '', 'OEM Partner', '', 0, 0);
INSERT INTO prj_additional_info VALUES (3, 'Just watch me!', 'Awesome BL Manager', 'Awesome Sponsor', 'OEM Partner', 'Key customer', 0, 0);
INSERT INTO prj_additional_info VALUES (4, 'Very questionable one', '', '', 'OEM Partner', 'Key customer', 0, 0);

INSERT INTO prj_jira_params VALUES (1, 'Metrics Scope', 'RQ Release');
INSERT INTO prj_jira_params VALUES (2, 'Metrics Scope', 'RQ Release');
INSERT INTO prj_jira_params VALUES (3, 'Metrics Scope', 'RQ Release');
INSERT INTO prj_jira_params VALUES (4, 'Metrics Scope', 'RQ Release');

INSERT INTO prj_ecma_backlog_target VALUES (1, 'DR4', 101);
INSERT INTO prj_ecma_backlog_target VALUES (1, 'TR', 999);
INSERT INTO prj_ecma_backlog_target VALUES (2, 'DR4', 1);
INSERT INTO prj_ecma_backlog_target VALUES (3, 'TR', 10);

INSERT INTO prj_contrib VALUES (1, 2);
INSERT INTO prj_contrib VALUES (1, 3);
INSERT INTO prj_contrib VALUES (1, 4);

INSERT INTO prj_status_report
   VALUES (1,
           'This is huge summary for project one. There is a lot of text. It includes Red Yellow and Green bullets. Most probably should be separated from summary report',
           'This is the Red flags section',
           'This is the Orange flags section',
           'This is the Green flags section',
           'This is the Details section'
);

INSERT INTO prj_indicators_reqs VALUES (1, 1, 3, 0, 2);
INSERT INTO prj_indicators_reqs VALUES (2, 2, 3, 1, 2);
INSERT INTO prj_indicators_reqs VALUES (3, 1, 3, 5, 1);
INSERT INTO prj_indicators_reqs VALUES (4, 2, 2, 3, 2);

INSERT INTO prj_blc_dashboard VALUES (1, 'PM', '2019-09-15', 'admin_user', 1, 5, 8, 1, 5, 8, 8, 5, 1, 1, 5, 8);
INSERT INTO prj_blc_dashboard VALUES (1, 'SALES', '2019-09-13', 'admin_user', 8, 5, 8, 1, 8, 5, 8, 5, 8, 1, 8, 8);
INSERT INTO prj_blc_dashboard VALUES (2, 'PMO', '2019-09-14', 'pm_user', 8, 5, 1, 8, 5, 1, 8, 5, 1, 8, 5, 1);

INSERT INTO prj_blc_dashboard_comments VALUES (1, 'PM', 'Comment1');
INSERT INTO prj_blc_dashboard_comments VALUES (1, 'PMO', 'Comment2');
INSERT INTO prj_blc_dashboard_comments VALUES (1, 'SALES', 'Comment3');
INSERT INTO prj_blc_dashboard_comments VALUES (2, 'PMO', 'Comment3');
INSERT INTO prj_blc_dashboard_comments VALUES (3, 'SALES', 'Fill it!');


INSERT INTO prj_indicators_quality VALUES (2, 'quality', 1, 100, 95);
INSERT INTO prj_indicators_quality VALUES (2, 'defects', 1, 200, 195);
INSERT INTO prj_indicators_quality VALUES (2, 'backlog', 1, 300, 295);
INSERT INTO prj_indicators_quality VALUES (2, 'execution', 1, 400, 395);
INSERT INTO prj_indicators_quality VALUES (2, 'execution', 2, 401, 35);
INSERT INTO prj_indicators_quality VALUES (2, 'rate', 1, 500, 'http://google.com');

INSERT INTO prj_indicators_quality_comments VALUES (1, 'quality', 'Comment on quality');
INSERT INTO prj_indicators_quality_comments VALUES (1, 'defects', 'Comment on defects');
INSERT INTO prj_indicators_quality_comments VALUES (1, 'backlog', 'Comment on backlog');
INSERT INTO prj_indicators_quality_comments VALUES (1, 'execution', 'Comment on execution');
INSERT INTO prj_indicators_quality_comments VALUES (1, 'rate', 'Comment on rate');

INSERT INTO prj_risks VALUES (1, 1, '1.1', 4, 0.7, 1, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', null, null, 0);
INSERT INTO prj_risks VALUES (1, 2, '1.2', 4, 0.7, 2, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', '2020-12-15', 'admin_user', 1);
INSERT INTO prj_risks VALUES (1, 3, '1.3', 4, 0.7, 3, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', null, null, 0);
INSERT INTO prj_risks VALUES (1, 4, '1.4', 4, 0.7, 4, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', '2020-12-15', 'admin_user', 1);
INSERT INTO prj_risks VALUES (1, 5, '1.5', 4, 0.7, 6, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', null, null, 0);
INSERT INTO prj_risks VALUES (1, 6, '1.6', 4, 0.7, 7, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', null, null, 0);
INSERT INTO prj_risks VALUES (1, 7, '1.7', 4, 0.7, 9, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', null, null, 0);
INSERT INTO prj_risks VALUES (1, 8, '1.8', 4, 0.7, 10, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', null, null, 0);
INSERT INTO prj_risks VALUES (1, 9, '1.9', 4, 0.7, 11.5, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', null, null, 0);

INSERT INTO prj_risks_info VALUES (1, '2020-06-14', 'admin_user');

INSERT INTO prj_actions VALUES (1, 'ACTION', 1, 'First action to test', 'ACTIVE', 'LOW', 'Aleksandr Iksanov', 'This is the optional info for first action', '2020-02-02', 'High quality action! I hope so...', 'Super status, mate!', '2020-01-15', '2021-01-31');
INSERT INTO prj_actions VALUES (1, 'INFORMATIONAL', 2, 'Second action to test', 'POSTPONED', 'MEDIUM', 'Aleksandr Iksanov', 'This is the optional info for second action', '2020-02-02', 'High quality action! I hope so...', 'Super status, mate!', '2020-01-15', '2021-01-31');
INSERT INTO prj_actions VALUES (1, 'DECISION', 3, 'Third action to test', 'CLOSED', 'HIGH', 'Aleksandr Iksanov', 'This is the optional info for third action', '2020-02-02', 'High quality action! I hope so...', 'Super status, mate!', '2020-01-15', '2021-01-31');
INSERT INTO prj_actions VALUES (1, 'ACTION', 4, 'Fourth action to test', 'NOT_OCCUR', 'HIGH', 'Aleksandr Iksanov', 'This is the optional info for forth action', '2020-02-02', 'High quality action! I hope so...', 'Super status, mate!', '2020-01-15', '2021-01-31');

INSERT INTO prj_actions_related_risks VALUES (2, '1.1', 1), (2, '2.1', 1);

INSERT INTO prj_cost VALUES (1, 0, 0, 'DR1', '2340', 'Committed comment 1'),
                            (1, 0, 1, 'DR4', '2000', 'Realized comment 1'),
                            (1, 1, 0, 'DR0', '4287', 'Committed comment 2'),
                            (1, 1, 1, 'DR5', '4200', 'Realized comment 2');
INSERT INTO prj_cost_details VALUES (1, '2020-02-10');

INSERT INTO prj_cost VALUES (2, 0, 0, 'DR1', '2340', 'Committed comment 1'),
                            (2, 1, 1, 'DR5', '4200', 'Realized comment 2');
INSERT INTO prj_cost_details VALUES (2, '2020-05-11');