public class StringMatching extends javax.swing.JDialog {
    //info[0] = indices, info[1] = matches
    static String[] info = new String[2];
    
    
    
    public StringMatching(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        buttonGroup1.add(bruteForceRadioButton);
        buttonGroup1.add(KMPRadioButton);
        buttonGroup1.add(BMRadioButton);
        lengthOfString.setVisible(false);
        lengthOfStringLabel.setVisible(false);
        jLabel2.setVisible(false);
        lengthOfPattern.setVisible(false);
        lengthOfPatternLabel.setVisible(false);
        jLabel4.setVisible(false);
        jLabel3.setVisible(false);
        jLabel8.setVisible(false);
        stringOutput.setVisible(false);
        patternOutput.setVisible(false);
    }
    //method to generate random text
    static String getAlphaNumericString(int n){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+
        "0123456789"+"abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for(int i = 0; i<n; i++){
            int index = (int)(AlphaNumericString.length()*Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
    
    
    
    
    
    
    
    public static String[] BruteForceStringMatch(String text, String pattern) {
        java.util.Objects.requireNonNull(text, "text");
        java.util.Objects.requireNonNull(pattern, "pattern");
        StringBuilder positions = new StringBuilder();
        int count = 0;
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            int j = 0;
            while (j < pattern.length() && pattern.charAt(j) == text.charAt(i + j)) j++;
            if (j == pattern.length()) { positions.append(i).append(", "); count++; }
        }
        return new String[] {positions.toString(), Integer.toString(count)};
    }


    
    
    
    
     public static String[] KMP(String text, String pattern) {
        java.util.Objects.requireNonNull(text, "text");
        java.util.Objects.requireNonNull(pattern, "pattern");
        if (pattern.isEmpty()) return BruteForceStringMatch(text, pattern);
        int[] prefix = new int[pattern.length()];
        for (int i = 1, length = 0; i < pattern.length();) {
            if (pattern.charAt(i) == pattern.charAt(length)) prefix[i++] = ++length;
            else if (length > 0) length = prefix[length - 1];
            else prefix[i++] = 0;
        }
        StringBuilder positions = new StringBuilder();
        int count = 0;
        for (int i = 0, j = 0; i < text.length();) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++; j++;
                if (j == pattern.length()) {
                    positions.append(i-j).append(", "); count++;
                    j = prefix[j-1];
                }
            } else if (j > 0) j = prefix[j-1];
            else i++;
        }
        return new String[] {positions.toString(), Integer.toString(count)};
    }
 


    //BM Algorithm

    static int NO_OF_CHARS = Character.MAX_VALUE + 1;
    //get max
    static int max(int a, int b) {
        if (a > b)
            return a;
        else
            return b;
    }

    //ABCBALAA
    // BC



    //Fills the table for chars
    static void badCharHeuristic(String str, int size, int badchar[]) {
        //all elements become -1
        for (int i = 0; i < NO_OF_CHARS; i++)
            badchar[i] = -1;

        //fills the actual value of the last occurrence of a char
        for (int i = 0; i < size; i++)
            badchar[(int) str.charAt(i)] = i;
            
    }


    //the main function
    static String[] BMStringMatch(String txt, String pat) {
        String indices="";
        int matches = 0;
        java.util.Objects.requireNonNull(txt, "text");
        java.util.Objects.requireNonNull(pat, "pattern");
        if (pat.isEmpty()) return BruteForceStringMatch(txt, pat);
        int m = pat.length();
        int n = txt.length();

        int badchar[] = new int[NO_OF_CHARS];

        //fills the table in array badchar
        badCharHeuristic(pat, m, badchar);

        //abcbba
        // bba
        //b: 1, a: 2 *:3

        //b:1, b:2, a:1

        //s is how much we will shift
        int s = 0;
        while (s <= (n - m)) {
            int j = m - 1;
            //checks to see if the words match or not
            while (j >= 0 && pat.charAt(j) == txt.charAt(s+j))
                j--;
            //if j is negative, there was a match, else we increase s
            if (j < 0) {
                indices += s+", ";
                matches++;
                //System.out.println("Patterns occur at shift " + s);
                //checks to see if its at the end of the word or not
                //txt[s+m] is the next m length word after
                if(s+m<n)
                    s+=m-badchar[txt.charAt(s+m)];
                else
                    s++;
            } else //shifts the pattern to the next bad character in the text
            /*we use max because if the last occurrence of a bad character in
              the pattern is on the right of the current character, it will do
              a negative shift. but the main operation should be
              j - badchar[txt.charAt(s+j)]*/
              //

              //
                s += max(1, j - badchar[txt.charAt(s+j)]);
        }
        
        return new String[] {indices, Integer.toString(matches)};
    }
    
   

    //lines 212-432, code to design the GUI                     
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lengthOfStringLabel = new javax.swing.JLabel();
        lengthOfString = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lengthOfPatternLabel = new javax.swing.JLabel();
        lengthOfPattern = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        patternTextLabel = new javax.swing.JLabel();
        patternText = new javax.swing.JTextField();
        stringTextLabel = new javax.swing.JLabel();
        stringText = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        randomStringCheckBox = new javax.swing.JCheckBox();
        randomPatternCheckBox = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        numberOfMatches = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        matchingIndex = new javax.swing.JLabel();
        bruteForceRadioButton = new javax.swing.JRadioButton();
        KMPRadioButton = new javax.swing.JRadioButton();
        BMRadioButton = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        timeInSeconds = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        stringOutput = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        patternOutput = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lengthOfStringLabel.setText("Length of String");

        jLabel2.setText("letters");

        lengthOfPatternLabel.setText("Length of Pattern");

        jLabel4.setText("letters");

        patternTextLabel.setText("Pattern Text");

        stringTextLabel.setText("String Text");

        jButton1.setText("Find Substring");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        randomStringCheckBox.setText("Random String");
        randomStringCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomStringCheckBoxActionPerformed(evt);
            }
        });

        randomPatternCheckBox.setText("Random Pattern");
        randomPatternCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomPatternCheckBoxActionPerformed(evt);
            }
        });

        jLabel5.setText("Number of Matches: ");

        numberOfMatches.setText("0");

        jLabel6.setText("Matching Indices");

        matchingIndex.setText("-");

        bruteForceRadioButton.setText("Brute Force");

        KMPRadioButton.setText("KMP");

        BMRadioButton.setText("BM");

        jLabel7.setText("Time in Seconds");

        timeInSeconds.setText("0");

        jLabel9.setText("ns");

        jLabel3.setText("String:");

        stringOutput.setText("-");

        jLabel8.setText("Pattern: ");

        patternOutput.setText("-");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lengthOfPatternLabel)
                                .addComponent(lengthOfStringLabel))
                            .addComponent(bruteForceRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lengthOfString, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lengthOfPattern, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(randomPatternCheckBox)
                            .addComponent(randomStringCheckBox))
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(numberOfMatches, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(timeInSeconds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(matchingIndex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(stringTextLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(patternTextLabel, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(stringText)
                                    .addComponent(patternText)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(KMPRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(patternOutput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(stringOutput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addComponent(BMRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lengthOfStringLabel)
                    .addComponent(lengthOfString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(randomStringCheckBox))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lengthOfPatternLabel)
                    .addComponent(lengthOfPattern, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(randomPatternCheckBox))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bruteForceRadioButton)
                    .addComponent(BMRadioButton)
                    .addComponent(KMPRadioButton))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stringTextLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(stringText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(patternTextLabel)
                            .addComponent(patternText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(numberOfMatches)
                        .addComponent(jLabel3))
                    .addComponent(stringOutput))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(matchingIndex)
                    .addComponent(jLabel8)
                    .addComponent(patternOutput))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(timeInSeconds)
                    .addComponent(jLabel9))
                .addGap(20, 20, 20))
        );

        pack();
    }                    

    private void randomStringCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        //removes unnecessary labels/textboxes and adds necessary labels/textboxes
        if(randomStringCheckBox.isSelected()){
            stringText.setVisible(false);
            stringTextLabel.setVisible(false);
            lengthOfString.setVisible(true);
            lengthOfStringLabel.setVisible(true);
            jLabel2.setVisible(true);
            jLabel3.setVisible(true);
            stringOutput.setVisible(true);
        }
        else{
            stringText.setVisible(true);
            stringTextLabel.setVisible(true);
            lengthOfString.setVisible(false);
            lengthOfStringLabel.setVisible(false);
            jLabel2.setVisible(false);
            jLabel3.setVisible(false);
            stringOutput.setVisible(false);
        }
    }                                                    

    private void randomPatternCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {         
        //removes unnecessary labels/textboxes and adds necessary labels/textboxes                                             
        if(randomPatternCheckBox.isSelected()){
            patternText.setVisible(false);
            patternTextLabel.setVisible(false);
            lengthOfPattern.setVisible(true);
            lengthOfPatternLabel.setVisible(true);
            jLabel4.setVisible(true);
            jLabel8.setVisible(true);
            patternOutput.setVisible(true);
        } else {
            patternText.setVisible(true);
            patternTextLabel.setVisible(true);
            lengthOfPattern.setVisible(false);
            lengthOfPatternLabel.setVisible(false);
            jLabel4.setVisible(false);
            jLabel8.setVisible(false);
            patternOutput.setVisible(false);
        }
    }                                                     

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String str;
        String pattern;
        long startTime = 0;
        long endTime = 0;
        String information[] = null;
        if(randomStringCheckBox.isSelected())
            str = getAlphaNumericString(Integer.parseInt(lengthOfString.getText()));
        else
            str = stringText.getText();
        if(randomPatternCheckBox.isSelected())
            pattern = getAlphaNumericString(Integer.parseInt(lengthOfPattern.getText()));
        else
            pattern = patternText.getText();
        //calculates the time
        if(bruteForceRadioButton.isSelected()){
            startTime = System.nanoTime();
            information = BruteForceStringMatch(str, pattern);
            endTime = System.nanoTime();
        } else if(KMPRadioButton.isSelected()){
            startTime = System.nanoTime();
            information = KMP(str, pattern);
            endTime = System.nanoTime();
        } else if(BMRadioButton.isSelected()){
            startTime = System.nanoTime();
            information = BMStringMatch(str, pattern);
            endTime = System.nanoTime();
        }
        //Put the output in the GUI
        if(information != null){
            matchingIndex.setText(information[0]);
            numberOfMatches.setText(information[1]);
            timeInSeconds.setText(Long.toString((endTime-startTime)));
            patternOutput.setText(pattern);
            stringOutput.setText(str);
        }
        
        
        
    }                                        

    //GUI Code
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StringMatching.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StringMatching.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StringMatching.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StringMatching.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                StringMatching dialog = new StringMatching(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JRadioButton BMRadioButton;
    private javax.swing.JRadioButton KMPRadioButton;
    private javax.swing.JRadioButton bruteForceRadioButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField lengthOfPattern;
    private javax.swing.JLabel lengthOfPatternLabel;
    private javax.swing.JTextField lengthOfString;
    private javax.swing.JLabel lengthOfStringLabel;
    private javax.swing.JLabel matchingIndex;
    private javax.swing.JLabel numberOfMatches;
    private javax.swing.JLabel patternOutput;
    private javax.swing.JTextField patternText;
    private javax.swing.JLabel patternTextLabel;
    private javax.swing.JCheckBox randomPatternCheckBox;
    private javax.swing.JCheckBox randomStringCheckBox;
    private javax.swing.JLabel stringOutput;
    private javax.swing.JTextField stringText;
    private javax.swing.JLabel stringTextLabel;
    private javax.swing.JLabel timeInSeconds;
    // End of variables declaration                   
}
