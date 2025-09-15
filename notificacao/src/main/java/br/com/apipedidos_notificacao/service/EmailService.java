package br.com.apipedidos_notificacao.service;

import br.com.apipedidos_notificacao.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private MailSender mailSender;
	
	public void enviarEmail(Pedido pedido) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("jimpo-do-danone@gmail.com");
		simpleMailMessage.setTo(pedido.getEmailNotificacao());
		simpleMailMessage.setSubject("Pedido de compra");
		simpleMailMessage.setText(this.gerarMensagem(pedido));
		mailSender.send(simpleMailMessage);
	}
	
	private String gerarMensagem(Pedido pedido) {
		String pedidoId = pedido.getId().toString();
		String cliente = pedido.getCliente();
		String valor = String.valueOf(pedido.getValorTotal());
		String status = pedido.getStatus().name();
		return String.format("Olá %s seu pedido de numero %s no valor de %s, foi realizado com sucesso \nStatus: %s.",
						cliente, pedidoId, valor, status);
	}
}
