# University Project
garazuj.to is a website for car passionates with a lot of features:
- user profiles,
- each user can add cars to his profile,
- cars can contain informations about services, fuel consuption, etc.,
- users can create guides which can be commented by other people.

## Contributors
- [Damian Czarnota](https://github.com/Damian-Czarnota/)
- [Rafał Bocheński](https://github.com/rbochenski1996/)
- [Przemysław Gędźba](https://github.com/pgedzba/)

## Front-End
The same contributors created front-end for this app.
Clone [front-end](https://github.com/Damian-Czarnota/garazuj-to)

## Setup project
1. Clone the project: ``` git clone https://github.com/pgedzba/garazuj.to-Api.git ```
2. Add project to IDE of your choice
4. Run it using maven

## Setup database
1. Create a new database:
`` mysql> create database garazuj_to;
mysql> create user 'admin'@'localhost' identified by 'garazuj';
mysql> grant all on garazuj_to.* to 'admin'@'localhost'; ``
3. Serve database on `` port 3310 ``
