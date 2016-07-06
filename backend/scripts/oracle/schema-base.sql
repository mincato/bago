
CREATE TABLE USERS
(
  ID number(38) NOT NULL,
  USERNAME character varying(255) NOT NULL,
  FIRST_NAME character varying(255) NOT NULL,
  MIDDLE_NAME character varying(255) NULL,
  LAST_NAME character varying(255) NOT NULL,
  EMAIL character varying(255) NOT NULL,
  PASSWORD character varying(255) NOT NULL,
  CONSTRAINT USERS_PKEY PRIMARY KEY (ID)
);

CREATE SEQUENCE USERS_SEQ;

CREATE OR REPLACE TRIGGER USERS_TRIGGER
BEFORE INSERT ON USERS
FOR EACH ROW
BEGIN
  SELECT USERS_SEQ.NEXTVAL
  INTO :new.id
  FROM dual;
END;
/

CREATE TABLE DEVELOPERS
(
  ID number(38) NOT NULL,
  NAME character varying(255) NOT NULL,
  LAST_NAME character varying(255) NOT NULL,
  SENIORITY INTEGER,
  DATE_ENTRY DATE,
  CONSTRAINT DEV_PKEY PRIMARY KEY (ID)
);

CREATE SEQUENCE DEVELOPERS_SEQ;

CREATE OR REPLACE TRIGGER DEVELOPERS_TRIGGER
BEFORE INSERT ON DEVELOPERS
FOR EACH ROW
BEGIN
  SELECT DEVELOPERS_SEQ.NEXTVAL
  INTO :new.id
  FROM dual;
END;
/

CREATE TABLE ROLES
(
  ID number(38) NOT NULL,
  NAME character varying(255) NOT NULL,
  CONSTRAINT ROLES_PKEY PRIMARY KEY (ID)
);

CREATE SEQUENCE ROLES_SEQ;

CREATE OR REPLACE TRIGGER ROLES_TRIGGER
BEFORE INSERT ON ROLES
FOR EACH ROW
BEGIN
  SELECT ROLES_SEQ.NEXTVAL
  INTO :new.id
  FROM dual;
END;
/

CREATE TABLE PERMISSIONS
(
  ID number(38) NOT NULL,
  NAME character varying(255) NOT NULL,
  CONSTRAINT PERM_PKEY PRIMARY KEY (ID)
);

CREATE SEQUENCE PERMISSIONS_SEQ;

CREATE OR REPLACE TRIGGER PERMISSIONS_TRIGGER
BEFORE INSERT ON PERMISSIONS
FOR EACH ROW
BEGIN
  SELECT PERMISSIONS_SEQ.NEXTVAL
  INTO :new.id
  FROM dual;
END;
/

CREATE TABLE USER_ROLES
(
  USER_ID number(38) NOT NULL,
  ROLE_ID number(38) NOT NULL,
  CONSTRAINT UR_PKEY PRIMARY KEY (USER_ID, ROLE_ID),
  CONSTRAINT UR_ROLE_FKEY FOREIGN KEY (ROLE_ID)
      REFERENCES ROLES (ID),
  CONSTRAINT UR_USER_FKEY FOREIGN KEY (USER_ID)
      REFERENCES USERS (ID)
);
/

CREATE TABLE ROLES_PERMISSIONS
(
  ROLE_ID number(38) NOT NULL,
  PERMISSION_ID number(38) NOT NULL,
  CONSTRAINT RP_PKEY PRIMARY KEY (ROLE_ID, PERMISSION_ID),
  CONSTRAINT RP_PERM_FKEY FOREIGN KEY (PERMISSION_ID)
      REFERENCES PERMISSIONS (ID),
  CONSTRAINT RP_ROLE_ID_FKEY FOREIGN KEY (ROLE_ID)
      REFERENCES ROLES (ID)
);