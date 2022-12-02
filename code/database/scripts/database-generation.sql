CREATE TABLE doctor_notes (
    doctor_note_id        NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    institution_id        NUMBER NOT NULL,
    preliminary_diagnosis VARCHAR2(200 CHAR) NOT NULL
);

ALTER TABLE doctor_notes ADD CONSTRAINT doctor_notes_pk PRIMARY KEY ( doctor_note_id );

CREATE TABLE employees (
    employee_id   NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    last_name     VARCHAR2(30 CHAR) NOT NULL,
    first_name    VARCHAR2(30 CHAR) NOT NULL,
    middle_name   VARCHAR2(30 CHAR) NOT NULL,
    login         VARCHAR2(30 CHAR) NOT NULL,
    password_hash VARCHAR2(32 CHAR) NOT NULL,
    password_salt VARCHAR2(32 CHAR) NOT NULL
);

ALTER TABLE employees ADD CONSTRAINT employees_pk PRIMARY KEY ( employee_id );

CREATE TABLE employees_roles (
    employees_role_id NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    role_id           NUMBER NOT NULL,
    employee_id       NUMBER NOT NULL
);

ALTER TABLE employees_roles ADD CONSTRAINT employees_roles_pk PRIMARY KEY ( employees_role_id );

CREATE TABLE institutions (
    institution_id   NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    institution_name VARCHAR2(200 CHAR) NOT NULL
);

ALTER TABLE institutions ADD CONSTRAINT institutions_pk PRIMARY KEY ( institution_id );

CREATE TABLE patients (
    patient_id   NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    last_name    VARCHAR2(30 CHAR) NOT NULL,
    first_name   VARCHAR2(30 CHAR) NOT NULL,
    middle_name  VARCHAR2(30 CHAR) NOT NULL,
    phone_number VARCHAR2(20 CHAR) NOT NULL,
    birth_date   DATE NOT NULL,
    address      VARCHAR2(200 CHAR) NOT NULL,
    job          VARCHAR2(100 CHAR) NOT NULL
);

ALTER TABLE patients ADD CONSTRAINT patients_pk PRIMARY KEY ( patient_id );

CREATE TABLE plan_el (
    plan_el_id        NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    employee_id       NUMBER NOT NULL,
    service_id        NUMBER NOT NULL,
    plan_el_status_id NUMBER NOT NULL,
    time_stamp        TIMESTAMP NOT NULL
);

ALTER TABLE plan_el ADD CONSTRAINT plan_el_pk PRIMARY KEY ( plan_el_id );

