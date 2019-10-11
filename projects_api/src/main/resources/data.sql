INSERT INTO PRJ_WORKSPACE_GENERAL (project_uid, project_name, project_type, project_rigor, project_state, project_manager, project_completion, EPM_project, project_template) VALUES ('19CDC35F-96D4-450C-882F-0B633D1D405E', 'Project One', 'Offer', 'High', 'COMMITTED', 'IKSANOV Aleksander', 85, 0, 'Offer');
INSERT INTO PRJ_WORKSPACE_GENERAL (project_uid, project_name, project_type, project_rigor, project_state, project_manager, project_completion, EPM_project, project_template) VALUES ('19GHJGHF-96D4-450C-882F-AFGAFGDFS233', 'Project tWO', 'Program', 'Low', 'FORECAST', 'IVANOV Ivan', 35, 1, 'PMO');

INSERT INTO prj_products(product_line, product_name, product_manager, product_group, product_division, product_bu, product_unit, product_team, product_release, project_id) VALUES ('Awesome line', 'Awesome name', 'Awesome manager', 'Awesome group', 'Awesome', 'Awesome', 'Awesome unit', 'Awesome team', 'Awesome release', 1);

INSERT INTO prj_milestones VALUES (1, 'OR', '2019-04-09', '2019-04-10', 100, 'www.google.com', 1);
INSERT INTO prj_milestones VALUES (1, 'DR0', '2019-09-22', '2019-09-29', 50, 'www.google.com', 0);
INSERT INTO prj_milestones VALUES (1, 'DR1', '2019-10-19', '2019-03-05', 50, 'www.google.com', 1);
INSERT INTO prj_milestones VALUES (1, 'DR2', '2019-11-22', '2019-11-30', 50, 'www.google.com', 0);
INSERT INTO prj_milestones VALUES (1, 'DR3', '2019-12-24', '2019-12-25', 50, 'www.google.com', 1);
INSERT INTO prj_milestones VALUES (1, 'DR4', '2019-12-29', '2019-09-24', 50, 'www.google.com', 0);
INSERT INTO prj_milestones VALUES (1, 'DR5', '2020-01-06', '2020-01-15', 50, 'www.google.com', 1);
INSERT INTO prj_milestones VALUES (1, 'OBR', '2020-01-16', '2020-01-31', 50, 'www.google.com', 1);
INSERT INTO prj_milestones VALUES (1, 'CI', '2020-02-06', '2019-07-18', 50, 'www.google.com', 1);

INSERT INTO prj_workspace_info VALUES (1, 'ENABLED', '2019-04-11', '2019-04-11', 'aiksanov');
INSERT INTO prj_workspace_info VALUES (2, 'DISABLED', '2019-04-11', '2019-04-11', 'aiksanov');

INSERT INTO prj_indicators_health VALUES (1, 1, 1, 2, 1, 0, '2019-04-11');
INSERT INTO prj_indicators_health VALUES (1, 1, 1, 2, 0, 2, '2019-04-16');
INSERT INTO prj_indicators_health VALUES (1, 3, 3, 3, 3, 3, '2019-04-30');

INSERT INTO prj_indicators_comments VALUES (1, 'overall', 'Overall comment');
INSERT INTO prj_indicators_comments VALUES (1, 'schedule', 'Schedule comment');
INSERT INTO prj_indicators_comments VALUES (1, 'scope', 'Scope comment');
INSERT INTO prj_indicators_comments VALUES (1, 'quality', 'Quality comment');
INSERT INTO prj_indicators_comments VALUES (1, 'cost', 'Cost comment');

INSERT INTO prj_urls VALUES (1, 'www.charter.url', 'or.bplan.url', 'upd.bplan.url', 'www.tailored.ru', 'www.lessons.learned', 'project.collab.url', 'pwa.url', 'repo.url', 'defects.url', 'req.url', 'cis.url');

INSERT INTO prj_additional_info VALUES (1, 'Super nice project, biach', 'Awesome BL Manager', 'Awesome Sponsor', 'OEM Partner');

INSERT INTO prj_status_report
    VALUES (1,
            'This is huge summary for project one. There is a lot of text. It includes Red Yellow and Green bullets. Most probably should be separated from summary report',
            'And this is actions. There are a lot of actions for each project.'
);

INSERT INTO prj_indicators_reqs VALUES (1, 1, 3, 0, 2);

INSERT INTO prj_blc_roles VALUES ('pm', 'Project Manager');
INSERT INTO prj_blc_roles VALUES ('pmo', 'PMO - Quality');
INSERT INTO prj_blc_roles VALUES ('sales', 'Sales');

INSERT INTO prj_blc_dashboard VALUES (1, 'pm', '2019-09-15', 'aiksanov', 1, 2, 3, 1, 2, 3, 3, 2, 1, 1, 2, 3, 'Comment1!');
INSERT INTO prj_blc_dashboard VALUES (1, 'pmo', '2019-09-14', 'aiksanov2', 3, 2, 1, 3, 2, 1, 3, 2, 1, 3, 2, 1, 'Comment1!');
INSERT INTO prj_blc_dashboard VALUES (1, 'sales', '2019-09-13', 'aiksanov3', 3, 2, 3, 1, 3, 2, 3, 2, 3, 1, 3, 3, 'Comment1!');

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