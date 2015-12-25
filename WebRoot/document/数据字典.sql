--��̨����Ա��
create table lz_admin
(
	admin_id int primary key identity(1,1),	--����Աid
	username varchar(20) unique,	--�û���
	pwd varchar(32),	--����
	name varchar(20),	--����
	sex char(1),		--�Ա�
	birthday datetime,		--����
	phone	varchar(20),		--����
	mobile  varchar(20),		--�ֻ�
	qq		varchar(20),	--qq
	email	varchar(50),	--���� email.value.trim().replace(/\./g, "|")  email.replace('|', '.')
	address	varchar(100),		--��ַ
	del_flag	char(1),		--״̬ 0��ɾ�� 1����ʽ 2����ְ 3���ݼ� 4:���� 5:δ���
	login_time datetime,  --����½ʱ��
	in_company_time datetime,  --��ְʱ��
	other_time datetime,  --����״̬ʱ�䣬������ְ���ݼٵ�
	dept_id int, --��������
	kh_xu numeric(11,2), --���뿼��ָ�꣬-2:�ֹ���д  ĳֵ���̶�ֵ  0��������
	kh_xin numeric(11,2), --���뿼��ָ�꣬-2:�ֹ���д  ĳֵ���̶�ֵ  0��������
	kh_not numeric(11,2), --����Ѷ����ָ�꣬-2:�ֹ���д  ĳֵ���̶�ֵ  0��������
	create_time	datetime default getdate(),	--����ʱ��
	phone_fen	varchar(20),		--�ֻ�
	job char(1), --ְ�� 0���༭ 1��ҵ�� 2��֧��
	area_code	varchar(20),		--����
	office varchar(1), --�칫�ص�
	salary_no varchar(50), --���ʱ���
	browser varchar(1)  --spark��ʹ������� 0��ϵͳĬ�� 1��IE
)
;

--��ɫ��
create table lz_role
(
	role_id	int	primary key identity(1,1),	--��ɫid
	role_name	varchar(100),		--��ɫ����
	role_desc		varchar(400),		--��ע
	create_time	datetime default getdate()	--����ʱ��
)
;

--����Ա/��ɫ������
create table lz_admin_role
(
	admin_id int,		--����Աid
	role_id		int,		--��ɫid
	create_time	datetime default getdate(),		--����ʱ��
	primary key (admin_id, role_id)
)
;

--�˵���
create table lz_menu
(
	menu_id		int	primary key identity(1,1),		--�˵�id
	menu_name	varchar(50),	--�˵�����
	menu_url	varchar(100),	--�˵�url
	menu_level	char(1),		--�˵�����
	menu_order	int,		--�˵�˳��
	menu_desc	varchar(400),	--��ע
	parent_id		int,		--�ϼ��˵�id
	create_time	datetime default getdate()	--����ʱ��
)
;

--��ɫ/�˵�������
create table lz_role_menu
(
	role_id		int,		--��ɫid
	menu_id		int,		--�˵�id
	create_time	datetime default getdate(),		--����ʱ��
	primary key (role_id, menu_id)
)
;

--����Ա/�˵�������
create table lz_admin_menu
(
	admin_id int,		--����Աid
	menu_id		int,		--�˵�id
	create_time	datetime default getdate(),		--����ʱ��
	primary key (admin_id, menu_id)
)
;

--���ű�
create table lz_department
(
	dept_id int primary key identity(1,1), --����
	dept_name varchar(100),  --��������
	dept_level char(1),  --���ż���
	dept_order int,  --����˳��
	parent_id int,  --�ϼ�����
	admin_id int,  --���Ÿ�����
	kh_flag char(1),  --�Ƿ���뿼��  0��������  1������
	kh_xu numeric(11,2), --���뿼��ָ�꣬-2:�ֹ���д  ĳֵ���̶�ֵ  0��������  -1����Ա����ָ��ĺ�
	kh_xin numeric(11,2), --���뿼��ָ�꣬-2:�ֹ���д  ĳֵ���̶�ֵ  0��������  -1����Ա����ָ��ĺ�
	kh_not numeric(11,2), --����Ѷ����ָ�꣬-2:�ֹ���д  ĳֵ���̶�ֵ  0��������  -1����Ա����ָ��ĺ�
	create_time	datetime default getdate(), --����ʱ��
	txl_id int, --��Ӧ΢����ҵ��ͨѶ¼�в���id
)
;

