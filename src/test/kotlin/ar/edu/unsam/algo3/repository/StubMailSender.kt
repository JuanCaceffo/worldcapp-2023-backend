package ar.edu.unsam.algo3.repository

class StubMailSender: MailSender{
    val mailsEnviados = mutableListOf<Mail>()

    override fun sendMail(mail:Mail) {
        mailsEnviados.add(mail)
    }
}