--后台管理员表
create table lz_admin
(
	admin_id int primary key identity(1,1),	--管理员id
	username varchar(20) unique,	--用户名
	pwd varchar(32),	--密码
	name varchar(20),	--姓名
	sex char(1),		--性别
	birthday datetime,		--生日
	phone	varchar(20),		--座机
	mobile  varchar(20),		--手机
	qq		varchar(20),	--qq
	email	varchar(50),	--邮箱 email.value.trim().replace(/\./g, "|")  email.replace('|', '.')
	address	varchar(100),		--地址
	del_flag	char(1),		--状态 0：删除 1：正式 2：离职 3：休假 4:试用 5:未审核
	login_time datetime,  --最后登陆时间
	in_company_time datetime,  --入职时间
	other_time datetime,  --其他状态时间，比如离职、休假等
	dept_id int, --所属部门
	kh_xu numeric(11,2), --续入考核指标，-2:手工填写  某值：固定值  0：不考核
	kh_xin numeric(11,2), --新入考核指标，-2:手工填写  某值：固定值  0：不考核
	kh_not numeric(11,2), --非资讯考核指标，-2:手工填写  某值：固定值  0：不考核
	create_time	datetime default getdate(),	--创建时间
	phone_fen	varchar(20),		--分机
	job char(1), --职务 0：编辑 1：业务 2：支持
	area_code	varchar(20),		--区号
	office varchar(1), --办公地点
	salary_no varchar(50), --工资编码
	browser varchar(1)  --spark打开使用浏览器 0：系统默认 1：IE
)
;

--角色表
create table lz_role
(
	role_id	int	primary key identity(1,1),	--角色id
	role_name	varchar(100),		--角色名称
	role_desc		varchar(400),		--备注
	create_time	datetime default getdate()	--创建时间
)
;

--管理员/角色关联表
create table lz_admin_role
(
	admin_id int,		--管理员id
	role_id		int,		--角色id
	create_time	datetime default getdate(),		--创建时间
	primary key (admin_id, role_id)
)
;

--菜单表
create table lz_menu
(
	menu_id		int	primary key identity(1,1),		--菜单id
	menu_name	varchar(50),	--菜单名称
	menu_url	varchar(100),	--菜单url
	menu_level	char(1),		--菜单级别
	menu_order	int,		--菜单顺序
	menu_desc	varchar(400),	--备注
	parent_id		int,		--上级菜单id
	create_time	datetime default getdate()	--创建时间
)
;

--角色/菜单关联表
create table lz_role_menu
(
	role_id		int,		--角色id
	menu_id		int,		--菜单id
	create_time	datetime default getdate(),		--创建时间
	primary key (role_id, menu_id)
)
;

--管理员/菜单关联表
create table lz_admin_menu
(
	admin_id int,		--管理员id
	menu_id		int,		--菜单id
	create_time	datetime default getdate(),		--创建时间
	primary key (admin_id, menu_id)
)
;

--部门表
create table lz_department
(
	dept_id int primary key identity(1,1), --主键
	dept_name varchar(100),  --部门名称
	dept_level char(1),  --部门级别
	dept_order int,  --部门顺序
	parent_id int,  --上级部门
	admin_id int,  --部门负责人
	kh_flag char(1),  --是否参与考核  0：不考核  1：考核
	kh_xu numeric(11,2), --续入考核指标，-2:手工填写  某值：固定值  0：不考核  -1：成员考核指标的和
	kh_xin numeric(11,2), --新入考核指标，-2:手工填写  某值：固定值  0：不考核  -1：成员考核指标的和
	kh_not numeric(11,2), --非资讯考核指标，-2:手工填写  某值：固定值  0：不考核  -1：成员考核指标的和
	create_time	datetime default getdate(), --创建时间
	txl_id int, --对应微信企业号通讯录中部门id
)
;

