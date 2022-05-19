package com.venachain.precompiled;

/**
 * Contract Info data structure for CNS Management.
 */
public class ContractInfo {
    private final String name;            // 【必填】合约名称（数字下划线英文字符，长度限制：1-128位）
    private final String version;         // 【必填】合约版本号【注：注册时版本号需要递增添加】
    private final String address;         // 【必填】合约地址

    private ContractInfo(ContractInfoBuilder builder) {
        name = builder.name;
        version = builder.version;
        address = builder.address;
    }

    public static class ContractInfoBuilder {
        private String name;
        private String version;
        private String address;

        public ContractInfoBuilder() {}

        public ContractInfo build() {
            return new ContractInfo(this);
        }

        public ContractInfoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ContractInfoBuilder version(String version) {
            this.version = version;
            return this;
        }

        public ContractInfoBuilder address(String address) {
            this.address = address;
            return this;
        }
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getAddress() {
        return address;
    }
}
