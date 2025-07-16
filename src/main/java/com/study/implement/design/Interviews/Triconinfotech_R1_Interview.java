//package com.design.patter.implementation;
//
//@RestController
//public class Controller {
//
//    @Autowired
//    private final ServiceClass serviceClass;
//
//    public Controller(ServiceClass serviceClass) {
//        this.serviceClass = serviceClass;
//    }
//
//    @PostMapping("/student/save")
//    public ResponseEntity<ResponseObject> responseObjectResponseEntity(@RequestBody Student student){
//        serviceClass.save(student);
//        return new ResponseEntity
//    }
//}
//
//HashMap<K,V>
//
//
//Node <- value
//
//ConcurrentHashMap
//
//
//applications.properties
//server.port = 8080
//application.yml
//server:
//    port:8082
//
//List<Object> objectList = functionCall();
//
//resultList = objectList.stream()
//.filter(function - boolean).map(take out some value).collect(Collectors.toList);
//
////
//
////second highest salary in entire org
//
//select salary
//from org
//order by salary desc
//limit 1 offset 1;
//
////second highest salary in org for each department
//
//select salary, department
//from org
//        where salary < (select salary from )
//group by department
//order by salary desc
//
//
//
//