--业务员月度考核表
create table lz_busi_kh
(
	kh_id int primary key identity(1,1), --主键
	admin_id int,  --业务员id
	admin_name varchar(20),	--业务员姓名
	dept_id2 int,  --二级部门id
	dept_name2 varchar(100),  --二级部门名称
	kh_xu2 numeric(11,2), --续入考核指标，-2:手工填写  某值：固定值  0：不考核  -1：成员考核指标的和
	kh_xin2 numeric(11,2), --新入考核指标，-2:手工填写  某值：固定值  0：不考核  -1：成员考核指标的和
	kh_not2 numeric(11,2), --非资讯考核指标，-2:手工填写  某值：固定值  0：不考核  -1：成员考核指标的和
	dept_id1 int,  --一级部门id
	dept_name1 varchar(100),  --一级部门名称
	kh_xu1 numeric(11,2), --续入考核指标，-2:手工填写  某值：固定值  0：不考核  -1：成员考核指标的和
	kh_xin1 numeric(11,2), --新入考核指标，-2:手工填写  某值：固定值  0：不考核  -1：成员考核指标的和
	kh_not1 numeric(11,2), --非资讯考核指标，-2:手工填写  某值：固定值  0：不考核  -1：成员考核指标的和
	xu_in numeric(11,2),  --续入收入
	xu_out numeric(11,2),  --续入成本
	xu_in2 numeric(11,2),  --续入毛收入
	xu_kh  numeric(11,2),  --续入考核指标
	xu_lv  int,  --续入完成率
	xin_in numeric(11,2),  --新收入
	xin_out numeric(11,2),  --新入成本
	xin_in2 numeric(11,2),  --新入毛收入
	xin_kh  numeric(11,2),  --新入考核指标
	xin_lv  int,  --新入完成率
	not_in numeric(11,2),  --非资讯收入
	not_out numeric(11,2),  --非资讯成本
	not_in2 numeric(11,2),  --非资讯毛收入
	not_kh  numeric(11,2),  --非资讯考核指标
	not_lv  int,  --非资讯完成率
	hj_in2  numeric(11,2),  --合计收入
	hj_lv  int,  --合计完成率
	status char(1),  -- 0：待确认  1：已确认  2：退回  3：最终提交
	kh_desc varchar(400), --退回备注
	kh_month varchar(7),  --月份
	rowspan int,  --合并行
	create_time	datetime default getdate() --创建时间
)
;

--系统操作日志表
create table lz_sys_log
(
	log_id int primary key identity(1,1), --主键
	admin_id int, --操作人员
	menu_id int, --菜单
	operate_type char(1), --操作类型 0：删除  1：添加  2：修改 3:查询
	ip varchar(20), --ip
	method varchar(100), --执行的目标方法
	log_desc varchar(2000), --备注
	create_time	datetime default getdate(), --创建时间
	log_from varchar(1), --0：后台 1：前台
)
;

--忘了密码
create table lz_pwd_active
(
uuid varchar(36) primary key,
username varchar(20),
create_time	datetime default getdate() --创建时间
)
;

--邮箱配置表
create table lz_mail_config
(
mail_id int primary key identity(1,1), --主键
mail_host varchar(50),
mail_username varchar(50),
mail_password varchar(50),
mail_port int,
create_time	datetime default getdate() --创建时间
)
;

--通知表
create table lz_notice
(
noti_id int primary key identity(1,1), --主键
noti_from varchar(50), --发送人
noti_to varchar(50), --接收人
noti_subject varchar(100),  --主题
noti_text varchar(4000), --内容
noti_error varchar(1000),  --错误信息
noti_type varchar(1), --类型 0：邮件 1：spark 2：微信
noti_status varchar(1), --状态 0：未发送 1：发送成功 2：发送失败
send_time datetime, --设置的发送时间
execute_time datetime, --执行时间
create_time	datetime default getdate() --创建时间
)
;

--产业链表
create table lz_product_chain
(
chan_id int primary key identity(1,1), --主键
chan_name varchar(100),  --产业链名称
chan_desc varchar(400), --备注
create_time	datetime default getdate(), --创建时间
chan_show varchar(1), --前台显示  0：隐藏 1：显示
chan_order int --顺序
)
;

--产业链/产品关联表
create table lz_prochain_pro
(
	chan_id int,		--产业链id
	pro_id		int,		--产品id
	create_time	datetime default getdate(),		--创建时间
	primary key (chan_id, pro_id)
)
;

--产品表
create table lz_product
(
	pro_id int primary key identity(1,1), --主键
	pro_cn_name varchar(100),  --产品中文名称
	pro_real_name varchar(100) unique, --产品正式名称
	pro_en_name varchar(50), --产品英文名称
	pro_short_name varchar(100), --缩写
	pro_unit varchar(50), --单位
	pro_desc varchar(400), --备注
	create_time	datetime default getdate() --创建时间
)
;