--ҵ��Ա�¶ȿ��˱�
create table lz_busi_kh
(
	kh_id int primary key identity(1,1), --����
	admin_id int,  --ҵ��Աid
	admin_name varchar(20),	--ҵ��Ա����
	dept_id2 int,  --��������id
	dept_name2 varchar(100),  --������������
	kh_xu2 numeric(11,2), --���뿼��ָ�꣬-2:�ֹ���д  ĳֵ���̶�ֵ  0��������  -1����Ա����ָ��ĺ�
	kh_xin2 numeric(11,2), --���뿼��ָ�꣬-2:�ֹ���д  ĳֵ���̶�ֵ  0��������  -1����Ա����ָ��ĺ�
	kh_not2 numeric(11,2), --����Ѷ����ָ�꣬-2:�ֹ���д  ĳֵ���̶�ֵ  0��������  -1����Ա����ָ��ĺ�
	dept_id1 int,  --һ������id
	dept_name1 varchar(100),  --һ����������
	kh_xu1 numeric(11,2), --���뿼��ָ�꣬-2:�ֹ���д  ĳֵ���̶�ֵ  0��������  -1����Ա����ָ��ĺ�
	kh_xin1 numeric(11,2), --���뿼��ָ�꣬-2:�ֹ���д  ĳֵ���̶�ֵ  0��������  -1����Ա����ָ��ĺ�
	kh_not1 numeric(11,2), --����Ѷ����ָ�꣬-2:�ֹ���д  ĳֵ���̶�ֵ  0��������  -1����Ա����ָ��ĺ�
	xu_in numeric(11,2),  --��������
	xu_out numeric(11,2),  --����ɱ�
	xu_in2 numeric(11,2),  --����ë����
	xu_kh  numeric(11,2),  --���뿼��ָ��
	xu_lv  int,  --���������
	xin_in numeric(11,2),  --������
	xin_out numeric(11,2),  --����ɱ�
	xin_in2 numeric(11,2),  --����ë����
	xin_kh  numeric(11,2),  --���뿼��ָ��
	xin_lv  int,  --���������
	not_in numeric(11,2),  --����Ѷ����
	not_out numeric(11,2),  --����Ѷ�ɱ�
	not_in2 numeric(11,2),  --����Ѷë����
	not_kh  numeric(11,2),  --����Ѷ����ָ��
	not_lv  int,  --����Ѷ�����
	hj_in2  numeric(11,2),  --�ϼ�����
	hj_lv  int,  --�ϼ������
	status char(1),  -- 0����ȷ��  1����ȷ��  2���˻�  3�������ύ
	kh_desc varchar(400), --�˻ر�ע
	kh_month varchar(7),  --�·�
	rowspan int,  --�ϲ���
	create_time	datetime default getdate() --����ʱ��
)
;

--ϵͳ������־��
create table lz_sys_log
(
	log_id int primary key identity(1,1), --����
	admin_id int, --������Ա
	menu_id int, --�˵�
	operate_type char(1), --�������� 0��ɾ��  1�����  2���޸� 3:��ѯ
	ip varchar(20), --ip
	method varchar(100), --ִ�е�Ŀ�귽��
	log_desc varchar(2000), --��ע
	create_time	datetime default getdate(), --����ʱ��
	log_from varchar(1), --0����̨ 1��ǰ̨
)
;

--��������
create table lz_pwd_active
(
uuid varchar(36) primary key,
username varchar(20),
create_time	datetime default getdate() --����ʱ��
)
;

--�������ñ�
create table lz_mail_config
(
mail_id int primary key identity(1,1), --����
mail_host varchar(50),
mail_username varchar(50),
mail_password varchar(50),
mail_port int,
create_time	datetime default getdate() --����ʱ��
)
;

--֪ͨ��
create table lz_notice
(
noti_id int primary key identity(1,1), --����
noti_from varchar(50), --������
noti_to varchar(50), --������
noti_subject varchar(100),  --����
noti_text varchar(4000), --����
noti_error varchar(1000),  --������Ϣ
noti_type varchar(1), --���� 0���ʼ� 1��spark 2��΢��
noti_status varchar(1), --״̬ 0��δ���� 1�����ͳɹ� 2������ʧ��
send_time datetime, --���õķ���ʱ��
execute_time datetime, --ִ��ʱ��
create_time	datetime default getdate() --����ʱ��
)
;

