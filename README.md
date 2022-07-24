# Realo API
[Docker Image](https://hub.docker.com/r/abialiauski/realo) 

```` docker run -d --name realo -p 8080:8080 realo ````

![example workflow](https://github.com/h1alexbel/realo/actions/workflows/maven.yml/badge.svg)
![GitHub pull requests](https://img.shields.io/github/issues-pr/h1alexbel/realo)
[![Hits-of-Code](https://hitsofcode.com/github/h1alexbel/realo?branch=main)](https://hitsofcode.com/github/h1alexbel/realo/view?branch=main)
<br><br><br>
**Authors**: Aliaksei Bialiauski

## Contents

- [What is the goal of this project?](#what-is-the-goal-of-this-project)
- [Requirements](#requirements)
- [Design Diagrams](#design)
- [Architecture](#architecture)
- [Code](#code)
- [Want to contribute?](#want-to-contribute)

# What is the goal of this project?

Create open source Engine/API for real estate markets.

# Requirements

1.User authentication and authorization

2.Create and track Providers of the Estates

3.Create and track Estates

4.Create and track Announcements

5.Add Announcements of Estate sell to user's interests list

6.Flexible data quering by the API filters


# Not supported yet

[Admin by default credentials](https://github.com/h1alexbel/realo/issues/18)

## Glossary 📝
1.User - the user/customer of the application.

2.Provider - person, or an organization which can provide market with estates and its building.

3.Estate - an extensive area of land in the country, usually with a large house, owned by one person, family, or organization.

4.Announcement - a formal public statement about a estate on some resources/sites.

# Architecture
RESTful API created with N-tier architecture(Repository | Service | Controller)

# Want to contribute?
Don't be shy. Just open [issue](https://github.com/h1alexbel/realo/issues) or [pull request](https://github.com/h1alexbel/realo/pulls).