--产品属性表
create table lz_product_property
(
	prop_id int primary key identity(1,1), --主键
	pro_id int, --产品
	prop_cn_name varchar(100),  --属性中文名称
	prop_en_name varchar(100),  --属性英文名称
	prop_show char(1),  --显示方式  0：下拉框  1：文本框
	prop_chk char(1), --验证规则  0：无验证  1：字母  2：数字  3：数据
	prop_desc varchar(400),  --备注
	create_time	datetime default getdate()	--创建时间
)
;

--产品属性选项表
create table lz_product_property_option
(
opti_id int primary key identity(1,1), --主键
pro_id int, --产品
prop_id int,  --产品属性id
opti_name varchar(100), --选项名称
opti_order int, --选项顺序
create_time	datetime default getdate()	--创建时间
)
;

--产品数据组表
create table lz_product_template
(
temp_id int primary key identity(1,1), --主键
pro_id int, --产品
temp_name varchar(100),  --名称
temp_desc varchar(400),  --备注
create_time	datetime default getdate(),	--创建时间
temp_type char(1), --统计类型：0：日 1：周 2：月 3：年
temp_unit varchar(50), --单位
clas_id int, --数据组分类
task_start_time int,
task_end_time int,
)
;

--产品数据组属性关联表
create table lz_product_temp_prop
(
temp_id int, --数据组id
prop_id int, --属性id
prop_order int, --属性顺序
prop_null char(1),  --是否必填  0：否  1：是
create_time	datetime default getdate(),	--创建时间
primary key (temp_id, prop_id)
)
;

--产品数据单元表
create table lz_product_unit
(
	unit_id int primary key identity(1,1), --主键
	unit_name varchar(200) unique,  --名称
	temp_id int, --数据组id
	admin_id int, --编辑
	unit_desc varchar(400),  --备注
	create_time	datetime default getdate()	--创建时间
    task_start_time int, --任务开始显示时间
    task_end_time int, --任务显示截止时间
)
;

--产品数据单元属性关联表
create table lz_product_unit_prop
(
	unit_id int,  --产品单元id
	prop_id int, --产品属性id
	prop_value varchar(200),  --产品属性值
	create_time	datetime default getdate(),	--创建时间
	primary key (unit_id, prop_id)
)
;

--产品单元值表
create table lz_product_unit_value
(
unit_id int,  --产品单元id
data_time datetime, --数据日期
unit_value numeric(11,2),  --产品单元值
unit_desc varchar(400),  --备注
add_admin_id int, --添加人
add_time datetime,	--添加时间
upd_admin_id int, --修改人
upd_time datetime, --修改时间
primary key (unit_id, data_time)
)
;

--产品单元值导入临时表
create table lz_product_unit_value_impo
(
impo_id int primary key identity(1,1), --主键
unit_id int,  --产品单元id
data_time datetime, --数据日期
unit_value numeric(11,2),  --产品单元值
unit_desc varchar(400),  --备注
impo_admin_id int, --导入人
impo_time datetime,	--导入时间
batch_no varchar(36) --批次号
)
;

--账户表
create table lz_account 
(
	acct_en_name varchar(50) primary key, --英文名称
	acct_cn_name varchar(100) unique, --账户名称
	acct_no varchar(50), --账号
	del_flag char(1), --删除标识 0：已删除 1：未删除
	create_time	datetime default getdate()	--创建时间
)
;

--财务到账表
create table lz_account_in
(
	acct_in_id int primary key identity(1,1),
	acct_en_name varchar(50), --账户
	company varchar(100), --汇款单位
	deal_time datetime, --到账时间
	money numeric(11,2), --金额
	deal_bank varchar(100), --交易行名
	deal_type1 varchar(100), --交易方式
	deal_type2 varchar(100), --交易类别
	deal_channel varchar(100), --交易渠道
	province varchar(100), --对方省市
	acct_no varchar(50), --对方账号
	acct_name varchar(100), --对方户名
	deal_man varchar(20), --汇款人
	deal_desc1 varchar(200), --交易说明
	deal_desc2 varchar(200), --交易摘要
	deal_desc3 varchar(200), --交易附言
	upd_time datetime, --修改时间
	create_time	datetime default getdate()	--录入时间
) 
;

