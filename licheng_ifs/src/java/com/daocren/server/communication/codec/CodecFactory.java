package com.daocren.server.communication.codec;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

/**
 * ��/���빤���࣬������Ӧ����ˣ��ͻ���
 *
 * @author Daocren
 */
public class CodecFactory extends DemuxingProtocolCodecFactory {
    /**
     * ���췽��
     *
     * @param server ָ���Ƿ�Ϊ�������˱���빤��
     */
    public CodecFactory(boolean server) {
        super.register(CommonEncoder.class);
        super.register(CommonDecoder.class);
        super.register(KeepAliveEncoder.class);
        super.register(KeepAliveDecoder.class);

        if (server) {
            super.register(BindReqDecoder.class);
        } else {
            super.register(BindReqEncoder.class);
        }
    }
}
