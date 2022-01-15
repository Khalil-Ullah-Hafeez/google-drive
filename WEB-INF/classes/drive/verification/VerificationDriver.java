package drive.verification;

public class VerificationDriver {
    private String email;

    public VerificationDriver( String email )
    {
        this.email = email;
    }

    public void sendVerificationMail()
    {
        Verification.sendMail( this.email );
    }

}
