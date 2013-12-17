package models
import com.decodified.scalassh._
import com.decodified.scalassh.HostKeyVerifiers._
import play.api.Logger
import scala.sys.process._
import com.decodified.scalassh.SimplePasswordProducer
import com.decodified.scalassh.PasswordLogin

/**
 * Created by kamilbadyla on 17.12.2013.
 */
case class SSHLogin(ip : String, login: String, pass: String) {
  var sshClient : SshClient = _
  def connect() : Boolean = {
    var result = Seq("nc", "-z", "-G", "1", ip, "22").!
    if (result == 0) {
      sshClient = new SshClient(HostConfig( PasswordLogin(login, SimplePasswordProducer(pass)), ip, 22, None, None, None, false, KnownHosts.right.toOption.getOrElse(DontVerify), HostConfig.DefaultSshjConfig))
      sshClient.authenticatedClient.isRight
    }
    else
      false
  }

}
