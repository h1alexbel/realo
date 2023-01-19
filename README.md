![mvn](https://github.com/h1alexbel/realo/actions/workflows/maven.yml/badge.svg)
[![Docker](https://img.shields.io/docker/v/abialiauski/realo/latest)](https://hub.docker.com/repository/docker/abialiauski/realo/general)
[![codecov](https://codecov.io/github/h1alexbel/realo/branch/main/graph/badge.svg?token=SQYcfchyTm)](https://codecov.io/github/h1alexbel/realo)
[![Hits-of-Code](https://hitsofcode.com/github/h1alexbel/realo?branch=main)](https://hitsofcode.com/github/h1alexbel/realo/view?branch=main)
![GitHub pull requests](https://img.shields.io/github/issues-pr/h1alexbel/realo)

Realo is an experimental engine for Real Estate deals.

## Glossary
1.User - the user/customer of the application.

2.Provider - person, or an organization which can provide market with estates and its building.

3.Estate - an extensive area of land in the country, usually with a large house, owned by one person, family, or organization.

4.Announcement - a formal public statement about a estate on some resources/sites.

# Prerequisites

You need to have [```Docker```](https://www.docker.com), ```Java 11+```, and ```Maven 3.3+``` installed.

# Quick Start

Run this script to start 2 containers: Realo-API,
and [PostgreSQL](https://www.postgresql.org)

```shell
$ sh up.sh
```

Then, go to ```POST```
```localhost:8080/api/v1/auth/login```
With the next body:

```json
{
  "username": "api_admin",
  "password": "password"
}
```

You will receive [Access](https://www.wikiwand.com/en/Access_token) token.
You will need them using the Realo API.
Access Token expires in 5 minutes.

# Endpoints
API Management docs placed here: ```/swagger-ui```

# Security

Realo is a secured API. We are using [```JWT```](https://www.wikiwand.com/en/JSON_Web_Token) token
to authenticate and authorize users.
So, each request to the secured resources should go with the ```Autorization``` header.
e.g.:

```
-X Authorization: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYm9vay5jb20iLCJyb2xlIjoiQURNSU4iLCJpYXQiOjE2NzE0NjA4OTcsImV4cCI6MTY3MTQ2MTE5N30.O8m05s3xEkhroTFjh9xdaCUMdUB1B-pKEu9f0TaLb1s
```

Also, we have 3 types of authority: ```USER```, ```AGENT``` and the ```ADMIN```

# Want to contribute?

Don't be shy. Just submit an [issue](https://github.com/h1alexbel/realo/issues) or open
a [pull request](https://github.com/h1alexbel/realo/pulls).