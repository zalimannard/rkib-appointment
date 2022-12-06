-- Generated by Oracle SQL Developer Data Modeler 22.2.0.165.1149
--   at:        2022-12-07 00:39:30 MSK
--   site:      Oracle Database 21c
--   type:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE application_status_types (
    id   NUMBER
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 NOCACHE ORDER )
    NOT NULL,
    name VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE application_status_types ADD CONSTRAINT application_status_types_PK PRIMARY KEY ( id );

CREATE TABLE application_statuses (
    id      NUMBER
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 NOCACHE ORDER )
    NOT NULL,
    type_id NUMBER NOT NULL,
    name    VARCHAR2(100 CHAR) NOT NULL
);

ALTER TABLE application_statuses ADD CONSTRAINT application_statuses_PK PRIMARY KEY ( id );

CREATE TABLE applications (
    id                    NUMBER
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 NOCACHE ORDER )
    NOT NULL,
    parent_appointment_id NUMBER NOT NULL,
    patient_id            NUMBER NOT NULL,
    doctor_note_id        NUMBER NOT NULL,
    appointment_status_id NUMBER NOT NULL,
    final_diagnosis       VARCHAR2(300 CHAR) NOT NULL
);

CREATE UNIQUE INDEX applications__IDX ON
    applications (
        parent_appointment_id
    ASC );

ALTER TABLE applications ADD CONSTRAINT applications_PK PRIMARY KEY ( id );

CREATE TABLE doctor_notes (
    id             NUMBER
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 NOCACHE ORDER )
    NOT NULL,
    institution_id NUMBER NOT NULL,
    diagnosis      VARCHAR2(100 CHAR) NOT NULL
);

ALTER TABLE doctor_notes ADD CONSTRAINT doctor_notes_PK PRIMARY KEY ( id );

CREATE TABLE doctors (
    id          NUMBER
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 NOCACHE ORDER )
    NOT NULL,
    last_name   VARCHAR2(30 CHAR) NOT NULL,
    first_name  VARCHAR2(30 CHAR) NOT NULL,
    middle_name VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE doctors ADD CONSTRAINT doctors_PK PRIMARY KEY ( id );

CREATE TABLE institutions (
    id   NUMBER
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 NOCACHE ORDER )
    NOT NULL,
    name VARCHAR2(200 CHAR) NOT NULL
);

ALTER TABLE institutions ADD CONSTRAINT institutions_PK PRIMARY KEY ( id );

CREATE TABLE patients (
    id           NUMBER
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 NOCACHE ORDER )
    NOT NULL,
    last_name    VARCHAR2(30 CHAR) NOT NULL,
    first_name   VARCHAR2(30 CHAR) NOT NULL,
    middle_name  VARCHAR2(30 CHAR) NOT NULL,
    phone_number VARCHAR2(20 CHAR) NOT NULL,
    birthdate    DATE NOT NULL,
    address      VARCHAR2(200 CHAR) NOT NULL,
    occupation   VARCHAR2(100 CHAR) NOT NULL
);

ALTER TABLE patients ADD CONSTRAINT patients_PK PRIMARY KEY ( id );

CREATE TABLE schedule_element_status_types (
    id   NUMBER
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 NOCACHE ORDER )
    NOT NULL,
    name VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE schedule_element_status_types ADD CONSTRAINT schedule_element_status_types_PK PRIMARY KEY ( id );

CREATE TABLE schedule_element_statuses (
    id      NUMBER
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 NOCACHE ORDER )
    NOT NULL,
    type_id NUMBER NOT NULL,
    name    VARCHAR2(100 CHAR) NOT NULL
);

ALTER TABLE schedule_element_statuses ADD CONSTRAINT schedule_element_statuses_PK PRIMARY KEY ( id );

CREATE TABLE schedule_elements (
    id                    NUMBER
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 NOCACHE ORDER )
    NOT NULL,
    status_id             NUMBER NOT NULL,
    doctor_id             NUMBER NOT NULL,
    service_id            NUMBER NOT NULL,
    appointment_timestamp TIMESTAMP NOT NULL
);

