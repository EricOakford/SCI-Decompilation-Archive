;;; Sierra Script 1.0 - (do not remove this comment)
(include system.sh) (include sci2.sh)
(include verbs.sh) (include talkers.sh)
(include flags.sh) (include puzzles.sh)
(include nums.sh) (include icons.sh)
(include combat.sh) (include maze.sh)
(include "815.shm")	;for death messages

(define STARTTELL 0)
(define ENDTELL	999)

(define SAME_COLOR 42)
(define CHANGE_COLOR 54)

; howFast values
(enum
	slow
	medium
	fast
	fastest
)

; General Game Defines
(define	NUM_STATS	5)
(define	NUM_SKILLS	8)
(define	NUM_DERIVS	4)
(define NUM_SPELLS	8)
(define NUM_ATTRIBS 25)	;(+ NUM_STATS NUM_SKILLS NUM_DERIVS NUM_SPELLS))
(define NUM_INVITEMS 40)

(define GAMEDAY 3600)	;number of ticks per game day
(define GAMEHOUR 150) 	;number of ticks per game hour

(define STAM_RATE	20)		;recovery rate for stamina
(define HEAL_RATE	15)		;recovery rate for health
(define MANA_RATE	5)		;recovery rate for mana

; Door states
(enum
	doorClosed
	doorClosing
	doorOpen
	doorOpening
)

; Character Classes
(enum
	FIGHTER
	MAGIC_USER
	THIEF
)

; Ego Moving Modes
(enum
	MOVE_WALK		;0
	MOVE_RUN		;1
	MOVE_SNEAK		;2
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
(enum
	iSilver			;0
	iRations		;1
	iSword			;2
	iChainmail		;3
	iLeather		;4
	iShield			;5
	iDagger			;6
	iLockPick		;7
	iThiefKit		;8
	iThiefLicense	;9
	iRock			;10
	iFlask			;11
	iHealingPotion	;12
	iManaPotion		;13
	iStaminaPotion	;14
	iDisenchant		;15
	iBrassKey		;16
	iMagicGem		;17
	iRing			;18
	iGhostOil		;19
	iMagicMirror	;20
	iMandrake		;21
	iFruit			;22
	iVegetables		;23
	iAcorn			;24
	iSeed			;25
	iFlowers		;26
	iGreenFur		;27
	iFairyDust		;28
	iFlyingWater 	;29
	iMushroom		;30
	iVase			;31
	iCandelabra		;32
	iMusicBox		;33
	iCandlesticks	;34
	iPearls			;35
	iCheetaurClaw	;36
	iTrollBeard		;37
	iGold			;38
	iPaper			;39
	iLastInvItem	;40	;this MUST be last
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
(define reNORTH 1)
(define reEAST	2)
(define reSOUTH 4)
(define reWEST	8)

; Nest Condition
(enum
	nestInTree
	nestOnGround
	nestBurnt
	nestGone
)

; Bar Drinks
(enum
	drinkNOTHING
	drinkALE
	drinkSWEAT
	drinkBREATH
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
	hutSTATE4
	hutGAVEGEM	; Now 5 instead of 4 like before
)

; Inn Meal States
(enum
	mealNOTHING
	mealORDERED
	mealATTABLE
	mealFINISHED
	mealCLEARED
)

; Kobold States
(enum
	koboldASLEEP
	koboldAWAKE
	koboldSTATE2
	koboldSTATE3
	koboldDEAD
)