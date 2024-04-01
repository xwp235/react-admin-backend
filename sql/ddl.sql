DROP TABLE IF EXISTS "public"."sys_menu";
CREATE TABLE "public"."sys_menu" (
                                     "id" serial,
                                     "sm_id" int4 NOT NULL,
                                     "sm_parent_id" int4,
                                     "sm_type" int2 NOT NULL,
                                     "sm_path" varchar(255),
                                     "sm_state" int2,
                                     "sm_icon" varchar(60),
                                     "sm_name" jsonb NOT NULL,
                                     "sm_code" varchar(255),
                                     "sm_level_chain" varchar(255) NOT NULL,
                                     "sm_level" int4 NOT NULL,
                                     "sm_sort" int4 NOT NULL,
                                     "sm_remark" varchar(255),
                                     "create_time" timestamptz(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                                     "update_time" timestamptz(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                                     "create_account_id" varchar(30) NOT NULL,
                                     "update_account_id" varchar(30) NOT NULL,
                                     "sm_enable_edit" bool NOT NULL DEFAULT true,
                                     "sm_enable_delete" bool NOT NULL DEFAULT true,
                                     PRIMARY KEY ("id"),
                                     CONSTRAINT "unq_sys_menu_1" UNIQUE ("sm_id"),
                                     CONSTRAINT "unq_sys_menu_2" UNIQUE ("sm_code"),
                                     CONSTRAINT "unq_sys_menu_3" UNIQUE ("sm_path")
);

DROP INDEX IF EXISTS idx_sys_menu_1;
CREATE INDEX idx_sys_menu_1 ON "public"."sys_menu"("sm_parent_id");

COMMENT ON COLUMN "public"."sys_menu"."sm_id" IS '菜单ID';

COMMENT ON COLUMN "public"."sys_menu"."sm_parent_id" IS '父菜单ID，顶级菜单值为NULL';

COMMENT ON COLUMN "public"."sys_menu"."sm_type" IS '菜单类型，菜单=1 按钮=2 页面=3';

COMMENT ON COLUMN "public"."sys_menu"."sm_path" IS '菜单类型为菜单或页面时才需要设置';

COMMENT ON COLUMN "public"."sys_menu"."sm_state" IS '菜单状态  停用=0 启用=1,菜单类型为菜单或页面时才需要设置';

COMMENT ON COLUMN "public"."sys_menu"."sm_icon" IS '菜单图标，仅在菜单类型为按钮时才需要设置';

COMMENT ON COLUMN "public"."sys_menu"."sm_name" IS '菜单名称，支持多语言:[{"ja":"メニュー１","en":"Menu1","ja":"菜单1"}]';

COMMENT ON COLUMN "public"."sys_menu"."sm_code" IS '每项菜单的唯一标识';

COMMENT ON COLUMN "public"."sys_menu"."sm_level_chain" IS '菜单层级链';

COMMENT ON COLUMN "public"."sys_menu"."sm_level" IS '菜单所在层级';

COMMENT ON COLUMN "public"."sys_menu"."sm_sort" IS '菜单所在层级的排序';

COMMENT ON COLUMN "public"."sys_menu"."sm_remark" IS '菜单备注';

DROP TABLE IF EXISTS "public"."mast_account";
CREATE TABLE "public"."mast_account" (
                                     "id" int8,
                                     "ma_user_id" int8 NOT NULL,
                                     "ma_username" varchar(16) NOT NULL,
                                     "create_time" timestamptz(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                                     "update_time" timestamptz(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                                     "update_user_id" int8 NOT NULL,
                                     "data_valid_period" tstzrange NOT NULL DEFAULT '[1970-01-01 00:00:00,2222-12-31 23:59:59]',
                                     PRIMARY KEY ("id"),
                                     CONSTRAINT "unq_ma_account_1" UNIQUE ("ma_username"),
                                     CONSTRAINT "unq_ma_account_2" UNIQUE ("ma_user_id")
);

