;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)
(include talkers.sh) (include verbs.sh)

;Script defines
(define GKINIT	10)
(define COLORINIT	11)
(define GKICONBAR	12)
(define PROCS	13)
(define GK_CONTROLS	14)
(define GK_INV	15)
(define SPEED	17)
(define GKEGO	18)
(define DISPOSE	19)
(define GKNARRATOR	22)
(define GKINSET	23)
(define RECORDER	90)
(define TAPE_PLAYER	91)
(define GKABOUT	92)
(define DEBUG 99)
(define rgPark 401)
(define TELLER 920)

;Talker scripts
(define tlkEgo		1100)
(define tlkGrace	1101)
(define tlkSarge	1102)

;sound types for GKSound
(enum
	soundMUSIC
	soundSFX
)

;Icon bar defines
(enum
	ICON_WALK
	ICON_TAKE
	ICON_OPEN
	ICON_LOOK
	ICON_INVENTORY
	ICON_HELP
	ICON_OPERATE
	ICON_MOVE
	ICON_ASK
	ICON_TALK
	ICON_RECORDER
	ICON_CONTROLS
	ICON_USEITEM
	ICON_SCORE
)

;Inventory items
(enum
	iPhoEnvelope 	;0
	iMurderPhoto	;1
	iGradPhoto		;2
	iTweezers		;3
	iMagGlass		;4
	iGiftCert		;5
	iHundred		;6
	iPhonePage		;7
	iWolfPhone		;8
	iNews1810		;9
	iGuntJournal	;10
	iRitLetter		;11
	iMosKey			;12
	iDrumBook 		;13
	iVeveCopy 		;14
	iPoliceVeve		;15
	iBadge			;16
	iTracker		;17
	iCrocMask		;18
	iOil			;19
	iMusScale		;20
	iLakeScale		;21
	iTwoScales		;22
	iTatooTrace		;23
	iKeyEnvelope	;24
	iMosLetter		;25
	iClay			;26
	iSketchBook		;27	
	iSLakePatter	;28
	iVoodooCode1	;29
	iSVoodooCode	;30
	iRitPhoto		;31
	iLostDrawing	;32
	iLuckyDog		;33
	iSnakeSkin		;34
	iReconVeve		;35
	iCollar			;36
	iBraceMold		;37
	iBraceRep		;38
	iVeil			;39
	iFortScale		;40
	iSignalDev		;41
	iBrick			;42
	iHartNotes		;43
	iWolfLetter		;44
)

;Event flags
(enum
	fFlag0					;0
	fFlag1					;1
	fCopiedVeve				;2
	fStoleVeve				;3
	fTurnedUpThermostat		;4
	fFlag5					;5
	fFlag6					;6
	fFlag7					;7
	fFlag8					;8
	fBeignetHinted			;9		;original name
	fGraceAtBookstore		;10
	fBeenToLake				;11		;original name
	fFlag12					;12
	fFlag13					;13
	fFlag14					;14
	fDayIsDone				;15
	fCaseIsReopened			;16		;original name
	fFlag17					;17
	fFlag18					;18
	fFlag19					;19
	fFlag20					;20
	fFlag21					;21
	fFlag22					;22
	fFlag23					;23
	fFlag24					;24
	fFlag25					;25
	fFlag26					;26
	fFlag27					;27
	fFlag28					;28
	fFlag29					;29
	fFlag30					;30
	fFlag31					;31
	fFlag32					;32
	fFlag33					;33
	fFlag34					;34
	fFlag35					;35
	fGaveGracePattern		;36
)
(define fGotLakeScale 40)
(define fFlag43 43)
(define fFlag46 46)
(define fIsVGA 52)
(define fFlag55 55)
(define fGotPhotos 61)
(define fFlag62 62)
(define fFlag63 63)
(define fUsedCopRadio 64)
(define fFlag65 65)
(define fFlag66 66)
(define fFlag95 95)
(define fMetFranks 96)
(define fMetSarge 97)
(define fFlag98 98)
(define fCanEnterOfficialArea 99)
(define fFlag100 100)
(define fMimeFollowsEgo 101)
(define fFlag105 105)
(define fFlag106 106)
(define fFlag107 107)
(define fMimeAnnoyedCop 108)
(define LOOKED_AT_LAKE_PATTERN 109)
(define TURNED_ON_MUSEUM_FAN 110)
(define GAVE_LUCKY_DOG_TO_TAPPER 111)
(define OPENED_MUSEUM_COFFIN 112)
(define PUT_TRACKER_IN_COFFIN 113)
(define RUBBED_WISHING_STUMP 114)
(define f230GetPhotos 123)
(define POINTS_MIMEVSCOP 124)
(define POINTS_OPERATERADIO 125)
(define POINTS_GOTOLAKE 126)
(define POINTS_GETLAKESCALE 128)
(define fSpeedDisabled 198)
(define fLookingAtLakeGrass 199)
(define TALKED_TO_GRANNY 200)
(define VISITED_GRANNY 201)
(define VISITED_ATTIC 202)
(define fUsingMagnifyingGlass 218)
(define IN_INTRODUCTION 220)