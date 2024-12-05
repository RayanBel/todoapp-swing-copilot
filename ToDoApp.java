public class ToDoApp {
    public static void main(String[] args) {
        // Asegura que la interfaz se ejecute en el Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                /* Código comentado manualmente */
                // try {
                //     // Intenta establecer el Look and Feel del sistema
                //     UIManager.setLookAndFeel(
                //         UIManager.getSystemLookAndFeelClassName()
                //     );
                // } catch (Exception e) {
                //     e.printStackTrace();
                // }
                /* Fin del comentado manual */
                
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}
