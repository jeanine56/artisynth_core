package artisynth.core.gui.jythonconsole;

import java.io.*;
import javax.swing.*;
import org.python.util.*;
import org.python.core.*;

/**
 * A Jython console that operates from a Swing-based ReadlinePanel that
 * provides interactive emacs-style editing.
 */
public class JythonPanelConsole extends InteractiveConsole {

   protected ReadlinePanel myConsole;
   protected ReadlinePanel.OutputWriter myWriter;
   protected BufferedReader myReader;
   protected Thread myThread;
   //protected boolean myPromptSent = false;
   //protected boolean myInterruptReq = false;
   //protected boolean myQuitReq = false;
   //protected boolean myInsideExec = false;

   JythonConsoleImpl myImpl;

   protected class InterruptHandler implements ReadlinePanel.InterruptHandler {

      public InterruptHandler () {
      }

      public void interrupt() {
         interruptThread();
      }
   }

   public boolean interruptThread() {
      synchronized (myImpl) {
         if (myImpl.isInsideExec()) {
            myImpl.requestInterrupt();
            if (myThread != null) {
               myThread.interrupt();
            }
            return true;
         }
         else {
            return false;
         }
      }
   }

   public boolean interruptRequestPending() {
      return myImpl.interruptRequestPending();
   }

   public void quitThread() {
      myImpl.requestQuit();
   }

   public JythonPanelConsole() {
      super();

      myConsole = new ReadlinePanel();
      myConsole.setLineWrap (true);
      myWriter = new ReadlinePanel.OutputWriter(myConsole);
      myReader = myConsole.createReader();
      setErr (myWriter);
      setOut (myWriter);
      myImpl = new JythonConsoleImpl (this);
      myImpl.setupSymbols();
   }

   public void sleep (int msec) throws InterruptedException {
      Thread.sleep (msec);
   }

   public void exit (int code) {
      System.exit (code);
   }

   private static String protectWindowsSlashes(String filename) {
      StringBuilder out = new StringBuilder();
      char[] chars = filename.toCharArray();
      
      for (int idx=0; idx <chars.length; idx++) {
         if (chars[idx] == '\\' ) {
            out.append("\\\\");  // double it up
            if (idx+1 < chars.length && chars[idx+1] == '\\') {
               // skip next char if was already doubled
               idx++;
            }
         } else  {
            out.append(chars[idx]);
         }
      }
      return out.toString();
   }

   protected void requestScript (String fileName, String[] args) {
      // protect single slashes
      fileName = protectWindowsSlashes(fileName);
      StringBuilder sb = new StringBuilder();
      sb.append ("script('"+fileName+"'");
      if (args != null) {
         sb.append (",[");
         for (int k=0; k<args.length; k++) {
            if (k > 0) {
               sb.append(",");
            }
            sb.append ("'"+args[k]+"'");
         }
         sb.append ("]");
      }
      sb.append (")");
      myConsole.setLineText (sb.toString());
      myConsole.acceptLine();
   }

   public void executeScript (String fileName) throws IOException {
      myImpl.executeScript (fileName);
   }

   @Override
   public void interact(String banner, PyObject file) {
      myImpl.interact (banner, file);
   }

   @Override
   public String raw_input (PyObject prompt) {
      prompt = myImpl.killRedundantPrompt (prompt);
      String promptStr = prompt.toString();
      myConsole.requestInput (promptStr);
      String line;
      try {
         line = myReader.readLine();
      }
      catch (IOException e) {
         System.out.println (e);
         line = "";
      }
      return line;
   }

   @Override
   public void runcode(PyObject code) {
      try {
         myImpl.runcode (code);
      }
      catch (Exception e) {
         // paranoid - just in case an InterruptException slips through between
         // calls to raise/lowerCodeExecLevel within myImpl.runcode(). No need
         // to do anything; just catch the signal
         if (e instanceof RuntimeException) {
            // shouldn't happen ...
            throw (RuntimeException)e;
         }
         else if (!(e instanceof InterruptedException)) {
            // shouldn't happen ...
            throw new RuntimeException(e);
         }
      }
      if (myThread != null) {
         // cleaning interrupted status, in case exec was interrupted
         // but an exception was not actually thrown
         myThread.interrupted();
      }
   }

   public void interact () {
      myThread = Thread.currentThread();
      myConsole.setInterruptHandler (new InterruptHandler());
      interact (null, null);
   }

   public static void main (String[] args) {

      JythonPanelConsole console = new JythonPanelConsole();

      JFrame frame = new JFrame();
      JScrollPane pane = new JScrollPane(console.myConsole);
      pane.setVerticalScrollBarPolicy (
         ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      pane.setHorizontalScrollBarPolicy (
         ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      frame.getContentPane().add (pane);
      frame.pack();
      frame.setVisible (true);

      console.interact();
   }

}