--业务到账表
create table lz_account_busi
(                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
	acct_busi_id int primary key identity(1,1),
	acct_in_id int, ----认账 0：未认账
	acct_in_time datetime, --认账时间
	admin_id int, --业务员
	acct_en_name varchar(50), --账户
	company varchar(100), --汇款单位
	money numeric(11,2), --金额
	xu_type char(1), --续入类型 0：新入 1：续入 2：会议
	meet_name varchar(100), --会议名称
	meet_id int ,--会议编号 dj 2014-05-08
	contract_content varchar(100),   --合同内容(掌中宝、地图、短信、彩信  多选)
	username varchar(100), --用户名
	contract_no varchar(50), --合同编号
	bill_need char(1), --是否需要发票 1：是  0：否  2：待定
	bill_qian char(1), --是否提前开发票 1：是  0：否
	bill_type char(1), --发票类型 0：增值税普通发票 1：增值税专用发票
	bill_content char(1), --开票内容 0：信息费 1：会议费 2：广告费
	shui_no varchar(50), --税号
	cust_phone varchar(20), --客户电话
	cust_address varchar(100), --客户地址
	cust_bank varchar(100), --客户开户行
	cust_acct_no varchar(50), --客户开户行账号
	bill_flag char(1), --开票标识 0：未开票 1：已开票 2：发票作废
	bill_time datetime, --开票时间
	bill_no varchar(50), --票号
	file_flag char(1), --归档标识 0：未归档 1：已归档
	create_time	datetime default getdate(),	--创建时间
	file_no varchar(50), --归档号
	file_desc varchar(400), --归档备注
	busi_cnt_flag char(1), --业务笔数 0：单笔业务 1：多笔业务
	money_bill numeric(11,2), --开票金额
	money_in numeric(11,2), --认账金额
	busi_group_id varchar(36), --多笔业务分组id
	busi_desc varchar(400), --备注
	kdgs varchar(1), --快递公司
	kddh varchar(50), --快递单号
	dxcb numeric(11,2), --短信成本
	qtcb numeric(11,2),  --其他成本
	meet_id int,
	tcbl numeric(11,2), --提成比例
	tcje numeric(11,2), --提成金额
)
;

--产品数据表
create table lz_product_datatab
(
tab_id int primary key identity(1,1), --主键
pro_name varchar(100), --产品名称
tab_subject varchar(100), --标题
tab_subject2 varchar(100), --副标题
tab_cols int, --表头列数
date_cols int, --日期列数
date_size int, --日期步长
date_init varchar(50), --初始日期
tab_type varchar(100), --类型
create_time	datetime default getdate() --创建时间
)
;

--产品数据表列
create table lz_product_datatab_col
(
col_id int primary key identity(1,1), --主键
tab_id int, --产品数据表id
col_name varchar(100), --列名
col_no int, --列号
col_width int, --列宽
col_type varchar(100), --类型
create_time	datetime default getdate() --创建时间
)
;

--产品数据表行
create table lz_product_datatab_row
(
row_id int primary key identity(1,1), --主键
tab_id int, --产品数据表id
row_name varchar(100), --行显示名称
col_id int, --列id
row_no int, --行号
parent_id int, --上级id，第一列的为0
parent_ids varchar(100), --上级冗余id
col_end char(1), --最后一列 0：否 1：是                                                                                                                        
unit_id int, --数据单元id
create_time	datetime default getdate() --创建时间
)
;

--编辑/产品关联表
create table lz_admin_product
(
	admin_id int,		--管理员id
	pro_id		int,		--产品id
	create_time	datetime default getdate(),		--创建时间
	primary key (admin_id, pro_id)
)
;

--部门负责人申请修改人员所属部门、状态表
create table lz_dept_admin_apply
(
aply_id int primary key identity(1,1), --主键
admin_id int, --申请人id
upd_admin_id int, --要修改的人id
aply_type char(1), --申请类型 0：部门 1：状态
cur_dept_id int, --当前部门
upd_dept_id int, --改后部门
cur_del_flag char(1), --当前状态
upd_del_flag char(1), --改后状态
aply_status char(1), --申请状态 0：未审核 1：同意 2：拒绝
create_time	datetime default getdate() --创建时间
)
;

