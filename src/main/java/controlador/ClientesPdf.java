package controlador;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Clientes;
import services.ClientesService;
import services.ClientesServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/clienteshtml", "/clientes.pdf"})
public class ClientesPdf extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Simular servicio para obtener lista de clientes
        ClientesService service = new ClientesServiceImplement();
        List<Clientes> clientes = service.listar();

        // Detectar el formato solicitado
        String servletPath = req.getServletPath();

        if (servletPath.endsWith(".pdf")) {
            generarPDF(clientes, resp); // Generar PDF
        } else {
            generarHTML(clientes, req, resp); // Generar HTML
        }
    }

    private void generarPDF(List<Clientes> clientes, HttpServletResponse resp) throws IOException {
        try {
            // Configurar la respuesta HTTP para descargar el archivo PDF
            resp.setContentType("application/pdf");
            resp.setHeader("Content-Disposition", "attachment; filename=clientes.pdf");

            // Crear el documento PDF
            Document document = new Document();
            PdfWriter.getInstance(document, resp.getOutputStream());
            document.open();

            // Título del documento
            document.add(new Paragraph("Listado de Clientes", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
            document.add(new Paragraph(" ")); // Espacio en blanco

            // Crear una tabla PDF
            PdfPTable table = new PdfPTable(5); // 5 columnas
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Encabezados de la tabla
            String[] headers = {"ID Cliente", "Nombre", "Apellido", "Género", "Teléfono"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(5);
                table.addCell(cell);
            }

            // Agregar datos de los clientes
            for (Clientes cliente : clientes) {
                table.addCell(String.valueOf(cliente.getIdClientes())); // ID Cliente
                table.addCell(cliente.getNombre()); // Nombre
                table.addCell(cliente.getApellido()); // Apellido
                table.addCell(cliente.getGenero()); // Género
                table.addCell(cliente.getTelefono()); // Teléfono como String
            }

            // Agregar la tabla al documento
            document.add(table);

            // Cerrar el documento
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error al generar el archivo PDF", e);
        }
    }

    private void generarHTML(List<Clientes> clientes, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Generar HTML con tabla y enlace para descargar PDF
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.print("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>Listado Clientes</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listado Clientes</h1>");
            out.println("<p><a href=\"" + req.getContextPath() + "/clientes.pdf\">Descargar PDF</a></p>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>ID Cliente</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Apellido</th>");
            out.println("<th>Género</th>");
            out.println("<th>Teléfono</th>");
            out.println("</tr>");
            clientes.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getIdClientes() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getApellido() + "</td>");
                out.println("<td>" + p.getGenero() + "</td>");
                out.println("<td>" + p.getTelefono() + "</td>");
                out.println("</tr>");
            });
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
