import javax.swing.JFrame;

public class PlotterButLibraryTester 
{
    public static void main(String[] args)
    {
        PlotterButLibrary plot = new PlotterButLibrary();
        plot.Plot();
        plot.setSize(800, 400);
        plot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        plot.setVisible(true);
    }    
}
