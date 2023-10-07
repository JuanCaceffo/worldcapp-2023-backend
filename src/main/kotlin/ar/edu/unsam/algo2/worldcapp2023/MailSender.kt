package ar.edu.unsam.algo2.worldcapp2023
interface MailSender{
    fun sendMail(mail:Mail){}
}
data class Mail(
    val from:String,
    val to:String,
    val subject:String,
    val content:String
)