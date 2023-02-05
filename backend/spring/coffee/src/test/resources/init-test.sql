delete from member;
delete from menu;
delete from order_line;
delete from purchase_order;

commit;

insert into member (member_id, name, point, version) values ('010-3354-5349', 'daniel kim', 145000, 1);
insert into member (member_id, name, point, version) values ('010-9195-5349', 'crystal yun', 95000, 2);
insert into member (member_id, name, point, version) values ('010-2725-5349', 'kay yun', 90000, 3);

insert into menu (menu_id, menu_name, price) values (1, 'americano', 3500);
insert into menu (menu_id, menu_name, price) values (2, 'ice americano', 4000);
insert into menu (menu_id, menu_name, price) values (3, 'latte', 4200);
insert into menu (menu_id, menu_name, price) values (4, 'ice tea', 3000);

insert into purchase_order (order_no, member_id, total_point, order_date)
values (1, '010-3354-5349', 49000, CURRENT_TIMESTAMP());
insert into purchase_order (order_no, member_id, total_point, order_date)
values (2, '010-9195-9531', 70000, CURRENT_TIMESTAMP());


insert into order_line (order_number, menu_id, price, quantity, total_price, order_date)
values (1, 1, 3500, 4, 14000, CURRENT_TIMESTAMP());
insert into order_line (order_number, menu_id, price, quantity, total_price, order_date)
values (1, 2, 4000, 2, 8000, CURRENT_TIMESTAMP());
insert into order_line (order_number, menu_id, price, quantity, total_price, order_date)
values (1, 3, 4200, 5, 21000, CURRENT_TIMESTAMP());
insert into order_line (order_number, menu_id, price, quantity, total_price, order_date)
values (1, 4, 3000, 2, 6000, CURRENT_TIMESTAMP());
insert into order_line (order_number, menu_id, price, quantity, total_price, order_date)
values (2, 1, 3500, 4, 14000, CURRENT_TIMESTAMP());
insert into order_line (order_number, menu_id, price, quantity, total_price, order_date)
values (2, 2, 4000, 2, 8000, CURRENT_TIMESTAMP());
insert into order_line (order_number, menu_id, price, quantity, total_price, order_date)
values (2, 3, 4200, 5, 21000, CURRENT_TIMESTAMP());
insert into order_line (order_number, menu_id, price, quantity, total_price, order_date)
values (2, 4, 3000, 2, 6000, CURRENT_TIMESTAMP());