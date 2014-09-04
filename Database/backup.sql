/*
 Navicat Premium Data Transfer

 Source Server         : LibraryManagementSystem
 Source Server Type    : PostgreSQL
 Source Server Version : 90305
 Source Host           : 127.0.0.1
 Source Database       : librarymanagementsystem
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90305
 File Encoding         : utf-8

 Date: 09/03/2014 23:27:05 PM
*/

-- ----------------------------
--  Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS "public"."book";
CREATE TABLE "public"."book" (
	"title" varchar(2044) NOT NULL COLLATE "default",
	"authors" varchar(2044) COLLATE "default",
	"release_date" date,
	"pages" int4,
	"publisher" varchar(2044) COLLATE "default",
	"description" text COLLATE "default",
	"barcode" int8,
	"isbn" varchar(2044) COLLATE "default",
	"edition" int4,
	"category" varchar(2044) COLLATE "default",
	"rating" float8,
	"book_id" int4 NOT NULL DEFAULT nextval('"Book_book_id_seq"'::regclass),
	"image_fpath" varchar(2044) COLLATE "default",
	"summary" text COLLATE "default",
	"price" float8
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."book" OWNER TO "lms";

COMMENT ON TABLE "public"."book" IS 'Describes the book, not to be confused with the book entity, which describes the physical copy of the book stored in our library.
This one just describes a book in general containing all of it information. Physical book may not necessarily even exist.';

-- ----------------------------
--  Table structure for book_entity
-- ----------------------------
DROP TABLE IF EXISTS "public"."book_entity";
CREATE TABLE "public"."book_entity" (
	"available" bool NOT NULL DEFAULT true,
	"book_id" int4 NOT NULL,
	"location" varchar(2044) COLLATE "default",
	"book_entity_id" int4 NOT NULL DEFAULT nextval('book_entity_book_entity_id_seq'::regclass),
	"leased" bool NOT NULL DEFAULT false,
	"acquisition_date" date
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."book_entity" OWNER TO "lms";

-- ----------------------------
--  Table structure for book_lease
-- ----------------------------
DROP TABLE IF EXISTS "public"."book_lease";
CREATE TABLE "public"."book_lease" (
	"lease_id" int8 NOT NULL DEFAULT nextval('"BookLease_lease_id_seq"'::regclass),
	"lease_date" date NOT NULL,
	"due_date" date NOT NULL,
	"renewed" bool NOT NULL DEFAULT false,
	"member_id" int4 NOT NULL,
	"employee_id" int4 NOT NULL,
	"book_entity_id" int4 NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."book_lease" OWNER TO "lms";

-- ----------------------------
--  Table structure for book_return
-- ----------------------------
DROP TABLE IF EXISTS "public"."book_return";
CREATE TABLE "public"."book_return" (
	"book_entity_id" int4,
	"lease_date" date,
	"due_date" date,
	"member_id" int4,
	"ammount_charged" money,
	"return_date" date,
	"member_comments" varchar COLLATE "default",
	"member_rating" float8,
	"employee_id" int4,
	"book_return_id" int4 NOT NULL DEFAULT nextval('book_return_book_return_id_seq'::regclass)
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."book_return" OWNER TO "lms";

-- ----------------------------
--  Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS "public"."employee";
CREATE TABLE "public"."employee" (
	"firstname" varchar(2044) NOT NULL COLLATE "default",
	"lastname" varchar(2044) NOT NULL COLLATE "default",
	"role" varchar(2044) NOT NULL COLLATE "default",
	"employee_id" int4 NOT NULL DEFAULT nextval('"Employee_employee_id_seq"'::regclass)
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."employee" OWNER TO "lms";

-- ----------------------------
--  Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS "public"."item";
CREATE TABLE "public"."item" (
	"name" varchar COLLATE "default",
	"description" varchar COLLATE "default",
	"condition" varchar COLLATE "default",
	"category" varchar COLLATE "default",
	"item_id" int4 NOT NULL DEFAULT nextval('item_item_id_seq'::regclass),
	"price" float8
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."item" OWNER TO "lms";

-- ----------------------------
--  Table structure for item_entity
-- ----------------------------
DROP TABLE IF EXISTS "public"."item_entity";
CREATE TABLE "public"."item_entity" (
	"item_id" int4,
	"acquisition_date" date,
	"leased" bool DEFAULT false,
	"available" bool DEFAULT true,
	"location" varchar COLLATE "default",
	"item_entity_id" int4 NOT NULL DEFAULT nextval('item_entity_item_entity_id_seq'::regclass)
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."item_entity" OWNER TO "lms";

-- ----------------------------
--  Table structure for item_lease
-- ----------------------------
DROP TABLE IF EXISTS "public"."item_lease";
CREATE TABLE "public"."item_lease" (
	"item_entity_id" int4,
	"lease_date" date,
	"due_date" date,
	"renewed" bool DEFAULT false,
	"member_id" int4,
	"employee_id" int4,
	"item_lease_id" int4 NOT NULL DEFAULT nextval('item_lease_item_lease_id_seq'::regclass)
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."item_lease" OWNER TO "lms";

-- ----------------------------
--  Table structure for item_return
-- ----------------------------
DROP TABLE IF EXISTS "public"."item_return";
CREATE TABLE "public"."item_return" (
	"item_entity_id" int4,
	"member_id" int4,
	"employee_id" int4,
	"lease_date" date,
	"due_date" date,
	"return_date" date,
	"ammount_charged" money,
	"item_return_id" int4 NOT NULL DEFAULT nextval('item_return_item_return_id_seq'::regclass)
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."item_return" OWNER TO "lms";

-- ----------------------------
--  Table structure for magazine
-- ----------------------------
DROP TABLE IF EXISTS "public"."magazine";
CREATE TABLE "public"."magazine" (
	"title" varchar(2044) NOT NULL COLLATE "default",
	"magazine_id" int4 NOT NULL DEFAULT nextval('"Magazine_magazine_id_seq"'::regclass),
	"publisher" varchar(2044) COLLATE "default",
	"author" varchar(2044) COLLATE "default",
	"frequency" varchar(2044) COLLATE "default",
	"language" varchar(2044) COLLATE "default",
	"isbn" varchar(2044) COLLATE "default",
	"price" float8
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."magazine" OWNER TO "lms";

-- ----------------------------
--  Table structure for magazine_edition
-- ----------------------------
DROP TABLE IF EXISTS "public"."magazine_edition";
CREATE TABLE "public"."magazine_edition" (
	"magazine_id" int4 NOT NULL,
	"mag_edition_id" int4 NOT NULL DEFAULT nextval('magazine_edition_mag_edition_id_seq'::regclass),
	"edition_date" date NOT NULL,
	"edition_title" varchar COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."magazine_edition" OWNER TO "lms";

-- ----------------------------
--  Table structure for magazine_entity
-- ----------------------------
DROP TABLE IF EXISTS "public"."magazine_entity";
CREATE TABLE "public"."magazine_entity" (
	"magazine_edition_id" int4 NOT NULL,
	"mag_entity_id" int4 NOT NULL DEFAULT nextval('magazine_entity_mag_entity_id_seq'::regclass),
	"available" bool NOT NULL DEFAULT true
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."magazine_entity" OWNER TO "lms";

-- ----------------------------
--  Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS "public"."member";
CREATE TABLE "public"."member" (
	"firstname" varchar(2044) NOT NULL COLLATE "default",
	"lastname" varchar(2044) NOT NULL COLLATE "default",
	"dob" date,
	"registration_date" date,
	"username" varchar(2044) COLLATE "default",
	"country" varchar(100) COLLATE "default",
	"city" varchar(100) COLLATE "default",
	"postal_code" varchar(10) COLLATE "default",
	"address_1" varchar(2044) COLLATE "default",
	"address_2" varchar(2044) COLLATE "default",
	"address_3" varchar(2044) COLLATE "default",
	"email" varchar(200) COLLATE "default",
	"phone_number" varchar(20) COLLATE "default",
	"member_id" int4 NOT NULL DEFAULT nextval('member_member_id_seq'::regclass),
	"balance" float8
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."member" OWNER TO "lms";

-- ----------------------------
--  Primary key structure for table book
-- ----------------------------
ALTER TABLE "public"."book" ADD PRIMARY KEY ("book_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table book
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16554" AFTER UPDATE ON "public"."book" FROM "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16554" ON "public"."book" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16553" AFTER DELETE ON "public"."book" FROM "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16553" ON "public"."book" IS NULL;

-- ----------------------------
--  Primary key structure for table book_entity
-- ----------------------------
ALTER TABLE "public"."book_entity" ADD PRIMARY KEY ("book_entity_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Uniques structure for table book_entity
-- ----------------------------
ALTER TABLE "public"."book_entity" ADD CONSTRAINT "unique_book_id" UNIQUE ("book_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table book_entity
-- ----------------------------
CREATE UNIQUE INDEX  "book_entity_book_entity_id_key" ON "public"."book_entity" USING btree(book_entity_id ASC NULLS LAST);

-- ----------------------------
--  Triggers structure for table book_entity
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16577" AFTER UPDATE ON "public"."book_entity" FROM "public"."book_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16577" ON "public"."book_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16576" AFTER DELETE ON "public"."book_entity" FROM "public"."book_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16576" ON "public"."book_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16775" AFTER UPDATE ON "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16775" ON "public"."book_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16774" AFTER DELETE ON "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16774" ON "public"."book_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16556" AFTER UPDATE ON "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16556" ON "public"."book_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16555" AFTER INSERT ON "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16555" ON "public"."book_entity" IS NULL;

-- ----------------------------
--  Primary key structure for table book_lease
-- ----------------------------
ALTER TABLE "public"."book_lease" ADD PRIMARY KEY ("lease_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Uniques structure for table book_lease
-- ----------------------------
ALTER TABLE "public"."book_lease" ADD CONSTRAINT "unique_book_entity_id" UNIQUE ("book_entity_id") NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."book_lease" ADD CONSTRAINT "unique_employee_id" UNIQUE ("employee_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table book_lease
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16579" AFTER UPDATE ON "public"."book_lease" FROM "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16579" ON "public"."book_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16578" AFTER INSERT ON "public"."book_lease" FROM "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16578" ON "public"."book_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16618" AFTER UPDATE ON "public"."book_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16618" ON "public"."book_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16617" AFTER INSERT ON "public"."book_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16617" ON "public"."book_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16586" AFTER UPDATE ON "public"."book_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16586" ON "public"."book_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16585" AFTER INSERT ON "public"."book_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16585" ON "public"."book_lease" IS NULL;

-- ----------------------------
--  Primary key structure for table book_return
-- ----------------------------
ALTER TABLE "public"."book_return" ADD PRIMARY KEY ("book_return_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table book_return
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16777" AFTER UPDATE ON "public"."book_return" FROM "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16777" ON "public"."book_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16776" AFTER INSERT ON "public"."book_return" FROM "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16776" ON "public"."book_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16782" AFTER UPDATE ON "public"."book_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16782" ON "public"."book_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16781" AFTER INSERT ON "public"."book_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16781" ON "public"."book_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16787" AFTER UPDATE ON "public"."book_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16787" ON "public"."book_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16786" AFTER INSERT ON "public"."book_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16786" ON "public"."book_return" IS NULL;

-- ----------------------------
--  Primary key structure for table employee
-- ----------------------------
ALTER TABLE "public"."employee" ADD PRIMARY KEY ("employee_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table employee
-- ----------------------------
CREATE UNIQUE INDEX  "employee_employee_id_key" ON "public"."employee" USING btree(employee_id ASC NULLS LAST);

-- ----------------------------
--  Triggers structure for table employee
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16584" AFTER UPDATE ON "public"."employee" FROM "public"."book_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16584" ON "public"."employee" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16583" AFTER DELETE ON "public"."employee" FROM "public"."book_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16583" ON "public"."employee" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16822" AFTER UPDATE ON "public"."employee" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16822" ON "public"."employee" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16821" AFTER DELETE ON "public"."employee" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16821" ON "public"."employee" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16785" AFTER UPDATE ON "public"."employee" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16785" ON "public"."employee" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16784" AFTER DELETE ON "public"."employee" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16784" ON "public"."employee" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16807" AFTER UPDATE ON "public"."employee" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16807" ON "public"."employee" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16806" AFTER DELETE ON "public"."employee" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16806" ON "public"."employee" IS NULL;

-- ----------------------------
--  Primary key structure for table item
-- ----------------------------
ALTER TABLE "public"."item" ADD PRIMARY KEY ("item_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table item
-- ----------------------------
CREATE UNIQUE INDEX  "item_item_id_key" ON "public"."item" USING btree(item_id ASC NULLS LAST);

-- ----------------------------
--  Triggers structure for table item
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16791" AFTER UPDATE ON "public"."item" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16791" ON "public"."item" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16790" AFTER DELETE ON "public"."item" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16790" ON "public"."item" IS NULL;

-- ----------------------------
--  Primary key structure for table item_entity
-- ----------------------------
ALTER TABLE "public"."item_entity" ADD PRIMARY KEY ("item_entity_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table item_entity
-- ----------------------------
CREATE UNIQUE INDEX  "item_entity_item_entity_id_key" ON "public"."item_entity" USING btree(item_entity_id ASC NULLS LAST);

-- ----------------------------
--  Triggers structure for table item_entity
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16812" AFTER UPDATE ON "public"."item_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16812" ON "public"."item_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16811" AFTER DELETE ON "public"."item_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16811" ON "public"."item_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16797" AFTER UPDATE ON "public"."item_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16797" ON "public"."item_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16796" AFTER DELETE ON "public"."item_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16796" ON "public"."item_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16793" AFTER UPDATE ON "public"."item_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16793" ON "public"."item_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16792" AFTER INSERT ON "public"."item_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16792" ON "public"."item_entity" IS NULL;

-- ----------------------------
--  Primary key structure for table item_lease
-- ----------------------------
ALTER TABLE "public"."item_lease" ADD PRIMARY KEY ("item_lease_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table item_lease
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16804" AFTER UPDATE ON "public"."item_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16804" ON "public"."item_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16803" AFTER INSERT ON "public"."item_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16803" ON "public"."item_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16809" AFTER UPDATE ON "public"."item_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16809" ON "public"."item_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16808" AFTER INSERT ON "public"."item_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16808" ON "public"."item_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16799" AFTER UPDATE ON "public"."item_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16799" ON "public"."item_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16798" AFTER INSERT ON "public"."item_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16798" ON "public"."item_lease" IS NULL;

-- ----------------------------
--  Primary key structure for table item_return
-- ----------------------------
ALTER TABLE "public"."item_return" ADD PRIMARY KEY ("item_return_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table item_return
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16819" AFTER UPDATE ON "public"."item_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16819" ON "public"."item_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16818" AFTER INSERT ON "public"."item_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16818" ON "public"."item_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16824" AFTER UPDATE ON "public"."item_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16824" ON "public"."item_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16823" AFTER INSERT ON "public"."item_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16823" ON "public"."item_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16814" AFTER UPDATE ON "public"."item_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16814" ON "public"."item_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16813" AFTER INSERT ON "public"."item_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16813" ON "public"."item_return" IS NULL;

-- ----------------------------
--  Primary key structure for table magazine
-- ----------------------------
ALTER TABLE "public"."magazine" ADD PRIMARY KEY ("magazine_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table magazine
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16533" AFTER UPDATE ON "public"."magazine" FROM "public"."magazine_edition" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16533" ON "public"."magazine" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16532" AFTER DELETE ON "public"."magazine" FROM "public"."magazine_edition" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16532" ON "public"."magazine" IS NULL;

-- ----------------------------
--  Primary key structure for table magazine_edition
-- ----------------------------
ALTER TABLE "public"."magazine_edition" ADD PRIMARY KEY ("mag_edition_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table magazine_edition
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16570" AFTER UPDATE ON "public"."magazine_edition" FROM "public"."magazine_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16570" ON "public"."magazine_edition" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16569" AFTER DELETE ON "public"."magazine_edition" FROM "public"."magazine_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16569" ON "public"."magazine_edition" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16535" AFTER UPDATE ON "public"."magazine_edition" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16535" ON "public"."magazine_edition" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16534" AFTER INSERT ON "public"."magazine_edition" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16534" ON "public"."magazine_edition" IS NULL;

-- ----------------------------
--  Primary key structure for table magazine_entity
-- ----------------------------
ALTER TABLE "public"."magazine_entity" ADD PRIMARY KEY ("mag_entity_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Uniques structure for table magazine_entity
-- ----------------------------
ALTER TABLE "public"."magazine_entity" ADD CONSTRAINT "unique_magazine_edition_id" UNIQUE ("magazine_edition_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table magazine_entity
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16572" AFTER UPDATE ON "public"."magazine_entity" FROM "public"."magazine_edition" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16572" ON "public"."magazine_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16571" AFTER INSERT ON "public"."magazine_entity" FROM "public"."magazine_edition" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16571" ON "public"."magazine_entity" IS NULL;

-- ----------------------------
--  Primary key structure for table member
-- ----------------------------
ALTER TABLE "public"."member" ADD PRIMARY KEY ("member_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table member
-- ----------------------------
CREATE UNIQUE INDEX  "member_member_id_key" ON "public"."member" USING btree(member_id ASC NULLS LAST);

-- ----------------------------
--  Triggers structure for table member
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16616" AFTER UPDATE ON "public"."member" FROM "public"."book_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16616" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16615" AFTER DELETE ON "public"."member" FROM "public"."book_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16615" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16817" AFTER UPDATE ON "public"."member" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16817" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16816" AFTER DELETE ON "public"."member" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16816" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16780" AFTER UPDATE ON "public"."member" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16780" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16779" AFTER DELETE ON "public"."member" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16779" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16802" AFTER UPDATE ON "public"."member" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16802" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16801" AFTER DELETE ON "public"."member" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16801" ON "public"."member" IS NULL;

-- ----------------------------
--  Foreign keys structure for table book_entity
-- ----------------------------
ALTER TABLE "public"."book_entity" ADD CONSTRAINT "lnk_book_entity_book2" FOREIGN KEY ("book_id") REFERENCES "public"."book" ("book_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table book_lease
-- ----------------------------
ALTER TABLE "public"."book_lease" ADD CONSTRAINT "lnk_book_lease_book_entity2" FOREIGN KEY ("book_entity_id") REFERENCES "public"."book_entity" ("book_entity_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."book_lease" ADD CONSTRAINT "lnk_book_lease_employee2" FOREIGN KEY ("employee_id") REFERENCES "public"."employee" ("employee_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."book_lease" ADD CONSTRAINT "lnk_book_lease_member" FOREIGN KEY ("member_id") REFERENCES "public"."member" ("member_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table book_return
-- ----------------------------
ALTER TABLE "public"."book_return" ADD CONSTRAINT "lnk_book_entity_id" FOREIGN KEY ("book_entity_id") REFERENCES "public"."book_entity" ("book_entity_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."book_return" ADD CONSTRAINT "lnk_member_id" FOREIGN KEY ("member_id") REFERENCES "public"."member" ("member_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."book_return" ADD CONSTRAINT "lnk_employee_id" FOREIGN KEY ("employee_id") REFERENCES "public"."employee" ("employee_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table item_entity
-- ----------------------------
ALTER TABLE "public"."item_entity" ADD CONSTRAINT "lnk_item_id" FOREIGN KEY ("item_id") REFERENCES "public"."item" ("item_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table item_lease
-- ----------------------------
ALTER TABLE "public"."item_lease" ADD CONSTRAINT "lnk_item_entity_id" FOREIGN KEY ("item_entity_id") REFERENCES "public"."item_entity" ("item_entity_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."item_lease" ADD CONSTRAINT "lnk_member_id" FOREIGN KEY ("member_id") REFERENCES "public"."member" ("member_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."item_lease" ADD CONSTRAINT "lnk_employee_id" FOREIGN KEY ("employee_id") REFERENCES "public"."employee" ("employee_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table item_return
-- ----------------------------
ALTER TABLE "public"."item_return" ADD CONSTRAINT "lnk_item_entity_id" FOREIGN KEY ("item_entity_id") REFERENCES "public"."item_entity" ("item_entity_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."item_return" ADD CONSTRAINT "lnk_member_id" FOREIGN KEY ("member_id") REFERENCES "public"."member" ("member_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."item_return" ADD CONSTRAINT "lnk_employee_id" FOREIGN KEY ("employee_id") REFERENCES "public"."employee" ("employee_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table magazine_edition
-- ----------------------------
ALTER TABLE "public"."magazine_edition" ADD CONSTRAINT "lnk_magazine_edition_magazine2" FOREIGN KEY ("magazine_id") REFERENCES "public"."magazine" ("magazine_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table magazine_entity
-- ----------------------------
ALTER TABLE "public"."magazine_entity" ADD CONSTRAINT "lnk_magazine_entity_magazine_edition2" FOREIGN KEY ("magazine_edition_id") REFERENCES "public"."magazine_edition" ("mag_edition_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

