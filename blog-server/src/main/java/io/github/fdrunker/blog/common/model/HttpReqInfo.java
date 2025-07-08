package io.github.fdrunker.blog.common.model;

import lombok.Data;

/**
 * Http请求参数
 */
@Data
public class HttpReqInfo {
    private boolean header;
    private boolean file;
    private String param;
    private String value;
    private String fileName;
    private byte[] data;

    public HttpReqInfo(String param, String value) {
        this.header = false;
        this.file = false;
        this.param = param;
        this.value = value;
    }

    public HttpReqInfo(String param, String fileName, byte[] data) {
        this.header = false;
        this.file = true;
        this.param = param;
        this.fileName = fileName;
        this.data = data;
    }

    public HttpReqInfo(String param, String value, boolean header) {
        this.header = header;
        this.file = false;
        this.param = param;
        this.value = value;
    }
}
