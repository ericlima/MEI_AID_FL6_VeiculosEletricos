package pt.ipcb.ad.veiculoseletricos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pt.ipcb.ad.veiculoseletricos.model.Veiculo;
import pt.ipcb.ad.veiculoseletricos.model.VeiculoDTO;
import pt.ipcb.ad.veiculoseletricos.service.VeiculoNaoEncontradoException;
import pt.ipcb.ad.veiculoseletricos.service.VeiculoRepository;

@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping("/{marca}/{modelo}")
    public VeiculoDTO obtemVeiculo(@PathVariable String marca,@PathVariable String modelo) {

        Veiculo veiculo = veiculoRepository.findByMarcaAndModelo(marca, modelo);

        if (veiculo == null) {
            throw new VeiculoNaoEncontradoException("Veiculo não encontrado");
        }

        VeiculoDTO veiculoDTO = new VeiculoDTO();
        veiculoDTO.setMarca(marca);
        veiculoDTO.setModelo(modelo);
        veiculoDTO.setBateria(veiculo.getBateria());
        veiculoDTO.setAutonomia(veiculo.getAutonomia());
        return veiculoDTO;

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoDTO criaVeiculo(@RequestBody VeiculoDTO veiculoDTO) {

        Veiculo veiculo = new Veiculo();
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setBateria(veiculoDTO.getBateria());
        veiculo.setAutonomia(veiculoDTO.getAutonomia());

        veiculo = veiculoRepository.save(veiculo);

        VeiculoDTO resposta = new VeiculoDTO();
        resposta.setMarca(veiculo.getMarca());
        resposta.setModelo(veiculo.getModelo());
        resposta.setBateria(veiculo.getBateria());
        resposta.setAutonomia(veiculo.getAutonomia());

        return resposta;
    }

}
