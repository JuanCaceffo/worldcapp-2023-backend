package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.Usuario
import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.service.UsuarioService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("*")
class UserController(val userService: UsuarioService) {
    @PostMapping("/user/login")
    @Operation(summary = "Permite logear un usuario al sistema.",)
    fun loginUser(@RequestBody dataUser: UsuarioLoginDTO): UsuarioLogeadoDTO = userService.login(dataUser)

    @GetMapping("/user/{id}")
    @Operation(summary = "Permite buscar un usuario por ID")
    //TODO: Serializar datos necesarios del usuario para el front
    fun searchUser(@PathVariable id: Int): Usuario = userService.searchByID(id)

    @GetMapping("/user/{id}/info-profile")
    @Operation(summary = "Obtiene la info del profile del usuario")
    fun getProfileInfo(@PathVariable id: Int): UsuarioInfoProfileDTO = userService.getProfileInfo(id)

    @PatchMapping("/user/request-figurita")
    @Operation(summary = "Permite realizar una solicitud de una figurita a un usuario")
    fun figuritaRequest(@RequestBody requestData: RequestFiguDTO) = userService.figuritaRequest(requestData)

    @PatchMapping("/user/{id}/info-profile")
    @Operation(summary = "Permite actualizar la informacion del usuario")
    fun editProfileInfo(@RequestBody profileInfo: UsuarioInfoProfileDTO,  @PathVariable id: Int): UsuarioInfoProfileDTO = userService.editProfileInfo(profileInfo, id)

    @GetMapping("/user/{id}/lista-figus-faltantes")
    @Operation(summary = "permite obtener la lista de figuritas faltantes del usaurio")
    fun getMissingFigus(@PathVariable id: Int): List<FiguritaDTO> = userService.getMissinFigus(id)

    @GetMapping("/user/{id}/lista-figus-duplicadas")
    @Operation(summary = "permite obtener la lista de figuritas duplicadas del usaurio")
    fun getDuplicateFigus(@PathVariable id: Int): List<FiguritaDTO> = userService.getDuplicateFigus(id)

}