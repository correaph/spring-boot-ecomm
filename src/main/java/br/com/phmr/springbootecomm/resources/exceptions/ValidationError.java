package br.com.phmr.springbootecomm.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	public ValidationError(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = -3153989498984130898L;

	private List<FieldMessage> errors = new ArrayList<>();

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String fieldMessage) {
		errors.add(new FieldMessage(fieldName, fieldMessage));
	}

}
