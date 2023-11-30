package ttps.java.grupo1.config;

public class StartupConfig {

    public static void printStartupMessages() {
        System.out.println("\u001B[35m");
        System.out.println("***********************************************");
        System.out.println("*          Grupo1 App Cuentas Claras          *");
        System.out.println("***********************************************\u001B[0m");
        System.out.println("\u001B[33mPORT: http://localhost:8080\u001B[0m");
        System.out.println("\u001B[36mAPI-docs: http://localhost:8080/v3/api-docs\u001B[0m");
        System.out.println("\u001B[32mSwagger-UI: http://localhost:8080/swagger-ui/index.html\u001B[0m");
    }
}