--��ҵ����
create table lz_product_chain
(
chan_id int primary key identity(1,1), --����
chan_name varchar(100),  --��ҵ������
chan_desc varchar(400), --��ע
create_time	datetime default getdate(), --����ʱ��
chan_show varchar(1), --ǰ̨��ʾ  0������ 1����ʾ
chan_order int --˳��
)
;

--��ҵ��/��Ʒ������
create table lz_prochain_pro
(
	chan_id int,		--��ҵ��id
	pro_id		int,		--��Ʒid
	create_time	datetime default getdate(),		--����ʱ��
	primary key (chan_id, pro_id)
)
;

--��Ʒ��
create table lz_product
(
	pro_id int primary key identity(1,1), --����
	pro_cn_name varchar(100),  --��Ʒ��������
	pro_real_name varchar(100) unique, --��Ʒ��ʽ����
	pro_en_name varchar(50), --��ƷӢ������
	pro_short_name varchar(100), --��д
	pro_unit varchar(50), --��λ
	pro_desc varchar(400), --��ע
	create_time	datetime default getdate() --����ʱ��
)
;

--��Ʒ���Ա�
create table lz_product_property
(
	prop_id int primary key identity(1,1), --����
	pro_id int, --��Ʒ
	prop_cn_name varchar(100),  --������������
	prop_en_name varchar(100),  --����Ӣ������
	prop_show char(1),  --��ʾ��ʽ  0��������  1���ı���
	prop_chk char(1), --��֤����  0������֤  1����ĸ  2������  3������
	prop_desc varchar(400),  --��ע
	create_time	datetime default getdate()	--����ʱ��
)
;

--��Ʒ����ѡ���
create table lz_product_property_option
(
opti_id int primary key identity(1,1), --����
pro_id int, --��Ʒ
prop_id int,  --��Ʒ����id
opti_name varchar(100), --ѡ������
opti_order int, --ѡ��˳��
create_time	datetime default getdate()	--����ʱ��
)
;

--��Ʒ�������
create table lz_product_template
(
temp_id int primary key identity(1,1), --����
pro_id int, --��Ʒ
temp_name varchar(100),  --����
temp_desc varchar(400),  --��ע
create_time	datetime default getdate(),	--����ʱ��
temp_type char(1), --ͳ�����ͣ�0���� 1���� 2���� 3����
temp_unit varchar(50), --��λ
clas_id int, --���������
task_start_time int,
task_end_time int,
)
;

--��Ʒ���������Թ�����
create table lz_product_temp_prop
(
temp_id int, --������id
prop_id int, --����id
prop_order int, --����˳��
prop_null char(1),  --�Ƿ����  0����  1����
create_time	datetime default getdate(),	--����ʱ��
primary key (temp_id, prop_id)
)
;

--��Ʒ���ݵ�Ԫ��
create table lz_product_unit
(
	unit_id int primary key identity(1,1), --����
	unit_name varchar(200) unique,  --����
	temp_id int, --������id
	admin_id int, --�༭
	unit_desc varchar(400),  --��ע
	create_time	datetime default getdate()	--����ʱ��
    task_start_time int, --����ʼ��ʾʱ��
    task_end_time int, --������ʾ��ֹʱ��
)
;

--��Ʒ���ݵ�Ԫ���Թ�����
create table lz_product_unit_prop
(
	unit_id int,  --��Ʒ��Ԫid
	prop_id int, --��Ʒ����id
	prop_value varchar(200),  --��Ʒ����ֵ
	create_time	datetime default getdate(),	--����ʱ��
	primary key (unit_id, prop_id)
)
;

--��Ʒ��Ԫֵ��
create table lz_product_unit_value
(
unit_id int,  --��Ʒ��Ԫid
data_time datetime, --��������
unit_value numeric(11,2),  --��Ʒ��Ԫֵ
unit_desc varchar(400),  --��ע
add_admin_id int, --�����
add_time datetime,	--���ʱ��
upd_admin_id int, --�޸���
upd_time datetime, --�޸�ʱ��
primary key (unit_id, data_time)
)
;

