package gui;

import model.GuiPanel;

public class PanelErrors
{

    public GuiPanel loadError(String errorCode)
    {
        GuiPanel error = new GuiPanel("#bd2130", "#ffffff", "Error no reconocido", "", "reload", 10);
        switch (errorCode.toUpperCase())
        {
            case "CARD_READER_CRASH":
                error.setMainText("Error del lector de tarjetas");
                error.setBottomText("El lector de tarjetas ha fallado.");
                break;
            case "PC_INET_CRASH":
                error.setMainText("Error de conexión");
                error.setBottomText("El equipo no está conectado a una red.");
                break;
            case "CARD_READER_DRIVER_NOT_FOUND":
                error.setMainText("Drivers no instalados");
                error.setBottomText("El lector de tarjetas requiere de los drivers.");
                break;
            case "CARD_READER_NOT_FOUND":
                error.setMainText("Error del lector de tarjetas");
                error.setBottomText("No se ha detectado ningún lector de tarjetas.");
                break;
            case "OPEN_WEB_ERROR":
                error.setMainText("Error al abrir la web");
                error.setBottomText("No se ha detectado ningún navegador compatible.");
                break;
            case "INCOMPATIBLE_OS":
                error.setMainText("Sistema operativo incompatible");
                error.setBottomText("Por favor, contacta con SCM.");
                break;
            case "ALREADY_RUNNING":
                error.setMainText("Programa duplicado");
                error.setBottomText("El programa ya estaba iniciado.");
                break;
            case "API_ACCESS_ERROR":
                error.setMainText("Error al leer DNI");
                error.setBottomText("El servidor API falló.");
                break;
            case "API_MAC_INVALID":
                error.setMainText("Error de API");
                error.setBottomText("El equipo actual no está autorizado.");
                break;
            case "DNI_API_ERROR":
                error.setMainText("Error al leer DNI (API)");
                error.setBottomText("El servidor principal tuvo un problema interno.");
                break;
            case "PC_API_NOT_ADDED":
                error.setMainText("PC No adherido a la API");
                error.setBottomText("El equipo actual no tiene permisos para ejecutar la aplicación.");
                break;
            case "API_OFFLINE":
                error.setMainText("Error del servidor (API)");
                error.setBottomText("El servidor principal no está disponible.");
                break;
            default:
                error.setMainText("¡ERROR INESPERADO!");
                error.setTimeout(5);
                error.setBottomText("La aplicación va a reiniciarse. Por favor, espera " + error.getTimeout() + " segundos...");
                error.setAction("close");
                break;
        }
        return error;
    }

}
