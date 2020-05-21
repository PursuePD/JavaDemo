package com.cjl.common.util.result;

/**
 * @author: 小崔
 * @create: 2020-05-15 15:37
 * @Description:
 */
public class ResultBulider {

    public static final Integer SUCCESS = 0;

    public static final Integer ERROR = -1;


    public static <T> Result<T> success() {
	return new Result<T>(SUCCESS, null, null ,true);
    }

    public static <T> Result<T> success(T data) {
	return new Result<T>(SUCCESS, null, data ,true);
    }

    public static <T> Result<T> error(Integer code) {
	return new Result<T>(code, null, null ,false);
    }

    public static <T> Result<T> error(String msg) {
	return new Result<T>(ERROR, msg, null ,false);
    }

    public static <T> Result<T> error(String msg, Object... args) {
	return new Result<T>(ERROR, format(msg, args), null ,false);
    }

    public static <T> Result<T> error(Integer code, String msg, Object... args) {
	return new Result<T>(code, format(msg, args), null ,false);
    }

    public static <T> Result<T> error(Integer code, String msg) {
	return new Result<T>(code, msg, null ,false);
    }

    public static <T> Result<T> error(Integer code, T data) {
	return new Result<T>(code, null, data ,false);
    }

    static String format(String template, Object... args) {
	template = String.valueOf(template); // null -> "null"

	// start substituting the arguments into the '%s' placeholders
	StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
	int templateStart = 0;
	int i = 0;
	while (i < args.length) {
	    int placeholderStart = template.indexOf("%s", templateStart);
	    if (placeholderStart == -1) {
		break;
	    }
	    builder.append(template.substring(templateStart, placeholderStart));
	    builder.append(args[i++]);
	    templateStart = placeholderStart + 2;
	}
	builder.append(template.substring(templateStart));

	// if we run out of placeholders, append the extra args in square braces
	if (i < args.length) {
	    builder.append(" [");
	    builder.append(args[i++]);
	    while (i < args.length) {
		builder.append(", ");
		builder.append(args[i++]);
	    }
	    builder.append(']');
	}

	return builder.toString();
    }
}
