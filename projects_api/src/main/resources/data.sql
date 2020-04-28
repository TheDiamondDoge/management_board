INSERT INTO PRJ_WORKSPACE_GENERAL (project_uid, project_name, project_type, project_rigor, project_state, project_manager, EPM_project, project_template) VALUES ('19CDC35F-96D4-450C-882F-0B633D1D405E', 'Pineapple', 'Offer', 'High', 'COMMITTED', 'IKSANOV Aleksander',  0, 'Offer');
INSERT INTO PRJ_WORKSPACE_GENERAL (project_uid, project_name, project_type, project_rigor, project_state, project_manager, EPM_project, project_template) VALUES ('19GHJGHF-96D4-450C-882F-AFGAFGDFS233', 'Cherry', 'Program', 'Low', 'FORECAST', 'IVANOV Ivan', 1, 'PMO');
INSERT INTO PRJ_WORKSPACE_GENERAL (project_uid, project_name, project_type, project_rigor, project_state, project_manager, EPM_project, project_template) VALUES ('19GHJGHF-96D4-450C-882F-AF2321232233', 'Tomato', 'Program', 'Low', 'FORECAST', 'IVANOV Ivan', 0, 'PMO');
INSERT INTO PRJ_WORKSPACE_GENERAL (project_uid, project_name, project_type, project_rigor, project_state, project_manager, EPM_project, project_template) VALUES ('19GHJGHF-6754-450C-882F-AFGAFGDFS233', 'Project Four', 'Program', 'Low', 'FORECAST', 'IVANOV Ivan', 0, 'PMO');

INSERT INTO prj_products(product_line, product_name, product_manager, product_group, product_division, product_bu, product_unit, product_team, product_release, project_id) VALUES ('Awesome line', 'Awesome name', 'Awesome manager', 'Awesome group', 'CBD', 'Awesome', 'Awesome unit', 'Awesome team', 'Awesome release', 1);