--��Ʒ��Ԫֵ������ʱ��
create table lz_product_unit_value_impo
(
impo_id int primary key identity(1,1), --����
unit_id int,  --��Ʒ��Ԫid
data_time datetime, --��������
unit_value numeric(11,2),  --��Ʒ��Ԫֵ
unit_desc varchar(400),  --��ע
impo_admin_id int, --������
impo_time datetime,	--����ʱ��
batch_no varchar(36) --���κ�
)
;

--�˻���
create table lz_account 
(
	acct_en_name varchar(50) primary key, --Ӣ������
	acct_cn_name varchar(100) unique, --�˻�����
	acct_no varchar(50), --�˺�
	del_flag char(1), --ɾ����ʶ 0����ɾ�� 1��δɾ��
	create_time	datetime default getdate()	--����ʱ��
)
;

--�����˱�
create table lz_account_in
(
	acct_in_id int primary key identity(1,1),
	acct_en_name varchar(50), --�˻�
	company varchar(100), --��λ
	deal_time datetime, --����ʱ��
	money numeric(11,2), --���
	deal_bank varchar(100), --��������
	deal_type1 varchar(100), --���׷�ʽ
	deal_type2 varchar(100), --�������
	deal_channel varchar(100), --��������
	province varchar(100), --�Է�ʡ��
	acct_no varchar(50), --�Է��˺�
	acct_name varchar(100), --�Է�����
	deal_man varchar(20), --�����
	deal_desc1 varchar(200), --����˵��
	deal_desc2 varchar(200), --����ժҪ
	deal_desc3 varchar(200), --���׸���
	upd_time datetime, --�޸�ʱ��
	create_time	datetime default getdate()	--¼��ʱ��
) 
;

--ҵ���˱�
create table lz_account_busi
(                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
	acct_busi_id int primary key identity(1,1),
	acct_in_id int, ----���� 0��δ����
	acct_in_time datetime, --����ʱ��
	admin_id int, --ҵ��Ա
	acct_en_name varchar(50), --�˻�
	company varchar(100), --��λ
	money numeric(11,2), --���
	xu_type char(1), --�������� 0������ 1������ 2������
	meet_name varchar(100), --��������
	meet_id int ,--������ dj 2014-05-08
	contract_content varchar(100),   --��ͬ����(���б�����ͼ�����š�����  ��ѡ)
	username varchar(100), --�û���
	contract_no varchar(50), --��ͬ���
	bill_need char(1), --�Ƿ���Ҫ��Ʊ 1����  0����  2������
	bill_qian char(1), --�Ƿ���ǰ����Ʊ 1����  0����
	bill_type char(1), --��Ʊ���� 0����ֵ˰��ͨ��Ʊ 1����ֵ˰ר�÷�Ʊ
	bill_content char(1), --��Ʊ���� 0����Ϣ�� 1������� 2������
	shui_no varchar(50), --˰��
	cust_phone varchar(20), --�ͻ��绰
	cust_address varchar(100), --�ͻ���ַ
	cust_bank varchar(100), --�ͻ�������
	cust_acct_no varchar(50), --�ͻ��������˺�
	bill_flag char(1), --��Ʊ��ʶ 0��δ��Ʊ 1���ѿ�Ʊ 2����Ʊ����
	bill_time datetime, --��Ʊʱ��
	bill_no varchar(50), --Ʊ��
	file_flag char(1), --�鵵��ʶ 0��δ�鵵 1���ѹ鵵
	create_time	datetime default getdate(),	--����ʱ��
	file_no varchar(50), --�鵵��
	file_desc varchar(400), --�鵵��ע
	busi_cnt_flag char(1), --ҵ����� 0������ҵ�� 1�����ҵ��
	money_bill numeric(11,2), --��Ʊ���
	money_in numeric(11,2), --���˽��
	busi_group_id varchar(36), --���ҵ�����id
	busi_desc varchar(400), --��ע
	kdgs varchar(1), --��ݹ�˾
	kddh varchar(50), --��ݵ���
	dxcb numeric(11,2), --���ųɱ�
	qtcb numeric(11,2),  --�����ɱ�
	meet_id int,
	tcbl numeric(11,2), --��ɱ���
	tcje numeric(11,2), --��ɽ��
)
;

--��Ʒ���ݱ�
create table lz_product_datatab
(
tab_id int primary key identity(1,1), --����
pro_name varchar(100), --��Ʒ����
tab_subject varchar(100), --����
tab_subject2 varchar(100), --������
tab_cols int, --��ͷ����
date_cols int, --��������
date_size int, --���ڲ���
date_init varchar(50), --��ʼ����
tab_type varchar(100), --����
create_time	datetime default getdate() --����ʱ��
)
;

