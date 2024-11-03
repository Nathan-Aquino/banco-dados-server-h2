package org.bancoH2.banco_dados_sistema;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PreDestroy;

@SpringBootApplication
public class BancoDadosSistemaApplication {

	private static Server h2Server;

	public static void main(String[] args) throws SQLException {
        try {
            if (h2Server != null && h2Server.isRunning(true)) {
                h2Server.stop();
            }
            h2Server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-ifNotExists").start();
            
            SpringApplication.run(BancoDadosSistemaApplication.class, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@PreDestroy
    public void stopServer() {
        if (h2Server != null) {
            h2Server.stop();
        }
    }

}
