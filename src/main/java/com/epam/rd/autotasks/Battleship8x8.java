package com.epam.rd.autotasks;

import static java.lang.Math.pow;
import static java.lang.Math.abs;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;  
    }

    public boolean shoot(String shot) {
        //determining the index of the shot        
        int row = abs((int)shot.charAt(1) - 56); 
        int column = abs((int)shot.charAt(0) - 72);
        int index = row*8 +column;
        long shotLong = (long) pow(2,index);

        if(shot.equals("A1")){
            shotLong++;
        }

        String shipsString = Long.toBinaryString(ships);
        String shotByteString = Long.toBinaryString(shotLong);

        shots = shotLong | shots;

        if(ships > 0 ) shipsString = '0' + shipsString;
        while(shipsString.length()<64){
            shipsString = shipsString +'0';
        }
        while(shotByteString.length()<64){
            shotByteString = '0' + shotByteString;
        }
        
        if(shipsString.charAt(abs(index-63)) == '1') return true;
        else return false;
    }

    public String state() {
        String state = "";
        String shotsString = Long.toBinaryString(shots);
        while(shotsString.length() < 64){
            shotsString= '0' + shotsString;
        }
        String shipsString = Long.toBinaryString(ships) ;
        if(ships > 0 ) shipsString = '0' + shipsString;
        while(shipsString.length() <= 64){
            shipsString+= '0';
        }
        int index = 1;

        for(int i = 0; i < 64; i++){
            if(shipsString.charAt(i) == '1'){
                if(shotsString.charAt(i) == '1'){
                    state+= '☒';
                }
                else{
                    state += '☐';
                }
            }
            else if(shipsString.charAt(i) == '0'){
                if(shotsString.charAt(i) == '1'){
                    state+= '×';
                }
                else{
                    state += '.';
                }
            }
            if(index % 8 ==0) state+="\n";
            index++;
        }
        return state;
    }
}