INSERT INTO prj_milestones VALUES (1, 'OR', '2019-10-19', '2019-03-05', 50, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (1, 'DR0', '2019-09-22', '2019-09-29', 50, 'www.google.com', 0);
INSERT INTO prj_milestones VALUES (1, 'DR1', '2019-04-09', '2019-04-10', 100, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (1, 'DR2', '2019-11-22', '2019-11-30', 50, 'www.google.com', 0);
INSERT INTO prj_milestones VALUES (1, 'DR3', '2019-12-24', '2019-12-25', 50, 'www.google.com', 1);
INSERT INTO prj_milestones VALUES (1, 'DR4', '2019-09-24', '2020-02-22', 50, 'www.google.com', 0);
INSERT INTO prj_milestones VALUES (1, 'OBR', '2020-01-16', '2020-02-06', 50, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (1, 'CI', '2020-02-06', '2020-02-06', 50, 'www.google.com', 1);
INSERT INTO prj_milestones VALUES (1, 'DR5', '2020-01-06', '2020-01-15', 50, 'www.google.com', 1);

INSERT INTO prj_milestones VALUES (3, 'OR', '2019-10-19', '2019-03-05', 50, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (3, 'DR0', '2019-09-22', '2019-09-29', 50, 'www.google.com', 0);
INSERT INTO prj_milestones VALUES (3, 'DR1', '2019-04-09', '2019-04-10', 100, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (3, 'DR2', '2019-11-22', '2019-11-30', 50, 'www.google.com', 0);
INSERT INTO prj_milestones VALUES (3, 'DR3', '2019-12-24', '2019-12-25', 50, 'www.google.com', 1);
INSERT INTO prj_milestones VALUES (3, 'DR4', '2019-09-24', NULL, 50, 'www.google.com', 0);
INSERT INTO prj_milestones VALUES (3, 'OBR', '2020-01-16', '2020-02-06', 50, 'http://www.google.com', 1);
INSERT INTO prj_milestones VALUES (3, 'CI', '2020-02-06', '2020-02-06', 50, 'www.google.com', 1);
INSERT INTO prj_milestones VALUES (3, 'DR5', '2020-01-06', '2020-01-15', 50, 'www.google.com', 1);

INSERT INTO prj_workspace_info VALUES (1, 'ENABLED', '2019-04-11', '2019-04-11', 'aiksanov');
INSERT INTO prj_workspace_info VALUES (2, 'DISABLED', '2019-04-11', '2019-04-11', 'aiksanov');
INSERT INTO prj_workspace_info VALUES (3, 'ENABLED', '2019-04-11', '2019-04-11', 'aiksanov');
INSERT INTO prj_workspace_info VALUES (4, 'ENABLED', '2019-04-11', '2019-04-11', 'aiksanov');

INSERT INTO prj_indicators_health VALUES (1, 1, 1, 2, 1, 0, '2019-04-11');
INSERT INTO prj_indicators_health VALUES (1, 1, 1, 2, 0, 2, '2019-04-16');
INSERT INTO prj_indicators_health VALUES (1, 3, 3, 3, 3, 3, '2019-04-30');

INSERT INTO prj_indicators_comments VALUES (1, 'overall', 'Overall comment');
INSERT INTO prj_indicators_comments VALUES (1, 'schedule', 'Schedule comment');
INSERT INTO prj_indicators_comments VALUES (1, 'scope', 'Scope comment');
INSERT INTO prj_indicators_comments VALUES (1, 'quality', 'Quality comment');
INSERT INTO prj_indicators_comments VALUES (1, 'cost', 'Cost comment');

INSERT INTO prj_urls VALUES (1, 'www.charter.url', 'or.bplan.url', 'upd.bplan.url', 'www.tailored.ru', 'www.lessons.learned', 'www.project.plan', 'www.launching.plan', 'project.collab.url', 'www.sales.force', 'pwa.url', 'repo.url', 'defects.url', 'req.url', 'cis.url');

INSERT INTO prj_field_comments VALUES (1, 'updatedBusinessPlan', 'test bp');
INSERT INTO prj_field_comments VALUES (1, 'drChecklist', 'test check');
INSERT INTO prj_field_comments VALUES (1, 'lessonsLearned', 'test ll');
INSERT INTO prj_field_comments VALUES (1, 'projectPlan', 'test pp');
INSERT INTO prj_field_comments VALUES (1, 'launchingPlan', 'test lp');

INSERT INTO prj_additional_info VALUES (1, 'Super nice project, biach', 'Awesome BL Manager', 'Awesome Sponsor', 'OEM Partner', 'Key customer', 0, 0);

INSERT INTO prj_jira_params VALUES (1, 'Metrics Scope', 'RQ Release');

INSERT INTO prj_ecma_backlog_target VALUES (1, 'DR4', 101);
INSERT INTO prj_ecma_backlog_target VALUES (1, 'TR', 999);

INSERT INTO prj_contrib VALUES (1, 3);
INSERT INTO prj_contrib VALUES (1, 4);

INSERT INTO prj_status_report
   VALUES (1,
           'This is huge summary for project one. There is a lot of text. It includes Red Yellow and Green bullets. Most probably should be separated from summary report',
           'This is the Red flags section',
           'This is the Orange flags section',
           'This is the Green flags section',
           'This is the Details section',
           '2020-04-20',
           1

 );

INSERT INTO prj_indicators_reqs VALUES (1, 1, 3, 0, 2);

INSERT INTO prj_blc_dashboard VALUES (1, 'PM', '2019-09-15', 'aiksanov', 1, 5, 8, 1, 5, 8, 8, 5, 1, 1, 5, 8);
INSERT INTO prj_blc_dashboard VALUES (1, 'PMO', '2019-09-14', 'aiksanov2', 8, 5, 1, 8, 5, 1, 8, 5, 1, 8, 5, 1);
INSERT INTO prj_blc_dashboard VALUES (1, 'SALES', '2019-09-13', 'aiksanov3', 8, 5, 8, 1, 8, 5, 8, 5, 8, 1, 8, 8);

INSERT INTO prj_blc_dashboard_comments VALUES (1, 'PM', 'Comment1');
INSERT INTO prj_blc_dashboard_comments VALUES (1, 'PMO', 'Comment2');
INSERT INTO prj_blc_dashboard_comments VALUES (1, 'SALES', 'Comment3');

INSERT INTO prj_indicators_quality VALUES (1, 'quality', 1, 100, 95);
INSERT INTO prj_indicators_quality VALUES (1, 'defects', 1, 200, 195);
INSERT INTO prj_indicators_quality VALUES (1, 'backlog', 1, 300, 295);
INSERT INTO prj_indicators_quality VALUES (1, 'execution', 1, 400, 395);
INSERT INTO prj_indicators_quality VALUES (1, 'execution', 2, 401, 35);
INSERT INTO prj_indicators_quality VALUES (1, 'rate', 1, 500, 495);

INSERT INTO prj_indicators_quality_comments VALUES (1, 'quality', 'Comment on quality');
INSERT INTO prj_indicators_quality_comments VALUES (1, 'defects', 'Comment on defects');
INSERT INTO prj_indicators_quality_comments VALUES (1, 'backlog', 'Comment on backlog');
INSERT INTO prj_indicators_quality_comments VALUES (1, 'execution', 'Comment on execution');
INSERT INTO prj_indicators_quality_comments VALUES (1, 'rate', 'Comment on rate');

INSERT INTO prj_risks VALUES (1, 1, '1.1', 4, 0.7, 1, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', 0);
INSERT INTO prj_risks VALUES (1, 2, '1.2', 4, 0.7, 2, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', 1);
INSERT INTO prj_risks VALUES (1, 3, '1.3', 4, 0.7, 3, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', 0);
INSERT INTO prj_risks VALUES (1, 4, '1.4', 4, 0.7, 4, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', 1);
INSERT INTO prj_risks VALUES (1, 5, '1.5', 4, 0.7, 6, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', 0);
INSERT INTO prj_risks VALUES (1, 6, '1.6', 4, 0.7, 7, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', 0);
INSERT INTO prj_risks VALUES (1, 7, '1.7', 4, 0.7, 9, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', 0);
INSERT INTO prj_risks VALUES (1, 8, '1.8', 4, 0.7, 10, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', 0);
INSERT INTO prj_risks VALUES (1, 9, '1.9', 4, 0.7, 11.5, 1.0, 1.0, 'Many products in parallel', 'Overloaded Metiers', 'Business impact indeed', 'FAst response, Sonic style', 'Mitigation:Priority on content\nLast chance corrective action:Delay product availability or some major content', '2020-12-15', '1000ME', '2000ME', 'Iksanov', '12CaE', '2020-12-15', '2020-12-15', '2020-12-15', 0);

INSERT INTO prj_actions_registry VALUES (0, ''), (1, 'Action'), (2, 'Informational'), (3, 'Decision');
INSERT INTO prj_actions_state VALUES (0, ''), (1, 'Active'), (2, 'Postponed'), (3, 'Closed'), (4, 'Did not occur');
INSERT INTO prj_actions_priority VALUES (0, ''), (1, 'Low'), (2, 'Medium'), (3, 'High');

INSERT INTO prj_actions VALUES (1, 1, 1, 'First actions to test', 1, 0, 'Aleksandr Iksanov', 'This is the optional info for first action', '2020-02-02', 'High quality action! I hope so...', 'Super status, mate!', '2020-01-15', '2021-01-31');
INSERT INTO prj_actions VALUES (1, 2, 2, 'Second actions to test', 2, 1, 'Aleksandr Iksanov', 'This is the optional info for second action', '2020-02-02', 'High quality action! I hope so...', 'Super status, mate!', '2020-01-15', '2021-01-31');
INSERT INTO prj_actions VALUES (1, 3, 3, 'Third actions to test', 3, 2, 'Aleksandr Iksanov', 'This is the optional info for third action', '2020-02-02', 'High quality action! I hope so...', 'Super status, mate!', '2020-01-15', '2021-01-31');
INSERT INTO prj_actions VALUES (1, 0, 4, 'Fourth actions to test', 4, 3, 'Aleksandr Iksanov', 'This is the optional info for forth action', '2020-02-02', 'High quality action! I hope so...', 'Super status, mate!', '2020-01-15', '2021-01-31');

INSERT INTO prj_actions_related_risks VALUES (2, 1, 1), (2, 2, 1);

INSERT INTO prj_cost VALUES (1, 0, 0, 'DR1', '2340', 'Committed comment 1'),
                            (1, 0, 1, 'DR4', '2000', 'Realized comment 1'),
                            (1, 1, 0, 'DR0', '4287', 'Committed comment 2'),
                            (1, 1, 1, 'DR5', '4200', 'Realized comment 2');
INSERT INTO prj_cost_details VALUES (1, '2020-02-10');