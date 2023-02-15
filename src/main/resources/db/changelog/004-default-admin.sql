-- liquibase formatted sql

-- changeset abialiauski:1
INSERT INTO user_storage.user_account(first_name, last_name, gender, login, password, email, role, country, city,
                                      phone_number, preferred_messenger)
VALUES ('API_ADMIN', 'API_ADMIN', 'MALE', 'api_admin', '$2a$12$1uqgG2LMIqQlbFaPFnvCmOnuIn9WpF05m3MKlpt7U5E4wmRMKduvm',
        'admin@realo.com', 'ADMIN', 'US', 'Palo Alto', '(330) 526-8446',
        NULL);