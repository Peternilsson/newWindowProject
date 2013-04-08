
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;

import blog.Author;
import blog.Blogg;
import blog.Category;
import blog.Post;

public class MainWindow {
	private static Text text;
	private static Text text_1;
	private static Text text_3;
	private static List list;
	private static List list_1;
	private static List list_2;
	private static Button btnSkicka;
	public static Blogg blogg = new Blogg();
	public static Category category = new Category();
	private static Text text_2;
	private static Text text_4;
	private static int temp;
	private static int temp2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(559, 419);
		shell.setText("SWT Application");
		
		Label lblFrnamn = new Label(shell, SWT.NONE);
		lblFrnamn.setBounds(10, 13, 59, 14);
		lblFrnamn.setText("F\u00F6rnamn:");
		
		Label lblEfternamn = new Label(shell, SWT.NONE);
		lblEfternamn.setBounds(10, 40, 59, 14);
		lblEfternamn.setText("Efternamn:");
		
		text = new Text(shell, SWT.BORDER);
		text.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		text.setBounds(99, 10, 129, 19);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		text_1.setBounds(99, 35, 129, 19);
		
		Label lblInlgg = new Label(shell, SWT.NONE);
		lblInlgg.setBounds(7, 253, 59, 14);
		lblInlgg.setText("Inl\u00E4gg");
		
		btnSkicka = new Button(shell, SWT.NONE);
		btnSkicka.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int the_index = 0;
				
				Author a = null;
				for(; the_index < blogg.authors.size(); the_index++){
					if((text.getText().equals( blogg.authors.get(the_index).firstName) && text_1.getText().equals( blogg.authors.get(the_index).lastName))){
						a = blogg.authors.get(the_index);
						break;
					}
				}
				if(a == null){
					a = new Author(text.getText(), text_1.getText());
					list_2.add(text.getText() + " " + text_1.getText());
					blogg.authors.add(a);
				}
				
				
				Category c = null;
				for(; the_index < blogg.categories.size(); the_index++){
					if(text_4.getText().equals( blogg.categories.get(the_index).name)){
						c = blogg.categories.get(the_index);
						break;
					}
				}
				if(c == null){
					c = new Category(text_4.getText());
					blogg.categories.add(c);
					list_1.add(text_4.getText());
				}
				Post post = new Post(text_3.getText(),text_2.getText(),20130326, a, c);
				blogg.posts.add(post);
				list.add(text_3.getText());
			}
		});
		btnSkicka.setBounds(99, 361, 94, 28);
		btnSkicka.setText("Skicka");
		
		Button btnAvbryt = new Button(shell, SWT.NONE);
		btnAvbryt.setBounds(199, 361, 94, 28);
		btnAvbryt.setText("Avbryt");
		
		Label lblTitel = new Label(shell, SWT.NONE);
		lblTitel.setBounds(7, 227, 59, 14);
		lblTitel.setText("Rubrik:");
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(99, 224, 129, 19);
		
		Label lblKategori = new Label(shell, SWT.NONE);
		lblKategori.setBounds(10, 135, 59, 14);
		lblKategori.setText("Kategori");
		
		list = new List(shell, SWT.BORDER);
		list.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = list.getSelectionIndex();
				String firstNameStr = blogg.posts.get(i).author.firstName;
				String lastNameStr = blogg.posts.get(i).author.lastName;
				String titleStr = blogg.posts.get(i).title;
				String categoryStr = blogg.posts.get(i).category.name;
				String contentStr = blogg.posts.get(i).content;
				temp = list.getSelectionIndex();
				temp2 = list.getSelectionIndex();
				text.setText(firstNameStr);
				text_1.setText(lastNameStr);
				text_3.setText(titleStr);
				text_4.setText(categoryStr);
				text_2.setText(contentStr);
			}
		});
		list.setBounds(378, 13, 153, 215);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		text_2.setBounds(99, 250, 216, 105);
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		text_4.setBounds(99, 132, 129, 19);
		
		list_1 = new List(shell, SWT.BORDER);
		list_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int j = list_1.getSelectionIndex();
				String thiscategoryStr = list_1.getItem(j);
				blogg.posts.get(temp).category.name = thiscategoryStr;
				text_4.setText(thiscategoryStr);
			}
		});
		list_1.setBounds(99, 152, 129, 66);
		
		list_2 = new List(shell, SWT.BORDER);
		list_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int k = list_2.getSelectionIndex();
				String authorStr = list_2.getItem(k);
				String[] result = authorStr.split("\\s+");
				blogg.authors.get(temp2).firstName = result[0];
				blogg.authors.get(temp2).lastName = result[1];
				
				text.setText(result[0]);
				text_1.setText(result[1]);
			}
		});
		list_2.setBounds(99, 55, 129, 66);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public Button getBtnSkicka() {
		return btnSkicka;
	}
}
