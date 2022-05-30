;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here
(include system.sh) (include sci2.sh)
(include Talkers.sh) (include Verbs.sh)

(define GLORY_INIT	1)
(define GLORY_ROOM	5)
(define TIME		7)
(define TARGET		8)
(define CASTAREA	12)
(define CASTOPEN	13)
(define CHARSHEET	15)
(define GLORY_INV	16)
(define DEBUG		18)
(define GLORY_ABOUT	19)
(define GLORY_ACTIONS	20)
(define GLORY_MAGIC	21)
(define TELLER	23)
(define GLORY_CONTROLS	25)
(define EGODEAD	26)
(define STARTAROOM	27)
(define GLORY_EGO	28)
(define GLORY_ICONS	36)
(define CASTFETCH	37)
(define CHARSAVE	52)
(define IMPORT		54)
(define ZCODE		90)

;Talker scripts
(define NIKOLAI_TALKER	64)
(define BURGO_TALKER	65)
(define GATEKEEPER_TALKER	66)
(define GNOME_TALKER	67)
(define KATRINA_TALKER	68)
(define CRANIUM_TALKER	69)
(define TOBY_TALKER		70)
(define TANYA_TALKER	71)
(define DOMOVOI_TALKER	72)
(define IGOR_TALKER	73)
(define AVIS_TALKER	74)
(define ANNA_TALKER	75)
(define HANS_TALKER	76)
(define FRANZ_TALKER	77)
(define IVAN_TALKER		78)
(define LESHY_TALKER	79)
(define GYPSY_TALKER	92)
(define BELLA_TALKER	93)
(define OLGA_TALKER	94)
(define KATRINA_HOOD_TALKER	95)
(define KATRINA_RED_TALKER	96)
(define KATRINA_BLACK_TALKER	97)
(define INNKEEPER_TALKER	98)
(define TANYA_VAMP_TALKER	99)
(define TOBY_NICE_TALKER	101)
(define CHIEF_TALKER	103)
(define ERASMUS_TALKER	104)
(define FENRUS_TALKER	105)
(define NIKOLAI_GHOST_TALKER	106)

; General Game Defines
(define QG1_NUM_ATTRIBS 25)
(define	QG2_NUM_ATTRIBS 30)
(define QG3_NUM_ATTRIBS 34)
(define NUM_ATTRIBS 42)
(define NUM_SPELLS 22)
(define NUM_INVITEMS 56) ;(- iLastInvItem 1)

(define GAMEDAY 3600)	;number of ticks per game day
(define GAMEHOUR 150) 	;number of ticks per game hour

;Inventory items
(enum
	iPurse			;0
	iManaFruit		;1
	iPoisonCure		;2
	iHealingPotion	;3
	iRations		;4
	iThrowdagger	;5
	iRocks			;6
	iCandle			;7
	iCandy			;8
	iFlask			;9
	iWater			;10
	iGruegoo		;11
	iBonemeal		;12
	iLockpick		;13
	iKeyRing		;14
	iOil			;15
	iGrapnel		;16
	iArmor			;17
	iShield			;18
	iSword			;19
	iDagger			;20
	iFlint			;21
	iGarlic			;22
	iGuildcard		;23
	iToolkit		;24
	iDarksign		;25
	iHat			;26
	iCorn			;27
	iPiepan			;28
	iBones			;29
	iBerries		;30
	iHumorbar		;31
	iRehydrator		;32
	iDoll			;33
	iBlackbird		;34
	iCloth			;35
	iChicken		;36
	iAmulet			;37
	iHair			;38
	iBroom			;39
	iFlowers		;40
	iWillowisp		;41
	iLocket			;42
	iStatue			;43
	iTorch			;44
	iJewelry		;45
	iKnob			;46
	iStaff			;47
	iBonsai			;48
	iHammer			;49
	iStake			;50
	iBag			;51
	iBoneRit		;52
	iBloodRit		;53
	iBreathRit		;54
	iSenseRit		;55
	iHeartRit		;56
	iLastInvItem	;57	;this MUST be last except for the keys
	;the remaining below are for the keyring
	iLabKey			;58
	iInnKey			;59	;unused, but has the same message as the crest key.
						;There is actually an unused message for a key for
						;the inn's chest. Maybe this was originally intended for that?
	iCryptKey		;60
	iGuildKey		;61
	iCrestKey		;62
	iBurgoKey		;63
)

