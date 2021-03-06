package ensiscolarite.com.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author aaa
 */
public class MainFrm extends javax.swing.JFrame {

    /**
	 * 
	 */
	private ArrayList<Double> lesMoyennes;

	public ArrayList<Double> lesMoyennes() {
		return lesMoyennes;
	}

	public void setLesMoyennes(ArrayList<Double> lesMoyennes) {
		this.lesMoyennes = lesMoyennes;
	}
	
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form MainFrm
     */
    public MainFrm(ArrayList<Double> allMoyennes) {
    	setLesMoyennes(allMoyennes);
        initComponents(	);
        this.setLocationRelativeTo(null);
        pnChart.setLayout(new java.awt.BorderLayout());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnChart = new javax.swing.JPanel();
        btn3DPie = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnChart.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnChartLayout = new javax.swing.GroupLayout(pnChart);
        pnChart.setLayout(pnChartLayout);
        pnChartLayout.setHorizontalGroup(
            pnChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnChartLayout.setVerticalGroup(
            pnChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
        );

        btn3DPie.setText("Graphique des moyennes des �l�ves");
        btn3DPie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3DPieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 226, Short.MAX_VALUE)
                        .addComponent(btn3DPie)
                        .addGap(18, 18, 18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    	.addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn3DPie))
                .addContainerGap())
        );

        pack();
        
       
    }

    private void btn3DPieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3DPieActionPerformed
    	 // create dataset for pie chart
    	
    	ArrayList<Double> lesMoyennes = lesMoyennes();
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Moyennes bon", lesMoyennes.get(2));
        dataset.setValue("Moyenne interm�diaire", lesMoyennes.get(1));
        dataset.setValue("Moyenne mauvaise", lesMoyennes.get(0));
        // create pir chart
        JFreeChart chart = ChartFactory.createPieChart3D(
                "Moyennes des �l�ves", // chart title                   
                dataset, // data 
                true, // include legend                   
                true,
                false);
        // set chart properties
        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(270);
        plot.setForegroundAlpha(0.60f);
        plot.setInteriorGap(0.02);
        // create chart panel the add it to swing panel in  jframe
        ChartPanel chartPanel = new ChartPanel(chart);
        pnChart.removeAll();        // clear panel before add new chart
        pnChart.add(chartPanel, BorderLayout.CENTER);
        pnChart.validate();         // refresh panel to display new chart
        
    }//GEN-LAST:event_btn3DPieActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn3DPie;
    private javax.swing.JPanel pnChart;
    // End of variables declaration//GEN-END:variables

}
