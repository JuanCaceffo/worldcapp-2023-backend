package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.FiltroPuntoDeVenta
import ar.edu.unsam.algo3.domain.PuntoDeVenta
import ar.edu.unsam.algo3.dto.MarketCardDTO
import ar.edu.unsam.algo3.dto.toMarketCardDTO
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service

data class Province(val province: String, val locations: List<String>)

val inputProvinces: List<Province> = listOf(
    Province("Buenos Aires", listOf("Avellaneda", "Bahía Blanca", "General Pacheco", "La Plata", "Mar del Plata", "San Isidro", "San Martin", "Tandil", "Tigre", "Vicente López", "3 de Febrero")),
    Province("CABA", listOf("Balvanera", "Belgrano", "Palermo", "San Nicolás", "San Telmo", "Villa Crespo")),
    Province("Catamarca", listOf("San Fernando del Valle de Catamarca", "Andalgalá", "Belén", "Santa María")),
    Province("Chaco", listOf("Resistencia", "Barranqueras", "Castelli", "Presidencia Roque Sáenz Peña", "Quitilipi", "Villa Ángela")),
    Province("Chubut", listOf("Rawson", "Comodoro Rivadavia", "Esquel", "Puerto Madryn", "Trelew")),
    Province("Córdoba", listOf("Córdoba", "Río Cuarto", "Villa Carlos Paz", "Jesús María", "La Falda", "Villa María")),
    Province("Corrientes", listOf("Corrientes", "Goya", "Mercedes", "Paso de los Libres")),
    Province("Entre Ríos", listOf("Paraná", "Concordia", "Gualeguaychú", "Colón", "Victoria")),
    Province("Formosa", listOf("Formosa", "Clorinda", "Pirané", "Las Lomitas")),
    Province("Jujuy", listOf("San Salvador de Jujuy", "San Pedro", "Palpalá", "Libertador General San Martín")),
    Province("La Pampa", listOf("Santa Rosa", "General Pico", "Toay", "Realicó")),
    Province("La Rioja", listOf("La Rioja", "Chilecito", "Famatina", "Chamical")),
    Province("Mendoza", listOf("Mendoza", "San Rafael", "Godoy Cruz", "Luján de Cuyo")),
    Province("Misiones", listOf("Posadas", "Puerto Iguazú", "Oberá", "Eldorado")),
    Province("Neuquén", listOf("Neuquén", "San Martín de los Andes", "Junín de los Andes", "Zapala")),
    Province("Río Negro", listOf("Viedma", "San Carlos de Bariloche", "General Roca", "Cipolletti")),
    Province("Salta", listOf("Salta", "San Ramón de la Nueva Orán", "Cafayate", "Metán")),
    Province("San Juan", listOf("San Juan", "Rivadavia", "Chimbas", "Pocito")),
    Province("San Luis", listOf("San Luis", "Villa Mercedes", "Merlo", "La Toma")),
    Province("Santa Cruz", listOf("Río Gallegos", "El Calafate", "Puerto Deseado", "Caleta Olivia")),
    Province("Santa Fe", listOf("Rosario", "Santa Fe", "Rafaela", "Venado Tuerto", "San Lorenzo")),
    Province("Santiago del Estero", listOf("Santiago del Estero", "La Banda", "Termas de Río Hondo", "Fernández")),
    Province("Tierra del Fuego", listOf("Ushuaia", "Río Grande", "Tolhuin")),
    Province("Tucumán", listOf("San Miguel de Tucumán", "Yerba Buena", "Tafí Viejo", "Río Grande", "Aguilares"))
)

@Service
class ProvinceService() {
    fun getAll(): List<Province> = inputProvinces
}