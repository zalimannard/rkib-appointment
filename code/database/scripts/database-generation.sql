CREATE TABLE access_rights (
    access_right_id   NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    access_right_name VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE access_rights ADD CONSTRAINT access_rights_pk PRIMARY KEY ( access_right_id );

CREATE TABLE appointment_status_types (
    appointment_status_type_id   NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    appointment_status_type_name VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE appointment_status_types ADD CONSTRAINT appointment_status_types_pk PRIMARY KEY ( appointment_status_type_id );

CREATE TABLE appointment_statuses (
    appointment_status_id      NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    appointment_status_type_id NUMBER NOT NULL,
    appointment_status_name    VARCHAR2(50 CHAR) NOT NULL
);

ALTER TABLE appointment_statuses ADD CONSTRAINT appointment_statuses_pk PRIMARY KEY ( appointment_status_id );

CREATE TABLE appointments (
    appointment_id        NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    parent_appointment_id NUMBER NOT NULL,
    patient_id            NUMBER NOT NULL,
    appointment_status_id NUMBER NOT NULL,
    doctor_note_id        NUMBER NOT NULL,
    final_diagnosis       VARCHAR2(200 CHAR) NOT NULL
);

CREATE UNIQUE INDEX appointments__idx ON
    appointments (
        parent_appointment_id
    ASC );

ALTER TABLE appointments ADD CONSTRAINT appointments_pk PRIMARY KEY ( appointment_id );

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

CREATE TABLE employees_access_rights (
    employees_access_right_id NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    access_right_id           NUMBER NOT NULL,
    employee_id               NUMBER NOT NULL
);

ALTER TABLE employees_access_rights ADD CONSTRAINT employees_access_rights_pk PRIMARY KEY ( employees_access_right_id );

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

CREATE TABLE schedule_item_status_types (
    si_status_type_id   NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    si_status_type_name VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE schedule_item_status_types ADD CONSTRAINT schedule_item_status_types_pk PRIMARY KEY ( si_status_type_id );

CREATE TABLE schedule_item_statuses (
    si_status_id                  NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    si_status_name                VARCHAR2(30 CHAR) NOT NULL,
    schedule_item_status_types_id NUMBER NOT NULL
);

ALTER TABLE schedule_item_statuses ADD CONSTRAINT schedule_item_statuses_pk PRIMARY KEY ( si_status_id );

CREATE TABLE schedule_items (
    schedule_item_id        NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    employee_id             NUMBER NOT NULL,
    service_id              NUMBER NOT NULL,
    schedule_item_status_id NUMBER NOT NULL,
    time_stamp              TIMESTAMP NOT NULL
);

ALTER TABLE schedule_items ADD CONSTRAINT schedule_items_pk PRIMARY KEY ( schedule_item_id );

CREATE TABLE scheduled_appointments (
    scheduled_appointment_id NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    schedule_item_id         NUMBER NOT NULL,
    appointment_id           NUMBER NOT NULL
);

ALTER TABLE scheduled_appointments ADD CONSTRAINT scheduled_appointments_pk PRIMARY KEY ( scheduled_appointment_id );

CREATE TABLE services (
    service_id   NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    service_name VARCHAR2(100 CHAR) NOT NULL
);

ALTER TABLE services ADD CONSTRAINT services_pk PRIMARY KEY ( service_id );

CREATE TABLE unscheduled_appointments (
    unscheduled_appointment_id NUMBER GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    employee_id                NUMBER NOT NULL,
    service_id                 NUMBER NOT NULL,
    appointment_id             NUMBER NOT NULL,
    time_stamp                 TIMESTAMP NOT NULL,
    commentary                 VARCHAR2(200 CHAR) NOT NULL
);

ALTER TABLE unscheduled_appointments ADD CONSTRAINT unscheduled_appointments_pk PRIMARY KEY ( unscheduled_appointment_id );

ALTER TABLE appointment_statuses
    ADD CONSTRAINT as_appointment_status_types_fk FOREIGN KEY ( appointment_status_type_id )
        REFERENCES appointment_status_types ( appointment_status_type_id );

ALTER TABLE appointments
    ADD CONSTRAINT a_appointment_statuses_fk FOREIGN KEY ( appointment_status_id )
        REFERENCES appointment_statuses ( appointment_status_id );

ALTER TABLE appointments
    ADD CONSTRAINT a_appointments_fk FOREIGN KEY ( parent_appointment_id )
        REFERENCES appointments ( appointment_id );

ALTER TABLE appointments
    ADD CONSTRAINT a_doctor_notes_fk FOREIGN KEY ( doctor_note_id )
        REFERENCES doctor_notes ( doctor_note_id );

ALTER TABLE appointments
    ADD CONSTRAINT a_patients_fk FOREIGN KEY ( patient_id )
        REFERENCES patients ( patient_id );

ALTER TABLE doctor_notes
    ADD CONSTRAINT dn_institutions_fk FOREIGN KEY ( institution_id )
        REFERENCES institutions ( institution_id );

ALTER TABLE employees_access_rights
    ADD CONSTRAINT ear_access_rights_fk FOREIGN KEY ( access_right_id )
        REFERENCES access_rights ( access_right_id );

ALTER TABLE employees_access_rights
    ADD CONSTRAINT ear_employees_fk FOREIGN KEY ( employee_id )
        REFERENCES employees ( employee_id );

ALTER TABLE schedule_item_statuses
    ADD CONSTRAINT sis_schedule_item_status_types_fk FOREIGN KEY ( schedule_item_status_types_id )
        REFERENCES schedule_item_status_types ( si_status_type_id );

ALTER TABLE schedule_items
    ADD CONSTRAINT si_employees_fk FOREIGN KEY ( employee_id )
        REFERENCES employees ( employee_id );

ALTER TABLE schedule_items
    ADD CONSTRAINT si_schedule_item_statuses_fk FOREIGN KEY ( schedule_item_status_id )
        REFERENCES schedule_item_statuses ( si_status_id );

ALTER TABLE schedule_items
    ADD CONSTRAINT si_services_fk FOREIGN KEY ( service_id )
        REFERENCES services ( service_id );

ALTER TABLE scheduled_appointments
    ADD CONSTRAINT sa_appointments_fk FOREIGN KEY ( appointment_id )
        REFERENCES appointments ( appointment_id );

ALTER TABLE scheduled_appointments
    ADD CONSTRAINT sa_schedule_items_fk FOREIGN KEY ( schedule_item_id )
        REFERENCES schedule_items ( schedule_item_id );

ALTER TABLE unscheduled_appointments
    ADD CONSTRAINT ua_appointments_fk FOREIGN KEY ( appointment_id )
        REFERENCES appointments ( appointment_id );

ALTER TABLE unscheduled_appointments
    ADD CONSTRAINT ua_employees_fk FOREIGN KEY ( employee_id )
        REFERENCES employees ( employee_id );

ALTER TABLE unscheduled_appointments
    ADD CONSTRAINT ua_services_fk FOREIGN KEY ( service_id )
        REFERENCES services ( service_id );
