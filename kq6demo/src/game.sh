;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here
(include system.sh) (include sci2.sh)
(include verbs.sh) (include talkers.sh)

; Global scripts
(define KQ6ICON 98)
(define COLOR_INIT	902)
(define KQ6CONTROLS	903)
(define KQ6INV	907)
(define DEBUG	911)
(define KQ6WINDOW	912)
(define PROCS	913)
(define DISPOSE 919)

;Inventory items
(enum
	iMap			;0
	iBoringBook		;1
	iBrick			;2
	iBrush			;3
	iHair			;4
	iClothes		;5
	iCoal			;6
	iDeadMansCoin	;7
	iDagger			;8
	iCoin			;9
	iEgg			;10
	iSkull			;11
	iFeather		;12
	iFlower			;13
	iFlute			;14
	iGauntlet		;15
	iCassimaHair	;16
	iHandkerchief	;17
	iHoleInTheWall	;18
	iHuntersLamp	;19
	iLetter			;20
	iLettuce		;21
	iMilk			;22
	iMint			;23
	iMirror			;24
	iNewLamp		;25
	iNail			;26
	iNightingale	;27
	iTicket			;28
	iParticiple		;29
	iPearl			;30
	iPeppermint		;31
	iNote			;32
	iPotion			;33
	iRabbitFoot		;34
	iRibbon			;35
	iRiddleBook		;36
	iRing			;37
	iRose			;38
	iRoyalRing		;39
	iSacredWater	;40
	iScarf			;41
	iScythe			;42
	iShield			;43
	iSkeletonKey	;44
	iSpellBook		;45
	iTeaCup			;46
	iPoem			;47
	iTinderBox		;48
	iTomato			;49
	iSentence		;50
	iInk			;51
	iEndItems		;52 ;end point for items
)

;Iconbar defines
(enum
	ICON_WALK
	ICON_DO
	ICON_LOOK
	ICON_TALK
	ICON_ITEM
	ICON_INVENTORY
	ICON_CONTROL
)	

;Regions
(define regionCROWN 10)
(define regionSACRED 20)
(define regionCLIFFS 21)
(define regionLABYRINTH 30)
(define regionWONDER 40)
(define regionBEAST 50)
(define regionMIST 60)
(define regionDEAD 70)
(define regionCASTLE 80)
(define regionBASEMENT 81)
(define SPEED 99)

;Views
(define vKQ6Window 930)

;Event flags
(define fBefriendedClown 10)
(define fKingQueenRevived 15)
(define fBeenAtCastleGate		18)
(define fBeenInBookstore		27)
(define fBeenInPawnshop		28)
(define fFragmented 38)
(define fEgoDead	44)
(define fIsVGA 48)
(define fSaveDisabled 49)
(define fFoundHair 143)
(define fUsedInk 151)
(define fDrankPotion 153)