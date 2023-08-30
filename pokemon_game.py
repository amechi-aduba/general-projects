import pygame
from pygame.locals import*
import os
import random
pygame.init()


Charizard_Front = pygame.image.load(os.path.join("Images", "CharizardF.png"))
Blastoise_Front = pygame.image.load(os.path.join("Images", "BlastoiseF.png"))
Venasaur_Front = pygame.image.load(os.path.join("Images", "VenasaurF.png"))
Charizard_Back = pygame.image.load(os.path.join("Images", "CharizardB.png"))
Venasaur_Back = pygame.image.load(os.path.join("Images", "VenasaurB.png"))
Blastoise_Back = pygame.image.load(os.path.join("Images", "BlastoiseB.png"))

class Button():
   
    def __init__(self, x, y, image):
        self.image = pygame.transform.scale(image, (300,300))
        self.rect = self.image.get_rect()
        self.rect.topleft = (x,y)
        self.clicked = False
    def display_button(self, game):
        click = True 
        A = False
        position = pygame.mouse.get_pos()
        SCREEN.blit(self.image, (self.rect.x, self.rect.y))
        if self.rect.collidepoint(position):
            if pygame.mouse.get_pressed()[0] == 1 and self.clicked == False:
                self.clicked = True
                game.clicked = True
                SCREEN.fill(black)

click = False   
text = pygame.font.SysFont('Constantia', 20) 
class bbutton():
    button_color = (25,190,225)
    other_color = (75,225,255)
    click_color= (50,150,255)
    text_color = (0,0,0)
    width = 100
    height = 20
   
    def __init__(self, x, y, moves):
        self.x = x 
        self.y = y 
        self.moves = moves 
    def battle_button(self):
        pygame.init()
        global click
        A = False
        mouse_position = pygame.mouse.get_pos()
        battle_button = Rect(self.x,self.y,self.width, self.height)
        if battle_button.collidepoint(mouse_position):
            if pygame.mouse.get_pressed()[0] == 1: 
                click = True
                pygame.draw.rect(SCREEN2, self.click_color, battle_button)
            elif pygame.mouse.get_pressed()[0] == 0 and click == True:
                click = False
                A = True
            else:
                pygame.draw.rect(SCREEN2, self.other_color, battle_button)
        else:
            pygame.draw.rect(SCREEN2, self.button_color, battle_button)
        
        
        display_text = text.render(self.moves,True, self.text_color)
        txt_len = display_text.get_width()
        SCREEN2.blit(display_text, (self.x + int(self.width / 2) - int(txt_len / 2), self.y + 5) )
        return A


flamethrower = bbutton(700,600, "Flamethrower")
razor_leaf = bbutton(700,600, "Razor Leaf")
water_gun = bbutton(700,600, "Water Gun")
tackle = bbutton(700, 700, "Tackle")

class Pokemon_Health():
    def __init__(self, x, y, health, max_health):
        self.x = x
        self.y = y
        self.health = health
        self.max_health = max_health

    
    def draw(self, health):
        self.health = health
        ratio = self.health / self.max_health
        pygame.draw.rect(SCREEN2, red, (self.x, self.y, 150, 20))
        pygame.draw.rect(SCREEN2, green, (self.x, self.y, 150 * ratio, 20))
     
class Pokemon:
    def __init__(self, attack, moves, health, type):
        self.attack = attack
        self.moves = moves
        self.health = health
        self.type = type

    def display_pokemon(self,image, x,y, size):
        new_image = pygame.transform.scale((image),(size))
        SCREEN2.blit(new_image, (x,y))
    
    def display_battle(self, pokemon, other_pokemon):
        self.display_pokemon(self, pokemon, 0,400, (450,450))
        self.display_pokemon(self, other_pokemon, 450, 10, (450,450))


##############
#BACKGROUND# 
WIDTH, HEIGHT, = 900, 900
SCREEN = pygame.display.set_mode((WIDTH,HEIGHT))
pygame.display.set_caption("Amechi and Mauro's Pokemon Game")

#battle background
WIDTH, HEIGHT = 900, 900
SCREEN2 = pygame.display.set_mode((WIDTH,HEIGHT))

#############
#COLORS#
white = (255,255,255)
green = (0,255,0)
red = (255,0,0)
blue = (0,0,255)
black = (0,0,0)
fuchisa = (255, 0, 255)
gray = (128, 128, 128)
lime = (0, 128, 0)
maroon = (128, 0, 0)
navyblue = (0, 0, 128)
olive = (128, 128, 0)
purple = (128, 0, 128)
teal = (0,128,128)

##############
#FPS
frames = 60
##############

