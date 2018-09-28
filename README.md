# 目录

* [需求分析](#需求分析)  
	* [系统设计的目的和意义](#系统设计的目的和意义)  
	* [数据字典](#数据字典)  
		* [数据项](#数据项)  
		* [数据结构](#数据结构)  
		* [数据流](#数据流)  
		* [数据存储](#数据存储)  
		* [处理过程](#处理过程)  
	* [功能分析](#功能分析)  
		* [功能流程图](#功能流程图)  
		* [普通用户](#普通用户)  
		* [管理员](#管理员)  
* [概念结构设计](#概念结构设计)  
* [逻辑结构设计](#逻辑结构设计)  
* [物理结构设计](#物理结构设计)  
	* [宿舍楼表](#宿舍楼表)  
	* [宿舍表](#宿舍表)  
	* [用电量表](#用电量表)  
	* [学生表](#学生表)  
	* [普通用户表](#普通用户表)  
	* [管理员表](#管理员表)  
* [系统实现](#系统实现)  
	* [普通用户](#普通用户)  
	* [管理员](#管理员)  

# 需求分析

## 系统设计的目的和意义
针对传统人工进行学生宿舍电量查询、交费与管理过程繁琐的问题，使用统一终端设备查询、交费与管理的方法，达到简化流程、方便实用、提高效率的效果。

## 数据字典

### 数据项

数据项名	|含义说明	| 数据类型  	|长度	|取值范围
--|--|--|--|--|
fid			|栋号			|varchar	|2		|最长2位
flevel		|楼层数			|varchar	|2		|最长2位
froom		|房间数			|int		|		|最长3位
fexpect		|宿舍楼可住人数	|int		|		|最长3位
freal		|宿舍楼实住人数	|int		|		|最长3位
did			|宿舍号			|varchar	|4		|最长4位
dexpect		|宿舍可住人数		|int		|		|最长1位
dreal		|宿舍实住人数		|int		|		|最长1位
stuid		|学号			|varchar	|13		|13位
name		|姓名			|varchar	|10		|最长10位
sex			|性别			|varchar	|2		|2位
age			|年龄			|Int		|0-65   |
depart		|系别			|varchar	|20		|最长20位
major		|专业			|varchar	|20		|最长20位
phone		|手机号			|varchar	|11		|最长11位
year		|年份			|varchar	|4		|4位
month		|月份			|varchar	|2		|最长2位
electric	|电量度数		|varchar	|4		|最长4位
fare		|用电费用		|double		|       |
money		|欠费总额		|double		|       |
password	|普通用户密码		|varchar	|20		|最长20位
rid			|工号			|varchar	|20		|最长20位
rname		|管理员用户名		|varchar	|10		|最长10位
rpassword	|管理员密码		|varchar	|20		|最长20位

### 数据结构

数据结构名|	组成
--|--
宿舍楼	|栋号，楼层数，房间数，可住人数，实住人数
宿舍		|栋号，宿舍号，可住人数，实住人数，居住性别，欠费总额
学生		|学号，姓名，性别，年龄，系别，专业，栋号，宿舍号，手机号
用电量	|栋号，宿舍号，年份，月份，用电度数，用电费用
普通用户	|学号，普通用户密码
管理员	|工号，管理员用户名，管理员密码

### 数据流

|数据流|
|--|
|数据流名：宿舍楼表 <br>说明：宿舍楼的信息<br>数据流来源：系统录入<br>数据流去向：宿舍楼表<br>组成：栋号，楼层数，房间数，可住人数，实住人数
|数据流名：宿舍表<br>说明：宿舍的信息<br>数据流来源：系统录入<br>数据流去向：宿舍表<br>组成：栋号，宿舍号，可住人数，实住人数，居住性别，欠费总额<br>
|数据流名：学生表<br>说明：学生的信息<br>数据流来源：系统录入<br>数据流去向：学生表<br>组成：学号，姓名，性别，年龄，系别，专业，栋号，宿舍号，手机号
|数据流名：用电量表<br>说明：用电信息<br>数据流来源：系统录入<br>数据流去向：用电量表<br>组成：栋号，宿舍号，年份，月份，用电度数，用电费用<br>
|数据流名：普通用户表<br>说明：普通用户信息<br>数据流来源：用户注册录入<br>数据流去向：普通用户表<br>组成：学号，普通用户密码<br>
|数据流名：管理员表<br>说明：管理员信息<br>数据流来源：管理员注册录入<br>数据流去向：管理员表<br>组成：工号，管理员用户名，管理员密码<br>

### 数据存储

|数据存储|
|--|
|数据存储名：宿舍楼信息表<br>说明：存储了宿舍楼的基本信息<br>输入的数据流：系统宿舍楼信息录入<br>输出的数据流：录入数据库<br>组成：宿舍楼信息=栋号+楼层数+房间数+可住人数+实住人数<br>
|数据存储名：宿舍信息表<br>说明：存储了宿舍的基本信息<br>输入的数据流：系统宿舍信息录入<br>输出的数据流：录入数据库<br>组成：宿舍信息=栋号+宿舍号+可住人数+实住人数+居住性别+欠费总额<br>
|数据存储名：学生信息表<br>说明：存储了学生的基本信息<br>输入的数据流：系统学生信息录入<br>输出的数据流：录入数据库<br>组成：学生信息=学号+姓名+性别+年龄+系别+专业+栋号+宿舍号+手机号<br>
|数据存储名：用电信息表<br>说明：存储了每个宿舍每个月用电的基本信息<br>输入的数据流：系统用电信息录入<br>输出的数据流：录入数据库<br>组成：用电信息=栋号+宿舍号+年份+月份+用电度数+用电费用<br>
|数据存储名：用户注册信息表<br>说明：存储了学生注册的基本信息<br>输入的数据流：用户注册信息录入<br>输出的数据流：录入数据库<br>组成：注册用户信息=学号+密码<br>
|数据存储名：管理员注册信息表<br>说明：存储了管理员注册的基本信息<br>输入的数据流：管理员注册信息录入<br>输出的数据流：录入数据库<br>组成：管理员信息=工号+管理员用户名+管理员密码<br>

### 处理过程

|处理过程|
|--|
|处理过程名：注册检验<br>说明：检验要注册的信息是否合理<br>输入：输入要注册的信息<br>处理：检验信息格式是否正确，检验数据库是否已经存在该信息<br>输出：原注册页面或注册成功页面<br>
|处理过程名：登录检验<br>说明：检验要登录的信息是否合理<br>输入：输入要登录的信息<br>处理：检验信息格式是否正确，检验数据库是否存在该信息<br>输出：原登录页面或系统主界面<br>
|处理过程名：查询信息<br>说明：通过联合查询的方式查询所需信息<br>输入：输入查询的条件<br>处理：在数据库中查询符合条件的数据<br>输出：符合条件的数据信息页面<br>
|处理过程名：添加信息<br>说明：添加学生信息<br>输入：输入要添加的学生信息<br>处理：在数据库中添加输入的学生信息<br>输出：新的信息表页面<br>
|处理过程名：修改信息<br>说明：修改学生信息<br>输入：输入修改的学生信息<br>处理：在数据库中修改学生信息<br>输出：新的信息表页面<br>
|处理过程名：删除信息<br>说明：删除学生信息<br>输入：选择要删除的学生信息<br>处理：在数据库中删除该学生信息<br>输出：新的信息表页面<br>
|处理过程名：更新欠费总额<br>说明：模拟交电费的过程<br>输入：输入要支付的金额<br>处理：将数据库中原欠费总额减去输入的支付金额并更新<br>输出：更新后的欠费总额<br>
|处理过程名：修改密码<br>说明：用户进行密码的修改<br>输入：输入旧密码、新密码、确认密码信息<br>处理：检验旧密码是否与数据库中的密码一致，将旧密码更新为新密码<br>输出：修改成功提示框<br>

## 功能分析

### 功能流程图

![process](https://github.com/linwt/electric/blob/master/picture/process.png)

### 普通用户

- 注册：通过用户注册页面用学号、密码注册一个新的账户，来使用此系统。
- 登录：通过学号、密码登录此系统。
- 查询：通过搜索指定的栋号、宿舍号、年份、月份，能够查询到每月对应的用电度数和用电费用。
- 交费：在交费页面中输入相应金额后可进行模拟交费。
- 修改密码：为了提高密码安全性，用户能够进行密码的修改。

### 管理员

- 注册：通过管理员注册页面用工号、用户名、密码注册管理员账号。
- 登录：通过工号、密码登录后使用管理学生信息功能。
- 查询
	- 学生页面：通过学号、栋号、宿舍号查询学生的具体信息。  
	- 宿舍楼页面：通过栋号查询宿舍楼具体信息。  
	- 宿舍页面：通过栋号、宿舍号查询宿舍具体信息。
- 修改：管理员能修改学生的某些信息。
- 添加：添加一条学生记录。
- 删除：删除一条学生记录。

# 概念结构设计

确定实体、实体的属性、实体之间的联系。  
![er](https://github.com/linwt/electric/blob/master/picture/er.png)

# 逻辑结构设计

- 宿舍楼（*栋号*，楼层数，房间数，可住人数，实住人数） 
- 宿舍（*栋号，宿舍号*，可住人数，实住人数，居住性别，欠费总额）
- 学生（*学号*，姓名，性别，年龄，系别，专业，栋号，宿舍号，手机号）
- 用电量（*栋号，宿舍号，年份，月份*，用电度数，用电费用）
- 普通用户（*学号*，普通用户密码）
- 管理员（*管理员号*，管理员用户名，管理员密码） 

# 物理结构设计

## 宿舍楼表

```mysql
create table floor (
  fid  varchar(2) not null ,  
  flevel  varchar(2) not null ,  
  froom  int not null ,  
  fexcept  int not null ,  
  freal  int not null ,  
  primary key(fid)  
);
```

## 宿舍表

```mysql
create table dorm (
  fid  varchar(2) not null ,
  did  varchar(4) not null ,
  dexcept  int not null ,
  dreal  int not null ,
  dsex  varchar(2) not null ,
  dmoney  double not null ,
  primary key (fid, did),
  foreign key (fid) references floor (fid)
);
```

## 用电量表

```mysql
create table ele (
  fid  varchar(2) not null , 
  did  varchar(4) not null ,
  year  varchar(4) not null ,
  month  varchar(2) not null ,
  electric  varchar(4) not null ,
  fare  double not null ,
  primary key (fid,did, year, month),
  foreign key (fid, did) references dorm (fid, did);
);
```

## 学生表

```mysql
create table stu (
  stuid  varchar(13) not null ,
  name  varchar(10) not null ,
  sex  varchar(2) not null ,
  age  int not null ,
  depart  varchar(20) not null ,
  major  varchar(20) not null ,
  phone  varchar(11) not null ,
  fid  varchar(2) not null ,
  did  varchar(4) not null ,
  primary key (stuid)
);  

create trigger tri1 
after inster on stu
for each row 
begin
update floor 
set freal=(freal+1) where fid=new.fid;
update dorm
set dreal=(dreal+1) where fid=new.fid and did=new.did; 
end;
```

## 普通用户表

```mysql
create table user (
  stuid  varchar(13) not null , 
  password  varchar(20) not null ,
  primary key (stuid)
);
```

## 管理员表

```mysql
create table root (
  rid  varchar(20) not null ,
  rname  varchar(10) not null ,
  rpassword  varchar(20) not null ,
  primary key (rid)
);
```

# 系统实现

部分功能界面展示

## 普通用户

- 注册  
![floor](https://github.com/linwt/electric/blob/master/picture/regist.png)
- 登录  
![floor](https://github.com/linwt/electric/blob/master/picture/login.png)
- 电量信息页  
![electric](https://github.com/linwt/electric/blob/master/picture/electric.png)

## 管理员

- 学生信息页  
![stu](https://github.com/linwt/electric/blob/master/picture/stu.png)
- 宿舍楼信息页  
![floor](https://github.com/linwt/electric/blob/master/picture/floor.png)
- 宿舍信息页  
![dorm](https://github.com/linwt/electric/blob/master/picture/dorm.png)
