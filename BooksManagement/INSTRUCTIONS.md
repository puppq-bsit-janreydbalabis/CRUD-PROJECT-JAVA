The errors you are seeing are because your Eclipse project is out of sync with the project files. To fix this, you need to update the Maven project in Eclipse.

Please follow these steps in your Eclipse IDE:

1.  Right-click on the 'BooksManagement' project in the 'Project Explorer' view.
2.  In the context menu, go to 'Maven'.
3.  In the 'Maven' sub-menu, click on 'Update Project...'.
4.  In the 'Update Maven Project' dialog, make sure the 'BooksManagement' project is checked.
5.  Check the box that says 'Force update of snapshots/releases'.
6.  Click the 'OK' button.

After Eclipse finishes updating the project, the errors should disappear. Please let me know if this resolves the issue.