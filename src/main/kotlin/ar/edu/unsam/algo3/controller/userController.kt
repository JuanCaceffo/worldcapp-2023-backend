package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.Usuario
import ar.edu.unsam.algo3.dto.UsuarioLogeadoDTO
import ar.edu.unsam.algo3.dto.UsuarioLoginDTO
import ar.edu.unsam.algo3.service.UsuarioService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val userService: UsuarioService) {
    @PostMapping("/user/login")
    @Operation(summary = "Permite logear un usuario al sistema.",)
    fun loginUser(@RequestBody dataUser: UsuarioLoginDTO): UsuarioLogeadoDTO = userService.login(dataUser)

    @GetMapping("/user/{id}")
    @Operation(summary = "Permite buscar un usuario por ID")
    fun searchUser(@PathVariable id: Int): Usuario = userService.searchByID(id)
}