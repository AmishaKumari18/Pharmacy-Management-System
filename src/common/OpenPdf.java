/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;
import java.io.File;
import javax.swing.JOptionPane;
import dao.PharmacyUtils;

/**
 *
 * @author 91727
 */
public class OpenPdf {
    public static void openBy(String id) {
        try {
            String filePath = PharmacyUtils.billPath + id + ".pdf";
            if (new File(filePath).exists()) {
                Process p = Runtime.getRuntime().exec("cmd /c start " + filePath);
            } else {
                JOptionPane.showMessageDialog(null, "File does not exist");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
