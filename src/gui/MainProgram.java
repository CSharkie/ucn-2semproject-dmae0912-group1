package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainProgram {

	public MainProgram() {
	}

	protected static Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainProgram window = new MainProgram();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		
		shell = new Shell();
		shell.setSize(800, 600);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation (x, y);

		shell.open();
		shell.layout();
		
		shell.setText("Morocco Holiday Center");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder tabFolder = new TabFolder(composite, SWT.NONE);

		/* TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Orders");

		SalesOrderGUI orderGUI = new SalesOrderGUI(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(orderGUI);

		TabItem tbtmInvoices = new TabItem(tabFolder, SWT.NONE);
		tbtmInvoices.setText("Invoices");

		InvoiceGUI invoiceGUI = new InvoiceGUI(tabFolder, SWT.NONE);
		tbtmInvoices.setControl(invoiceGUI); */

		TabItem tbtmPersons = new TabItem(tabFolder, SWT.NONE);
		tbtmPersons.setText("Persons");

		PersonGUI personGUI = new PersonGUI(tabFolder, SWT.NONE);
		tbtmPersons.setControl(personGUI);

		/* TabItem tbtmProducts = new TabItem(tabFolder, SWT.NONE);
		tbtmProducts.setText("Products");

		ProductGUI productGUI = new ProductGUI(tabFolder, SWT.NONE);
		tbtmProducts.setControl(productGUI);

		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("Suppliers");

		SupplierGUI supplierGUI = new SupplierGUI(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(supplierGUI); */

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");

		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);

		MenuItem mntmClose = new MenuItem(menu_1, SWT.NONE);
		mntmClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		mntmClose.setText("Close");

		MenuItem mntmHe = new MenuItem(menu, SWT.NONE);
		mntmHe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(shell);
				box.setText("About");
				box.setMessage("Group nr. 1"
				+"\n\n" +
				"Cristi Toma"
				+"\n" +
				"Cristian Dan Cojocaru"
				+"\n" +
				 "Elisabeta Rebeca Roata"
				+"\n" +
				 "Laszlo Czegledi"
				+"\n" +
				 "Madalin Claudiu Danceanu");
				box.open();
			}
		});
		mntmHe.setText("About");

	}

}
