package gui;

import controller.SportFacilityCtr;
import model.SportFacility;
import model.LinkedList;

import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

public class SportFacilityGUI extends Composite {

	private SportFacilityCtr sportFacilityCtr;

	// Tables
	private Table table;

	// Text Fields
	private Text txt_id;
	private Text txt_name;
	private Text txt_maxPersons;
	private Text txt_type;
	private Text txt_cost;
	private Text txt_numberOfLocations;
	private Text search_id;

	private Button btn_delete;
	private Button btn_save;
	private Button btn_edit;
	private Button btn_create;

	public SportFacilityGUI(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new BorderLayout(0, 0));

		sportFacilityCtr = new SportFacilityCtr();

		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.WEST);
		composite_2.setLayout(new BorderLayout(0, 0));

		Composite composite_8 = new Composite(composite_2, SWT.NONE);
		composite_8.setLayoutData(BorderLayout.NORTH);
		RowLayout rl_composite_8 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_8.center = true;
		composite_8.setLayout(rl_composite_8);

		Label lbl_name = new Label(composite_8, SWT.NONE);
		lbl_name.setText("Name:");

		search_id = new Text(composite_8, SWT.BORDER);
		search_id.setLayoutData(new RowData(116, SWT.DEFAULT));

		Button btn_search = new Button(composite_8, SWT.NONE);
		btn_search.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int id = 0;
				boolean error = false;
				try {
					id = Integer.parseInt(search_id.getText());
				} catch (NumberFormatException ex) {
					MessageBox box = new MessageBox(getShell(), 0);
					box.setText("Error");
					box.setMessage("There was an error. Please try again");
					box.open();
					error = true;
				}
				if (!error)
					showSearchedRooms(id);
			}
		});
		btn_search.setText("Search");

		Composite composite_9 = new Composite(composite_2, SWT.NONE);
		composite_9.setLayout(new FillLayout(SWT.HORIZONTAL));

		ScrolledComposite scrolledComposite = new ScrolledComposite(
				composite_9, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setWidth(40);
		tblclmnId.setText("ID");

		TableColumn tblclmnFirstName = new TableColumn(table, SWT.NONE);
		tblclmnFirstName.setWidth(100);
		tblclmnFirstName.setText("Type");

		TableColumn tblclmnSurName = new TableColumn(table, SWT.NONE);
		tblclmnSurName.setWidth(100);
		tblclmnSurName.setText("Price");
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table
				.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		Composite composite_3 = new Composite(this, SWT.NONE);
		composite_3.setLayout(new BorderLayout(0, 0));

		Composite composite_4 = new Composite(composite_3, SWT.NONE);
		composite_4.setLayoutData(BorderLayout.SOUTH);
		composite_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Composite composite_5 = new Composite(composite_4, SWT.NONE);
		composite_5.setLayout(new RowLayout(SWT.HORIZONTAL));

		btn_delete = new Button(composite_5, SWT.CENTER);
		btn_save = new Button(composite_5, SWT.CENTER);
		btn_save.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				boolean create = false;
				int sportFacilityId = 0;
				String name = null;
				int maxPersons = 0;
				double cost = 0.0;
				String type = null;
				int numberOfLocations = 0;
				try {
					sportFacilityId = Integer.parseInt(txt_id.getText());
				} catch (NumberFormatException ex) {
					create = true;
					boolean error = false;
					try {
						name = txt_name.getText();
						maxPersons = Integer.parseInt(txt_maxPersons.getText());
						cost = Double.parseDouble(txt_cost.getText());
						type = txt_type.getText();
						numberOfLocations = Integer
								.parseInt(txt_numberOfLocations.getText());
					} catch (Exception exc) {
						MessageBox box = new MessageBox(getShell(), 0);
						box.setText("Error");
						box.setMessage("There was an error. Please try again");
						box.open();
						error = true;
					}
					if (!error) {
						boolean ok = true;
						try {
							sportFacilityId = sportFacilityCtr
									.insertSportFacility(name, maxPersons,
											cost, type, numberOfLocations);
						} catch (Exception ex1) {
							ok = false;
							MessageBox box = new MessageBox(getShell(), 0);
							box.setText("Error");
							box.setMessage("There was an error. Please try again");
							box.open();
						}
						if (ok) {
							showAllRooms();
							showRoom(sportFacilityId);
						}
					}
				}
				if (!create) {
					boolean error = false;
					try {
						name = txt_name.getText();
						maxPersons = Integer.parseInt(txt_maxPersons.getText());
						cost = Double.parseDouble(txt_cost.getText());
						type = txt_type.getText();
						numberOfLocations = Integer
								.parseInt(txt_numberOfLocations.getText());
					} catch (Exception exc) {
						MessageBox box = new MessageBox(getShell(), 0);
						box.setText("Error");
						box.setMessage("There was an error. Please try again");
						box.open();
						error = true;
					}
					if (!error) {
						boolean ok = true;
						try {
							sportFacilityCtr.updateSportFacility(
									sportFacilityId, name, maxPersons, cost,
									type, numberOfLocations);
						} catch (Exception ex1) {
							MessageBox box = new MessageBox(getShell(), 0);
							box.setText("Error");
							box.setMessage("There was an error. Please try again");
							box.open();
							ok = false;
						}
						if (ok) {
							showAllRooms();
							showRoom(sportFacilityId);
						}
					}

				}
			}
		});
		btn_edit = new Button(composite_5, SWT.CENTER);
		btn_create = new Button(composite_5, SWT.CENTER);

		btn_delete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int sportFacilityId = 0;
				boolean error = false;
				try {
					sportFacilityId = Integer.parseInt(txt_id.getText());
				} catch (NumberFormatException ex) {
					error = true;
					MessageBox box = new MessageBox(getShell(), 0);
					box.setText("Error");
					box.setMessage("There was an error. Please try again");
					box.open();
				}
				if (!error) {
					try {
						sportFacilityCtr.removeSportFacility(sportFacilityId);
					} catch (Exception ex) {
						MessageBox box = new MessageBox(getShell(), 0);
						box.setText("Error");
						box.setMessage("There was an error. Please try again");
						box.open();
					}
					showAllRooms();
					resetFields();
					btn_delete.setEnabled(false);
					btn_edit.setEnabled(false);
					btn_save.setEnabled(false);
					btn_create.setEnabled(true);
				}
			}
		});
		btn_delete.setLayoutData(new RowData(75, 50));
		btn_delete.setText("DELETE");
		btn_delete.setEnabled(false);

		btn_save.setLayoutData(new RowData(75, 50));
		btn_save.setText("SAVE");
		btn_save.setEnabled(false);

		btn_edit.setLayoutData(new RowData(75, 50));
		btn_edit.setEnabled(false);
		btn_edit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btn_delete.setEnabled(false);
				btn_edit.setEnabled(false);
				btn_save.setEnabled(true);
				btn_create.setEnabled(false);

				txt_id.setEditable(false);
				txt_name.setEditable(true);
				txt_maxPersons.setEditable(true);
				txt_cost.setEditable(true);
				txt_type.setEditable(true);
				txt_name.setEditable(true);
				txt_numberOfLocations.setEditable(true);
				search_id.setEditable(true);
			}
		});
		btn_edit.setText("EDIT");

		btn_create.setLayoutData(new RowData(75, 50));
		btn_create.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btn_delete.setEnabled(false);
				btn_edit.setEnabled(false);
				btn_save.setEnabled(true);
				btn_create.setEnabled(false);

				resetFields();

				txt_id.setEditable(false);
				txt_name.setEditable(true);
				txt_maxPersons.setEditable(true);
				txt_cost.setEditable(true);
				txt_type.setEditable(true);
				txt_name.setEditable(true);
				txt_numberOfLocations.setEditable(true);
				search_id.setEditable(true);
			}
		});
		btn_create.setText("CREATE");

		Composite composite_6 = new Composite(composite_3, SWT.NONE);
		composite_6.setLayoutData(BorderLayout.CENTER);
		composite_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 40));

		Composite composite_7 = new Composite(composite_6, SWT.NONE);
		composite_7.setLayout(new GridLayout(2, false));

		Label lblId = new Label(composite_7, SWT.NONE);
		lblId.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false,
				1, 1));
		lblId.setBounds(0, 0, 55, 15);
		lblId.setText("ID:");

		txt_id = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_id = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1,
				1);
		gd_txt_id.widthHint = 203;
		txt_id.setEditable(false);
		txt_id.setLayoutData(gd_txt_id);

		Label lblName = new Label(composite_7, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblName.setText("Name:");

		txt_name = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_name = new GridData(SWT.LEFT, SWT.CENTER, true, false,
				1, 1);
		gd_txt_name.widthHint = 203;
		txt_name.setEditable(false);
		txt_name.setLayoutData(gd_txt_name);

		Label lblMaxPersons = new Label(composite_7, SWT.NONE);
		lblMaxPersons.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblMaxPersons.setText("Max persons:");

		txt_maxPersons = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_maxPersons = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txt_maxPersons.widthHint = 203;
		txt_maxPersons.setEditable(false);
		txt_maxPersons.setLayoutData(gd_txt_maxPersons);

		Label lblCost = new Label(composite_7, SWT.NONE);
		lblCost.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblCost.setText("Cost:");

		txt_cost = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_cost = new GridData(SWT.LEFT, SWT.CENTER, true, false,
				1, 1);
		gd_txt_cost.widthHint = 203;
		txt_cost.setEditable(false);
		txt_cost.setLayoutData(gd_txt_cost);

		Label lblType = new Label(composite_7, SWT.NONE);
		lblType.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblType.setText("Type:");

		txt_type = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_type = new GridData(SWT.LEFT, SWT.CENTER, true, false,
				1, 1);
		gd_txt_type.widthHint = 203;
		txt_type.setEditable(false);
		txt_type.setLayoutData(gd_txt_type);

		Label lblNumberOfLocations = new Label(composite_7, SWT.NONE);
		lblNumberOfLocations.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER,
				false, false, 1, 1));
		lblNumberOfLocations.setText("Number of locations:");

		txt_numberOfLocations = new Text(composite_7, SWT.BORDER);
		txt_numberOfLocations.setEditable(false);
		txt_numberOfLocations.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));

		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				int id = Integer.parseInt(table.getItem(
						table.getSelectionIndex()).getText(0));
				showRoom(id);
			}
		});
		showAllRooms();
	}

	private void showAllRooms() {
		table.clearAll();
		table.setItemCount(0);
		LinkedList<SportFacility> sportFacilities = sportFacilityCtr
				.getAllSportFacilities();
		for (SportFacility sportFacility : sportFacilities) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, String.valueOf(sportFacility.getSportFacilityId()));
			item.setText(1, sportFacility.getName());
			item.setText(2, String.valueOf(sportFacility.getCost()));
		}

	}

	private void showSearchedRooms(int id) {
		table.clearAll();
		table.setItemCount(0);
		SportFacility sportFacility = sportFacilityCtr
				.searchSportFacilityById(id);
		if (sportFacility != null) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, String.valueOf(sportFacility.getSportFacilityId()));
			item.setText(1, sportFacility.getName());
			item.setText(2, String.valueOf(sportFacility.getCost()));
		}
	}

	private void showRoom(int id) {
		SportFacility sportFacility = sportFacilityCtr
				.searchSportFacilityById(id);
		txt_id.setText(String.valueOf(sportFacility.getSportFacilityId()));
		txt_name.setText(sportFacility.getName());
		txt_maxPersons.setText(String.valueOf(sportFacility.getMaxPersons()));
		txt_cost.setText(String.valueOf(sportFacility.getCost()));
		txt_type.setText(sportFacility.getType());
		txt_numberOfLocations.setText(String.valueOf(sportFacility
				.getNumberOfLocations()));

		txt_id.setEditable(false);
		txt_name.setEditable(false);
		txt_maxPersons.setEditable(false);
		txt_cost.setEditable(false);
		txt_type.setEditable(false);
		txt_numberOfLocations.setEditable(false);

		btn_create.setEnabled(true);
		btn_edit.setEnabled(true);
		btn_delete.setEnabled(true);
		btn_save.setEnabled(false);
	}

	public void resetFields() {
		txt_id.setText("");
		txt_name.setText("");
		txt_maxPersons.setText("");
		txt_cost.setText("");
		txt_type.setText("");
		txt_numberOfLocations.setText("");
		search_id.setText("");
	}
}