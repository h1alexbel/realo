-- liquibase formatted sql

-- changeset abialiauski:1
CREATE TABLE IF NOT EXISTS estate_storage.provider
(
    id            BIGSERIAL          NOT NULL,
    name          VARCHAR(64) UNIQUE NOT NULL,
    web_site_link VARCHAR(256) UNIQUE,
    CONSTRAINT pk_provider PRIMARY KEY (id)
);

-- changeset abialiauski:2
CREATE TABLE IF NOT EXISTS estate_storage.estate
(
    id                    BIGSERIAL        NOT NULL,
    estate_type           VARCHAR(48)      NOT NULL,
    square                DOUBLE PRECISION NOT NULL,
    description           VARCHAR(512)     NOT NULL,
    year_of_construction  INTEGER          NOT NULL,
    provider_id           BIGINT REFERENCES estate_storage.provider (id),
    country               VARCHAR(32)      NOT NULL,
    city                  VARCHAR(32)      NOT NULL,
    district              VARCHAR(64),
    street                VARCHAR(64)      NOT NULL,
    closest_metro_station VARCHAR(128)     NOT NULL,
    CONSTRAINT pk_estate PRIMARY KEY (id)
);

-- changeset abialiauski:3
CREATE TABLE IF NOT EXISTS user_storage.user_account
(
    id                  BIGSERIAL           NOT NULL,
    first_name          VARCHAR(64)         NOT NULL,
    last_name           VARCHAR(64)         NOT NULL,
    gender              VARCHAR(16)         NOT NULL,
    login               VARCHAR(64) UNIQUE  NOT NULL,
    password            VARCHAR(128)        NOT NULL,
    email               VARCHAR(128) UNIQUE NOT NULL,
    role                VARCHAR(64)         NOT NULL,
    country             VARCHAR(32)         NOT NULL,
    city                VARCHAR(48),
    phone_number        VARCHAR(16)         NOT NULL,
    preferred_messenger VARCHAR(10),
    CONSTRAINT pk_user_account PRIMARY KEY (id)
);

-- changeset abialiauski:4
CREATE TABLE IF NOT EXISTS announcement_storage.announcement
(
    id                BIGSERIAL          NOT NULL,
    announcement_type VARCHAR(64)        NOT NULL,
    title             VARCHAR(64) UNIQUE NOT NULL,
    details           VARCHAR(512),
    estate_id         BIGINT REFERENCES estate_storage.estate (id),
    user_id           BIGINT REFERENCES user_storage.user_account (id),
    created_at        date,
    price             DECIMAL,
    currency_type     VARCHAR(3),
    is_loan_possible  BOOLEAN            NOT NULL,
    CONSTRAINT pk_announcement PRIMARY KEY (id)
);

-- changeset abialiauski:5
CREATE TABLE IF NOT EXISTS user_storage.user_interests
(
    announcement_id int8 NOT NULL REFERENCES announcement_storage.announcement (id),
    user_id         int8 NOT NULL REFERENCES user_storage.user_account (id)
);