CREATE TABLE ns_status (
    status_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    status_name VARCHAR2(255) NOT NULL
);

CREATE TABLE ns_role (
    role_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_name VARCHAR2(255) NOT NULL
);

CREATE TABLE ns_sp_category (
    sp_category_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    categories VARCHAR2(255) NOT NULL
);

CREATE TABLE ns_user_info (
    user_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    full_name VARCHAR2(255) NOT NULL,
    email VARCHAR2(255) NOT NULL UNIQUE,
    phone_number VARCHAR2(20) NOT NULL
);

CREATE TABLE ns_login (
    user_id UNIQUE NOT NULL,
    user_name VARCHAR2(255) NOT NULL,
    password VARCHAR2(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES ns_user_info (user_id)
);


CREATE TABLE ns_user_login_role (
    user_id NUMBER NOT NULL,
    role_id NUMBER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES ns_login (user_id),
    FOREIGN KEY (role_id) REFERENCES ns_role (role_id)
);

CREATE TABLE ns_address (
    address_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    address_flat_number VARCHAR2(255) NOT NULL,
    address_house_number VARCHAR2(255) NOT NULL,
    address_street_name VARCHAR2(255) NOT NULL,
    address_locality VARCHAR2(255) NOT NULL,
    address_district VARCHAR2(255) NOT NULL,
    address_pincode NUMBER NOT NULL,
    address_country VARCHAR2(255) NOT NULL
);

CREATE TABLE ns_member_data (
    member_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    full_name VARCHAR2(255) NOT NULL,
    age NUMBER CHECK (member_age > 0) NOT NULL,
    gender VARCHAR2(10) NOT NULL,
	identification_url VARCHAR2(2048) NOT NULL,
    admin_id NUMBER NOT NULL,
    status_id NUMBER NOT NULL,
    FOREIGN KEY (status_id) REFERENCES ns_status (status_id),
    FOREIGN KEY (admin_id) REFERENCES ns_user_info (user_id)
);

CREATE TABLE ns_nurse_license (
    nurse_license_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    sp_id NUMBER NOT NULL,
    nurse_license_url VARCHAR2(2000) NOT NULL,
    FOREIGN KEY (sp_id) REFERENCES ns_user_info (user_id)
);

CREATE TABLE ns_qualification (
    sp_qualification_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    sp_id NUMBER NOT NULL,
    experience_in_years NUMBER NOT NULL,
    role_name VARCHAR2(255) NOT NULL,
    FOREIGN KEY (sp_id) REFERENCES ns_user_info (user_id)
);

CREATE TABLE ns_service_provider (
	sp_id NUMBER PRIMARY KEY,
	gender varchar2(10) NOT NULL,
	category NUMBER NOT NULL,
	weekly_salary NUMBER NOT NULL,
	available_from DATE NOT NULL,
	available_to DATE NOT NULL,
	address_id NUMBER NOT NULL,
	qualification_id NUMBER NOT NULL,
	live_in NUMBER(1) NOT NULL,
	admin_id NUMBER NOT NULL,
	status_id NUMBER NOT NULL,
	id_proof_url varchar2(2000) NOT NULL,
	nurse_lisence_id NUMBER,
	FOREIGN KEY (sp_id) REFERENCES ns_user_info (user_id),
	FOREIGN KEY (category) REFERENCES ns_sp_category (sp_category_id),
	FOREIGN KEY (address_id) REFERENCES ns_address (address_id),
	FOREIGN KEY (admin_id) REFERENCES ns_user_info(user_id),
	FOREIGN KEY (status_id) REFERENCES ns_status (status_id),
	FOREIGN KEY (nurse_lisence_id) REFERENCES ns_nurse_license (nurse_license_id)
);

CREATE TABLE ns_request (
    request_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    client_id NUMBER NOT NULL,
    sp_id NUMBER NOT NULL,
    address_id NUMBER NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    member_id NUMBER NOT NULL,  
    message_to_sp VARCHAR2(2048),
    message_from_sp VARCHAR2(2048),
    status_id NUMBER NOT NULL,
	FOREIGN KEY (client_id) REFERENCES ns_user_info (user_id),
    FOREIGN KEY (sp_id) REFERENCES ns_user_info (user_id),
    FOREIGN KEY (address_id) REFERENCES ns_address (address_id),
    FOREIGN KEY (member_id) REFERENCES ns_member_data (member_id),
    FOREIGN KEY (status_id) REFERENCES ns_status (status_id)
);

INSERT INTO ns_status (status_name) VALUES ('active'),
('inactive'),
('pending'),
('accepted'),
('rejected'),
('deleted');

INSERT INTO ns_role (role_name) VALUES ('admin'),
('service provider'),
('client');

INSERT INTO ns_sp_category (categories) VALUES ('Nurse'),
('Caregiver'),
('Dual-Role Nurse and Caregiver');

INSERT INTO ns_user_info (full_name, email, phone_number) VALUES 
('John Doe', 'john.doe@example.com', '9876543210'),
('Jane Smith', 'jane.smith@example.com', '8765432109'),
('Robert Brown', 'robert.brown@example.com', '7654321098'),
('Emily Davis', 'emily.davis@example.com', '6543210987'),
('Michael Wilson', 'michael.wilson@example.com', '5432109876'),
('Sarah Johnson', 'sarah.johnson@example.com', '4321098765'),
('David Moore', 'david.moore@example.com', '3210987654'),
('Laura Taylor', 'laura.taylor@example.com', '2109876543'),
('James Anderson', 'james.anderson@example.com', '1098765432'),
('Patricia Thomas', 'patricia.thomas@example.com', '0987654321');

INSERT INTO ns_login (user_id, user_name, password)
VALUES 
(1, 'johndoe', 'password1'),
(2, 'janesmith', 'password2'),
(3, 'robertbrown', 'password3'),
(4, 'emilydavis', 'password4'),
(5, 'michaelwilson', 'password5'),
(6, 'sarahjohnson', 'password6'),
(7, 'davidmoore', 'password7'),
(8, 'laurataylor', 'password8'),
(9, 'jamesanderson', 'password9'),
(10, 'patriciathomas', 'password10');

INSERT INTO ns_user_login_role (user_id, role_id)
VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 2),
(5, 3),
(6, 2),
(7, 3),
(8, 2),
(9, 3),
(10, 1);

