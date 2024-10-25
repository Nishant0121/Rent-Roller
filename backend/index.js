import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import nodemailer from "nodemailer";
import PDFDocument from "pdfkit";

dotenv.config();

const app = express();
const port = 5000;

// Middleware
app.use(cors());
app.use(express.json());

// Nodemailer configuration
const transporter = nodemailer.createTransport({
  host: "smtp.gmail.com",
  port: 587,
  secure: false,
  auth: {
    user: "rentroller35@gmail.com",
    pass: "wodsndozelosdpws",
  },
});

// API endpoint to send email
app.post("/api/send-email", async (req, res) => {
  const { to, message } = req.body;

  if (!to || !message) {
    return res
      .status(300)
      .json({ success: false, error: "to or message missing" });
  }

  try {
    // Create a new PDF document
    const doc = new PDFDocument();
    let buffers = [];
    doc.on("data", buffers.push.bind(buffers));
    doc.on("end", async () => {
      let pdfData = Buffer.concat(buffers);

      // Send email with PDF attachment
      const info = await transporter.sendMail({
        from: "nishantpatil0121@gmail.com",
        to: to,
        subject: "Rental Details",
        text: message,
        attachments: [
          {
            filename: "rental_details.pdf",
            content: pdfData,
            contentType: "application/pdf",
          },
        ],
      });

      console.log("Message sent:", info.messageId);
      res.status(200).json({ success: true, messageId: info.messageId });
    });

    // Add content to PDF
    doc.image("RentRollerLOGO.png", 50, 45, { width: 100, height: 54 }); // Add logo
    doc
      .font("Helvetica-Bold")
      .fontSize(20)
      .text("Rent Roller Pvt. Ltd.", { align: "center" });
    doc
      .font("Helvetica-Bold")
      .fontSize(18)
      .text("Rental Details", { align: "center" });
    doc.moveDown();

    // Add message content
    doc.fontSize(12).text(message);

    // Finalize the PDF and end the stream
    doc.end();
  } catch (error) {
    console.error("Error sending email:", error);
    res.status(400).json({ success: false, error: error.message });
  }
});

app.post("/api/send-email/bill", async (req, res) => {
  const {
    to,
    totalAmount,
    customerName,
    vehicleId,
    rentDate,
    returnDate,
    mode_of_payment,
    fine,
    damageCost,
    customerId,
    rentPrice, // Ensure this is passed
  } = req.body;

  try {
    // Create a new PDF document
    const today = new Date();
    const dd = today.getDate();
    const mm = today.getMonth() + 1; //January is 0!
    const yyyy = today.getFullYear();

    const formattedDate = dd + "/" + mm + "/" + yyyy;
    const doc = new PDFDocument();
    let buffers = [];
    doc.on("data", buffers.push.bind(buffers));
    doc.on("end", async () => {
      const pdfData = Buffer.concat(buffers);

      // Send email with PDF attachment
      const info = await transporter.sendMail({
        from: process.env.EMAIL_USER,
        to: to,
        subject: "Rental Bill",
        text: "Please find attached your rental bill.",
        attachments: [
          {
            filename: "rental_bill.pdf",
            content: pdfData,
            contentType: "application/pdf",
          },
        ],
      });

      console.log("Bill sent:", info.messageId);
      res.status(200).json({ success: true, messageId: info.messageId });
    });

    // Add content to the PDF
    doc
      .image("RentRollerLOGO.png", 50, 45, { width: 100, height: 54 }) // Ensure logo path is correct
      .font("Helvetica-Bold")
      .fontSize(20)
      .text("Rent Roller Pvt. Ltd.", { align: "center" });

    doc
      .font("Helvetica-Bold")
      .fontSize(18)
      .text("Rental Bill", { align: "center" });

    doc.moveDown();

    // Bill Information Table
    doc
      .font("Helvetica")
      .fontSize(12)
      .text(`Customer Name    : ${customerName}`, 50, 150)
      .text(`Customer ID      : ${customerId}`, 50, 170)
      .text(`Vehicle ID       : ${vehicleId}`, 50, 190)
      .text(
        "___________________________________________________________________________  ",
        50,
        210,
        {
          width: 600,
        }
      )
      .text(`Rent Date        : ${rentDate}`, 50, 230)
      .text(`Actual Return Date : ${returnDate}`, 300, 230)
      .text(`Return Date      : ${formattedDate}`, 50, 250)
      .text(`Mode of Payment  : ${mode_of_payment}`, 50, 270)
      .text(`Fine             : ${fine}`, 50, 290)
      .text(`Damage Cost      : ${damageCost}`, 50, 310);

    // Add table-like structure for quantities, prices, and totals
    doc.moveDown();
    doc.text(
      "___________________________________________________________________________ ",
      50,
      370,
      {
        width: 600,
      }
    );
    doc.font("Helvetica-Bold").text("Bill Description", 50, 330);
    // doc.text("Qty (Q)", 300, 330);
    doc.text("Fine", 300, 330);
    doc.text("Amount", 400, 330);

    // Example row of products
    doc.font("Helvetica").text("Vehicle Rent", 50, 350);
    // doc.text("1", 300, 350);
    doc.text(`${fine}`, 300, 350);
    doc.text(`${totalAmount}`, 400, 350);

    // Total amounts
    doc.moveDown();
    doc.text(
      " ____________________________________________________________________________",
      50,
      370,
      {
        width: 800,
      }
    );
    doc
      .font("Helvetica-Bold")
      .text(`Net Total: ${totalAmount}`, 300, 440, { align: "right" });

    // Footer
    doc.text("Authorised Signatory", 50, 400);

    // Finalize the PDF and end the stream
    doc.end();
  } catch (error) {
    console.error("Error sending bill:", error);
    res.status(400).json({ success: false, error: error.message });
  }
});

// Start the server
app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});
