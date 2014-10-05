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

 Date: 10/01/2014 16:29:36 PM
*/

-- ----------------------------
--  Sequence structure for BookLease_lease_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."BookLease_lease_id_seq";
CREATE SEQUENCE "public"."BookLease_lease_id_seq" INCREMENT 1 START 20 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."BookLease_lease_id_seq" OWNER TO "lms";

-- ----------------------------
--  Sequence structure for Book_book_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."Book_book_id_seq";
CREATE SEQUENCE "public"."Book_book_id_seq" INCREMENT 1 START 101449 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."Book_book_id_seq" OWNER TO "lms";

-- ----------------------------
--  Sequence structure for Employee_employee_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."Employee_employee_id_seq";
CREATE SEQUENCE "public"."Employee_employee_id_seq" INCREMENT 1 START 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."Employee_employee_id_seq" OWNER TO "lms";

-- ----------------------------
--  Sequence structure for Magazine_magazine_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."Magazine_magazine_id_seq";
CREATE SEQUENCE "public"."Magazine_magazine_id_seq" INCREMENT 1 START 28 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."Magazine_magazine_id_seq" OWNER TO "lms";

-- ----------------------------
--  Sequence structure for book_entity_book_entity_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."book_entity_book_entity_id_seq";
CREATE SEQUENCE "public"."book_entity_book_entity_id_seq" INCREMENT 1 START 22 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."book_entity_book_entity_id_seq" OWNER TO "lms";

-- ----------------------------
--  Sequence structure for book_return_book_return_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."book_return_book_return_id_seq";
CREATE SEQUENCE "public"."book_return_book_return_id_seq" INCREMENT 1 START 26 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."book_return_book_return_id_seq" OWNER TO "lms";

-- ----------------------------
--  Sequence structure for hibernate_sequence
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."hibernate_sequence";
CREATE SEQUENCE "public"."hibernate_sequence" INCREMENT 1 START 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."hibernate_sequence" OWNER TO "lms";

-- ----------------------------
--  Sequence structure for item_entity_item_entity_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."item_entity_item_entity_id_seq";
CREATE SEQUENCE "public"."item_entity_item_entity_id_seq" INCREMENT 1 START 6 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."item_entity_item_entity_id_seq" OWNER TO "lms";

-- ----------------------------
--  Sequence structure for item_item_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."item_item_id_seq";
CREATE SEQUENCE "public"."item_item_id_seq" INCREMENT 1 START 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."item_item_id_seq" OWNER TO "lms";

-- ----------------------------
--  Sequence structure for item_lease_item_lease_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."item_lease_item_lease_id_seq";
CREATE SEQUENCE "public"."item_lease_item_lease_id_seq" INCREMENT 1 START 3 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."item_lease_item_lease_id_seq" OWNER TO "lms";

-- ----------------------------
--  Sequence structure for item_return_item_return_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."item_return_item_return_id_seq";
CREATE SEQUENCE "public"."item_return_item_return_id_seq" INCREMENT 1 START 2 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."item_return_item_return_id_seq" OWNER TO "lms";

-- ----------------------------
--  Sequence structure for magazine_edition_mag_edition_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."magazine_edition_mag_edition_id_seq";
CREATE SEQUENCE "public"."magazine_edition_mag_edition_id_seq" INCREMENT 1 START 40 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."magazine_edition_mag_edition_id_seq" OWNER TO "lms";

-- ----------------------------
--  Sequence structure for magazine_entity_mag_entity_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."magazine_entity_mag_entity_id_seq";
CREATE SEQUENCE "public"."magazine_entity_mag_entity_id_seq" INCREMENT 1 START 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."magazine_entity_mag_entity_id_seq" OWNER TO "lms";

-- ----------------------------
--  Sequence structure for member_member_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."member_member_id_seq";
CREATE SEQUENCE "public"."member_member_id_seq" INCREMENT 1 START 3 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."member_member_id_seq" OWNER TO "lms";

