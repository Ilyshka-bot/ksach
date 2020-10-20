package com.psu.service;

import com.psu.entity.*;
import com.psu.repository.OrderRepository;
import com.psu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService {



    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JdbcTemplate t;

    public void saveOrder(ViewExcursion viewExcursion){
        List<Excursion> allExc = clientService.allExcursion();
        Order order = new Order();

        if(viewExcursion.getName().equals("simple")) viewExcursion.setName("обычные");
        else if(viewExcursion.getName().equals("study")) viewExcursion.setName("учебные");
        else if(viewExcursion.getName().equals("many")) viewExcursion.setName("массовые");

        for(Excursion ex: allExc){
            if(ex.getViewExcursion().getName().equals(viewExcursion.getName())){
                order.setExcursion(ex);
                break;
            }
        }
        order.setUserOrder(userService.getUser());

        order.setCompleteOrNot("not complete");

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        order.setDateOrder(formatForDateNow.format(dateNow));

        orderRepository.save(order);
    }

    public List<Order> getMyOrders(){
        List<Order> orders = new LinkedList<>();
        String ORDER_QUERY = "select o.id ,o.date_order,e.name, u.fullname, o.complete_or_not " +
                "from \"t_order\" as o, \"t_excursion\" as e, \"t_user\" as u " +
                "where o.user_order_id = ? and o.excursion_id = e.id and " +
                "o.user_get_id = u.id";
        User me = userService.getUser();
        try {
            orders = t.query(ORDER_QUERY,new OrderMapper(),me.getId());
        } catch (DataAccessException e) {
            System.out.println("All employee's are busy, wait!");
        } catch (NumberFormatException e) {
        }
        return orders;
    }

    public List<Order> getNotMyOrder(){
        List<Order> orders = new LinkedList<>();
        String ORDER_QUERY = "select o.id ,o.date_order,e.name " +
                "from \"t_order\" as o, \"t_excursion\" as e " +
                "where o.user_get_id is null and o.user_order_id = ? and o.excursion_id = e.id " +
                "order by o.id;";
        User me = userService.getUser();
        try {
            orders = t.query(ORDER_QUERY,new NotOrderMapper(),me.getId());
        } catch (DataAccessException e) {
            System.out.println("All employee's are busy, wait!");
        } catch (NumberFormatException e) {
        }
        return orders;
    }

    public List<Order> getNotCompleteOrder(){
        List<Order> orders = new LinkedList<>();
        String ORDER_QUERY = "select o.id, u.username, e.name " +
                "from t_order as o, t_user as u, t_excursion as e " +
                "where o.complete_or_not = 'not complete' and o.user_order_id = u.id " +
                "and o.excursion_id = e.id and o.user_get_id is Null";

        try {
            orders = t.query(ORDER_QUERY,new OrderMapperNotComplete());
        } catch (DataAccessException e) {
            System.out.println("All employee's are busy, wait!");
        } catch (NumberFormatException e) {
        }

        return orders;
    }

    public void employeeGetOrder(Long order_id){
        String ORDER_QUERY = "UPDATE public.t_order" +
                " SET user_get_id = ?" +
                " WHERE id = ?;";

        t.update(ORDER_QUERY,userService.getUser().getId(),order_id);
    }
    public void employeeCompleteOrder(Long order_id){
        String ORDER_QUERY = "UPDATE public.t_order" +
                " SET complete_or_not = 'complete'" +
                " WHERE id = ?;";

        t.update(ORDER_QUERY,order_id);
    }

    public List<Order> getNotCompleteOrdersEmployee(){

        String ORDER_QUERY = "select o.id, u.username, e.name, o.date_order, o.complete_or_not " +
                "from t_order as o, t_user as u, t_excursion as e " +
                "where o.user_get_id = u.id and o.excursion_id = e.id and u.id = ? and o.complete_or_not = 'not complete'";
        List<Order> orders = new LinkedList<>();

        try {
            orders = t.query(ORDER_QUERY,new OrderMapperNotCompleteEmployee(),userService.getUser().getId());
        } catch (DataAccessException e) {
            System.out.println("All employee's are busy, wait!");
        } catch (NumberFormatException e) {
        }

        return orders;
    }
    public List<Order> getCompleteOrdersEmployee(){

        String ORDER_QUERY = "select o.id, o.complete_or_not, u.username, e.name " +
                "from t_order as o, t_user as u, t_excursion as e " +
                "where o.user_get_id = ? and o.excursion_id = e.id and u.id = o.user_order_id and " +
                "o.complete_or_not = 'complete'";
        List<Order> orders = new LinkedList<>();

        try {
            orders = t.query(ORDER_QUERY,new OrderMapperCompleteEmployee(),userService.getUser().getId());
        } catch (DataAccessException e) {
            System.out.println("All employee's are busy, wait!");
        } catch (NumberFormatException e) {
        }

        return orders;
    }


    public class OrderMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            Excursion excursion = new Excursion();
            excursion.setName(resultSet.getString("name"));

            User userGet = new User();
            userGet.setFullname(resultSet.getString("fullname"));

            Order order = new Order();
            order.setId(resultSet.getLong("id"));
            order.setDateOrder(resultSet.getString("date_order"));
            order.setCompleteOrNot(resultSet.getString("complete_or_not"));
            order.setUserGet(userGet);
            order.setExcursion(excursion);

            return order;
        }
    }
    public class NotOrderMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            Excursion excursion = new Excursion();
            excursion.setName(resultSet.getString("name"));

            Order order = new Order();
            order.setId(resultSet.getLong("id"));
            order.setDateOrder(resultSet.getString("date_order"));
            order.setExcursion(excursion);

            return order;
        }
    }

    public class OrderMapperNotComplete implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            Excursion excursion = new Excursion();
            excursion.setName(resultSet.getString("name"));

            User userGet = new User();
            userGet.setUsername(resultSet.getString("username"));

            Order order = new Order();
            order.setId(resultSet.getLong("id"));
            order.setUserGet(userGet);
            order.setExcursion(excursion);

            return order;
        }
    }
    public class OrderMapperNotCompleteEmployee implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            Excursion excursion = new Excursion();
            excursion.setName(resultSet.getString("name"));

            User userGet = new User();
            userGet.setUsername(resultSet.getString("username"));

            Order order = new Order();
            order.setId(resultSet.getLong("id"));
            order.setDateOrder(resultSet.getString("date_order"));
            order.setCompleteOrNot(resultSet.getString("complete_or_not"));
            order.setUserGet(userGet);
            order.setExcursion(excursion);

            return order;
        }
    }
        public class OrderMapperCompleteEmployee implements RowMapper<Order> {
            @Override
            public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                Excursion excursion = new Excursion();
                excursion.setName(resultSet.getString("name"));

                User userOrder = new User();
                userOrder.setUsername(resultSet.getString("username"));

                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setCompleteOrNot(resultSet.getString("complete_or_not"));
                order.setUserOrder(userOrder);
                order.setExcursion(excursion);

                return order;
            }
        }
}
