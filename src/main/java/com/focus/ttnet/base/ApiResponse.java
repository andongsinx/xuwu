package com.focus.ttnet.base;

/**
 * Api 格式封装
 */
public class ApiResponse {

    private int code;

    private String message;

    private Object data;

    private boolean more;


    public ApiResponse(int code, String message, Object data, boolean more) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.more = more;
    }


    public ApiResponse() {
        this.code = Status.SUCCESS.getCode();
        this.message = Status.SUCCESS.getStandardMessage();
    }

    public ApiResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static ApiResponse ofMessage(int code, String message) {
        return new ApiResponse(code, message, null);
    }

    public static ApiResponse ofSuccess(Object data) {
        return new ApiResponse(Status.SUCCESS.getCode(), Status.SUCCESS.getStandardMessage(), data);
    }

    public static ApiResponse ofStatus(Status status) {
        return new ApiResponse(status.getCode(), status.getStandardMessage(), null);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public enum Status {
        SUCCESS(200, "ok");


        private int code;

        private String standardMessage;

        Status(int code, String standardMessage) {
            this.code = code;
            this.standardMessage = standardMessage;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getStandardMessage() {
            return standardMessage;
        }

        public void setStandardMessage(String standardMessage) {
            this.standardMessage = standardMessage;
        }
    }
}
