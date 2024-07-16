import roundButtonUtil.roundButton;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class calculador extends JFrame {
    public calculador() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Calculadora");
        setSize(500, 700);
        setResizable(false);
        ImageIcon calculadora = new ImageIcon("src/calculadora.png");
        setIconImage(calculadora.getImage());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.black);
        final String[] currentOperation = {""};

        JLabel calcTela = new JLabel("");
        calcTela.setBounds(30, 30, 440, 100);
        calcTela.setOpaque(true);
        calcTela.setBackground(Color.white);
        calcTela.setForeground(Color.black);
        calcTela.setFont(new Font("Arial", Font.PLAIN, 20));
        calcTela.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(calcTela);

        roundButton[] bt = new roundButton[20];
        int startX = 30;
        int startY = 150;
        byte count = 0;
        final boolean[] booleanFoda = new boolean[1];
        final boolean[] variavelNulaFoda = new boolean[1];

        for (byte j = 0; j < 5; j++) {
            for (byte i = 0; i < 4; i++) {
                if (count > 3 && count < 7) {
                    bt[count] = new roundButton(String.valueOf(count - 3));
                    bt[count].addActionListener(e -> {
                        if (booleanFoda[0]) {
                            calcTela.setText("");
                            removedNumbers.clear();
                            booleanFoda[0] = false;
                        }
                    });
                } else if (count > 7 && count < 11) {
                    bt[count] = new roundButton(String.valueOf(count - 4));
                    bt[count].addActionListener(e -> {
                        if(booleanFoda[0]) {
                            calcTela.setText("");
                            removedNumbers.clear();
                            booleanFoda[0] = false;
                        }
                    });
                } else if (count > 11 && count < 15) {
                    bt[count] = new roundButton(String.valueOf(count - 5));
                    bt[count].addActionListener(e -> {
                        if(booleanFoda[0]) {
                            calcTela.setText("");
                            removedNumbers.clear();
                            booleanFoda[0] = false;
                        }
                    });
                } else {
                    switch (count) {
                        case 0 -> bt[count] = new roundButton("AC");
                        case 1 -> bt[count] = new roundButton("←");
                        case 2 -> bt[count] = new roundButton("%");
                        case 3 -> bt[count] = new roundButton("÷");
                        case 7 -> bt[count] = new roundButton("X");
                        case 11 -> bt[count] = new roundButton("-");
                        case 15 -> bt[count] = new roundButton("+");
                        case 19 -> bt[count] = new roundButton("=");
                        case 18 -> bt[count] = new roundButton(",");
                        case 17 -> bt[count] = new roundButton('0');
                    }
                }
                if (bt[count] != null) {
                    bt[count].setBounds(startX + 105 * i, startY + 105 * j, 97, 97);
                    bt[count].setFont(new Font("Arial", Font.PLAIN, 20));
                    bt[count].setForeground(Color.white);
                    bt[count].setBackground(Color.DARK_GRAY);
                    bt[count].setBorderPainted(false);
                    panel.add(bt[count]);
                    byte finalCount = count;
                    bt[count].addActionListener(e -> {
                            String currentText = calcTela.getText();
                        if(bt[finalCount].getText().equals("←")) {
                            if(!currentText.isEmpty()) {
                                calcTela.setText(currentText.substring(0, currentText.length() -1));
                            }
                        }
                        else if (bt[finalCount].getText().equals("AC")) {
                            currentText = "";
                            removedNumbers.clear();
                            calcTela.setText(currentText);
                        }
                        else {
                            calcTela.setText(currentText + bt[finalCount].getText());
                            calcTela.revalidate();
                            calcTela.repaint();
                            variavelNulaFoda[0] = true;
                        }
                        switch (bt[finalCount].getText()) {
                            case "-":
                                if(!booleanFoda[0] && variavelNulaFoda[0]) {
                                    currentOperation[0] = "-";
                                    storeRemovedNumbers(currentText);
                                    currentText = "";
                                    calcTela.setText(currentText);
                                }
                                else {
                                    calcTela.setText("");
                                    removedNumbers.clear();
                                }
                                break;
                            case "+":
                                if(booleanFoda[0]) {
                                    currentOperation[0] = "+";
                                    storeRemovedNumbers(currentText);
                                    currentText = "";
                                    calcTela.setText(currentText);
                                }
                                else {
                                    calcTela.setText("");
                                    removedNumbers.clear();
                                }
                                break;
                            case "X":
                                if(booleanFoda[0]) {
                                    currentOperation[0] = "*";
                                    storeRemovedNumbers(currentText);
                                    currentText = "";
                                    calcTela.setText(currentText);
                                }
                                else {
                                    calcTela.setText("");
                                    removedNumbers.clear();
                                }
                                break;
                            case "÷":
                                if(booleanFoda[0]) {
                                    currentOperation[0] = "/";
                                    storeRemovedNumbers(currentText);
                                    currentText = "";
                                    calcTela.setText(currentText);
                                }
                                else {
                                    calcTela.setText("");
                                    removedNumbers.clear();
                                }
                                break;
                            case "%":
                                if(booleanFoda[0]) {
                                    currentOperation[0] = "%";
                                    storeRemovedNumbers(currentText);
                                    currentText = "";
                                    calcTela.setText(currentText);
                                }
                                else {
                                    calcTela.setText("");
                                    removedNumbers.clear();
                                }
                                break;
                            case "=":
                                if(!removedNumbers.isEmpty()) {
                                    storeRemovedNumbers(currentText.replace("=", ""));
                                    if (!removedNumbers.isEmpty()) {
                                        currentText = String.valueOf(calculo(currentOperation[0], removedNumbers.get(0), removedNumbers.get(1)));
                                    }
                                    calcTela.setText(currentText);
                                    removedNumbers.clear();
                                    booleanFoda[0] = true;
                                    break;
                                }
                                else {
                                    calcTela.setText("");
                                    removedNumbers.clear();
                                }
                        }
                    });

                }
                count++;
            }
        }

        add(panel);
    }

    private final List<Integer> removedNumbers = new ArrayList<>();

    private void storeRemovedNumbers(String text) {
        if (!text.isEmpty()) {
                int removedNumber = Integer.parseInt(text);
                removedNumbers.add(removedNumber);
        }
    }

    private float calculo(String character, int a, int b) {
        switch(character) {
            case "-" -> { return a - b;}
            case "+" -> {return a + b;}
            case "/" -> {return (float) a/b;}
            case "%" -> {return (float) b * ((float) a / 100); }
        }
        if (removedNumbers.isEmpty()) {
            return 0;
        }
        else {
            System.out.println(character);
           return 505;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(calculador::new);
    }
}
