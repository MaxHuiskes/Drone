/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.droneproject;

import me.friwi.tello4j.api.drone.TelloDrone;
import me.friwi.tello4j.api.drone.WifiDroneFactory;
import me.friwi.tello4j.api.exception.TelloCommandTimedOutException;
import me.friwi.tello4j.api.exception.TelloCustomCommandException;
import me.friwi.tello4j.api.exception.TelloGeneralCommandException;
import me.friwi.tello4j.api.exception.TelloNetworkException;
import me.friwi.tello4j.api.exception.TelloNoValidIMUException;
import me.friwi.tello4j.api.video.TelloVideoExportType;
import me.friwi.tello4j.api.video.VideoWindow;

/**
 *
 * @author Leon
 */
public class Vluchtplan {
    
    public static void main(String[] args){
        
        try(TelloDrone drone = new WifiDroneFactory().build()){
            drone.connect();
            drone.addStateListener((o,n) ->{
            
            });
            
            drone.addVideoListener(new VideoWindow());
            drone.setVideoExportType(TelloVideoExportType.BUFFERED_IMAGE);
            drone.setStreaming(true);
            
            drone.takeoff();
            drone.forward(30);
            drone.land();
            
            while(true);
        }
        catch(TelloNoValidIMUException e){
            e.printStackTrace();
        }
        catch(TelloNetworkException e){
            e.printStackTrace();
        }
        catch(TelloCommandTimedOutException e){
            e.printStackTrace();
        }
        catch(TelloCustomCommandException e){
           e.printStackTrace();
        }
        catch(TelloGeneralCommandException e){
            e.printStackTrace();
        }
    }
    
}
