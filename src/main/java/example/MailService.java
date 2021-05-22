package example;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
Интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.
*/
public  interface MailService {
    Sendable processMail(Sendable mail) throws IllegalAccessException;
}


/*
Класс, в котором скрыта логика настоящей почты
*/
  class RealMailService implements MailService {

    @Override
    public Sendable processMail(Sendable mail) {
        // Здесь описан код настоящей системы отправки почты.
        return mail;
    }
}
//ненадёжный MailService
class UntrustworthyMailWorker implements MailService {
    RealMailService variable = new RealMailService();
    protected final MailService[] mailServices;
    public UntrustworthyMailWorker(MailService[] mailServices) {
        this.mailServices = mailServices;

    }
    public RealMailService getRealMailService() {
        return this.variable;
    }
    @Override
    public Sendable processMail(Sendable mail)  {
        Sendable sendMail;
        for ( MailService ms: this.mailServices
             ) {
            try {
                sendMail = ms.processMail(mail);
                mail = sendMail;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return variable.processMail(mail);
    }
}
//шпиёнь
class Spy implements MailService {
    public static final String AUSTIN_POWERS = "Austin Powers";
    private  String from;
    private  String to;
    private String message;
    private Logger LOGGER;
    public Spy(Logger logger) {
        this.LOGGER = logger;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailMessage) {
            this.from = mail.getFrom();
            this.to = mail.getTo();
            this.message = ((MailMessage) mail).getMessage();
            if (this.from.equals(AUSTIN_POWERS) || this.to.equals(AUSTIN_POWERS)) {
                this.LOGGER.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                        new Object[] {mail.getFrom(), mail.getTo(), ((MailMessage) mail).getMessage()});
                return mail;
            }
            LOGGER.log(Level.INFO, "Usual correspondence: from {0} to {1}", new Object[] {mail.getFrom(), mail.getTo()});
        }
        return mail;
    }
}

class Thief implements MailService {
    private final int cost;
    private static int stolenValue;

   public Thief(int cost) {
        this.cost = cost;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) {
            Package pack = ((MailPackage) mail).getContent();
            String content = ((MailPackage) mail).getContent().getContent();
            String from = mail.getFrom();
            String to = mail.getTo();
            if (pack.getPrice() > this.cost) {
                stolenValue+=pack.getPrice();
                return new MailPackage(from, to,new Package("stones instead of " + content, 0));
            }
        }
        return mail;
    }

    public int getStolenValue() {
        return stolenValue;
    }
}

class Inspector implements MailService {
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";
    public Inspector() {}
    @Override
    public Sendable processMail(Sendable mail)  {
/*        if (mail instanceof MailMessage) {

                if (((MailMessage) mail).getMessage().contains("stones")) {
                    throw new StolenPackageException();
                }
            if (((MailMessage) mail).getMessage().contains(WEAPONS) || ((MailMessage) mail).getMessage().contains(BANNED_SUBSTANCE)) {
                throw new IllegalPackageException();
            }

        }*/
        if (mail instanceof MailPackage) {
            Package pack = ((MailPackage) mail).getContent();
            if (pack.getContent().contains(WEAPONS) || pack.getContent().contains(BANNED_SUBSTANCE)) {
                throw new IllegalPackageException();
            }
            if (pack.getContent().contains("stones")) {
                throw new StolenPackageException();
            }

        }
        return mail;

    }
}

 class IllegalPackageException extends RuntimeException {
    public IllegalPackageException() {
        System.out.println("Illegal package: " );
    }
}

  class StolenPackageException extends RuntimeException {
    public StolenPackageException() {
        System.out.println("Stolen Package exception: " );
    }
}