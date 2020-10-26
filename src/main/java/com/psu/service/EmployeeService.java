package com.psu.service;

import com.psu.entity.*;
import com.psu.enums.ExcursionsName;
import com.psu.repository.EmployeeRepository;
import com.psu.repository.GraphicRepository;
import com.psu.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private GraphicRepository graphicRepository;
    @Autowired
    private JdbcTemplate t;

    public void saveEmployee(Employee employee, User user){
        userService.saveEmployee(user);
        employee.setUser(user);

        employeeRepository.save(employee);
    }

    public Employee getEmployee(User user){
        return employeeRepository.findByUser(user);
    }

    public void deleteEmployee(Employee employee){
        employeeRepository.delete(employee);
    }

    public void sendOrderToGraphic(Long order_id){
        User meEmployee = userService.getUser();
        Employee employee = employeeRepository.findByUser(meEmployee);

        String GRAPHIC_QUERY = "INSERT INTO public.t_graphic_employee(" +
                "date_start, time_end, time_start, employee_id, order_id) values ('','','', ?, ?);";

        t.update(GRAPHIC_QUERY, employee.getUser().getId(),order_id);
    }
    public void sendTimeToGraphic(String dateStart, String timeStart, Long graphicId){
        GraphicEmployee graphicEmployee = graphicRepository.findGraphicEmployeeById(graphicId);
        String excursionName = graphicEmployee.getOrder().getExcursion().getName();

        if(!dateStart.equals("")){
            String GRAPHIC_QUERY = "UPDATE \"t_graphic_employee\" " +
                    "SET date_start = ? " +
                    "WHERE id = ?;";

            t.update(GRAPHIC_QUERY,dateStart, graphicId);

            if(!timeStart.equals("")){
                GRAPHIC_QUERY = "UPDATE \"t_graphic_employee\" " +
                        "SET time_start = ? " +
                        "WHERE id = ?;";

                t.update(GRAPHIC_QUERY,timeStart,graphicId);

                String timeEnd = getTimeEnd(timeStart, excursionName);
                GRAPHIC_QUERY = "UPDATE \"t_graphic_employee\" " +
                        "SET time_end = ? " +
                        "WHERE id = ?;";

                t.update(GRAPHIC_QUERY,timeEnd,graphicId);

            }
        }

    }

    public String getTimeEnd(String timeStart, String excursionName){
        String timeEnd = "";
        String massTimeDur[] = timeStart.split(":");
        int hour = Integer.parseInt(massTimeDur[0]);
        int minute = Integer.parseInt(massTimeDur[1]);
        System.out.println(hour + " =-=-=-==" + minute);

        if(excursionName.equals(ExcursionsName.экскурсия1.name())){
            hour += 1;
        }else if(excursionName.equals(ExcursionsName.экскурсия2.name())){
            hour += 1;
            minute += 30;
            if(minute > 59){
                hour += 1;
                minute -= 60;
            }
        }else if(excursionName.equals(ExcursionsName.экскурсия3.name())){
            hour += 2;
        }
        String minutes = String.valueOf(minute);
        if(minute >= 0 && minute < 10){
            minutes = "0" + minute;
        }

        timeEnd = hour + ":" + minutes;

        return timeEnd;
    }

    public boolean checkTimeStart(String timeStart){
        if(timeStart.equals("")) return false;
        String massTimeStart[] = timeStart.split(":");
        if(Integer.parseInt(massTimeStart[0]) < 9 || Integer.parseInt(massTimeStart[0]) > 20)
            return false;
        return true;
    }

    public boolean checkDateStart(String dateStart){
        if(dateStart.equals("")) return false;
        int currentMonth = getCurrentMonth() + 1;

        String currentDate = getCurrentYear() + "-" + currentMonth + "-" + getCurrentDay();
        if(!userService.isCorrectDate(currentDate, dateStart,"graph")){
            return false;
        }
        return true;
    }

    public static int getCurrentYear()
    {
        java.util.Calendar calendar = java.util.Calendar.getInstance(java.util.TimeZone.getDefault(), java.util.Locale.getDefault());
        calendar.setTime(new java.util.Date());
        return calendar.get(java.util.Calendar.YEAR);
    }
    public static int getCurrentMonth()
    {
        java.util.Calendar calendar = java.util.Calendar.getInstance(java.util.TimeZone.getDefault(), java.util.Locale.getDefault());
        calendar.setTime(new java.util.Date());
        return calendar.get(Calendar.MONTH);
    }
    public static int getCurrentDay() {
        java.util.Calendar calendar = java.util.Calendar.getInstance(java.util.TimeZone.getDefault(), java.util.Locale.getDefault());
        calendar.setTime(new java.util.Date());
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    public List<GraphicEmployee> getGraphic(){
        List<GraphicEmployee> graphics = new LinkedList<>();
        String GRAPHIC_QUERY = "SELECT g.id, e.name, o.complete_or_not, g.date_start, g.time_start, g.time_end " +
        "FROM t_graphic_employee as g,t_excursion as e, t_order as o " +
        "where g.employee_id = ? and g.order_id = o.id and o.excursion_id = e.id " +
                "order by g.id;";

        try {
            graphics = t.query(GRAPHIC_QUERY,new graphicMapper(),userService.getUser().getId());
        } catch (DataAccessException e) {
            System.out.println("All employee's are busy, wait!");
        } catch (NumberFormatException e) {
        }

        return graphics;
    }

    public class graphicMapper implements RowMapper<GraphicEmployee> {
        @Override
        public GraphicEmployee mapRow(ResultSet resultSet, int i) throws SQLException {
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
    }
}
