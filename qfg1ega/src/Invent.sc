;;; Sierra Script 1.0 - (do not remove this comment)
(script# INVENT)
(include game.sh)
(use Main)
(use System)

(public
	SaidInv 0
	WtCarried 1
	SaidSpell 2
)

(local
	[invNames NUM_INVITEMS] =
		[
			'/silver,alm,alm'
			'/gold'
			'/food,ration'
			'/mandrake'
			'/key'
			'/blade'
			'/dagger'
			'/leather,armor'
			'/shield'
			'/note,message,note,scroll'
			'/apple,apple'
			'/carrot,produce'
			'/gem[<magic,glowing]'
			'/vase'
			'/candelabra'
			'/box<music'
			'/candlestick'
			'/pearl,necklace,string'
			'/ring[<healer,gold]'
			'/seed'
			'/boulder'
			'/flower'
			'/lockpick[<hasp]'
			'/kit[<thief,implement]'
			'/certificate[<thief]'
			'/bottle[<empty,!*]'
			'/fur[<green]'
			'/dust<faerie,magic'
			'/water,(bottle<water)'
			'/mushroom[<magic]'
			'/claw[<cheetaur]'
			'/beard[<troll]'
			'/chainmail,chain,chainmail'
			'/healing,(potion<healing)'
			'/potion<mana,magic,power'
			'/potion<vigor,stamina'
			'/potion<hero,heroism'
			'/potion<disenchant,disenchant'
			'/grease,(potion,grease<ghost,ghoul)'
			'/mirror[<magic,hand]'
			'/acorn[<dryad,magic]'
		]
	[verbMagic NUM_SPELLS] =
		[
			'/open,(spell<open)'
			'/detect,(spell,magic<detect),(spell<magic<detect)'
			'/trigger,(spell<trigger)'
			'/dazzle,(spell<dazzle)'
			'/zap,(spell<zap)'
			'/calm,(spell<calm)'
			'/flame,fire,dart,fireball,(spell<flame,fire,dart,fireball)'
			'/fetch,(spell<fetch)'
		]
)
(procedure (SaidInv event &tmp index obj)
	;parses a saidEvent and returns the inventory number of the item being asked about.
	;if there is no match, it returns NULL (or 0)
	
	(= index 0)
	(while (< index NUM_INVITEMS)
		(if (Said [invNames index]) (return (+ index 1)))
		(++ index)
	)
	(event claimed: TRUE)
	(return NULL)
)

(procedure (WtCarried &tmp index obj)
	(= obj 1)
	(= index 0)
	(while (<= obj NUM_INVITEMS)
		(= index
			(+ index (* [invNum obj] [invWeight obj]))
		)
		(++ obj)
	)
	(= index (/ (+ index 59) 60))
)

(procedure (SaidSpell event &tmp index obj)
	(= index 0)
	(while (< index 8)
		(if (Said [verbMagic index]) (return (+ OPEN index)))
		(++ index)
	)
	(event claimed: TRUE)
	(return FALSE)
)

(class Inventory of Object
	;the standard inventory system used by most Sierra games is 
	;insufficient for the more complicated inventory management 
	;used by Hero's Quest (and later games).
	;So the Inventory object is not used in this game.
	(properties)
	
	(method (init)
		(return TRUE)
	)
)
