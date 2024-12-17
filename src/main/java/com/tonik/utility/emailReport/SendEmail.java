package com.tonik.utility.emailReport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import com.tonik.utility.ExtentReporter;
import com.tonik.utility.excel.ExcelUpdate;

public class SendEmail {
    private static final String USERNAME = "kaushik.mr1983@gmail.com";
    private static final String PASSWORD = "jehjklesanptcmlp";
    private static final String HOST = "smtp.gmail.com";
    private static final String[] TO = {"trazak@tonikbank.com"};
    private static final String[] CC = {};
    private static final String[] BCC = {};

//    public static void EmailReport(String platform) throws MessagingException, IOException {
//        String version = null;
//        try {
//            version = ExtentReporter.buildVersion;
//            System.out.println("Build version: " + version);
//        } catch (Exception e) {
//            System.err.println("Failed to fetch build version: " + e.getMessage());
//        }
//        String filepath = ExcelUpdate.xlpath;
//        String fileName = ExcelUpdate.xlFileName;
//        String subject = prepareSubject(platform, version);
//        boolean enableAttachment = true;
//        String columnHeader = "Number of Total Test";
//        String columnHeader2 = "Number of Test";
//        String moduleName = "Module Name";
//        String moduleResult = "Module Result";
////        Fetching dynamic report data
//        StringBuilder insertResult = ExtentReporter.updateResult();
//        StringBuilder insertModuleResult = ExtentReporter.updateModuleResult();
//        StringBuilder insertModuleResult1 = ExtentReporter.updatePercentageOffailure();
////        Prepare the email content
//        String emailContent;
//        if ("ABC" .equals("TV")) {
////            Replace "ABC" comparison logic with actual condition if needed s
//            subject = "Android TV Analysed Report, APP version - 20.21106.3";
//            columnHeader = "Module Name";
//            filepath = System.getProperty("user.dir") + "\\Analysed_Reports\\Analysed_Reports.xlsx";
//            emailContent = buildTVReportContent(columnHeader, columnHeader2, insertResult);
//        } else {
//            emailContent = buildDefaultReportContent(columnHeader2, moduleName, moduleResult, insertModuleResult, insertModuleResult1);
//        }
////        Sending the email
//        sendMail(USERNAME, PASSWORD, TO, CC, BCC, subject, emailContent, ExcelUpdate.xlpath, ExcelUpdate.xlFileName, enableAttachment);
//    }


    public static void EmailReport(String platform) throws MessagingException, IOException {
        String version = null;
        try {
            version = ExtentReporter.buildVersion;
            System.out.println("Build version: " + version);
        } catch (Exception e) {
            // Handle exception if needed
        }
        String filepath = ExcelUpdate.xlpath;
        String subject = generateSubject(platform, version);
        boolean EnableAttachment = true;
        String fileName = ExcelUpdate.xlFileName;
        StringBuilder InsertResult = ExtentReporter.updateResult();
        StringBuilder InsertModuleResult = ExtentReporter.updateModuleResult();
        StringBuilder InsertModuleResult1 = ExtentReporter.updatePercentageOffailure();
        String Table = generateEmailTable(platform, version, InsertResult, InsertModuleResult, InsertModuleResult1);
        sendMail(USERNAME, PASSWORD, TO, CC, BCC, subject, Table, ExcelUpdate.xlpath, ExcelUpdate.xlFileName, EnableAttachment);
    }

    private static String generateSubject(String platform, String version) {
        if ("Android".equalsIgnoreCase(platform)) {
            return "Android App | Version " + version + " | Android APP – Jenkins Scheduled Execution HLS";
        } else if ("Web".equalsIgnoreCase(platform)) {
            return "Web | " + ExtentReporter.version + " | Chrome – Jenkins Scheduled Execution HLS";
        } else if ("iOS".equalsIgnoreCase(platform)) {
        return "iOS | " + ExtentReporter.version + " | iOS APP – Jenkins Scheduled Execution HLS";
    }
        return "Execution Report";
    }

