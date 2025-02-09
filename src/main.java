import Control.controlPrincipal;
import Vista.VistaPrincipal;

public class main {

    public static void main(String[] arg){

        
        controlPrincipal ctrl = new controlPrincipal();
        
        VistaPrincipal vista = new VistaPrincipal(ctrl);
        vista.setVisible(true);

        ctrl.setVista(vista);
    }
}
