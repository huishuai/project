package com.dvn.miniboss.ca

/**
 * ָ��ģ���
 * ��ע������������crm��Ҳ���ڣ��޸ĸ���ʱ��ע��ͬʱ�޸�����crm��
 */
class CaTemplate implements Serializable {
    //ָ��ģ��ID����
    String id
    //ָ��ģ������
    String name
    //״̬:1��nullΪ���ã� 0Ϊ����
    String status

    //ģ��״̬
    public static final String STATUS_RUNNING = "1"   //����
    public static final String STATUS_STOPPED = "0"   //����

    public static final String SPECIALUSER = "1000" //�����û���
    public static final String VILLAGE = "1001" //ũ���û�
    public static final String CITY = "1002" //�����û�
    public static final String VILLAGE_SPECIALUSER = "1003" //ũ�������û���
    public static final String CITY_SPECIALUSER = "1004" //���������û���

    static constraints = {
        id(size: 0..20, nullable: false)
        name(size: 0..100, nullable: true)
        status(size: 1..1, blank: false)
    }

    static mapping = {
        table "ca_template"
        version false
        id generator: 'assigned', column: 'ID'
        cache false
    }
}
