;;; Sierra Script 1.0 - (do not remove this comment)
(include system.sh)
(include sci2.sh)
(include Flags.sh)
(include Puzzles.sh)
(include Nums.sh)
(include menu.sh)
(include combat.sh)

; howFast values
(enum
	slow
	medium
	fast
	fastest
)

; General Game Defines
(define NUMSTATS		25)
(define NUM_INVITEMS	41)
(define NUM_SPELLS		8)
(define GAMEDAY		3600)	;number of ticks per game day
(define GAMEHOUR	150) 	;number of ticks per game hour
(define STAM_RATE	20)		;recovery rate for stamina
(define HEAL_RATE	15)		;recovery rate for health
(define MANA_RATE	5)		;recovery rate for mana

; Character Classes
(enum
	FIGHTER
	MAGIC_USER
	THIEF
)

; Ego Moving Modes
(enum -1
	MOVE_NOCHANGE
	MOVE_WALK
	MOVE_RUN
	MOVE_SNEAK
)

; Skills
(enum
	STR			;0
	INT			;1
	AGIL		;2
	VIT			;3
	LUCK		;4
	WEAPON		;5
	PARRY		;6
	DODGE		;7
	STEALTH		;8
	PICK		;9
	THROW		;10
	CLIMB		;11
	MAGIC		;12
	EXPER		;13
	HEALTH		;14
	STAMINA		;15
	MANA		;16
; Magic Spells
	OPEN		;17
	DETMAGIC	;18
	TRIGGER		;19
	DAZZLE		;20
	ZAP			;21
	CALM		;22
	FLAMEDART	;23
	FETCH		;24
)

; Inventory
(enum 1
	iSilver			;1
	iGold			;2
	iRations		;3
	iMandrake		;4
	iBrassKey		;5
	iSword			;6
	iDagger			;7
	iLeather		;8
	iShield			;9
	iPaper			;10
	iFruit			;11
	iVegetables		;12
	iMagicGem		;13
	iVase			;14
	iCandelabra		;15
	iMusicBox		;16
	iCandlesticks	;17
	iPearls			;18
	iRing			;19
	iSeed			;20
	iRock			;21
	iFlowers		;22
	iLockPick		;23
	iThiefKit		;24
	iThiefLicense	;25
	iFlask			;26
	iGreenFur		;27
	iFairyDust		;28
	iWater			;29
	iMushroom		;30
	iCheetaurClaw	;31
	iTrollBeard		;32
	iChainmail		;33
	iHealingPotion	;34
	iManaPotion		;35
	iStaminaPotion	;36
	iHeroPotion 	;37		;Called "aa" in-game; according to the inventory script, it was intended to be a Potion of Heroism.
	iDisenchant 	;38
	iGhostOil 		;39
	iMagicMirror 	;40
	iAcorn 			;41
)

; Time Zones
(enum
	TIME_DAWN
	TIME_MIDMORNING
	TIME_MIDDAY
	TIME_MIDAFTERNOON
	TIME_SUNSET
	TIME_NIGHT
	TIME_MIDNIGHT
	TIME_NOTYETDAWN
)

;Random Encounter Entrances
(define reNORTH $0001)
(define reEAST	$0002)
(define reSOUTH $0004)
(define reWEST	$0008)

; Nest Condition
(enum
	nestInTree
	nestOnGround
	nestBurnt
)

; Bar Drinks
(enum
	drinkNothing
	drinkAle
	drinkSweat
	drinkBreath
)

; Baba Yaga states
(enum
	babaNAME
	babaBRAVE
	babaFETCH
	babaBRING
	babaFINALE
)

; Baba Yaga's Hut states
(enum
	hutINITIAL
	hutMETSKULL
	hutNODEAL
	hutDEALMADE
	hutGAVEGEM
)

; Inn Meal States
(enum
	mealNOTHING
	mealORDERED
	mealATTABLE
	mealFINISHED
)

; Kobold States
(enum
	koboldASLEEP
	koboldAWAKE
	koboldSTATE2
	koboldSTATE3
	koboldDEAD
)