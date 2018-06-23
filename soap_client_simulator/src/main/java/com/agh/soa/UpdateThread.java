package com.agh.soa;

import com.agh.edu.soap_client_simulator.ElementDto;
import com.agh.edu.soap_client_simulator.SoapServices;
import com.agh.edu.soap_client_simulator.SoapServicesImplService;

import java.util.Random;

public class UpdateThread implements Runnable {

    private Thread t;
    private ElementDto elementDto;
    private Random random;

    public UpdateThread(ElementDto elementDto) {
        this.elementDto = elementDto;
        random = new Random();
    }

    public void run() {
        try {
            Thread.sleep(10000);
            SoapServicesImplService soapServicesImplService = new SoapServicesImplService();
            SoapServices soapServicesImplPort = soapServicesImplService.getSoapServicesImplPort();
            int randomNumber = random.nextInt(2) - 2;
            ElementDto elementDtoById = soapServicesImplPort.getElementDtoById(elementDto.getId());
            elementDtoById.setPower(elementDtoById.getPower() + randomNumber);
            soapServicesImplPort.editElement(elementDtoById);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start () {
        if (t == null) {
            t = new Thread(this);
            t.start ();
        }
    }
}
