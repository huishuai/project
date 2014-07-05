package com.miniboss.sync.ftp;


public class SyncConstant {
    public static final String BOSS_FTP_FILE_ID_FORMAT="0000";
    /**
     * ����ָ���
     */
    public static final String SPARATOR = "\0";
    /**
     * ����
     */
    public static final String SPARATOR_LINE = "|";
    /**
     * �»���
     */
    public static final String LINE = "_";
    /**
     * �س�����
     */
    public static final String SPARATOR_N = "\n";
    /**
     * �������������
     */
    public static final String SPARATOR_LINE_REGEX = "\\|";
    /**
     * �����������������
     */
    public static final String SPARATOR_LINE_REGEX_STR = "\\\"\\|\\\"";
    /**
     * ��������������ַ�
     */
    public static final String SPARATOR_REGEX = "\\\0";
    /**
     * ��Ʒȫ�����ݱ���
     */
    public static final String READ_PRODUCT_TYPE = "A10001";
    /**
     * ��Ʒ������Դ���ݱ���
     */
    public static final String READ_ASSEST_TYPE = "B10002";
    /**
     * ��ȫ��������ϵ���ݱ���
     */
    public static final String READ_ORDER_DISORDER_TYPE = "C03002";
    /**
     * ��Ʒ�����ݴ��·��
     */
    public static final String READ_PRODUCT = "/product";
    /**
     * ��Դ�����ݴ��·��
     */
    public static final String READ_ASSEST = "/assest";
    /**
     * ������ϵ���·��
     */
    public static final String READ_ORDER_INFO = "/order";
    /**
     * Ĭ��ʱ��
     */
    public static final String DEFAULT_TIME_STRING = "'19000101000001'";
    public static final String NEW_DEFAULT_TIME_STRING = "'1900-01-01'";
    /**
     * DAT
     */
    public static final String DAT = "DAT";
    /**
     * dat��׺
     */
    public static final String SPARATOR_DAT = ".DAT";
    /**
     * md5��׺
     */
    public static final String MD5 = ".MD5";
    /**
     * ȫ�������ļ���ŵ�ַ
     */
    public static final String WRITE_INITIAL_PATH = "/initial/";
    /**
     * ���������ļ���ŵ�ַ
     */
    public static final String WRITE_INCREMENT_PATH = "/increment/";


    public static final String  FILE_STOR_PATH="/receive/";//���ݴ��Ŀ¼
    /**
     * ��ʼ��ȫ�������ļ�����
     */
    public static final String WRITE_INIT = "A01001";
    /**
     * ��ʵʱ���������ļ�����
     */
    public static final String WRITE_EVERY_HOUR = "B01002";
    /**
     * ������ȫ�������ļ�����
     */
    public static final String WRITE_EVERY_DAY = "C02001";
    /**
     * ׼ʵʱ����ӰƬý����Ϣ�����ļ�����
     */
    public static final String WRITE_PUBLISHASSET_ADD = "A02004";
    /**
     * �ַ�����
     */
    public static final String GBK = "GBK";
    /**
     * TWO
     */
    public static final String TWO = "2";
    /**
     * ONE
     */
    public static final String ONE = "1";
    /**
     * ״̬����Ч
     */
    public static final String ORDER_SUCCESS = "01";
    /**
     * ״̬����Ч
     */
    public static final String NO = "n";
    /**
     * ��ȡ�ļ�ʱ�����С
     */
    public static final int READ_SIZE = 1024000;
    /**
     * http��URLͷ
     */
    public static final String HTTP_SUFFER = "http://";
    /**
     * �򶩹���ϵ��ʱ���в���һ�����ݵ�SQL���
     */
    public static final String INSERT_SUBSCRIBERELATIONSHIP_SQL = "insert into INSERT_SUBSCRIBERELATIONSHIP (ID, STBID, BUSINESSINFOID, TYPE, STATUS, OPERATIONTIME, STARTTIME, ENDTIME)\n" +
            "values (?,?,?,?,?,?,?,?)";
    public static final String INSERT_SUBSCRIBERELATIONSHIP_SINGLE_SQL = "insert into SINGLE_SUBSCRIBERELATIONSHIP (ID, STBID, BUSINESSINFOID, TYPE, STATUS, OPERATIONTIME, STARTTIME, ENDTIME)\n" +
            "values (?,?,?,?,?,?,?,?)";
    /**
     * ɾ��������ϵ��ʱ��SQL���
     */
    public static final String TRUNCATE_TABLE_INSERT_SUBSCRIBERELATIONSHIP_SQL = "truncate table insert_subscriberelationship";
    public static final String TRUNCATE_TABLE_INSERT_SUBSCRIBERELATIONSHIP_SINGLE_SQL = "truncate table single_subscriberelationship";