INSERT INTO ns_address (address_flat_number, address_house_number, address_street_name, address_locality, address_district, address_pincode, address_country)
VALUES 
('A-101', '12', 'Maple Street', 'Downtown', 'Metropolis', 100001, 'USA'),
('B-202', '34', 'Oak Avenue', 'Suburbia', 'Gotham', 100002, 'USA'),
('C-303', '56', 'Pine Road', 'Uptown', 'Star City', 100003, 'USA'),
('D-404', '78', 'Birch Lane', 'Old Town', 'Coast City', 100004, 'USA'),
('E-505', '90', 'Spruce Street', 'West End', 'Central City', 100005, 'USA'),
('F-606', '11', 'Cedar Drive', 'East Side', 'Blüdhaven', 100006, 'USA'),
('G-707', '13', 'Elm Street', 'North Side', 'Keystone City', 100007, 'USA'),
('H-808', '15', 'Willow Way', 'South End', 'Fawcett City', 100008, 'USA'),
('I-909', '17', 'Fir Boulevard', 'New Town', 'Gateway City', 100009, 'USA'),
('J-1010', '19', 'Ash Alley', 'Central Park', 'National City', 100010, 'USA');

INSERT INTO ns_member_data (full_name, age, gender, identification_url, admin_id, status_id)
VALUES 
('Alice Brown', 25, 'Female', 'https://example.com/id1', 1, 1),
('Bob Smith', 30, 'Male', 'https://example.com/id2', 2, 3),
('Carol Johnson', 65, 'Female', 'https://example.com/id3', 3, 1),
('David Lee', 45, 'Male', 'https://example.com/id4', 4, 2),
('Eve Miller', 75, 'Female', 'https://example.com/id5', 5, 4),
('Frank Taylor', 55, 'Male', 'https://example.com/id6', 6, 2),
('Grace Wilson', 35, 'Female', 'https://example.com/id7', 7, 3),
('Hank Davis', 40, 'Male', 'https://example.com/id8', 8, 1),
('Ivy White', 50, 'Female', 'https://example.com/id8', 9, 1),
('Jack Black', 60, 'Male', 'https://example.com/id10', 10, 5);