--管理员/mac关联表
create table lz_admin_mac
(
mac_id int primary key identity(1,1), --主键
username varchar(20),
mac varchar(32), 
mac_status varchar(1), --0：未审核 1：启用 2：关闭
mac_desc varchar(400),
upd_time	datetime
)
;

--管理员/ip关联表
create table lz_admin_ip
(
ip_id int primary key identity(1,1), --主键
username varchar(20),
ip_from varchar(20),
ip_to varchar(20),
ipl_from bigint,
ipl_to bigint,
ip_desc varchar(400),
create_time	datetime default getdate() --创建时间
)
;

--装置企业表
create table lz_device_company
(
comp_id int primary key identity(1,1), --主键
comp_name varchar(100) unique,
create_time	datetime default getdate() --创建时间
)
;

--装置类型表
create table lz_device_type
(
type_id int primary key identity(1,1), --主键
type_name varchar(100) unique,
create_time	datetime default getdate() --创建时间
)
;

--装置数据表
create table lz_device
(
devi_id int primary key identity(1,1), --主键
comp_id int, --装置企业id
type_id int, --装置类型id
tao_name varchar(100), --套名
chan numeric(11,2), --产能
ma_time datetime, --上马时间
status varchar(1), --状态 0：建设中 1：停车 2：开车 3：废弃
del_flag varchar(1), --删除标识 0：已删除 1：未删除
create_time	datetime default getdate() --创建时间
)
;

--装置页面表
create table lz_device_page
(
page_id int primary key identity(1,1), --主键
page_name varchar(100), --名称
create_time	datetime default getdate(), --创建时间
chan_id int --产业链
)
;

--装置页面配置表
create table lz_device_page_config
(
page_id int,
conf_type varchar(1), --0:装置企业 1:装置类型
conf_id int, --关联id
conf_order int, --顺序
create_time	datetime default getdate(), --创建时间
primary key (page_id, conf_type, conf_id)
)
;

--主账户、数据权限配置表
create table lz_cust_auth
(
emc_id int, --主账户id
auth_type varchar(1), --0:装置页面 1:产业链(只对产品分类，不关联权限) 2:数据单元页面 3:栏目页面 4:产品 5:单网(只对产业链分类，不关联权限)
auth_id int, --权限id
create_time	datetime default getdate(), --创建时间
auth_start_time datetime, --权限开始时间
auth_end_time datetime, --权限截止时间
primary key (emc_id, auth_type, auth_id)
)
;

--数据单元页面
create table lz_product_unit_page
(
page_id int primary key identity(1,1), --主键
page_name varchar(100), --名称
chan_id int, --产业链
start_time datetime, --开始时间
end_time datetime, --截止时间
time_show varchar(1), --时间显示 0：纵向 1：横向
tjkj varchar(1), --统计口径 0：年 1：月 2：日
tjxs varchar(1), --统计显示 0：第一条数据 1：最后一条数据
create_time	datetime default getdate() --创建时间
)
;

--数据单元页面/数据单元关联表
create table lz_prounitpage_unit
(
page_id int,
unit_id int,
unit_order int,
create_time	datetime default getdate(), --创建时间
alias varchar(200), --别名
primary key (page_id, unit_id)
)
;

--工作日表
create table lz_workday
(
day_time datetime primary key, --日期
work varchar(1), --0：休息日 1：工作日
upd varchar(1), --0：未修改 1：修改
upd_admin_id int, --修改人
upd_time datetime, --修改时间
create_time	datetime default getdate() --创建时间
)
;

--二级部门/产品关联表
create table lz_dept_product
(
	dept_id int,		--二级部门id
	pro_id		int,		--产品id
	create_time	datetime default getdate(),		--创建时间
	primary key (dept_id, pro_id)
)
;

--spark在线统计表
create table lz_spark_online
(
username varchar(20), --用户名
login_time datetime, --登陆日期
online int, --在线时长 分钟
last_upd_time datetime, --最后更新时间
create_time datetime default getdate(),		--首次登录时间
primary key (username, login_time)
)
;

--数据组分类表
create table lz_product_temp_class
(
clas_id int primary key identity(1,1), --主键
clas_name varchar(100),  --分类名称
clas_desc varchar(400), --备注
create_time	datetime default getdate(), --创建时间
clas_order int --顺序
)
;