--��Ʒ���ݱ���
create table lz_product_datatab_col
(
col_id int primary key identity(1,1), --����
tab_id int, --��Ʒ���ݱ�id
col_name varchar(100), --����
col_no int, --�к�
col_width int, --�п�
col_type varchar(100), --����
create_time	datetime default getdate() --����ʱ��
)
;

--��Ʒ���ݱ���
create table lz_product_datatab_row
(
row_id int primary key identity(1,1), --����
tab_id int, --��Ʒ���ݱ�id
row_name varchar(100), --����ʾ����
col_id int, --��id
row_no int, --�к�
parent_id int, --�ϼ�id����һ�е�Ϊ0
parent_ids varchar(100), --�ϼ�����id
col_end char(1), --���һ�� 0���� 1����                                                                                                                        
unit_id int, --���ݵ�Ԫid
create_time	datetime default getdate() --����ʱ��
)
;

--�༭/��Ʒ������
create table lz_admin_product
(
	admin_id int,		--����Աid
	pro_id		int,		--��Ʒid
	create_time	datetime default getdate(),		--����ʱ��
	primary key (admin_id, pro_id)
)
;

--���Ÿ����������޸���Ա�������š�״̬��
create table lz_dept_admin_apply
(
aply_id int primary key identity(1,1), --����
admin_id int, --������id
upd_admin_id int, --Ҫ�޸ĵ���id
aply_type char(1), --�������� 0������ 1��״̬
cur_dept_id int, --��ǰ����
upd_dept_id int, --�ĺ���
cur_del_flag char(1), --��ǰ״̬
upd_del_flag char(1), --�ĺ�״̬
aply_status char(1), --����״̬ 0��δ��� 1��ͬ�� 2���ܾ�
create_time	datetime default getdate() --����ʱ��
)
;

--����Ա/mac������
create table lz_admin_mac
(
mac_id int primary key identity(1,1), --����
username varchar(20),
mac varchar(32), 
mac_status varchar(1), --0��δ��� 1������ 2���ر�
mac_desc varchar(400),
upd_time	datetime
)
;

--����Ա/ip������
create table lz_admin_ip
(
ip_id int primary key identity(1,1), --����
username varchar(20),
ip_from varchar(20),
ip_to varchar(20),
ipl_from bigint,
ipl_to bigint,
ip_desc varchar(400),
create_time	datetime default getdate() --����ʱ��
)
;

--װ����ҵ��
create table lz_device_company
(
comp_id int primary key identity(1,1), --����
comp_name varchar(100) unique,
create_time	datetime default getdate() --����ʱ��
)
;

--װ�����ͱ�
create table lz_device_type
(
type_id int primary key identity(1,1), --����
type_name varchar(100) unique,
create_time	datetime default getdate() --����ʱ��
)
;

--װ�����ݱ�
create table lz_device
(
devi_id int primary key identity(1,1), --����
comp_id int, --װ����ҵid
type_id int, --װ������id
tao_name varchar(100), --����
chan numeric(11,2), --����
ma_time datetime, --����ʱ��
status varchar(1), --״̬ 0�������� 1��ͣ�� 2������ 3������
del_flag varchar(1), --ɾ����ʶ 0����ɾ�� 1��δɾ��
create_time	datetime default getdate() --����ʱ��
)
;

--װ��ҳ���
create table lz_device_page
(
page_id int primary key identity(1,1), --����
page_name varchar(100), --����
create_time	datetime default getdate(), --����ʱ��
chan_id int --��ҵ��
)
;

--װ��ҳ�����ñ�
create table lz_device_page_config
(
page_id int,
conf_type varchar(1), --0:װ����ҵ 1:װ������
conf_id int, --����id
conf_order int, --˳��
create_time	datetime default getdate(), --����ʱ��
primary key (page_id, conf_type, conf_id)
)
;