;Key mask values
(define KEY_LAB		$0001)
(define KEY_INN		$0002)
(define KEY_CRYPT	$0004)
(define KEY_GUILD	$0008)
(define KEY_CREST	$0010)
(define KEY_BURGO	$0020)

;Sword states
(enum
	swordBATTERED
	swordFINE
	swordAXE
	swordPALADIN
)

;Shield states
(enum
	shieldNORMAL
	shieldPALADIN
)

;Armor states
(enum
	armorLEATHER
	armorPLATE
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

; Character Classes
(enum
	FIGHTER
	MAGIC_USER
	THIEF
	PALADIN
)

; Puzzle points by character class
(define puzzleFIGHTER $0001)
(define puzzleWIZARD $0002)
(define puzzleTHIEF $0004)
(define puzzlePALADIN $0008)

; Ego Moving Modes
(enum
	MOVE_WALK
	MOVE_RUN
	MOVE_SNEAK
)

; Action Bar Icons
(define ACTION_RUN $0001)
(define ACTION_SNEAK $0002)
(define ACTION_REST $0004)

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
	COMM		;13
	HONOR		;14
	ACROBATICS	;15
	EXPER		;16		;Unused
	HEALTH		;17
	STAMINA		;18
	MANA		;19
; Magic Spells
	OPEN			;20
	DETMAGIC		;21
	TRIGGER			;22
	DAZZLE			;23
	ZAP				;24
	CALM			;25
	FLAMEDART		;26
	FETCH			;27
	FORCEBOLT		;28
	LEVITATE		;29
	REVERSAL		;30
	JUGGLE			;31
	STAFF			;32
	LIGHTNING		;33
	FROSTBITE		;34
	RITUAL			;35
	INVISIBILITY	;36
	AURA			;37
	PROTECTION		;38
	RESISTANCE		;39
	GLIDE			;40
	HEALING			;41
	ENDSTATS		;42
)

; Battle results
(enum
	battleEGOLOST
	battleEGOWON
	battleEGORUNS
)

; Icons
(enum 
	ICON_WALK
	ICON_LOOK
	ICON_DO
	ICON_TALK
	ICON_ACTIONS
	ICON_CAST
	ICON_USEIT
	ICON_INVENTORY
	ICON_CONTROL
	ICON_HELP
)


;Event flags
(enum 1
	fWornOut		;1
	fHungry			;2
	fStarving 		;3
	fUnused4		;4
	fOverloaded		;5
	fInMainGame		;6
	fFlag7			;7
	fReversal		;8
)
(define fGeasWarning	11)
(define fPoisoned		14)
(define fSenseDanger	20)
(define fHonorShield	21)
(define fBabaFrog		22)
(define fWasWizard		29)
(define fMetBurgomeisterToday	32)
(define fIgorMissing	37)
(define fIgorDead		38)
(define fIgorSaved		48)
(define fCantSave		50)
(define fHideCursor		51)
(define fListenedHall	112)	;original Sierra name
(define fInPuzzle		147)
(define fSomeoneTalking	148)
(define fKnowBaba		150)	;original Sierra name
(define fUltimateJoke 	161)	;original Sierra name
(define fFlag165		165)
(define fLeshyName		176)	;original Sierra name
(define fRusalkaName	177)	;original Sierra name
(define fBarrierPhrase	186)	;original Sierra name
(define fGotManaFruit	189)
(define fGotHydration	198)	;original Sierra name
(define fDoneExercisingToday	204)
(define fKissedKatrina	234)
(define fLastDayToSaveIgor	238)
(define fGotCryptKey	268)
(define fMetAnnaTonight	338)
(define fSolvedTomb		361)	;original Sierra name
(define fHoldingTorch	373)
(define fAutoSave		379)
(define fRestartingGame	382)
(define fLearnedFlamingSword	385)
(define fBoughtBlackBird	387)	;flag set from QG3
(define fVoiceOn		400)
(define fRusalkaFriend	444)	;original Sierra name
(define fGotGlide		498)	;original Sierra name