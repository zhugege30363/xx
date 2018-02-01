package dao;

import java.util.List;

import vo.Address;

public interface IAddressDao {
   public List<Address> findAll() throws Exception;
   public void add(Address address) throws Exception;
   
   public List<Address> findBypage(Address address) throws Exception;
   public Long count(Address address) throws Exception;
   
}
