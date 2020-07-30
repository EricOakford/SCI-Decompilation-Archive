;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)

; howFast values
(enum
	slow
	medium
	fast
	fastest
)

;Icon buttons
(enum
	ICON_WALK
	ICON_LOOK
	ICON_DO
	ICON_TALK
	ICON_SMELL
	ICON_TASTE
	ICON_ITEM
	ICON_INVENTORY
	ICON_CONTROL
	ICON_HELP
)

;Inventory items
(enum
	iCartridge			;0
	iKeyCard			;1
	iGadget				;2
	iSurvivalKit		;3
	iKnife				;4
	iDehydratedWater	;5
	iBrokenGlass		;6
	iRock				;7
	iOratPart			;8
	iSkimmerKey			;9
	iBuckazoid			;10
	;iKeronianAle
	iJetpack			;11
	iPulseray			;12
	iGrenade			;13
	iRemote				;14
	iWidget				;15
	iPlant				;16
	iBarCoupon			;17
	iDroidsBUsCoupon	;18
	iSarienIDCard		;19
)

;Verbs
(enum 8
	verbShowControl	;8
	verbHideControl	;9
	verbDummy		;10
	verbTaste		;11
	verbSmell		;12
)

;Deltaur clothing states
(enum
	egoSpacesuit
	egoWithHelmet
	egoNoHelmet
)

;Ulence Flats actors
(define BLUESBROS 300)
(define MADONNA 301)
(define ZZTOP 302)
(define SLOTGUY 303)

;Ship scripts
(define KEYPAD 400)
(define ASTEROIDS 401)
(define WARBIRD 402)

;Regions
(define ARCADA 700)
(define ULENCE 702)
(define DELTAUR 703)
(define KERONA 704)
(define VIEWCART	706)
(define DELTAUR_DEATH 707)

;Ship destination states
(enum
	shipNOSECTOR	;press the EXIT button; go to asteroid field
	shipWRONGSECTOR	;give three incorrect answers; either go to asteroid field or Romulan Warbird
	shipDELTAUR		;give correct code; go to Deltaur
)

;Space Quest framework scripts
(define	DEBUG			800)
(define	DISPOSE			801)
(define	COLORINIT		802)
(define	SPEED			803)
(define	REGPATH			806)
(define	MOVETOCOORDS	807)
(define	INERTIA			809)
(define	ABOUT			811)
(define	SQROOM			812)
(define SQEGO			813)
(define ELEVATOR		817)
(define	DSCRIPT			818)
(define	RANGEOSC		819)

;Event flags
(enum
	fLogging				;0
	fUnused1				;1
	fUnused2				;2
	fUnused3				;3
	fUnused4				;4
	fUnused5				;5
	fSeenHologram			;6
	fFirstExitPod			;7
	fScientistDead			;8
	fFlag9					;9	;this flag is tested and cleared when standing up from the data cartridge computer, but is never set
	fBayDoorsOpen			;10
	fSlotAlienDead			;11
	fWidgetOnSlots			;12
	fPlayedSlots			;13
	fHaveSkimmer			;14
	fTookSkimmerKey			;15
	fWearingSpacesuit		;16
	fBeenInArcadaLab		;17
	fDrilledIntoGround		;18
	fBoughtBadShip			;19
	fTinyFollows			;20
	fGrateMonsterStuck		;21
	fGotStalagmite			;22
	fPutRockInHole			;23
	fGotStoreCredit			;24
	fBoughtNavDroid			;25
	fBoughtSaucer			;26
	fBoughtRealShip			;27
	fBeenAtShipLot			;28
	fTrunkMovedToLaundry	;29
	fUnused30				;30
	fGrateMonsterAppears	;31
	fBoughtDefTechParts		;32
	fHaveNavDroid			;33
	fHidingInTrunk			;34
	fPushedTrunk			;35
	fOpenedVent1			;36
	fOpenedVent2			;37
	fZappedByBarrier		;38
	fSlotsDeath				;39
	fMuggerPloy				;40
	fGotMugged				;41
	fUnused42				;42
	fUnused43				;43
	fBoughtSUXDroid			;44
	fBeenInDroidsBUs		;45
	fLasersFried			;46
	fStarGeneratorGuardDead	;47
	fGadgetOn				;48
	fOratPartKnown			;49
	fForceFieldOff			;50
	fLeftDeltaurPodBay		;51
	fPodGone				;52
	fStartedSelfDestruct	;53
	fDeltaurSelfDestructs	;54
	fSpiderLanded			;55
	fPushedPodOffSkeleton	;56
	fSpiderDead				;57
	fElevatorGuardDead		;58
	fOratDead				;59
	fGotOratPart			;60
	fOpenedKit				;61
	fBeenInLaundry			;62
	fSlotMachineBroken		;63
	fUnused64				;64 
	fDisguiseMessage		;65
	fDroidWaitsOutsideBar	;66
	fFlag67					;67	;unknown; is tested at room 41, but is never set
	fGrellAppeared			;68
	fWasherOpen				;69
	fRockHasGoo				;70
	fGooInCave				;71
	fFlag72					;72 ;unknown; is tested at room 31, but is never set
	fFlag73					;73 ;unknown; is set at room 53 by the fire droid
)

;Puzzle point flags
(enum 130
	fGetCartridge			;130
	fLearnCartridgeName		;131
	f5GetKeyCard			;132
	f7GetWidget				;133
	f10OpenBayDoors			;134
	f10UnlockDoor			;135
	f11PutOnSpacesuit		;136
	f11GetGadget			;137
	f13EscapeArcada			;138
	f14AutoNav				;139
	fGetKit					;140
	fGetGlass				;141
	fGetPlant				;142
	fEnterCaves				;143
	fTrapGrateMonster		;144
	fPlugGeyser				;145
	fBlewUpLasers			;146
	fPassAcidDrops			;147
	fKillSpider				;148
	fKillOrat				;149
	fTakeOratPart			;150
	f35FlyOut				;151
	fLearnSelfDestructCode	;152
	f41EnterUlenceFlats		;153
	f41SellSkimmer			;154
	f43LearnDeltaurSector	;155
	f43PlaySlots			;156
	f46GetNavRobot			;157
	f45BuyRealShip			;158
	fExitKerona				;159
	fExitDeltaurAirlock		;160
	fUnused161				;161
	fUnused162				;162
	fExitVentilationShaft	;163
	fGetInWasher			;164
	fGetGrenade				;165
	f58GetWeapon			;166
	fUnused167				;167
	fKillStarGeneratorGuard	;168
	fKillDeltaurGuard		;169
	fGetRemote				;170
	fTurnOffForceField		;171
	fSelfDestructPoints		;172
	fEnterDeltaurPodBay		;173
	fEnterDeltaurEscapePod	;174
	fGetSarienID			;175
)