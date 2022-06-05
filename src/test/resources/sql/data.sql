INSERT INTO estate_storage.provider(name, web_site_link)
VALUES ('A100 Development', 'https://a-100development.by/');

INSERT INTO estate_storage.estate(estate_type, square, description, year_of_construction, provider_id, country, city,
                                  district, street, closest_metro_station)
VALUES ('COUNTRY', 126.2, 'Description',
        2012, 1, 'Belarus',
        'Minsk', 'Moskovskiy', 'Central Main', '-');

INSERT INTO announcement_storage.announcement(announcement_type, title, details, estate_id, user_id, created_at, price,
                                              currency_type, is_loan_possible)
VALUES ('FOR_SELL', 'Title', 'Details', 1, null,
        null, 65000, 'USD', true);

INSERT INTO user_storage.user_account(first_name, last_name, gender, login, password, email, role, country, city,
                                      phone_number, preferred_messenger)
VALUES ('B', 'A', 'MALE', 'logg', '1oih1bjg',
        'meiakopgjeoi', 0, 'BY', 'Minsk',
        '+7864237', 'VIBER');