;;; Sierra Script 1.0 - (do not remove this comment)
;**************************************************************
;***
;***	GAME.SH--
;***
;**************************************************************


(include system.sh) (include sci2.sh) ;system and kernel functions
(include talkers.sh) (include verbs.sh) ;message defines

;
; Global stuff

(define	MAIN			0)
(define DEBUG			10)
(define DISPOSE			11)
(define COLORINIT		12)
(define ABOUT			13)
(define SQ5INV			15)
(define DEBUG_INV		16)
(define SMOPPER			17)
(define SQ5EGO			18)
(define GRAVMOVE		19)
(define DEATHROOM		20)
(define FLICKER			21)
(define PRITALK			22)
(define SQ5CONTROLS		24)
(define VELOCITY_MOVER	25)
(define SCROLLINV		26)
(define SCROLLINSET		27)
(define ANIMDLG			30)
(define COLORBTN		32)
(define SPEAKWIN		877)
(define PUNCHCARD		2000)

;
; Actual rooms

; Regions
(define rgGenetix		31)
(define rgStarCon		109)
(define rgEureka		210)
(define rgKiz			350)
(define rgSBar			505)

; Indices for the icons in the icon bar
(enum
	ICON_WALK 		;0
	ICON_LOOK		;1
	ICON_DO			;2
	ICON_TALK		;3
	ICON_ORDER		;4
	ICON_ITEM		;5
	ICON_INVENTORY	;6
	ICON_CONTROL	;7
	ICON_HELP		;8
)

;Inventory items
(enum
	iBuckazoids				;0
	iFloorScrubber			;1
	iSafetyCones			;2
	iDistributorCap			;3
	iTransporterFuse		;4
	iAntacid				;5
	iShipOpener				;6
	iCuttingTorch			;7
	iSpike					;8
	iOxygenTank				;9
	iCommunicator			;10
	iHolePunch				;11
	iCloakingDevice			;12
	iSpaceMonkeysPackage	;13
	iBusinessCard			;14
	iGenetixCanister		;15
	iLiquidNitroTank		;16
	iKizBranch				;17
	iKizFruit				;18
	iFrock					;19
	iWD40Head				;20
	iOxygenMask				;21
	iPaper					;22
)

;WD-40 states
(enum
	WD40hostile
	WD40blownUp
	WD40repaired
)

;Talker scripts
(define tlkRoger	1880)
(define tlkBea		1882)
(define tlkQuirk	1883)
(define tlkCliffy	1884)
(define tlkDroole	1885)
(define tlkFlo		1886)
(define tlkWD40		1887)

;Message to call in EgoDead
(enum 1
	deathSLACKER		;1
	deathTESTFAIL		;2
	deathCHEATER		;3
	deathPOWERFAILURE	;4
	deathLIFESUPPORT	;5
	deathENCORE			;6
	deathBLOBBED		;7
	deathDECOMPRESSED	;8
	deathPUKED			;9
	deathAIRLOCK		;10
	deathSELFDESTRUCT	;11
	deathDUMB			;12
	deathNOAIRTANK		;13
	deathOUTOFAIR		;14
	deathDUMMY1			;15
	deathNOGAS			;16
	deathWD40			;17
	deathWD40TUNNEL		;18
)

(define deathNOMASK			28)
(define deathWRONGSECTION	32)
(define deathGENETIXGUARD	33)
(define deathSQUISHED		34)
(define deathPHASERONBLOB	35)
(define deathFORGOTDISTCAP	36)
(define deathDEFEATIST		39)
(define deathBREAKBEA		47)
(define deathFORGOTNITRO	49)
(define deathNOTORCH		51)

;Event flags
(enum
	fIsVGA					;0
	fAttendedClass			;1
	fSawArgument			;2
	fJanitorClosetOpen		;3
	fConesOnFloor			;4
	fScrubberOnFloor		;5
	fCleanedCrest			;6
	fSawMouseInHall			;7
	fSawElvis				;8
)
(define fEgoIsFly				22)
(define fEgoHumanAgain			23)
(define fInstalledDistCap		59)
(define fInGoliathChamber		68)
(define fSetSelfDestruct		84)
(define fGotCliffy				87)
(define fSawVaderAndObiWan		107)
(define fScrubberPlaced			122)
(define fScrubberOn				123)
(define f166AceTest				162)
(define f135TakeTest			160)
(define f135CheatTest			161)
(define f122GetCones			163)
(define f122GetScrubber			164)
(define f119CrestClean			165)
(define f801GetCliffy			192)
(define f801ReturnToEureka		193)
(define fSawPacman				124)
(define f800InfiltratedGoliath	196)
(define f800CutHole				197)
(define f850BeatQuirk			215)
(define fGotNitroTank			234)
(define fPunchedHolesInCard		238)
(define fEnteredDriveBay		244)
(define f1005InstallCap			245)
(define f1010EnterGrate			246)
(define f1020ShieldSwitch		247)
(define f1025FlipSwitch			248)
(define f1050PukoidsTransformed	249)
(define f1040CallCliffy			250)


;Views
(define vEgo				0)
(define vEgoStarcon			1)
(define vEgoStand			2)
(define vEgoStandStarcon	3)
(define vEgoRunning			4)

;Cursor views
(define vWalkCursor 	980)
(define vLooKCursor		981)
(define vDoCursor		982)
(define vTalkCursor		983)
(define vCommandCursor	984)
(define vExitCursor		986)
(define vHelpCursor		989)

; Interface views
(define vIcons	990)
	(enum
		lIconWalk
		lIconLook
		lIconHand
		lIconTalk
		lIconInvItem
		lIconInventory
		lIconDummy1
		lIconControls
		lIconDummy2
		lIconHelp
		lIconCommand
		lIconDummy3
		lIconDummy4
		lIconDisabled
		lIconFly
		lIconScrub
	)
(define vGameControls	995)
	(enum
		lSliderText
		lControlFixtures
		lSaveButton
		lRestoreButton
		lRestartButton
		lQuitButton
		lAboutButton
		lHelpButton
		lOKButton
		lControlScore
	)