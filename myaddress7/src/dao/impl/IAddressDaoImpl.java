package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IAddressDao;
import util.DaoSupport;
import vo.Address;

public class IAddressDaoImpl extends DaoSupport implements IAddressDao{

	@Override
	public List<Address> findAll() throws Exception {
		List<Address> list=new ArrayList<Address>();
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from myaddress";
		
		try {
			pst=getPrepareSta(sql);
			rs=pst.executeQuery();
		
		while (rs.next()) {
			Address address=new Address();
			address.setId(rs.getLong("addrid"));
			address.setUsername(rs.getString("username"));
			address.setEmail(rs.getString("email"));
			list.add(address);
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			free(pst,rs);
		}
		
		
		return list;
	}

	@Override
	public void add(Address address) throws Exception {
	
		PreparedStatement pst=null;
		String sql="insert into myaddress(addrid,username,email) values(addr_seq.nextval,?,?)";
		
		try {
			pst=getPrepareSta(sql);
			pst.setString(1, address.getUsername());
			pst.setString(2,address.getEmail());
			
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			free(pst,null);
		}
	     }

	@Override
	public List<Address> findBypage(Address address) throws Exception {
		List<Address> list=new ArrayList<Address>();
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from (select rownum rn,myaddress.* from myaddress order by addrid) where rn between ? and ?";
		pst=getPrepareSta(sql);
		pst.setLong(1, (address.getCurrentPage()-1)*address.getPageSize()+1);
		pst.setLong(2, address.getCurrentPage()*address.getPageSize());
		try{
		rs=pst.executeQuery();
		while(rs.next()){
			//new vo
			Address address2= new Address();
			address2.setId(rs.getLong("addrid"));
			address2.setUsername(rs.getString("username"));
			address2.setEmail(rs.getString("email"));
			list.add(address2);
		}
		}catch(Exception e){
			throw e;
		}finally{
			//调用jdbc的关闭方法
			free(pst,rs);
		}
		
		return list;
	}

	@Override
	public Long count(Address address) throws Exception {
		String sql="select count(*) from myaddress";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			//语句对象执行sql
			pst = getPrepareSta(sql);
	
			rs = pst.executeQuery();
			
			if(rs.next()){
				return rs.getLong(1);
			}
		}catch(Exception e){
			throw e;
		}finally{
			//调用jdbc的关闭方法
			free(pst,rs);
		}
		return 0L;
	}
	
	
	
	

}
