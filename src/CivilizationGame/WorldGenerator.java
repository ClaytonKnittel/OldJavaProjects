package CivilizationGame;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class WorldGenerator extends JFrame implements Runnable, MouseListener {
    private static final long serialVersionUID = 1L;
    public static int[][] s=new int[200][200];
    public static int[][] p=new int[200][200];
    public static int[][] type=new int[200][200];
    public static int[][] clouds=new int[200][200];
    public static int[][] territories=new int[200][200];
    public static int[][][] cloudPos=new int[200][200][2];
    public static int[][][] desertColor=new int[16][16][3];
    public static int[][][] treeColor=new int [200][200][6];
    public static ArrayList<int[]> p1Troops=new ArrayList<int[]>();   //1st # is troop type, second is x pos, third is y pos, 4th is strength
    public static ArrayList<int[]> p2Troops=new ArrayList<int[]>();
    public static ArrayList<int[]> p3Troops=new ArrayList<int[]>();
    public static ArrayList<int[]> AITroops=new ArrayList<int[]>();
    public static int scale=3;
    public static double xPos=0, yPos=0;
    public static int xSpeed=0, ySpeed=0;
    private Graphics h;
    private Image im;
    public boolean loRez=true, hiRez=false, switcheroo=false, setDesertColor=true, titleScreen=true, settings=false, ready=false;
    private boolean atHome=true;
    public int red=0;
    public int green=0;
    public int blue=0;
    public int speeds=3;
    public static boolean cloudd=false;
    public static boolean neverever=true;
    public static boolean threeD=true;
    public Image img;
    public static final int WIDTH=600;
    public static final int HEIGHT=600;
    public static final boolean player2=true, player3=true;
    public static int spaceNum=0;
    public static int island1Size=8000, island2Size=3500, island3Size=800, island4Size=500, island5Size=0, island6Size=0;
    
    public boolean time=false;
    
    public Image imageS;
    
    public static int spaceNumx=0, spaceNumy=0;
    public static boolean highlightPlay=false;
    

    public static void nothing() {
        
    }
    
    /**   Game rules:
     * gold-to buy stuff (resources, faster building troops)
     * ruins: troops brought in here get enhanced, or they just give you resources
     * iron (weapons)
     * 
     * 
     * 
     * 
     * Settings:
     * City-states  0-24
     * Map type     Normal, archipelago, fractile (1 land, with holes), big lake (a big lake), 
     * Map size     Small, medium, large
     * Game pace    Quick, normal, slow, marathon
     * Game era     Pick era
     * World age    Pick how old earth is
     * 
     * 
     * 
     * Terrain:
     * 
     * resources: food  construction  gold  movement  defense
     * coast        1        0          1      1               lighthouse (+1 food, +1 production, 0)
     * desert       0        0          0      1       -10%    farm (+1 food)  trading post (+1 gold, +2 next to river)
     * grassland    2        0          0      1               farm (+1 food)  trading post (+1 gold, +2 next to river)
     * hill         0        2          0      2       +25%    farm (+1 food, must be next to fresh water)  mine (+1 production)  trading post (+1 gold, +2 next to river)
     * mountain     0        0          0     n/a      +25%
     * ocean        1        0          1      1               lighthouse (+1 food)
     * plains       1        1          0      1       -10%    farm (+1 food)  trading post (+1 gold, +2 next to river)
     * snow         0        0          0      1       -10%
     * tundra       1        0          0      1       -10%    farm (+1 food, must be next to fresh water)  trading post (+1 gold, +2 next to river)
     * forest       1        1          0      2       +25%    clear-cut (+1 food, +1 production)?  lumber mill (+1 production)  trading post (+1 gold, +2 next to river)
     * jungle       1       -1          0      2       +25%    clear-cut (+1 food, +3 production)  trading post (+1 gold, +2 next to river)
     * marsh       -1        0          0      2       -15%    drain (+3 food)  trading post (+1 gold, +2 next to river)
     * atoll        1        1          1      1
     * flood plains 2        0          0      1       -10%    farm (+1 food)  trading post (+1 gold, +2 next to river)
     * ice          0        0          0     n/a
     * lakes        2        0          1     n/a
     * oasis        3        0          1      1       -10%
     * 
     * 
     * technology tree: |         ancient era                 |           classical era               |           medieval era           |            renaissance era           |            industrial era           |                  modern era        |             atomic era            |                   information era                  |
     *                                     +-6-sailing---------10-optics----------- ------------------ ------------------+-42-compass-----
     *                +----4-pottery-------+-6-calendar----------------------------+31-philosophy-----+-31-theology-------               +-86-astronomy------------navigation----+-(arch)archaeology-------+-biology-----+-refrigeration----- --------------+-penicillin-----+-ecology---------- -telecommunications-- -globalization-----
     *                |                    +-6-writing-----------------------------31-drama and poetry-                  |-54-education---                                       |                         T-electricity- -radio-------------+-plastics------                |                  +-mobile tactics------+-particle physics--
     *                |                                                                               |                  |               +-86-acoustics-----------+-architecture---(econ)scientific theory--             +-replaceable parts--              +-atomic theory--T-nuclear fission---                     |                  |
     *                +4-animal husbandry----6-trapping-------+12-horseback riding--------------------+-31-civil service--               |                        |              |                         |             |                   +-electronics---                |                  +-advanced ballistics--                  +-future tech
     * agriculture----+                    |                  |                                       |                  +-54-chivalry---+-86(edu)banking---------T-economics------industrialization-------+-steam power- -flight------------+-ballistics---^-radar---------- -rocketry--------- -satellites----------+-nuclear fusion----
     *                +----4-archery-------+-6-the wheel--------12-mathematics------31-currency--------31-guilds----------               |                        |              +-rifling------------------             |                                  |                |                  +-robotics------------ -nanotechnology----
     *                |                                       |                    |-31engineering-----------------------+-54-machinery--+-86(chiv)printing press--              |                                       +-railroad------------combustion---+-combined arms--+-computers-------- -lasers--------------+-stealth-----------
     *                +-----4-mining-------+-6-masonry--------+-12-construction----+                  +-31metal casting--T-54-physics-----                        +-metallurgy---T-military science--------+-dynamite-----
     *                                     +-6-bronze working- ---------------------12-iron working----                  --54-steel------+-86gunpowder--------------chemistry------fertilizer---------------
     * 
     * 
     * Buildings:
     * 
     * Ancient Era:
     *          Maintenance  Effect
     * barracks   1 gold    +15% experience to all units trained in the city
     * circus     0 gold    +2 happiness, city must have horses or ivory nearby
     * granary    1 gold    +2 food, +1 more per wheat, banana, and deer resource
     * library    1 gold    +1 science per 2 people in the city
     * monument   1 gold    +2 culture
     * stone works1 gold    +1 production, +1 more per marble and stone resource, +1 happiness, city must have marble or stone nearby, city can't be built on plains
     * walls      0 gold    +5 defense, +50 HP
     * water mill 2 gold    +2 food, +1 production, city must border a river/lake
     * 
     * Technologies:
     * agriculture 1
     * animal husbandry 3
     * archery 4
     * bronze working 12
     * calendar 7
     * masonry 11
     * mining 5
     * pottery 2
     * sailing 6
     * the wheel 10
     * trapping 9
     * writing 8
     * 
     * 
     * 
     * 
     * Classical Era:
     *          Maintenance  Effect
     * aqueduct   1 gold    +40% food is carried over after a new citizen is born
     * colosseum  1 gold    +2 happiness
     * courthouse 4 gold    reduces extra unhappiness in occupied city, can only be built in occupied cities
     * lighthouse 1 gold    +1 food from fish resources, city must be build on coast
     * market     0 gold    +2 gold, +25% gold, 1 merchant specialist slot
     * mint       0 gold    +2 gold per gold & silver resource, city must be near gold or silver
     * stable     1 gold    +1 production per sheep, cattle, and horses resource nearby, +15% production for mounted units, city must have a pasture nearby
     * temple     2 gold    +2 faith, requires shrine or pyramid
     * 
     * Technologies:
     * construction 16
     * currency 19
     * drama and poetry 18
     * engineering 20
     * horseback riding 14
     * iron working 21
     * mathematics 15
     * optics 13
     * philosophy 17
     * 
     * 
     * 
     * Medieval Era
     *          Maintenance  Effect
     * armory     1 gold    +15 experience for all units trained in city, requires barracks
     * castle     0 gold    +7 defense, +25 HP, requires walls
     * forge      1 gold    +15% production for land units, +1 production per iron resource, city must have iron nearby
     * garden     1 gold    +25% great person generation, city must border a river or a lake
     * harbor     2 gold    +1 production for sea resources, forms a trade route across water, city must be built on a coast
     * university 2 gold    +33% science, +2 science per jungle, 2 scientist specialist slots, requires library
     * workshop   2 gold    +2 production, +10% production, 1 engineer specialist slot
     * 
     * Technologies:
     * chivalry
     * civil service
     * compass
     * education
     * guilds
     * machinery
     * metal casting
     * physics
     * steel
     * theology
     * 
     * 
     * 
     * Renaissance Era
     *          Maintenance  Effect
     * arsenal    0 gold    +9 defense, +25 HP, requires castle
     * bank       0 gold    +2 gold, +25% gold, 1 merchant specialist slot, requires market
     * observatory0 gold    +50% science, city must border a mountain
     * opera houes2 gold    +4 culture, 1 artist specialist slot, requires amphitheater
     * seaport    2 gold    +15% production for naval units, +1 production and gold from sea resources, city must be built on coast, requires harbor
     * theater    2 gold    +3 happiness, requires colosseum
     * windmill   2 gold    +2 production, +10% production for buildings, 1 engineer specialist slot, city can't be built on hills
     * 
     * Technologies:
     * acoustics
     * architecture
     * astronomy
     * banking
     * chemistry
     * economics
     * gunpowder
     * metallurgy
     * navigation
     * printing press
     * 
     * 
     * 
     * Industrial Era
     *          Maintenance  Effect
     * factory    3 gold    +4 production, +10% production, 2 engineer specialist slots, requires workshop, requires 1 coal
     * hospital   2 gold    +5 food, requires aqueduct
     * hydro plant3 gold    +1 production for tiles bordering a river, city must border a river, requires 1 aluminum
     * military academy1 gold +15 experience for all units trained in the city, requires armory
     * museum     3 gold    +5 culture, 2 artist specialist slots, requires opera house
     * public school3 gold  +3 science, +1 science per 2 people in city, 1 scientist specialist slot, requires university
     * stock exchange0 gold +3 gold, +33% gold, 2 merchant specialist slots, requires bank
     * 
     * Technologies:
     * archaeology
     * biology
     * dynamite
     * electricity
     * fertilizer
     * industrialization
     * military science
     * rifling
     * scientific theory
     * steam power
     * 
     * 
     * 
     * Modern Era
     *          Maintenance  Effect
     * broadcast tower3 gold+3 culture, +33% culture, requires museum
     * military base0 gold  +12 defense, +25 HP, requires arsenal
     * research lab 3 gold  +4 science, +50% science, 1 scientist specialist slot, requires public school
     * stadium    2 gold    +4 happiness, can't provide more happiness than people in the city, requires theater
     * 
     * Technologies:
     * ballistics
     * combustion
     * electronics
     * flight
     * plastics
     * radio
     * railroad
     * refrigeration
     * replaceable parts
     * 
     * 
     * 
     * Atomic Era
     *          Maintenance  Effect
     * medical lab 3 gold   +25% good is carried over after a new citizen is born, requires hospital, effects stack with the aqueduct
     * nuclear plant, 3 gold, +5 production, +15% production, requires 1 uranium, city can't have solar plant
     * solar plant 3 gold  +5 production, +15% production, city must border or be built on a desert, city can't have a nuclear plant
     * 
     * Technologies:
     * atomic theory
     * combined arms
     * computers
     * ecology
     * nuclear fission
     * penicillin
     * radar
     * rocketry
     * 
     * 
     * 
     * Information Era
     *          Maintenance  Effect
     * spaceship factory, 3 gold, +3 production, +50% production for spaceship parts, requires factory, requires 1 aluminum
     * 
     * Technologies:
     * advanced ballistics
     * future tech
     * globalization
     * lasers
     * mobile tactics
     * nanotechnology
     * nuclear fusion
     * particle physics
     * robotics
     * satellites
     * stealth
     * telecommunications
     * the internet
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * Units:
     * 
     * 
     * Civilian Units:
     *1 settler       (tech: - prod cost: 106 move: 2 str: - range str: - range: - req. resource: - notes: can found cities, city growth suspended during production)
     *2 work boat     (tech: sailing  prod cost: 30 move: 4 str: - range str: - range: - req. resource: - notes: can build fishing boats and Offshore Platform)
     *3 worker        (tech: - prod cost: 70 move: 2 str: - range str: - range: - req. resource: - notes: can build tile improvements)
     *109 missionary  (tech: - prod cost: 200 move: 4 str: 1000 range str: - range: - req. resource: - notes: spread religion)
     *110 inquisitor  (tech: - prod cost: 200 move: 3 str: 0 range str: - range: - req. resource: - notes: removes Heresy)
     * 
     * Trade Units:
     *107 caravan  (tech: animal husbandry  prod cost: 75 move: 1 str: - range str: - range: 10 req. resource: - notes: establish trade route, limited visibility)
     *108 cargo ship  (tech: sailing  prod cost: 100 move: 1 str: - range str: - range: 45 req. resource: - notes: establish trade route, limited visibility)
     * 
     * Great People:
     *4 great artist      (tech: - prod cost: - move: 2 str: - range str: - range: - req. resource: - notes: creates great work, triggers golden age)
     *5 great engineer    (tech: - prod cost: - move: 2 str: - range str: - range: - req. resource: - notes: can build manufactory)
     *6 great general     (tech: - prod cost: - move: 2 str: - range str: - range: - req. resource: - notes: +25% bonus to nearby units, can build citadel)
     *7  khan (mongolia)  (tech: (great general)  prod cost: - move: 5 str: - range str: - range: - req. resource: - notes: heals adjacent units)
     *8 great merchant    (tech: - prod cost: - move: 2 str: - range str: - range: - req. resource: - notes: can build customs house)
     *9  merchant of venice (venice)  (tech: - prod cost: - move: 2 str: - range str: - range: - req. resource: - notes: trade mission bonus)
     *10 great scientist  (tech: - prod cost: - move: 2 str: - range str:- range:- req. resource:- notes: can build academy)
     *103 great writer    (tech: - prod cost: - move: 2 str: - range str: - range: - req. resource: - notes: creates great work, creates political treaties)
     *104 great musician  (tech: - prod cost: - move: 2 str: - range str: - range: - req. resource: - notes: creates great work, concert tour)
     *105 great prophet   (tech: - prod cost: - move: 2 str: 2000 range str: - range: - req. resource: - notes: founds religion, spreads religion, builds holy site)
     *106 great admiral   (tech: - prod cost: - move: 2 str: - range str: - range: - req. resource: - notes: +15% combat bonus, can heal nearby naval units)
     *
     * 
     * Spaceship Parts:
     *11 ss booster         (tech: robotics  prod cost: 750 move: 2 str: - range str: - range: - req. resource: - notes: starship component, 3 required)
     *12 ss cockpit         (tech: satellites  prod cost: 750 move: 2 str: - range str: - range: - req. resource: - notes: starship component, 1 required)
     *13 ss engine          (tech: particle physics  prod cost: 750 move: 2 str: - range str: - range: - req. resource: - notes: starship component, 1 required)
     *14 ss stasis chamber  (tech: nanotechnology  prod cost: 750 move: 2 str: - range str: - range: - req. resource: - notes: starship component, 1 required)
     *
     *Recon Units:
     *111 pathfinder  (tech: - prod cost: 45 move: 2 str: 8 range str: - range: - req. resource: - notes: shoshone only, native tongue, ignores terrain cost)
     *
     * 
     * Ancient Era:
     * 
     * ranged:
     *15 archer                  (tech: archery  prod cost: 40 move: 2 str: 5 range str: 7 range: 2 req. resource: - notes: may not melee attack)
     *16  atlatlist (maya)       (tech: archery  prod cost: 36 move: 2 str: 5 range str: 7 range: 2 req. resource: - notes: may not melee attack)
     *17  bowman (babylon)       (tech: archery  prod cost: 40 move: 2 str: 7 range str: 9 range: 2 req. resource: - notes:)
     *18  slinger (inca)         (tech: archery  prod cost: 40 move: 2 str: 4 range str: 7 range: 2 req. resource: - notes: withdraw before melee)
     *112 hand-axe (barbarians)  (tech: archery  prod cost: 56 move: 2 str: 9 range str: 9 range: 1 req. resource: - notes: may not melee attack)
     * 
     * melee:
     *19 warrior                          (tech: - prod cost: 40 move: 2 str: 8 range str: - range: - req. resource: -)
     *20  brute (barbarians and germany)  (tech: - prod cost: 20 move: 2 str: 8 range str: - range: - req. resource: - notes:)
     *21  jaguar (aztecs)                 (tech: - prod cost: 40 move: 2 str: 8 range str: - range: - req. resource: - notes: +50% combat strength in jungle, heals +2 on kill, woodsman)
     *22  maori warrior (polynesia)       (tech: - prod cost: 40 move: 2 str: 8 range str: - range: - req. resource: - notes: reduces enemy strength 10%)
     *23 scout                            (tech: - prod cost: 25 move: 2 str: 5 range str: - range: - req. resource: - notes: ignores terrain cost)
     *24 spearman                         (tech: bronze working  prod cost: 56 move: 2 str: 11 range str: - range: - req. resource: - notes: +50% bonus vs. mounted)
     *25  hoplite (greece)                (tech: bronze working  prod cost: 56 move: 2 str: 13 range str: - range: - req. resource: - notes: +100% bonus vs. mounted)
     *26  immortal (persia)               (tech: bronze working  prod cost: 56 move: 2 str: 12 range str: - range: - req. resource: - notes: +100% bonus vs. mounted, heals 2x as fast)
     *113 phalanx (sumerian)              (tech: mining  prod cost: 56 move: 2 str: 8 range str: - range: - req. resource: - notes: +100% bonus vs. mounted)
     * 
     * mounted:
     *27 chariot archer         (tech: the wheel  prod cost: 56 move: 4 str: 6 range str: 10 range: 2 req. resource: horses notes: rough terrain penalty: no defensive bonuses)
     *28  war chariot (egypt)   (tech: the wheel  prod cost: 56 move: 5 str: 6 range str: 10 range: 2 req. resource: - notes: rough terrain penalty: no defensive bonuses)
     *29  war elephant (india)  (tech: the wheel  prod cost: 70 move: 3 str: 9 range str: 11 range: 2 req. resource: - notes: no defensive bonuses)
     * 
     * naval melee:
     *30  galley (barbarians and ottomans)  (tech: - prod cost: 50 move: 3 str: 7 range str: - range: - req. resource: - notes: can't enter deep ocean)
     *31 trireme  (tech: sailing  prod cost: 56 move: 4 str: 10 range str: - range: - req. resource: - notes: can't enter deep oceans)
     * 
     * 
     * 
     * Classical Era
     * 
     * siege weapons:
     *32 catapult          (tech: mathematics  prod cost: 75 move: 2 str: 7 range str: 8 range: 2 req. resource: iron  notes: must set up to fire, +200% vs. cities, -1 vision, no defensive bonuses)
     *33  ballista (rome)  (tech: mathematics  prod cost: 75 move: 2 str: 8 range str: 10 range: 2 req. resource: iron  notes: must set up to fire, +200% vs. cities, -1 vision, no defensive bonuses)
     * 
     * melee:
     *34 swordsmen                   (tech: iron working  prod cost: 75 move: 2 str: 14 range str: - range: - req. resource: iron  notes:)
     *35  legion (rome)              (tech: iron working  prod cost: 75 move: 2 str: 17 range str: - range: - req. resource: iron  notes: can build forts and roads)
     *36  mohawk warrior (iroquois)  (tech: iron working  prod cost: 75 move: 2 str: 14 range str: - range: - req. resource: - notes: +50% combat strength in forests/jungles)
     * 
     * mounted:
     *37 horseman                     (tech: horseback riding  prod cost: 75 move: 4 str: 12 range str: - range: - req. resource: horses  notes: can move after attacking, no defensive bonuses, -33% against cities)
     *38  companion cavalry (greece)  (tech: horseback riding  prod cost: 75 move: 5 str: 14 range str: - range: - req. resource: horses  notes: can move after attacking, great generals I, no defensive bonuses, -33% against cities)
     * 
     *   (tech: - prod cost: - move: - str: - range str: - range: - req. resource: - notes:
     * 
     * Midieval Era
     * 
     * ranged:
     *39 crossbowman            (tech: machinery  prod cost: 120 move: 2 str: 13 range str: 18 range: 2 req. resource: - notes: may not melee attack)
     *40  cho-ko-nu (china)     (tech: machinery  prod cost: 120 move: 2 str: 13 range str: 14 range: 2 req. resource: - notes: may attack twice)
     *41  longbowman (england)  (tech: machinery  prod cost: 120 move: 2 str: 13 range str: 18 range: 3 req. resource: - notes: may not melee attack)
     * 
     * siege weapons:
     *42 trebuchet         (tech: physics  prod cost: 120 move: 2 str: 12 range str: 14 range: 2 req. resource: - notes: must set up to fire, +200% vs. cities, -1 vision, no defensive bonuses)
     *43  hwach'a (korea)  (tech: physics  prod cost: 120 move: 2 str: 11 range str: 26 range: 2 req. resource: iron  notes: must set up to fire, no defensive bonuses)
     * 
     * melee:
     *44 longswordsman           (tech: steel  prod cost: 120 move: 2 str: 21 range str: - range: - req. resource: iron  notes:)
     *45  berserker (denmark)    (tech: steel  prod cost: 120 move: 3 str: 21 range str: - range: - req. resource: iron  notes: amphibious)
     *46  samurai (japan)        (tech: steel  prod cost: 120 move: 2 str: 21 range str: - range: - req. resource: iron  notes: shock I, great generals II, can build fishing boats)
     *47 pikeman                 (tech: civil service  prod cost: 90 move: 2 str: 16 range str: - range: - req. resource: - notes: +100% bonus vs. mounted)
     *48  landsknecht (germany)  (tech: civli service  prod cost: 45 move: 2 str: 16 range str: - range: - req. resource: - notes: +50% bonus vs. mounted, free pillage, double plunder)
     * 
     * mounted:
     *49 knight                        (tech: chivalry  prod cost: 120 move: 4 str: 20 range str: - range: - req. resource: horses  notes: can move after attacking, no defensive bonuses, -33% vs. cities)
     *50  camel archer (arabia)        (tech: chivalry  prod cost: 120 move: 4 str: 17 range str: 21 range: 2 req. resource: horses  notes: can move after attacking, no defensive bonuses)
     *51  conquistador (spain)         (tech: chivalry  prod cost: 120 move: 4 str: 20 range str: - range: - req. resource: horses  notes: no penalty vs. cities, +2 sight, embarkation with defense, can found cities)
     *52  keshik (mongolia)            (tech: chivalry  prod cost: 165 move: 5 str: 15 range str: 16 range: 2 req. resource: horses  notes: great generals I, quick study, can move after attacking, no defensive bonuses)
     *53  mandekalu cavalry (songhai)  (tech: chivalry  prod cost: 120 move: 4 str: 20 range str: - range: - req. resource: horses notes: can move after attacking, no defensive bonuses)
     *54  naresuan's elephant (siam)   (tech: chivalry  prod cost: 120 move: 3 str: 25 range str: - range: - req. resource: - notes: +50% vs. mounted, can move after attacking, no defensive bonuses, -33% vs. cities)
     * 
     * naval ranged:
     *55 galleass                 (tech: compass  prod cost: 100 move: 3 str: 16 range str: 17 range: 2 req. resource: - notes: can't enter deep ocean)
     *56 great galleass (venice)  (tech: compass  prod cost: 110 move: 3 str: 18 range str: 20 range: 2 req. resource: - notes: can't enter deep ocean)
     * 
     * 
     * 
     * Renaissance Era
     * 
     * siege weapons:
     *57 cannon  (tech: chemistry  prod cost: 185 move: 2 str: 14 range str: 20 range: 2 req. resource: - notes: must set up to fire, +200% vs. cities, -1 vision, no defensive bonuses)
     * 
     * gunpowder units:
     *58 musketman              (tech: gunpowder  prod cost: 150 move: 2 str: 24 range str: - range: - req. resource: - notes:)
     *59  janissary (ottomans)  (tech: gunpowder  prod cost: 150 move: 2 str: 24 range str: - range: - req. resource: - notes: +25% strength when attacking, heals +50 if enemy destroyed)
     *60  minuteman (america)   (tech: gunpowder  prod cost: 150 move: 2 str: 24 range str: - range: - req. resource: - notes: ignores terrain cost, drill I)
     *61  musketeer (france)    (tech: gunpowder  prod cost: 150 move: 2 str: 28 range str: - range: - req. resource: - notes:)
     *62  tercio (spain)        (tech: gunpowder  prod cost: 155 move: 2 str: 26 range str: - range: - req. resource: - notes: +50% vs. mounted)
     *  
     * mounted:
     *63 lancer              (tech: metallurgy  prod cost: 185 move: 4 str: 25 range str: - range: - req. resource: horses  notes: formation I, can move after attacking)
     *64  sipahi (ottomans)  (tech: metallurgy  prod cost: 185 move: 5 str: 25 range str: - range: - req. resource: horses  notes: formation I, +1 sight range, can move after attacking, free pillage)
     *  
     * naval melee:
     *65 caravel               (tech: astronomy  prod cost: 120 move: 4 str: 20 range str: - range: - req. resource: - notes: +1 sight, widthdraw before melee)
     *66  turtle ship (korea)  (tech: astronomy  prod cost: 120 move: 4 str: 36 range str: - range: - req. resource: - notes: can't enter deep ocean)
     *  
     * naval ranged:
     *67 frigate                      (tech: navigation  prod cost: 185 move: 5 str: 25 range str: 28 range: 2 req. resource: iron  notes: may not melee attack)
     *68  ship of the line (england)  (tech: navigation  prod cost: 170 move: 7 str: 30 range str: 35 range: 2 req. resource: iron  notes: +1 sight range)
     *  
     * 
     * 
     * 
     * Industrial Era
     * 
     * siege:
     *69 artillery  (tech: dynamite  prod cost: 250 move: 2 str: 21 range str: 28 range: 3 req. resource: - notes: must set up to fire, +200% vs. cities, -1 vision, no defensive bonuses, indirect fire)
     * 
     * gunpowder units:
     *70 rifleman                           (tech: rifling  prod cost: 225 move: 2 str: 34 range str: - range: - req. resource: - notes:)
     *71  norwegian ski infantry (denmark)  (tech: rifling  prod cost: 220 move: 2 str: 34 range str: - range: - req. resource: - notes: bonuses on snow, hills, and tundra)
     *  
     * mounted:
     *72 cavalry            (tech: military science  prod cost: 225 move: 4 str: 34 range str: - range: - req. resource: horses  notes: can move after attacking, -33% vs. cities, no defensive bonuses)
     *73  cossack (russia)  (tech: military science  prod cost: 225 move: 4 str: 34 range str: - range: - req. resource: horses  notes: can move after attacking, +33% vs. damaged units, -33% vs. cities, no defensive bonuses)
     * 
     * naval melee:
     *74 ironclad  (tech: steam power  prod cost: 270 move: 3 str: 45 range str: - range: - req. resource: coal  notes: +33 vs. cities, double movement in coast)
     * 
     * 
     * 
     * Modern Era
     * 
     * gunpowder units:
     *75 anti-aircraft gun         (tech: radio  prod cost: 375 move: 2 str: 50 range str: - range: - req. resource: - notes: +100% vs. armor)
     *76  foreign legion (france)  (tech: replacable parts  prod cost: 320 move: 2 str: 42 range str: - range: - req. resource: - notes: foreign lands +20% bonus)
     *77 infantry                  (tech: plastics  prod cost: 320 move: 2 str: 70 range str: - range: - req. resource: - notes:)
     * 
     * naval melee:
     *78 destroyer  (tech: electricity  prod cost: 375 move: 6 str: 55 range str: - range: - req. resource: oil  notes:+3 sight range, +100% vs. subs, intercept, indirect fire)
     * 
     * naval ranged:
     *79 battleship  (tech: telegraph  prod cost: 375 move: 3 str: 55 range str: 65 range: 3 req. resource: oil  notes: indirect fire)
     *80 carrier     (tech: flight  prod cost: 375 move: 5 str: 50 range str: - range: - req. resource: oil  notes: can carry 3 aircraft, may not melee attack)
     *81 submarine   (tech: refrigeration prod cost: 375 move: 5 str: 35 range str: 60 range: 3 req. resource: oil  notes: invisible, can see subs, may enter ice tiles, +100 on attack)
     * 
     * 
     * 
     * 
     * Atomic Era
     * 
     * siege:
     *82 rocket artillery  (tech: rocketry  prod cost: 425 move: 3 str: 45 range str: 60 range: 3 req. resource: aluminum  notes: must set up to fire, +200% vs. cities, -1 vision, no defensive bonuses, indirect fire)
     * 
     * gunpowder units:
     *83 anti-tank gun  (tech: combined arms  prod cost: 270 move: 2 str: 50 range str: - range: - req. resource: - notes: +100% vs. armor)
     *84 mobile SAM     (tech: computers  prod cost: 425 move: 3 str: 65 range str: - range: 2 req. resource: - notes: interception(100), +150% vs. air)
     *85 paratrooper    (tech: radar  prod cost: 375 move: 2 str: 65 range str: - range: - req. resource: - notes: may paratroop up to 5 spaces)
     * 
     * armored units:
     *86 tank               (tech: combustion  prod cost: 375 move: 5 str: 70 range str: - range: - req. resource: oil  notes: can move after attack, no defensive bonuses)
     *87  panzer (germany)  (tech: combustion  prod cost: 375 move: 6 str: 80 range str: - range: - req. resource: oil  notes: can move after attack, no defensive bonuses)
     * 
     * bomber:
     *88 bomber          (tech: radar  prod cost: 375 move: - str: - range str: 65 range: 10 req. resource: oil  notes: vulnerable to fighters)
     *89  b17 (america)  (tech: radar  prod cost: 375 move: - str: - range str: 70 range: 10 req. resource: oil  notes: evasion I, seige I)
     * 
     * fighter:
     *90 fighter        (tech: flight  prod cost: 375 move: - str: - range str: 45 range: 8 req. resource: oil  notes: interception(100), air sweep recon, +150% vs. bombers & helicopters)
     *91  zero (japan)  (tech: flight  prod cost: 375 move: - str: - range str: 45 range: 8 req. resource: oil  notes: interception(100), +33% vs. fighters, recon, air sweep, +150 vs. bombers & helicopters)
     * 
     * helicopter:
     *92 helicopter gunship  (tech: rocketry  prod cost: 425 move: 6 str: 60 range str: - range: - req. resource: aluminum  notes: all tile cost 1, hovering unit, +100 vs. tanks, no defensive bonuses, can't capture cities)
     * 
     * bombs:
     *93 atomic bomb  (tech: nuclear fission  prod cost: 600 move: - str: - range str: - range: 10 req. resource: uranium  notes: evation(50))
     * 
     * 
     * 
     * Information Era
     * 
     * gunpowder units:
     *94 mechanized infantry  (tech: electronics  prod cost: 375 move: 4 str: 90 range str: - range: - req. resource: - notes:)
     * 
     * naval ranged:
     *95 missile cruiser    (tech: robotics  prod cost: 425 move: 7 str: 80 range str: 100 range: 3 req. resource: - notes: can see subs, +100 vs. subs, can carry 3 cargo, interception(100))
     *96 nucelar submarine  (tech: computers  prod cost: 425 move: 6 str: 50 range str: 85 range: 3 req. resource: aluminum  notes: can carry 2 missiles, +100 vs. sub, invisible, can see subs, may enter ice tiles)
     * 
     * armored units:
     *97 modern armor       (tech: lasers prod cost: 425 move: 5 str: 100 range str: - range: - req. resource: aluminum  notes: no defensive bonuses, can move after attack)
     *98 giant death robot  (tech: nuclear fusion  prod cost: 425 move: 5 str: 150 range str: - range: - req. resource: uranium  notes: no defensive bonuses, can move after attacking)
     * 
     * bomber:
     *99 stealth bomber  (tech: stealth  prod cost: 425 move: - str: - range str: 85 range: 20 req. resource: aluminum  notes: evation(100), recon)
     * 
     * fighter:
     *100 jet fighter  (tech: lasers  prod cost: 425 move: - str: - range str: 60 range: 10 req. resource: aluminum  notes: interception(100), recon, air sweep, +150% vs. bombers & helicopters)
     * 
     * bombs:
     *101 guided missile   (tech: satellites  prod cost: 150 move: - str: - range str: 60 range: 8 req. resource: - notes: destroyed on attack, evation(100))
     *102 nuclear missile  (tech: advanced ballistics  prod cost: 1000 move: - str: - range str: - range: 12 req. resource: 2x uranium  notes: evation(50))
     * 
     */
    
    
    
    
    
    
    
    
    
    
    
    public void mousePressed (MouseEvent me) {
        int mx=me.getX();
        int my=me.getY();
        if (titleScreen) {
            if (mx>=275 && mx<=535 && my>=240 && my<=332)
                highlightPlay=true;
        }
        else if (settings) {}
    }
    public void mouseClicked (MouseEvent me) {
        int mx=me.getX();
        int my=me.getY();
        if (ready)
            spaceNum=retSpace(mx, my);
        else {
            spaceNumx=mx;
            spaceNumy=my;
        }
        repaint();
    }
    public void mouseReleased (MouseEvent me) {
        highlightPlay=false;
    }
    public void mouseEntered (MouseEvent me) {}
    public void mouseExited (MouseEvent me) {}
    
    public int retSpace(int mx, int my) {
        //0-39999
        for (int fir=0; fir<200; fir++) {
            for (int sec=0; sec<200; sec++) {
                if (mx > ((fir+1)*scale)-(int)(((double)xPos/3)*scale) && mx <= ((fir+2)*scale)-(int)(((double)xPos/3)*scale) && my > ((sec+1)*scale)-(int)(((double)yPos/3)*scale) && my <= ((sec+2)*scale)-(int)(((double)yPos/3)*scale))
                    return fir+(sec*200);
            }
        }
        return 10;
    }
    
    public void run() {
        try {
            while (true) {
                if (ready) {
                    xPos+=xSpeed;
                    yPos+=ySpeed;
                    int lalx=600-(int)(3*(600.0/(double)scale));
                    int laly=600-(int)(3*(600.0/(double)scale));
                    if (xPos>lalx)
                        xPos=lalx;
                    if (yPos>laly)
                        yPos=laly;
                    if (xPos<=0)
                        xPos=0;
                    if (yPos<=0)
                        yPos=0;
                }
                else if (settings) {
                    if (spaceNumx>=323 && spaceNumx<=487 && spaceNumy>=419 && spaceNumy<=446) {
                    	randomize();
                    	ready=true;
                    	settings=false;
                    }
                }
                else {  //
                    if (spaceNumx>=323 && spaceNumx<=487 && spaceNumy>=419 && spaceNumy<=446) {
                        System.exit(0);
                    }
                    if (spaceNumx>=323 && spaceNumx<=487 && spaceNumy>=204 && spaceNumy<=231) {
                        settings=true;
                        titleScreen=false;
                    }
                }
                repaint();
                Thread.sleep(15);
            }
        }
        catch (Exception e) {
            System.err.println("Error\nError\nError\nError\nError\nError\nError\nError\nError");
        }
    }
    
    public WorldGenerator() {
        System.out.println("Constructor");
        addKeyListener(new k());
        addMouseListener(this);
        setSize(810,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("CivClayton");
        try {
        	imageS = ImageIO.read(new File("U:/Pictures/SettlerUnit.gif"));
        } catch (IOException e) {
            System.err.println("Failure");
        }
        repaint();
    }
    
    public static void main(String args[]) {
        System.out.println("Main");
        WorldGenerator w=new WorldGenerator();
        Thread t=new Thread(w);
        t.start();
    }
    
    
    
    public class k extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int k=e.getKeyCode();
            if (ready) {
                if (k==KeyEvent.VK_SHIFT) {
                    xSpeed*=3;
                    ySpeed*=3;
                }
                if (k==KeyEvent.VK_UP) {
                    ySpeed=-1*speeds;
                }
                else if (k==KeyEvent.VK_DOWN) {
                    ySpeed=speeds;
                }
                else if (k==KeyEvent.VK_LEFT) {
                    xSpeed=-1*speeds;
                }
                else if (k==KeyEvent.VK_RIGHT) {
                    xSpeed=speeds;
                }
                else if (k==KeyEvent.VK_K) {
                    int cc=1;
                    scale++;
                    if (scale > 4) {
                        cc++;
                        scale++;
                        speeds=2;
                    }
                    if (scale>8) {
                        cc+=3;
                        scale+=3;
                    }
                    if (scale > 16) {
                        cc+=7;
                        scale+=7;
                        speeds=1;
                    }
                    double numdepix=600.0/((double)scale-cc);
                    double numofpix=600.0/((double)scale);
                    xPos+=(int)(.5*((numdepix*3)-(numofpix*3)));
                    yPos+=(int)(.5*((numdepix*3)-(numofpix*3)));
                    if (scale > 31) {
                        if (loRez)
                            switcheroo=true;
                        loRez=false;
                        hiRez=true;
                    }
                }
                else if (k==KeyEvent.VK_M) {
                    int cc=1;
                    scale--;
                    if (scale > 4) {
                        cc++;
                        scale--;
                        speeds=2;
                    }
                    if (scale > 8) {
                        cc+=3;
                        scale-=3;
                    }
                    if (scale > 16) {
                        cc+=7;
                        scale-=7;
                        speeds=1;
                    }
                    if (scale < 32) {
                        loRez=true;
                        hiRez=false;
                    }
                    if (scale<3)
                        scale=3;
                    else {
                        double numdepix=600.0/((double)scale+cc);
                        double numofpix=600.0/((double)scale);
                        xPos+=(int)(.5*((numdepix*3)-(numofpix*3)));
                        yPos+=(int)(.5*((numdepix*3)-(numofpix*3)));
                    }
                }
            }
        }
        public void keyReleased(KeyEvent e) {
            int k=e.getKeyCode();
            if (ready) {
                if (k==KeyEvent.VK_SHIFT) {
                    xSpeed/=3;
                    ySpeed/=3;
                }
                if (k==KeyEvent.VK_UP) {
                    ySpeed=0;
                }
                if (k==KeyEvent.VK_DOWN) {
                    ySpeed=0;
                }
                if (k==KeyEvent.VK_LEFT) {
                    xSpeed=0;
                }
                if (k==KeyEvent.VK_RIGHT) {
                    xSpeed=0;
                }
            }
        }
    }
    
    public static void randomize() {
        int x1=(int)(Math.random()*6+45);
        int y1=(int)(Math.random()*6+45);
        int x2=(int)(Math.random()*6+130);
        int y2=(int)(Math.random()*6+130);
        int x3=(int)(Math.random()*6+45);
        int y3=(int)(Math.random()*6+130);
        int x4=(int)(Math.random()*6+130);
        int y4=(int)(Math.random()*6+45);
        
        
        
        if (cloudd) {
            for (int i=0;i<200;i++) {
                for (int j=0;j<200;j++)
                    clouds[i][j]=1;
            }
        }
        
        
        for (int i=0;i<200;i++) {
            for (int j=0;j<200;j++)
                type[i][j]=0;
        }
        
        
        
        
        
        System.out.println("Randomize");
        s[x1][y1]=1;
        p[x1][y1]=1;
        
        
        int x=x1;
        int y=y1;
        int total=1;
        while (total<island1Size) {                    //ISLAND 1
            int chooser=(int)(Math.random()*4+1);
            if (chooser==1) {
                y--;
                if (x<6||y<6) {
                    y++;
                }
                else if (x>120 || y>120) {
                    y++;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=1;
                    total++;
                }
            }
            else if (chooser==2) {
                x--;
                if (x<6||y<6) {
                    x++;
                }
                else if (x>120 || y>120) {
                    x++;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=1;
                    total++;
                }
            }
            else if (chooser==3) {
                y++;
                if (x<6||y<6) {
                    y--;
                }
                else if (x>120 || y>120) {
                    y--;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=1;
                    total++;
                }
            }
            else if (chooser==4) {
                x++;
                if (x<6||y<6) {
                    x--;;
                }
                else if (x>120 || y>120) {
                    x--;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=1;
                    total++;
                }
            }
        }
        System.out.println("Island 1");
        
        
        
        s[x2][y2]=1;
        p[x2][y2]=2;
        
        x=x2;
        y=y2;
        total=1;
        while (total<island2Size) {                      //ISLAND 2
            int chooser=(int)(Math.random()*4+1);
            if (chooser==1) {
                y--;
                if (x<80||y<80) {
                    y++;
                }
                else if (x>195 || y>195) {
                    y++;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=2;
                    total++;
                }
            }
            else if (chooser==2) {
                x--;
                if (x<80||y<80) {
                    x++;
                }
                else if (x>195 || y>195) {
                    x++;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=2;
                    total++;
                }
            }
            else if (chooser==3) {
                y++;
                if (x<80||y<80) {
                    y--;
                }
                else if (x>195 || y>195) {
                    y--;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=2;
                    total++;
                }
            }
            else if (chooser==4) {
                x++;
                if (x<80||y<80) {
                    x--;;
                }
                else if (x>195 || y>195) {
                    x--;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=2;
                    total++;
                }
            }
        }
        System.out.println("Island 2");
        
        
        
        
        x=x3;
        y=y3;
        total=1;
        while (total<island3Size) {              //island 3
            int chooser=(int)(Math.random()*4+1);
            if (chooser==1) {
                y--;
                if (y<80) {
                    y++;
                }
                else if (s[x][y]==1) {
                    s[x][y]=1;
                    p[x][y]=3;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=3;
                    total++;
                }
            }
            else if (chooser==2) {
                x--;
                if (x<5) {
                    x++;
                }
                else if (s[x][y]==1) {
                    s[x][y]=1;
                    p[x][y]=3;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=3;
                    total++;
                }
            }
            else if (chooser==3) {
                y++;
                if (y>195) {
                    y--;
                }
                else if (s[x][y]==1) {
                    s[x][y]=1;
                    p[x][y]=3;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=3;
                    total++;
                }
            }
            else if (chooser==4) {
                x++;
                if (x>120) {
                    x--;
                }
                else if (s[x][y]==1) {
                    s[x][y]=1;
                    p[x][y]=3;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=3;
                    total++;
                }
            }
        }
        System.out.println("Island 3");
        
        
        
        
        x=x4;
        y=y4;
        total=1;
        while (total<island4Size) {              //island 4
            int chooser=(int)(Math.random()*4+1);
            if (chooser==1) {
                y--;
                if (y<5) {
                    y++;
                }
                else if (y>120) {
                    y++;
                }
                else if (s[x][y]==1) {
                    s[x][y]=1;
                    p[x][y]=4;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=4;
                    total++;
                }
            }
            else if (chooser==2) {
                x--;
                if (x<80) {
                    x++;
                }
                else if (x>195) {
                    x++;
                }
                else if (s[x][y]==1) {
                    s[x][y]=1;
                    p[x][y]=4;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=4;
                    total++;
                }
            }
            else if (chooser==3) {
                y++;
                if (y<5) {
                    y--;
                }
                else if (y>120) {
                    y--;
                }
                else if (s[x][y]==1) {
                    s[x][y]=1;
                    p[x][y]=4;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=4;
                    total++;
                }
            }
            else if (chooser==4) {
                x++;
                if (x<80) {
                    x--;;
                }
                else if (x>195) {
                    x--;
                }
                else if (s[x][y]==1) {
                    s[x][y]=1;
                    p[x][y]=4;
                }
                else {
                    s[x][y]=1;
                    p[x][y]=4;
                    total++;
                }
            }
        }
        System.out.println("Island 4");
        
        int tot=0;
        int tt=0;
        
        for (int i=0; i<200; i++) {
            for (int j=0; j<200; j++) {
                if (s[i][j]==0) {
                    tot=0;
                    try {
                        if (s[i-1][j]==1) {
                            tot++;
                        }
                        if (s[i+1][j]==1) {
                            tot++;
                        }
                        if (s[i][j-1]==1) {
                            tot++;
                        }
                        if (s[i][j+1]==1) {
                            tot++;
                        }
                        
                        if (tt>0) {
                            if (tot>1)
                                s[i][j]=1;
                            tt=0;
                        }
                        if (tot>2) {
                            s[i][j]=1;
                            tt=1;
                        }
                    }
                    catch (Exception e) {
                    }
                }
            }
        }
        
        for (int i=0; i<200; i++) {
            for (int j=0; j<200; j++) {
                if (s[i][j]==1) {
                    tot=0;
                    try {
                        if (s[i-1][j]==0) {
                            tot++;
                        }
                        if (s[i+1][j]==0) {
                            tot++;
                        }
                        if (s[i][j-1]==0) {
                            tot++;
                        }
                        if (s[i][j+1]==0) {
                            tot++;
                        }
                        
                        if (tot>1) {
                            
                            if (s[i-1][j]==0)
                                s[i-1][j]=3;
                            if (s[i+1][j]==0)
                                s[i+1][j]=3;
                            if (s[i][j-1]==0)
                                s[i][j-1]=3;
                            if (s[i][j+1]==0)
                                s[i][j+1]=3;
                        }
                    }
                    catch (Exception e) {
                    }
                }
            }
        }
        
        for (int i=0; i<200; i++) {
            for (int j=0; j<200; j++) {
                if (s[i][j]==0) {
                    tot=0;
                    try {
                        if (s[i-1][j]==1 || s[i-1][j]==3) {
                            tot++;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i+1][j]==1 || s[i+1][j]==3) {
                            tot++;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i][j-1]==1 || s[i][j-1]==3) {
                            tot++;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i][j+1]==1 || s[i][j+1]==3) {
                            tot++;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (tt>0) {
                            if (tot>1)
                                s[i][j]=1;
                            tt=0;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (tot>2) {
                            s[i][j]=1;
                            tt=1;
                        }
                    }
                    catch(Exception e) {}
                }
            }
        }
        
        
        
        
        for (int i=0; i<200; i++) {
            for (int j=0; j<200; j++)
                if (s[i][j]==0)
                    s[i][j]=2;
        }
        for (int i=0; i<200; i++) {
            for (int j=0; j<200; j++)
                if (s[i][j]==3)
                    s[i][j]=1;
        }
        
        
        for (int i=0; i<200; i++) {
            for (int j=0; j<200; j++) {
                if (s[i][j]==2) {
                    s[i][j]=2;
                    try {
                        if (s[i][j-1]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i][j-2]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i][j-3]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i][j+1]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i][j+2]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i][j+3]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i-1][j]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i-2][j]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i-3][j]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i+1][j]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i+2][j]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i+3][j]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        
                        if (s[i-1][j-1]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i-1][j-2]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i-1][j-3]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i-2][j-1]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i-2][j-2]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i-3][j-1]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        
                        if (s[i+1][j-1]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i+1][j-2]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i+1][j-3]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i+2][j-1]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i+2][j-2]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i+3][j-1]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        
                        if (s[i-1][j+1]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i-1][j+2]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i-1][j+3]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i-2][j+1]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i-2][j+2]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i-3][j+1]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        
                        if (s[i+1][j+1]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i+1][j+2]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i+1][j+3]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i+2][j+1]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i+2][j+2]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                    try {
                        if (s[i+3][j+1]==1) {
                            s[i][j]=3;
                        }
                    }
                    catch(Exception e) {}
                }
            }
        }
        
        //5=player 1  6=player 2  7=barbaric city 8=player 3  9=enemy cities
        int city1x=-1000,city2x=-1000,city3x=-1000, bcity1x=-1000, bcity2x=-1000, bcity3x=-1000, bcity4x=-1000, bcity5x=-1000, bcity6x=-1000;
        int city1y=-1000,city2y=-1000,city3y=-1000, bcity1y=-1000, bcity2y=-1000, bcity3y=-1000, bcity4y=-1000, bcity5y=-1000, bcity6y=-1000;
        boolean boo;
        int counter=0;
        for (int iii=5; iii<10; iii++) {
            boo=true;
            if (iii>5&&iii<9) {
                counter=1;
            }
            if (iii==7) {
                counter=5;
            }
            while (boo) {
                boolean rrr=true;
                x=(int)(Math.random()*200);
                y=(int)(Math.random()*200);
                if (iii==5) {
                    if (p[x][y]==1) {
                        city1x=x;
                        city1y=y;
                        boo=false;
                        s[x][y]=5;
                        int[] thing=new int[4];
                        thing[0]=1;
                        thing[1]=x;
                        thing[2]=y;
                        thing[3]=0;
                        p1Troops.add(thing);
                    }
                }
                else if (iii==6) {
                    if (p[x][y]==1) {
                        if (city1x>(x-12)&&city1x<(x+12)) {
                            if (city1y>(y-12)&&city1y<(y+12)) {
                                rrr=false;
                            }
                        }
                        if (rrr) {
                            city2x=x;
                            city2y=y;
                            boo=false;
                            s[x][y]=6;
                            int[] thing=new int[4];
                            thing[0]=1;
                            thing[1]=x;
                            thing[2]=y;
                            thing[3]=0;
                            p2Troops.add(thing);
                        }
                    }
                }
                else if (iii==7) {
                    if (s[x][y]==1) {
                        if (city1x>x-12&&city1x<x+12) {
                            if ((city1y)>(y-12)&&(city1y)<(y+12)) {
                                rrr=false;
                            }
                        }
                        else if (city2x>(x-12)&&city2x<(x+12)) {
                            if ((city2y)>(y-12)&&(city2y)<(y+12)) {
                                rrr=false;
                            }
                        }
                        else if (bcity1x>(x-12)&&bcity1x<(x+12)) {
                            if ((bcity1y)>(y-12)&&(bcity1y)<(y+12)) {
                                rrr=false;
                            }
                        }
                        else if (bcity2x>(x-12)&&bcity2x<(x+12)) {
                            if ((bcity2y)>(y-12)&&(bcity2y)<(y+12)) {
                                rrr=false;
                            }
                        }
                        else if (bcity3x>(x-12)&&bcity3x<(x+12)) {
                            if ((bcity3y)>(y-12)&&(bcity3y)<(y+12)) {
                                rrr=false;
                            }
                        }
                        else if (bcity4x>(x-12)&&bcity4x<(x+12)) {
                            if ((bcity4y)>(y-12)&&bcity4y<(y+12)) {
                                rrr=false;
                            }
                        }
                        else if (bcity5x>(x-12)&&bcity5x<(x+12)) {
                            if ((bcity5y)>(y-12)&&(bcity5y)<(y+12)) {
                                rrr=false;
                            }
                        }
                        else {
                            if (counter<=0 && rrr) {
                                bcity6x=x;
                                bcity6y=y;
                                boo=false;
                            }
                            else if (rrr) {
                                if (bcity4x>=0) {
                                    bcity5x=x;
                                    bcity5y=y;
                                }
                                else if (bcity3x>=0) {
                                    bcity4x=x;
                                    bcity4y=y;
                                }
                                else if (bcity2x>=0) {
                                    bcity3x=x;
                                    bcity3y=y;
                                }
                                else if (bcity1x>=0) {
                                    bcity2x=x;
                                    bcity2y=y;
                                }
                                else {
                                    bcity1x=x;
                                    bcity1y=y;
                                }
                                counter--;
                            }
                            s[x][y]=7;
                        }
                    }
                }
                else if (iii==8) {
                    if (city1x>x-12&&city1x<x+12) {
                        if ((city1y)>(y-12)&&(city1y)<(y+12)) {
                            rrr=false;
                        }
                    }
                    else if (city2x>(x-12)&&city2x<(x+12)) {
                        if ((city2y)>(y-12)&&(city2y)<(y+12)) {
                            rrr=false;
                    }
                        if ((bcity1y)>(y-12)&&(bcity1y)<(y+12)) {
                            rrr=false;
                        }
                    }
                    else if (bcity2x>(x-12)&&bcity2x<(x+12)) {
                        if ((bcity2y)>(y-12)&&(bcity2y)<(y+12)) {
                            rrr=false;
                        }
                    }
                    else if (bcity3x>(x-12)&&bcity3x<(x+12)) {
                        if ((bcity3y)>(y-12)&&(bcity3y)<(y+12)) {
                            rrr=false;
                        }
                    }
                    else if (bcity4x>(x-12)&&bcity4x<(x+12)) {
                        if ((bcity4y)>(y-12)&&bcity4y<(y+12)) {
                            rrr=false;
                        }
                    }
                    else if (bcity5x>(x-12)&&bcity5x<(x+12)) {
                        if ((bcity5y)>(y-12)&&(bcity5y)<(y+12)) {
                            rrr=false;
                        }
                    }
                    if (bcity6x>(x-12)&&bcity6x<(x+12)) {
                        if ((bcity6y)>(y-12)&&(bcity6y)<(y+12)) {
                            rrr=false;
                        }
                    }
                    if (p[x][y]==2 && rrr) {
                        city3x=x;
                        city3y=y;
                        boo=false;
                        s[x][y]=8;
                        int[] thing=new int[4];
                        thing[0]=1;
                        thing[1]=x;
                        thing[2]=y;
                        thing[3]=0;
                        p3Troops.add(thing);
                    }
                }
                else {
                    if (city1x>x-12&&city1x<x+12) {
                        if ((city1y)>(y-12)&&(city1y)<(y+12)) {
                            rrr=false;
                        }
                    }
                    else if (city2x>(x-12)&&city2x<(x+12)) {
                        if ((city2y)>(y-12)&&(city2y)<(y+12)) {
                            rrr=false;
                        }
                    }
                    else if (bcity1x>(x-12)&&bcity1x<(x+12)) {
                        if ((bcity1y)>(y-12)&&(bcity1y)<(y+12)) {
                            rrr=false;
                        }
                    }
                    else if (bcity2x>(x-12)&&bcity2x<(x+12)) {
                        if ((bcity2y)>(y-12)&&(bcity2y)<(y+12)) {
                            rrr=false;
                        }
                    }
                    else if (bcity3x>(x-12)&&bcity3x<(x+12)) {
                        if ((bcity3y)>(y-12)&&(bcity3y)<(y+12)) {
                            rrr=false;
                        }
                    }
                    else if (bcity4x>(x-12)&&bcity4x<(x+12)) {
                        if ((bcity4y)>(y-12)&&bcity4y<(y+12)) {
                            rrr=false;
                        }
                    }
                    else if (bcity5x>(x-12)&&bcity5x<(x+12)) {
                        if ((bcity5y)>(y-12)&&(bcity5y)<(y+12)) {
                            rrr=false;
                        }
                    }
                    if (bcity6x>(x-12)&&bcity6x<(x+12)) {
                        if ((bcity6y)>(y-12)&&(bcity6y)<(y+12)) {
                            rrr=false;
                        }
                    }
                    if (city3x>(x-12)&&city3x<(x+12)) {
                        if ((city3y)>(y-12)&&(city3y)<(y+12)) {
                            rrr=false;
                        }
                    }
                    if (p[x][y]==2) {
                        boo=false;
                        s[x][y]=9;
                    }
                }
            }
        }
        
        int preventInfLoop=0;
        
        //mountains
        boolean tryagain=false;
        boo=true;
        int creatimes=(int)(Math.random()*5+5);
        while (creatimes>0) {
            while (boo) {
                do {
                    x=(int)(Math.random()*200);
                    y=(int)(Math.random()*200);
                    tryagain=false;
                    try {
                        if (s[x][y]==1) {
                            int ttt=(int)(Math.random()*6+15);
                            while (ttt>0) {
                                int chooser=(int)(Math.random()*4+1);
                                preventInfLoop++;
                                if (chooser==1) {
                                    y--;
                                    if (s[x][y]==1) {
                                        s[x][y]=10;
                                        ttt--;
                                    }
                                    else if (s[x][y]==10) {}
                                    else {
                                        y++;
                                    }
                                }
                                else if (chooser==2) {
                                    x--;
                                    if (s[x][y]==1) {
                                        s[x][y]=10;
                                        ttt--;
                                    }
                                    else if (s[x][y]==10) {}
                                    else {
                                        x++;
                                    }
                                }
                                else if (chooser==3) {
                                    y++;
                                    if (s[x][y]==1) {
                                        s[x][y]=10;
                                        ttt--;
                                    }
                                    else if (s[x][y]==10) {}
                                    else {
                                        y--;
                                    }
                                }
                                else if (chooser==4) {
                                    x++;
                                    if (s[x][y]==1) {
                                        s[x][y]=10;
                                        ttt--;
                                    }
                                    else if (s[x][y]==10) {}
                                    else {
                                        x--;
                                    }
                                }
                                if (preventInfLoop>150)
                                    ttt=0;
                            }
                            if (ttt==0) {
                                boo=false;
                                preventInfLoop=0;
                            }
                        }
                    }
                    catch (Exception e) {
                        tryagain=true;
                    }
                }
                while (tryagain);
            }
            creatimes--;
            boo=true;
        }
        
        
        
        //forests
        preventInfLoop=0;
        tryagain=false;
        boo=true;
        creatimes=(int)(Math.random()*5+35);
        while (creatimes>0) {
            while (boo) {
                do {
                    x=(int)(Math.random()*200);
                    y=(int)(Math.random()*200);
                    tryagain=false;
                    try {
                        if (s[x][y]==1) {
                            int ttt=(int)(Math.random()*6+15);
                            while (ttt>0) {
                                int chooser=(int)(Math.random()*4+1);
                                preventInfLoop++;
                                if (chooser==1) {
                                    y--;
                                    if (s[x][y]==1) {
                                        type[x][y]=1;
                                        ttt--;
                                    }
                                    else if (type[x][y]==1) {}
                                    else {
                                        y++;
                                    }
                                }
                                else if (chooser==2) {
                                    x--;
                                    if (s[x][y]==1) {
                                        type[x][y]=1;
                                        ttt--;
                                    }
                                    else if (type[x][y]==1) {}
                                    else {
                                        x++;
                                    }
                                }
                                else if (chooser==3) {
                                    y++;
                                    if (s[x][y]==1) {
                                        type[x][y]=1;
                                        ttt--;
                                    }
                                    else if (type[x][y]==1) {}
                                    else {
                                        y--;
                                    }
                                }
                                else if (chooser==4) {
                                    x++;
                                    if (s[x][y]==1) {
                                        type[x][y]=1;
                                        ttt--;
                                    }
                                    else if (type[x][y]==1) {}
                                    else {
                                        x--;
                                    }
                                }
                                if (preventInfLoop>150)
                                    ttt=0;
                            }
                            if (ttt==0) {
                                boo=false;
                                preventInfLoop=0;
                            }
                        }
                    }
                    catch (Exception e) {
                        tryagain=true;
                    }
                }
                while (tryagain);
            }
            creatimes--;
            boo=true;
        }
        
        
        //deserts
        preventInfLoop=0;
        tryagain=false;
        boo=true;
        creatimes=(int)(Math.random()*5+25);
        while (creatimes>0) {
            while (boo) {
                do {
                    x=(int)(Math.random()*200);
                    y=(int)(Math.random()*200);
                    tryagain=false;
                    try {
                        if (s[x][y]==1 && type[x][y]==0) {
                            int ttt=(int)(Math.random()*6+10);
                            while (ttt>0) {
                                int chooser=(int)(Math.random()*4+1);
                                preventInfLoop++;
                                if (chooser==1) {
                                    y--;
                                    if (s[x][y]==1 && type[x][y]==0) {
                                        type[x][y]=2;
                                        ttt--;
                                    }
                                    else if (type[x][y]==2) {}
                                    else {
                                        y++;
                                    }
                                }
                                else if (chooser==2) {
                                    x--;
                                    if (s[x][y]==1 && type[x][y]==0) {
                                        type[x][y]=2;
                                        ttt--;
                                    }
                                    else if (type[x][y]==2) {}
                                    else {
                                        x++;
                                    }
                                }
                                else if (chooser==3) {
                                    y++;
                                    if (s[x][y]==1 && type[x][y]==0) {
                                        type[x][y]=2;
                                        ttt--;
                                    }
                                    else if (type[x][y]==2) {}
                                    else {
                                        y--;
                                    }
                                }
                                else if (chooser==4) {
                                    x++;
                                    if (s[x][y]==1 && type[x][y]==0) {
                                        type[x][y]=2;
                                        ttt--;
                                    }
                                    else if (type[x][y]==2) {}
                                    else {
                                        x--;
                                    }
                                }
                                if (preventInfLoop>150)
                                    ttt=0;
                            }
                            if (ttt==0) {
                                boo=false;
                                preventInfLoop=0;
                            }
                        }
                    }
                    catch (Exception e) {
                        tryagain=true;
                    }
                }
                while (tryagain);
            }
            creatimes--;
            boo=true;
        }
        
        
        
        
        //grasslands
        preventInfLoop=0;
        tryagain=false;
        boo=true;
        creatimes=(int)(Math.random()*5+20);
        while (creatimes>0) {
            while (boo) {
                do {
                    x=(int)(Math.random()*200);
                    y=(int)(Math.random()*200);
                    tryagain=false;
                    try {
                        if (s[x][y]==1) {
                            int ttt=(int)(Math.random()*6+7);
                            while (ttt>0) {
                                int chooser=(int)(Math.random()*4+1);
                                preventInfLoop++;
                                if (chooser==1) {
                                    y--;
                                    if (s[x][y]==1 && type[x][y]==0) {
                                        type[x][y]=3;
                                        ttt--;
                                    }
                                    else if (type[x][y]==3) {}
                                    else {
                                        y++;
                                    }
                                }
                                else if (chooser==2) {
                                    x--;
                                    if (s[x][y]==1 && type[x][y]==0) {
                                        type[x][y]=3;
                                        ttt--;
                                    }
                                    else if (type[x][y]==3) {}
                                    else {
                                        x++;
                                    }
                                }
                                else if (chooser==3) {
                                    y++;
                                    if (s[x][y]==1 && type[x][y]==0) {
                                        type[x][y]=3;
                                        ttt--;
                                    }
                                    else if (type[x][y]==3) {}
                                    else {
                                        y--;
                                    }
                                }
                                else if (chooser==4) {
                                    x++;
                                    if (s[x][y]==1 && type[x][y]==0) {
                                        type[x][y]=3;
                                        ttt--;
                                    }
                                    else if (type[x][y]==3) {}
                                    else {
                                        x--;
                                    }
                                }
                                if (preventInfLoop>150)
                                    ttt=0;
                            }
                            if (ttt==0) {
                                boo=false;
                                preventInfLoop=0;
                            }
                        }
                    }
                    catch (Exception e) {
                        tryagain=true;
                    }
                }
                while (tryagain);
            }
            creatimes--;
            boo=true;
        }
        
        
        
        
        //jungle
        preventInfLoop=0;
        tryagain=false;
        boo=true;
        creatimes=(int)(Math.random()*4+1);
        while (creatimes>0) {
            while (boo) {
                do {
                    x=(int)(Math.random()*200);
                    y=(int)(Math.random()*200);
                    tryagain=false;
                    try {
                        if (s[x][y]==1) {
                            int ttt=(int)(Math.random()*15+30);
                            while (ttt>0) {
                                int chooser=(int)(Math.random()*4+1);
                                preventInfLoop++;
                                if (chooser==1) {
                                    y--;
                                    if (s[x][y]==1 && type[x][y]==0) {
                                        type[x][y]=8;
                                        ttt--;
                                    }
                                    else if (type[x][y]==8) {}
                                    else {
                                        y++;
                                    }
                                }
                                else if (chooser==2) {
                                    x--;
                                    if (s[x][y]==1 && type[x][y]==0) {
                                        type[x][y]=8;
                                        ttt--;
                                    }
                                    else if (type[x][y]==8) {}
                                    else {
                                        x++;
                                    }
                                }
                                else if (chooser==3) {
                                    y++;
                                    if (s[x][y]==1 && type[x][y]==0) {
                                        type[x][y]=8;
                                        ttt--;
                                    }
                                    else if (type[x][y]==8) {}
                                    else {
                                        y--;
                                    }
                                }
                                else if (chooser==4) {
                                    x++;
                                    if (s[x][y]==1 && type[x][y]==0) {
                                        type[x][y]=8;
                                        ttt--;
                                    }
                                    else if (type[x][y]==8) {}
                                    else {
                                        x--;
                                    }
                                }
                                if (preventInfLoop>500)
                                    ttt=0;
                            }
                            if (ttt==0) {
                                boo=false;
                                preventInfLoop=0;
                            }
                        }
                    }
                    catch (Exception e) {
                        tryagain=true;
                    }
                }
                while (tryagain);
            }
            creatimes--;
            boo=true;
        }
        
        

        
        
        
        for (int tx=0; tx<200; tx++) {
            for (int ty=0; ty<200; ty++) {
                if (s[tx][ty]==1) {
                    if (type[tx][ty]==0) {
                        type[tx][ty]=5;
                    }
                }
            }
        }
        
        System.out.println("Here");
        
        
        
        
        
        
        
        
        
        for (int i=0;i<200;i++) {
            for (int j=0;j<200;j++) {
                if (s[i][j]==5 || s[i][j]==6 || s[i][j]==8) {
                    clouds[i][j]=0;
                    try {
                        clouds[i-1][j]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i-2][j]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i+1][j]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i+2][j]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i][j-1]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i][j-2]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i][j+1]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i][j+2]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i-1][j-1]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i-1][j-2]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i-2][j-1]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i+1][j-1]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i+1][j-2]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i+2][j-1]=0;
                    }
                    catch(Exception e) {}
                    
                    try {
                        clouds[i-1][j+1]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i-1][j+2]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i-2][j+1]=0;
                    }
                    catch(Exception e) {}
                    
                    try {
                        clouds[i+1][j+1]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i+1][j+2]=0;
                    }
                    catch(Exception e) {}
                    try {
                        clouds[i+2][j+1]=0;
                    }
                    catch(Exception e) {}
                }
            }
        }
        
        for (int i=0; i<200; i++) {
            for (int j=0; j<200; j++) {
                if (s[i][j]>4) {
                    if (s[i][j]==5) {
                        territories[i][j]=1;
                        try {
                            territories[i-1][j]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-2][j]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+2][j]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j-1]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j-2]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j+1]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j+2]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-1][j-1]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-1][j-2]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-2][j-1]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j-1]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j-2]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+2][j-1]=1;
                        }
                        catch(Exception e) {}
                        
                        try {
                            territories[i-1][j+1]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-1][j+2]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-2][j+1]=1;
                        }
                        catch(Exception e) {}
                        
                        try {
                            territories[i+1][j+1]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j+2]=1;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+2][j+1]=1;
                        }
                        catch(Exception e) {}
                    }
                    else if (s[i][j]==6) {
                        territories[i][j]=2;
                        try {
                            territories[i-1][j]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-2][j]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+2][j]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j-1]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j-2]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j+1]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j+2]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-1][j-1]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-1][j-2]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-2][j-1]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j-1]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j-2]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+2][j-1]=2;
                        }
                        catch(Exception e) {}
                        
                        try {
                            territories[i-1][j+1]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-1][j+2]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-2][j+1]=2;
                        }
                        catch(Exception e) {}
                        
                        try {
                            territories[i+1][j+1]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j+2]=2;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+2][j+1]=2;
                        }
                        catch(Exception e) {}
                    }
                    else if (s[i][j]==7) {
                        territories[i][j]=3;
                        try {
                            territories[i-1][j]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-2][j]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+2][j]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j-1]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j-2]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j+1]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j+2]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-1][j-1]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-1][j-2]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-2][j-1]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j-1]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j-2]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+2][j-1]=3;
                        }
                        catch(Exception e) {}
                        
                        try {
                            territories[i-1][j+1]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-1][j+2]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-2][j+1]=3;
                        }
                        catch(Exception e) {}
                        
                        try {
                            territories[i+1][j+1]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j+2]=3;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+2][j+1]=3;
                        }
                        catch(Exception e) {}
                    }
                    else if (s[i][j]==8) {
                        territories[i][j]=4;
                        try {
                            territories[i-1][j]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-2][j]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+2][j]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j-1]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j-2]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j+1]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j+2]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-1][j-1]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-1][j-2]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-2][j-1]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j-1]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j-2]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+2][j-1]=4;
                        }
                        catch(Exception e) {}
                        
                        try {
                            territories[i-1][j+1]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-1][j+2]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-2][j+1]=4;
                        }
                        catch(Exception e) {}
                        
                        try {
                            territories[i+1][j+1]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j+2]=4;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+2][j+1]=4;
                        }
                        catch(Exception e) {}
                    }
                    else if (s[i][j]==9) {
                        territories[i][j]=5;
                        try {
                            territories[i-1][j]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-2][j]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+2][j]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j-1]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j-2]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j+1]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i][j+2]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-1][j-1]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-1][j-2]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-2][j-1]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j-1]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j-2]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+2][j-1]=5;
                        }
                        catch(Exception e) {}
                        
                        try {
                            territories[i-1][j+1]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-1][j+2]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i-2][j+1]=5;
                        }
                        catch(Exception e) {}
                        
                        try {
                            territories[i+1][j+1]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+1][j+2]=5;
                        }
                        catch(Exception e) {}
                        try {
                            territories[i+2][j+1]=5;
                        }
                        catch(Exception e) {}
                    }
                }
            }
        }
        
        
        
        System.out.println("Randomize bottom");
    }
    
    public static void reset() {
        for (int i=0; i<200; i++) {
            for (int j=0; j<200; j++) {
                s[i][j]=0;
                p[i][j]=0;
            }
        }
    }
    
    public void paint(Graphics g) {
        im=createImage(getWidth(),getHeight());
        h=im.getGraphics();
        paintComponent(h);
        g.drawImage(im,0,0,this);
    }
    
    public void paintComponent(Graphics g) {
        //2D renderer
        if (ready) {
            g.setColor(new Color(64,164,223));
            g.fillRect(0,0,600,600);
        }
        else if (settings) {
            g.setColor(Color.white);
            g.fillRect(0,0,810,600);
            g.setColor(Color.black);
            g.setFont(new Font("Futura", Font.PLAIN, 50));
            g.drawString("Settings", 318, 80);
            g.setFont(new Font("Bell MT", Font.PLAIN, 24));
            g.setColor(Color.blue);
            g.drawString("City States:", 40,112);
            g.drawString("Map Type:", 40, 163);
            g.drawString("Map Size:", 40, 214);
            g.drawString("GamePace:", 40, 265);
            g.drawString("Game Era:", 40, 316);
            g.drawString("Start", 382, 441);
            
            g.drawRect(228,136,372,29);
            g.drawRect(228,187,372,29);
            g.drawRect(228,238,372,29);
            g.drawRect(228,289,372,29);
            
            
            g.drawRect(323,419,164,27);
        }
        else {
            g.setColor(Color.white);
            g.fillRect(0,0,810,600);
            try {
                if (atHome)
                    g.drawImage(ImageIO.read(new File("/Volumes/CLAYTON FD/Pictures/HomeScreen.gif")), 0, 0, this);
                else
                    g.drawImage(ImageIO.read(new File("U:/Pictures/HomeScreen.gif")), 0, 0, this);
            }
            catch (Exception e) {
                System.err.println("SecondFailure");
            }
            g.setColor(new Color(0,149,187));
            g.drawRect(323,204,164,27);
            g.drawRect(323,233,164,27);
            g.drawRect(323,262,164,27);
            g.drawRect(323,291,164,27);
            g.drawRect(323,320,164,27);
            g.drawRect(323,419,164,27);
        }
        
        
        if (loRez && ready) {
            for (int i=0; i<200; i++) {
                for (int j=0; j<200; j++) {
                    double XPOS=((double)xPos)/3;
                    double YPOS=((double)yPos)/3;
                    int repx=((i+1)*scale)-(int)(XPOS*scale);
                    int repy=((j+1)*scale)-(int)(YPOS*scale);
                    
                    if (repx+scale<-10 || repx>610 || repy+scale<-10 || repy>610) {}
                    else if (clouds[i][j]==0) {
                        if (s[i][j]==1) {
                            if (type[i][j]==1) {  //forest
                                g.setColor(new Color(77,189,51));
                                g.fillRect(repx, repy, scale, scale);
                            }
                            else if (type[i][j]==2) {  //desert
                                g.setColor(new Color(250,242,82));
                                g.fillRect(repx, repy, scale, scale);
                            }
                            else if (type[i][j]==3) {  //grassland
                                g.setColor(new Color(68,238,85));
                                g.fillRect(repx, repy, scale, scale);
                            }
                            else if (type[i][j]==4) {  //hill
                                g.setColor(new Color(171,247,179));
                                g.fillRect(repx, repy, scale, scale);
                            }
                            else if (type[i][j]==5) {  //plains
                                g.setColor(new Color(165,245,158));
                                g.fillRect(repx, repy, scale, scale);
                            }
                            else if (type[i][j]==6) {  //snow
                                g.setColor(new Color(230,245,254));
                                g.fillRect(repx, repy, scale, scale);
                            }
                            else if (type[i][j]==7) {  //tundra
                                g.setColor(new Color(209,253,216));
                                g.fillRect(repx, repy, scale, scale);
                            }
                            else if (type[i][j]==8) {  //jungle
                                g.setColor(new Color(15,245,52));
                                g.fillRect(repx, repy, scale, scale);
                            }
                        }
                        else if (s[i][j]==2) {
                            g.setColor(new Color(4,36,174));
                            g.fillRect(repx, repy, scale, scale);
                        }
                        else if (s[i][j]==3) {
                            g.setColor(new Color(64,164,223));
                            g.fillRect(((i+1)*scale)-((int)(XPOS*scale)), repy, scale, scale);
                        }
                        
                        else if (s[i][j]==5) {
                            g.setColor(new Color(232,125,11));
                            g.fillRect(((i+1)*scale)-((int)(XPOS*scale)), repy, scale, scale);
                        }
                        else if (s[i][j]==6) {
                            g.setColor(new Color(0,150,0));
                            g.fillRect(repx, repy, scale, scale);
                        }
                        
                        else if (s[i][j]==7) {
                            g.setColor(new Color(85,51,17));
                            g.fillRect(repx, repy, scale, scale);
                        }
                        
                        else if (s[i][j]==8) {
                            g.setColor(new Color(0,0,150));
                            g.fillRect(repx, repy, scale, scale);
                        }
                        else if (s[i][j]==9) {
                            g.setColor(new Color(238,55,13));
                            g.fillRect(repx, repy, scale, scale);
                        }
                        else if (s[i][j]==10) {
                            g.setColor(new Color(91,59,21));
                            g.fillRect(repx, repy, scale, scale);
                        }
                        
                        
                        if (territories[i][j]==1) {
                            g.setColor(new Color(232,125,11));
                            try {
                                if (territories[i][j-1]!=1)
                                    g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                            } catch(Exception e) {
                                g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                            }
                            try {
                                if (territories[i-1][j]!=1) {
                                    g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                                }
                            } catch(Exception e) {
                                g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            }
                            try {
                                if (territories[i+1][j]!=1)
                                    g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            } catch(Exception e) {
                                g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            }
                            try {
                                if (territories[i][j+1]!=1)
                                    g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            } catch(Exception e) {
                                g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            }
                        }
                        if (territories[i][j]==2) {
                            g.setColor(new Color(0,150,0));
                            try {
                                if (territories[i][j-1]!=2)
                                    g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                            } catch(Exception e) {
                                g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                            }
                            try {
                                if (territories[i-1][j]!=2) {
                                    g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                                }
                            } catch(Exception e) {
                                g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            }
                            try {
                                if (territories[i+1][j]!=2)
                                    g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            } catch(Exception e) {
                                g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            }
                            try {
                                if (territories[i][j+1]!=2)
                                    g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            } catch(Exception e) {
                            	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	}
                        }
                        if (territories[i][j]==3) {
                        	g.setColor(new Color(85,51,17));
                        	try {
                        		if (territories[i][j-1]!=3)
                                	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                        	} catch(Exception e) {
                            	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                        	}
                        	try {
                        		if (territories[i-1][j]!=3) {
                                	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        		}
                        	} catch(Exception e) {
                            	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	}
                        	try {
                        		if (territories[i+1][j]!=3)
                                	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	} catch(Exception e) {
                            	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	}
                        	try {
                        		if (territories[i][j+1]!=3)
                                	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	} catch(Exception e) {
                            	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	}
                        }
                        if (territories[i][j]==4) {
                        	g.setColor(new Color(0,0,150));
                        	try {
                        		if (territories[i][j-1]!=4)
                                	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                        	} catch(Exception e) {
                            	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                        	}
                        	try {
                        		if (territories[i-1][j]!=4) {
                                	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        		}
                        	} catch(Exception e) {
                            	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	}
                        	try {
                        		if (territories[i+1][j]!=4)
                                	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	} catch(Exception e) {
                            	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	}
                        	try {
                        		if (territories[i][j+1]!=4)
                                	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	} catch(Exception e) {
                            	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	}
                        }
                        if (territories[i][j]==5) {
                        	g.setColor(new Color(238,55,13));
                        	try {
                        		if (territories[i][j-1]!=5)
                                	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                        	} catch(Exception e) {
                            	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                        	}
                        	try {
                        		if (territories[i-1][j]!=5) {
                                	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        		}
                        	} catch(Exception e) {
                            	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	}
                        	try {
                        		if (territories[i+1][j]!=5)
                                	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	} catch(Exception e) {
                            	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	}
                        	try {
                        		if (territories[i][j+1]!=5)
                                	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	} catch(Exception e) {
                            	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                        	}
                        }
                    }
                    else {
                        g.setColor(new Color(245,246,255));
                        g.fillRect(repx, repy, scale, scale);
                    }
                }
            }
            
            for (int unit=0; unit<p1Troops.size(); unit++) {
            	int[] go=p1Troops.get(unit);
            	int repx=((go[1]+1)*scale)-(int)(((double)xPos/3)*scale);
            	int repy=((go[2]+1)*scale)-(int)(((double)yPos/3)*scale);
            	g.setColor(new Color(232,125,11));
                if (repx+scale<0 || repx>600 || repy+scale<0 || repy>600) {}
                else if (clouds[go[1]][go[2]]==0) {
	            	switch (go[0]) {
	            	case 1: g.drawImage(imageS, repx, repy, scale, scale, this); g.drawRect(repx, repy, scale, scale);
	            		break;
	            	case 2:
	            		break;
	            	case 3:
	            		break;
	            	case 4:
	            		break;
	            	case 5:
	            		break;
	            	case 6:
	            		break;
	            	case 7:
	            		break;
	            	case 8:
	            		break;
	            	case 9:
	            		break;
	            	case 10:
	            		break;
	            	case 11:
	            		break;
	            	case 12:
	            		break;
	            	case 13:
	            		break;
	            	case 14:
	            		break;
	            	case 15:
	            		break;
	            	case 16:
	            		break;
	            	case 17:
	            		break;
	            	case 18:
	            		break;
	            	case 19:
	            		break;
	            	case 20:
	            		break;
	            	case 21:
	            		break;
	            	case 22:
	            		break;
	            	case 23:
	            		break;
	            	case 24:
	            		break;
	            	case 25:
	            		break;
	            	case 26:
	            		break;
	            	case 27:
	            		break;
	            	case 28:
	            		break;
	            	case 29:
	            		break;
	            	case 30:
	            		break;
	            	case 31:
	            		break;
	            	case 32:
	            		break;
	            	case 33:
	            		break;
	            	case 34:
	            		break;
	            	case 35:
	            		break;
	            	case 36:
	            		break;
	            	case 37:
	            		break;
	            	case 38:
	            		break;
	            	case 39:
	            		break;
	            	case 40:
	            		break;
	            	case 41:
	            		break;
	            	case 42:
	            		break;
	            	case 43:
	            		break;
	            	case 44:
	            		break;
	            	case 45:
	            		break;
	            	case 46:
	            		break;
	            	case 47:
	            		break;
	            	case 48:
	            		break;
	            	case 49:
	            		break;
	            	case 50:
	            		break;
	            	case 51:
	            		break;
	            	case 52:
	            		break;
	            	case 53:
	            		break;
	            	case 54:
	            		break;
	            	case 55:
	            		break;
	            	case 56:
	            		break;
	            	case 57:
	            		break;
	            	case 58:
	            		break;
	            	case 59:
	            		break;
	            	case 60:
	            		break;
	            	case 61:
	            		break;
	            	case 62:
	            		break;
	            	case 63:
	            		break;
	            	case 64:
	            		break;
	            	case 65:
	            		break;
	            	case 66:
	            		break;
	            	case 67:
	            		break;
	            	case 68:
	            		break;
	            	case 69:
	            		break;
	            	case 70:
	            		break;
	            	case 71:
	            		break;
	            	case 72:
	            		break;
	            	case 73:
	            		break;
	            	case 74:
	            		break;
	            	case 75:
	            		break;
	            	case 76:
	            		break;
	            	case 77:
	            		break;
	            	case 78:
	            		break;
	            	case 79:
	            		break;
	            	case 80:
	            		break;
	            	case 81:
	            		break;
	            	case 82:
	            		break;
	            	case 83:
	            		break;
	            	case 84:
	            		break;
	            	case 85:
	            		break;
	            	case 86:
	            		break;
	            	case 87:
	            		break;
	            	case 88:
	            		break;
	            	case 89:
	            		break;
	            	case 90:
	            		break;
	            	case 91:
	            		break;
	            	case 92:
	            		break;
	            	case 93:
	            		break;
	            	case 94:
	            		break;
	            	case 95:
	            		break;
	            	case 96:
	            		break;
	            	case 97:
	            		break;
	            	case 98:
	            		break;
	            	case 99:
	            		break;
	            	case 100:
	            		break;
	            	case 101:
	            		break;
	            	case 102:
	            		break;
	            	case 103:
	            		break;
	            	case 104:
	            		break;
	            	case 105:
	            		break;
	            	case 106:
	            		break;
	            	case 107:
	            		break;
	            	case 108:
	            		break;
	            	case 109:
	            		break;
	            	case 110:
	            		break;
	            	case 111:
	            		break;
	            	case 112:
	            		break;
	            	case 113:
	            		break;
	            	}
                }
            }
            
            
            for (int unit=0; unit<p2Troops.size(); unit++) {
            	int[] go=p2Troops.get(unit);
            	int repx=((go[1]+1)*scale)-(int)(((double)xPos/3)*scale);
            	int repy=((go[2]+1)*scale)-(int)(((double)yPos/3)*scale);
            	g.setColor(new Color(0,150,0));
                if (repx+scale<0 || repx>600 || repy+scale<0 || repy>600) {}
                else if (clouds[go[1]][go[2]]==0) {
	            	switch (go[0]) {
	            	case 1: g.drawImage(imageS, repx, repy, scale, scale, this); g.drawRect(repx, repy, scale, scale);
	            		break;
	            	case 2:
	            		break;
	            	case 3:
	            		break;
	            	case 4:
	            		break;
	            	case 5:
	            		break;
	            	case 6:
	            		break;
	            	case 7:
	            		break;
	            	case 8:
	            		break;
	            	case 9:
	            		break;
	            	case 10:
	            		break;
	            	case 11:
	            		break;
	            	case 12:
	            		break;
	            	case 13:
	            		break;
	            	case 14:
	            		break;
	            	case 15:
	            		break;
	            	case 16:
	            		break;
	            	case 17:
	            		break;
	            	case 18:
	            		break;
	            	case 19:
	            		break;
	            	case 20:
	            		break;
	            	case 21:
	            		break;
	            	case 22:
	            		break;
	            	case 23:
	            		break;
	            	case 24:
	            		break;
	            	case 25:
	            		break;
	            	case 26:
	            		break;
	            	case 27:
	            		break;
	            	case 28:
	            		break;
	            	case 29:
	            		break;
	            	case 30:
	            		break;
	            	case 31:
	            		break;
	            	case 32:
	            		break;
	            	case 33:
	            		break;
	            	case 34:
	            		break;
	            	case 35:
	            		break;
	            	case 36:
	            		break;
	            	case 37:
	            		break;
	            	case 38:
	            		break;
	            	case 39:
	            		break;
	            	case 40:
	            		break;
	            	case 41:
	            		break;
	            	case 42:
	            		break;
	            	case 43:
	            		break;
	            	case 44:
	            		break;
	            	case 45:
	            		break;
	            	case 46:
	            		break;
	            	case 47:
	            		break;
	            	case 48:
	            		break;
	            	case 49:
	            		break;
	            	case 50:
	            		break;
	            	case 51:
	            		break;
	            	case 52:
	            		break;
	            	case 53:
	            		break;
	            	case 54:
	            		break;
	            	case 55:
	            		break;
	            	case 56:
	            		break;
	            	case 57:
	            		break;
	            	case 58:
	            		break;
	            	case 59:
	            		break;
	            	case 60:
	            		break;
	            	case 61:
	            		break;
	            	case 62:
	            		break;
	            	case 63:
	            		break;
	            	case 64:
	            		break;
	            	case 65:
	            		break;
	            	case 66:
	            		break;
	            	case 67:
	            		break;
	            	case 68:
	            		break;
	            	case 69:
	            		break;
	            	case 70:
	            		break;
	            	case 71:
	            		break;
	            	case 72:
	            		break;
	            	case 73:
	            		break;
	            	case 74:
	            		break;
	            	case 75:
	            		break;
	            	case 76:
	            		break;
	            	case 77:
	            		break;
	            	case 78:
	            		break;
	            	case 79:
	            		break;
	            	case 80:
	            		break;
	            	case 81:
	            		break;
	            	case 82:
	            		break;
	            	case 83:
	            		break;
	            	case 84:
	            		break;
	            	case 85:
	            		break;
	            	case 86:
	            		break;
	            	case 87:
	            		break;
	            	case 88:
	            		break;
	            	case 89:
	            		break;
	            	case 90:
	            		break;
	            	case 91:
	            		break;
	            	case 92:
	            		break;
	            	case 93:
	            		break;
	            	case 94:
	            		break;
	            	case 95:
	            		break;
	            	case 96:
	            		break;
	            	case 97:
	            		break;
	            	case 98:
	            		break;
	            	case 99:
	            		break;
	            	case 100:
	            		break;
	            	case 101:
	            		break;
	            	case 102:
	            		break;
	            	case 103:
	            		break;
	            	case 104:
	            		break;
	            	case 105:
	            		break;
	            	case 106:
	            		break;
	            	case 107:
	            		break;
	            	case 108:
	            		break;
	            	case 109:
	            		break;
	            	case 110:
	            		break;
	            	case 111:
	            		break;
	            	case 112:
	            		break;
	            	case 113:
	            		break;
	            	}
                }
	        }
            
            for (int unit=0; unit<p3Troops.size(); unit++) {
            	int[] go=p3Troops.get(unit);
            	int repx=((go[1]+1)*scale)-(int)(((double)xPos/3)*scale);
            	int repy=((go[2]+1)*scale)-(int)(((double)yPos/3)*scale);
            	g.setColor(new Color(0,0,150));
                if (repx+scale<0 || repx>600 || repy+scale<0 || repy>600) {}
                else if (clouds[go[1]][go[2]]==0) {
	            	switch (go[0]) {
	            	case 1: g.drawImage(imageS, repx, repy, scale, scale, this); g.drawRect(repx, repy, scale, scale);
	            		break;
	            	case 2:
	            		break;
	            	case 3:
	            		break;
	            	case 4:
	            		break;
	            	case 5:
	            		break;
	            	case 6:
	            		break;
	            	case 7:
	            		break;
	            	case 8:
	            		break;
	            	case 9:
	            		break;
	            	case 10:
	            		break;
	            	case 11:
	            		break;
	            	case 12:
	            		break;
	            	case 13:
	            		break;
	            	case 14:
	            		break;
	            	case 15:
	            		break;
	            	case 16:
	            		break;
	            	case 17:
	            		break;
	            	case 18:
	            		break;
	            	case 19:
	            		break;
	            	case 20:
	            		break;
	            	case 21:
	            		break;
	            	case 22:
	            		break;
	            	case 23:
	            		break;
	            	case 24:
	            		break;
	            	case 25:
	            		break;
	            	case 26:
	            		break;
	            	case 27:
	            		break;
	            	case 28:
	            		break;
	            	case 29:
	            		break;
	            	case 30:
	            		break;
	            	case 31:
	            		break;
	            	case 32:
	            		break;
	            	case 33:
	            		break;
	            	case 34:
	            		break;
	            	case 35:
	            		break;
	            	case 36:
	            		break;
	            	case 37:
	            		break;
	            	case 38:
	            		break;
	            	case 39:
	            		break;
	            	case 40:
	            		break;
	            	case 41:
	            		break;
	            	case 42:
	            		break;
	            	case 43:
	            		break;
	            	case 44:
	            		break;
	            	case 45:
	            		break;
	            	case 46:
	            		break;
	            	case 47:
	            		break;
	            	case 48:
	            		break;
	            	case 49:
	            		break;
	            	case 50:
	            		break;
	            	case 51:
	            		break;
	            	case 52:
	            		break;
	            	case 53:
	            		break;
	            	case 54:
	            		break;
	            	case 55:
	            		break;
	            	case 56:
	            		break;
	            	case 57:
	            		break;
	            	case 58:
	            		break;
	            	case 59:
	            		break;
	            	case 60:
	            		break;
	            	case 61:
	            		break;
	            	case 62:
	            		break;
	            	case 63:
	            		break;
	            	case 64:
	            		break;
	            	case 65:
	            		break;
	            	case 66:
	            		break;
	            	case 67:
	            		break;
	            	case 68:
	            		break;
	            	case 69:
	            		break;
	            	case 70:
	            		break;
	            	case 71:
	            		break;
	            	case 72:
	            		break;
	            	case 73:
	            		break;
	            	case 74:
	            		break;
	            	case 75:
	            		break;
	            	case 76:
	            		break;
	            	case 77:
	            		break;
	            	case 78:
	            		break;
	            	case 79:
	            		break;
	            	case 80:
	            		break;
	            	case 81:
	            		break;
	            	case 82:
	            		break;
	            	case 83:
	            		break;
	            	case 84:
	            		break;
	            	case 85:
	            		break;
	            	case 86:
	            		break;
	            	case 87:
	            		break;
	            	case 88:
	            		break;
	            	case 89:
	            		break;
	            	case 90:
	            		break;
	            	case 91:
	            		break;
	            	case 92:
	            		break;
	            	case 93:
	            		break;
	            	case 94:
	            		break;
	            	case 95:
	            		break;
	            	case 96:
	            		break;
	            	case 97:
	            		break;
	            	case 98:
	            		break;
	            	case 99:
	            		break;
	            	case 100:
	            		break;
	            	case 101:
	            		break;
	            	case 102:
	            		break;
	            	case 103:
	            		break;
	            	case 104:
	            		break;
	            	case 105:
	            		break;
	            	case 106:
	            		break;
	            	case 107:
	            		break;
	            	case 108:
	            		break;
	            	case 109:
	            		break;
	            	case 110:
	            		break;
	            	case 111:
	            		break;
	            	case 112:
	            		break;
	            	case 113:
	            		break;
	                }
                }
            }
            
            
            g.setColor(new Color(255,255,0));
            double XPOS=((double)xPos)/3;
            double YPOS=((double)yPos)/3;
            int drawerx=(((spaceNum%200)+1)*scale)-(int)(XPOS*scale);
            int drawery=(((spaceNum/200)+1)*scale)-(int)(YPOS*scale);
            g.drawRect(drawerx,drawery,scale-1,scale-1);
            g.setColor(Color.white);
            g.fillRect(600,0,210,600);
            g.setColor(Color.black);
            switch(s[spaceNum%200][spaceNum/200]) {
            case 1:	switch(type[spaceNum%200][spaceNum/200]) {
            case 1: g.drawString("Forest",650,50);
            	break;
            case 2: g.drawString("Desert",650,50);
            	break;
            case 3: g.drawString("Grassland",650,50);
            	break;
            case 4: g.drawString("Hill",650,50);
            	break;
            case 5: g.drawString("Plains",650,50);
            	break;
            case 6: g.drawString("Snow",650,50);
            	break;
            case 7: g.drawString("Tundra",650,50);
            	break;
            case 8: g.drawString("Jungle",650,50);
            	break;
            }
            	break;
            case 2:	g.drawString("Ocean",650,50);
            	break;
            case 3:	g.drawString("Shore",650,50);
            	break;
            case 4:	g.drawString("Don't know how this got selected",650,50);
            	break;
            case 5:	g.drawString("Player 1",650,50);
            	break;
            case 6:	g.drawString("Player 2",650,50);
            	break;
            case 7:	g.drawString("Barbarian",650,50);
            	break;
            case 8:	g.drawString("Player 3",650,50);
            	break;
            case 9:	g.drawString("AI",650,50);
            	break;
            case 10: g.drawString("Mountains",650,50);
            	break;
            default: g.drawString("Land",650,50);
            }
        }

        
        
        
        
        else if (hiRez && ready) {
            if (switcheroo) {
                for (int xo=0;xo<200;xo++) {
                    for (int yo=0;yo<200;yo++) {
                        
                        red=(int)(Math.random()*91+10);
                        green=(int)(Math.random()*56+200);
                        blue=(int)(Math.random()*41+10);
                        treeColor[xo][yo][0]=red;
                        treeColor[xo][yo][1]=green;
                        treeColor[xo][yo][2]=blue;
                        red=(int)(Math.random()*21+75);
                        green=(int)(Math.random()*21+40);
                        blue=(int)(Math.random()*21+15);
                        treeColor[xo][yo][3]=red;
                        treeColor[xo][yo][4]=green;
                        treeColor[xo][yo][5]=blue;
                    }
                }
            }
            for (int i=0; i<200; i++) {
                for (int j=0; j<200; j++) {
                    double XPOS=((double)xPos)/3;
                    double YPOS=((double)yPos)/3;
                    int repx=((i+1)*scale)-(int)(XPOS*scale);
                    int repy=((j+1)*scale)-(int)(YPOS*scale);
                    
                    
                    if (repx+scale<-scale || repx>600+scale || repy+scale<-scale || repy>600+scale) {}
                    else {
                        int drawerx=((i+1)*scale)-(int)(XPOS*scale);
                        int drawery=((j+1)*scale)-(int)(YPOS*scale);
                        int[] xTri=new int[3];
                        int[] yTri=new int[3];
                        if (clouds[i][j]==0) {
                            if (s[i][j]==1) {
                            	int xPose=((i+1)*scale)-(int)(XPOS*scale);
                            	int yPose=((j+1)*scale)-(int)(YPOS*scale);
                            	if (type[i][j]==1) {  //forest
                            		g.setColor(new Color(77,189,51));
                                	g.fillRect(xPose, yPose, scale, scale);
                                    g.setColor(new Color(77,189,51));
                                    g.fillRect(repx, repy, scale, scale);
                                    g.setColor(new Color(treeColor[i][j][3],treeColor[i][j][4],treeColor[i][j][5]));
                                    xTri[0]=drawerx+(scale/8);
                                    xTri[1]=drawerx+(scale/4);
                                    xTri[2]=drawerx+(scale/4);
                                    yTri[0]=drawery+((7*scale)/8);
                                    yTri[1]=drawery+((6*scale)/8);
                                    yTri[2]=drawery+((7*scale)/8);
                                    g.fillPolygon(xTri, yTri, 3);
                                    xTri[0]=drawerx+(scale/2);
                                    xTri[1]=drawerx+((3*scale)/8);
                                    xTri[2]=drawerx+((3*scale)/8);
                                    yTri[0]=drawery+((7*scale)/8);
                                    yTri[1]=drawery+((6*scale)/8);
                                    yTri[2]=drawery+((7*scale)/8);
                                    g.fillPolygon(xTri, yTri, 3);
                                    g.fillRect(drawerx+(scale/4),drawery+(scale/2),scale/8,(3*scale)/8);
                                    
                                    xTri[0]=drawerx+((5*scale)/8);
                                    xTri[1]=drawerx+((3*scale)/4);
                                    xTri[2]=drawerx+((3*scale)/4);
                                    yTri[0]=drawery+((6*scale)/8);
                                    yTri[1]=drawery+((5*scale)/8);
                                    yTri[2]=drawery+((6*scale)/8);
                                    g.fillPolygon(xTri, yTri, 3);
                                    xTri[0]=drawerx+(scale);
                                    xTri[1]=drawerx+((7*scale)/8);
                                    xTri[2]=drawerx+((7*scale)/8);
                                    yTri[0]=drawery+((6*scale)/8);
                                    yTri[1]=drawery+((5*scale)/8);
                                    yTri[2]=drawery+((6*scale)/8);
                                    g.fillPolygon(xTri, yTri, 3);
                                    g.fillRect(drawerx+((3*scale)/4),drawery+((3*scale)/8),scale/8,(3*scale)/8);
                                    g.setColor(new Color(treeColor[i][j][0],treeColor[i][j][1],treeColor[i][j][2]));
                                    g.fillOval(drawerx+(scale/8),drawery+(scale/8),((3*scale)/8),((3*scale)/8));
                                    g.fillOval(drawerx+((5*scale)/8),drawery,((3*scale)/8),((3*scale)/8));
                            	}
                            	else if (type[i][j]==2) {  //desert
                            		if (setDesertColor) {
                            			for (int col=0;col<16;col++) {
                            				for (int loc=0;loc<16;loc++) {
                                    			int rojo=(int)(Math.random()*16+240);
                                    			int verde=(int)(Math.random()*21+230);
                                    			int azul=(int)(Math.random()*21+70);
                            					desertColor[col][loc][0]=rojo;
                            					desertColor[col][loc][1]=verde;
                            					desertColor[col][loc][2]=azul;
                            				}
                            			}
                    					setDesertColor=false;
                            		}
                            		g.setColor(new Color(250,238,85));
                                	g.fillRect(xPose, yPose, scale, scale);
                        			for (int col=0;col<16;col++) {
                        				for (int loc=0;loc<16;loc++) {
                        					g.setColor(new Color(desertColor[col][loc][0],desertColor[col][loc][1],desertColor[col][loc][2]));
                        					g.fillRect(xPose+(int)((double)(col*scale)/16), yPose+(int)((double)(loc*scale)/16), scale/16, scale/16);
                        				}
                        			}
                            	}
                            	else if (type[i][j]==3) {  //grassland
                            		g.setColor(new Color(68,238,85));
                                	g.fillRect(xPose, yPose, scale, scale);
                                	g.setColor(new Color(46,160,57));
                                	int xPlacement=xPose+(scale/16);
                                	int yPlacement=yPose+(scale/16);
                                	for (int y=0; y<scale; y+=scale/8) {
                                		for (int x=0; x<scale; x+=scale/16) {
                                			if (x%2==0)
                                				g.drawLine(xPlacement+x, yPlacement+y, xPlacement+x, yPlacement+y+(scale/16));
                                			else
                                				g.drawLine(xPlacement+x, yPlacement+y+(scale/16), xPlacement+x, yPlacement+y+(scale/8));
                                		}
                                	}
                            	}
                            	else if (type[i][j]==4) {  //hill
                            		g.setColor(new Color(171,247,179));
                                	g.fillRect(xPose, yPose, scale, scale);
                                	g.setColor(Color.black);
                                	g.drawArc(xPose-((8*scale)/4), yPose+((2*scale)/3), scale*4, scale*4, 60, 30);
                                	g.drawArc(xPose-scale, yPose+((7*scale)/16), scale*4, scale*4, 90, 27);
                                	g.drawArc(xPose-((6*scale)/4), yPose+((13*scale)/32), scale*3, scale*3, 70, 20);
                            	}
                            	else if (type[i][j]==5) {  //plains
                            		g.setColor(new Color(165,245,158));
                                	g.fillRect(xPose, yPose, scale, scale);
                            	}
                            	else if (type[i][j]==6) {  //snow
                            		g.setColor(new Color(230,245,254));
                                	g.fillRect(xPose, yPose, scale, scale);
                            	}
                            	else if (type[i][j]==7) {  //tundra
                            		g.setColor(new Color(209,253,216));
                                	g.fillRect(xPose, yPose, scale, scale);
                                	
                            	}
                            	else if (type[i][j]==8) {  //jungle
                            		g.setColor(new Color(15,245,52));
                                	g.fillRect(xPose, yPose, scale, scale);
                                	g.setColor(new Color(10,200,45));
                                	g.fillRoundRect(xPose+(scale/8), yPose+(scale/8), (3*scale)/4, (3*scale)/4, scale/16, scale/16);
                            	}
                                
                            }
                            else if (s[i][j]==2) {
                            	g.setColor(new Color(4,36,174));
                                g.fillRect(drawerx,drawery,drawerx+scale,drawery+scale);
                            }
                            else if (s[i][j]==3) {
                                g.setColor(new Color(64,164,223));
                                g.fillRect(repx, repy, scale, scale);
                            }
                            
                            else if (s[i][j]==5) {
                                g.setColor(new Color(109,109,109));
                                g.fillRect(repx, repy, scale, scale);
                                g.setColor(new Color(232,125,11));
                                g.drawRect(repx, repy, scale-1, scale-1);
                            }
                            else if (s[i][j]==6) {
                                g.setColor(new Color(109,109,109));
                                g.fillRect(repx, repy, scale, scale);
                                g.setColor(new Color(0,150,0));
                                g.drawRect(repx, repy, scale-1, scale-1);
                            }
                            
                            else if (s[i][j]==7) {
                                g.setColor(new Color(148,71,41));
                                g.fillRect(repx, repy, scale, scale);
                                g.setColor(new Color(85,51,17));
                                g.drawRect(repx, repy, scale-1, scale-1);
                                g.setColor(Color.black);
                                g.drawRect((i*scale)-(int)(XPOS*scale)+(scale/5)+scale,(j*scale)-(int)(YPOS*scale)+(scale/5)+scale,scale/5,scale/5);
                            }
                            
                            else if (s[i][j]==8) {
                                g.setColor(new Color(109,109,109));
                                g.fillRect(repx, repy, scale, scale);
                                    g.setColor(new Color(0,0,150));
                                g.drawRect(repx, repy, scale-1, scale-1);
                            }
                            else if (s[i][j]==9) {
                                g.setColor(new Color(109,109,109));
                                g.fillRect(repx, repy, scale, scale);
                                g.setColor(new Color(238,55,13));
                                g.drawRect(repx, repy, scale-1, scale-1);
                            }
                            else if (s[i][j]==10) {
                                g.setColor(new Color(77,189,51));
                                g.fillRect(repx, repy, scale, scale);
                                
                                
                                g.setColor(new Color(91,59,21));
                                xTri[0]=repx+(scale/8);
                                xTri[1]=repx+((3*scale)/8);
                                xTri[2]=repx+((5*scale)/8);
                                yTri[0]=repy+((3*scale)/8);
                                yTri[1]=repy+(scale/8);
                                yTri[2]=repy+((3*scale)/8);
                                g.fillPolygon(xTri, yTri, 3);
                                
                                xTri[0]=repx+(scale/2);
                                xTri[1]=repx+((3*scale)/4);
                                xTri[2]=repx+(scale);
                                yTri[0]=repy+(scale/2);
                                yTri[1]=repy+(scale/4);
                                yTri[2]=repy+(scale/2);
                                g.fillPolygon(xTri, yTri, 3);
                                
                                xTri[0]=repx+(scale/2);
                                xTri[1]=repx+((3*scale)/4);
                                xTri[2]=repx+(scale);
                                yTri[0]=repy+((3*scale)/4);
                                yTri[1]=repy+(scale/2);
                                yTri[2]=repy+((3*scale)/4);
                                g.fillPolygon(xTri, yTri, 3);
                                
                                xTri[0]=repx+(scale/8);
                                xTri[1]=repx+((3*scale)/8);
                                xTri[2]=repx+((9*scale)/16);
                                yTri[0]=repy+((5*scale)/8);
                                yTri[1]=repy+((3*scale)/8);
                                yTri[2]=repy+((9*scale)/16);
                                g.fillPolygon(xTri, yTri, 3);
                                
                                xTri[0]=repx+((3*scale)/8);
                                xTri[1]=repx+((5*scale)/8);
                                xTri[2]=repx+((7*scale)/8);
                                yTri[0]=repy+(scale);
                                yTri[1]=repy+((3*scale)/4);
                                yTri[2]=repy+scale;
                                g.fillPolygon(xTri, yTri, 3);
                                
                                g.setColor(Color.black);
                                g.drawLine(repx+(scale/8),repy+((3*scale)/8),repx+((3*scale)/8),repy+(scale/8));
                                g.drawLine(repx+((3*scale)/8),repy+(scale/8),repx+((5*scale)/8),repy+((3*scale)/8));
                                g.drawLine(repx+(scale/2),repy+(scale/2),repx+((3*scale)/4),repy+(scale/4));
                                g.drawLine(repx+((3*scale)/4),repy+(scale/4),repx+(scale),repy+(scale/2));
                                g.drawLine(repx+(scale/2),repy+((3*scale)/4),repx+((3*scale)/4),repy+(scale/2));
                                g.drawLine(repx+((3*scale)/4),repy+(scale/2),repx+(scale),repy+((3*scale)/4));
                                g.drawLine(repx+(scale/8),repy+((5*scale)/8),repx+((3*scale)/8),repy+((3*scale)/8));
                                g.drawLine(repx+((3*scale)/8),repy+((3*scale)/8),repx+((9*scale)/16),repy+((9*scale)/16));
                                
                                g.drawLine(repx+((3*scale)/8),repy+(scale),repx+((5*scale)/8),repy+((3*scale)/4));
                                g.drawLine(repx+((5*scale)/8),repy+((3*scale)/4),repx+((3*scale)/4),repy+((7*scale)/8));
                                
                                
                                if (s[i+1][j]!=10 && s[i-1][j]!=10) {
                                    if (s[i+1][j]==1||s[i+1][j]>4)
                                        g.setColor(new Color(77,189,51));
                                    else if (s[i+1][j]==2)
                                        g.setColor(new Color(64,164,223));
                                    else if (s[i+1][j]==3)
                                        g.setColor(new Color(70,176,137));
                                    else if (s[i+1][j]==4)
                                        g.setColor(new Color(0,0,0));
                                    xTri[0]=repx+(scale);
                                    xTri[1]=repx+((3*scale)/4);
                                    xTri[2]=repx+(scale);
                                    yTri[0]=repy+(scale/2);
                                    yTri[1]=repy+(scale/4);
                                    yTri[2]=repy;
                                    g.fillPolygon(xTri, yTri, 3);
                                }
                                
                            }
                            if (territories[i][j]==1) {
                            	g.setColor(new Color(232,125,11));
                            	try {
                            		if (territories[i][j-1]!=1)
                                    	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                            	} catch(Exception e) {
                                	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                            	}
                            	try {
                            		if (territories[i-1][j]!=1) {
                                    	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            		}
                            	} catch(Exception e) {
                                	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	}
                            	try {
                            		if (territories[i+1][j]!=1)
                                    	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	} catch(Exception e) {
                                	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	}
                            	try {
                            		if (territories[i][j+1]!=1)
                                    	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	} catch(Exception e) {
                                	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	}
                            }
                            if (territories[i][j]==2) {
                            	g.setColor(new Color(0,150,0));
                            	try {
                            		if (territories[i][j-1]!=2)
                                    	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                            	} catch(Exception e) {
                                	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                            	}
                            	try {
                            		if (territories[i-1][j]!=2) {
                                    	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            		}
                            	} catch(Exception e) {
                                	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	}
                            	try {
                            		if (territories[i+1][j]!=2)
                                    	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	} catch(Exception e) {
                                	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	}
                            	try {
                            		if (territories[i][j+1]!=2)
                                    	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	} catch(Exception e) {
                                	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	}
                            }
                            if (territories[i][j]==3) {
                            	g.setColor(new Color(85,51,17));
                            	try {
                            		if (territories[i][j-1]!=3)
                                    	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                            	} catch(Exception e) {
                                	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                            	}
                            	try {
                            		if (territories[i-1][j]!=3) {
                                    	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            		}
                            	} catch(Exception e) {
                                	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	}
                            	try {
                            		if (territories[i+1][j]!=3)
                                    	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	} catch(Exception e) {
                                	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	}
                            	try {
                            		if (territories[i][j+1]!=3)
                                    	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	} catch(Exception e) {
                                	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	}
                            }
                            if (territories[i][j]==4) {
                            	g.setColor(new Color(0,0,150));
                            	try {
                            		if (territories[i][j-1]!=4)
                                    	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                            	} catch(Exception e) {
                                	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                            	}
                            	try {
                            		if (territories[i-1][j]!=4) {
                                    	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            		}
                            	} catch(Exception e) {
                                	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	}
                            	try {
                            		if (territories[i+1][j]!=4)
                                    	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	} catch(Exception e) {
                                	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	}
                            	try {
                            		if (territories[i][j+1]!=4)
                                    	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	} catch(Exception e) {
                                	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	}
                            }
                            if (territories[i][j]==5) {
                            	g.setColor(new Color(238,55,13));
                            	try {
                            		if (territories[i][j-1]!=5)
                                    	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                            	} catch(Exception e) {
                                	g.drawLine(repx, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, repy);
                            	}
                            	try {
                            		if (territories[i-1][j]!=5) {
                                    	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            		}
                            	} catch(Exception e) {
                                	g.drawLine(repx, repy, repx, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	}
                            	try {
                            		if (territories[i+1][j]!=5)
                                    	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	} catch(Exception e) {
                                	g.drawLine(((i+2)*scale)-(int)(XPOS*scale)-1, repy, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	}
                            	try {
                            		if (territories[i][j+1]!=5)
                                    	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	} catch(Exception e) {
                                	g.drawLine(repx, ((j+2)*scale)-(int)(YPOS*scale)-1, ((i+2)*scale)-(int)(XPOS*scale)-1, ((j+2)*scale)-(int)(YPOS*scale)-1);
                            	}
                            }
                        }
                        else {
                            if (neverever) {
                                for (int xo=0;xo<200;xo++) {
                                    for (int yo=0;yo<200;yo++) {
                                        int xpp=(int)(Math.random()*7+2);
                                        cloudPos[xo][yo][0]=xpp;
                                        int ypp=(int)(Math.random()*7+2);
                                        cloudPos[xo][yo][1]=ypp;
                                    }
                                }
                                neverever=false;
                            }
                            g.setColor(new Color(245,246,255));
                            g.fillRect(repx, repy, scale, scale);
                            g.setColor(Color.black);
                            g.drawArc(repx+cloudPos[i][j][0],repy+cloudPos[i][j][1],scale/2,scale/2,0,180);
                        }
                    }
                }
            }
            switcheroo=false;
            g.setColor(new Color(255,255,0));
            double XPOS=((double)xPos)/3;
            double YPOS=((double)yPos)/3;
            int drawerx=(((spaceNum%200)+1)*scale)-(int)(XPOS*scale);
            int drawery=(((spaceNum/200)+1)*scale)-(int)(YPOS*scale);
            g.drawRect(drawerx,drawery,scale-1,scale-1);
            g.drawRect(drawerx+1,drawery+1,scale-3,scale-3);
            g.setColor(Color.white);
            g.fillRect(600,0,210,600);
            g.setColor(Color.black);
            switch(s[spaceNum%200][spaceNum/200]) {
            case 1:	switch(type[spaceNum%200][spaceNum/200]) {
            case 1: g.drawString("Forest",650,50);
        	break;
	        case 2: g.drawString("Desert",650,50);
	        	break;
	        case 3: g.drawString("Grassland",650,50);
	        	break;
	        case 4: g.drawString("Hill",650,50);
	        	break;
	        case 5: g.drawString("Plains",650,50);
	        	break;
	        case 6: g.drawString("Snow",650,50);
	        	break;
	        case 7: g.drawString("Tundra",650,50);
	        	break;
	        case 8: g.drawString("Jungle",650,50);
	        	break;
	        }
            	break;
            case 2:	g.drawString("Ocean",650,50);
            	break;
            case 3:	g.drawString("Shore",650,50);
            	break;
            case 4:	g.drawString("Don't know how this got selected",650,50);
            	break;
            case 5:	g.drawString("Player 1",650,50);
            	break;
            case 6:	g.drawString("Player 2",650,50);
            	break;
            case 7:	g.drawString("Barbarian",650,50);
            	break;
            case 8:	g.drawString("Player 3",650,50);
            	break;
            case 9:	g.drawString("AI",650,50);
            	break;
            case 10: g.drawString("Mountains",650,50);
            	break;
            default: g.drawString("Land",650,50);
            }
        }
        switcheroo=false;
        
        
        //minimap
        if (ready) {
	        int XPOS=605;
	        int YPOS=400;
	        for (int i=0; i<200; i++) {
	            for (int j=0; j<200; j++) {
	                if (clouds[i][j]==0) {
	                	int r=0;
	                	int gr=0;
	                	int b=0;
	                    if (s[i][j]==1) {
	                        g.setColor(new Color(77,189,51));
	                        r=77;
	                        gr=189;
	                        b=51;
	                        g.drawLine(XPOS+(i-1),YPOS+(j-1),XPOS+(i-1),YPOS+(j-1));
	                    }
	                    else if (s[i][j]==2) {
	                    	g.setColor(new Color(4,36,174));
	                        r=4;
	                        gr=36;
	                        b=174;
	                        g.drawLine(XPOS+(i-1),YPOS+(j-1),XPOS+(i-1),YPOS+(j-1));
	                    }
	                    else if (s[i][j]==3) {
	                        g.setColor(new Color(64,164,223));
	                        r=64;
	                        gr=164;
	                        b=223;
	                        g.drawLine(XPOS+(i-1),YPOS+(j-1),XPOS+(i-1),YPOS+(j-1));
	                    }
	                    
	                    else if (s[i][j]==5) {
	                        g.setColor(new Color(232,125,11));
	                        r=232;
	                        gr=125;
	                        b=11;
	                        g.drawLine(XPOS+(i-1),YPOS+(j-1),XPOS+(i-1),YPOS+(j-1));
	                    }
	                    else if (s[i][j]==6) {
	                        g.setColor(new Color(0,150,0));
	                        r=0;
	                        gr=150;
	                        b=0;
	                        g.drawLine(XPOS+(i-1),YPOS+(j-1),XPOS+(i-1),YPOS+(j-1));
	                    }
	                    
	                    else if (s[i][j]==7) {
	                        g.setColor(new Color(85,51,17));
	                        r=85;
	                        gr=51;
	                        b=17;
	                        g.drawLine(XPOS+(i-1),YPOS+(j-1),XPOS+(i-1),YPOS+(j-1));
	                    }
	                    
	                    else if (s[i][j]==8) {
	                        g.setColor(new Color(0,0,150));
	                        r=0;
	                        gr=0;
	                        b=150;
	                        g.drawLine(XPOS+(i-1),YPOS+(j-1),XPOS+(i-1),YPOS+(j-1));
	                    }
	                    else if (s[i][j]==9) {
	                        g.setColor(new Color(238,55,13));
	                        r=238;
	                        gr=55;
	                        b=13;
	                        g.drawLine(XPOS+(i-1),YPOS+(j-1),XPOS+(i-1),YPOS+(j-1));
	                    }
	                    else if (s[i][j]==10) {
	                        g.setColor(new Color(91,59,21));
	                        r=91;
	                        gr=59;
	                        b=21;
	                        g.drawLine(XPOS+(i-1),YPOS+(j-1),XPOS+(i-1),YPOS+(j-1));
	                    }
	                    
	                    
	                    if (territories[i][j]==1) {
	                    	g.setColor(new Color((r+4*232)/5,(gr+4*125)/5,(b+4*11)/5));
	                    	g.drawLine(XPOS+(i-1),YPOS+(j-1),XPOS+(i-1),YPOS+(j-1));
	                    }
	                    if (territories[i][j]==2) {
	                    	g.setColor(new Color((r)/5,(gr+4*150)/5,(b)/5));
	                    	g.drawLine(XPOS+(i-1),YPOS+(j-1),XPOS+(i-1),YPOS+(j-1));
	                    }
	                    if (territories[i][j]==3) {
	                    	g.setColor(new Color((r+4*85)/5,(gr+4*51)/5,(b+4*17)/5));
	                    	g.drawLine(XPOS+(i-1),YPOS+(j-1),XPOS+(i-1),YPOS+(j-1));
	                    }
	                    if (territories[i][j]==4) {
	                    	g.setColor(new Color((r)/5,(gr)/5,(b+4*150)/5));
	                    	g.drawLine(XPOS+(i-1),YPOS+(j-1),XPOS+(i-1),YPOS+(j-1));
	                    }
	                    if (territories[i][j]==5) {
	                    	g.setColor(new Color((r+4*238)/5,(gr+4*55)/5,(b+4*13)/5));
	                    	g.drawLine(XPOS+(i-1),YPOS+(j-1),XPOS+(i-1),YPOS+(j-1));
	                    }
	                }
	                else {
	                    g.setColor(new Color(245,246,255));
	                    g.drawLine(XPOS+(i-1),YPOS+(j-1),XPOS+(i-1),YPOS+(j-1));
	                }
	            }
	        }
	        g.setColor(Color.yellow);
	        g.drawRect((int)(604+xPos/3), (int)(399+yPos/3), 600/scale, 600/scale);
	        
	        
	        
	        
	        
        }
    }
}