CREATE TABLE plan_el_sttss (
    plan_el_status_id NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    plan_type_id      NUMBER NOT NULL,
    plan_status_name  VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE plan_el_sttss ADD CONSTRAINT plan_el_sttss_pk PRIMARY KEY ( plan_el_status_id );

CREATE TABLE plan_el_types (
    plan_el_type_id     NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    si_status_type_name VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE plan_el_types ADD CONSTRAINT plan_el_types_pk PRIMARY KEY ( plan_el_type_id );

CREATE TABLE roles (
    role_id   NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    role_name VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE roles ADD CONSTRAINT roles_pk PRIMARY KEY ( role_id );

CREATE TABLE scheduled (
    scheduled_id NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    plan_el_id   NUMBER NOT NULL,
    visit_id     NUMBER NOT NULL
);

ALTER TABLE scheduled ADD CONSTRAINT scheduled_pk PRIMARY KEY ( scheduled_id );

CREATE TABLE services (
    service_id   NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    service_name VARCHAR2(100 CHAR) NOT NULL
);

ALTER TABLE services ADD CONSTRAINT services_pk PRIMARY KEY ( service_id );

CREATE TABLE unscheduled (
    unscheduled_id NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    employee_id    NUMBER NOT NULL,
    service_id     NUMBER NOT NULL,
    visit_id       NUMBER NOT NULL,
    time_stamp     TIMESTAMP NOT NULL,
    commentary     VARCHAR2(200 CHAR) NOT NULL
);

ALTER TABLE unscheduled ADD CONSTRAINT unscheduled_pk PRIMARY KEY ( unscheduled_id );

CREATE TABLE visit_statuses (
    visit_status_id   NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    visit_type_id     NUMBER NOT NULL,
    visit_status_name VARCHAR2(50 CHAR) NOT NULL
);

ALTER TABLE visit_statuses ADD CONSTRAINT visit_statuses_pk PRIMARY KEY ( visit_status_id );

CREATE TABLE visit_types (
    visit_type_id   NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    visit_type_name VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE visit_types ADD CONSTRAINT visit_types_pk PRIMARY KEY ( visit_type_id );

CREATE TABLE visits (
    visit_id        NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    parent_visit_id NUMBER NOT NULL,
    patient_id      NUMBER NOT NULL,
    visit_status_id NUMBER NOT NULL,
    doctor_note_id  NUMBER NOT NULL,
    final_diagnosis VARCHAR2(200 CHAR) NOT NULL
);

CREATE UNIQUE INDEX visits__idx ON
    visits (
        parent_visit_id
    ASC );

ALTER TABLE visits ADD CONSTRAINT visits_pk PRIMARY KEY ( visit_id );

ALTER TABLE doctor_notes
    ADD CONSTRAINT doctor_notes_institutions_fk FOREIGN KEY ( institution_id )
        REFERENCES institutions ( institution_id );

ALTER TABLE employees_roles
    ADD CONSTRAINT employees_roles_employees_fk FOREIGN KEY ( employee_id )
        REFERENCES employees ( employee_id );

ALTER TABLE employees_roles
    ADD CONSTRAINT employees_roles_roles_fk FOREIGN KEY ( role_id )
        REFERENCES roles ( role_id );

ALTER TABLE plan_el
    ADD CONSTRAINT plan_el_employees_fk FOREIGN KEY ( employee_id )
        REFERENCES employees ( employee_id );

ALTER TABLE plan_el
    ADD CONSTRAINT plan_el_plan_el_sttss_fk FOREIGN KEY ( plan_el_status_id )
        REFERENCES plan_el_sttss ( plan_el_status_id );

ALTER TABLE plan_el
    ADD CONSTRAINT plan_el_services_fk FOREIGN KEY ( service_id )
        REFERENCES services ( service_id );

ALTER TABLE plan_el_sttss
    ADD CONSTRAINT plan_el_sttss_plan_el_types_fk FOREIGN KEY ( plan_type_id )
        REFERENCES plan_el_types ( plan_el_type_id );

ALTER TABLE scheduled
    ADD CONSTRAINT scheduled_plan_el_fk FOREIGN KEY ( plan_el_id )
        REFERENCES plan_el ( plan_el_id );

ALTER TABLE scheduled
    ADD CONSTRAINT scheduled_visits_fk FOREIGN KEY ( visit_id )
        REFERENCES visits ( visit_id );

ALTER TABLE unscheduled
    ADD CONSTRAINT unscheduled_employees_fk FOREIGN KEY ( employee_id )
        REFERENCES employees ( employee_id );

ALTER TABLE unscheduled
    ADD CONSTRAINT unscheduled_services_fk FOREIGN KEY ( service_id )
        REFERENCES services ( service_id );

ALTER TABLE unscheduled
    ADD CONSTRAINT unscheduled_visits_fk FOREIGN KEY ( visit_id )
        REFERENCES visits ( visit_id );

ALTER TABLE visit_statuses
    ADD CONSTRAINT visit_statuses_visit_types_fk FOREIGN KEY ( visit_type_id )
        REFERENCES visit_types ( visit_type_id );

ALTER TABLE visits
    ADD CONSTRAINT visits_doctor_notes_fk FOREIGN KEY ( doctor_note_id )
        REFERENCES doctor_notes ( doctor_note_id );

ALTER TABLE visits
    ADD CONSTRAINT visits_patients_fk FOREIGN KEY ( patient_id )
        REFERENCES patients ( patient_id );

ALTER TABLE visits
    ADD CONSTRAINT visits_visit_statuses_fk FOREIGN KEY ( visit_status_id )
        REFERENCES visit_statuses ( visit_status_id );

ALTER TABLE visits
    ADD CONSTRAINT visits_visits_fk FOREIGN KEY ( parent_visit_id )
        REFERENCES visits ( visit_id );
