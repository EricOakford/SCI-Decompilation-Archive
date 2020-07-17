;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)

;Inventory items
(enum
	iBuckazoid		;0
	iRope			;1
	iBomb			;2
	iRabbit			;3
	iBattery		;4
	iJar			;5
	iPaperWithGum	;6
	iOxygenTank		;7
	iHintbook		;8
	iPen			;9
	IAtmCard		;10
	iPlug			;11
	iCigar			;12
	iMatches		;13
	iDiskette		;14
	iLaptop			;15
)

;Icons
(enum
	ICON_WALK		;0
	ICON_LOOK		;1
	ICON_DO			;2
	ICON_TALK		;3
	ICON_SMELL		;4
	ICON_TASTE		;5
	ICON_ITEM		;6
	ICON_INVENTORY	;7
	ICON_CONTROL	;8
	ICON_HELP		;9
)

;Game-specific verbs
;(for generic verbs, refer to SYSTEM.SH)
(enum 10
	verbTaste
	verbSmell
)

;Space Quest framework scripts
(define	AUDSCRIPT		765)
(define	DEBUG			800)
(define	DISPOSE			801)
(define	COLORINIT		802)
(define	SPEED			803)
(define	BELTWAY			804)
(define	REGPATH			806)
(define	MOVETOCOORDS	807)
(define	NOSEPICK		808)
(define	INERTIA			809)
(define	ROBOT_MANAGER	810)
(define	ABOUT			811)
(define	SQROOM			812)
(define SQEGO			813)
(define TALKICON		814)
(define	SQDIALOG		816)
(define SQNARRATOR		817)
(define	SQGCONTROL		818)
(define	ZOID			819)
(define SQFEATURE		820)
(define EGODEAD			900)

;Regions
(define MALL 700)
(define STREET 701)
(define SEWER 702)
(define BUTTE 703)
(define BRAIN 704)
(define BUNNY 705)
(define ULENCE 706)
(define INTRO 707)
(define HINTBOOK 708)
(define LANDING 709)

;Death reasons
(enum
	deathGENERIC
	deathSMOKING
	deathSHOPLIFTING
	deathDILLYDALLY
	deathBLOWNUP
	deathFORMATTED
	deathSTIFF
	deathFORCEFIELD
	deathFALLSTREETS
	deathSTANDTHERE
	deathKNEWCOMING
	deathVOHAULWINS
	deathZEROIQ
	deathSLIME
	deathSKATELUMP
	deathSKATEORAMA
	deathORTEGA
	deathZAPZAP
	deathWORTHTRY
	deathLEAVEFORMATTING
	deathDROIDBLAST
	deathSPEARED
	deathFALLHANGAR
	deathDROIDTAGGED
	deathALLOVER
	deathMALLSECURED
	deathLEAVEMALL
	deathSLUG
	deathSPLEEN
	deathCHANGEMACHINE
	deathORBS
)

;Death icons
(enum
	iconDEAD
	iconLIGHTNING
	iconSPEAR
	iconSLIME
	iconSLUG
	iconMELTED
	iconEXPLODE
	iconDEAD_DRESS
	iconLASER
	iconLASER_DRESS
)

;Event flags
(enum
	fPoliceLanded				;0
	fFindTimepod				;1
	fTimeTravel					;2
	fSpOnBelt 					;3
	fSlugBait					;4
	fTimepodInArcade			;5
	fTallGuyBrokeSign			;6
	fMeltedLock					;7
	fSocksClosed				;8
	fArcadeClosed				;9	;NOTE: This flag is tested, but never set, so the arcade is never closed.
	fBigTallClosed				;10
	fMonolithBurgerClosed		;11
	fRadioShockClosed			;12
	fSoftwareExcessClosed		;13
	fUnused14					;14
	fMetBikers					;15
	fPoliceArrive				;16
	fBoughtNewPants				;17
	fExitedSewers				;18
	fPoliceAtArcade				;19
	fPoliceAtCeiling			;20
	fIsVGA						;21
	fPoliceInSkateORama			;22
	fPoliceAtMallEntrance		;23
	fBeenAtSoftwareExcess		;24
	fFreaksAtSoftwareExcess		;25
	fFirstTimeTravel			;26
	fDeletedKQ43				;27
	fDeletedLL4					;28
	fFlag29						;29	;NOTE: This flag is tested in rm371's doit: and the Monolith Burger minigame warning, but is never set
	fPoliceLookForEgo			;30
	fBoughtHintbook				;31
	fMeetLatexBabes				;32
	fATMCardDeclined			;33
	fFindSecretButton			;34
	fEnterArcadeTimepod			;35
	fEscapeMall					;36
	fSpFellInNest				;37
	fDeletedDroids				;38
	fGetRope					;39
	fGetLaptop					;40
	fCatchBunny					;41
	fEnterGrate					;42
	fHideInCompartment			;43
	fReturnBomb					;44
	fAvoidDeathDroid			;45
	fZapTentacles				;46
	fGetOxygenTank				;47
	fKillSlug					;48
	fBuyDress					;49
	fWearDress					;50
	fWorkedAtMonolithBurger		;51
	fSkippedBurgerMinigame		;52	;lose 3 points
	fGetJar						;53
	fSeeProfessorMessage		;54
	fGetGum						;55
	fEnterHangarTimepod			;56
	fUnused57					;57
	fBeamsRevealed				;58
	fBeamsAligned				;59
	fUseLaptopInBrain			;60
	fDroidsGone					;61
	fStartFormatting			;62
	fUnused63					;63
	fGetDiskette				;64
	fInsertDiskette				;65
	fWonGame					;66
	fGotBattery					;67
	fOpenComputerDoor			;68
	fGotBomb					;69
	fMonolithBurgerFired		;70
	fPlugInLaptop				;71
	fBatteryInLaptop			;72
	fClimbOutManhole			;73
	fGetSlime					;74
	fKickBikes					;75
	fGotMatches					;76
	fDodgedBikers				;77
	fUnused78					;78
	fReachVohaulsChamber		;79
	fGotATMCard					;80
	fGotCigar					;81
	fPayForPants				;82
	fATMCardAccepted			;83
	fBuyPlug					;84
	fPayForHintbook				;85
	fGoToUlence					;86
	fStealTimepod				;87
	fEnterProgrammingChamber	;88
)