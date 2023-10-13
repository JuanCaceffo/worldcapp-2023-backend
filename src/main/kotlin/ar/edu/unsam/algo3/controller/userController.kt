package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.UsuarioLoginDTO
import ar.edu.unsam.algo3.service.UsuarioService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class UserController(val userService: UsuarioService) {
    @PostMapping("/user/login")
    @Operation(summary = "Permite logear un usuario al sistema.",)
    fun loginUser(@RequestBody dataUser: UsuarioLoginDTO) = userService.login(dataUser)

}