package com.douzone.mvc.action.board;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;


public class BoardActionFactory extends AbstractActionFactory{

	@Override
	public Action getAction(String actionName) {
			Action action = null;
		
		if("write".equals(actionName)) {
			action = new BoardWriteAction();
		} else if("writeForm".equals(actionName)) {
			action = new BoardWriteFormAction();
		} else if("delete".equals(actionName))
		{
			action = new BoardDeleteAction();
		}
		else if("view".equals(actionName)) {
			action = new BoardViewFormAction();
		}else if("modifyForm".equals(actionName))
		{
			action = new ModifyFormAction();
		}else if("modify".equals(actionName)) {
			action = new ModifyAction();
		}else if("replyForm".equals(actionName)) {
			action = new ReplyFormAction();
		}else if("reply".equals(actionName))
		{
			action = new ReplyAction();
		}else if("search".equals(actionName))
		{
			action = new SearchAction();
		}
		else if("select".equals(actionName))
		{
			action = new BoardFormAction();
		}else if("comment".equals(actionName))
		{
			action = new CommentInputAction();
		}else if("commentdelete".equals(actionName)) {
			action = new CommentDeleteAction();
		}
		else {
			action = new BoardFormAction();
		}
		
		return action;
	}


}