ALTER TABLE schedule_elements ADD CONSTRAINT schedule_elements_PK PRIMARY KEY ( id );

CREATE TABLE scheduled_visits (
    id                  NUMBER
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 NOCACHE ORDER )
    NOT NULL,
    schedule_element_id NUMBER NOT NULL,
    application_id      NUMBER NOT NULL
);

ALTER TABLE scheduled_visits ADD CONSTRAINT scheduled_visits_PK PRIMARY KEY ( id );

CREATE TABLE services (
    id   NUMBER
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 NOCACHE ORDER )
    NOT NULL,
    name VARCHAR2(100 CHAR) NOT NULL
);

ALTER TABLE services ADD CONSTRAINT services_PK PRIMARY KEY ( id );

CREATE TABLE unscheduled_visits (
    id                    NUMBER
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 NOCACHE ORDER )
    NOT NULL,
    doctor_id             NUMBER NOT NULL,
    service_id            NUMBER NOT NULL,
    application_id        NUMBER NOT NULL,
    appointment_timestamp TIMESTAMP NOT NULL,
    "comment"             VARCHAR2(300 CHAR) NOT NULL
);

ALTER TABLE unscheduled_visits ADD CONSTRAINT unscheduled_visits_PK PRIMARY KEY ( id );

ALTER TABLE application_statuses
    ADD CONSTRAINT application_statuses_application_status_types_FK FOREIGN KEY ( type_id )
        REFERENCES application_status_types ( id );

ALTER TABLE applications
    ADD CONSTRAINT applications_application_statuses_FK FOREIGN KEY ( appointment_status_id )
        REFERENCES application_statuses ( id );

ALTER TABLE applications
    ADD CONSTRAINT applications_applications_FK FOREIGN KEY ( parent_appointment_id )
        REFERENCES applications ( id );

ALTER TABLE applications
    ADD CONSTRAINT applications_doctor_notes_FK FOREIGN KEY ( doctor_note_id )
        REFERENCES doctor_notes ( id );

ALTER TABLE applications
    ADD CONSTRAINT applications_patients_FK FOREIGN KEY ( patient_id )
        REFERENCES patients ( id );

ALTER TABLE doctor_notes
    ADD CONSTRAINT doctor_notes_institutions_FK FOREIGN KEY ( institution_id )
        REFERENCES institutions ( id );

ALTER TABLE schedule_element_statuses
    ADD CONSTRAINT schedule_element_statuses_schedule_element_status_types_FK FOREIGN KEY ( type_id )
        REFERENCES schedule_element_status_types ( id );

ALTER TABLE schedule_elements
    ADD CONSTRAINT schedule_elements_doctors_FK FOREIGN KEY ( doctor_id )
        REFERENCES doctors ( id );

ALTER TABLE schedule_elements
    ADD CONSTRAINT schedule_elements_schedule_element_statuses_FK FOREIGN KEY ( status_id )
        REFERENCES schedule_element_statuses ( id );

ALTER TABLE schedule_elements
    ADD CONSTRAINT schedule_elements_services_FK FOREIGN KEY ( service_id )
        REFERENCES services ( id );

ALTER TABLE scheduled_visits
    ADD CONSTRAINT scheduled_visits_applications_FK FOREIGN KEY ( application_id )
        REFERENCES applications ( id );

ALTER TABLE scheduled_visits
    ADD CONSTRAINT scheduled_visits_schedule_elements_FK FOREIGN KEY ( schedule_element_id )
        REFERENCES schedule_elements ( id );

ALTER TABLE unscheduled_visits
    ADD CONSTRAINT unscheduled_visits_applications_FK FOREIGN KEY ( application_id )
        REFERENCES applications ( id );

ALTER TABLE unscheduled_visits
    ADD CONSTRAINT unscheduled_visits_doctors_FK FOREIGN KEY ( doctor_id )
        REFERENCES doctors ( id );

ALTER TABLE unscheduled_visits
    ADD CONSTRAINT unscheduled_visits_services_FK FOREIGN KEY ( service_id )
        REFERENCES services ( id );



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                            13
-- CREATE INDEX                             1
-- ALTER TABLE                             28
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
