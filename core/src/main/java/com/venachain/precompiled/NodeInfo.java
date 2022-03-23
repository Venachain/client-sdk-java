package com.venachain.precompiled;

import java.math.BigInteger;

/**
 * Node Info data structure for Node Management.
 */
public class NodeInfo {
    private final String name;          //[必填] 全网唯一，不能重复。所有接口均以此为主键。
    private final Integer status;        //[必填] 1:正常；2：删除
    private final String publicKey;     //[必填] 节点公钥，全网唯一，不能重复
    private final Integer p2pPort;       //[必填] p2p 通讯端口

    private final String externalIP;    //[必填] 外网 IP
    private final String internalIP;    //[必填] 内网 IP

    private final String owner;         //[可选] 申请者的地址
    private final String desc;          //[可选] 【注意：最大长度100个字符, 任意字符串】节点描述
    private final Integer type;          //[必填] 1:共识节点; 2:观察者节点；3：轻节点
    private final Integer rpcPort;       //[可选] rpc 通讯端口
    private final BigInteger delayNum;      //[可选] 共识节点延迟设置的区块高度 (可选, 默认实时设置)

    private NodeInfo(NodeInfoBuilder builder) {
        this.name = builder.name;
        this.status = builder.status;
        this.publicKey = builder.publicKey;
        this.p2pPort = builder.p2pPort;
        this.externalIP = builder.externalIP;
        this.internalIP = builder.internalIP;

        this.owner = builder.owner;
        this.desc = builder.desc;
        this.type = builder.type;
        this.rpcPort = builder.rpcPort;
        this.delayNum = builder.delayNum;
    }

    public static class NodeInfoBuilder {
        private String name;
        private Integer status;
        private String publicKey;
        private Integer p2pPort;

        private String externalIP;
        private String internalIP;

        private String owner;
        private String desc;
        private Integer type;
        private Integer rpcPort;
        private BigInteger delayNum;

        public NodeInfoBuilder() {}

        public NodeInfoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public NodeInfoBuilder publicKey(String publicKey) {
            this.publicKey = publicKey;
            return this;
        }

        public NodeInfoBuilder status(Integer status) {
            this.status = status;
            return this;
        }

        public NodeInfoBuilder p2pPort(Integer p2pPort) {
            this.p2pPort = p2pPort;
            return this;
        }

        public NodeInfoBuilder externalIP(String externalIP) {
            this.externalIP = externalIP;
            return this;
        }

        public NodeInfoBuilder internalIP(String internalIP) {
            this.internalIP = internalIP;
            return this;
        }

        public NodeInfoBuilder owner(String owner) {
            this.owner = owner;
            return this;
        }

        public NodeInfoBuilder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public NodeInfoBuilder type(Integer type) {
            this.type = type;
            return this;
        }

        public NodeInfoBuilder rpcPort(Integer rpcPort) {
            this.rpcPort = rpcPort;
            return this;
        }

        public NodeInfoBuilder delayNum(BigInteger delayNum) {
            this.delayNum = delayNum;
            return this;
        }

        public NodeInfo build() {
            return new NodeInfo(this);
        }
    }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public Integer getP2pPort() {
        return p2pPort;
    }

    public String getExternalIP() {
        return externalIP;
    }

    public String getInternalIP() {
        return internalIP;
    }

    public String getOwner() {
        return owner;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getType() {
        return type;
    }

    public Integer getRpcPort() {
        return rpcPort;
    }

    public BigInteger getDelayNum() {
        return delayNum;
    }
}