--主账户跟踪表
create table lz_mainuser_gz
(
gz_id int primary key identity(1,1), --主键
emc_id int, --主账户
admin_id int, --跟踪人
gz_type varchar(1), --类型：0：系统 1：个人
gz_text varchar(200), --内容
create_time	datetime default getdate(), --创建时间
cuc_id int, --联系人
)
;

--单网
create table lz_product_wang
(
wang_id int primary key identity(1,1), --主键
wang_name varchar(100),  --产业链名称
wang_order int, --顺序
wang_desc varchar(400), --备注
create_time	datetime default getdate() --创建时间
)
;

--单网/产业链关联表
create table lz_product_wang_chain
(
wang_id int,
chan_id int,
chan_order int,
create_time	datetime default getdate(),
primary key (wang_id, chan_id)
)
;

--产品/数据单元意见表
create table lz_product_yj
(
cuu_id int,
yj_id int,
yj_type varchar(1), --0:产品 1:数据单元
yj_text varchar(4000), --意见
create_time	datetime default getdate(), --创建时间
primary key (cuu_id, yj_id, yj_type)
)
;

--产品关注表
create table lz_product_gz
(
cuu_id int,
pro_id int,
create_time	datetime default getdate(), --创建时间
primary key (cuu_id, pro_id)
)
;

--企业供求表
create table lz_ente_gq
(
gq_id int primary key identity(1,1), --主键
cue_id int, --企业id
gq_type varchar(1), --0：求购 1：供应
gq_title varchar(200), --规格型号
pro_id int, --产品
gq_num int, --数量
gq_unit varchar(100), --单位
gq_cycle varchar(1), --时间周期 0：日 1：周 2：月 3：季 4：年
gq_price numeric(11,2), --价格
gq_yxsj datetime, --有效期
cuc_id int, --联系人
admin_id int, --编辑
gq_desc varchar(400), --备注
upd_admin_id int, --修改人
upd_time datetime, --修改时间
create_time	datetime default getdate() --创建时间
gq_from varchar(1), --供求来源 0：后台 1：前台PC 2：前台微信 3:客户前台微信
gq_sh varchar(1), --审核状态 0:未审核 1：通过 2：拒绝
bd_admin_id int, --最后拨打人
bd_time datetime, --最后拨打时间
dt_admin_id int, --最后打通人（编辑）
dt_time datetime, --最后打通时间
refresh_time datetime, --刷新时间
gq_xia varchar(1), --0：下架 1：上架
prop_1 int default 0,
prop_2 int default 0,
prop_3 int default 0,
prop_4 int default 0,
prop_5 int default 0,
prop_6 int default 0,
prop_7 int default 0,
prop_8 int default 0,
prop_9 int default 0,
prop_10 int default 0,
gq_province int, --全国 0：不是全国 1：是全国
)
;

--供求联系人查看记录
create table lz_cont_see
(
see_id int primary key identity(1,1), --主键
cuu_id int, --登录账户
cuc_id int, --查看的联系人
create_time	datetime default getdate(), --创建时间
)
;

--供求产品属性表
create table lz_pro_prop_gq
(
prop_id int primary key identity(1,1), --主键
pro_id int, --产品
prop_cn_name varchar(100),  --属性中文名称
prop_en_name varchar(100),  --属性英文名称
prop_order int, --属性顺序
gq_col int, --对应供求表中的字段
prop_desc varchar(400),  --备注
create_time	datetime default getdate()	--创建时间
)
;

--供求产品属性选项表
create table lz_pro_prop_opti_gq
(
opti_id int primary key identity(1,1), --主键
pro_id int, --产品
prop_id int,  --产品属性id
opti_name varchar(100), --选项名称
opti_order int, --选项顺序
create_time	datetime default getdate()	--创建时间
)
;

--区域表
create table oc_areas
(
id int primary key identity(1,1), --主键
aname varchar(40),
)
;

--供求、省关联表
create table lz_gq_province
(
gq_id int,
pid int,
create_time	datetime default getdate(),	--创建时间
primary key (gq_id, pid)
)
;

--短信任务表
create table lz_sms_task
(
task_id int primary key identity(1,1),
task_name varchar(200), --短信任务名称
admin_id int, --所属编辑
sms_text varchar(500), --短信模版
task_desc varchar(400), --任务备注
add_admin_id int, --创建人
add_time datetime, --创建时间
upd_admin_id int, --修改人
upd_time datetime, --修改时间
)
;

