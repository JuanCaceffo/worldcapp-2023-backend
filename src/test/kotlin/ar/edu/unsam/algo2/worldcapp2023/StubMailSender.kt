package ar.edu.unsam.algo2.worldcapp2023

class StubMailSender: MailSender{
    val mailsEnviados = mutableListOf<Mail>()

    override fun sendMail(mail:Mail) {
        mailsEnviados.add(mail)
    }
}