-- ----------------------------
--  Sequence structure for visit_visitid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."visit_visitid_seq";
CREATE SEQUENCE "public"."visit_visitid_seq" INCREMENT 1 START 54 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."visit_visitid_seq" OWNER TO "lms";

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
	"book_entity_id" int4 NOT NULL,
	"visitid" int8 NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."book_lease" OWNER TO "lms";

-- ----------------------------
--  Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS "public"."employee";
CREATE TABLE "public"."employee" (
	"firstname" varchar(2044) NOT NULL COLLATE "default",
	"lastname" varchar(2044) NOT NULL COLLATE "default",
	"role" varchar(2044) NOT NULL COLLATE "default",
	"employee_id" int4 NOT NULL DEFAULT nextval('"Employee_employee_id_seq"'::regclass),
	"address1" varchar(255) COLLATE "default",
	"address2" varchar(255) COLLATE "default",
	"address3" varchar(255) COLLATE "default",
	"city" varchar(255) COLLATE "default",
	"country" varchar(255) COLLATE "default",
	"postalcode" varchar(255) COLLATE "default",
	"username" varchar(255) NOT NULL COLLATE "default",
	"password" varchar(255) NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."employee" OWNER TO "lms";

-- ----------------------------
--  Table structure for magazine
-- ----------------------------
DROP TABLE IF EXISTS "public"."magazine";
CREATE TABLE "public"."magazine" (
	"title" varchar(2044) NOT NULL COLLATE "default",
	"magazine_id" int4 NOT NULL DEFAULT nextval('"Magazine_magazine_id_seq"'::regclass),
	"publisher" varchar(2044) COLLATE "default",
	"frequency" varchar(2044) COLLATE "default",
	"language" varchar(2044) COLLATE "default",
	"isbn" varchar(2044) COLLATE "default",
	"price" float8
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."magazine" OWNER TO "lms";

-- ----------------------------
--  Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS "public"."book";
CREATE TABLE "public"."book" (
	"title" varchar(2044) NOT NULL COLLATE "default",
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
	"summary" text COLLATE "default",
	"price" float8,
	"image" bytea
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."book" OWNER TO "lms";

COMMENT ON TABLE "public"."book" IS 'Describes the book, not to be confused with the book entity, which describes the physical copy of the book stored in our library.
This one just describes a book in general containing all of it information. Physical book may not necessarily even exist.';

-- ----------------------------
--  Table structure for magazine_edition
-- ----------------------------
DROP TABLE IF EXISTS "public"."magazine_edition";
CREATE TABLE "public"."magazine_edition" (
	"magazine_id" int4 NOT NULL,
	"mag_edition_id" int4 NOT NULL DEFAULT nextval('magazine_edition_mag_edition_id_seq'::regclass),
	"edition_date" date NOT NULL,
	"edition_title" varchar COLLATE "default",
	"image" bytea
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."magazine_edition" OWNER TO "lms";

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
	"acquisition_date" date NOT NULL,
	"supplied_by" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."book_entity" OWNER TO "lms";

-- ----------------------------
--  Table structure for magazine_entity
-- ----------------------------
DROP TABLE IF EXISTS "public"."magazine_entity";
CREATE TABLE "public"."magazine_entity" (
	"magazine_edition_id" int4 NOT NULL,
	"mag_entity_id" int4 NOT NULL DEFAULT nextval('magazine_entity_mag_entity_id_seq'::regclass),
	"available" bool NOT NULL DEFAULT true,
	"acquistion_date" date NOT NULL
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
	"dob" date NOT NULL,
	"registration_date" date NOT NULL,
	"username" varchar(2044) NOT NULL COLLATE "default",
	"email" varchar(200) NOT NULL COLLATE "default",
	"phone_number" varchar(20) COLLATE "default",
	"member_id" int4 NOT NULL DEFAULT nextval('member_member_id_seq'::regclass),
	"balance" float8 NOT NULL,
	"address1" varchar(255) COLLATE "default",
	"address2" varchar(255) COLLATE "default",
	"address3" varchar(255) COLLATE "default",
	"city" varchar(255) COLLATE "default",
	"country" varchar(255) COLLATE "default",
	"postalcode" varchar(255) COLLATE "default",
	"currentvisit_visitid" int8,
	"password" varchar(255) NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."member" OWNER TO "lms";

-- ----------------------------
--  Table structure for book_return
-- ----------------------------
DROP TABLE IF EXISTS "public"."book_return";
CREATE TABLE "public"."book_return" (
	"book_entity_id" int4,
	"lease_date" date NOT NULL,
	"due_date" date NOT NULL,
	"member_id" int4,
	"return_date" date NOT NULL,
	"member_comments" varchar COLLATE "default",
	"member_rating" float8,
	"employee_id" int4,
	"book_return_id" int4 NOT NULL DEFAULT nextval('book_return_book_return_id_seq'::regclass),
	"ammount_charged" float8 NOT NULL,
	"lost" bool NOT NULL DEFAULT false,
	"book_book_id" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."book_return" OWNER TO "lms";

-- ----------------------------
--  Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS "public"."item";
CREATE TABLE "public"."item" (
	"name" varchar NOT NULL COLLATE "default",
	"description" varchar COLLATE "default",
	"condition" varchar COLLATE "default",
	"category" varchar COLLATE "default",
	"item_id" int4 NOT NULL DEFAULT nextval('item_item_id_seq'::regclass),
	"price" float8,
	"image" bytea
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."item" OWNER TO "lms";

-- ----------------------------
--  Table structure for item_entity
-- ----------------------------
DROP TABLE IF EXISTS "public"."item_entity";
CREATE TABLE "public"."item_entity" (
	"item_id" int4 NOT NULL,
	"acquisition_date" date NOT NULL,
	"leased" bool NOT NULL DEFAULT false,
	"available" bool NOT NULL DEFAULT true,
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
	"item_entity_id" int4 NOT NULL,
	"lease_date" date NOT NULL,
	"due_date" date NOT NULL,
	"renewed" bool NOT NULL DEFAULT false,
	"member_id" int4 NOT NULL,
	"employee_id" int4 NOT NULL,
	"item_lease_id" int4 NOT NULL DEFAULT nextval('item_lease_item_lease_id_seq'::regclass),
	"visitid" int8 NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."item_lease" OWNER TO "lms";

-- ----------------------------
--  Table structure for item_return
-- ----------------------------
DROP TABLE IF EXISTS "public"."item_return";
CREATE TABLE "public"."item_return" (
	"item_entity_id" int4 NOT NULL,
	"member_id" int4 NOT NULL,
	"employee_id" int4 NOT NULL,
	"lease_date" date NOT NULL,
	"due_date" date NOT NULL,
	"return_date" date NOT NULL,
	"item_return_id" int4 NOT NULL DEFAULT nextval('item_return_item_return_id_seq'::regclass),
	"lost" bool NOT NULL,
	"ammount_charged" float8 NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."item_return" OWNER TO "lms";

-- ----------------------------
--  Table structure for visit
-- ----------------------------
DROP TABLE IF EXISTS "public"."visit";
CREATE TABLE "public"."visit" (
	"visitid" int8 NOT NULL DEFAULT nextval('visit_visitid_seq'::regclass),
	"memberid" int4 NOT NULL,
	"entrytime" timestamp(6) NOT NULL,
	"exittime" timestamp(6) NOT NULL,
	"current" bool NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."visit" OWNER TO "lms";

-- ----------------------------
--  Table structure for book_request
-- ----------------------------
DROP TABLE IF EXISTS "public"."book_request";
CREATE TABLE "public"."book_request" (
	"bookid" int4 NOT NULL,
	"requestid" int4 NOT NULL,
	"supplier" varchar(200) NOT NULL COLLATE "default",
	"uri" varchar(500) COLLATE "default",
	"quantity" int4 NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."book_request" OWNER TO "lms";

-- ----------------------------
--  Table structure for book_author
-- ----------------------------
DROP TABLE IF EXISTS "public"."book_author";
CREATE TABLE "public"."book_author" (
	"book_book_id" int4 NOT NULL,
	"author" varchar(255) NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."book_author" OWNER TO "lms";


-- ----------------------------
--  Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."BookLease_lease_id_seq" RESTART 21 OWNED BY "book_lease"."lease_id";
ALTER SEQUENCE "public"."Book_book_id_seq" RESTART 101450 OWNED BY "book"."book_id";
ALTER SEQUENCE "public"."Employee_employee_id_seq" RESTART 2 OWNED BY "employee"."employee_id";
ALTER SEQUENCE "public"."Magazine_magazine_id_seq" RESTART 29 OWNED BY "magazine"."magazine_id";
ALTER SEQUENCE "public"."book_entity_book_entity_id_seq" RESTART 23 OWNED BY "book_entity"."book_entity_id";
ALTER SEQUENCE "public"."book_return_book_return_id_seq" RESTART 27 OWNED BY "book_return"."book_return_id";
ALTER SEQUENCE "public"."hibernate_sequence" RESTART 2;
ALTER SEQUENCE "public"."item_entity_item_entity_id_seq" RESTART 7 OWNED BY "item_entity"."item_entity_id";
ALTER SEQUENCE "public"."item_item_id_seq" RESTART 2 OWNED BY "item"."item_id";
ALTER SEQUENCE "public"."item_lease_item_lease_id_seq" RESTART 4 OWNED BY "item_lease"."item_lease_id";
ALTER SEQUENCE "public"."item_return_item_return_id_seq" RESTART 3 OWNED BY "item_return"."item_return_id";
ALTER SEQUENCE "public"."magazine_edition_mag_edition_id_seq" RESTART 41 OWNED BY "magazine_edition"."mag_edition_id";
ALTER SEQUENCE "public"."magazine_entity_mag_entity_id_seq" RESTART 2 OWNED BY "magazine_entity"."mag_entity_id";
ALTER SEQUENCE "public"."member_member_id_seq" RESTART 4 OWNED BY "member"."member_id";
ALTER SEQUENCE "public"."visit_visitid_seq" RESTART 55 OWNED BY "visit"."visitid";
-- ----------------------------
--  Primary key structure for table book_lease
-- ----------------------------
ALTER TABLE "public"."book_lease" ADD PRIMARY KEY ("lease_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Uniques structure for table book_lease
-- ----------------------------
ALTER TABLE "public"."book_lease" ADD CONSTRAINT "unique_book_entity_id" UNIQUE ("book_entity_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table book_lease
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16579" AFTER UPDATE ON "public"."book_lease" FROM "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16579" ON "public"."book_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16578" AFTER INSERT ON "public"."book_lease" FROM "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16578" ON "public"."book_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17120" AFTER UPDATE ON "public"."book_lease" FROM "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17120" ON "public"."book_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17119" AFTER INSERT ON "public"."book_lease" FROM "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17119" ON "public"."book_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16618" AFTER UPDATE ON "public"."book_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16618" ON "public"."book_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16617" AFTER INSERT ON "public"."book_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16617" ON "public"."book_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16586" AFTER UPDATE ON "public"."book_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16586" ON "public"."book_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16585" AFTER INSERT ON "public"."book_lease" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16585" ON "public"."book_lease" IS NULL;

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
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17244" AFTER UPDATE ON "public"."employee" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17244" ON "public"."employee" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17243" AFTER DELETE ON "public"."employee" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_setnull_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17243" ON "public"."employee" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16584" AFTER UPDATE ON "public"."employee" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16584" ON "public"."employee" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16583" AFTER DELETE ON "public"."employee" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16583" ON "public"."employee" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17229" AFTER UPDATE ON "public"."employee" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17229" ON "public"."employee" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17228" AFTER DELETE ON "public"."employee" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_setnull_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17228" ON "public"."employee" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16807" AFTER UPDATE ON "public"."employee" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16807" ON "public"."employee" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16806" AFTER DELETE ON "public"."employee" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16806" ON "public"."employee" IS NULL;

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
--  Primary key structure for table book
-- ----------------------------
ALTER TABLE "public"."book" ADD PRIMARY KEY ("book_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table book
-- ----------------------------
CREATE UNIQUE INDEX  "book_book_id_key" ON "public"."book" USING btree(book_id ASC NULLS LAST);

-- ----------------------------
--  Triggers structure for table book
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16554" AFTER UPDATE ON "public"."book" FROM "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16554" ON "public"."book" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16553" AFTER DELETE ON "public"."book" FROM "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16553" ON "public"."book" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17136" AFTER UPDATE ON "public"."book" FROM "public"."book_request" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17136" ON "public"."book" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17135" AFTER DELETE ON "public"."book" FROM "public"."book_request" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17135" ON "public"."book" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17249" AFTER UPDATE ON "public"."book" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17249" ON "public"."book" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17248" AFTER DELETE ON "public"."book" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17248" ON "public"."book" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16868" AFTER UPDATE ON "public"."book" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16868" ON "public"."book" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16867" AFTER DELETE ON "public"."book" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16867" ON "public"."book" IS NULL;

-- ----------------------------
--  Primary key structure for table magazine_edition
-- ----------------------------
ALTER TABLE "public"."magazine_edition" ADD PRIMARY KEY ("mag_edition_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table magazine_edition
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16570" AFTER UPDATE ON "public"."magazine_edition" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16570" ON "public"."magazine_edition" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16569" AFTER DELETE ON "public"."magazine_edition" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16569" ON "public"."magazine_edition" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16535" AFTER UPDATE ON "public"."magazine_edition" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16535" ON "public"."magazine_edition" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16534" AFTER INSERT ON "public"."magazine_edition" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16534" ON "public"."magazine_edition" IS NULL;

-- ----------------------------
--  Primary key structure for table book_entity
-- ----------------------------
ALTER TABLE "public"."book_entity" ADD PRIMARY KEY ("book_entity_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table book_entity
-- ----------------------------
CREATE UNIQUE INDEX  "book_entity_book_entity_id_key" ON "public"."book_entity" USING btree(book_entity_id ASC NULLS LAST);

-- ----------------------------
--  Triggers structure for table book_entity
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16577" AFTER UPDATE ON "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16577" ON "public"."book_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16576" AFTER DELETE ON "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16576" ON "public"."book_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17219" AFTER UPDATE ON "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17219" ON "public"."book_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17218" AFTER DELETE ON "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_setnull_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17218" ON "public"."book_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16556" AFTER UPDATE ON "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16556" ON "public"."book_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16555" AFTER INSERT ON "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16555" ON "public"."book_entity" IS NULL;

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
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17175" AFTER UPDATE ON "public"."member" FROM "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17175" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17174" AFTER INSERT ON "public"."member" FROM "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17174" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17112" AFTER UPDATE ON "public"."member" FROM "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17112" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17111" AFTER DELETE ON "public"."member" FROM "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17111" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17234" AFTER UPDATE ON "public"."member" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17234" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17233" AFTER DELETE ON "public"."member" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_setnull_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17233" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16616" AFTER UPDATE ON "public"."member" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16616" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16615" AFTER DELETE ON "public"."member" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16615" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17224" AFTER UPDATE ON "public"."member" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17224" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17223" AFTER DELETE ON "public"."member" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_setnull_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17223" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16802" AFTER UPDATE ON "public"."member" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16802" ON "public"."member" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16801" AFTER DELETE ON "public"."member" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16801" ON "public"."member" IS NULL;

-- ----------------------------
--  Primary key structure for table book_return
-- ----------------------------
ALTER TABLE "public"."book_return" ADD PRIMARY KEY ("book_return_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table book_return
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17221" AFTER UPDATE ON "public"."book_return" FROM "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17221" ON "public"."book_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17220" AFTER INSERT ON "public"."book_return" FROM "public"."book_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17220" ON "public"."book_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17226" AFTER UPDATE ON "public"."book_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17226" ON "public"."book_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17225" AFTER INSERT ON "public"."book_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17225" ON "public"."book_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17251" AFTER UPDATE ON "public"."book_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17251" ON "public"."book_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17250" AFTER INSERT ON "public"."book_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17250" ON "public"."book_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17231" AFTER UPDATE ON "public"."book_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17231" ON "public"."book_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17230" AFTER INSERT ON "public"."book_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17230" ON "public"."book_return" IS NULL;

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
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17239" AFTER UPDATE ON "public"."item_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17239" ON "public"."item_entity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17238" AFTER DELETE ON "public"."item_entity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_setnull_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17238" ON "public"."item_entity" IS NULL;
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
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17125" AFTER UPDATE ON "public"."item_lease" FROM "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17125" ON "public"."item_lease" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17124" AFTER INSERT ON "public"."item_lease" FROM "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17124" ON "public"."item_lease" IS NULL;
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
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17236" AFTER UPDATE ON "public"."item_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17236" ON "public"."item_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17235" AFTER INSERT ON "public"."item_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17235" ON "public"."item_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17246" AFTER UPDATE ON "public"."item_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17246" ON "public"."item_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17245" AFTER INSERT ON "public"."item_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17245" ON "public"."item_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17241" AFTER UPDATE ON "public"."item_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17241" ON "public"."item_return" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17240" AFTER INSERT ON "public"."item_return" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17240" ON "public"."item_return" IS NULL;

-- ----------------------------
--  Primary key structure for table visit
-- ----------------------------
ALTER TABLE "public"."visit" ADD PRIMARY KEY ("visitid") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table visit
-- ----------------------------
CREATE UNIQUE INDEX  "visit_visitid_key" ON "public"."visit" USING btree(visitid ASC NULLS LAST);

-- ----------------------------
--  Triggers structure for table visit
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17118" AFTER UPDATE ON "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17118" ON "public"."visit" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17117" AFTER DELETE ON "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17117" ON "public"."visit" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17173" AFTER UPDATE ON "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17173" ON "public"."visit" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17172" AFTER DELETE ON "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17172" ON "public"."visit" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17114" AFTER UPDATE ON "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17114" ON "public"."visit" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17113" AFTER INSERT ON "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17113" ON "public"."visit" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17123" AFTER UPDATE ON "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17123" ON "public"."visit" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_17122" AFTER DELETE ON "public"."visit" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_17122" ON "public"."visit" IS NULL;

-- ----------------------------
--  Primary key structure for table book_request
-- ----------------------------
ALTER TABLE "public"."book_request" ADD PRIMARY KEY ("requestid") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table book_request
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17138" AFTER UPDATE ON "public"."book_request" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17138" ON "public"."book_request" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_17137" AFTER INSERT ON "public"."book_request" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_17137" ON "public"."book_request" IS NULL;

-- ----------------------------
--  Triggers structure for table book_author
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16870" AFTER UPDATE ON "public"."book_author" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16870" ON "public"."book_author" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16869" AFTER INSERT ON "public"."book_author" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16869" ON "public"."book_author" IS NULL;

-- ----------------------------
--  Foreign keys structure for table book_lease
-- ----------------------------
ALTER TABLE "public"."book_lease" ADD CONSTRAINT "lnk_book_lease_book_entity2" FOREIGN KEY ("book_entity_id") REFERENCES "public"."book_entity" ("book_entity_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."book_lease" ADD CONSTRAINT "lnk_book_lease_employee2" FOREIGN KEY ("employee_id") REFERENCES "public"."employee" ("employee_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."book_lease" ADD CONSTRAINT "lnk_book_lease_member" FOREIGN KEY ("member_id") REFERENCES "public"."member" ("member_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."book_lease" ADD CONSTRAINT "lnk_visitid" FOREIGN KEY ("visitid") REFERENCES "public"."visit" ("visitid") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table magazine_edition
-- ----------------------------
ALTER TABLE "public"."magazine_edition" ADD CONSTRAINT "lnk_magazine_edition_magazine2" FOREIGN KEY ("magazine_id") REFERENCES "public"."magazine" ("magazine_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table book_entity
-- ----------------------------
ALTER TABLE "public"."book_entity" ADD CONSTRAINT "lnk_book_entity_book2" FOREIGN KEY ("book_id") REFERENCES "public"."book" ("book_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table magazine_entity
-- ----------------------------
ALTER TABLE "public"."magazine_entity" ADD CONSTRAINT "lnk_magazine_entity_magazine_edition2" FOREIGN KEY ("magazine_edition_id") REFERENCES "public"."magazine_edition" ("mag_edition_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table member
-- ----------------------------
ALTER TABLE "public"."member" ADD CONSTRAINT "fk_m2et3q1ijm3r03sqfpto8rfi2" FOREIGN KEY ("currentvisit_visitid") REFERENCES "public"."visit" ("visitid") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table book_return
-- ----------------------------
ALTER TABLE "public"."book_return" ADD CONSTRAINT "lnk_book_entity_id" FOREIGN KEY ("book_entity_id") REFERENCES "public"."book_entity" ("book_entity_id") ON UPDATE CASCADE ON DELETE SET NULL NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."book_return" ADD CONSTRAINT "lnk_member_id" FOREIGN KEY ("member_id") REFERENCES "public"."member" ("member_id") ON UPDATE CASCADE ON DELETE SET NULL NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."book_return" ADD CONSTRAINT "lnk_employee_id" FOREIGN KEY ("employee_id") REFERENCES "public"."employee" ("employee_id") ON UPDATE CASCADE ON DELETE SET NULL NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."book_return" ADD CONSTRAINT "fk_hc6cr048w2pjxj0yoy2dq74si" FOREIGN KEY ("book_book_id") REFERENCES "public"."book" ("book_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

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
ALTER TABLE "public"."item_lease" ADD CONSTRAINT "lnk_visitid" FOREIGN KEY ("visitid") REFERENCES "public"."visit" ("visitid") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table item_return
-- ----------------------------
ALTER TABLE "public"."item_return" ADD CONSTRAINT "lnk_member_id" FOREIGN KEY ("member_id") REFERENCES "public"."member" ("member_id") ON UPDATE CASCADE ON DELETE SET NULL NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."item_return" ADD CONSTRAINT "lnk_item_entity_id" FOREIGN KEY ("item_entity_id") REFERENCES "public"."item_entity" ("item_entity_id") ON UPDATE CASCADE ON DELETE SET NULL NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."item_return" ADD CONSTRAINT "lnk_employee_id" FOREIGN KEY ("employee_id") REFERENCES "public"."employee" ("employee_id") ON UPDATE CASCADE ON DELETE SET NULL NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table visit
-- ----------------------------
ALTER TABLE "public"."visit" ADD CONSTRAINT "lnk_memberid" FOREIGN KEY ("memberid") REFERENCES "public"."member" ("member_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table book_request
-- ----------------------------
ALTER TABLE "public"."book_request" ADD CONSTRAINT "lnk_bookid" FOREIGN KEY ("bookid") REFERENCES "public"."book" ("book_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table book_author
-- ----------------------------
ALTER TABLE "public"."book_author" ADD CONSTRAINT "fk_8rochm2hffd0u0a1gfb6cy1af" FOREIGN KEY ("book_book_id") REFERENCES "public"."book" ("book_id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
