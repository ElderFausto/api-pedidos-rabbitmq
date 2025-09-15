package br.com.apipedidos_notificacao.listener;

import br.com.apipedidos_notificacao.entities.Pedido;
import br.com.apipedidos_notificacao.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {
	private final Logger logger = LoggerFactory.getLogger(PedidoListener.class);
	
	
	private final EmailService emailService;
	
	public PedidoListener(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@RabbitListener(queues = "pedidos.v1.pedido-criado.gerar.notificacao")
	public void enviarNotificacao(Pedido pedido) {
		emailService.enviarEmail(pedido);
		logger.info("Noficacao gerada: {}", pedido.toString());
	}
}