INSERT INTO ns_nurse_license (sp_id, nurse_license_url)
VALUES 
(2, 'https://example.com/license1'),
(4, 'https://example.com/license2'),
(6, 'https://example.com/license3'),
(8, 'https://example.com/license4'),
(10, 'https://example.com/license5');

INSERT INTO ns_qualification (sp_id, experience_in_years, role_name)
VALUES 
(2, 5, 'Nurse'),
(4, 10, 'Caregiver'),
(6, 8, 'Nurse cum Caregiver'),
(8, 3, 'Nurse'),
(10, 15, 'Caregiver');

INSERT INTO ns_service_provider (sp_id, gender, category, weekly_salary, available_from, available_to, address_id, qualification_id, live_in, admin_id, status_id, id_proof_url, nurse_lisence_id)
VALUES 
(2, 'Female', 1, 1500, TO_DATE('2023-09-01', 'YYYY-MM-DD'), TO_DATE('2024-09-01', 'YYYY-MM-DD'), 1, 1, 1, 1, 1, 'https://example.com/idproof1', 1),
(4, 'Male', 2, 1000, TO_DATE('2023-09-01', 'YYYY-MM-DD'), TO_DATE('2024-09-01', 'YYYY-MM-DD'), 2, 2, 0, 2, 2, 'https://example.com/idproof2', 2),
(6, 'Female', 3, 1200, TO_DATE('2023-09-01', 'YYYY-MM-DD'), TO_DATE('2024-09-01', 'YYYY-MM-DD'), 3, 3, 1, 3, 3, 'https://example.com/idproof3', 3),
(8, 'Male', 1, 1400, TO_DATE('2023-09-01', 'YYYY-MM-DD'), TO_DATE('2024-09-01', 'YYYY-MM-DD'), 4, 4, 0, 4, 1, 'https://example.com/idproof4', 4),
(10, 'Female', 2, 1600, TO_DATE('2023-09-01', 'YYYY-MM-DD'), TO_DATE('2024-09-01', 'YYYY-MM-DD'), 5, 5, 1, 5, 2, 'https://example.com/idproof5', 5);

INSERT INTO ns_request (client_id, sp_id, address_id, start_date, end_date, member_id, message_to_sp, message_from_sp, status_id)
VALUES 
(1, 2, 1, TO_DATE('2024-01-01', 'YYYY-MM-DD'), TO_DATE('2024-06-01', 'YYYY-MM-DD'), 1, 'Please take care of my father.', NULL, 1),
(3, 4, 2, TO_DATE('2024-02-01', 'YYYY-MM-DD'), TO_DATE('2024-07-01', 'YYYY-MM-DD'), 2, 'Need assistance with daily activities.', NULL, 3),
(5, 6, 3, TO_DATE('2024-03-01', 'YYYY-MM-DD'), TO_DATE('2024-08-01', 'YYYY-MM-DD'), 3, 'Looking for a nurse for post-surgery care.', NULL, 2),
(7, 8, 4, TO_DATE('2024-04-01', 'YYYY-MM-DD'), TO_DATE('2024-09-01', 'YYYY-MM-DD'), 4, 'Require caregiver for elderly care.', NULL, 1),
(9, 10, 5, TO_DATE('2024-05-01', 'YYYY-MM-DD'), TO_DATE('2024-10-01', 'YYYY-MM-DD'), 5, 'Need nurse for long-term care.', NULL, 4);

