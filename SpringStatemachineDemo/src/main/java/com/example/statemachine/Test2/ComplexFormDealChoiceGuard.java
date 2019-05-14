package com.example.statemachine.Test2;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

/**
 * DEAL_CHOICE guard
 * @author wphmoon
 *
 */
public class ComplexFormDealChoiceGuard implements Guard<ComplexFormStates, ComplexFormEvents> {

	@Override
	public boolean evaluate(StateContext<ComplexFormStates, ComplexFormEvents> context) {
		System.out.println("ComplexFormDealChoiceGuard!!!!!!!!!!!!!");
		boolean returnValue = false;
		Form form = context.getMessage().getHeaders().get("form", Form.class);
		
		if ((form.formName == null)||(form.formName.indexOf("坏") > -1)) {
			returnValue = false;
		} else {
			returnValue = true;
		}
		
		System.out.println(form.toString()+" is "+returnValue);
		return returnValue;
	}

}
