package com.psu.service;

import com.psu.entity.*;
import com.psu.repository.ClientRepository;
import com.psu.repository.EmployeeRepository;
import com.psu.repository.RoleRepository;
import com.psu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.*;
import javax.mail.internet.InternetAddress;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    private JdbcTemplate t;

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        Role role = roleRepository.findRoleById(1L);
        user.setRole(role);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean checkUsername(String username){
        return username.matches("[a-zA-Z0-9_]{2,}+");
    }

    public boolean checkFio(String fio){
        String regex = "[А-Я][а-я]{2,}\\s[А-Я][а-я]{2,}\\s[А-Я][а-я]{2,}";
        return  fio.matches(regex);
    }

    public boolean checkPassportData(String passport){
        return passport.matches("[а-яА-яa-zA-Z0-9. ]{2,}+");
    }

    public boolean checkUsernameInBD(User user){
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        return true;
    }

    public boolean saveEmployee(User user){
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        Role role = roleRepository.findRoleById(3L);
        user.setRole(role);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            User user = userRepository.findUserById(userId);
            user.setRole(null);
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public boolean isCorrectDate(String startDate, String endDate, String status){
        boolean result = true;
        String[] start = startDate.split("-");
        String[] end = endDate.split("-");
        int startYear = Integer.parseInt(start[0]);
        int startMonth = Integer.parseInt(start[1]);
        int startDay = Integer.parseInt(start[2]);

        int endYear = Integer.parseInt(end[0]);
        int endMonth = Integer.parseInt(end[1]);
        int endDay = Integer.parseInt(end[2]);

        if(status.equals("reg")) {
            if (startYear > endYear || (startYear == endYear && startMonth > endMonth) ||
                    (startYear == endYear && startMonth == endMonth && startDay >= endDay)) {
                result = false;
            }
        }else if(status.equals("graph")){
            if (startYear > endYear || (startYear == endYear && startMonth > endMonth) ||
                    (startYear == endYear && startMonth == endMonth && startDay > endDay)) {
                result = false;
            }
        }
        return result;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        grantedAuthoritySet.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grantedAuthoritySet);
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public User getUser() {
        String username = getCurrentUsername();
        CriteriaQuery<User> criteriaQuery = em.getCriteriaBuilder().createQuery(User.class);
        Root<User> userRequest = criteriaQuery.from(User.class);
        Expression<String> exp = userRequest.get("username");
        Predicate predicate = exp.in(username);
        criteriaQuery.where(predicate);
        try {
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return new User();
        }
    }

    public List<Map<String, Object>> report() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (User product : userRepository.findAll()) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("id", product.getId());
            item.put("username", product.getUsername());
            item.put("fullname", product.getFullname());
            item.put("name", product.getRole().getName());
            item.put("mail", product.getMail());
            result.add(item);
        }
        return result;
    }
}
