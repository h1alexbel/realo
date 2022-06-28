INSERT INTO estate_storage.provider(name, web_site_link)
VALUES ('A100 Development', 'https://a-100development.by/');

INSERT INTO user_storage.user_account(first_name, last_name, gender, login, password, email, role, country, city,
                                      phone_number, preferred_messenger)
VALUES ('John', 'Doe', 'MALE', 'jog123', '1231fa', 'doe@gmail.com',
        'USER', 'Belarus', 'Minsk', '+189471', 'VIBER'),

       ('Alexey', 'Belyavsky', 'MALE', 'Alexey77',
        'root', 'email.com', 'USER', 'US', 'SFA',
        '+352352753', 'TELEGRAM');

INSERT INTO estate_storage.estate(id, estate_type, square, description, year_of_construction, provider_id, country,
                                  city,
                                  district, street, closest_metro_station)
VALUES (1, 'LIVING', 45.0, 'DEs', 2023,
        (SELECT provider.id FROM estate_storage.provider WHERE name = 'A100 Development'),
        'V', 'G',
        'F', 'S', 'W'),
       (2, 'COMMERCIAL', 67.7, 'Description', 2020,
        (SELECT provider.id FROM estate_storage.provider WHERE name = 'A100 Development'),
        'India', 'Mumbai', 'no', 'Street 27', 'no'),
       (3, 'COMMERCIAL', 67.7, 'Description', 2019,
        null, 'Russia', 'Moscow', 'Okt',
        'Main street', 'Moskovskaya'),
       (4, 'COUNTRY', 60.1, 'Pleasant estate in Moscow!', 2016,
        null, 'Russia', 'Moscow', 'Okt',
        'Main street', 'no'),
       (5, 'COUNTRY', 122.1, 'Top country estate in Moscow!', 2016,
        null, 'Russia', 'Moscow', 'Dict',
        'Dict street', 'no'),
       (6, 'COMMERCIAL', 78.4, 'Top commercial estate in Moscow!', 2011,
        null, 'Russia', 'Moscow', 'Dict',
        'Dict street', 'no'),
       (7, 'COMMERCIAL', 21.4, 'Small but cozy estate in Ukraine!', 2011,
        null, 'Ukraine', 'Kiev', 'Go Disc',
        'Go street', 'Petra Kazimirova'),
       (8, 'COMMERCIAL', 22.4, 'Small but cozy estate in Ukraine!', 2011,
        null, 'Ukraine', 'Kiev', 'Go Disc',
        'Go street', 'Petra Kazimirova');


INSERT INTO announcement_storage.announcement(announcement_type, title, details, estate_id, user_id, created_at, price,
                                              currency_type, is_loan_possible)
VALUES ('FOR_SELL', 'So cheap!', 'Details', null, null,
        now(), 15,
        'EUR', true),
       ('LONG_TERM_RENT', 'Mumbai so good place', 'Some details',
        2, null, now(), 25000, 'USD', true),
       ('SHORT_TERM_RENT', 'Moscow top estate!', 'Some details',
        3, null, now(), 250000, 'USD', true),
       ('SHORT_TERM_RENT', 'Kiev top estate!', 'Some details',
        7, null, now(), 250000, 'USD', true),
       ('SHORT_TERM_RENT', 'Kiev top estate #2!', 'Some details',
        8, null, now(), 250000, 'USD', true);