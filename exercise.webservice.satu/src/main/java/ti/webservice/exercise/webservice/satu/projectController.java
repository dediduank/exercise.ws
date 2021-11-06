/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ti.webservice.exercise.webservice.satu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Dedi Wardani
 */
@Controller

public class projectController {

    ArrayList<List<String>> daftar = new ArrayList<>();//resource data

    void insertdata() {
        daftar.add(Arrays.asList("Makanan", "Nasi Goreng"));
        daftar.add(Arrays.asList("Minuman", "Es Teh"));
        daftar.add(Arrays.asList("Buah", "Semangka"));
    }

    @RequestMapping("/viewalldata")
    public String getData(Model model, HttpServletRequest data) {

        if (daftar.isEmpty()) {
            insertdata();
        }

        String caridata = data.getParameter("product");//variable from index
        String myBtn = data.getParameter("btn");//variable from index
        String datanotfound = "";

        List<String> buffer = new ArrayList<>(); //temporary arraylist
        ArrayList<List<String>> buffer2 = new ArrayList<>(); //temporary arraylist

        ArrayList<List<String>> senddata = new ArrayList<>();//store data to arraylist for view data

        for (int idx = 0; buffer2.size() < daftar.size(); idx++) { //menyimpan data source awal untuk ditampilkan
            buffer2.add(daftar.get(idx));
        }

        if (myBtn.equals("1")) {

            for (int idx = 0; idx < daftar.size(); idx++) { //proses pencarian data
                buffer = daftar.get(idx);
                if (buffer.get(0).contains(caridata)) {
                    senddata.add(daftar.get(idx)); //menyimpan data ketika ditemukan
                    break;
                }
            }
            if (senddata.isEmpty()) {
                datanotfound = "Tidak ditemukan";
            }
            model.addAttribute("dataproduct", senddata);
        } else {
            for (int idx = 0; idx < daftar.size(); idx++) {
                buffer = daftar.get(idx);
                if (buffer.get(0).contains(caridata)) {
                    daftar.remove(idx);
                    break;
                }
            }

            if (buffer2.size() != daftar.size()) {
                datanotfound = "Data Berhasil Dihapus";
            }else{
                datanotfound = "Data Tidak Ditemukan";
            }
            model.addAttribute("dataproduct", daftar);
        }
        model.addAttribute("listdata", buffer2);
        
        model.addAttribute("dataisnull", datanotfound);

        return "viewdata";
    }


}
