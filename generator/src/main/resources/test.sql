--DROP TABLE IF EXISTS `t_crm_client`;
CREATE TABLE `t_crm_client`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,

  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `service_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  -- `version` int(11) UNSIGNED DEFAULT NULL,

  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

--DROP TABLE IF EXISTS `t_crm_employer`;
CREATE TABLE `t_crm_employer`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,

  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  -- `status` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  -- `version` int(11) UNSIGNED DEFAULT NULL,
  `client_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,

  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


--DROP TABLE IF EXISTS `t_crm_employee`;
CREATE TABLE `t_crm_employee`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,

    -- ======= start ==========
    `er_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,

    `cost_center` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `department` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `custom_code` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `is_er_li` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `er_li_rate` float(24, 2) DEFAULT NULL,

    `code` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `position` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `status` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,

    `type` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `hire_date` datetime(0) DEFAULT NULL,
    `departure_date` datetime(0) DEFAULT NULL,
    `contract_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,

    `probation_salary` float(24, 2) DEFAULT NULL,
    `probation_end_date` datetime(0) DEFAULT NULL,
    `base_salary` float(24, 2) DEFAULT NULL,
    `net_base_salary` float(24, 2) DEFAULT NULL,
    `daily_salary` float(24, 2) DEFAULT NULL,
    `exparts_subsistence_allowance` float(24, 2) DEFAULT NULL,
    `fixed_allowance` float(24, 2) DEFAULT NULL,
    `transportation_allowance` float(24, 2) DEFAULT NULL,
    `mobile_allowance` float(24, 2) DEFAULT NULL,
    `meal_allowance` float(24, 2) DEFAULT NULL,
    `heating_allowance` float(24, 2) DEFAULT NULL,
    `other_fixed_allowance` float(24, 2) DEFAULT NULL,
    `middle_shift_allowance` float(24, 2) DEFAULT NULL,
    `night_shift_allowance` float(24, 2) DEFAULT NULL,
    `birthday_allowance` float(24, 2) DEFAULT NULL,
    `enterprise_annuity_deduction` float(24, 2) DEFAULT NULL,
    `one_child_allowance` float(24, 2) DEFAULT NULL,
    `union_fee` float(24, 2) DEFAULT NULL,
    `thirteenth_bonus_month` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `is_13salary_annul_tax` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `is_ab_erIIT` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `f001` float(24, 2) DEFAULT NULL,
    `f002` float(24, 2) DEFAULT NULL,
    `f003` float(24, 2) DEFAULT NULL,
    `er_withdraw` float(24, 2) DEFAULT NULL,
    `f005` float(24, 2) DEFAULT NULL,

    `sihf_location` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `residence_type` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `residence_address` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `si_account` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `si_province` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `si_city` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `si_status` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `si_base` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `si_er_base` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `si_startdate` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `si_enddate` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `si_location_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `hf_account` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `hf_sup_account` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `hf_province` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `hf_city` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `hf_base` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `hf_er_base` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `hf_startdate` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `hf_enddate` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `hf_location_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `is_new_sihf_acctount` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `four_hospital` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,

    `iit_location` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `tax_refund_rate` float(24, 2) DEFAULT NULL,

    `commercial_insurance` float(24, 2) DEFAULT NULL,
    `total_cost_of_ci` float(24, 2) DEFAULT NULL,

    `name_en` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `gender` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `nation` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `id_type` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `id_num` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `birthday` datetime(0) DEFAULT NULL,
    `phone` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `email` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `home_address` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `zip_code` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `bank_account` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `bank_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `bank_account_holder` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `bank_address` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `bank_code` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,

    `archive_fee` float(24, 2) DEFAULT NULL,

    `is_portal` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `if_salary` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `if_si` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `if_hf` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `if_iit` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `deposit_rate` float(24, 2) DEFAULT NULL,

    `heating_fee_er` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,

    `withhold_agent_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `salary_location_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `tax_formula` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,

    `ci_end_date` datetime(0) DEFAULT NULL,
    `er_li_end_date` datetime(0) DEFAULT NULL,
    `uion_fee_end_date` datetime(0) DEFAULT NULL,
    `departure_reason_type` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `departure_reason` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    -- ======= end ==========

  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
