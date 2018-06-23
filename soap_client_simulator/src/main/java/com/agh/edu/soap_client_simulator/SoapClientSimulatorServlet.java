package com.agh.edu.soap_client_simulator;

import com.agh.soa.UpdateThread;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/soapClientSimulatorServlet")
public class SoapClientSimulatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String elementNewName = req.getParameter("elementNewName");
        int elementId = Integer.parseInt(req.getParameter("elementId"));

        SoapServicesImplService soapServicesImplService = new SoapServicesImplService();
        SoapServices soapServicesImplPort = soapServicesImplService.getSoapServicesImplPort();
        ElementDto elementDto = new ElementDto();
        elementDto.setId(elementId);
        elementDto.setName(elementNewName);
        soapServicesImplPort.editElement(elementDto);
        new UpdateThread(elementDto).start();
    }

}