/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Stock.Stock;

/**
 *
 * @author trongvo
 */
public class Main {
    public static void main(String [ ] args){
        Stock st = new Stock();
        
        st.lireElement();
        
        st.afficheStock();
    }
    
}
