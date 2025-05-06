# Drools Rule Engine - SpringBoot

How many times in you projects needed of a rule engine?

Mmmm....in my short career frequently 🤔
The past month during a technical working call the topic was represented and one my Senior teammates say: "Have you ever tried Drools?"

For my was the first time that I listen this product, so I decided to learn and touch with my hand Drools and consequence of this, I deiced to write this short tutorial.

Enjoy 🧑🏻‍💻

### Database

Run MySQL database instance:

```shell
podman run -d --rm --name mysql-db -e MYSQL_ROOT_PASSWORD=<YOUR-PWD> -e MYSQL_DATABASE=order_db -p3306:3306 mysql:9.0
```

Database details:

- DB Name: `order_db`
- Username: `root`
- Password: `<YOUR-PWD>`

Connection string: `jdbc:mysql://localhost:3306/order_db`.

#### Create tables

Execute this SLQ scripts in order to define DB table and store Drools rule configuration.

```mysql
create table order_rule(
    id numeric(10) not null,
    description varchar(100) not null,
    rule_file blob
);
alter table order_rule add constraint order_rule_pk primary key (id);
```

```mysql
insert into order_rule(id, description, rule_file) VALUES (1, 'order_availability.drl', null);
commit;
```

After that row has been created, load file as BLOB into column, to archive this can use your favorite RDBMS client. 

## Let's test

Open new CLI and digit the below `curl` command:

```shell
curl --location 'http://localhost:8080/api/v1/orders' \
--header 'Content-Type: application/json' \
--data '{
"description": "Test rule",
"amount": 100,
"price": 10.50
}'`
```

Obtain this response from API:

```json
{
"status": "Quantity not available!"
}
```

this because the rule check for available amounts:

```
rule "Magazine availability > 10"
 when
  OrderDTO(amount > 10)
 then
  result.setStatus("Quantity not available!");
end
```

If you change the amaount from 100 to 1, and re-try obtain:

```json
{
"status": "Quantity available!"
}
```

## References

- [hub.docker.com/_/mysql](https://medium.com/@tobintom/spring-boot-with-drools-rules-engine-d73e1af3c411)
- [docs.drools.org](https://medium.com/@tobintom/spring-boot-with-drools-rules-engine-d73e1af3c411)
- [spring-boot-with-drools-rules-engine](https://medium.com/@tobintom/spring-boot-with-drools-rules-engine-d73e1af3c411)