class Main:
    def __init__(self):
        self.Charizard_Front = pygame.image.load(os.path.join("Images", "CharizardF.png"))
        self.Blastoise_Front = pygame.image.load(os.path.join("Images", "BlastoiseF.png"))
        self.Venasaur_Front = pygame.image.load(os.path.join("Images", "VenasaurF.png"))
        self.Charizard_Back = pygame.image.load(os.path.join("Images", "CharizardB.png"))
        self.Venasaur_Back = pygame.image.load(os.path.join("Images", "VenasaurB.png"))
        self.Blastoise_Back = pygame.image.load(os.path.join("Images", "BlastoiseB.png"))
        self.clicked = False
    
    def new_screen(self):
        Battle_background = pygame.image.load(os.path.join("Images", "Pokemon_BackGround.png"))
        Battle_background = pygame.transform.scale(Battle_background,(WIDTH, HEIGHT))
        SCREEN2.blit(Battle_background, (0,0))
        
    def background(self,charizard_clicked, venasaur_clicked, blastoise_clicked):
        if (self.clicked):
            new_window(charizard_clicked, venasaur_clicked, blastoise_clicked)
        else:
            Start_background = pygame.image.load(os.path.join("Images", "Start_backgound.png"))
            Start_background = pygame.transform.scale(Start_background,(WIDTH, HEIGHT))
            SCREEN.blit(Start_background, (0,0))
    
    def print_text(self,text, location, size, color):
        pygame.font.init()
        font = pygame.font.SysFont("Cooper", size, bold=True, italic=False) #this will make the font bold but not italicized with a size of 32
        block = font.render(text, location, color)
        SCREEN.blit(block, location)
    
    def choose_pokemon(self): #this will be the function where the user chooses their pokemon
        pygame.init()
        self.print_text('Python Pokemon', (200, 60), 80, red)
        charizard = Button(10, 450, self.Charizard_Front)
        venasaur = Button(590, 450, self.Venasaur_Front)
        blastoise = Button(300, 450, self.Blastoise_Front)
        charizard.display_button(self)
        venasaur.display_button(self)
        blastoise.display_button(self)
        
        if charizard.clicked == True:
            charizard_clicked = True
            venasaur_clicked = False
            blastoise_clicked = False
            new_window(40, ['flamethrower', 'tackle'], 250, 'fire',charizard_clicked,venasaur_clicked, blastoise_clicked)
        elif venasaur.clicked == True:
            venasaur_clicked = True
            charizard_clicked = False
            blastoise_clicked = False
            new_window(40, ['razor leaf', 'tackle'], 250, 'grass',charizard_clicked,venasaur_clicked, blastoise_clicked)
        elif blastoise.clicked == True:
            blastoise_clicked = True
            venasaur_clicked = False
            charizard_clicked = False
            new_window(40, ['water gun', 'tackle'], 250, 'water',charizard_clicked,venasaur_clicked, blastoise_clicked)

def main():
    game = Main()
    clock = pygame.time.Clock()
    run = True
    charizard_clicked = False
    venasaur_clicked = False
    blastoise_clicked = False
    clock.tick(frames) #makes the game run at 60 frames per second
    while run:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                run = False
        game.background(charizard_clicked, venasaur_clicked, blastoise_clicked)
        game.choose_pokemon()
        pygame.display.update()
    pygame.quit()

    

    

def new_window(attack, moves, health, type, charizard_clicked,venusaur_clicked,blastoise_clicked):
    game = Main()
    clock = pygame.time.Clock()
    run = True
    clock.tick(frames) #makes the game run at 60 frames per second

    if charizard_clicked == True:
        pokemon = Charizard_Back
        health = 250
        attack = 40
        other_pokemon = Venasaur_Front
        other_type = 'grass'
        other_health = 250
        other_attack = 40
    elif blastoise_clicked == True:
        pokemon = Blastoise_Back
        other_pokemon = Charizard_Front
        other_type = 'fire'
        other_health = 250
        other_attack = 40
    elif venusaur_clicked == True:
        pokemon = Venasaur_Back
        other_pokemon = Blastoise_Front
        other_type = 'water'
        other_health = 250
        other_attack = 40
    
    
    if other_type == 'fire':
        if type == 'grass':
            #super_effective
            other_attack *= 2
            health -= attack
        elif type == 'water':
            #not effective
            other_attack /= 2
    elif other_type == 'water':
        if type == 'fire':
            #super_effective
            other_attack *= 2
            health -= attack
        elif type == 'grass':
            #not effective
            other_attack /= 2
            health -= attack
    elif other_type == 'grass':
        if type == 'water':
            #super_effective
            other_attack *= 2
            other_health -= attack
        elif type == 'fire':
            #not effective
            attack /= 2
            health -= attack

    while run:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                run = False
        game.new_screen()
        Pokemon.display_battle(Pokemon, pokemon, other_pokemon)
        pokemon_health_bar = Pokemon_Health(500,100,health,250)
        other_pokemon_health_bar = Pokemon_Health(100,100,other_health, 250)
        pokemon_health_bar.draw(health)
        other_pokemon_health_bar.draw(other_health)
        if charizard_clicked == True:
            tackle.battle_button()
            flamethrower.battle_button()
            if flamethrower == True:
                if other_type == 'grass':
                    attack *= 2
                    other_health -= attack
                elif other_type == 'water':
                    #not effective
                    attack /= 2
                    other_health -= attack
            pygame.display.update()
        if venusaur_clicked == True:
            tackle.battle_button()
            razor_leaf.battle_button()
            if razor_leaf == True:
                if other_type == 'water':
                    #super_effective
                    other_attack *= 2
                    other_health -= attack
                elif other_type == 'fire':
                    #not effective
                    other_attack /= 2
                    other_health -= attack
        if blastoise_clicked == True:
            tackle.battle_button()
            water_gun.battle_button()
            if water_gun == True:
                if other_type == 'fire':
                    #super_effective
                    attack *= 2
                    other_health -= attack
                elif other_type == 'grass':
                    #not effective
                    attack /= 2
                    other_health -= attack
            

        pygame.display.update()
    pygame.quit()

if __name__ == "__main__":
    pygame.init()
    main()