--短信任务/联系人关联表
create table lz_sms_cont
(
task_id int,
cuc_id int,
upd_admin_id int, --修改人
upd_time datetime, --修改时间
primary key (task_id, cuc_id),
)
;

--短信内容表
create table lz_sms_text
(
text_id int primary key identity(1,1),
task_id int,
sms_text varchar(500), --短信内容
send_admin_id int, --发送人
send_time datetime, --发送时间
send_date int, --发送日期
)
;
create index ix_send_date on lz_sms_text (send_date);

--短信日志表(me:去掉重发标志，加上个批次号，同一条短信重复发送批次号相同，失败的重发触发事件为接口返回值为失败时候，复制当前记录再添加；不用再加定时任务查询所有失败的再添加到表中，这样麻烦，这样需要用到重发标志)
create table lz_sms_log
(
log_id int primary key identity(1,1),
send_admin_id int, --发送人
mobile varchar(20), --手机号码
mobile4 varchar(4), --手机号码后四位
text_id int,
sms_type varchar(1), --短信类型 0:编辑短信 1:验证码 2:推广短信 3:短讯通
pay_type varchar(1), --付费类型 0:免费 1:付费
sms_intf varchar(1), --接口 0:点客 1:百悟
own_admin_id int, --所属人ID(联系人所对应主账号的跟踪人id)
send_status varchar(1), --发送状态 0:未发送 1:发送成功 2:发送失败 3:已发送
send_time datetime, --发送时间
send_date int, --发送日期
execute_time datetime, --执行时间
retu_time datetime, --接口返回时间
retu_msg varchar(1000), --接口返回信息
repe_cnt int, --重发次数
repe_flag varchar(1), --重发标志 0:未重发 1:已重发（所谓重新发送，就是复制当前失败记录，再重新添加到表中，状态为未发送。不是直接调用发送接口；也不是直接修改失败记录状态为未发送，这样没法保留发送历史记录）
msg_id varchar(100), --状态报告使用
)
;
create index ix_send_date on lz_sms_log (send_date);

--短信接口表
create table lz_sms_intf
(
mobile4 varchar(4) primary key, --手机号码后四位
sms_intf varchar(1), --接口 0:点客 1:百悟
create_time	datetime default getdate()	--创建时间
)
;






















--预计到账表-dingjie
CREATE TABLE lz_account_except(
account_except_id INT  not null identity(1,1) ,--编号
admin_id INT   ,--人员编号
money_xin NUMERIC(11,2)   ,--新入	
money_xv NUMERIC(11,2)   ,--续入
money_not NUMERIC(11,2)   ,--非资讯
upd_date DATETIME,   --最后更新时间
desc_xin	VARCHAR(100) ,
desc_xv		VARCHAR(100),
desc_not	VARCHAR(100) ,
PRIMARY KEY(account_except_id));

--师徒关系表-dingjie
CREATE TABLE LZ_TEACHER_STUDENT(
TSA_ID INT   not null identity(1,1) ,
TSA_STUDENT_ID INT   ,
TSA_TEACHER_ID INT   ,
TSA_REGISTOR INT   ,
TSA_REGIST_DATE DATETIME    default getdate() 
PRIMARY KEY(TSA_ID));

--部门协作负责人表-dingjie
CREATE TABLE LZ_DEPT_COOPERADMIN(
DCA_ID INT   not null identity(1,1) ,
DCA_DEPTID INT   ,
DCA_ADMIN_ID INT   ,
DCA_REGISTOR INT   ,
DCA_REGIST_DATE DATETIME    default getdate() 
PRIMARY KEY(DCA_ID));

--系统字典配置表-dingjie
CREATE TABLE LZ_DICTIONARY_CONFIGURE(
DCF_ID INT   not null identity(1,1) ,
DCF_TYPE NVARCHAR(40)   not null,
DCF_TYPEDES NVARCHAR(200)   not null,
DCF_KEY NVARCHAR(40)   not null,
DCF_VALUE NVARCHAR(40)   not null,
DCF_DES NVARCHAR(200)   ,
DCF_STATUS NVARCHAR(20)   ,
DCF_REGISTOR NVARCHAR(40)   ,
DCF_REGIST_DATE DATETIME   not null default GETDATE() 
PRIMARY KEY(DCF_ID));

