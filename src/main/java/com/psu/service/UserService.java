package com.psu.service;

import com.psu.entity.*;
import com.psu.repository.ClientRepository;
import com.psu.repository.EmployeeRepository;
import com.psu.repository.RoleRepository;
import com.psu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return (List<User>) userRepository.findAll();
    }

    public void saveUser(User user) {

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
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

        user.setRoles(Collections.singleton(new Role(3L, "ROLE_EMPLOYEE")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {

        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
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
    public boolean isCorrectDate(String startDate, String endDate, String status){//(2020-12-28)
        System.out.println(startDate + "--=-=-==-= " + endDate);

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

        for(Role role : user.getRoles())
            grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getName()));

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

}
