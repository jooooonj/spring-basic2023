package com.ll.basic1.shared.resultData;

import lombok.Getter;
@Getter
public class Result {
    private final String resultCode;
    private final String msg;
    private final Object data;

    public Result(String resultCode, String msg) {
        this(resultCode, msg, null);
    }

    public Result(String resultCode, String msg, Object data) {
        this.resultCode = resultCode;
        this.msg=msg;
        this.data=data;
    }

    public boolean isSuccess() {
       return resultCode.startsWith("S");
    }
}
