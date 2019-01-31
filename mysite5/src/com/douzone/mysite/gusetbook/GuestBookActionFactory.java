package com.douzone.mysite.gusetbook;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;
import com.douzone.mysite.action.user.JoinFormAction;

public class GuestBookActionFactory extends AbstractActionFactory{

	@Override
	public Action getAction(String actionName) {
			Action action =null;
			
			if(actionName==null)
				 action = new GuestBookAction();
			else if(actionName.equals("insert"))
			{
				 action=new GusetBookInsertAction();
				
			}
			else if(actionName.equals("deleteform"))
			{
				action = new GuestBookDeleteFormAction();
			}
			else if(actionName.equals("delete"))
			{
				action = new GuestBookDeleteAction();
			}
		return action;
	}

}
