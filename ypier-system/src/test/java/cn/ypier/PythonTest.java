package cn.ypier;/*
 * @Author Ypier
 */


import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class PythonTest {



//    @Autowired
//    private TbCustomerMapper customerMapper;
//    @Autowired
//    private ProductTypeMapper productTypeMapper;


//    @Autowired
//    private ProductTypeDto productTypeDto;
//    @Autowired
//    private CarTypeDto carTypeDto;
//    @Autowired
//    private ProjectGradeDto projectGradeDto;
//    @Autowired
//    private UrgentDegreesDto urgentDegreesDto;
//    @Autowired
//    private ProductGroupDto productGroupDto;
//    @Autowired
//    private ProductStateDto productStateDto;
//    @Autowired
//    private DevelopNatureDto developNatureDto;
//    @Autowired
//    private ProductionDeliverDto productionDeliverDto;
//    @Autowired
//    private ProductionimportTypeDto productionimportTypeDto;
//    @Autowired
//    private ProductLevelDto productLevelDto;


    /**
     * 量产交付表
     */
//    @Test
//    public void  productionDeliverDtoTest(){
//        List<String> names = productionDeliverDto.getName();
//        names.forEach(name->{
//            productTypeMapper.insertProductionDeliverDto(name);
//        });
//    }

    /**
     * 量产导入方式
     */
//    @Test
//    public void  productionimportTypeDtoTest(){
//        List<String> names = productionimportTypeDto.getName();
//        names.forEach(name->{
//            productTypeMapper.insertProductionimportTypeDto(name);
//        });
//    }

    //产品级别
//    @Test
//    public void  productLevelDtoTest(){
//        List<String> names = productLevelDto.getName();
//        names.forEach(name->{
//            productTypeMapper.insertproductLevelDto(name);
//        });
//    }

    //产品级别
//    @Test
//    public void  developNatureDtoTest(){
//        List<String> names = developNatureDto.getName();
//        names.forEach(name->{
//            productTypeMapper.insertDevelopNatureDto(name);
//        });
//    }


    //产品状态
//    @Test
//    public void productStateDtoTest(){
//        List<String> names = productStateDto.getName();
//        names.forEach(name->{
//            productTypeMapper.insertProductStateDto(name);
//        });
//    }



    //产品类型
//    @Test
//    public  void  productTypeTest(){
//        List<String> names = productTypeDto.getName();
//        names.forEach(s->{
//            productTypeMapper.insert(s);
//        });
//    }

    //车型类别
//    @Test
//    public  void  carTypeTest(){
//        List<String> names = carTypeDto.getName();
//        names.forEach(s->{
//            productTypeMapper.insertcarttype(s);
//        });
//    }


    //项目等级
//    @Test
//    public void projectgradesTest(){
//        List<String> names = projectGradeDto.getName();
//        names.forEach(s->{
//            productTypeMapper.insertprojectgrade(s);
//        });
//    }

    //紧急程度
//    @Test
//    public void  urgentdegreesTest(){
//        List<String> names = urgentDegreesDto.getName();
//        names.forEach(name->{
//            productTypeMapper.inserturgentDegrees(name);
//        });
//    }

//    //产品组
//    @Test
//    public void productgroupsTest(){
//        List<String> names = productGroupDto.getName();
//        names.forEach(name->{
//            productTypeMapper.insertproductGroup(name);
//        });
//    }

//    @Test
//    public void test1(){
//       List<CustomerBean> customerBeanList  = customerMapper.selectAll();
////       customerBeanList.forEach(customerBean -> {
////           customerBean.setName(customerBean.getName().replace("\r\n",""));
////           customerBean.setCompany(customerBean.getCompany().replace("\r\n",""));
////           customerMapper.updateCustomer(customerBean);
////       });
//        System.out.println(customerBeanList);
//    }



//    @Autowired
//    private CompanyDto companyDto;

//    @Test
//    public void companyDtoTest(){
//        List<Map<String, String>> companylist = companyDto.getCompanylist();
//        for (Map<String, String> company: companylist) {
//            CustomerBean customer = new CustomerBean();
//            customer.setName_whole(company.get("company"));
//            customer.setName_easy(company.get("name"));
//            productTypeMapper.insertCustomer(customer);
//        }
//    }

//    @Test
//    public void  test(){
//
//        try {
//            Process proc = Runtime.getRuntime().exec("python E:\\ypier-python\\read_excel.py");// 执行py文件
//            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//            StringBuffer stringBuffer  = new StringBuffer();
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                stringBuffer.append(line);
//            }
//            in.close();
//            proc.waitFor();
//            JSONArray objects = JSON.parseArray(stringBuffer.toString());
//            List<CustomerBean> customerBeanList =  new ArrayList<>();
//            objects.forEach(o -> {
//                Map<String,String> maps = (Map<String, String>) o;
//                maps.forEach((key,value)->{
//                    String[] strs = key.split("\n");
//                    for (String s : strs) {
//                        s = s.replace("/","").replace("\\","");
//                        if(s.contains("（") && s.contains("）")){
//                            s = s.replace("等","");
//                            String fname = s.substring(0, s.indexOf("（"));
//                            System.out.println(fname);
//                            String tname = s.substring(s.indexOf("（") + 1, s.indexOf("）"));
//                            String[] fsplits = tname.split("、");
//                            for (String fs:fsplits) {
//                                CustomerBean customerBean = new CustomerBean();
//                                String s1 = fs;
//                                customerBean.setName_easy(s1);
//                                customerBean.setNode_name_array(value);
//                                customerBeanList.add(customerBean);
//                            }
//                        }else{
//                            CustomerBean customerBean = new CustomerBean();
//                            customerBean.setName_easy(s);
//                            customerBean.setNode_name_array(value);
//                            customerBeanList.add(customerBean);
//                        }
//                    }
//                });
//            });
//            a:
//            for (CustomerBean customerBean:customerBeanList) {
//                String name_easy = customerBean.getName_easy();
//                for (Map<String,String> companyDto:companyDto.getCompanylist()) {
//                    if(companyDto.get("name").equals(name_easy)){
//                        customerBean.setName_whole(companyDto.get("company"));
//                        continue a;
//                    }
//                }
//            }
//            customerBeanList.forEach(customerBean -> {
//                if(customerBean.getName_whole() == null || customerBean.getName_whole().equals("")){
//                    log.info(customerBean.getName_easy()+"/n");
//                }
//            });
//            System.out.println(customerBeanList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
