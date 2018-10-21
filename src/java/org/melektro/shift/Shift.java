/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.melektro.shift;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * REST Web Service
 *
 * @author Marius
 */
@Path("shift")
public class Shift {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public Shift() {
    }

    /**
     * Retrieves representation of an instance of org.melektro.shift.Shift
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() throws InterruptedException {
        //TODO return proper representation object
        setup();
        loop();
        return "<started/>";
    }

    /**
     * PUT method for updating or creating an instance of Shift
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

//int latchPin = 8;  //Pin connected to Pin 12 of 74HC595 (Latch)
//int clockPin = 12; //Pin connected to Pin 11 of 74HC595 (Clock)
//int dataPin = 11;  //Pin connected to Pin 14 of 74HC595 (Data)
    GpioPinDigitalOutput clockPin;
    GpioPinDigitalOutput dataPin;
    GpioPinDigitalOutput latchPin;

    void setup() {

        final GpioController gpio = GpioFactory.getInstance();
        latchPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "latch", PinState.LOW);
        clockPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "clock", PinState.LOW);
        dataPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "data", PinState.LOW);

        // set shutdown state for this pin
        latchPin.setShutdownOptions(true, PinState.LOW);
        clockPin.setShutdownOptions(true, PinState.LOW);
        dataPin.setShutdownOptions(true, PinState.LOW);
        latchPin.low();
    }

    void loop() throws InterruptedException {
        //count from 0 to 255
        for (byte i = 0; i < 256; i++) {
            shiftDataOut(i);
            //set latchPin low then high to send data out
            latchPin.low();
            Thread.sleep(2000);
            latchPin.high();
            Thread.sleep(1000);
        }
    }

    void shiftDataOut(byte dataOut) {
        // Shift out 8 bits LSB first, clocking each with a rising edge of the clock line

        for (byte i = 0; i <= 7; i++) {              // for each bit in dataOut send out a bit
            clockPin.low();     //set clockPin to LOW prior to sending bit

            // if the value of DataOut and (logical AND) a bitmask
            // are true, set pinState to 1 (HIGH)
            if ((dataOut & (1 << i)) > 0) {
                dataPin.high();
            } else {
                dataPin.low();
            }

        }
        clockPin.high(); //stop shifting out data
    }
}
