;;; Sierra Script 1.0 - (do not remove this comment)
(include system.sh) (include sci2.sh)
(include verbs.sh) (include talkers.sh)

;Global scripts
(define GLORY_INIT			1)
(define TIME				7)
(define TARGET				8)
(define CASTAREA			12)
(define CASTOPEN			13)
(define CHARSHEET			15)
(define GLORY_INV			16)
(define SPEED				17)
(define GLORY_DEBUG			18)
(define GLORY_ABOUT			19)
(define GLORY_ACTIONS		20)
(define GLORY_MAGIC			21)
(define GLORY_WINDOW		22)
(define TELLER				23)
(define GLORY_CONTROL		25)
(define EGODEAD				26)
(define STARTAROOM			27)
(define GLORY_EGO			28)
(define CHEST				29)
(define OPEN_GIFT			30)
(define LEVITATION			31)
(define PROJECTILE 			32)
(define OCC_CYCLE			33)
(define UHURA_TALKER		34)
(define RAKEESH_TALKER		35)
(define JOHARI_TALKER		36)
(define CASTFETCH			37)
(define ARRAY_PATH			38)
(define YESUFU_TALKER		39)
(define HARAMI_TALKER		40)
(define MONkEY_TALKER		41)
(define LAIBON_TALKER		42)
(define SHAMAN_TALKER		43)
(define LEADER_TALKER		44)
(define STAFF_SCRIPT		46)
(define VENDOR				47)
(define PRIESTESS_TALKER	48)
(define KREESHA_TALKER		49)
(define PANORAMA			50)
(define BAZAAR				51)
(define CHARSAVE			52)
(define STORY_TALKER		53)
(define IMPORT				54)
(define COMBAT_CONTROLS		56)
(define JUMPX				57)
(define ELDER_TALKER		58)
(define GLORY_TALKER		59)
(define INTRO				61)
(define CASTJUGGLE			62)
(define OPENING_CREDITS		63)
(define OPENING				100)
(define WHERE_TO			101)
(define CHARSEL				140)
(define INIT_ADDTOS			403)
(define MCHASE				404)

; howFast values
(enum
	slow
	medium
	fast
	fastest
)

; General Game Defines
(define NUM_STATS 5)
(define NUM_SKILLS 10)
(define NUM_DERIVS 5)
(define NUM_SPELLS 14)
(define NUM_ATTRIBS 34) ;(+ NUM_STATS NUM_SKILLS NUM_DERIVS NUM_SPELLS)
(define NUM_INVITEMS 46) ;(- iLastInvItem 1)

(define QG1_NUM_ATTRIBS 25)
(define	QG2_NUM_ATTRIBS 30)

(define GAMEDAY 3600)	;number of ticks per game day
(define GAMEHOUR 150) 	;number of ticks per game hour

; Inventory
(enum
	iRoyals 	;0		;Dinars and Commons are stored in global variables 424 and 410, respectively.
	iSword		;1
	iFineDagger	;2
	iFineSpear	;3
	iChainmail	;4
	iShield		;5
	iGrapnel	;6
	iToolkit	;7
	iGuildCard	;8		;Unused; item data and description exist for this, but there is no corresponding icon.
	iTinderbox	;9
	iDaggers	;10
	iCurePills	;11
	iHealPills	;12
	iManaPills	;13
	iRations	;14
	iWaterskin	;15
	iDispell	;16
	iFish		;17
	iMeat		;18
	iFruit		;19
	iBeads		;20
	iSkins		;21
	iHorn		;22
	iRocks		;23
	iVine		;24
	iOil		;25
	iRope		;26
	iGagGift	;27
	iPin		;28
	iHoney		;29
	iFeather	;30
	iAmulet		;31		;Unused; you can't buy from the Amulet seller, and there is no corresponding icon or description.
	iLeopard	;32
	iBird		;33
	iOpal		;34
	iVineFruit	;35
	iGem		;36
	iPeaceWater	;37
	iHeartGift	;38
	iOrchid		;39
	iRobe		;40
	iBridge		;41
	iEye		;42		;Unused; the Anubis statue just has the Fire Opal item, and there is no corresponding icon.
	iNote		;43
	iWood		;44
	iMagicSpear	;45
	iMagicDrum	;46
	iLastInvItem	;this marks where inventory items end
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

; Bargain results
(enum 1
	 bargainSUCCESS
	 bargainTRY1
	 bargainTRY2
	 bargainTRY3
	 bargainFAIL
	 bargainNODEAL
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
	EXPER		;15		;Unused
	HEALTH		;16
	STAMINA		;17
	MANA		;18
; Magic Spells
	OPEN				;19
	DETMAGIC			;20
	TRIGGER				;21
	DAZZLE				;22
	ZAP					;23
	CALM				;24
	FLAMEDART			;25
	FETCH				;26
	FORCEBOLT			;27
	LEVITATE			;28
	REVERSAL			;29
	JUGGLE				;30
	STAFF				;31
	LIGHTNING			;32
	HEALING				;33
	ENDSTATS			;34	;scrapped spell?
)

; Icons
(enum 
	ICON_LEFT	;left border of icon bar
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
	ICON_RIGHT	;right border of icon bar
)

;Sword states
(enum
	swordNormal
	swordMagic
)

;Event flags
(enum 1
	fWornOut			;1
	fHungry				;2
	fStarving			;3
	fUnused4			;4
	fOverloaded			;5
	fInMainGame			;6
	fFlag7				;7
	fReversal			;8
)
(define fFlag14 14)
(define fCantBePaladin 19)
(define fFlag31 31)
(define fFlag34		34)
(define fRakeeshSworeOath 35)
(define fJohariReleased		65)
(define fCanSummonStaff 69)
(define fEgoIsAsleep 81)
(define fFlag88	88)
(define fWentIntoLeopardmanVillage	92)
(define fCharSheetActive 101)
(define fEnteredLeopardmanVillage 102)
(define fManuReleased	109)
(define fFedHarami 113)
(define fPoisoned 115)
(define fGotOrchid 116)
(define fSpeedDisabled 119)
(define fFlag121 121)
(define fHadMeeting 130)
(define fNightPaletteDisabled 131)
(define fEnteredGuardianDen	132)
(define fNeedStaff	133)
(define fVisitedBazaar 135)
(define fFakeDeath 140)
(define fStartedEncounter	143)
(define fWasWizard	144)
(define fGotBlackBird	147)
(define fSenseDanger 150)
(define fHonorShield 151)
(define fBabaFrog 158)
(define fFlag162 162)
(define fGotFineDagger	166)
(define fGotFineSpear	167)

(define fLearnFlamingSword		202)
(define fLearnHealing			203)
(define fLearnSenseDanger		204)
(define fLearnHonorShield		205)
(define fLearnJugglingLights	206)

(define fExchangedDinars		213)
(define fBoughtZebraSkin		219)
(define fBoughtWaterskin		220)
(define fBoughtFineSpear		222)
(define fBoughtTinderbox		225)
(define fBoughtBlackBird		226)
(define fBoughtFineDagger		227)

;Monster arenas
(define vDinosaur 560)
(define vAnt 565)
(define vGoblin 570)	;unused
;EO: Goblins are mentioned in 550.MSG and the Famous Explorer's Correspondence Course.
(define vApeman 575)
(define vCobra 580)
(define vCroc 585)
(define vDeWorm 590)	;demon worm
(define vLeopardmanEnemy 595)
(define vDemon 845)
(define vGargoyle 855)
(define vMirrorMonster 860)