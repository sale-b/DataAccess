/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silab.nst.dan9.dataaccess.service.impl;

import silab.nst.dan9.dataaccess.domain.User;
import silab.nst.dan9.dataaccess.repository.StatsRepository;
import silab.nst.dan9.dataaccess.repository.UserRepository;
import silab.nst.dan9.dataaccess.service.UserService;

/**
 *
 * @author laptop-02
 */
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final StatsRepository statsRepository;

    public UserServiceImpl(UserRepository userRepository, StatsRepository statsRepository) {
        this.userRepository = userRepository;
        this.statsRepository = statsRepository;
    }
    
    @Override
    public User add(User user) throws Exception {
        //dodaj user u tabelu user
        //azuriraj tabelu stats tako sto ces naci red ciji je kljuc key=USER i value uvecaj za 1
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(User user) throws Exception {
        //obrisati usera iz tabele user
        //azuriraj tabelu stats tako sto ces naci red ciji je kljuc key=USER i value smanji za 1
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User update(User user) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
