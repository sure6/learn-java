use demo;
create table if not exists offices
(
    office_code int(10)     not null auto_increment primary key ,
    city        varchar(50) not null,
    address     varchar(50) not null,
    country     varchar(50) not null,
    postal_code varchar(15) not null
    )engine = InnoDB default  charset =utf8;

create table if not exists employee(
    employee_number int(11) not null  auto_increment,
    last_name varchar(50) not null,
    first_name varchar(50) not null,
    mobile varchar(25) not null ,
    office_code int(10) not null ,
    job_title varchar(50) not null ,
    birth datetime,
    note varchar(255),
    sex varchar(5),
    primary key (employee_number),
    constraint offices_employee_fk foreign key (office_code) references offices(office_code)
    )engine = InnDB default charset = utf8;

ALTER TABLE employee engine = InnoDB;

show create table employee;

ALTER TABLE employee modify mobile varchar(25) after office_code;

desc employee;

alter table  employee change birth employee_birth datetime;

alter table  employee modify sex char(1) not null ;
alter table  employee drop note;
alter table  employee add favoriate_activity varchar(100);

drop table if exists offices;
alter table  employee_info drop foreign key offices_employee_fk;

alter table employee rename employee_info;

alter table offices modify office_code int(11) not null auto_increment;
alter table offices add primary key(office_code);

alter table employee_info add constraint offices_employee_fk foreign key (office_code) references offices(office_code);


insert into offices(city, address, country, postal_code) values
                                                             ('北京','东城区','神仙乡','10000'),
                                                             ('天津','南开区','天王村','11000'),
                                                             ('南京','玄武区','吴王乡','12310'),
                                                             ('上海','静安区','城隍庙','25412'),
                                                             ('杭州','余杭区','仓前街道','17452'),
                                                             ('武汉','江夏区','藏龙岛','345201'),
                                                             ('重庆','江北区','机场村','79268'),
                                                             ('成都','郫都区','武侯祠','79946'),
                                                             ('广州','番禺区','梵语街道','84689'),
                                                             ('深圳','宝安区','宝安街道','345841');

insert into employee_info(last_name, first_name, office_code, mobile, job_title, employee_birth, sex, favoriate_activity) values
                                                                                                                              ('三','张','1','13256733287','医生','1996/04/01','男','我爱玩游戏'),
                                                                                                                              ('四','李','10','15835538658','护士','1994/01/01','女','我爱化妆'),
                                                                                                                              ('五','王','3','17582468285','牙医','1991/05/14','男','案件嗷嗷叫感觉'),
                                                                                                                              ('六','赵','5','19523832856','工程师','1988/07/13','男','阿德撒发生发'),
                                                                                                                              ('七','吴','8','14582438538','设计师','1999/06/14','女','阿萨大大大'),
                                                                                                                              ('八','陈','2','1253548685','建筑师','2001/09/15','女','暗杀十几个咯破');