    /**
     * ��ѯ���ݿ����Ƿ���ڶ�����ϵ��ʱ��SQL���
     */
    public static final String SELECT_HAVE_INSERT_SUBSCRIBERELATIONSHIP_TABLE_ORACLE_SQL = "select * from ALL_OBJECTS WHERE   OBJECT_NAME   ='INSERT_SUBSCRIBERELATIONSHIP' ";
    public static final String SELECT_HAVE_INSERT_SUBSCRIBERELATIONSHIP_TABLE_SINGLE_ORACLE_SQL = "select * from ALL_OBJECTS WHERE   OBJECT_NAME   ='SINGLE_SUBSCRIBERELATIONSHIP' ";
    public static final String PROCEDURE_COMPARE_NAME="P_SUBSCRIBERELATIONSHIPSYNCH";

    public static final String SELECT_CREATE_PROCEDURE_COMPARE="select * from ALL_OBJECTS WHERE   OBJECT_NAME   ='"+ PROCEDURE_COMPARE_NAME+"'";
    public static final String SELECT_HAVE_INSERT_SUBSCRIBERELATIONSHIP_TABLE_MYSQL_SQL="show tables  where Tables_in_all='INSERT_SUBSCRIBERELATIONSHIP'";
    /**
     * ����������ϵ��ʱ��SQl���
     */
    public static final String CREATE_TABLE_INSERT_SUBSCRIBERELATIONSHIP_SQL = "create table insert_subscriberelationship (\n" +
            "   id VARCHAR2(60) not null,\n" +
            "   stbId VARCHAR2(255),\n" +
            "   businessInfoId VARCHAR2(255),\n" +
            "   productId VARCHAR2(255),\n" +
            "   type VARCHAR2(255),\n" +
            "   operationTime DATE,\n" +
            "   startTime DATE,\n" +
            "   endTime DATE,\n" +
            "   name VARCHAR2(100),\n" +
            "   createTime DATE,\n" +
            "   status VARCHAR2(10),\n" +
            "   memo VARCHAR2(255),\n" +
            "   createUser VARCHAR2(60),\n" +
            "   lastUpdateTime DATE,\n" +
            "   lastUpdateUser VARCHAR2(60),\n" +
            "   primary key (id)\n" +
            ")";
    public static final String CREATE_TABLE_INSERT_SUBSCRIBERELATIONSHIP_SINGLE_SQL = "create table SINGLE_SUBSCRIBERELATIONSHIP (\n" +
            "   id VARCHAR2(60) not null,\n" +
            "   stbId VARCHAR2(255),\n" +
            "   businessInfoId VARCHAR2(255),\n" +
            "   productId VARCHAR2(255),\n" +
            "   type VARCHAR2(255),\n" +
            "   operationTime DATE,\n" +
            "   startTime DATE,\n" +
            "   endTime DATE,\n" +
            "   name VARCHAR2(100),\n" +
            "   createTime DATE,\n" +
            "   status VARCHAR2(10),\n" +
            "   memo VARCHAR2(255),\n" +
            "   createUser VARCHAR2(60),\n" +
            "   lastUpdateTime DATE,\n" +
            "   lastUpdateUser VARCHAR2(60),\n" +
            "   primary key (id)\n" +
            ")";
    public static final String CREATE_PROCEDURE_COMPARE_SQL= "create or replace procedure "+PROCEDURE_COMPARE_NAME+" is\n" +
            "  v_stbid          varchar(255);\n" +
            "  v_businessinfoid varchar(255);\n" +
            "  v_OPERATIONTIME  DATE;\n" +
            "  v_STARTTIME      DATE;\n" +
            "  v_ENDTIME        DATE;\n" +
            "  v_CREATETIME     DATE;\n" +
            "  v_LASTUPDATETIME DATE;\n" +
            "  v_TYPE           varchar(255);\n" +
            "  v_NAME           varchar(100);\n" +
            "  v_STATUS         varchar(10);\n" +
            "  v_MEMO           varchar(255);\n" +
            "  v_CREATEUSER     varchar(60);\n" +
            "  v_LASTUPDATEUSER varchar(60);\n" +
            "  v_count          number;\n" +
            "  cursor bossbills is\n" +
            "    select stbid, businessinfoid from SINGLE_SUBSCRIBERELATIONSHIP;\n" +
            "  cursor bsmpbills is\n" +
            "    select stbid, businessinfoid from ismp_subscriberelationship;\n" +
            "begin\n" +
            "  open bossbills;\n" +
            "  loop\n" +
            "    fetch bossbills\n" +
            "      into v_stbid, v_businessinfoid;\n" +
            "    EXIT WHEN bossbills%NOTFOUND;\n" +
            "    v_count := 0;\n" +
            "    select count(id)\n" +
            "      into v_count\n" +
            "      from ismp_subscriberelationship\n" +
            "     where stbid = v_stbid\n" +
            "       and businessinfoid = v_businessinfoid;\n" +
            "    if (v_count <= 0) then\n" +
            "      select t.operationtime,\n" +
            "             t.starttime,\n" +
            "             t.endtime,\n" +
            "             t.createtime,\n" +
            "             t.lastupdatetime,\n" +
            "             t.type,\n" +
            "             t.name,\n" +
            "             t.status,\n" +
            "             t.memo,\n" +
            "             t.createuser,\n" +
            "             t.lastupdateuser\n" +
            "        into v_OPERATIONTIME,\n" +
            "             v_STARTTIME,\n" +
            "             v_ENDTIME,\n" +
            "             v_CREATETIME,\n" +
            "             v_LASTUPDATETIME,\n" +
            "             v_TYPE,\n" +
            "             v_NAME,\n" +
            "             v_STATUS,\n" +
            "             v_MEMO,\n" +
            "             v_CREATEUSER,\n" +
            "             v_LASTUPDATEUSER\n" +
            "        from SINGLE_SUBSCRIBERELATIONSHIP t\n" +
            "       where stbid = v_stbid\n" +
            "         and businessinfoid = v_businessinfoid;\n" +
            "      insert into ismp_subscriberelationship\n" +
            "        (id,\n" +
            "         stbid,\n" +
            "         businessinfoid,\n" +
            "         OPERATIONTIME,\n" +
            "         STARTTIME,\n" +
            "         ENDTIME,\n" +
            "         CREATETIME,\n" +
            "         LASTUPDATETIME,\n" +
            "         SPID,\n" +
            "         TYPE,\n" +
            "         NAME,\n" +
            "         STATUS,\n" +
            "         MEMO,\n" +
            "         CREATEUSER,\n" +
            "         LASTUPDATEUSER)\n" +
            "      values\n" +
            "        (v_stbid || '_' || v_businessinfoid,\n" +
            "         v_stbid,\n" +
            "         v_businessinfoid,\n" +
            "         v_OPERATIONTIME,\n" +
            "         v_STARTTIME,\n" +
            "         v_ENDTIME,\n" +
            "         v_CREATETIME,\n" +
            "         v_LASTUPDATETIME,\n" +
            "         '',\n" +
            "         v_TYPE,\n" +
            "         v_NAME,\n" +
            "         v_STATUS,\n" +
            "         v_MEMO,\n" +
            "         v_CREATEUSER,\n" +
            "         v_LASTUPDATEUSER);\n" +
            "    end if;\n" +
            "  end loop;\n" +
            "  commit;\n" +
            "  close bossbills;\n" +
            "  open bsmpbills;\n" +
            "  loop\n" +
            "    fetch bsmpbills\n" +
            "      into v_stbid, v_businessinfoid;\n" +
            "    EXIT WHEN bsmpbills%NOTFOUND;\n" +
            "    v_count := 0;\n" +
            "    select count(id)\n" +
            "      into v_count\n" +
            "      from SINGLE_SUBSCRIBERELATIONSHIP\n" +
            "     where stbid = v_stbid\n" +
            "       and businessinfoid = v_businessinfoid;\n" +
            "    if (v_count <= 0) then\n" +
            "      delete from ismp_subscriberelationship\n" +
            "       where stbid = v_stbid\n" +
            "         and businessinfoid = v_businessinfoid;\n" +
            "    end if;\n" +
            "  end loop;\n" +
            "  commit;\n" +
            "  close bsmpbills;\n" +
            "end P_SUBSCRIBERELATIONSHIPSYNCH;";
    public static final String SHELL_VALUE = "echo \"shell start\"\n" +
            "echo \"create or flush floder\"\n" +
            "rm -rf /opt/boss/200810\n" +
            "mkdir /opt/boss/200810\n" +
            "touch /opt/boss/200810/lock\n" +
            "echo \"download file\"\n" +
            "lftp -c \"open 59.151.15.16; user dishi 111111;user dishi 111111;lcd 200810;get 200809/A3100120080908001.md5;bye\"\n" +
            "cd 200810\n" +
            "echo \"sort and compare file\"\n" +
            "cat A3100120080908001.md5 | sed -e s/' '*$// | sort | sed -e s/\\|/' '/g   |awk '{print $1,$4,$5,$6,$7,$2,$3}' |uniq -f5 |awk '{print $1\"|\"$6\"|\"$7\"|\"$2\"|\"$3\"|\"$4\"|\"$5}' >A3100120080908001.BAK\n" +
            "rm -rf /opt/boss/200810/lock\n" +
            "echo \"all option complete\"\n" +
            "echo \"shell close\"";

    public static final String SHELL_FILE_NAME = "downloadOrder.sh";

    public static final String CLOSS_SHELL = "shell close";
}
