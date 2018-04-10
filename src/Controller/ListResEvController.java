/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AffichEventClientController.d;
import Entities.Evenement;
import Entities.ReserEv;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import services.ReserEvServices;

/**
 * FXML Controller class
 *
 * @author lv
 */
public class ListResEvController implements Initializable {

    @FXML
    private TableView<?> tableRes;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> numtel;
    @FXML
    private TableColumn<?, ?> mail;
    @FXML
    private TableColumn<?, ?> ident;
    @FXML
    private Button tirragee;
 public static int evt ; 
    @FXML
    private TableColumn<ReserEv, String> id;
    public static int idR;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void iData(Evenement e) {
       ReserEvServices v =  new  ReserEvServices();
        System.out.println(e.getId());
            ArrayList arrayList = (ArrayList) v.AfficherAllReseC(e.getId());
            ObservableList observablelist = FXCollections.observableArrayList(arrayList);
     
            tableRes.setItems(observablelist);
                   id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
             mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
               numtel.setCellValueFactory(new PropertyValueFactory<>("tel"));
               ident.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
               tableRes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        evt = e.getId();
  }

    @FXML
    private void tirragePDF(ActionEvent event) throws IOException, DocumentException, java.io.IOException {
                Node source = (Node) event.getSource();

        FileChooser chooser = new FileChooser();

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("PDF Files(.pdf)", ".pdf");
        chooser.getExtensionFilters().add(filter);

        File file = chooser.showSaveDialog(source.getScene().getWindow());
        if (file != null) {
            exporterPdf(file);

        }
        
    }

      public void exporterPdf(File file) throws IOException, DocumentException, java.io.IOException {
         Document doc = new Document();
 ObservableList<ReserEv> r,f;
        f=(ObservableList<ReserEv>) tableRes.getItems();
        r=(ObservableList<ReserEv>) tableRes.getSelectionModel().getSelectedItems();
        ReserEv ev = new ReserEv();
        ReserEvServices Ev=new ReserEvServices();
      String nom=   f.stream().map(e->e.getNom()).reduce("    ",(a,b)->a+"\n \n     "+b);
      String prenom=   f.stream().map(e->e.getPrenom()).reduce("",(a,b)->a+"\n\n     "+b);
        
           String mail=   f.stream().map(e->e.getMail()).reduce("",(a,b)->a+"\n     "+b);
           String NumTel=   f.stream().map(e->e.getTel()).toString();
              /*.forEach((A) -> {
         A.getNom();
            
        })*/
;
          
                     
      
       
            
                PdfWriter.getInstance(doc, new FileOutputStream(file));
                doc.open();

//             doc.add(new Paragraph("-------------"));
//                Image img = Image.getInstance("‪C://Users//amaln//Desktop//liked.png");
//                img.scaleAbsoluteHeight(92);
//                img.scaleAbsoluteWidth(600);
//                img.setAlignment(Image.ALIGN_CENTER);
//                doc.add(img);
                doc.add(new Paragraph("   "));
              
                doc.add(new Phrase("Liste de reservations", FontFactory.getFont("Arial", 20, Font.BOLDITALIC)));
//          
             
                doc.add(new Paragraph("   "));

                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);
                PdfPCell cell;

                cell = new PdfPCell(new Phrase("Nom", FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(cell);
            
               

                cell = new PdfPCell(new Phrase("Prenom", FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(cell);
  
                cell = new PdfPCell(new Phrase("Email", FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(cell);
                 
                cell = new PdfPCell(new Phrase("Num Tel", FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(cell);
                
             // String pass= generate(8);
              cell = new PdfPCell(new Phrase("identifiant", FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(cell);
                
                  System.out.println(ev);
                      
                 ////////////////////////////////////////////////////////////////////////////
             ReserEv rs = new ReserEv();
               ReserEvServices res = new ReserEvServices();
               
           List<ReserEv>  lRes = new ArrayList<ReserEv>();
                System.out.println(evt);
                lRes = res.AfficherAllReseC(evt);
             
               for (ReserEv a : lRes) {

         

         
             
                 cell = new PdfPCell(new Phrase(a.getNom(), FontFactory.getFont("Comic Sans MS", 12)));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.WHITE);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(a.getPrenom(), FontFactory.getFont("Comic Sans MS", 12)));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.WHITE);
                    table.addCell(cell);

                     cell = new PdfPCell(new Phrase(a.getMail(), FontFactory.getFont("Comic Sans MS", 12)));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.WHITE);
                    table.addCell(cell);
                    
                    cell = new PdfPCell(new Phrase(String.valueOf(a.getTel()), FontFactory.getFont("Comic Sans MS", 12)));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.WHITE);
                    table.addCell(cell);
                     cell = new PdfPCell(new Phrase(String.valueOf(a.getIdentifiant()), FontFactory.getFont("Comic Sans MS", 12)));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.WHITE);
                    table.addCell(cell);
         
                
                /////////////////////////////////////////////////////////////////////////////
      
                
               

         }
               doc.add(table);
       doc.close();
                Desktop.getDesktop().open(file);}
  
class URLBuilder {
    private StringBuilder folders, params;
    private String connType, host;

    void setConnectionType(String conn) {
        connType = conn;
    }

    URLBuilder(){
        folders = new StringBuilder();
        params = new StringBuilder();
    }

    URLBuilder(String host) {
        this();
        this.host = host;
    }

    void addSubfolder(String folder) {
        folders.append("/");
        folders.append(folder);
    }

    void addParameter(String parameter, String value) {
        if(params.toString().length() > 0){params.append("&");}
        params.append(parameter);
        params.append("=");
        params.append(value);
    }

    String getURL() throws URISyntaxException, MalformedURLException {
        URI uri = new URI(connType, host, folders.toString(),
                params.toString(), null);
        return uri.toURL().toString();
    }

    String getRelativeURL() throws URISyntaxException, MalformedURLException{
        URI uri = new URI(null, null, folders.toString(), params.toString(), null);
        return uri.toString();
    }
    }}
    