--���˻�������Ȩ�����ñ�
create table lz_cust_auth
(
emc_id int, --���˻�id
auth_type varchar(1), --0:װ��ҳ�� 1:��ҵ��(ֻ�Բ�Ʒ���࣬������Ȩ��) 2:���ݵ�Ԫҳ�� 3:��Ŀҳ�� 4:��Ʒ 5:����(ֻ�Բ�ҵ�����࣬������Ȩ��)
auth_id int, --Ȩ��id
create_time	datetime default getdate(), --����ʱ��
auth_start_time datetime, --Ȩ�޿�ʼʱ��
auth_end_time datetime, --Ȩ�޽�ֹʱ��
primary key (emc_id, auth_type, auth_id)
)
;

--���ݵ�Ԫҳ��
create table lz_product_unit_page
(
page_id int primary key identity(1,1), --����
page_name varchar(100), --����
chan_id int, --��ҵ��
start_time datetime, --��ʼʱ��
end_time datetime, --��ֹʱ��
time_show varchar(1), --ʱ����ʾ 0������ 1������
tjkj varchar(1), --ͳ�ƿھ� 0���� 1���� 2����
tjxs varchar(1), --ͳ����ʾ 0����һ������ 1�����һ������
create_time	datetime default getdate() --����ʱ��
)
;

--���ݵ�Ԫҳ��/���ݵ�Ԫ������
create table lz_prounitpage_unit
(
page_id int,
unit_id int,
unit_order int,
create_time	datetime default getdate(), --����ʱ��
alias varchar(200), --����
primary key (page_id, unit_id)
)
;

--�����ձ�
create table lz_workday
(
day_time datetime primary key, --����
work varchar(1), --0����Ϣ�� 1��������
upd varchar(1), --0��δ�޸� 1���޸�
upd_admin_id int, --�޸���
upd_time datetime, --�޸�ʱ��
create_time	datetime default getdate() --����ʱ��
)
;

--��������/��Ʒ������
create table lz_dept_product
(
	dept_id int,		--��������id
	pro_id		int,		--��Ʒid
	create_time	datetime default getdate(),		--����ʱ��
	primary key (dept_id, pro_id)
)
;

--spark����ͳ�Ʊ�
create table lz_spark_online
(
username varchar(20), --�û���
login_time datetime, --��½����
online int, --����ʱ�� ����
last_upd_time datetime, --������ʱ��
create_time datetime default getdate(),		--�״ε�¼ʱ��
primary key (username, login_time)
)
;

--����������
create table lz_product_temp_class
(
clas_id int primary key identity(1,1), --����
clas_name varchar(100),  --��������
clas_desc varchar(400), --��ע
create_time	datetime default getdate(), --����ʱ��
clas_order int --˳��
)
;

--���˻����ٱ�
create table lz_mainuser_gz
(
gz_id int primary key identity(1,1), --����
emc_id int, --���˻�
admin_id int, --������
gz_type varchar(1), --���ͣ�0��ϵͳ 1������
gz_text varchar(200), --����
create_time	datetime default getdate(), --����ʱ��
cuc_id int, --��ϵ��
)
;

--����
create table lz_product_wang
(
wang_id int primary key identity(1,1), --����
wang_name varchar(100),  --��ҵ������
wang_order int, --˳��
wang_desc varchar(400), --��ע
create_time	datetime default getdate() --����ʱ��
)
;

--����/��ҵ��������
create table lz_product_wang_chain
(
wang_id int,
chan_id int,
chan_order int,
create_time	datetime default getdate(),
primary key (wang_id, chan_id)
)
;

--��Ʒ/���ݵ�Ԫ�����
create table lz_product_yj
(
cuu_id int,
yj_id int,
yj_type varchar(1), --0:��Ʒ 1:���ݵ�Ԫ
yj_text varchar(4000), --���
create_time	datetime default getdate(), --����ʱ��
primary key (cuu_id, yj_id, yj_type)
)
;

--��Ʒ��ע��
create table lz_product_gz
(
cuu_id int,
pro_id int,
create_time	datetime default getdate(), --����ʱ��
primary key (cuu_id, pro_id)
)
;

