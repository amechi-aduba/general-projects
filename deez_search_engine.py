from tkinter import *
from googlesearch import search
import webbrowser

deez_window = Tk()
deez_window.geometry("1920x1080")
deez_window.title("deez search engine")
#font name bilo or helvetica

#main window
main = Canvas(deez_window, width = 1920, height = 1080, bg="skyblue")

#search = Text(deez_window, height = 5, width=52)
label = Label(deez_window, text = "seach online with deez...")
label.config(font = ('helvetica 12'))

#creating the text
main.create_text(615, 150, text="D", fill ="blue", font =('helvetica 100 bold italic'))
main.create_text(710, 150, text="e", fill ="red", font =('helvetica 100 bold italic'))
main.create_text(800, 150, text="e", fill ="green", font =('helvetica 100 bold italic'))
main.create_text(885, 150, text="z", fill ="yellow", font =('helvetica 100 bold italic'))
main.pack()

#user input functions
def create_searchbar():
    global searchbar
    main.bind('<Return>')
    searchbar = Entry(main, font= ('helvetica 15'), relief= SUNKEN)
    searchbar.place(x=615, y= 305, width= 375, height= 40)
    search_button = Button(main, text= 'search', bg = 'grey', fg= 'light grey', font= ('helvetica 15 bold'), command= search_functions)
    search_button.place(x= 510, y= 305)
def clicked(link, clicked):
    if clicked == True:
      webbrowser.open_new_tab(link)
def search_functions():
    x = 600
    y = 450
    google_search = searchbar.get()
    for i in search(google_search, num= 5, stop=5, pause= 2):
        link = Label(main, text= i, fg= 'blue', font= ('helvetica 15 bold'), command= webbrowser.open_new_tab(i))
        link.place(x=x, y= y)
        y = y + 50
        link.bind("<Button1", command= clicked)
        link.pack()
        clicked = False

    
        
#search bar
main.create_rectangle(500, 300, 1000, 350, fill= 'light grey')
main.create_rectangle(500, 300, 600, 350, fill= 'grey')
#main.create_text(550, 325, text= 'search', font=('helvetica 15 bold') )
create_searchbar()
deez_window.mainloop()