--old
create view v_dept_admin as 
select dept_id, dept_name, dept_level, dept_order, parent_id, admin_id
from lz_department
where dept_level = '1'
union
select x.dept_id, x.dept_name, x.dept_level, x.dept_order, x.parent_id, y.admin_id
from lz_department x
cross join lz_admin_role y
where x.dept_level = '1'
and y.role_id = 12
;

--new
create view v_dept_admin as  
SELECT dept_id, dept_name, dept_level, dept_order, parent_id, admin_id
FROM lz_department WHERE dept_level = '2'
UNION 
SELECT LD.dept_id,LD.dept_name,LD.dept_level,LD.dept_order,LD.parent_id,DC.DCA_ADMIN_ID as admin_id 
FROM lz_department LD JOIN LZ_DEPT_COOPERADMIN DC ON LD.dept_id = DC.DCA_DEPTID WHERE LD.dept_level = '2'
UNION 
SELECT d2.dept_id, d2.dept_name, d2.dept_level, d2.dept_order, d2.parent_id, d1.admin_id 
FROM lz_department d2 LEFT JOIN lz_department d1 on d2.parent_id = d1.dept_id WHERE  d1.dept_level = '1'
UNION 
SELECT LD2.dept_id,LD2.dept_name,LD2.dept_level,LD2.dept_order,LD2.parent_id,DC.DCA_ADMIN_ID as admin_id 
FROM lz_department LD2 LEFT JOIN lz_department LD1 on LD1.dept_id = LD2.parent_id 
JOIN LZ_DEPT_COOPERADMIN DC ON LD1.dept_id = DC.DCA_DEPTID where LD2.dept_level = '2'
UNION
SELECT  x.dept_id, x.dept_name, x.dept_level, x.dept_order, x.parent_id, y.admin_id
FROM    lz_department x CROSS JOIN    lz_admin_role y
WHERE   x.dept_level = '2' AND y.role_id = 12
;

CREATE view v_admin as  
select  
aa.admin_id manager_id,aa.dept_id,aa.dept_name,aa.dept_level,aa.dept_order,aa.parent_id,bb.admin_id,bb.name 
from v_dept_admin aa 
join lz_admin bb on bb.dept_id = aa.dept_id  and bb.del_flag != '0'
union 
select e.admin_id manager_id,d.dept_id,d.dept_name,d.dept_level,d.dept_order,d.parent_id,e.admin_id,e.name from lz_admin e join lz_department d on e.dept_id = d.dept_id and e.del_flag != '0'
union 
select k.TSA_TEACHER_ID manager_id,f.dept_id,f.dept_name,f.dept_level,f.dept_order,f.parent_id,g.admin_id,g.name  from lz_admin g join lz_department f on g.dept_id = f.dept_id  join LZ_TEACHER_STUDENT k on k.TSA_STUDENT_ID = g.admin_id  and g.del_flag != '0' 
;





-- 初始化
truncate table lz_admin;
truncate table lz_role;
truncate table lz_admin_role;
truncate table lz_menu;
truncate table lz_role_menu;
truncate table lz_admin_menu;
truncate table lz_department;
truncate table lz_busi_kh;

insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('系统管理', '', '1', 1, 0);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('系统维护', '', '2', 1, 1);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('菜单维护', '/menu/queryMenu', '3', 1, 2);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('角色维护', '/role/queryRole', '3', 2, 2);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('管理员维护', '/admin/queryAdmin', '3', 3, 2);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('部门维护', '/dept/queryDept', '3', 4, 2);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('业务考核', '', '2', 2, 1);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('业务考核维护', '/busi/queryBusi', '3', 1, 7);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('业务考核确认', '/busi/queryBusiConfirm', '3', 2, 7);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('个人设置', '', '2', 3, 1);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('我的信息', '/admin/showMyInfo/0', '3', 1, 10);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('系统日志', '/sysLog/querySysLog', '3', 5, 2);

insert into lz_role (role_name) values ('超级管理员');

insert into lz_role_menu (role_id, menu_id) 
select 1, menu_id from lz_menu;

insert into lz_admin (username, pwd, name, sex, phone, email, del_flag, dept_id, kh_xu, kh_xin, kh_not)
values ('admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', '1', '2591619', 'lzwl@oilchem.net', '1', -1, 0, 0, 0);

insert into lz_admin_role (admin_id, role_id)
values (1, 1);


















