-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, username, name, last_name, active)
VALUES
  (1, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'user@mail.com', 'user', 'Name', 'Surname',
   1);
-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, username, name, last_name, active)
VALUES (2, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'name@gmail.com', 'namesurname', 'Name',
        'Surname', 1);

INSERT INTO ROLE (role_id, role)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLE (role_id, role)
VALUES (2, 'ROLE_USER');

INSERT INTO USER_ROLE (user_id, role_id)
VALUES (1, 1);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (1, 2);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (2, 2);

INSERT INTO PRODUCT (name, description, quantity, price)
VALUES ('Cloud Service One', 'Service One', 18, 35.75);
INSERT INTO PRODUCT (name, description, quantity, price)
VALUES ('Cloud Service Two', 'Service Two', 15, 19.45);
INSERT INTO PRODUCT (name, description, quantity, price)
VALUES ('Cloud Service Three', 'Service Three', 14, 119.35);
INSERT INTO PRODUCT (name, description, quantity, price)
VALUES ('Cloud Service Four', 'Service Four', 40, 1576.25);
INSERT INTO PRODUCT (name, description, quantity, price)
VALUES ('Cloud Service Five', 'Service Five', 80, 450.45);
INSERT INTO PRODUCT (name, description, quantity, price)
VALUES ('Cloud Service Six', 'Service Six', 90, 2514.15);