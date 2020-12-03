package br.com.phmr.springbootecomm.resources.exceptions;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msg;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Timestamp timestamp;

	public StandardError(String msg) {
		super();
		this.msg = msg;
		this.timestamp = new Timestamp(new Date().getTime());
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