--��ҵ�����
create table lz_ente_gq
(
gq_id int primary key identity(1,1), --����
cue_id int, --��ҵid
gq_type varchar(1), --0���� 1����Ӧ
gq_title varchar(200), --����ͺ�
pro_id int, --��Ʒ
gq_num int, --����
gq_unit varchar(100), --��λ
gq_cycle varchar(1), --ʱ������ 0���� 1���� 2���� 3���� 4����
gq_price numeric(11,2), --�۸�
gq_yxsj datetime, --��Ч��
cuc_id int, --��ϵ��
admin_id int, --�༭
gq_desc varchar(400), --��ע
upd_admin_id int, --�޸���
upd_time datetime, --�޸�ʱ��
create_time	datetime default getdate() --����ʱ��
gq_from varchar(1), --������Դ 0����̨ 1��ǰ̨PC 2��ǰ̨΢�� 3:�ͻ�ǰ̨΢��
gq_sh varchar(1), --���״̬ 0:δ��� 1��ͨ�� 2���ܾ�
bd_admin_id int, --��󲦴���
bd_time datetime, --��󲦴�ʱ��
dt_admin_id int, --����ͨ�ˣ��༭��
dt_time datetime, --����ͨʱ��
refresh_time datetime, --ˢ��ʱ��
gq_xia varchar(1), --0���¼� 1���ϼ�
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
gq_province int, --ȫ�� 0������ȫ�� 1����ȫ��
)
;

--������ϵ�˲鿴��¼
create table lz_cont_see
(
see_id int primary key identity(1,1), --����
cuu_id int, --��¼�˻�
cuc_id int, --�鿴����ϵ��
create_time	datetime default getdate(), --����ʱ��
)
;

--�����Ʒ���Ա�
create table lz_pro_prop_gq
(
prop_id int primary key identity(1,1), --����
pro_id int, --��Ʒ
prop_cn_name varchar(100),  --������������
prop_en_name varchar(100),  --����Ӣ������
prop_order int, --����˳��
gq_col int, --��Ӧ������е��ֶ�
prop_desc varchar(400),  --��ע
create_time	datetime default getdate()	--����ʱ��
)
;

--�����Ʒ����ѡ���
create table lz_pro_prop_opti_gq
(
opti_id int primary key identity(1,1), --����
pro_id int, --��Ʒ
prop_id int,  --��Ʒ����id
opti_name varchar(100), --ѡ������
opti_order int, --ѡ��˳��
create_time	datetime default getdate()	--����ʱ��
)
;

--�����
create table oc_areas
(
id int primary key identity(1,1), --����
aname varchar(40),
)
;

--����ʡ������
create table lz_gq_province
(
gq_id int,
pid int,
create_time	datetime default getdate(),	--����ʱ��
primary key (gq_id, pid)
)
;

--���������
create table lz_sms_task
(
task_id int primary key identity(1,1),
task_name varchar(200), --������������
admin_id int, --�����༭
sms_text varchar(500), --����ģ��
task_desc varchar(400), --����ע
add_admin_id int, --������
add_time datetime, --����ʱ��
upd_admin_id int, --�޸���
upd_time datetime, --�޸�ʱ��
)
;

--��������/��ϵ�˹�����
create table lz_sms_cont
(
task_id int,
cuc_id int,
upd_admin_id int, --�޸���
upd_time datetime, --�޸�ʱ��
primary key (task_id, cuc_id),
)
;

--�������ݱ�
create table lz_sms_text
(
text_id int primary key identity(1,1),
task_id int,
sms_text varchar(500), --��������
send_admin_id int, --������
send_time datetime, --����ʱ��
send_date int, --��������
)
;
create index ix_send_date on lz_sms_text (send_date);

--������־��(me:ȥ���ط���־�����ϸ����κţ�ͬһ�������ظ��������κ���ͬ��ʧ�ܵ��ط������¼�Ϊ�ӿڷ���ֵΪʧ��ʱ�򣬸��Ƶ�ǰ��¼����ӣ������ټӶ�ʱ�����ѯ����ʧ�ܵ�����ӵ����У������鷳��������Ҫ�õ��ط���־)
create table lz_sms_log
(
log_id int primary key identity(1,1),
send_admin_id int, --������
mobile varchar(20), --�ֻ�����
mobile4 varchar(4), --�ֻ��������λ
text_id int,
sms_type varchar(1), --�������� 0:�༭���� 1:��֤�� 2:�ƹ���� 3:��Ѷͨ
pay_type varchar(1), --�������� 0:��� 1:����
sms_intf varchar(1), --�ӿ� 0:��� 1:����
own_admin_id int, --������ID(��ϵ������Ӧ���˺ŵĸ�����id)
send_status varchar(1), --����״̬ 0:δ���� 1:���ͳɹ� 2:����ʧ�� 3:�ѷ���
send_time datetime, --����ʱ��
send_date int, --��������
execute_time datetime, --ִ��ʱ��
retu_time datetime, --�ӿڷ���ʱ��
retu_msg varchar(1000), --�ӿڷ�����Ϣ
repe_cnt int, --�ط�����
repe_flag varchar(1), --�ط���־ 0:δ�ط� 1:���ط�����ν���·��ͣ����Ǹ��Ƶ�ǰʧ�ܼ�¼����������ӵ����У�״̬Ϊδ���͡�����ֱ�ӵ��÷��ͽӿڣ�Ҳ����ֱ���޸�ʧ�ܼ�¼״̬Ϊδ���ͣ�����û������������ʷ��¼��
msg_id varchar(100), --״̬����ʹ��
)
;
create index ix_send_date on lz_sms_log (send_date);