    private static String generateEmailTable(String platform, String version, StringBuilder InsertResult,
                                             StringBuilder InsertModuleResult, StringBuilder InsertModuleResult1) {
        if ("ABC".equals("TV")) {  // This seems like a placeholder condition, consider revisiting
            return "<html><br/>Hi Team,<br/>Please find attached test automation execution results."
                    + "<h3><table align=\"center\" border=\"1\" cellpadding=\"5\" cellspacing=\"0\" style=\"border-collapse: collapse;\">"
                    + "<tr><td>Module Name</td><td><b>Number of validation Passed</b></td><td><b>Number of validation Failed</b></td></tr>"
                    + InsertResult + "</table></h3><br/>Regards,<br>TONIK Automation Team</html>";
        }
//        return "<html><br/>Hi Team,<br/>Please find attached test automation execution results."
//        + "<br/><b>Execution Summary:</b><br/><h3><table border=\"1\" cellpadding=\"5\" cellspacing=\"0\" style=\"border-collapse: collapse;\">"
//        + "<tr><td><b>Total Module Run</b></td>"
//        + "<td><b>Total Module Passed</b></td>"
//        + "<td><b>Total Module Failed</b></td>"
//        + "<td><b>Failed%</b></td>"
//        + "<td><b>Pass%</b></td></tr>"
//        + InsertModuleResult1
//        + "</table></h3><br/><b>Execution Details:</b><br/><table border=\"1\" cellpadding=\"5\" cellspacing=\"0\" style=\"border-collapse: collapse;\">"
//        + "<tr><td><b>Module Name</b></td>"
//        + "<td><b>Module Result</b></td></tr>"
//        + InsertModuleResult // Add detailed module results dynamically here
//        + "</table><br/><br/>"
//        + "Regards,<br/>TONIK Automation Team</html>";
        return "<html>\n" +
                "<br/>Hi Team,<br/>\n" +
                "<br/><br/>\n" +
                "Please find attached test automation execution results.<br/>\n" +
                "<br/><b>Execution Summary:</b><br/>\n" +
                "<h3>\n" +
                "<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\" style=\"border-collapse: collapse;\">\n" +
                "<tr>\n" +
                "    <td><b>Module Name</b></td>\n" +
                "    <td><b>Total test cases executed</b></td>\n" +
                "    <td><b>Total Module Passed</b></td>\n" +
                "    <td><b>Total Module Failed</b></td>\n" +
                "    <td><b>Failed %</b></td>\n" +
                "    <td><b>Pass %</b></td>\n" +
                "</tr>\n" + InsertModuleResult1 +
                "</table>\n" +
                "</h3>\n" +
                "<br/><b>Execution Details:</b><br/>\n" +
                "<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\" style=\"border-collapse: collapse;\">\n" +
                "<tr>\n" +
                "    <td><b>Test case Id Name</b></td>\n" +
                "    <td><b>Test case Result</b></td>\n" +
                "</tr>\n" +
                InsertModuleResult //<!-- Add detailed module results dynamically here -->\n"
                 +
                "</table>\n" +
                "<br/><br/>\n" +
                "Regards,<br/>\n" +
                "TONIK Automation Team\n" +
                "</html>\n";
    }



    private static String prepareSubject(String platform, String version) {
        if (platform.equalsIgnoreCase("Android")) {
            return "Android App | Version " + version + " | Android APP – Jenkins Scheduled Execution HLS";
        } else if (platform.equalsIgnoreCase("Web")) {
            return "PWA | " + ExtentReporter.version + " | Chrome – Jenkins Scheduled Execution HLS";
        } else {
            return "Execution Report | Version " + version;
        }
    }

    private static String buildTVReportContent(String columnHeader, String columnHeader2, StringBuilder insertResult) {
        return "Hi Team,<br/>Please find attached test automation execution results." + "<br>" + "<html><br>" + "<h3><table align=\"center\"></h3>\n\n" + "<table width=\"600\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" style=\"border:1px solid #ccc;\">\r\n" + "<tr>\r\n" + "<td>" + columnHeader + "</td>\r\n" + "<td><span style=\"font-weight:bold\">" + columnHeader2 + " Passed</span></td>\r\n" + "<td><span style=\"font-weight:bold\">" + columnHeader2 + " Failed</span></td>\r\n" + "</tr>\r\n" + insertResult + "</table>\r\n\n\n" + "<br>" + "</html><br/> Regards,<br> IGS Automation Team";
    }

