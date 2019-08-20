INSERT INTO PRJ_WORKSPACE_GENERAL (project_uid, project_name, project_type, project_rigor, project_state, project_manager, project_completion, EPM_project, project_template) VALUES ('19CDC35F-96D4-450C-882F-0B633D1D405E', 'Project One', 'Offer', 'High', 'COMMITTED', 'IKSANOV Aleksander', 85, 0, 'Offer');
INSERT INTO PRJ_WORKSPACE_GENERAL (project_uid, project_name, project_type, project_rigor, project_state, project_manager, project_completion, EPM_project, project_template) VALUES ('19GHJGHF-96D4-450C-882F-AFGAFGDFS233', 'Project tWO', 'Program', 'Low', 'FORECAST', 'IVANOV Ivan', 35, 1, 'PMO');

INSERT INTO prj_products(product_line, product_name, product_manager, product_group, product_division, product_bu, product_unit, product_team, product_release, project_id) VALUES ('Awesome line', 'Awesome name', 'Awesome manager', 'Awesome group', 'Awesome', 'Awesome', 'Awesome unit', 'Awesome team', 'Awesome release', 1);

INSERT INTO prj_milestones VALUES (1, 'OR', '2019-04-09', '2019-04-10', 100, 'www.google.com', 1);
INSERT INTO prj_milestones VALUES (1, 'DR0', '2019-05-22', '2019-05-23', 50, 'www.google.com', 1);

INSERT INTO prj_workspace_info VALUES (1, 'ENABLED', '2019-04-11', '2019-04-11', 'aiksanov');
INSERT INTO prj_workspace_info VALUES (2, 'DISABLED', '2019-04-11', '2019-04-11', 'aiksanov');

INSERT INTO prj_indicators_health VALUES (1, 'overall', 1, '2019-04-11');
INSERT INTO prj_indicators_health VALUES (1, 'overall', 2, '2019-04-12');
INSERT INTO prj_indicators_health VALUES (1, 'overall', 3, '2018-02-13');
INSERT INTO prj_indicators_health VALUES (1, 'cost', 3, '2019-04-14');
INSERT INTO prj_indicators_health VALUES (1, 'cost', 1, '2017-04-15');
INSERT INTO prj_indicators_health VALUES (1, 'cost', 2, '2014-04-16');
INSERT INTO prj_indicators_health VALUES (1, 'cost', 1, '2018-04-17');
INSERT INTO prj_indicators_health VALUES (1, 'cost', 3, '2014-04-18');
INSERT INTO prj_indicators_health VALUES (1, 'cost', 3, '2018-04-19');
INSERT INTO prj_indicators_health VALUES (1, 'cost', 2, '2019-05-20');

INSERT INTO prj_urls VALUES (1, 'www.charter.url', 'or.bplan.url', 'upd.bplan.url', 'www.tailored.ru', 'www.lessons.learned', 'project.collab.url', 'pwa.url', 'repo.url', 'defects.url', 'req.url', 'cis.url');

INSERT INTO prj_additional_info VALUES (1, 'Super nice project', 'Awesome BL Manager', 'Awesome Sponsor', 'OEM Partner');

INSERT INTO prj_status_report
    VALUES (1,
            'This is huge summary for project one. There is a lot of text. It includes Red Yellow and Green bullets. Most probably should be separated from summary report',
            'And this is actions. There are a lot of actions for each project.'
);