/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cpst.postal.settlement.user.security.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.cpst.framework.base.UserSessionUtil;
import com.cpst.postal.settlement.user.vo.BUserVO;

@SuppressWarnings("serial")
public class DataCRUDTag extends TagSupport {

	private int show;

	public DataCRUDTag() {

	}

	public int doStartTag() throws JspException {
		super.doStartTag();

		BUserVO user = UserSessionUtil.getUser();
		if (user.getDataEntryStaffFlag() == show)
			return EVAL_PAGE;
		else
			return SKIP_BODY;
	}

	public int doEndTag() throws JspException {

		return EVAL_PAGE;
	}

	public int getShow() {
		return show;
	}

	public void setShow(int show) {
		this.show = show;
	}

}