--���Žӿڱ�
create table lz_sms_intf
(
mobile4 varchar(4) primary key, --�ֻ��������λ
sms_intf varchar(1), --�ӿ� 0:��� 1:����
create_time	datetime default getdate()	--����ʱ��
)
;






















--Ԥ�Ƶ��˱�-dingjie
CREATE TABLE lz_account_except(
account_except_id INT  not null identity(1,1) ,--���
admin_id INT   ,--��Ա���
money_xin NUMERIC(11,2)   ,--����	
money_xv NUMERIC(11,2)   ,--����
money_not NUMERIC(11,2)   ,--����Ѷ
upd_date DATETIME,   --������ʱ��
desc_xin	VARCHAR(100) ,
desc_xv		VARCHAR(100),
desc_not	VARCHAR(100) ,
PRIMARY KEY(account_except_id));

--ʦͽ��ϵ��-dingjie
CREATE TABLE LZ_TEACHER_STUDENT(
TSA_ID INT   not null identity(1,1) ,
TSA_STUDENT_ID INT   ,
TSA_TEACHER_ID INT   ,
TSA_REGISTOR INT   ,
TSA_REGIST_DATE DATETIME    default getdate() 
PRIMARY KEY(TSA_ID));

--����Э�������˱�-dingjie
CREATE TABLE LZ_DEPT_COOPERADMIN(
DCA_ID INT   not null identity(1,1) ,
DCA_DEPTID INT   ,
DCA_ADMIN_ID INT   ,
DCA_REGISTOR INT   ,
DCA_REGIST_DATE DATETIME    default getdate() 
PRIMARY KEY(DCA_ID));

--ϵͳ�ֵ����ñ�-dingjie
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





-- ��ʼ��
truncate table lz_admin;
truncate table lz_role;
truncate table lz_admin_role;
truncate table lz_menu;
truncate table lz_role_menu;
truncate table lz_admin_menu;
truncate table lz_department;
truncate table lz_busi_kh;

insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('ϵͳ����', '', '1', 1, 0);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('ϵͳά��', '', '2', 1, 1);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('�˵�ά��', '/menu/queryMenu', '3', 1, 2);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('��ɫά��', '/role/queryRole', '3', 2, 2);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('����Աά��', '/admin/queryAdmin', '3', 3, 2);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('����ά��', '/dept/queryDept', '3', 4, 2);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('ҵ�񿼺�', '', '2', 2, 1);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('ҵ�񿼺�ά��', '/busi/queryBusi', '3', 1, 7);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('ҵ�񿼺�ȷ��', '/busi/queryBusiConfirm', '3', 2, 7);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('��������', '', '2', 3, 1);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('�ҵ���Ϣ', '/admin/showMyInfo/0', '3', 1, 10);
insert into lz_menu (menu_name, menu_url, menu_level, menu_order, parent_id)
values ('ϵͳ��־', '/sysLog/querySysLog', '3', 5, 2);

insert into lz_role (role_name) values ('��������Ա');

insert into lz_role_menu (role_id, menu_id) 
select 1, menu_id from lz_menu;

insert into lz_admin (username, pwd, name, sex, phone, email, del_flag, dept_id, kh_xu, kh_xin, kh_not)
values ('admin', '21232f297a57a5a743894a0e4a801fc3', '����Ա', '1', '2591619', 'lzwl@oilchem.net', '1', -1, 0, 0, 0);

insert into lz_admin_role (admin_id, role_id)
values (1, 1);


















