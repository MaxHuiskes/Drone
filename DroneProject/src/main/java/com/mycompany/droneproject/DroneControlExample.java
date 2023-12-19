package com.mycompany.droneproject;

import me.friwi.tello4j.api.drone.TelloDrone;
import me.friwi.tello4j.api.drone.WifiDroneFactory;
import me.friwi.tello4j.api.exception.*;
import me.friwi.tello4j.api.video.VideoWindow;
import me.friwi.tello4j.api.world.FlipDirection;
import me.friwi.tello4j.api.video.TelloVideoExportType;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class DroneControlExample extends JFrame {

    private DroneController droneController;
    private JTextArea textArea;

    public DroneControlExample() {
        setTitle("Drone Control Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.addKeyListener(new MyKeyListener());

        add(textArea);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DroneControlExample example = new DroneControlExample();
            example.startDroneControl();
        });


    }

    private void startDroneControl() {
        try {
            // Initialize and connect the drone
            TelloDrone drone = new WifiDroneFactory().build();
            drone.connect();
            drone.addStateListener((o, n) -> {
                System.out.println(n.getBattery());
            });

            // Create the drone controller with the connected drone
            droneController = new DroneController(drone);
            drone.addVideoListener(new VideoWindow());
            drone.setVideoExportType(TelloVideoExportType.BUFFERED_IMAGE);
            drone.setStreaming(true);

            // Start drone control in a separate thread
//             Thread droneControlThread = new Thread(() -> {
//                 try {
//                     // droneController.takeOff();
// //                    TimeUnit.SECONDS.sleep(2);
// //                    droneController.moveForward();
// //                    TimeUnit.SECONDS.sleep(2);
// //                    droneController.land();
//                 } catch (TelloNetworkException |
//                          TelloCommandTimedOutException | TelloCustomCommandException |
//                          TelloGeneralCommandException e) {
//                     e.printStackTrace();
//                 }
//             });
            // droneControlThread.start();

        } catch (TelloNetworkException | TelloCommandTimedOutException | TelloCustomCommandException |
                 TelloGeneralCommandException e) {
            e.printStackTrace();
        }
    }

    private class MyKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            char keyChar = e.getKeyChar();
            System.out.println("Key Typed: " + keyChar);

            // Example: Execute a command based on the key typed
            if (keyChar == 't') {
                try {
                    droneController.takeOff();
                    textArea.setText("");
                } catch (TelloNetworkException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCommandTimedOutException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCustomCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloGeneralCommandException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (keyChar == 'l') {
                try {
                    droneController.land();
                    textArea.setText("");
                } catch (TelloNetworkException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCommandTimedOutException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCustomCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloGeneralCommandException ex) {
                    throw new RuntimeException(ex);
                }
            }
             else if (keyChar == 'w') {
                try {
                    droneController.upMove();
                    textArea.setText("");
                } catch (TelloNetworkException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCommandTimedOutException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCustomCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloGeneralCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloNoValidIMUException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            else if (keyChar == 's') {
                try {
                    droneController.moveDown();
                    textArea.setText("");
                } catch (TelloNetworkException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCommandTimedOutException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCustomCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloGeneralCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloNoValidIMUException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            else if (keyChar == 'f') {
                try {
                    droneController.nflip();
                    textArea.setText("");
                } catch (TelloNetworkException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCommandTimedOutException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCustomCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloGeneralCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloNoValidIMUException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            System.out.println("Key Pressed: " + KeyEvent.getKeyText(keyCode));

            // Example: Execute a command based on the key pressed
            if (keyCode == KeyEvent.VK_UP) {
                try {
                    droneController.moveForward();
                } catch (TelloNetworkException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCommandTimedOutException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCustomCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloGeneralCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloNoValidIMUException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (keyCode == KeyEvent.VK_DOWN) {
                try {
                    droneController.moveBackward();
                } catch (TelloNetworkException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCommandTimedOutException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCustomCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloGeneralCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloNoValidIMUException ex) {
                    throw new RuntimeException(ex);
                }
            } else if(keyCode == KeyEvent.VK_LEFT){
                try {
                    droneController.turnLeft();
                } catch (TelloNetworkException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCommandTimedOutException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCustomCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloGeneralCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloNoValidIMUException ex) {
                    throw new RuntimeException(ex);
                }
            }else if(keyCode == KeyEvent.VK_RIGHT){
                try {
                    droneController.turnRight();
                } catch (TelloNetworkException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCommandTimedOutException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloCustomCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloGeneralCommandException ex) {
                    throw new RuntimeException(ex);
                } catch (TelloNoValidIMUException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            System.out.println("Key Released: " + KeyEvent.getKeyText(keyCode));

            // Example: Execute a command based on the key released
            if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
                droneController.stopHorizontalMovement();
            }
        }
    }

    private class DroneController {
        private TelloDrone drone;

        public DroneController(TelloDrone drone) {
            this.drone = drone;
        }

        // Example methods for controlling the drone
        public void takeOff() throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException {
            System.out.println("Drone taking off");
            drone.takeoff();
        }

        public void land() throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException {
            System.out.println("Drone landing");
            drone.land();
        }

        public void moveForward() throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException, TelloNoValidIMUException {
            System.out.println("Drone moving forward");
            drone.forward(30);
        }

        public void nflip() throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException, TelloNoValidIMUException {
            System.out.println("Drone flipping");
            drone.flip(FlipDirection.LEFT);
            drone.flip(FlipDirection.RIGHT);
            drone.flip(FlipDirection.FORWARD);
            drone.flip(FlipDirection.BACKWARD);
        }

        public void moveBackward() throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException, TelloNoValidIMUException {
            System.out.println("Drone moving backward");
            drone.backward(30);
        }

        public void turnLeft()throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException, TelloNoValidIMUException{
            System.out.println("Drone turning left 90");
            drone.turnLeft(90);
        }

        public void turnRight()throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException, TelloNoValidIMUException{
            System.out.println("Drone turning left 90");
            drone.turnRight(90);
        }
         public void upMove()throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException, TelloNoValidIMUException{
            System.out.println("Drone turning left 90");
            drone.up(20);
        }
         public void moveDown()throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException, TelloNoValidIMUException{
            System.out.println("Drone turning left 90");
            drone.down(20);
        }



        public void stopHorizontalMovement() {
            System.out.println("Drone stopping horizontal movement");
            // Add actual drone control logic here
        }
    }
}


