package cn.ypier.mapper;

import cn.ypier.tbpojo.CustomerBean;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeMapper {
    void insert(String name);

    void insertcarttype(String name);

    void insertprojectgrade(String name);

    void inserturgentDegrees(String name);

    void insertproductGroup(String name);

    void insertProductStateDto(String name);

    void insertDevelopNatureDto(String name);

    void insertProductionDeliverDto(String name);

    void insertProductionimportTypeDto(String name);

    void insertproductLevelDto(String name);

    void insertCustomer(CustomerBean customer);

    void updateCustomer(CustomerBean customerBean);
}
