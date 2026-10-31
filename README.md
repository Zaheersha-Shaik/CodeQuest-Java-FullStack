# CodeQuest – Programming Learning Game

## Technologies
HTML, CSS, JSP, Servlets, JDBC, MySQL  
JDK 17 | Apache Tomcat 10.1 | Eclipse IDE

## Quick Setup
1. Create MySQL database by running `database/codequest_db.sql`.
2. Open Eclipse → File → Import → Existing Projects into Workspace.
3. Configure Apache Tomcat 10.1.
4. Add MySQL Connector/J JAR to `WEB-INF/lib` (or Build Path).
5. Edit database username/password in `src/main/java/com/codequest/util/DBConnection.java`.
6. Run on Tomcat.

Default admin:
- username: `admin`
- password: `admin123`

## GitHub
After testing:
```bash
git init
git add .
git commit -m "Complete CodeQuest programming learning game"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/CodeQuest.git
git push -u origin main
```
