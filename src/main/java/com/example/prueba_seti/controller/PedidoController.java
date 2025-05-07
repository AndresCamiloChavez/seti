package com.example.prueba_seti.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba_seti.dto.PedidoMapper;
import com.example.prueba_seti.dto.PedidoRequest;
import com.example.prueba_seti.dto.PedidoResponse;
import com.example.prueba_seti.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> enviarPedido(@RequestBody PedidoMapper mapper) {
        PedidoRequest pedido = mapper.getEnviarPedido();
        PedidoResponse response = pedidoService.procesarPedido(pedido);
        return ResponseEntity.ok(response);
    }
}
