package com.psu.service;

import com.psu.entity.*;
import com.psu.entity.ObjectExcursion;
import com.psu.enums.ObjectsName;
import com.psu.object.ListObjectExcursion;
import com.psu.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ExcursionRepository excursionRepository;
    @Autowired
    private ObjectExcurtionRepository objectExcurtionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ViewExcursionRepository viewExcursionRepository;
    @Autowired
    private JdbcTemplate t;

    public List<Client> allClients() {
        return (List<Client>) clientRepository.findAll();
    }

    public List<Excursion> allExcursion() {
        return (List<Excursion>) excursionRepository.findAll();
    }

    public void saveClient(Client client){
        clientRepository.save(client);
    }

    public Client getClient(User user){
        return clientRepository.findByUser(user);
    }

    public void deleteClient(Client client){
        client.getUser().setRole(null);
        clientRepository.delete(client);
    }

    public void insertExcursion(ListObjectExcursion listObjectExcursion, ViewExcursion viewExcursion){

        Excursion excursion = new Excursion();
        excursion.setName(listObjectExcursion.getName());
        Set<ObjectExcursion> objectExcursions = new HashSet<>();

        for(String o : listObjectExcursion.getObjects()){
            ObjectExcursion objectExcursion = objectExcurtionRepository.findObjectExcursionByName(o);
            objectExcursions.add(objectExcursion);
        }

        excursion.setObjectExcursion(objectExcursions);

        int resTimeDuration = 0;
        excursion.setPrice(0L);
        excursion.setDescription("");

        if(viewExcursion.getTypeName().equals("simple")){
            viewExcursion.setTypeName("обычные");
        }
        else if(viewExcursion.getTypeName().equals("study")){
            viewExcursion.setTypeName("учебные");
        }
        else if(viewExcursion.getTypeName().equals("many")){
            viewExcursion.setTypeName("массовые");
        }
        viewExcursion = viewExcursionRepository.findViewExcursionByTypeName(viewExcursion.getTypeName());

        for(ObjectExcursion object : excursion.getObjectExcursion()){

            if(object.getName().equals(ObjectsName.VR.name())){
                resTimeDuration += getTimeAndSetExcursionObject(excursion,"Программа VR", 1L,object);

            }else if(object.getName().equals(ObjectsName.Graphic.name())){
                resTimeDuration += getTimeAndSetExcursionObject(excursion,"Программа неисправности", 2L,object);

            }else if(object.getName().equals(ObjectsName.Labirinth.name())){
                resTimeDuration += getTimeAndSetExcursionObject(excursion,"Программа Лабиринт", 3L,object);

            }else if(object.getName().equals(ObjectsName.Storm.name())){
                resTimeDuration += getTimeAndSetExcursionObject(excursion,"Программа гроза", 4L,object);

            }else if(object.getName().equals(ObjectsName.Numbers.name())){
                resTimeDuration += getTimeAndSetExcursionObject(excursion,"Программа SOS", 5L, object);
            }
        }
        String resTime = "";

        if(resTimeDuration == 60)
            resTime = "1 час";
        else if(resTimeDuration == 120)
            resTime = "2 часа";
        else if(resTimeDuration > 60 && resTimeDuration < 120){
            resTimeDuration -= 60;
            if(resTimeDuration > 0 && resTimeDuration < 10){
                resTime = 1 + ":" + "0" + resTimeDuration;
            }
            else resTime = 1 + ":" + resTimeDuration;
        }
        else if(resTimeDuration >= 120 && resTimeDuration < 180){
            resTimeDuration -= 120;
            if(resTimeDuration > 0 && resTimeDuration < 10){
                resTime = 2 + ":" + "0" + resTimeDuration;
            }
            else resTime = 2 + ":" + resTimeDuration;
        }
        else
            resTime = resTimeDuration + " минут";

        excursion.setTime(resTime);
        excursion.setViewExcursion(viewExcursion);
        String resultDescription = excursion.getDescription();//убираем запятую в конце описания
        resultDescription = resultDescription.substring(0,resultDescription.length() - 1);
        excursion.setDescription(resultDescription);

        Client client = getClient(userService.getUser());
        t.update("call insertExcursion(?, ?, ?, ?,?,?)", excursion.getName(), excursion.getDescription(), excursion.getPrice(),
                excursion.getTime(),
                client.getId() ,
                excursion.getViewExcursion().getId());

    }

    public boolean checkNameExcursion(String nameExc){

        List<Excursion> excursions = excursionRepository.findAll();
        for(Excursion excursion : excursions){
            if(excursion.getName().equals(nameExc)){
                return false;
            }
        }

        return true;
    }

    public int getTimeAndSetExcursionObject(Excursion excursion, String name, Long id, ObjectExcursion objectExcursion){
        String getDescript = excursion.getDescription();
        Long getPrice = excursion.getPrice();
        excursion.setDescription(getDescript + " " + name + ",");
        excursion.setPrice(getPrice + objectExcursion.getPrice());
        return Integer.parseInt(objectExcursion.getTimeDuration());

    }

    public List<String> getAllExcName(){
        List<String> names = new LinkedList<>();

        for(Excursion ex : excursionRepository.findAll()){
            names.add(ex.getName());
        }

        return names;
    }

    public boolean deleteExcursion(Long id) {
        Excursion delExc = excursionRepository.findExcursionById(id);
        Long clientId = 0L;
        try {
            clientId = delExc.getClient().getUser().getId();
        }catch (NullPointerException nu) {
            return false;
        }

        if (userService.getUser().getId().equals(clientId)){
            List<Order> order = orderRepository.findAllByExcursion(delExc);
            delExc.setClient(null);
            delExc.setViewExcursion(null);
            for(Order or : order){
                or.setUserGet(null);
                or.setUserOrder(null);
                orderRepository.delete(or);
            }
            excursionRepository.delete(delExc);
            return true;
        }
        return false;
    }

    public boolean cancelOrder(Long id){
        List<Order> orders = orderService.getNotMyOrder();
        try {
            for(Order o : orders){
                if(o.getId().equals(id)){
                    o.setUserGet(null);
                    o.setUserOrder(null);
                    orderRepository.delete(o);
                    return true;
                }
            }
        }catch (NullPointerException nu) {
        }
        return false;
    }
    /*public class objectMapper implements RowMapper<Excursion> {
        @Override
        public Excursion mapRow(ResultSet resultSet, int i) throws SQLException {
            Excursion excursion = new Excursion();
            excursion.setName(resultSet.getString("name"));

            Order order = new Order();
            order.setExcursion(excursion);
            order.setCompleteOrNot(resultSet.getString("complete_or_not"));

            GraphicEmployee graphicEmployee = new GraphicEmployee();
            graphicEmployee.setId(resultSet.getLong("id"));
            graphicEmployee.setDateStart(resultSet.getString("date_start"));
            graphicEmployee.setTimeStart(resultSet.getString("time_start"));
            graphicEmployee.setTimeEnd(resultSet.getString("time_end"));
            graphicEmployee.setOrder(order);

            return graphicEmployee;
        }
    }*/
}
