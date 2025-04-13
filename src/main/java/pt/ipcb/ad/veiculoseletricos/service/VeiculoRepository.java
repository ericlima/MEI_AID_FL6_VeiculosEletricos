package pt.ipcb.ad.veiculoseletricos.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ipcb.ad.veiculoseletricos.model.Veiculo;


public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Veiculo findByMarcaAndModelo(String marca, String modelo);

}
