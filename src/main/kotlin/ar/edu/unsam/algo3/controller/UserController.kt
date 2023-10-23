package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.Usuario
import ar.edu.unsam.algo3.dto.RequestFiguDTO
import ar.edu.unsam.algo3.dto.UsuarioInfoProfileDTO
import ar.edu.unsam.algo3.dto.UsuarioLogeadoDTO
import ar.edu.unsam.algo3.dto.UsuarioLoginDTO
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

    @PatchMapping("/user/request-figurita")
    @Operation(summary = "Permite realizar una solicitud de una figurita a un usuario")
    fun figuritaRequest(@RequestBody requestData: RequestFiguDTO) = userService.figuritaRequest(requestData)

    @GetMapping("/user/{id}/info-profile")
    @Operation(summary = "Obtiene la info del profile del usuario")
    fun getProfileInfo(@PathVariable id: Int): UsuarioInfoProfileDTO = userService.getProfileInfo(id)
}