package com.daocren.server.communication.codec;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

/**
 * 编/解码工厂类，可以适应服务端，客户端
 *
 * @author Daocren
 */
public class CodecFactory extends DemuxingProtocolCodecFactory {
    /**
     * 构造方法
     *
     * @param server 指定是否为服务器端编解码工厂
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
