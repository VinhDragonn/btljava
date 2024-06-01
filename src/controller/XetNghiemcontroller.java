package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import demo.TTBenhNhanView;
import demo.XetNghiemView;
import net.sf.jasperreports.web.actions.Action;
import net.sf.jasperreports.web.actions.ActionException;

public class XetNghiemcontroller implements Action, ActionListener {
	public XetNghiemView view;
	public XetNghiemcontroller(XetNghiemView view) {
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		if(cm.equals("Thêm")) {
			
			this.view.thucHienThemThiSinh();
		}
		else if(cm.equals("Xóa")) {
			this.view.xoa();
		}
		else if(cm.equals("Cập nhật")) {
			this.view.capnhat();
		}

		}
		
	

	@Override
	public boolean requiresRefill() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void run() throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
