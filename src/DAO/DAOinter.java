package DAO;

import java.util.ArrayList;

public interface DAOinter<T> {
	public int insert(T t);
	public int update(T t,String a);
	public int delete(String a);
	public T tim(String a);
	public ArrayList<T> selectall();
	public T selectByid(T t);
	public ArrayList<T> selectbyCondition (String Condition);
	public ArrayList<T> tim2(String a);
}
