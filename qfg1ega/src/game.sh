;;; Sierra Script 1.0 - (do not remove this comment)
(include system.sh)
(include sci2.sh)
(include gameEnumFlags.sh)
(include gameEnumPuzzlePoints.sh)
(include gameNums.sh)
(include menu.sh)

(define MAZEBUG 238)

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

; Priority States
(define LowPri 4) ;pRED
(define HighPri 7)	;pLGREY

;Hero's Quest Framework scripts
(define HQINIT			1)
(define NOTICE			2)
(define INVDESC			3)	;text only
(define DRINK 			5)
(define EAT 			6)
(define SLEEP 			7)
(define REST			8)
(define NOTICE2			9)

(define	INTRO			200)
(define	CHARSEL			202)
(define	CHARALLOC		203)
(define CHARSHEET		204)
(define	STATUSBAR		205)
(define INVSHEET		206)

(define	SPEED			299)

(define	ENDGAME			600)
(define CHARSAVE 		601)

(define DOORS			800)
(define STRING			802)
(define TALKOBJ		 	803)
(define KEYCURSOR		810)
(define TARGET			812)
(define TIME			813)
(define STARTAROOM		896)

; Locales and Regions
(define DEBUG		298)	;Locale
(define TOWN		801)	;Locale
(define FOREST		804)	;Locale
(define GHOSTS		805)	;Actors and Scripts for CEMETERY
(define CEMETERY	806)	;Region
(define CASTLE		807)	;Locale
(define STREET		811)	;Locale

;Combat Framework
;arena spell animations
(define ARENA_MAGIC  146)
(define ARENA_FLAME  147)
(define ARENA_ZAP 	 148)
(define ARENA_DAZZLE 149)
(define ARENA_CALM 	 150)

;arena combat animations
(define ARENA_THRUST 151)
(define ARENA_BLOCK	 152)
(define ARENA_PARRY	 153)
(define ARENA_DODGE	 154)
(define ARENA_PAIN	 155)

;core arena regions, classes and scripts
(define ENCOUNTER 	210) ;Region
(define ARENA 		211) ;Region
(define SKILLED 	212) ;Class
(define WARRIOR 	213) ;Class
(define MONSTER 	214) ;Class
(define CLOSECOMBAT 215) ;Script

;other scripts
(define	ABDULLA		166)
(define RENTAROOM	167)

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

; ///////////////////////////////////////////////////////
; Inventory
; ///////////////////////////////////////////////////////
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

; ///////////////////////////////////////////////////////
; Time Zones
; ///////////////////////////////////////////////////////
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

;TrySkill and SkillUsed amounts
;Skill Used amounts
(define tryStatThrowing 10) ;full define should be (/ [egoStats AGIL] 10) but SCICompanion doesn't support those types of defines
(define tryStatWeaponMaster 50)
(define tryStatKoboldDazzle 5)
(define tryStatStableStr 25)
(define tryStatStableVit 40)
(define tryStatRandomEncounter 12) ;full define should be (/ [monsterHP (GetMonsterIndex monsterInRoom)] 12) but SCICompanion doesn't support those types of defines

;TrySkill thresholds
(define tryPickSecretEntrance 85)
(define tryForceSecretEntrance 40) ;strength
(define tryBreakDownBrigandGate 60) ;strength
(define tryClimbBrigandGate 65)
(define tryClimbIntoTown 35)
(define tryFetchKoboldKey 35)
(define tryForceOpenKoboldChest 40) ;strength
(define tryPickKoboldChest 70)
(define tryCastOpenKoboldChest 50) ;open spell
(define trySneak 5)
(define trySneakKobold 35)
(define tryPickNose 40)
(define tryCastOpenFox 25) ;open spell
(define tryClimbSpittingSpirea 35)
(define tryClimbWaterfall 30)
(define tryCastOpenHenry 45) ;magic, specifically.
(define tryThrowHenry 25)
(define tryFaeryDance 25) ;agility
(define tryThrowNest 25)
(define tryPickLOL 30)
(define tryPickSheriff 50)
(define tryWalkTreeLimb 30) ;agility (the tree outside the healer's)
(define tryPickTavern 10)

;Random Encounter Entrances
(define reNORTH $0001)
(define reEAST	$0002)
(define reSOUTH $0004)
(define reWEST	$0008)

;note, the other creatures have their maxHP in a array in RandomEncounters.sc
(define MAX_HP_OGRE 112)
(define MAX_HP_BRUTUS 100)
(define MAX_HP_KOBOLD 67)
(define MAX_HP_BEAR 133)

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

; Mages Maze
(define ChaseRange 30)
	; Directions
(enum
	mazeN
	mazeNE
	mazeE
	mazeSE
	mazeS
	mazeSW
	mazeW
	mazeNW
)
; Bug Sizes
(enum
	smallBug
	mediumBug
	largeBug
)
; doCommands
(enum
	mmazeNOTHING
	mmazeCHOOSE
	mmazeFETCH
	mmazeOPEN
	mmazeTRIGGER
	mmazeFLAME
)

; Kobold States
(enum
	koboldASLEEP
	koboldAWAKE
	koboldSTATE2
	koboldSTATE3
	koboldDEAD
)


;Arena Actions (for the 'Skilled' class)
(enum
	ActNone
	ActThrust
	ActSlash
	ActParryUp
	ActParryDown
	ActDodgeLeft
	ActDodgeRight
	ActDuck
	ActLeap
	ActPain	;never used, just a guess
	ActDie
	ActCast
)

;Attack angles (for close combat)
(enum
	AttLeft
	AttRight
	AttStraight
)

;Armor values
(define LEATHER_VALUE 2)
(define CHAIN_VALUE 5)

;Weapon values
(define DAGGER_VALUE 5)
(define SWORD_VALUE 8)


; unique deaths
(define DIE_RESTART -2) ;show Restore, Restart, Quit instead of Retry, Restore, Quit)
(define DIE_NOFLAG 	-1)
(define DIE_START	450)
;there are roughly 100 deaths, so we'll reserve flags 450-550 for them (with room for 50 more in expansion)
(enum 450
	DIE_PICKNOSE
	DIE_ARRESTED
	DIE_CASTLEBARRACKS
	DIE_CASTLEGUARDS
	DIE_NIGHTGAUNT
	DIE_ARENA
	DIE_ARENA_CALM
	DIE_ARENA_UNARMED
	DIE_BEAR_CLOSE
)