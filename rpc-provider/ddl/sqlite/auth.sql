create table t_auth_permission (
	id text not null,
	menu_name varchar2(50) null,
	menu_code varchar2(50) not null,
	url text null,
	lev integer null,
	sort integer null,
	parent_id text null,
	remark varchar2(200) null,
	create_time timestamp not null,
	update_time timestamp null,
	is_del integer not null default 0,
	primary key (id)
);

create table t_auth_role (
	id text not null,
	role_name varchar2(50) null,
	role_code varchar2(50) not null,
	parent_id text not null,
	remark varchar2(200) null,
	create_time timestamp not null,
	update_time timestamp null,
	is_del integer not null default 0,
	primary key (id)
);

create table t_auth_role_permission (
	id text not null,
	role_id text not null,
	permission_id text null,
	create_time timestamp not null,
	update_time timestamp null,
	is_del integer not null default 0,
	primary key (id)
);

create table t_auth_user (
	id text not null,
	email varchar2(50) null,
	login_name varchar2(50) not null,
	user_name varchar2(50) not null,
	pass_word varchar2(50) not null,
	salt varchar2(50) null,
	create_time timestamp not null,
	update_time timestamp null,
	is_del integer not null default 0,
	primary key (id)
);

create table t_auth_user_role (
	id text not null,
	role_id text null,
	user_id text not null,
	create_time timestamp not null,
	update_time timestamp null,
	is_del integer not null default 0,
	primary key (id)
);