    private static String buildDefaultReportContent(String columnHeader2, String moduleName, String moduleResult, StringBuilder insertModuleResult, StringBuilder insertModuleResult1) {
        return "Hi Team,<br/>Please find attached test automation execution results." + "<br>" + "<html><br>" + "<span> Execution Summary: </span>" + "<br>" + "<h3><table align=\"left\"></h3>\n\n" + "<table width=\"600\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" style=\"border:1px solid #ccc;\">\r\n" + "<tr>\r\n" + "<td>Total Module Run</td>\r\n" + "<td><span style=\"font-weight:bold\">Total Module Passed</span></td>\r\n" + "<td><span style=\"font-weight:bold\">Total Module Failed</span></td>\r\n" + "<td><span style=\"font-weight:bold\">Failed%</span></td>\r\n" + "</tr>\r\n" + insertModuleResult1 + "</table>\r\n\n\n" + "<br>" + "<span style=\"font-weight: normal;\"> Execution Details: </span>" + "<table align=\"left\">\r\n" + "<table width=\"600\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" style=\"border:1px solid #ccc;\">\r\n" + "<tr>\r\n" + "<td><span style=\"font-weight:bold\">" + moduleName + "</span></td>\r\n" + "<td><span style=\"font-weight:bold\">" + moduleResult + "</span></td>\r\n" + "</tr>\r\n" + insertModuleResult + "</table>\r\n\n\n" + "</html>";
    }

    private static String generateEmailBody() {
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Hi Team,<br/>Please find attached test automation execution results.<br><br>");
        emailBody.append("<html>");
        emailBody.append("<h3>Overall Summary:</h3>");
        emailBody.append("<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\" style=\"border:1px solid black;\">");
        emailBody.append("<tr><th>Module</th><th>Result</th></tr>");
        emailBody.append((updateModuleResult()));
        emailBody.append("</table>");
        emailBody.append("<br/><b>Failure Percentage:</b>");
        emailBody.append("<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\" style=\"border:1px solid black;\">");
        emailBody.append("<tr><th>Total Tests</th><th>Passed</th><th>Failed</th><th>Failure %</th></tr>");
        emailBody.append(updatePercentageOffailure());
        emailBody.append("</table>");
        emailBody.append("<br/>Regards,<br>Automation Team");
        emailBody.append("</html>");
        return emailBody.toString();
    }

    public static void sendEmailReport() throws MessagingException, IOException {
        String subject = "Test Execution Report";
        boolean enableAttachment = true;
//        Generate email body with execution details
        String emailBody = generateEmailBody();
//        Send the email
        sendMail(USERNAME, PASSWORD, TO, CC, BCC, subject, emailBody, ExcelUpdate.xlpath, ExcelUpdate.xlFileName, enableAttachment);
    }

    public static void sendMail(String userName, String password, String[] to, String[] cc, String[] bcc, String subject, String emailBody, String attachmentPath, String fileName, boolean enableAttachment) throws MessagingException, IOException {
//        SMTP configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", "587");
//        Create session
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
//        Build email message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userName));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", to)));
        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(String.join(",", cc)));
        message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(String.join(",", bcc)));
        message.setSubject(subject);
//        Email body and attachments
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);
        if (enableAttachment) {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentPath);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(fileName);
            multipart.addBodyPart(attachmentPart);
        }
        message.setContent(multipart);
//        Send email
        Transport.send(message);
        System.out.println("Email sent successfully!");
    }

    public static StringBuilder updateModuleResult() {
        ArrayList<String> moduleResults = new ArrayList<>(Arrays.asList("Module1,Pass", "Module2,Fail", "Module3,Pass"));
        StringBuilder builder = new StringBuilder();
        for (String module : moduleResults) {
            String[] result = module.split(",");
            builder.append("<tr>");
            builder.append("<td>").append(result[0]).append("</td>");
            if ("Pass" .equalsIgnoreCase(result[1])) {
                builder.append("<td style='color:green;font-weight:bold;'>").append(result[1]).append("</td>");
            } else {
                builder.append("<td style='color:red;font-weight:bold;'>").append(result[1]).append("</td>");
            }
            builder.append("</tr>");
        }
        return builder;
    }

    public static StringBuilder updatePercentageOffailure() {
        int totalTests = 19;
        int failedTests = 6;
        int passedTests = totalTests - failedTests;
        float passPercentage = ((float) passedTests / totalTests) * 100;
        return new StringBuilder().append("<tr>").append("<td>").append(totalTests).append("</td>").append("<td>").append(passedTests).append("</td>").append("<td>").append(failedTests).append("</td>").append("<td>").append(String.format("%.2f", passPercentage)).append("%</td>").append("</tr>");
    }

    public static void main(String[] args) {
        try {
            ExcelUpdate.createExcel();
            ExcelUpdate.writeData(" bshj hh bhsh ", "PASS", "");

            sendEmailReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}