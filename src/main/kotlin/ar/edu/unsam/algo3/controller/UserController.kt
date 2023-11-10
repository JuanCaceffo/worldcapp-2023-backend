package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.service.UsuarioService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
class UserController(val userService: UsuarioService) {

    @PostMapping("/login")
    @Operation(summary = "Permite logear un usuario al sistema.",)
    fun loginUser(@RequestBody dataUser: UsuarioLoginDTO): UsuarioLogeadoDTO = userService.login(dataUser)

    @GetMapping("/{id}/info-profile")
    @Operation(summary = "Obtiene la info del profile del usuario")
    fun getProfileInfo(@PathVariable id: Int): UsuarioInfoProfileDTO = userService.getProfileInfo(id)


    @GetMapping("/{id}/user-info")
    @Operation(summary = "Obtiene la info del usuario")
    fun getUserInfo(@PathVariable id: Int): UsuarioInfoDTO = userService.getUserInfo(id)

    @GetMapping("/get-figurita-intercambio/usuario/{userID}/figurita/{figuritaId}")
    @Operation(summary = "Devuelve la figurita del usuario de la lita de figuritas a regalar")
    fun getFiguritaIntercambio(@PathVariable userID: Int, @PathVariable figuritaId: Int): FiguritaDTO{
        return userService.getGiftableFigurita(figuritaId,userID)
    }
    @PatchMapping("/request-figurita")
    @Operation(summary = "Permite realizar una solicitud de una figurita a un usuario")
    fun figuritaRequest(@RequestBody requestData: RequestFiguDTO) = userService.figuritaRequest(requestData)

    @PatchMapping("/{id}/info-profile")
    @Operation(summary = "Permite actualizar la informacion del usuario")
    fun editProfileInfo(@RequestBody profileInfo: UsuarioInfoProfileDTO,  @PathVariable id: Int): UsuarioInfoProfileDTO = userService.editProfileInfo(profileInfo, id)

    @PatchMapping("/{id}/user-info")
    @Operation(summary = "Permite actualizar el username del usuario")
    fun editUserUsername(@RequestBody userInfo: UsuarioInfoDTO,  @PathVariable id: Int): UsuarioInfoDTO = userService.editUserUsername(userInfo, id)

    @GetMapping("/{id}/lista-figus-repetidas")
    @Operation(summary = "permite obtener la lista de figuritas repetidas del usaurio")
    fun getFigusRepes(@PathVariable id: Int): List<FiguritaDTO> {
        return userService.getFigusRepes(id)
    }

    @GetMapping("/{id}/lista-figus-faltantes")
    @Operation(summary = "permite obtener la lista de figuritas faltantes del usaurio")
    fun getFigusFaltantes(@PathVariable id: Int): List<FiguritaDTO> {
        return userService.getFigusFaltantes(id)
    }

    @DeleteMapping("/{userID}/eliminar-figu-repe/{figuID}")
    @Operation(summary="Permite eliminar una figurita repetida del usuario")
    fun deleteFiguritaRepe(@PathVariable userID: Int, @PathVariable figuID: Int){
        userService.deleteFiguDuplciate(userID,figuID)
    }

    @DeleteMapping("/{userID}/eliminar-figu-faltante/{figuID}")
    @Operation(summary="Permite eliminar una figurita faltante del usuario")
    fun deleteFiguritaFaltante(@PathVariable userID: Int, @PathVariable figuID: Int){
        userService.deleteFiguFaltante(userID,figuID)
    }

    @PatchMapping("/agregar-figurita-faltante")
    @Operation(summary = "Permite agregar una figurita a la lista de faltantes del usuario")
    fun agregarFiguritaFaltante(@RequestBody figuToAddData: AddFiguDTO){
        userService.addFiguFaltante(figuToAddData)
    }

    @PatchMapping("/agregar-figurita-repetida")
    @Operation(summary = "Permite agregar una figurita a la lista de repetidas del usuario")
    fun agregarFiguritaRepetida(@RequestBody figuToAddData: AddFiguDTO){
        userService.addFiguRepe(figuToAddData)
    }
}