# Security

Security protects our own and our clients data. Security is important for privacy, functionality, and regulations such as HIPAA or PCI. Security breaches can break functionality of a product, or leak protected information causing irreparable damage to Concord's reputation. Below are common security issues and ways to combat them.

## Corporate Security

Protect your and Concord's data online.

### Email Security

**Do**:
use email for written communications that are less time sensitive or more formal than instant messages

**Don't**:

- Email passwords or similar values
  - Bearer tokens, Security Certificates and other values can all function as passwords.
- Email Personally Identifiable information (PII), Protected Health Information (PHI), or Payment Card Industry data (PCI).
- Use the wrong email
  - Don't use a Concord or client email to send/receive personal emails
  - Don't use a Concord email for client business (if you've been given a client email)
  - Don't use a client email for Concord business

If you shouldn't leave it on a notepad on your desk, you shouldn't email it.

**Caveats**:
Occasionally clients will expect you to email stuff you shouldn't, if that's the case always consider if there are more secure options available and if possible, use an encrypted email. If you suspect the email would be a HIPAA violation, DON'T send the email.

### Certificates, Credentials and Secrets

You are going to interact with certificates, credential and secrets while working at Concord. Here are some tips and guidelines about how to manage them securely.

- Don't email credentials
- Secure products to store and share credentials
  - Vault by HashiCorp
  - Cloud provider key/secret stores: AWS KMS, AWS Secrets Manager, Azure Key Vault, Google Cloud Secret Manager.
  - Personal password managers: Bitwarden
- Before sharing credentials with someone consider:
  - Am I allowed to share the credential? If not, direct them to whoever owns the credentials
  - Is this method of sharing credentials secure? If not, work to find a secure method.
  - How do we revoke access if this credential gets lost? If the safeguard fails, change the password or certificate.
- Passwords expire, get leaked, or otherwise need to be changed

## Security in Software Development

All products created through Concord must be secure!

### Coding Practices

Here are some good coding practices, note that this is not an exhaustive list.

- Don't execute user provided scripts or arbitrary strings. If you are providing a string, use a new file instead.
- Don't leak architecture or implementation details
  - Eg. If you serve your user a Spring Whitelabel Error Page, users will know that your application is built in Java and uses Spring​
  - Log a stack trace but don't provide it to a user​. Correlation ids can link a log and an error presented to an end user​
  - Don't log payloads, especially with sensitive data​. Log systems can often be accessed by people without 'need-to-know' privileges for all payload data
- Keep your versions up to date

#### Coding Patterns

- Immediately sanitize user input​
  - Users are sneaky and may be malicious, and therefore aren't to be trusted​
    - E.g., If you only want alphanumeric strings in your DB, and you get those strings from users via a REST API, make sure the string is alphanumeric right away in your REST controller, don't wait until you try to put it in your DB to see if it works.​
  - Different technologies support different character sets differently, which can lead to unintended behavior​
    - Unintended behavior can open vulnerabilities and leaks​
- Use well-maintained libraries with large user bases, preferably open-sourced​
  - Well-maintained libraries get frequent updates, frequent updates mean new security concerns are addressed sooner​
  - Libraries with large user bases have their vulnerabilities probed by more eyes, which means security concerns are brought to light sooner​
  - Open-sourced libraries can't hide vulnerabilities from their users since the source code is accessible​
- KISS​
  - Simple solutions have fewer edge cases and are easier to test exhaustively​
  - Simple solutions are easier to update if a security concern arises​
  - It is more difficult to introduce a vulnerability to a simple solution with a robust regression test suite without it being noticed​
  - Keeping solutions simple helps minimize attack surfaces

#### Whats the difference? Authentication, Authorization, Integrity and Hacking

Authentication: verifying an identity.
Authorization: determining if an identity is allows to perform and action
Integrity: when a system performs it function as intended
Hacking: when a system is circumvented allowing un-authorized identities to act on the system.

### OWASP Top 10

The Open Web Application Security Project (OWASP) provides tools and information for proper web security.
The OWASP top 10 are the 10 most prevalent and dangerous risks to web security and can be found [here](https://owasp.org/www-project-top-ten/).

1. Injection -- Easiest attack with most sever consequences!
2. Broken Authentication
3. Sensitive Data Exposure
4. XML External Entities
5. Broken Access Control
6. Security Misconfiguration
7. Cross-site Scripting
8. Insecure Deserialization
9. Using Components with Known Vulnerabilities
10. Insufficient Logging and Monitoring

[How to defend against OWASP top 10 Web Security risks](https://sucuri.net/guides/owasp-top-10-security-vulnerabilities-2021/).
[OWASP Top 10 CheatSheet](https://cheatography.com/deleted-2754/cheat-sheets/owasp-top-10-application-security-risks/).

Common places for security risks:

- Server logs
- Error logs
- URL construction
- Cookies

Common methods for defense:

- Access control - if you don't have access control, you should!
- Whitelist un-trusted data
- Verify un-trusted data against expected data patterns
- Don't log sensitive info or keys
- Properly set up security configurations: turn off unused features (ports, services, ect)

## More Resources

1. [Intro to InfoSec (Puralsight)](https://app.pluralsight.com/library/courses/information-security-introduction/table-of-contents​)
2. [OWASP Top 10 Proactive Controls](https://owasp.org/www-project-proactive-controls/​)
3. [OWASP Top 10 API Security Issues](https://concordusa1.sharepoint.com/Shared%20Documents/Forms/AllItems.aspx?id=%2FShared%20Documents%2FDelivery%2FEducational%20Resources%2FSecurity%2Fowasp%2Dapi%2Dsecurity%2Dtop%2D10%2Epdf&parent=%2FShared%20Documents%2FDelivery%2FEducational%20Resources%2FSecurity)​
4. ["Security in 5 – Application Development Guide"](https://concordusa1.sharepoint.com/Shared%20Documents/Forms/AllItems.aspx?id=%2FShared%20Documents%2FDelivery%2FEducational%20Resources%2FSecurity%2FSecurity%20In%20Five%20Secure%20Application%20Development%20Guideline%2Epdf&parent=%2FShared%20Documents%2FDelivery%2FEducational%20Resources%2FSecurity)​
5. [SEI Cert Coding Standards](https://wiki.sei.cmu.edu/confluence/display/seccode/SEI+CERT+Coding+Standards)
